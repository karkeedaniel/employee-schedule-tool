<%--
  Created by IntelliJ IDEA.
  User: danielkarkee
  Date: 3/9/16
  Time: 8:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <div class="row" ng-controller="registerCtrl">
            <div class="col-xs-offset-3 col-md-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-6">
                                <h4>Registration</h4>
                            </div>
                            <div class="col-xs-6">
                                <a ui-sref="login" class="btn btn-warning pull-right">Return to Login</a>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="alert alert-danger" ng-show="error">
                            There was a problem creating account. Please check your entry and try again.
                        </div>
                        <div class="alert alert-success" ng-show="success">
                            Account has been successfully created.
                        </div>
                        <div ng-hide="success">
                            <form class="form-horizontal" name="registerForm" ng-submit="register(registration)" autocomplete="off" novalidate>
                                <div class="form-group"
                                     ng-class="{'has-success has-feedback': registerForm.email.$valid, 'has-error has-feedback': !registerForm.email.$valid && registerForm.email.$dirty}">
                                    <label for="email" class="col-xs-2 control-label">Email</label>
                                    <div class="col-xs-10">
                                        <input type="email" id="email" name="email" class="form-control" placeholder="Email"
                                               ng-model="registration.email" required>
                                        <span ng-show="registerForm.email.$valid"
                                              class="glyphicon glyphicon-ok form-control-feedback"></span>
                                        <span ng-show="!registerForm.email.$valid && registerForm.email.$dirty"
                                              class="glyphicon glyphicon-remove form-control-feedback"></span>
                                    </div>
                                </div>
                                <div class="form-group"
                                     ng-class="{'has-success has-feedback': registerForm.year.$valid, 'has-error has-feedback': !registerForm.year.$valid && registerForm.year.$dirty}">
                                    <label for="year" class="col-xs-2 control-label">DOB</label>
                                    <div class="col-xs-10">
                                        <input type="text" id="year" name="year" class="form-control" placeholder="Birth year"
                                               ng-model="registration.year" ng-minlength="4" ng-maxlength="4"
                                               ng-pattern="/^[0-9]+$/" required>
                                        <span ng-show="registerForm.year.$valid"
                                              class="glyphicon glyphicon-ok form-control-feedback"></span>
                                        <span ng-show="!registerForm.year.$valid && registerForm.year.$dirty"
                                              class="glyphicon glyphicon-remove form-control-feedback"></span>
                                    </div>
                                </div>
                                <div class="form-group"
                                     ng-class="{'has-success has-feedback': registerForm.ssn.$valid, 'has-error has-feedback': !registerForm.ssn.$valid && registerForm.ssn.$dirty}">
                                    <label for="ssn" class="col-xs-2 control-label">SSN</label>
                                    <div class="col-xs-10">
                                        <input type="text" class="form-control" id="ssn" name="ssn" placeholder="Last 4 digit"
                                               ng-minlength="4" ng-maxlength="4"
                                               ng-pattern="/^[0-9]+$/"ng-model="registration.ssn" required>
                                        <span ng-show="registerForm.ssn.$valid"
                                              class="glyphicon glyphicon-ok form-control-feedback"></span>
                                        <span ng-show="!registerForm.ssn.$valid && registerForm.ssn.$dirty"
                                              class="glyphicon glyphicon-remove form-control-feedback"></span>
                                    </div>
                                </div>
                                <div class="form-group"
                                     ng-class="{'has-success has-feedback': registerForm.username.$valid, 'has-error has-feedback': !registerForm.username.$valid && registerForm.username.$dirty}">
                                    <label for="username" class="col-xs-2 control-label">Username</label>
                                    <div class="col-xs-10">
                                        <input type="text" class="form-control" id="username" name="username" placeholder="Username"
                                               ng-minlength="7" ng-pattern="/^[a-zA-Z0-9]+$/" ng-model="registration.username"
                                               required>
                                        <span ng-show="registerForm.username.$valid"
                                              class="glyphicon glyphicon-ok form-control-feedback"></span>
                                        <span ng-show="!registerForm.username.$valid && registerForm.username.$dirty"
                                              class="glyphicon glyphicon-remove form-control-feedback"></span>
                                    </div>
                                </div>
                                <div class="form-group"
                                     ng-class="{'has-success has-feedback': registerForm.password.$valid, 'has-error has-feedback': !registerForm.password.$valid && registerForm.password.$dirty}">
                                    <label for="password" class="col-xs-2 control-label">Password</label>
                                    <div class="col-xs-10">
                                        <input type="password" class="form-control" id="password" name="password" placeholder="Password"
                                               ng-minlength="8" ng-model="registration.password" required>
                                        <span ng-show="registerForm.password.$valid"
                                              class="glyphicon glyphicon-ok form-control-feedback"></span>
                                        <span ng-show="!registerForm.password.$valid && registerForm.password.$dirty"
                                              class="glyphicon glyphicon-remove form-control-feedback"></span>
                                    </div>
                                </div>
                                <div class="form-group"
                                     ng-class="{'has-success has-feedback': registerForm.confPassword.$valid, 'has-error has-feedback': !registerForm.confPassword.$valid && registerForm.confPassword.$dirty}">
                                    <label for="confPassword" class="col-xs-2 control-label">Confirm</label>
                                    <div class="col-xs-10">
                                        <input type="password" class="form-control" id="confPassword" name="confPassword"
                                               placeholder="Confirm Password" ng-minlength="8"
                                               ng-model="confPassword" ng-valid="false" required>
                                        <span ng-show="registerForm.confPassword.$valid"
                                              class="glyphicon glyphicon-ok form-control-feedback"></span>
                                        <span ng-show="!registerForm.confPassword.$valid && registerForm.confPassword.$dirty"
                                              class="glyphicon glyphicon-remove form-control-feedback"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-xs-offset-2 col-xs-10">
                                        <button type="submit" class="btn btn-success btn-block"
                                                ng-disabled="registerForm.$invalid">
                                            Register
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
