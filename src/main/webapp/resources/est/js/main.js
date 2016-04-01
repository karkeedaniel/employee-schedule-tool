/**
 * Created by danielkarkee on 3/25/16.
 */
angular.module("estApp")
    .controller("mainCtrl", function($rootScope, $scope, $state) {

        if ($rootScope.role === "DIRECTOR") {
            $scope.screens = [
                {name: "Employee", url: ".employee"},
                {name: "Approval", url: ".approval"}
            ];
            $state.go($scope.screens[0].url);
        }

        $scope.current = $scope.screens[0];

        $scope.setScreen = function (index) {
            $scope.current = $scope.screens[index];
        };
    });