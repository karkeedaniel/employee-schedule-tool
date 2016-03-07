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
    });
