/**
 * Created by danielkarkee on 3/2/16.
 */
angular.module("estApp")
    .controller("authCtrl", function($rootScope, $scope, $http, $location) {
        var url;

        var authenticate = function(credentials, callback) {

            $http.defaults.headers.common['authorization'] = "Basic " + btoa(credentials.username + ":" + credentials.password);

            $http({
                method: "get",
                url: "user-url"
            }).then(function successCallback(response) {
                $rootScope.user = response.data.user;
                url = response.data.url;
                $rootScope.authenticated = true;
                callback && callback();
            }, function errorCallback() {
                $rootScope.authenticated = false;
                callback && callback();
            })
        };

        $scope.login = function(credentials) {
            authenticate(credentials, function() {
                if ($rootScope.authenticated) {
                    $location.path(url);
                }
                $scope.error = !$rootScope.authenticated;
            });
        };

    });