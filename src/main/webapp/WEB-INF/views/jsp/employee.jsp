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
        <div ng-controller="employeeCtrl">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <a class="btn btn-success" ui-sref=".add">Add Employee</a>
                </div>
                <div class="panel-body">
                    <table datatable="ng" class="row-border hover">
                        <thead>
                            <tr>
                                <th>Employee Num</th>
                                <th>First Name</th>
                                <th>Middle Name</th>
                                <th>Last Name</th>
                                <th>Role</th>
                                <th>Status</th>
                                <th>Update</th>
                                <th>Schedule</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="employee in employeeList">
                                <td>{{employee.employeeNum}}</td>
                                <td>{{employee.firstName}}</td>
                                <td>{{employee.middleName}}</td>
                                <td>{{employee.lastName}}</td>
                                <td>{{employee.role}}</td>
                                <td>{{employee.status}}</td>
                                <td>
                                    <a class="btn btn-small btn-primary" ui-sref=".edit({employeeId : employee.employeeId})"><i class="fa fa-pencil-square"></i></a>
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
