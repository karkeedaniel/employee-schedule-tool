/**
 * Created by danielkarkee on 4/6/16.
 */
angular.module("estApp")
    .controller("scheduleCtrl", function($rootScope, $scope, $http, $filter, $uibModal, $state, DTOptionsBuilder, DTColumnDefBuilder) {
        $scope.jobScheduleList = [];

        getByDate = function(date) {
            $http({
                method: "get",
                url: "/job-schedule/getByEmployeeIdAndStartTime",
                params: {
                    'employeeId': $rootScope.id,
                    'startTime': $filter('date')(date, "yyyy-MM-dd HH:mm:ss")
                }
            }).then(function successCallback(response) {
                $scope.jobScheduleList = response.data;
            });
        };

        getCurrDate = function() {
            $scope.date = new Date();
            getByDate($scope.date);
        };

        getCurrDate();

        $scope.currDate = function() {
            getCurrDate();
        };

        $scope.prevDate = function(date) {
            $scope.date.setDate(date.getDate() - 1);
            getByDate($scope.date);
        };

        $scope.nextDate = function(date) {
            $scope.date.setDate(date.getDate() + 1);
            getByDate($scope.date);
        };

        $scope.open = function(jobName, location) {
            var job = [];
            job.name = jobName;
            job.location = location;

            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'locationModal',
                controller: 'locationModalInstanceCtrl',
                size: 'sm',
                resolve: {
                    job: function () {
                        return job;
                    }
                }
            })
        };

        $scope.event = function(job) {
            var newJob = job;
            if (newJob.jobState == 'SCHEDULED') {
                newJob.jobState = 'INPROGRESS'
            } else {
                newJob.jobState = 'COMPLETE';
            }
            $http({
                method: "put",
                url: "/job/update",
                data: newJob
            }).then(function successCallback() {
                $state.go("main.schedule");
            }, function errorCallback() {
                $state.go("main.schedule");
            });
        };

        $scope.dtOptions = DTOptionsBuilder.newOptions()
            .withOption('order', [2, 'desc']);
        $scope.dtColumnDefs = [
            DTColumnDefBuilder.newColumnDef(6).notSortable(),
            DTColumnDefBuilder.newColumnDef(7).notSortable()
        ];
    })
    .controller('locationModalInstanceCtrl', function ($scope, $uibModalInstance, job) {
        $scope.job = job;

        $scope.ok = function () {
            $uibModalInstance.close();
        };
    });