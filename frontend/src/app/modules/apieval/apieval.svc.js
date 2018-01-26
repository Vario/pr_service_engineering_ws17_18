/**
 * Created by wrichtsfeld on 30/11/2017.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.services')
        .service('APIEvalService', [
            'BackendAPIService',
            'APIModel',
            '$filter',
            '$q',
            function (BackendAPIService, APIModel, $filter, $q) {
                var self = this;
                self.apis = undefined;

                self.getAllAPIs = function() {
                    return BackendAPIService.getAPIs().then(function (data) {
                        if (data.length <= 0) {
                            //return $q.reject();
                        }
                        var newApis = self.getApiModelsMapped(data);
                        self.apis = newApis;
                        return newApis;
                    });
                };

                self.updateApiTitle = function(api, title) {
                    var model =
                        {
                            "title": title
                        };
                    return BackendAPIService.updateAPITitle(api.id, angular.toJson(model)).then(function (data) {
                        return;
                    });
                };

                self.updateApiSetting = function(apiid, settingsid) {
                    var model =
                        {
                            "id": settingsid
                        };
                    console.log("update api "+ apiid + " with settingsid:" + settingsid + " and model:" + angular.toJson(model));

                    return BackendAPIService.updateAPISetting(apiid, angular.toJson(model)).then(function (data) {
                        console.log("api updated");
                        return;
                    });
                };

                self.getApiModelsMapped = function(data) {
                    if(data != undefined) {

                        if (!angular.isArray(data)) {
                            data = [data];
                        }
                        var apis = [];

                        angular.forEach(data, function (value) {
                            var api = new APIModel(value);
                            apis.push(api);
                        });

                        return apis;
                    } else {
                        return [];
                    }
                };

                self.postAPI = function(api, toAPI) {
                    var newApi = JSON.parse(api);
                    var newAPIName =  newApi.info.title;
                    //check if api exists if it should not be linked to an api
                    if(toAPI == undefined) {
                        //if exists and it should be a new api, add timestamp
                        if(self.apiExists(newAPIName)) {
                            newAPIName = newAPIName + "-" + self.apis.length;
                        }
                    } else {
                        newAPIName = toAPI.name;
                    }
                    var model =
                        {
                            "title": newAPIName,
                            "version": newApi.info.version,
                            "swagger": newApi,
                            "settings-id": ""
                        };
                    return BackendAPIService.postAPIfile(model).then(function () {
                        console.log('api sent -> reload');
                    });
                };

                self.apiExists = function(toCheckApiName) {
                    var exists = false;
                    angular.forEach(self.apis, function (api) {
                        if(angular.equals(api.name, toCheckApiName)) {
                            exists = true;
                        }
                    });
                    return exists;
                };
                self.validateAPI = function(fileid) {
                    var validationObject =
                        {
                            "file-id": fileid
                        };
                    return BackendAPIService.validteAPIreport(validationObject).then(function () {
                        console.log('api validation sent -> reload');
                    });
                };

                self.compareAPIS = function(fileids) {
                    var comparisonObject =
                        {
                            "file-ids": fileids
                        };
                    console.log(comparisonObject);
                    return BackendAPIService.compareAPIReport(comparisonObject).then(function () {
                        console.log('api compare sent');
                    });
                };
            }

        ]);
})(angular);