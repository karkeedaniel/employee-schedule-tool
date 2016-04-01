/**
 * Created by danielkarkee on 3/23/16.
 */
angular.module("estApp")
    .controller("dirCtrl", function($scope) {
        $scope.screens = ["Employee", "Approval"];
        $scope.current = $scope.screens[0];

        $scope.setScreen = function (index) {
                $scope.current = $scope.screens[index];
        };
        $scope.getScreen = function () {
            return $scope.current == "Employee"
                ? "/emp" : "/approval";
        };
    });