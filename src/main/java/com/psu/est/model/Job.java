package com.psu.est.model;

import com.psu.est.model.interfaces.DomainObject;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * Created by gorzelic on 2/28/2016.
 */
@Entity
public class Job implements DomainObject {


    private Integer jobId;
    private String jobName;
    private Integer jobLocation;
    private String jobPhone;
    private Timestamp jobBookDate;
    private Timestamp jobDate;
    private Integer jobDuration;
    private Integer jobLevel;
    private String jobState;
    private String siteContactName;
    private String siteContactPhone;

    @Id
    @GeneratedValue
    @Column(name = "job_id", nullable = false)
    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    @Basic
    @Column(name = "job_name", nullable = true, length = 45)
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Basic
    @Column(name = "job_location", nullable = true)
    public Integer getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(Integer jobLocation) {
        this.jobLocation = jobLocation;
    }

    @Basic
    @Column(name = "job_phone", nullable = true, length = 45)
    public String getJobPhone() {
        return jobPhone;
    }

    public void setJobPhone(String jobPhone) {
        this.jobPhone = jobPhone;
    }

    @Basic
    @Column(name = "job_book_date", nullable = true)
    public Timestamp getJobBookDate() {
        return jobBookDate;
    }

    public void setJobBookDate(Timestamp jobBookDate) {
        this.jobBookDate = jobBookDate;
    }

    @Basic
    @Column(name = "job_date", nullable = true)
    public Timestamp getJobDate() {
        return jobDate;
    }

    public void setJobDate(Timestamp jobDate) {
        this.jobDate = jobDate;
    }

    @Basic
    @Column(name = "job_duration", nullable = true)
    public Integer getJobDuration() {
        return jobDuration;
    }

    public void setJobDuration(Integer jobDuration) {
        this.jobDuration = jobDuration;
    }

    @Basic
    @Column(name = "job_level", nullable = true)
    public Integer getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(Integer jobLevel) {
        this.jobLevel = jobLevel;
    }

    @Basic
    @Column(name = "job_state", nullable = true, length = 45)
    public String getJobState() {
        return jobState;
    }

    public void setJobState(String jobState) {
        this.jobState = jobState;
    }

    @Basic
    @Column(name = "site_contact_name", nullable = true, length = 45)
    public String getSiteContactName() {
        return siteContactName;
    }

    public void setSiteContactName(String siteContactName) {
        this.siteContactName = siteContactName;
    }

    @Basic
    @Column(name = "site_contact_phone", nullable = true, length = 45)
    public String getSiteContactPhone() {
        return siteContactPhone;
    }

    public void setSiteContactPhone(String siteContactPhone) {
        this.siteContactPhone = siteContactPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        if (jobId != null ? !jobId.equals(job.jobId) : job.jobId != null) return false;
        if (jobName != null ? !jobName.equals(job.jobName) : job.jobName != null) return false;
        if (jobLocation != null ? !jobLocation.equals(job.jobLocation) : job.jobLocation != null) return false;
        if (jobPhone != null ? !jobPhone.equals(job.jobPhone) : job.jobPhone != null) return false;
        if (jobBookDate != null ? !jobBookDate.equals(job.jobBookDate) : job.jobBookDate != null) return false;
        if (jobDate != null ? !jobDate.equals(job.jobDate) : job.jobDate != null) return false;
        if (jobDuration != null ? !jobDuration.equals(job.jobDuration) : job.jobDuration != null) return false;
        if (jobLevel != null ? !jobLevel.equals(job.jobLevel) : job.jobLevel != null) return false;
        if (jobState != null ? !jobState.equals(job.jobState) : job.jobState != null) return false;
        if (siteContactName != null ? !siteContactName.equals(job.siteContactName) : job.siteContactName != null)
            return false;
        if (siteContactPhone != null ? !siteContactPhone.equals(job.siteContactPhone) : job.siteContactPhone != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = jobId != null ? jobId.hashCode() : 0;
        result = 31 * result + (jobName != null ? jobName.hashCode() : 0);
        result = 31 * result + (jobLocation != null ? jobLocation.hashCode() : 0);
        result = 31 * result + (jobPhone != null ? jobPhone.hashCode() : 0);
        result = 31 * result + (jobBookDate != null ? jobBookDate.hashCode() : 0);
        result = 31 * result + (jobDate != null ? jobDate.hashCode() : 0);
        result = 31 * result + (jobDuration != null ? jobDuration.hashCode() : 0);
        result = 31 * result + (jobLevel != null ? jobLevel.hashCode() : 0);
        result = 31 * result + (jobState != null ? jobState.hashCode() : 0);
        result = 31 * result + (siteContactName != null ? siteContactName.hashCode() : 0);
        result = 31 * result + (siteContactPhone != null ? siteContactPhone.hashCode() : 0);
        return result;
    }
}
