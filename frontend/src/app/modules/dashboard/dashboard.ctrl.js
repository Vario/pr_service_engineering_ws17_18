/**
 * Created by wrichtsfeld on 04/12/2017.
 */
(function (angular) {
    'use strict';
    var ngmod = angular.module('apieval.dashboard');

    ngmod.controller('DashboardController', [
        '$filter',
        '$scope',
        '$rootScope',
        'ngDialog',
        '$http',
        'APIEvalService',
        'APIEvalSettingsService',
        function ($filter, $scope, $rootScope,ngDialog, $http, APIEvalService, APIEvalSettingsService, $sce) {
            console.log("dashboard loaded");

            $scope.selectedApi = undefined;
            $scope.selectedVersion = undefined;
            $scope.selectedFile = undefined;
            $scope.selectedViolationReport = undefined;
            $scope.selectedComparisonReport = undefined;
            $scope.apis = undefined;
            $scope.apifileurl = undefined;
            $scope.apifile = undefined;
            $scope.showSwaggerUI = undefined;
            $scope.selectedComparison = [];

            $scope.switchComparison = function(data){
                var o = $scope.selectedComparison[0];
                $scope.selectedComparison[0] = $scope.selectedComparison[1];
                $scope.selectedComparison[1] = o;
                data[0] = $scope.selectedComparison[0];
                data[1] = $scope.selectedComparison[1];
            };

            $scope.selectComparison = function(a, v, r){
                if($scope.selectedComparison.length < 2 && !r.checked){
                    var o = {
                        api : a,
                        version : v,
                        revision : r
                    };

                    r.checked = true;
                    $scope.selectedComparison.push(o);
                }else{
                    for(var g = 0; g < $scope.selectedComparison.length; g++){
                        if($scope.selectedComparison[g].revision == r){
                            r.checked = false;
                            if($scope.selectedComparison.length == 1) $scope.selectedComparison = [];
                            $scope.selectedComparison = $scope.selectedComparison.splice(g,g);
                        }
                    }
                }
                console.log($scope.selectedComparison);
            };

            loadData();

            function loadData() {
                $scope.loading.inc();
                APIEvalService.getAllAPIs().then(function (apis) {
                    $scope.apis = apis;
                    console.log(apis);
                }).catch(function (error) {
                    console.error(error);
                }).finally(function () {
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
                $scope.loading.inc();
                APIEvalSettingsService.getRules().then(function (rules) {
                    $scope.loading.dec();
                    if($scope.selectedApi.settingsid) {
                        console.log("get settings for id:" + $scope.selectedApi.settingsid);

                        //get api settings for api
                        APIEvalSettingsService.getSetting($scope.selectedApi.settingsid).then(function (settings) {
                            if(settings) {
                                //if there are settings, match them with the rules list
                                console.log("map settings (" + settings.rules.length + " rules) to rulelist");
                                var newRules = [];
                                angular.forEach(rules, function (rule) {
                                    var foundrule = false;
                                    angular.forEach(settings.rules, function (settingsrule) {
                                        if(settingsrule === rule.code) {
                                            foundrule = true;
                                            return;
                                        }
                                    });

                                    if(foundrule) {
                                        rule.checked = true;
                                    }
                                    newRules.push(rule);
                                });
                                $scope.showValidationDialog(e.file,newRules);
                            } else {
                                console.log("no settings found");
                                //if no settings, activate all rules
                                angular.forEach(rules, function (rule) {
                                    rule.checked = true;
                                });
                                $scope.showValidationDialog(e.file,rules);
                            }
                        });
                    } else {
                        console.log("no settings id for api yet");
                        //if no settings, activate all rules
                        angular.forEach(rules, function (rule) {
                            rule.checked = true;
                        });
                        $scope.showValidationDialog(e.file,rules);
                    }
                });
            };

            $scope.saveNewAPIName = function(api) {
                console.log("new api name: " + api.name);
                APIEvalService.updateApiTitle(api,api.name).then(function () {
                    api.edit = false;
                });
            };

            $scope.showCompareDialog = function() {
                var dialog = ngDialog.open({
                    template: 'app/modules/apieval/compare.tpl.html',
                    showClose: true,
                    className: "ngdialog-theme-default",
                    data: $scope.selectedComparison,
                    scope: $scope,
                    controller: ['$scope', function ($scope) {
                        // controller logic
                        $scope.closeDialog = function (compare) {
                            //Pass apis to close handler
                            if(compare) {
                                dialog.close($scope.ngDialogData);
                            } else {
                                dialog.closeByEscape();
                            }
                        };
                    }]
                });

                dialog.closePromise.then(function (data) {
                    $scope.loading.inc();
                    var apis = [data.value[0].revision.file, data.value[1].revision.file];
                    console.log("compare:" + apis);
                    APIEvalService.compareAPIS(apis).then(function (data) {
                        console.log(data);
                        $scope.loading.dec();
                        loadData();
                        $scope.selectedApi.expanded = true;
                        $scope.selectedVersion.expanded = true;
                        $scope.selectedFile.expanded = true;
                    }).catch(function (error) {
                        $scope.loading.dec();
                        console.log("Error comparing api");
                        console.error(error);
                    });
                });
            };

            $scope.showValidationDialog = function(fileid, evalRules) {
                var dialog = ngDialog.open({
                    template: 'app/modules/apieval/settings.apieval.tpl.html',
                    showClose: true,
                    className:"ngdialog-theme-default",
                    data:evalRules,
                    controller: ['$scope', function($scope) {
                        // controller logic
                        $scope.closeDialog = function() {
                            var checkedRules = [];
                            angular.forEach($scope.ngDialogData, function (rule) {
                                if(rule.checked) {
                                    checkedRules.push(rule.code);
                                }
                            });
                            dialog.close(checkedRules);
                        };
                    }]
                });

                dialog.closePromise.then(function (data) {
                    if($scope.selectedApi.settingsid) {
                        //Update settings
                        console.log("update settings");
                        APIEvalSettingsService.updateSetting($scope.selectedApi, data.value).then(function (settings) {
                            console.log("settings update for api:" + $scope.selectedApi.name);
                        });
                    } else {
                        //Create Settings
                        console.log("create settings");
                        APIEvalSettingsService.createSetting($scope.selectedApi.name, data.value).then(function (settings) {
                            //settings created, update api
                            APIEvalService.updateApiSetting($scope.selectedApi.id,settings.id).then(function () {
                                console.log("Api updated");
                            });
                        });
                    }
                }).then(function (){
                    console.log("validate");
                    $scope.loading.inc();
                    APIEvalService.validateAPI(fileid).then(function (data) {
                        console.log(data);
                        $scope.loading.dec();
                        loadData();
                        $scope.selectedApi.expanded = true;
                        $scope.selectedVersion.expanded = true;
                        $scope.selectedFile.expanded = true;
                     }).catch(function (error) {
                        $scope.loading.dec();
                        console.log("Error validating  api");
                        console.error(error);
                    });
                });
            };

            $scope.showViolationReport = function() {
                var dialog = ngDialog.open({
                    template: 'app/modules/reports/violation.tpl.html',
                    showClose: true,
                    className:"ngdialog-theme-default",
                    scope:$scope
                });

                dialog.closePromise.then(function () {

                });
            };

            $scope.showComparisonReport = function() {
                var dialog = ngDialog.open({
                    template: 'app/modules/reports/comparison.tpl.html',
                    showClose: true,
                    className:"ngdialog-theme-default",
                    scope:$scope
                });

                dialog.closePromise.then(function () {

                });
            };

            $scope.showSwagger = function(){
                var dialog = ngDialog.open({
                    template: 'app/modules/swaggerui/swaggerinfo.tpl.html',
                    showClose: true,
                    className:"ngdialog-theme-default",
                    scope:$scope
                });
            };

            $scope.select = function(api, version, revision, violationreport, comparisonreport){
                $scope.selectedComparisonReport = undefined;
                $scope.selectedViolationReport = undefined;
                $scope.selectedVersion = undefined;
                $scope.selectedFile = undefined;

                if(api) {
                    $scope.selectedApi = api;
                }
                if(version){
                    $scope.selectedVersion = version;
                }
                if(revision) {
                    $scope.selectedFile = revision;
                }
                setcurrentFileUrl();
                console.log(violationreport);
                if(violationreport) $scope.selectedViolationReport = violationreport;
                if(comparisonreport) $scope.selectedComparisonReport = comparisonreport;
            };

            function setcurrentFileUrl(){
                if ($scope.selectedFile) {
                    $scope.apifileurl = $scope.selectedFile.apifileurl;
                } else {
                    if($scope.selectedApi){
                        var maxRev = getMaxRevisionFor($scope.selectedApi);
                        if(maxRev) {
                            $scope.apifileurl = maxRev.apifileurl;
                        }
                    } else {
                        $scope.apifileurl = undefined;
                    }
                }
                console.log("current apifileurl:" + $scope.apifileurl);
            }

            $scope.trustSrc = function(src) {
                var trust = $sce.trustAsResourceUrl(src);
                console.log("trust:" + trust);
                return trust;
            };

            function getMaxRevisionFor(api) {
                {
                    var maxrev;
                    //console.log("check max revision for api:" + api.name);
                    angular.forEach(api.versions, function (vers) {
                        //console.log("look in vers:" + vers.number);
                        angular.forEach(vers.revisions, function (rev) {
                            if(maxrev) {
                                //console.log("check maxrev:" + maxrev.timestamp + "< rev.timstamp" + maxrev.timestamp);
                                if(maxrev.timestamp < rev.timestamp) {
                                    maxrev = rev;
                                }
                            } else {
                                //console.log("maxrev is null, set:" + rev.file);
                                maxrev = rev;
                            }
                        });
                    });
                    return maxrev;
                }
            }
            $scope.uploadNewApi = function (files){
                var file = files[0];
                if(file != undefined) {
                    var reader = new FileReader();
                    $scope.loading.inc();

                    // Closure to capture the file information.
                    reader.onload = (function (file) {
                        return function (e) {
                            //console.log('e readAsText target = ', e.target);
                            APIEvalService.postNewAPIs(e.target.result).then(function (resp) {
                                $scope.loading.dec();
                                loadData();
                            }).catch(function (error) {
                                console.log("Error uploading new api");
                                console.error(error);
                                $scope.loading.dec();

                            });
                        };
                    })(file);
                    reader.readAsText(file);

                }
            };

            $scope.uploadToExistingApi = function (files, api) {
                var file = files[0];
                if(file != undefined) {
                    var reader = new FileReader();

                    // Closure to capture the file information.
                    reader.onload = (function (file) {
                        return function (e) {
                            //console.log('e readAsText target = ', e.target);
                            APIEvalService.postNewAPIs(e.target.result).then(function (resp) {
                                console.log("new api version response: " + resp);
                                loadData();
                            }).catch(function (error) {
                                console.log("Error uploading api to existing api");
                                console.error(error);
                            });
                        };
                    })(file);
                    reader.readAsText(file);
                }
            };
        }]);
})(angular);