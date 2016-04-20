/**
 * Created by danielkarkee on 4/20/16.
 */
angular.module("estApp")
    .controller("resetCtrl", function($scope, $http) {
        $scope.resetPassword = function(reset) {
            $http({
                method: "post",
                url: "reset",
                data: reset
            }).then(function successCallback() {
                $scope.success = true;
                $scope.error = false;
            }, function errorCallback() {
                $scope.error = true;
                $scope.sucess = false;
                $scope.reset.password = null;
                $scope.confPassword = null;
            });
        };
    });