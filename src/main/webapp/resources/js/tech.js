/**
 * Created by danielkarkee on 3/12/16.
 */
angular.module("estApp")
    .controller("techCtrl", function($rootScope, $scope, $http, $location) {
        $rootScope.authenticated = true;
        console.log("I am here");
    });