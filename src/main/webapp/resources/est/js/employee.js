/**
 * Created by danielkarkee on 3/25/16.
 */
angular.module("estApp")
    .controller("employeeCtrl", function($scope, $state, $http, $window) {
        $scope.employeeList = [];

        $http({
            method: "get",
            url: "/employee/get-all/"
        }).then(function successCallback(response) {
            $scope.employeeList = response.data;
        });

        $scope.add = function(employee, location) {
            var employeeLocation = {employee, location};
            employeeLocation.employee = employee;
            employeeLocation.location = location;
            $http({
                method: "post",
                url: "/employee-location/persist",
                data: employeeLocation
            }).then(function successCallback(response) {
                // TODO - need to work on alert message.
                $window.alert("Employee successfully added.");
                $scope.employee = null;
                $scope.location = null;
            }, function errorCallback(response) {
                if (response.status == 409) {
                    $scope.error = true;
                }
            });
        };

        $scope.return = function() {
            $state.go("main.employee");
        };
    });