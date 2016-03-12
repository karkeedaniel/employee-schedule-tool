angular.module("estApp")
    .controller("authController", function($rootScope, $scope, $http, $location) {
        var authenticate = function(credentials, callback) {
            $http({
                method: "post",
                url: "login",
                headers: {
                    'authorization': "Basic " + btoa(credentials.username + ":" + credentials.password),
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                data: $.param({
                    username: credentials.username,
                    password: credentials.password
                })
            }).then(function successCallback() {
                $location.path("/technician");
                console.log($location.path());

                $rootScope.authenticated = true;
                callback && callback();

            }, function errorCallback() {
                $rootScope.authenticated = false;
                callback && callback();
            })
        };

        $scope.login = function(credentials) {
            authenticate(credentials, function() {
                $scope.error = !$rootScope.authenticated;
            });
        };

        $scope.test = function() {
            $http({
                method: "get",
                url: "technician",
                data: {
                    user: "djk"
                }
            });
        };
    });