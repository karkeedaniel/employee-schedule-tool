/**
 * Created by danielkarkee on 3/2/16.
 */
angular.module("estApp")
    .config(function($routeProvider, $httpProvider) {
        $routeProvider.when("/login", {
            templateUrl: "login"
        });

        $routeProvider.when("/mgr", {
            templateUrl: "mgr"
        });

        $routeProvider.when("/reset", {
            templateUrl: "reset"
        });

        $routeProvider.when("/register", {
            templateUrl: "register"
        });

        $routeProvider.when("/empModal", {
            templateUrl: "empModal"
        });

        $routeProvider.otherwise({
           templateUrl: "login"
        });

        $httpProvider.defaults.headers.common["X-Requested-With"] = "XMLHttpRequest";
    })
    .controller("estCtrl", function($rootScope, $scope, $http, $location) {
        $scope.logout = function () {
            $http.post('logout', {}).success(function () {
                $rootScope.authenticated = false;
                $location.path("/login");
            }).error(function () {
                $rootScope.authenticated = false;
            });
        };
    });
