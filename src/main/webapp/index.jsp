<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="estApp" lang="en" >
    <head>
        <meta charset="UTF-8">
        <title>Employee Schedule Tool</title>
        <spring:url value="/resources/js/est.js" var="estJS" />
        <spring:url value="/resources/js/auth.js" var="authJS" />

        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
              integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
              crossorigin="anonymous">
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
              integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
              crossorigin="anonymous">
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">


        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular-route.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

        <script>
            angular.module("estApp", ["ngRoute"]);
        </script>

        <script src="${estJS}"></script>
        <script src="${authJS}"></script>

    </head>
    <body>
        <div class="container-fluid">
            <div class="navbar navbar-inverse navbar-static-top">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">EMPLOYEE SCHEDULE TOOL</a>
                </div>
            </div>
            <ng-view />
        </div>
    </body>
</html>