package com.psu.est.service;

import com.psu.est.dao.interfaces.EmployeeDao;
import com.psu.est.dao.interfaces.JobDao;
import com.psu.est.dao.interfaces.LocationDao;
import com.psu.est.dao.interfaces.ScheduleDao;
import com.psu.est.model.Employee;
import com.psu.est.model.Job;
import com.psu.est.model.Location;
import com.psu.est.model.Schedule;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Created by gorzelic on 3/20/2016.
 */
@Service
public class ScheduleService {

    public static final java.time.DayOfWeek workWeekStart = DayOfWeek.MONDAY;
    public static final java.time.DayOfWeek workWeekEnd = DayOfWeek.FRIDAY;
    public static final java.time.LocalTime workDayStartTime = LocalTime.parse("08:00:00");
    public static final java.time.LocalTime workDayEndTime = LocalTime.parse("17:00:00");
    public static final java.time.Duration workDayBreaks = Duration.ofMinutes(60);
    public static final java.time.Duration workDayLength = Duration.between(workDayStartTime,workDayEndTime).minusMinutes(workDayBreaks.toMinutes());
    public static final java.time.Duration workDayStartOffset = Duration.between(LocalTime.parse("00:00:00"),workDayStartTime);
    public static final java.time.LocalTime lunchWindowStartTime = LocalTime.parse("11:00:00");
    public static final java.time.LocalTime lunchWindowStopTime = LocalTime.parse("14:00:00");
    // for conversions in and out of timestamp, assume for now all are in same timezone
    private ZoneId thisZoneId = ZoneId.systemDefault();  // or ZoneId.of("America/NewYork");
    private OffsetTime  thisOffsetTime = OffsetTime.now(thisZoneId);
    private ZoneOffset thisZoneOffset = thisOffsetTime.getOffset();

    // maximum travelDistance
    private static final double maxTravelDistance = 50.0;

    // tbd
    @Autowired
    private SessionFactory sessionFactory; // this exits in parent
    @Autowired
    private JobDao jobDao;
    @Autowired
    private LocationDao locationDao;
    @Autowired
    private ScheduleDao scheduleDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private LocationService locationService;

    // convert between java.time.ZonedDateTime and java.time.LocalDate time

    // ZonedDateTime to LocalDateTime is trivial since ZoneDateTime has a LocalDateTime
    // (+ timezone info)
    public java.time.LocalDateTime GetLocalDateTime(ZonedDateTime zonedDateTime) { return zonedDateTime.toLocalDateTime(); }
    public java.time.LocalDate GetLocalDate(ZonedDateTime zonedDateTime) { return zonedDateTime.toLocalDate(); }
    public java.time.LocalTime GetLocalTime(ZonedDateTime zonedDateTime) { return zonedDateTime.toLocalTime(); }
    // Going back not so much since we need timezone, for now we assume all is in same, but this is where
    // we would time localize instead of using thisZoneId
    public java.time.ZonedDateTime GetZoneDateTime(java.time.LocalDateTime localDateTime)
    {
        return ZonedDateTime.of(localDateTime, thisZoneId);
    }

    public java.time.ZonedDateTime GetZoneDateTime(java.time.LocalDate localDate)
    {
        // say this day starts at 00:00:00
        return ZonedDateTime.of(localDate,LocalTime.MIDNIGHT, thisZoneId);
    }

    public java.time.ZonedDateTime GetZoneDateTime (java.time.LocalDate localDate, java.time.LocalTime localTime)
    {
        return ZonedDateTime.of(localDate,localTime, thisZoneId);
    }

