<%--
  Created by IntelliJ IDEA.
  User: danielkarkee
  Date: 3/20/16
  Time: 2:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <div class="modal-header">
            <div class="row">
                <div class="col-sm-6">
                    <h3 class="modal-title">Job Information</h3>
                </div>
                <div class="col-sm-6">
                    <h3 class="modal-title"><span class="pull-right">{{employee.employeeNum}}</span></h3>
                </div>
            </div>

        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-sm-4">
                    <div class="form-group">
                        <label for="jobName">Job Name</label>
                        <input type="text" ng-model="job.jobName" class="form-control" id="jobName" ng-value=job.jobName>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label for="siteContactName">Site Contact Name</label>
                        <input type="text" ng-model="job.siteContactName" class="form-control" id="siteContactName" ng-value=job.siteContactName>
                    </div>
                </div>
             </div>
            <div class="row">
                <div class="col-sm-4">
                    <div class="form-group">
                        <label for="jobDate">Job Date</label>
                        <input type="date" ng-model="job.jobDate" class="form-control" id="jobDate" ng-value=job.jobDate>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <div class="form-group">
                        <label for="jobPhone">Job Phone</label>
                        <input type="text" ng-model="job.jobPhone" class="form-control" id="jobPhone" ng-value=job.jobPhone>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label for="jobState">Job State</label>
                        <input type="text" ng-model="job.jobState" class="form-control" id="jobState" ng-value=job.jobState>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button class="btn btn-success" type="button" ng-click="update(job)">Update</button>
            <button class="btn btn-primary" type="button" ng-click="ok()">OK</button>
            <button class="btn btn-warning" type="button" ng-click="cancel()">Cancel</button>
        </div>
    </body>
</html>
