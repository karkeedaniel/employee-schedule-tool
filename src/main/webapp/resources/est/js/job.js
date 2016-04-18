/**
 * Created by danielkarkee on 3/25/16.
 */
angular.module("estApp")
    .controller("jobCtrl", function($scope, $state, $http, $window) {
        $scope.jobList = [];

        $http({
            method: "get",
            url: "/job/get-all/"
        }).then(function successCallback(response) {
            $scope.jobList = response.data;
        });

        $scope.add = function(job, location) {
            var jobWithLocation = {job, location};
            jobWithLocation.job = job;
            jobWithLocation.location = location;
            $http({
                method: "post",
                url: "/job-location/persist",
                data: jobWithLocation
            }).then(function successCallback(response) {
                // TODO - need to work on alert message.
                $window.alert("Job successfully added.");
                $scope.job = null;
                $scope.location = null;
            }, function errorCallback(response) {
                console.log(response);
                if (response.status == 409) {
                    $scope.error = true;
                }
            });
        };
        $scope.return = function() {
            $state.go("main.job");
        };
    });