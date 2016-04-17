package com.psu.est.controller;

import com.psu.est.service.JobScheduleService;
import com.psu.est.view.JobSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by danielkarkee on 4/13/16.
 */
@RestController
public class JobScheduleController {

    @Autowired
    private JobScheduleService jobScheduleService;

    @RequestMapping(value = "/job-schedule/getByEmployeeIdAndStartTime", method = RequestMethod.GET)
    public List<JobSchedule> getByEmployeeIdAndStartTime(@RequestParam("employeeId")int employeeId, @RequestParam("startTime")Timestamp startTime) {
        return jobScheduleService.getListByEmployeeIdAndStartTime(employeeId, startTime);
    }
}
