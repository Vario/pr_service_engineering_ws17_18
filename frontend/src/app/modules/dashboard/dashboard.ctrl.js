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
                //if selected components are lower than 2 and passed revision is not checked
                //check revision and add object to array
                if($scope.selectedComparison.length < 2 && !r.checked){
                    var o = {
                        api : a,
                        version : v,
                        revision : r
                    };
                    r.checked = true;
                    $scope.selectedComparison.push(o);
                }
                //if revision is checked, find it and remove it from selection
                else if(r.checked) {
                    for(var g = $scope.selectedComparison.length -1; g >= 0; g--){
                        console.log($scope.selectedComparison[g].revision.id + "-" + r.id);
                        if($scope.selectedComparison[g].revision.id == r.id){
                            $scope.selectedComparison.splice(g,1);
                            r.checked = false;
                        }
                    }
                }
            };

            loadData();

/*
            function loadData() {
                $scope.loading.inc();
                APIEvalService.getAllAPIs().then(function (apis) {
                    if($scope.apis != undefined) {
                        for (var i = 0; i < $scope.apis.length; i++) {
                            var api1 = $scope.apis[i];
                            if (api1.expanded) {
                                for (var j = 0; j < apis.length; j++) {
                                    var api2 = apis[j];
                                    if (api1.id == api2.id) {
                                        apis[j].expanded = true;
                                        var versions1 = api1.versions;
                                        var versions2 = api2.versions;
                                        for (var a = 0; a < versions1.length; a++) {
                                            var v1 = versions1[a];
                                            if (v1.expanded) {
                                                v1 = versions1[a].number;
                                                for (var b = 0; b < versions2.length; b++) {
                                                    var v2 = versions1[b].number;
                                                    if (v1 == v2) {
                                                        apis[j].versions[b].expanded = true;
                                                        var revisions1 = api1.versions[a].revisions;
                                                        var revisions2 = api2.versions[b].revisions;
                                                        for(var x = 0; x < revisions1.length; x++){
                                                            var r1 = revisions1[x];
                                                            if(r1.expanded){
                                                                r1 = revisions1[x].id;
                                                                for(var y = 0; y < revisions2.length; y++){
                                                                    var r2 = revisions2[y].id;
                                                                    if(r1 == r2){
                                                                        apis[j].versions[b].revisions[y].expanded = true;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    $scope.apis = apis;
                    console.log(apis);
                }).catch(function (error) {
                    console.error(error);
                }).finally(function () {
                    $scope.loading.dec();
                });
            }
            */

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

            function reloadData() {
                $scope.loading.inc();
                APIEvalService.getAllAPIs().then(function (apis) {
                    $scope.apis = mapToExistingModels(apis);
                }).catch(function (error) {
                    console.error(error);
                }).finally(function () {
                    $scope.loading.dec();
                });
            }
            /*
            Method to map backend return apis to existing models and apply existing GUI Properties as checked or expanded
             */
            function mapToExistingModels(newAPIS) {
                var newMappedAPIS = [];
                angular.forEach(newAPIS, function (newApi) {
                    var index = $scope.apis.map(function(e) { return e.id; }).indexOf(newApi.id);
                    if(index >= 0) {
                        //map existing gui properties to api
                        newApi.expanded = $scope.apis[index].expanded;
                        //Iterrate versions
                        angular.forEach(newApi.versions, function (newVersion) {
                            var indexV = $scope.apis[index].versions.map(function(e) { return e.id; }).indexOf(newVersion.id);
                           //if version existed, update properties
                            if(indexV >= 0) {
                                //map all gui properties to version
                                newVersion.expanded =  $scope.apis[index].versions[indexV].expanded;
                            }

                            //Iterrate Revisions
                            angular.forEach(newVersion.revisions, function (newRevision) {
                                var indexR = $scope.apis[index].versions[indexV].revisions.map(function(e) { return e.id; }).indexOf(newRevision.id);
                                //if revision existed, update properties
                                if(indexR >= 0) {
                                    //map all GUI properties to revision
                                    newRevision.expanded =  $scope.apis[index].versions[indexV].revisions[indexR].expanded;
                                    newRevision.checked =  $scope.apis[index].versions[indexV].revisions[indexR].checked;
                                }
                            });
                        });
                        //add api with all updated properties
                        newMappedAPIS.push(newApi);
                    } else {
                        //api is new add to array
                        newMappedAPIS.push(newApi);
                    }
                });
                return newMappedAPIS;
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
                        reloadData();
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
                        $scope.closeDialog = function(validate) {
                            if(validate) {
                                var checkedRules = [];
                                angular.forEach($scope.ngDialogData, function (rule) {
                                    if(rule.checked) {
                                        checkedRules.push(rule.code);
                                    }
                                });
                                dialog.close(checkedRules);
                            } else {
                                dialog.closeByEscape();
                            }
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
                        reloadData();
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

            $scope.comparedTo = function(comparison) {
                //console.log("ay");
                //console.log(comparison["file-ids"]);
                var obj = {};
                var id = comparison["file-ids"][1];
                for(var i = 0; i < $scope.apis.length; i++){
                    var api = $scope.apis[i];
                    var versions = api.versions;
                    for(var j = 0; j < versions.length; j++){
                        var version = versions[j];
                        var revisions = version.revisions;
                        for(var h = 0; h < revisions.length; h++){
                            var revision = revisions[h];
                            if(revision.file == id){
                                obj = {
                                    revision : revision.timestamp,
                                    version : version.number,
                                    api : api.name
                                };
                            }
                        }
                    }
                }
                return obj;
            };

            /*
            Method to handle a selection in the tree template for api, version, revision or report
             */
            $scope.select = function(api, version, revision, comparisonreport){
                $scope.selectedVersion = undefined;
                $scope.selectedFile = undefined;
                $scope.selectedComparisonReport = undefined;
                $scope.showSwaggerUI = false;

                if(api) {
                    $scope.selectedApi = api;
                }
                if(version){
                    $scope.selectedVersion = version;
                }
                if(revision) {
                    $scope.selectedFile = revision;
                    $scope.selectedViolationReport = revision.violationreport;
                }
                setcurrentFileUrl();

                if(comparisonreport) {
                    $scope.selectedComparisonReport = comparisonreport;
                }

                console.log("comparisonreport:" + $scope.selectedComparisonReport);
            };

            /*
            Method to set current File which should be shown in info view template
             */
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

            $scope.toggleShowSwaggerUI = function() {
                $scope.showSwaggerUI = !$scope.showSwaggerUI;
                console.log("showswaggerUI:"+ $scope.showSwaggerUI);
            };
            /*
            Helper Method to get the max revision for an api
             */
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

            /*
            Method which is called, when a new api should be uploaded
             */
            $scope.uploadNewApi = function (files){
                var file = files[0];
                if(file != undefined) {
                    var reader = new FileReader();
                    $scope.loading.inc();

                    // Closure to capture the file information.
                    reader.onload = (function (file) {
                        return function (e) {
                            //console.log('e readAsText target = ', e.target);
                            APIEvalService.postAPI(e.target.result).then(function (resp) {
                                $scope.loading.dec();
                                reloadData();
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

            /*
            Method which is called, to Upload an api file to an existing api
             */
            $scope.uploadToExistingApi = function (files, api) {
                var file = files[0];
                if(file != undefined) {
                    var reader = new FileReader();

                    // Closure to capture the file information.
                    reader.onload = (function (file) {
                        return function (e) {
                            //console.log('e readAsText target = ', e.target);
                            APIEvalService.postAPI(e.target.result, api).then(function (resp) {
                                console.log("new api version response: " + resp);
                                reloadData();
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