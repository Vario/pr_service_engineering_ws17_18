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
            $scope.selectedReport = undefined;
            $scope.apis = undefined;
            $scope.apifileurl = undefined;
            $scope.apifile = undefined;
            $scope.showSwaggerUI = undefined;
            $scope.selectedComparison = [];

            $scope.selectComparison = function(r){
                if($scope.selectedComparison.length < 2 && !r.checked){
                    r.checked = true;
                    $scope.selectedComparison.push(r);
                }else{
                    for(var g = 0; g < $scope.selectedComparison.length; g++){
                        if($scope.selectedComparison[g] == r){
                            r.checked = false;
                            if($scope.selectedComparison.length == 1) $scope.selectedComparison = [];
                            $scope.selectedComparison.splice(g,g);
                        }
                    }
                }
                console.log($scope.selectedComparison);
            };

            /*$scope.canCompare = function(e) {
                var ct = 0;
                if($scope.selectedApi) {
                    for(var i = 0; i < $scope.selectedApi.versions.length; i++){
                        var version =  $scope.selectedApi.versions[i];
                        //console.log("Checked"+""+version.checked);
                        if(version.checked) {
                            ct+=1;
                            //console.log(version.checked)
                        }

                        for(var j = 0; j < version.revisions.length; j++){
                            var revision = version.revisions[j];
                            if(revision.checked) {
                                ct+= 1;
                            }
                        }
                    }
                }

                if(ct == 2) {
                    return true;
                }
                return false;
            };*/

            loadData();

            function loadData() {
                $scope.loading.inc();
                APIEvalService.getAllAPIs().then(function (apis) {
                    $scope.apis = apis;
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
                        $scope.showValidationDialog(rules);
                    }
                });
            };

            $scope.saveNewAPIName = function(api) {
                console.log("new api name: " + api.name);
                APIEvalService.updateApiTitle(api,api.name).then(function () {
                    api.edit = false;
                });
            };

            $scope.showValidationDialog = function(file, evalRules) {
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
                    APIEvalService.validateAPI([file]).then(function (data) {
                        console.log(data);
                        $scope.loading.dec();
                        loadData();
                        $scope.selectedApi.expanded = true;
                        $scope.selectedVersion.expanded = true;
                        $scope.selectedFile.expanded = true;
                     });
                });
            };

            $scope.showReport = function() {
                /*$scope.groupedViolations = $scope.selectedReport.violations.violations.reduce(function(arr, item){
                    arr[item['violation_type']] = arr[item['violation_type']] || { type: item['violation_type'], violations: []};
                    arr[item['violation_type']].violations.push(item);
                    return arr;
                }, []);*/
                var dialog = ngDialog.open({
                    template: 'app/modules/reports/reportdefault.tpl.html',
                    showClose: true,
                    className:"ngdialog-theme-default",
                    scope:$scope
                });

                dialog.closePromise.then(function () {

                });
            };

            $scope.select = function(api, version, revision, report){
                if(api) {
                    $scope.selectedApi = api;
                    $scope.selectedVersion = undefined;
                    $scope.selectedFile = undefined;
                    $scope.selectedReport = undefined;
                }
                if(version){
                    $scope.selectedVersion = version;
                    $scope.selectedFile = undefined;
                    $scope.selectedReport = undefined;
                }
                if(revision) {
                    $scope.selectedFile = revision;
                    $scope.selectedReport = undefined;
                }
                setcurrentFileUrl();
                if(report) $scope.selectedReport = report;
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

                    // Closure to capture the file information.
                    reader.onload = (function (file) {
                        return function (e) {
                            //console.log('e readAsText target = ', e.target);
                            APIEvalService.postNewAPIs(e.target.result).then(function (resp) {
                                loadData();
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
                                console.log("new api response: " + resp);
                                loadData();
                            });
                        };
                    })(file);
                    reader.readAsText(file);
                }
            };
        }]);
})(angular);