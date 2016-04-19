/**
 * Created by danielkarkee on 3/25/16.
 */
angular.module("estApp")
    .controller("employeeCtrl", function($scope, $state, $http) {
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
                $scope.success = true;
                $scope.error = false;
                $scope.employee = null;
                $scope.location = null;
            }, function errorCallback(response) {
                $scope.error = true;
                $scope.sucess = false;
            });
        };

        $scope.return = function() {
            $state.go("main.employee");
        };
    })
    .controller("editEmployeeCtrl", function($scope, $state, $stateParams, $http) {
        $http({
            method: "get",
            url: "/employee-location/get",
            params: {
                'employeeId': $stateParams.employeeId
            }
        }).then(function successCallback(response) {
            $scope.employee = response.data.employee;
            $scope.location = response.data.location;
        });

        $scope.return = function() {
            $state.go("main.employee");
        };

        $scope.update = function(employee, location) {
            var employeeLocation = {employee, location};
            employeeLocation.employee = employee;
            employeeLocation.location = location;
            $http({
                method: "put",
                url: "/employee-location/update",
                data: employeeLocation
            }).then(function successCallback(response) {
                $scope.success = true;
                $scope.error = false;
                $scope.employee = response.data.employee;
                $scope.location = response.data.location;
            }, function errorCallback(response) {
                $scope.error = true;
                $scope.sucess = false;
            });
        }
    });