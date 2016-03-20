<%--
  Created by IntelliJ IDEA.
  User: danielkarkee
  Date: 3/4/16
  Time: 8:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <script type="text/ng-template" id="empModalContent.html">
            <div class="modal-header">
                <h3 class="modal-title">I'm a modal!</h3>
            </div>
            <div class="modal-body">
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" type="button" ng-click="ok()">OK</button>
                <button class="btn btn-warning" type="button" ng-click="cancel()">Cancel</button>
            </div>
        </script>
    </head>
    <body>
        <div class="panel panel-default">
            <div class="panel-body" ng-controller="mgrCtrl">
                <table datatable="ng" class="row-border table-striped ">
                    <thead>
                    <tr>
                        <th>Employee Num</th>
                        <th>First Name</th>
                        <th>Middle Initial</th>
                        <th>Last Name</th>
                        <th>Detail</th>
                        <th>Edit</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="employee in employeeList">
                        <td>{{employee.employeeNum}}</td>
                        <td>{{employee.firstName}}</td>
                        <td>{{employee.middleInitial}}</td>
                        <td>{{employee.lastName}}</td>
                        <td>
                            <a class="btn btn-small btn-info" href="" ng-click="editable(employee, false)"><i class="fa fa-info-circle"></i></a>
                        </td>
                        <td>
                            <a class="btn btn-small btn-warning" href="" ng-click="editable(employee, true)"><i class="fa fa-pencil-square"></i></a>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
