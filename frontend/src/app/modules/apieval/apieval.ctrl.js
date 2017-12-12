/**
 * Created by wrichtsfeld on 30/11/2017.
 */
(function (angular) {
    'use strict';
    var ngmod = angular.module('apieval.apieval');

    ngmod.controller('APIEvalController', [
        '$scope',
        '$rootScope',
        '$http',
        'APIEvalService',
        function ($scope, $rootScope, $http, APIEvalService) {
            $scope.apis = undefined;

            loadData();
            function loadData() {
                $scope.loading.inc();
                APIEvalService.getAllAPIs().then(function (apis) {
                    $scope.apis = apis;
                    $scope.loading.dec();
                });
            }
        }]);
})(angular);
