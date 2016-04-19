/**
 * Created by danielkarkee on 3/30/16.
 */
angular.module("estApp")
    .controller("registerCtrl", function($scope, $http) {
        $scope.register = function(registration) {
            $http({
                method: "post",
                url: "register",
                data: registration
            }).then(function successCallback() {
                $scope.success = true;
                $scope.error = false;
                $scope.registration = null;
                $scope.confPassword = null;
            }, function errorCallback() {
                $scope.error = true;
                $scope.sucess = false;
                $scope.registration.password = null;
                $scope.confPassword = null;
            });
        };
    });