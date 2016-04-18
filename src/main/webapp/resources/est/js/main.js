/**
 * Created by danielkarkee on 3/25/16.
 */
angular.module("estApp")
    .controller("mainCtrl", function($rootScope, $scope, $state) {

        if ($rootScope.role === "DIRECTOR") {
            $scope.screens = [
                {name: "Employee", url: ".employee"},
                {name: "Approval", url: ".approval"},
                {name: "Job", url: ".job"}
            ];
            $state.go($scope.screens[0].url);
        } else if ($rootScope.role === "MANAGER") {
            $scope.screens = [
                {name: "Employee", url: ".employee"},
                {name: "Approval", url: ".approval"},
                {name: "Job", url: ".job"}
            ];
            $state.go($scope.screens[0].url);
        } else if ($rootScope.role === "PM") {
            $scope.screens = [
                {name: "Job", url: ".job"}
            ];
            $state.go($scope.screens[0].url);
        } else if ($rootScope.role === "TECHNICIAN") {
            $scope.screens = [
                {name: "Schedule", url: ".schedule"}
            ];
            $state.go($scope.screens[0].url);
        }

        $scope.current = $scope.screens[0];

        $scope.setScreen = function (index) {
            $scope.current = $scope.screens[index];
        };
    });