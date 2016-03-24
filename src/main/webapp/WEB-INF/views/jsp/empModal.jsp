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
                    <h3 class="modal-title">Employee Information</h3>
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
                        <label for="firstName">First Name</label>
                        <input type="text" ng-model="employee.firstName" class="form-control" id="firstName" ng-value=employee.firstName>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label for="middleName">Middle Name</label>
                        <input type="text" ng-model="employee.middleName" class="form-control" id="middleName" ng-value=employee.middleName>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label for="lastName">Last Name</label>
                        <input type="text" ng-model="employee.lastName" class="form-control" id="lastName" ng-value=employee.lastName>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <div class="form-group">
                        <label for="dob">DOB</label>
                        <input type="date" ng-model="employee.dob" class="form-control" id="dob" ng-value=employee.dob>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" ng-model="employee.email" class="form-control" id="email" ng-value=employee.email>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label for="gender">Gender</label>
                        <input type="text" ng-model="employee.gender" class="form-control" id="gender" ng-value=employee.gender>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <div class="form-group">
                        <label for="contact">contact</label>
                        <input type="text" ng-model="employee.contact" class="form-control" id="contact" ng-value=employee.contact>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label for="role">Role</label>
                        <input type="text" ng-model="employee.role" class="form-control" id="role" ng-value=employee.role>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label for="status">Status</label>
                        <input type="text" ng-model="employee.status" class="form-control" id="status" ng-value=employee.status>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button class="btn btn-success" type="button" ng-click="update(employee)">Update</button>
            <button class="btn btn-primary" type="button" ng-click="ok()">OK</button>
            <button class="btn btn-warning" type="button" ng-click="cancel()">Cancel</button>
        </div>
    </body>
</html>
