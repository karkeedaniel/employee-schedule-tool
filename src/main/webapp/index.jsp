<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="estApp" lang="en" >
    <head>
        <meta charset="UTF-8">
        <title>Employee Schedule Tool</title>
        <spring:url value="/resources/bootstrap/css/bootstrap.min.css" var="bootstrapCSS"/>
        <spring:url value="/resources/bootstrap/css/bootstrap-theme.min.css" var="bootstrapThemeCSS"/>
        <spring:url value="/resources/font-awesome/css/font-awesome.min.css" var="fontAwesomeCSS"/>
        <spring:url value="/resources/font-awesome/css/font-awesome.min.css" var="fontAwesomeCSS"/>

        <link rel="stylesheet" href="${bootstrapCSS}">
        <link rel="stylesheet" href="${bootstrapThemeCSS}">
        <link rel="stylesheet" href="${fontAwesomeCSS}">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">

        <spring:url value="/resources/jquery/js/jquery.min.js" var="jqueryJS" />
        <spring:url value="/resources/angular/js/angular.min.js" var="angularJS" />
        <spring:url value="/resources/angular/js/angular-animate.min.js" var="angularAnimateJS" />
        <spring:url value="/resources/angular-datatables/js/angular-datatables.min.js" var="angularDatatablesJS" />
        <spring:url value="/resources/angular-ui-bootstrap/js/angular-ui-bootstrap.min.js" var="angularUiBootstrapJS" />
        <spring:url value="/resources/est/js/est.js" var="estJS" />
        <spring:url value="/resources/est/js/auth.js" var="authJS" />
        <spring:url value="/resources/est/js/main.js" var="mainJS" />
        <spring:url value="/resources/est/js/employee.js" var="employeeJS" />
        <spring:url value="/resources/est/js/register.js" var="registerJS" />
        <spring:url value="/resources/est/js/schedule.js" var="scheduleJS" />
        <spring:url value="/resources/est/js/job.js" var="jobJS" />

        <script src="${jqueryJS}"></script>
        <script src="${angularJS}"></script>
        <script src="${angularAnimateJS}"></script>
        <script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
        <script src="${angularDatatablesJS}"></script>
        <script src="${angularUiBootstrapJS}"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/1.2.5/ui-bootstrap-tpls.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/0.2.18/angular-ui-router.min.js"></script>

        <script>
            angular.module("estApp", ["ui.router", "datatables", "ui.bootstrap", "ngAnimate"]);
        </script>

        <script src="${estJS}"></script>
        <script src="${authJS}"></script>
        <script src="${mainJS}"></script>
        <script src="${employeeJS}"></script>
        <script src="${registerJS}"></script>
        <script src="${scheduleJS}"></script>
        <script src="${jobJS}"></script>

        <%--<base href="/est/"></base>--%>

    </head>
    <body ng-controller="estCtrl">
    <nav class="navbar navbar-default navbar-static-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="">EMPLOYEE SCHEDULE TOOL</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="navbar-collapse">
                <ul class="nav navbar-nav navbar-right" ng-show="authenticated">
                    <li><a href="" ng-click="logout()">Logout</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container-fluid" ui-view></div>
    </body>
</html>

