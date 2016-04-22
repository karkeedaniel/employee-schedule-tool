<%--
  Created by IntelliJ IDEA.
  User: danielkarkee
  Date: 3/26/16
  Time: 8:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <div class="panel panel-primary" ng-controller="jobCtrl">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-xs-6">
                        <h4>Schedule Job</h4>
                    </div>
                    <div class="col-xs-6">
                        <button type="button" class="btn btn-warning pull-right" ng-click="return()">Return To Jobs</button>
                    </div>
                </div>
            </div>
            <div class="panel-body">
                <div class="alert alert-danger" ng-show="error">
                    Job already exists.
                </div>
                <form name="addJobForm" ng-submit="add(job, location)" novalidate>
                    <h3>Job Information</h3>
                    <div class="row">
                        <div class="col-xs-4">
                            <div class="form-group"
                                 ng-class="{'has-success has-feedback': addJobForm.jobName.$valid, 'has-error has-feedback': !addJobForm.jobName.$valid && addJobForm.jobName.$dirty}">
                                <label for="jobName">Job name</label>
                                <input type="text" class="form-control" id="jobName" name="jobName"
                                       placeholder="Job Name" ng-model="job.jobName"
                                       ng-pattern="/^[a-zA-Z0-9]+$/" required>
                                <span ng-show="addJobForm.jobName.$valid"
                                      class="glyphicon glyphicon-ok form-control-feedback"></span>
                                <span ng-show="!addJobForm.jobName.$valid && addJobForm.jobName.$dirty"
                                      class="glyphicon glyphicon-remove form-control-feedback"></span>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="form-group"
                                 ng-class="{'has-success has-feedback': addJobForm.siteContactName.$valid, 'has-error has-feedback': !addJobForm.siteContactName.$valid && addJobForm.siteContactName.$dirty}">
                                <label for="siteContactName">Site Contact</label>
                                <input type="text" class="form-control" id="siteContactName" name="siteContactName"
                                       placeholder="Site Contact" ng-model="job.siteContactName"
                                       ng-pattern="/^[a-zA-Z0-9]+$/" required>
                                <span ng-show="addJobForm.siteContactName.$valid"
                                      class="glyphicon glyphicon-ok form-control-feedback"></span>
                                <span ng-show="!addJobForm.siteContactName.$valid && addJobForm.siteContactName.$dirty"
                                      class="glyphicon glyphicon-remove form-control-feedback"></span>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="form-group"
                                 ng-class="{'has-success has-feedback': addJobForm.jobPhone.$valid, 'has-error has-feedback': !addJobForm.jobPhone.$valid && addJobForm.jobPhone.$dirty}">
                                <label for="jobPhone">Job phone</label>
                                <input type="text" ng-model="job.jobPhone" class="form-control" id="jobPhone"
                                       name="jobPhone" ng-minlength="10" ng-maxlength="10"
                                       ng-pattern="/^[0-9]+$/" required>
                                <span ng-show="addJobForm.jobPhone.$valid"
                                      class="glyphicon glyphicon-ok form-control-feedback"></span>
                                <span ng-show="!addJobForm.jobPhone.$valid && addJobForm.jobPhone.$dirty"
                                      class="glyphicon glyphicon-remove form-control-feedback"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label for="jobDate">Schedule Date</label>
                                <input type="date"  class="form-control" id="jobDate" ng-model="job.jobDate" required>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="form-group"
                                 ng-class="{'has-success has-feedback': addJobForm.jobDuration.$valid, 'has-error has-feedback': !addJobForm.jobDuration.$valid && addJobForm.jobDuration.$dirty}">
                                <label for="jobDuration">EstWork(min)</label>
                                <input type="text" class="form-control" id="jobDuration" name="jobDuration" placeholder="jobDuration"
                                       ng-minlength="2" ng-maxlength="3"
                                       ng-pattern="/^[0-9]+$/"ng-model="job.jobDuration" required>
                                <span ng-show="addJobForm.jobDuration.$valid"
                                      class="glyphicon glyphicon-ok form-control-feedback"></span>
                                <span ng-show="!addJobForm.jobDuration.$valid && addJobForm.jobDuration.$dirty"
                                      class="glyphicon glyphicon-remove form-control-feedback"></span>
                            </div>
                        </div>
                    </div>

                    <h3>Address</h3>
                    <div class="row">
                        <div class="col-xs-2">
                            <div class="form-group"
                                 ng-class="{'has-success has-feedback': addJobForm.streetNumber.$valid, 'has-error has-feedback': !addJobForm.streetNumber.$valid && addJobForm.streetNumber.$dirty}">
                                <label for="streetNumber">Street No</label>
                                <input type="text" ng-model="location.streetNumber" class="form-control" id="streetNumber"
                                       name="streetNumber" ng-minlength="1" ng-maxlength="8"
                                       ng-pattern="/^[0-9]+$/" required>
                                <span ng-show="addJobForm.streetNumber.$valid"
                                      class="glyphicon glyphicon-ok form-control-feedback"></span>
                                <span ng-show="!addJobForm.streetNumber.$valid && addJobForm.streetNumber.$dirty"
                                      class="glyphicon glyphicon-remove form-control-feedback"></span>
                            </div>
                        </div>
                        <div class="col-xs-10">
                            <div class="form-group"
                                 ng-class="{'has-success has-feedback': addJobForm.street.$valid, 'has-error has-feedback': !addJobForm.street.$valid && addJobForm.street.$dirty}">
                                <label for="street">Street Name</label>
                                <input type="text" ng-model="location.street" class="form-control" id="street"
                                       name="street" ng-minlength="1" ng-maxlength="50"
                                       ng-pattern="/^[a-zA-Z0-9 ]+$/" required>
                                <span ng-show="addJobForm.street.$valid"
                                      class="glyphicon glyphicon-ok form-control-feedback"></span>
                                <span ng-show="!addJobForm.street.$valid && addJobForm.street.$dirty"
                                      class="glyphicon glyphicon-remove form-control-feedback"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-4">
                            <div class="form-group"
                                 ng-class="{'has-success has-feedback': addJobForm.city.$valid, 'has-error has-feedback': !addJobForm.city.$valid && addJobForm.city.$dirty}">
                                <label for="street">City</label>
                                <input type="text" ng-model="location.city" class="form-control" id="city"
                                       name="city" ng-minlength="1" ng-maxlength="50"
                                       ng-pattern="/^[a-zA-Z ]+$/" required>
                                <span ng-show="addJobForm.city.$valid"
                                      class="glyphicon glyphicon-ok form-control-feedback"></span>
                                <span ng-show="!addJobForm.city.$valid && addJobForm.city.$dirty"
                                      class="glyphicon glyphicon-remove form-control-feedback"></span>
                            </div>
                        </div>
                        <div class="col-xs-2">
                            <div class="form-group"
                                 ng-class="{'has-success has-feedback': addJobForm.state.$valid, 'has-error has-feedback': !addJobForm.state.$valid && addJobForm.state.$dirty}">
                                <label for="state">State</label>
                                <input type="text" ng-model="location.state" class="form-control" id="state"
                                       name="state" ng-minlength="2" ng-maxlength="2"
                                       ng-pattern="/^[A-Z]+$/" required>
                                <span ng-show="addJobForm.state.$valid"
                                      class="glyphicon glyphicon-ok form-control-feedback"></span>
                                <span ng-show="!addJobForm.state.$valid && addJobForm.state.$dirty"
                                      class="glyphicon glyphicon-remove form-control-feedback"></span>
                            </div>
                        </div>
                        <div class="col-xs-2">
                            <div class="form-group"
                                 ng-class="{'has-success has-feedback': addJobForm.zip.$valid, 'has-error has-feedback': !addJobForm.zip.$valid && addJobForm.zip.$dirty}">
                                <label for="zip">Zip Code</label>
                                <input type="text" ng-model="location.zip" class="form-control" id="zip"
                                       name="zip" ng-minlength="5" ng-maxlength="5"
                                       ng-pattern="/^[0-9]+$/">
                                <span ng-show="addJobForm.zip.$valid"
                                      class="glyphicon glyphicon-ok form-control-feedback"></span>
                                <span ng-show="!addJobForm.zip.$valid && addJobForm.zip.$dirty"
                                      class="glyphicon glyphicon-remove form-control-feedback"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-offset-4 col-xs-4">
                            <button type="submit" class="btn btn-primary btn-block" ng-disabled="addJobForm.$invalid">
                                Submit
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
