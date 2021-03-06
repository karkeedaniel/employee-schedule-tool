<%--
  Created by IntelliJ IDEA.
  User: danielkarkee
  Date: 3/1/16
  Time: 6:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <div class="row" ng-controller="authCtrl">
            <div class="col-xs-offset-4 col-md-4">
                <div class="panel panel-primary">
                    <div class="panel-heading text-center">
                        <strong>Log in to EST</strong>
                    </div>
                    <div class="panel-body">
                        <div class="alert alert-danger" ng-show="error">
                            There was a problem logging in. Please try again.
                        </div>
                        <form name="authForm" ng-submit="login(credentials)" novalidate>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-addon"><i class="fa fa-user"></i></div>
                                    <input type="text" name="username" class="form-control" placeholder="Username" ng-model="credentials.username" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-addon"><i class="fa fa-lock"></i></div>
                                    <input type="password" name="password" class="form-control" placeholder="Password" ng-model="credentials.password" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-success btn-block" ng-disabled="authForm.$invalid">
                                    Log in
                                </button>
                            </div>
                            <div class="form-group">
                                <div class="col-md-6">
                                    <div class="text-center">
                                        <a ui-sref="register" class="btn btn-primary btn-block">Register</a>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="text-center">
                                        <a ui-sref="reset" class="btn btn-warning btn-block">Reset Password</a>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
