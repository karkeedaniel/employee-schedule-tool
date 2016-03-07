angular.module("estApp")
    .controller("authController", function($scope, $http, $location) {
        $scope.login = function(credentials) {
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
            });
        }
    });