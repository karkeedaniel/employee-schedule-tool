<%--
  Created by IntelliJ IDEA.
  User: danielkarkee
  Date: 3/4/16
  Time: 8:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <div class="container-fluid" ng-controller="scheduleCtrl">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-md-4">
                            <h5>{{date | date:'mediumDate'}}</h5>
                        </div>
                        <div class="col-md-8">
                            <div class="pull-right">
                                <button type="button" class="btn btn-default" ng-click="currDate()">Today</button>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" ng-click="prevDate(date)">Prev</button>
                                    <button type="button" class="btn btn-default" ng-click="nextDate(date)">Next</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <table datatable="" class="row-border hover">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>First name</th>
                            <th>Last name</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td>Foo</td>
                            <td>Bar</td>
                        </tr>
                        <tr>
                            <td>123</td>
                            <td>Someone</td>
                            <td>Youknow</td>
                        </tr>
                        <tr>
                            <td>987</td>
                            <td>Iamout</td>
                            <td>Ofinspiration</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>