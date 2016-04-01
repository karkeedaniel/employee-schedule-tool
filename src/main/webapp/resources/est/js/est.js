/**
 * Created by danielkarkee on 3/2/16.
 */
angular.module("estApp")
    .config(function($stateProvider, $urlRouterProvider, $httpProvider) {

        $urlRouterProvider.otherwise("/login");

        $stateProvider
            .state("login", {
                url: "/login",
                templateUrl: "/login"
            })
            .state("register", {
                url: "/register",
                templateUrl: "/register"
            })
            .state("main", {
                url: "/main",
                templateUrl: "/main"
            })
            .state("main.employee", {
                views: {
                    "sectionView": {
                        templateUrl: "/employee"
                    }
                }
            })
            .state("main.employee.add", {
                views: {
                    "sectionView@main": {
                        templateUrl: "/addEmployee"
                    }
                }
            })
            .state("main.employee.edit", {
                views: {
                    "sectionView@main": {
                        templateUrl: "/editEmployee"
                    }
                }
            })
            .state("main.employee.schedule", {
                views: {
                    "sectionView@main": {
                        templateUrl: "/schedule"
                    }
                }
            })
            .state("main.approval", {
                views: {
                    "sectionView": {
                        templateUrl: "/approval"
                    }
                }
            });

        $httpProvider.defaults.headers.common["X-Requested-With"] = "XMLHttpRequest";
    })
    .controller("estCtrl", function($rootScope, $scope, $http, $state) {
        $scope.logout = function () {
            $http.post('logout', {}).success(function () {
                $rootScope.authenticated = false;
                $state.go("login");
            }).error(function () {
                $rootScope.authenticated = false;
            });
        };
    });
