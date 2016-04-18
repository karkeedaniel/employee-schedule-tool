<%--
  Created by IntelliJ IDEA.
  User: danielkarkee
  Date: 3/23/16
  Time: 11:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <div ng-controller="jobCtrl">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <a class="btn btn-success" ui-sref=".add">Add Job</a>
                </div>
                <div class="panel-body">
                    <table datatable="ng" class="row-border hover">
                        <thead>
                            <tr>
                                <th>Job Name</th>
                                <th>Job Contact</th>
                                <th>Job Phone</th>
                                <th>Job Date</th>
                                <th>Job State</th>
                                <th>Edit</th>
                                <th>Schedule</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="job in jobList">
                                <td>{{job.jobName}}</td>
                                <td>{{job.siteContactName}}</td>
                                <td>{{job.jobPhone}}</td>
                                <td>{{job.jobDate}}</td>
                                <td>{{job.jobState}}</td>
                                 <td>
                                    <a class="btn btn-small btn-primary" ui-sref=".edit"><i class="fa fa-pencil-square"></i></a>
                                </td>
                                <td>
                                    <a class="btn btn-small btn-primary" ui-sref=".schedule"><i class="fa fa-calendar"></i></a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
