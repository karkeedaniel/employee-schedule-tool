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
            <div class="col-md-4"></div>
            <div class="col-md-4">
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
                        <form name="registerForm" class="form-horizontal" ng-submit=""novalidate>
                            <div class="form-group">
                                <label for="email" class="col-sm-2 control-label">Email</label>
                                <div class="col-sm-10">
                                    <input type="email" class="form-control" id="email" placeholder="Email" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="dob" class="col-sm-2 control-label">DOB</label>
                                <div class="col-sm-10">
                                    <input type="number" class="form-control" id="dob" placeholder="Birth year" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="ssn" class="col-sm-2 control-label">SSN</label>
                                <div class="col-sm-10">
                                    <input type="number" class="form-control" id="ssn" placeholder="Last 4 digit" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-primary btn-block" ng-disabled="registerForm.$invalid">
                                        Register
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
