<%--
  Created by IntelliJ IDEA.
  User: danielkarkee
  Date: 3/26/16
  Time: 8:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <script type="text/ng-template" id="locationModal">
            <div class="modal-header">
                <h3 class="modal-title">Job Location</h3>
            </div>
            <div class="modal-body">
                <address>
                    <strong>{{job.name}}</strong><br>
                    {{job.location.streetNumber}} {{job.location.street}}<br>
                    {{job.location.city}}, {{job.location.state}} {{job.location.zip}}
                </address>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" type="button" ng-click="ok()">OK</button>
            </div>
        </script>
    </head>
    <body>
        <div class="panel panel-primary" ng-controller="scheduleCtrl">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-xs-2 text-center">
                        <h4>{{date | date : 'mediumDate'}}</h4>
                    </div>
                    <div class="col-xs-4">
                        <button type="button" class="btn btn-default" ng-click="currDate()">Today</button>
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" ng-click="prevDate(date)">Prev</button>
                            <button type="button" class="btn btn-default" ng-click="nextDate(date)">Next</button>
                        </div>
                    </div>
                    <div ng-show="showButton" class="col-xs-6">
                        <button type="button" class="btn btn-warning pull-right" ng-click="return()">Return to Employee</button>
                    </div>
                </div>
            </div>
            <div class="panel-body">
                <table datatable="ng" class="row-border hover" dt-options="dtOptions" dt-column-defs="dtColumnDefs">
                    <thead>
                        <tr>
                            <th>Job Name</th>
                            <th>Type</th>
                            <th>Start time</th>
                            <th>End time</th>
                            <th>Travel time</th>
                            <th>Status</th>
                            <th>Location</th>
                            <th>Event</th>
                        </tr>
                    </thead>
                        <tr ng-repeat="jobSchedule in jobScheduleList">
                            <td>{{jobSchedule.job.jobName}}</td>
                            <td>{{jobSchedule.schedule.type}}</td>
                            <td>{{jobSchedule.schedule.startTime | date : 'shortTime'}}</td>
                            <td>{{jobSchedule.schedule.endTime | date : 'shortTime'}}</td>
                            <td>{{jobSchedule.schedule.travelTime}}</td>
                            <td>{{jobSchedule.job.jobState}}</td>
                            <td>
                                <button ng-show="jobSchedule.location" type="button" class="btn btn-small btn-primary" ng-click="open(jobSchedule.job.jobName, jobSchedule.location)"><i class="fa fa-globe"></i></button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-small btn-primary"
                                        ng-show="jobSchedule.job.jobState == 'SCHEDULED'"
                                        ng-click="event(jobSchedule.job)">
                                    Check In
                                </button>
                                <button ng-model="job.status" type="button" class="btn btn-small btn-success"
                                        ng-disabled="jobSchedule.job.jobState == 'COMPLETED'"
                                        ng-show="jobSchedule.job.jobState == 'INPROGRESS' || jobSchedule.job.jobState == 'COMPLETED'"
                                        ng-Value="COMPLETE" ng-click="event(jobSchedule.job)">
                                    Complete
                                </button>
                            </td>
                        </tr>
                </table>
            </div>
        </div>
    </body>
</html>
