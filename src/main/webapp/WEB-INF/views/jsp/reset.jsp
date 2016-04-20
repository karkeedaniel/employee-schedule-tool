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
    <div class="row" ng-controller="resetCtrl">
        <div class="col-xs-offset-3 col-md-6">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-6">
                            <h4>Reset Password</h4>
                        </div>
                        <div class="col-xs-6">
                            <a ui-sref="login" class="btn btn-warning pull-right">Return to Login</a>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="alert alert-danger" ng-show="error">
                        There was a problem resetting password. Please check your entry and try again.
                    </div>
                    <div class="alert alert-success" ng-show="success">
                        Password reset completed.
                    </div>
                    <div ng-hide="success">
                        <form class="form-horizontal" name="resetForm" ng-submit="resetPassword(reset)" autocomplete="off" novalidate>
                            <div class="form-group"
                                 ng-class="{'has-success has-feedback': resetForm.username.$valid, 'has-error has-feedback': !resetForm.username.$valid && resetForm.username.$dirty}">
                                <label for="username" class="col-xs-2 control-label">Username</label>
                                <div class="col-xs-10">
                                    <input type="text" class="form-control" id="username" name="username" placeholder="Username"
                                           ng-minlength="7" ng-pattern="/^[a-zA-Z0-9]+$/" ng-model="registration.username"
                                           required>
                                        <span ng-show="resetForm.username.$valid"
                                              class="glyphicon glyphicon-ok form-control-feedback"></span>
                                        <span ng-show="!resetForm.username.$valid && resetForm.username.$dirty"
                                              class="glyphicon glyphicon-remove form-control-feedback"></span>
                                </div>
                            </div>
                            <div class="form-group"
                                 ng-class="{'has-success has-feedback': resetForm.year.$valid, 'has-error has-feedback': !resetForm.year.$valid && resetForm.year.$dirty}">
                                <label for="year" class="col-xs-2 control-label">DOB</label>
                                <div class="col-xs-10">
                                    <input type="text" id="year" name="year" class="form-control" placeholder="Birth year"
                                           ng-model="resetForm.year" ng-minlength="4" ng-maxlength="4"
                                           ng-pattern="/^[0-9]+$/" required>
                                        <span ng-show="resetForm.year.$valid"
                                              class="glyphicon glyphicon-ok form-control-feedback"></span>
                                        <span ng-show="!resetForm.year.$valid && resetForm.year.$dirty"
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
