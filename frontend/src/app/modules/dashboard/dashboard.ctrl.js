/**
 * Created by wrichtsfeld on 04/12/2017.
 */
(function (angular) {
    'use strict';
    var ngmod = angular.module('apieval.dashboard');

    ngmod.controller('DashboardController', [
        '$scope',
        '$rootScope',
        'ngDialog',
        '$http',
        'APIEvalService',
        function ($scope, $rootScope,ngDialog, $http, APIEvalService) {
            console.log("dashboard loaded");

            $scope.selectedApi = undefined;
            $scope.selectedVersion = undefined;
            $scope.selectedFile = undefined;
            $scope.selectedReport = undefined;
            $scope.apis = undefined;
            $scope.apiurl = undefined;
            $scope.apifile = undefined;

            loadData();

            function loadData() {
                $scope.loading.inc();
                APIEvalService.getAllAPIs().then(function (apis) {
                    $scope.apis = apis;
                    $scope.loading.dec();
                });
            }

            $scope.showAPI = function(e) {
                $scope.show(e);
                //if expanded, close others too
                if(!e.expanded){
                    for(var i = 0; i < e.versions.length; i++){
                        if(e.versions[i].expanded) {
                            $scope.showVersion(e.versions[i]);
                        }
                    }
                }
            };
            $scope.showVersion = function(e) {
                $scope.show(e);
                //if expanded, close others too
                if(!e.expanded){
                    for(var i = 0; i < e.revisions.length; i++){
                        if(e.revisions[i].expanded) {
                            $scope.showRevision(e.revisions[i]);
                        }
                    }
                }
            };
            $scope.showRevision = function(e) {
                $scope.show(e);
            };

            $scope.show = function(e){
                if(e.expanded){
                    e.expanded = false;
                } else e.expanded = true;
            };

            $scope.validate = function(e){
                console.log("validate " + e.timestamp);

                $scope.loading.inc();
                APIEvalService.validateAPI([e.file]).then(function (data) {
                    $scope.loading.dec();
                });
            };

            $scope.showReport = function() {
                console.log("show API");
                ngDialog.openConfirm({
                    template: 'app/modules/reports/reportdefault.tpl.html',
                    showClose: true,
                    className:"ngdialog-theme-default",
                    scope: $scope
                }).then(closedReportDialog()).catch(function (error) {
                        console.error(error);});
            };
            function closedReportDialog() {
                console.log("closed dialog");
            }

            $scope.select = function(api, version, revision, report){
                if(api) $scope.selectedApi = api;
                if(version) $scope.selectedVersion = version;
                if(revision) {
                    console.log("select file:" + revision.file);
                    $scope.apiurl = revision.file;
                    $scope.selectedFile = revision;

                }
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