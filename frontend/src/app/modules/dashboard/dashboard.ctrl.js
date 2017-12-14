/**
 * Created by wrichtsfeld on 04/12/2017.
 */
(function (angular) {
    'use strict';
    var ngmod = angular.module('apieval.dashboard');

    ngmod.controller('DashboardController', [
        '$scope',
        '$rootScope',
        '$http',
        'APIEvalService',
        function ($scope, $rootScope, $http, APIEvalService) {
            console.log("dashboard loaded");

            $scope.selectedApi = undefined;
            $scope.selectedFile = undefined;
            $scope.selectedReport = undefined;
            $scope.apis = undefined;

            $scope.apifile = undefined;

            loadData();

            function loadData() {
                $scope.loading.inc();
                APIEvalService.getAllAPIs().then(function (apis) {
                    $scope.apis = apis;
                    $scope.loading.dec();
                });
            }

            $scope.show = function(e){
                if(e.expanded){
                    e.expanded = false;
                }else e.expanded = true;
                if(e.version){
                    for(i = 0; i < e.version.length; i++){
                        e.version[i].expanded = false;
                    }
                }
            };
            $scope.showFile = function(e){
                console.log("showfile");
                if(e.expanded){
                    e.expanded = false;
                }else e.expanded = true;

            };

            $scope.validate = function(e){
                console.log("validate " + e.timestamp);

                $scope.loading.inc();
                APIEvalService.validateAPI([e.file]).then(function (data) {
                    $scope.loading.dec();
                });
            };

            $scope.select = function(api, file, report){
                if(api) $scope.selectedApi = api;
                if(file) $scope.selectedFile = file;
                if(report) $scope.selectedReport = report;
            };

            /*$scope.$watch('apifile', function () {
                console.log("apifile changed");
                $scope.upload($scope.apifile);
            });*/

            $scope.upload = function (files) {
                var file = files[0];
                if(file != undefined) {
                    var reader = new FileReader();

                    // Closure to capture the file information.
                    reader.onload = (function (file) {
                        return function (e) {
                            console.log('e readAsText target = ', e.target);
                            APIEvalService.postNewAPIs(e.target.result).then(function (resp) {
                                loadData();
                            });
                        };
                    })(file);
                    reader.readAsText(file);
                }
            };
        }]);
})(angular);