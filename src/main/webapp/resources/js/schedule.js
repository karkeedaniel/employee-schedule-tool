/**
 * Created by danielkarkee on 3/15/16.
 */
angular.module("estApp")
    .controller("scheduleCtrl", function($scope) {
        $scope.date = new Date();

        $scope.currDate = function() {
            $scope.date = new Date();
        };

        $scope.prevDate = function(date) {
            $scope.date.setDate(date.getDate() - 1);
        };

        $scope.nextDate = function(date) {
            $scope.date.setDate(date.getDate() + 1);
        };
    });