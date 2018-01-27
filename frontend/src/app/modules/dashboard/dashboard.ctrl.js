/**
 * Created by wrichtsfeld on 04/12/2017.
 * Controller which handles all dashboard related methods
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

            //define needed variables
            $scope.selectedApi = undefined; //currently selected api
            $scope.selectedVersion = undefined; //currently selected api version
            $scope.selectedFile = undefined; //currently selected api revision
            $scope.selectedViolationReport = undefined; //selected violation report for revision
            $scope.selectedComparisonReport = undefined; // selected comparison report in revision
            $scope.apis = undefined; //all apis
            $scope.apifileurl = undefined; //url to currently selected api revision
            $scope.showSwaggerUI = undefined; //flag to identifiy if swagger UI should be shown
            $scope.selectedComparison = []; //array which

            //change comparison order for apis
            $scope.switchComparison = function(data){
                var o = $scope.selectedComparison[0];
                $scope.selectedComparison[0] = $scope.selectedComparison[1];
                $scope.selectedComparison[1] = o;
                data[0] = $scope.selectedComparison[0];
                data[1] = $scope.selectedComparison[1];
            };

            //method to handle selection of apis which should be compared
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
                //if revision is checked and should be unchecked, find it and remove it from selection
                else if(r.checked) {
                    for(var g = $scope.selectedComparison.length -1; g >= 0; g--){
                        if($scope.selectedComparison[g].revision.id == r.id){
                            $scope.selectedComparison.splice(g,1);
                            r.checked = false;
                        }
                    }
                }
            };

            //load data from backend
            loadData();

            //Method to load data from backend
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

            //method which loads data from backend and preserves treeview state (checked, ....)
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

            //validate an api revision
            $scope.validate = function(e){
                $scope.loading.inc();
                //get available rules form backend
                APIEvalSettingsService.getRules().then(function (rules) {
                    $scope.loading.dec();
                    if($scope.selectedApi.settingsid) {
                        //get api settings for api
                        APIEvalSettingsService.getSetting($scope.selectedApi.settingsid).then(function (settings) {
                            if(settings) {
                                //if there are settings, match them with the rules list
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
                                //if no settings, activate all rules
                                angular.forEach(rules, function (rule) {
                                    rule.checked = true;
                                });
                                $scope.showValidationDialog(e.file,rules);
                            }
                        });
                    } else {
                        //if no settings, activate all rules
                        angular.forEach(rules, function (rule) {
                            rule.checked = true;
                        });
                        $scope.showValidationDialog(e.file,rules);
                    }
                });
            };

            //store new name for api
            $scope.saveNewAPIName = function(api) {
                APIEvalService.updateApiTitle(api,api.name).then(function () {
                    api.edit = false;
                });
            };

            //show dialog to compare apis
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

                //continue when dialog is closed
                dialog.closePromise.then(function (data) {
                    $scope.loading.inc();
                    var apis = [data.value[0].revision.file, data.value[1].revision.file];
                    APIEvalService.compareAPIS(apis).then(function (data) {
                        $scope.loading.dec();
                        reloadData();
                        $scope.selectedApi.expanded = true;
                        $scope.selectedVersion.expanded = true;
                        $scope.selectedFile.expanded = true;
                    }).catch(function (error) {
                        $scope.loading.dec();
                        console.error(error);
                    });
                });
            };

            //show dialog for api validation
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

                //Continue when dialog is closed
                dialog.closePromise.then(function (data) {
                    if($scope.selectedApi.settingsid) {
                        //Update settings
                        APIEvalSettingsService.updateSetting($scope.selectedApi, data.value).catch(function (error) {
                            console.error(error);
                        });
                    } else {
                        //Create Settings for api
                        APIEvalSettingsService.createSetting($scope.selectedApi.name, data.value).then(function (settings) {
                            //settings created, update api
                            APIEvalService.updateApiSetting($scope.selectedApi.id,settings.id).catch(function (error) {
                                console.error(error);
                            });
                        }).catch(function (error) {
                            console.error(error);
                        });
                    }
                }).then(function (){
                    $scope.loading.inc();
                    APIEvalService.validateAPI(fileid).then(function (data) {
                        $scope.loading.dec();
                        reloadData();
                        $scope.selectedApi.expanded = true;
                        $scope.selectedVersion.expanded = true;
                        if($scope.selectedFile) {
                            $scope.selectedFile.expanded = true;
                        }
                     }).catch(function (error) {
                        $scope.loading.dec();
                        console.error(error);
                    });
                });
            };

            //show existing violation report
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

            //show existing comparison report
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
            }

            //toggle switch to show/hide swaggerUI
            $scope.toggleShowSwaggerUI = function() {
                $scope.showSwaggerUI = !$scope.showSwaggerUI;
            };

            /*
            Helper Method to get the max revision for an api
             */
             function getMaxRevisionFor(api) {
                {
                    var maxrev;
                    angular.forEach(api.versions, function (vers) {
                        angular.forEach(vers.revisions, function (rev) {
                            if(maxrev) {
                                if(maxrev.timestamp < rev.timestamp) {
                                    maxrev = rev;
                                }
                            } else {
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
                            APIEvalService.postAPI(e.target.result).then(function (resp) {
                                $scope.loading.dec();
                                reloadData();
                            }).catch(function (error) {
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
                            APIEvalService.postAPI(e.target.result, api).then(function (resp) {
                                reloadData();
                            }).catch(function (error) {
                                console.error(error);
                            });
                        };
                    })(file);
                    reader.readAsText(file);
                }
            };
        }]);
})(angular);