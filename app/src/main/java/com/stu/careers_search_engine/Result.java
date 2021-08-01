package com.stu.careers_search_engine;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Result {

    @SerializedName("jobId")
    @Expose
    private Integer jobId;
    @SerializedName("employerId")
    @Expose
    private Integer employerId;
    @SerializedName("employerName")
    @Expose
    private String employerName;
    @SerializedName("employerProfileId")
    @Expose
    private Object employerProfileId;
    @SerializedName("employerProfileName")
    @Expose
    private Object employerProfileName;
    @SerializedName("jobTitle")
    @Expose
    private String jobTitle;
    @SerializedName("locationName")
    @Expose
    private String locationName;
    @SerializedName("minimumSalary")
    @Expose
    private Object minimumSalary;
    @SerializedName("maximumSalary")
    @Expose
    private Object maximumSalary;
    @SerializedName("currency")
    @Expose
    private Object currency;
    @SerializedName("expirationDate")
    @Expose
    private String expirationDate;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("jobDescription")
    @Expose
    private String jobDescription;
    @SerializedName("applications")
    @Expose
    private Integer applications;
    @SerializedName("jobUrl")
    @Expose
    private String jobUrl;

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public Object getEmployerProfileId() {
        return employerProfileId;
    }

    public void setEmployerProfileId(Object employerProfileId) {
        this.employerProfileId = employerProfileId;
    }

    public Object getEmployerProfileName() {
        return employerProfileName;
    }

    public void setEmployerProfileName(Object employerProfileName) {
        this.employerProfileName = employerProfileName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Object getMinimumSalary() {
        return minimumSalary;
    }

    public void setMinimumSalary(Object minimumSalary) {
        this.minimumSalary = minimumSalary;
    }

    public Object getMaximumSalary() {
        return maximumSalary;
    }

    public void setMaximumSalary(Object maximumSalary) {
        this.maximumSalary = maximumSalary;
    }

    public Object getCurrency() {
        return currency;
    }

    public void setCurrency(Object currency) {
        this.currency = currency;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Integer getApplications() {
        return applications;
    }

    public void setApplications(Integer applications) {
        this.applications = applications;
    }

    public String getJobUrl() {
        return jobUrl;
    }

    public void setJobUrl(String jobUrl) {
        this.jobUrl = jobUrl;
    }

}