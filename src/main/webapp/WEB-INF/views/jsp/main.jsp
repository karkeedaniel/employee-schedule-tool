<%--
  Created by IntelliJ IDEA.
  User: danielkarkee
  Date: 3/25/16
  Time: 10:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <div class="row" ng-controller="mainCtrl">
            <div class="col-xs-2">
                <a ng-repeat="item in screens" class="btn btn-block btn-default"
                   ng-class="{'btn-primary': item == current }" ui-sref={{item.url}} ng-click="setScreen($index)">
                    {{item.name}}
                </a>
            </div>
            <div class="col-xs-10" >
                <div ui-view="sectionView"></div>
            </div>
        </div>
    </body>
</html>
