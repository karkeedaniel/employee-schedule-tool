/**
 * Created by danielkarkee on 3/20/16.
 */
angular.module("estApp")
    .controller("mgrCtrl", function($scope, $http, $uibModal) {
        $scope.employeeList = [];

        $http({
            method: "get",
            url: "/employee/get-all/"
        }).then(function successCallback(response) {
            $scope.employeeList = response.data;
        });

        $scope.editor = function(employee) {
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: '/empModal',
                controller: 'modalInstanceCtrl',
                resolve: {
                    employee: function () {
                        return employee;
                    }
                }
            });
        };

        $scope.add = function() {
            var employee = [];
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: '/addEmpModal',
                controller: 'modalInstanceCtrl',
                resolve: {
                    employee: function () {
                        return employee;
                    }
                }
            })
        }
    })
    .controller('modalInstanceCtrl', function ($scope, $http, $uibModalInstance, employee) {
        $scope.employee = employee;

        $scope.update = function(employee) {
            $http({
                method: "post",
                url: "/employee/update",
                data: employee
            }).then(function successCallback(response) {
                $scope.employee = response.data;
            });
        };

        $scope.ok = function () {
            $uibModalInstance.close();
        };

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };

        $scope.add = function(employee) {
            console.log(employee);
        }
    });

