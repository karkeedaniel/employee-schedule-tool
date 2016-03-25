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
        <div class="panel panel-default">
            <div class="panel-heading">
                <button class="btn btn-success" type="button" ng-click="add()">Add Employee</button>
            </div>
            <div class="panel-body" >
                <table datatable="ng" class="row-border table-striped ">
                    <thead>
                    <tr>
                        <th>Employee Num</th>
                        <th>First Name</th>
                        <th>Middle Name</th>
                        <th>Last Name</th>
                        <th>Status</th>
                        <th>View/Update</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="employee in employeeList">
                        <td>{{employee.employeeNum}}</td>
                        <td>{{employee.firstName}}</td>
                        <td>{{employee.middleName}}</td>
                        <td>{{employee.lastName}}</td>
                        <td>{{employee.status}}</td>
                        <td>
                            <a class="btn btn-small btn-primary" href="" ng-click="editor(employee)"><i class="fa fa-pencil-square"></i></a>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
