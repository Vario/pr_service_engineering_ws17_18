/**
 * Created by wrichtsfeld on 04/12/2017.
 */
(function (angular) {
    'use strict';
    var ngmod = angular.module('apieval.dashboard');

    ngmod.controller('DashboardController', [
        '$scope',
        '$rootScope',
        'APIEvalService',
        function ($scope, $rootScope, APIEvalService) {
            console.log("dashboard loaded");

            $scope.selectedApi = undefined;
            $scope.selectedVersion = undefined;
            $scope.selectedFile = undefined;
            $scope.selectedReport = undefined;
            $scope.apis = undefined;

            loadData();

            function loadData() {
                $scope.loading.inc();
                APIEvalService.getAllAPIs().then(function (apis) {
                    $scope.apis = apis;
                    $scope.loading.dec();
                });
            }

            $scope.show = function(e){
                if(e.selected){
                    e.selected = false;
                    $scope.selectedApi = [];
                    $scope.selectedVersion = [];
                    $scope.selectedFile = [];
                    $scope.selectedReport = [];
                }else e.selected = true;
                if(e.version){
                    $scope.selectedVersion = [];
                    $scope.selectedFile = [];
                    $scope.selectedReport = [];
                    for(i = 0; i < e.version.length; i++){
                        e.version[i].selected = false;
                    }
                }
            };

            $scope.select = function(api, version, file, report){
                if(api) $scope.selectedApi = api;
                if(version) $scope.selectedVersion = version;
                if(file) $scope.selectedFile = file;
                if(report) $scope.selectedReport = report;
            };
        }]);
})(angular);