    // java.sql.timestamp (used in database as DATETIME) to and from ZonedDateTime
    public java.sql.Timestamp GetTimestamp(ZonedDateTime zonedDateTime)
    {
        return new Timestamp(zonedDateTime.toInstant().toEpochMilli());
    }
    // again assuming default timezone
    public java.time.ZonedDateTime GetZonedDateTime(java.sql.Timestamp timestamp)
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(timestamp.getTime()),thisZoneOffset);
    }

    // java.sql.Timestamp to and from LocalDateTime, LocalDate, LocalTime
    public java.sql.Timestamp GetTimestamp(LocalDateTime localDateTime)
    {
        return new Timestamp(localDateTime.toInstant(thisZoneOffset).toEpochMilli());
    }

    public java.sql.Timestamp GetTimestamp(LocalDate localDate)
    {
        LocalDateTime localDateTime = LocalDateTime.of(localDate,LocalTime.MIDNIGHT);
        return new Timestamp(localDateTime.toInstant(thisZoneOffset).toEpochMilli());
    }

    public java.sql.Timestamp GetTimestamp(LocalDate localDate, LocalTime localTime)
    {
        LocalDateTime localDateTime = LocalDateTime.of(localDate,localTime);
        return new Timestamp(localDateTime.toInstant(thisZoneOffset).toEpochMilli());
    }
    public java.time.LocalDateTime GetLocalDateTime(java.sql.Timestamp timestamp)
    {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp.getTime()),thisZoneOffset);
    }

    public java.time.LocalTime GetLocalTime(java.sql.Timestamp timestamp)
    {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp.getTime()),thisZoneOffset);
        return localDateTime.toLocalTime();
    }

    public java.time.LocalDate GetLocalDate(java.sql.Timestamp timestamp)
    {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp.getTime()),thisZoneOffset);
        return localDateTime.toLocalDate();
    }

    // set back to midnight on same date
    public java.sql.Timestamp TruncateToDate(java.sql.Timestamp timestamp)
    {
        // in and out of database is via Timestamp
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(
                Instant.ofEpochMilli(timestamp.getTime()),thisZoneOffset);
        zonedDateTime = zonedDateTime.truncatedTo(ChronoUnit.DAYS);
        return new Timestamp(zonedDateTime.toInstant().toEpochMilli());
    }
