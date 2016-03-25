<%--
  Created by IntelliJ IDEA.
  User: danielkarkee
  Date: 3/23/16
  Time: 8:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <div class="panel panel-default row" ng-controller="dirCtrl">
            <div class="col-xs-2 panel-body">
                <a ng-repeat="item in screens" class="btn btn-block btn-default"
                   ng-class="{'btn-primary': item == current }" ng-click="setScreen($index)">
                    {{item}}
                </a>
            </div>
            <div class="col-xs-9 panel-body" >
                <div ng-include="getScreen()"></div>
            </div>
        </div>
    </body>
</html>
