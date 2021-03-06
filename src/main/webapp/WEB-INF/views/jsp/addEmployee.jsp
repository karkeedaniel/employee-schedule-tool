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
                    <div class="col-xs-6">
                        <h4>Add Employee</h4>
                    </div>
                    <div class="col-xs-6">
                        <button type="button" class="btn btn-warning pull-right" ng-click="return()">Return to Employee</button>
                    </div>
                </div>
            </div>
            <div class="panel-body">
                <div class="alert alert-danger" ng-show="error">
                    Employee account already exists.
                </div>
                <div class="alert alert-success" ng-show="success">
                    Employee account has been successfully created.
                </div>
                <form name="addEmployeeForm" ng-submit="add(employee, location)" novalidate>
                    <h3>Personal Information</h3>
                    <div class="row">
                        <div class="col-xs-4">
                            <div class="form-group"
                                 ng-class="{'has-success has-feedback': addEmployeeForm.firstName.$valid, 'has-error has-feedback': !addEmployeeForm.firstName.$valid && addEmployeeForm.firstName.$dirty}">
                                <label for="firstName">First name</label>
                                <input type="text" class="form-control" id="firstName" name="firstName"
                                       placeholder="First Name" ng-model="employee.firstName"
                                       ng-pattern="/^[a-zA-Z0-9]+$/" required>
                                <span ng-show="addEmployeeForm.firstName.$valid"
                                      class="glyphicon glyphicon-ok form-control-feedback"></span>
                                <span ng-show="!addEmployeeForm.firstName.$valid && addEmployeeForm.firstName.$dirty"
                                      class="glyphicon glyphicon-remove form-control-feedback"></span>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="form-group"
                                 ng-class="{'has-success has-feedback': addEmployeeForm.middleName.$valid, 'has-error has-feedback': !addEmployeeForm.middleName.$valid && addEmployeeForm.middleName.$dirty}">
                                <label for="middleName">Middle name</label>
                                <input type="text" class="form-control" id="middleName" name="middleName"
                                       placeholder="Middle Name" ng-model="employee.middleName"
                                       ng-pattern="/^[a-zA-Z0-9]+$/">
                                <span ng-show="addEmployeeForm.middleName.$valid"
                                      class="glyphicon glyphicon-ok form-control-feedback"></span>
                                <span ng-show="!addEmployeeForm.middleName.$valid && addEmployeeForm.middleName.$dirty"
                                      class="glyphicon glyphicon-remove form-control-feedback"></span>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="form-group"
                                 ng-class="{'has-success has-feedback': addEmployeeForm.lastName.$valid, 'has-error has-feedback': !addEmployeeForm.lastName.$valid && addEmployeeForm.lastName.$dirty}">
                                <label for="lastName">Last name</label>
                                <input type="text" class="form-control" id="lastName" name="lastName"
                                       placeholder="Last Name" ng-model="employee.lastName"
                                       ng-pattern="/^[a-zA-Z0-9]+$/" required>
                                <span ng-show="addEmployeeForm.lastName.$valid"
                                      class="glyphicon glyphicon-ok form-control-feedback"></span>
                                <span ng-show="!addEmployeeForm.lastName.$valid && addEmployeeForm.lastName.$dirty"
                                      class="glyphicon glyphicon-remove form-control-feedback"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label for="gender">Gender</label>
                                <select class="form-control" id="gender" ng-model="employee.gender" required>
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label for="dob">Date of Birth</label>
                                <input type="date"  class="form-control" id="dob" ng-model="employee.dob" required>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="form-group"
                                 ng-class="{'has-success has-feedback': addEmployeeForm.ssn.$valid, 'has-error has-feedback': !addEmployeeForm.ssn.$valid && addEmployeeForm.ssn.$dirty}">
                                <label for="ssn">SSN</label>
                                <input type="text" class="form-control" id="ssn" name="ssn" placeholder="SSN"
                                       ng-minlength="9" ng-maxlength="9"
                                       ng-pattern="/^[0-9]+$/"ng-model="employee.ssn" required>
                                <span ng-show="addEmployeeForm.ssn.$valid"
                                      class="glyphicon glyphicon-ok form-control-feedback"></span>
                                <span ng-show="!addEmployeeForm.ssn.$valid && addEmployeeForm.ssn.$dirty"
                                      class="glyphicon glyphicon-remove form-control-feedback"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-4">
                            <div class="form-group"
                                 ng-class="{'has-success has-feedback': addEmployeeForm.contact.$valid, 'has-error has-feedback': !addEmployeeForm.contact.$valid && addEmployeeForm.contact.$dirty}">
                                <label for="contact">Contact</label>
                                <input type="text" ng-model="employee.contact" class="form-control" id="contact"
                                       name="contact" ng-minlength="10" ng-maxlength="10"
                                       ng-pattern="/^[0-9]+$/" required>
                                <span ng-show="addEmployeeForm.contact.$valid"
                                      class="glyphicon glyphicon-ok form-control-feedback"></span>
                                <span ng-show="!addEmployeeForm.contact.$valid && addEmployeeForm.contact.$dirty"
                                      class="glyphicon glyphicon-remove form-control-feedback"></span>
                            </div>
                        </div>
                        <div class="col-xs-8">
                            <div class="form-group"
                                 ng-class="{'has-success has-feedback': addEmployeeForm.email.$valid, 'has-error has-feedback': !addEmployeeForm.email.$valid && addEmployeeForm.email.$dirty}">
                                <label for="email">Email address</label>
                                <input type="email" class="form-control" id="email" name="email" placeholder="Email"
                                       ng-model="employee.email" required>
                                <span ng-show="addEmployeeForm.email.$valid"
                                      class="glyphicon glyphicon-ok form-control-feedback"></span>
                                <span ng-show="!addEmployeeForm.email.$valid && addEmployeeForm.email.$dirty"
                                      class="glyphicon glyphicon-remove form-control-feedback"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2">
                            <div class="form-group">
                                <label for="role">Role</label>
                                <select class="form-control" id="role" ng-model="employee.role" required>
                                    <option value="DIRECTOR">DIRECTOR</option>
                                    <option value="MANAGER">MANAGER</option>
                                    <option value="PM">PROJECT MANAGER</option>
                                    <option value="TECHNICIAN">TECHNICIAN</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-xs-2">
                            <div class="form-group">
                                <label for="status">Status</label>
                                <select class="form-control" id="status" ng-model="employee.status" required>
                                    <option value="ACTIVE">ACTIVE</option>
                                    <option value="INACTIVE">INACTIVE</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <h3>Address</h3>
                    <div class="row">
                        <div class="col-xs-2">
                            <div class="form-group"
                                 ng-class="{'has-success has-feedback': addEmployeeForm.streetNumber.$valid, 'has-error has-feedback': !addEmployeeForm.streetNumber.$valid && addEmployeeForm.streetNumber.$dirty}">
                                <label for="streetNumber">Street No</label>
                                <input type="text" ng-model="location.streetNumber" class="form-control" id="streetNumber"
                                       name="streetNumber" ng-minlength="1" ng-maxlength="8"
                                       ng-pattern="/^[0-9]+$/" required>
                                <span ng-show="addEmployeeForm.streetNumber.$valid"
                                      class="glyphicon glyphicon-ok form-control-feedback"></span>
                                <span ng-show="!addEmployeeForm.streetNumber.$valid && addEmployeeForm.streetNumber.$dirty"
                                      class="glyphicon glyphicon-remove form-control-feedback"></span>
                            </div>
                        </div>
                        <div class="col-xs-10">
                            <div class="form-group"
                                 ng-class="{'has-success has-feedback': addEmployeeForm.street.$valid, 'has-error has-feedback': !addEmployeeForm.street.$valid && addEmployeeForm.street.$dirty}">
                                <label for="street">Street Name</label>
                                <input type="text" ng-model="location.street" class="form-control" id="street"
                                       name="street" ng-minlength="1" ng-maxlength="50"
                                       ng-pattern="/^[a-zA-Z0-9 ]+$/" required>
                                <span ng-show="addEmployeeForm.street.$valid"
                                      class="glyphicon glyphicon-ok form-control-feedback"></span>
                                <span ng-show="!addEmployeeForm.street.$valid && addEmployeeForm.street.$dirty"
                                      class="glyphicon glyphicon-remove form-control-feedback"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-4">
                            <div class="form-group"
                                 ng-class="{'has-success has-feedback': addEmployeeForm.city.$valid, 'has-error has-feedback': !addEmployeeForm.city.$valid && addEmployeeForm.city.$dirty}">
                                <label for="street">City</label>
                                <input type="text" ng-model="location.city" class="form-control" id="city"
                                       name="city" ng-minlength="1" ng-maxlength="50"
                                       ng-pattern="/^[a-zA-Z ]+$/" required>
                                <span ng-show="addEmployeeForm.city.$valid"
                                      class="glyphicon glyphicon-ok form-control-feedback"></span>
                                <span ng-show="!addEmployeeForm.city.$valid && addEmployeeForm.city.$dirty"
                                      class="glyphicon glyphicon-remove form-control-feedback"></span>
                            </div>
                        </div>
                        <div class="col-xs-2">
                            <div class="form-group"
                                 ng-class="{'has-success has-feedback': addEmployeeForm.state.$valid, 'has-error has-feedback': !addEmployeeForm.state.$valid && addEmployeeForm.state.$dirty}">
                                <label for="state">State</label>
                                <input type="text" ng-model="location.state" class="form-control" id="state"
                                       name="state" ng-minlength="2" ng-maxlength="2"
                                       ng-pattern="/^[A-Z]+$/" required>
                                <span ng-show="addEmployeeForm.state.$valid"
                                      class="glyphicon glyphicon-ok form-control-feedback"></span>
                                <span ng-show="!addEmployeeForm.state.$valid && addEmployeeForm.state.$dirty"
                                      class="glyphicon glyphicon-remove form-control-feedback"></span>
                            </div>
                        </div>
                        <div class="col-xs-2">
                            <div class="form-group"
                                 ng-class="{'has-success has-feedback': addEmployeeForm.zip.$valid, 'has-error has-feedback': !addEmployeeForm.zip.$valid && addEmployeeForm.zip.$dirty}">
                                <label for="zip">Zip Code</label>
                                <input type="text" ng-model="location.zip" class="form-control" id="zip"
                                       name="zip" ng-minlength="5" ng-maxlength="5"
                                       ng-pattern="/^[0-9]+$/" required>
                                <span ng-show="addEmployeeForm.zip.$valid"
                                      class="glyphicon glyphicon-ok form-control-feedback"></span>
                                <span ng-show="!addEmployeeForm.zip.$valid && addEmployeeForm.zip.$dirty"
                                      class="glyphicon glyphicon-remove form-control-feedback"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-offset-4 col-xs-4">
                            <button type="submit" class="btn btn-primary btn-block" ng-disabled="addEmployeeForm.$invalid">
                                Submit
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