/*
    public void TruncateToDate(java.sql.Timestamp timestamp)
    {
        // in and out of database is via Timestamp
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(
                Instant.ofEpochMilli(timestamp.getTime()),thisZoneOffset);
        zonedDateTime = zonedDateTime.truncatedTo(ChronoUnit.DAYS);
        timestamp = new Timestamp(zonedDateTime.toInstant().toEpochMilli());
    }
 */
    public boolean IsTimeDuringWorkWeek(ZonedDateTime time)
    {
        java.time.DayOfWeek weekDay = time.getDayOfWeek();
        if ( weekDay.getValue() >= workWeekStart.getValue() && weekDay.getValue()<= workWeekEnd.getValue())
            return true;
        else
            return false;
    }



    Duration GetEmployeeTimeScheduledForInterval(Employee employee, ZonedDateTime startTime, ZonedDateTime endTime)
    {
        List<Schedule> employeeSchedule = scheduleDao.GetScheduleByIntervalAndEmployeeID(startTime, endTime, employee.getEmployeeId());
        Duration timeScheduled = Duration.ofMinutes(0);
        for (Schedule schedule : employeeSchedule)
        {
            long thisTimeInSeconds = 0;
            if (schedule.getType().equalsIgnoreCase("JOB"))
                thisTimeInSeconds = (schedule.getTravelTime() + schedule.getDuration())*60;
            else {
                LocalDateTime sStartTime = GetLocalDateTime(schedule.getStartTime());
                LocalDateTime sEndTime = GetLocalDateTime(schedule.getEndTime());
                thisTimeInSeconds = Duration.between(sStartTime, sEndTime).getSeconds();
            }
            timeScheduled = Duration.ofSeconds(timeScheduled.getSeconds() + thisTimeInSeconds);
        }
        return timeScheduled;
    }

    public class Employee_Distance implements Comparable<Employee_Distance>{
        Employee employee;
        double  distance;
        Employee_Distance(Employee employee, double  distance)
        {
            this.employee = employee;
            this.distance = distance;
        }
        @Override
        public int compareTo(Employee_Distance o) {
            return (int)(distance - o.distance);
            //return 0;
        }
    }

    // public for testing
    public boolean AssignJobToEmployee(Job job, Employee employee)
    {
        if ( job == null || employee == null )return false;
        // get list of jobs on same day as job
        ZonedDateTime jobSchedulingWindowStart = GetZonedDateTime(TruncateToDate(job.getJobDate()));
        ZonedDateTime jobSchedulingWindowEnd = jobSchedulingWindowStart.plusHours(24);
        Location employeeHome = locationDao.get(employee.getBaseLocationId());
        List<Schedule> scheduleAssignments = scheduleDao.GetScheduleByIntervalAndEmployeeID(jobSchedulingWindowStart, jobSchedulingWindowEnd, employee.getEmployeeId());
        // get jobs and list of locations
        List<Location> wayPoints = new ArrayList<Location>();
        Map<Integer, Job> locationToJobMap = new HashMap<Integer, Job>();
        for (Schedule item : scheduleAssignments)
        {
            if (item.getType()=="JOB")
            {
                Job thisJob = jobDao.get(item.getJobId());
                wayPoints.add(locationDao.get(thisJob.getJobLocation()));
                locationToJobMap.put(thisJob.getJobLocation(),thisJob);
            }
        }
        // add the job to be scheduled as additional wayPoint
        wayPoints.add(locationDao.get(job.getJobLocation()));
        locationToJobMap.put(job.getJobLocation(),job);
        // map of travel times (in minutes) between location IDs
        Map<Integer, LocationService.RouteMetric> travelMetrics = new HashMap<Integer,LocationService.RouteMetric>();
        List<Location> BestRoute = OrderByBestRoute(employeeHome, wayPoints, travelMetrics);
        if (BestRoute==null)
            return false;
        // if we were successful, remove all previous job assignments since they could be completely reordered
        // including lunch
        for (Schedule assgn : scheduleAssignments)
        {
            if (assgn.getType()=="JOB")
            {
                Job thisJob = jobDao.get(assgn.getJobId());
                thisJob.setJobState("UNASSIGNED");
                thisJob.setJobDate(GetTimestamp(jobSchedulingWindowStart));
                jobDao.update(thisJob);
            }
            // to do, initialize job states
            scheduleDao.delete(assgn);
        }
        ZonedDateTime schedCursor = jobSchedulingWindowStart;
        schedCursor = AdvanceTimeCursor(schedCursor,workDayStartOffset.toMinutes() );
        boolean isLunchScheduled = false;
        for (int i=1; i<BestRoute.size(); i++)
        {
            LocationService.RouteMetric routeMetric = travelMetrics.get
                    (LocationService.GenUniqueKey(BestRoute.get(i-1).getLocationId(),BestRoute.get(i).getLocationId()));
            if (i == (BestRoute.size()-1))
            {
                // this is the trip home, schedule special job traveltime only
                schedCursor = ScheduleNonJob(schedCursor, routeMetric.travTimInMins, "DRIVEHOME", employee);
                break;
            }
            Job thisJob = locationToJobMap.get(BestRoute.get(i).getLocationId());
            thisJob.setJobState("SCHEDULED");
            Schedule jobAssign = new Schedule();
            jobAssign.setType("JOB");
            jobAssign.setEmployeeId(employee.getEmployeeId());
            jobAssign.setJobId(thisJob.getJobId());
            jobAssign.setTravelTime(routeMetric.travTimInMins);
            schedCursor = AdvanceTimeCursor(schedCursor,routeMetric.travTimInMins );
            jobAssign.setStartTime(GetTimestamp(schedCursor));
            thisJob.setJobDate(GetTimestamp(schedCursor));
            jobAssign.setDuration(thisJob.getJobDuration());
            schedCursor = AdvanceTimeCursor(schedCursor,thisJob.getJobDuration());
            jobAssign.setEndTime(GetTimestamp(schedCursor));
            jobDao.update(thisJob);
            scheduleDao.persist(jobAssign);
            if (!isLunchScheduled && CheckScheduleLunch(schedCursor))
            {
                //schedule lunch
                schedCursor = ScheduleNonJob(schedCursor, (int)workDayBreaks.toMinutes(), "BREAK", employee);
                isLunchScheduled = true;
            }

        }
        // if we made it this far schedule was a success
        return true;
    }

    ZonedDateTime AdvanceTimeCursor(ZonedDateTime cursor, long advMinutes){
        return cursor.plusMinutes(advMinutes);
    }

    boolean CheckScheduleLunch(ZonedDateTime cursor)
    {
        java.time.LocalTime timeOfDay = GetLocalTime(cursor);
        if (timeOfDay.isAfter(lunchWindowStartTime) && timeOfDay.isBefore(lunchWindowStopTime) )
            // it is it lunch time window
            return true;
        else
            return false;
    }

    ZonedDateTime ScheduleNonJob(ZonedDateTime cursor, int NonJobDurationMins, String NonJobType, Employee employee)
    {
        Schedule nonJob = new Schedule();
        nonJob.setType(NonJobType);
        nonJob.setEmployeeId(employee.getEmployeeId());
        nonJob.setStartTime(GetTimestamp(cursor));
        if (NonJobType.equalsIgnoreCase("DRIVEHOME"))
        {
            nonJob.setTravelTime(NonJobDurationMins);
            nonJob.setDuration(0);
        }
        else
        {
            nonJob.setTravelTime(0);
            nonJob.setDuration(NonJobDurationMins);
        }
        cursor = AdvanceTimeCursor(cursor,NonJobDurationMins);
        nonJob.setEndTime(GetTimestamp(cursor));
        scheduleDao.persist(nonJob);
        return cursor;
    }

    // wrapper for traveling salesmen solver
    // public for testing
    public List<Location> OrderByBestRoute(Location employeeHome, List<Location> wayPoints, Map<Integer, LocationService.RouteMetric> travelMetrics)
    {
        // first implementation uses googlemaps to solve
        return locationService.SolveTSP(employeeHome, wayPoints, travelMetrics);

        // second will use distance matrix API + own solver (tbd)
    }

    private boolean ScheduleJob(Job job, double max_distance)
    {
        //SortedMap<Employee_Distance, Employee> distanceMap = new TreeMap<Employee_Distance, Employee>();
        PriorityQueue<Employee_Distance> distanceQueue = new PriorityQueue<Employee_Distance>();
        //SortedSet<Employee_Distance> distanceSet = new TreeSet<Employee_Distance>();

        List<Employee> candidateList = employeeDao.getListByRole("TECHNICIAN");
        Location jobLocation = locationDao.get(job.getJobLocation());
        // truncate job startime to midnight and set endtime to 24hrs later
        ZonedDateTime jobSchedulingWindowStart = GetZonedDateTime(TruncateToDate(job.getJobDate()));
        // this could be passed as a parameter if need to increase window to find feasible schedule
        ZonedDateTime jobSchedulingWindowEnd = jobSchedulingWindowStart.plusHours(24);
        if (jobLocation == null)
        {
            //tbd - log error
            return false;
        }
        // remove those that are out of range
        for (Employee employee : candidateList)
        {
            Location empLocation = locationDao.get(employee.getBaseLocationId());
            if (employee.getBaseLocationId()== null)
            {
                // tbd log error
                // candidateList.remove(employee);
            }
            double empDistance = locationService.distance(empLocation,jobLocation);
            if (empDistance > max_distance)
            {
                //candidateList.remove(employee);
            }
            else
            {
                distanceQueue.add(new Employee_Distance(employee,empDistance));
            }
        }
        while (distanceQueue.size() > 0 )
        {
            Employee_Distance employee_distance = distanceQueue.poll();
            // find time already scheduled for entire day
            Duration alreadyScheduledTime = GetEmployeeTimeScheduledForInterval(employee_distance.employee, jobSchedulingWindowStart, jobSchedulingWindowEnd);
            // Add approx worst case travel time (est 35 mph)
            long travelMinutesEstimate = (long)(employee_distance.distance/35.0 * 60);
            long totalEstimatedJobMinutes = travelMinutesEstimate + job.getJobDuration();
            if ((totalEstimatedJobMinutes + alreadyScheduledTime.toMinutes()) <= (workDayLength.toMinutes()+workDayBreaks.toMinutes()))
            {
                Employee candidate = employee_distance.employee;
                if (AssignJobToEmployee(job,candidate)) return true;
            }
        }
        //could not schedule in this timeframe
        return false;
    }

    public List<Job> ScheduleUnAssignedJobs(ZonedDateTime startTime, ZonedDateTime endTime)
    {
        return ScheduleUnAssignedJobs(startTime, endTime, maxTravelDistance);
    }

    public List<Job> ScheduleUnAssignedJobs(ZonedDateTime startTime, ZonedDateTime endTime, double max_distance)
    {
        // need to truncate all job starttimes to midnight since they may have been scheduled before
        List<Job> jobList = jobDao.GetJobsByIntervalAndState(startTime, endTime, "UNASSIGNED");
        List<Job> unScheduledJobList = new ArrayList<Job>();
        for (Job job : jobList)
        {
            // in and out of database is via Timestamp
            // truncate to midnight on same date
            job.setJobDate(TruncateToDate(job.getJobDate()));
            jobDao.update(job);
            if (job.getJobLocation() <= 0)
            {
                // location has not been created yet
                // tbd log an error
                continue;
            }
          if (!ScheduleJob(job, max_distance))
              unScheduledJobList.add(job);
        }
        // return list of jobs that could not be scheduled
        return unScheduledJobList;
    }

    public boolean SchedulePTO(ZonedDateTime startTime, ZonedDateTime endTime, int employee_id)
    {
        return true;
    }

    public boolean ScheduleEmployeeDay(ZonedDateTime startTime, int employee_id)
    {
        return true;
    }


}
