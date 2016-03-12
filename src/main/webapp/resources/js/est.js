/**
 * Created by danielkarkee on 3/2/16.
 */
angular.module("estApp")
    .config(function($routeProvider, $httpProvider) {
        $routeProvider.when("/login", {
            templateUrl: "login"
        });

        $routeProvider.when("/technician", {
            templateUrl: "technician"
        });

        $routeProvider.otherwise({
           templateUrl: "login"
        });

        $httpProvider.defaults.headers.common["X-Requested-With"] = "XMLHttpRequest";
    })
    .controller("estController", function($rootScope, $scope, $http, $location) {
        $scope.logout = function () {
            $http.post('logout', {}).success(function () {
                $rootScope.authenticated = false;
                $location.path("/");
            }).error(function (data) {
                $rootScope.authenticated = false;
            });
        };
    })
    .controller("technicianController", function() {
        console.log("technicianController")
    });
