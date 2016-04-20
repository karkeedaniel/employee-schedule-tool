/**
 * Created by danielkarkee on 3/2/16.
 */
angular.module("estApp")
    .config(function($stateProvider, $urlRouterProvider, $httpProvider) {

        $urlRouterProvider.otherwise("/login");

        $stateProvider
            .state("login", {
                url: "/login",
                templateUrl: "login"
            })
            .state("register", {
                url: "/register",
                templateUrl: "register"
            })
            .state("main", {
                url: "/main",
                templateUrl: "main"
            })
            .state("main.employee", {
                url: "/employee",
                views: {
                    "sectionView": {
                        templateUrl: "employee"
                    }
                }
            })
            .state("main.employee.add", {
                url: "/add",
                views: {
                    "sectionView@main": {
                        templateUrl: "addEmployee"
                    }
                }
            })
            .state("main.employee.edit", {
                url: "/edit",
                views: {
                    "sectionView@main": {
                        templateUrl: "editEmployee"
                    }
                },
                params: {
                    employeeId: null
                }
            })
            .state("main.job", {
                url: "/job",
                views: {
                    "sectionView": {
                        templateUrl: "job"
                    }
                }
            })
            .state("main.job.add", {
                url: "/add",
                views: {
                    "sectionView@main": {
                        templateUrl: "addJob"
                    }
                }
            })
            .state("main.employee.schedule", {
                url: "/schedule",
                views: {
                    "sectionView@main": {
                        templateUrl: "schedule"
                    }
                }
            })
            .state("main.schedule", {
                url: "/schedule",
                views: {
                    "sectionView": {
                        templateUrl: "schedule"
                    }
                }
            });

        $httpProvider.defaults.headers.common["X-Requested-With"] = "XMLHttpRequest";
    })
    .controller("estCtrl", function($rootScope, $scope, $http, $state) {
        $state.go("login");
        $scope.logout = function () {
            $http.post('logout', {}).success(function () {
                $rootScope.authenticated = false;
                $state.go("login");
            }).error(function () {
                $rootScope.authenticated = false;
            });
        };
    });
