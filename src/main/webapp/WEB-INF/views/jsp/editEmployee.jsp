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
        <div class="panel panel-primary" ng-controller="employeeCtrl">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-xs-4"></div>
                    <div class="col-xs-4 text-center">
                        <h4>Edit Employee</h4>
                    </div>
                    <div class="col-xs-4">
                        <button type="button" class="btn btn-warning pull-right" ng-click="return()">Return to Employee</button>
                    </div>
                </div>

            </div>
            <div class="panel-body">
                <%--<form name="addEmployee" ng-submit="add(employee)" novalidate>--%>
                    <%--<h3>Personal Information</h3>--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-xs-4">--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="firstName">First name</label>--%>
                                <%--<input type="text" class="form-control" id="firstName" placeholder="First Name" ng-model="employee.firstName" required>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="col-xs-4">--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="middleName">Middle name</label>--%>
                                <%--<input type="text" class="form-control" id="middleName" placeholder="Middle Name" ng-model="employee.middleName">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="col-xs-4">--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="lastName">Last name</label>--%>
                                <%--<input type="text" class="form-control" id="lastName" placeholder="Last Name" ng-model="employee.lastName" required>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-xs-4">--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="gender">Gender</label>--%>
                                <%--<select class="form-control" id="gender" ng-model="employee.gender" required>--%>
                                    <%--<option value="Male">Male</option>--%>
                                    <%--<option value="Female">Female</option>--%>
                                <%--</select>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="col-xs-4">--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="dob">Date of Birth</label>--%>
                                <%--<input type="date"  class="form-control" id="dob" ng-model="employee.dob" required>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="col-xs-4">--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="ssn">SSN</label>--%>
                                <%--<input type="number" class="form-control" id="ssn" placeholder="SSN" ng-model="employee.ssn" maxlength="9" required>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-xs-4">--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="contact">Contact</label>--%>
                                <%--<input type="number" ng-model="employee.contact" class="form-control" id="contact" maxlength="10" required>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="col-xs-8">--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="email">Email address</label>--%>
                                <%--<input type="email" class="form-control" id="email" placeholder="Email" ng-model="employee.email" required>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-xs-4">--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="role">Role</label>--%>
                                <%--<select class="form-control" id="role" ng-model="employee.role" required>--%>
                                    <%--<option value="MANAGER">Manager</option>--%>
                                    <%--<option value="PROJECT MANAGER">Project Manager</option>--%>
                                    <%--<option value="TECHNICIAN">Technician</option>--%>
                                <%--</select>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="col-xs-4">--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="status">Status</label>--%>
                                <%--<select class="form-control" id="status" ng-model="employee.status" required>--%>
                                    <%--<option value="ACTIVE">Active</option>--%>
                                    <%--<option value="INACTIVE">Inactive</option>--%>
                                <%--</select>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--&lt;%&ndash;<h3>Address</h3>&ndash;%&gt;--%>
                    <%--<button type="submit" class="btn btn-primary btn-block" ng-disabled="addEmployee.$invalid">--%>
                        <%--Submit--%>
                    <%--</button>--%>
                <%--</form>--%>
            </div>
        </div>
    </body>
</html>
