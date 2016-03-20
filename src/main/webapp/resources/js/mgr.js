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

        $scope.editable = function(employee, edit) {
            console.log(employee);
            var modalInstance = $uibModal.open({
                animation: edit,
                templateUrl: 'empModalContent.html',
                controller: 'ModalInstanceCtrl',
                resolve: {
                    items: function () {
                        return $scope.items;
                    }
                }
            });
        };
    })
    .controller('ModalInstanceCtrl', function ($scope, $uibModalInstance) {

        $scope.ok = function () {
            $uibModalInstance.close($scope.selected.item);
        };

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
    });

