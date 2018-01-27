/**
 * Created by wrichtsfeld on 30/11/2017.
 * Service which handles all api related methods
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

                //get all apis from the backend
                self.getAllAPIs = function() {
                    return BackendAPIService.getAPIs().then(function (data) {
                        if (data.length <= 0) {
                            //return $q.reject();
                        }
                        //map api models to own structure
                        var newApis = self.getApiModelsMapped(data);
                        self.apis = newApis;
                        return newApis;
                    });
                };

                //update the title for an api
                self.updateApiTitle = function(api, title) {
                    var model =
                        {
                            "title": title
                        };
                    return BackendAPIService.updateAPITitle(api.id, angular.toJson(model)).then(function (data) {
                        return;
                    });
                };

                //Update settings id for an api
                self.updateApiSetting = function(apiid, settingsid) {
                    var model =
                        {
                            "id": settingsid
                        };

                    return BackendAPIService.updateAPISetting(apiid, angular.toJson(model)).then(function (data) {
                        return;
                    });
                };

                //Map api models to FE models
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

                //post a new api file
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
                    return BackendAPIService.postAPIfile(model);
                };

                //checks if an api exists
                self.apiExists = function(toCheckApiName) {
                    var exists = false;
                    angular.forEach(self.apis, function (api) {
                        if(angular.equals(api.name, toCheckApiName)) {
                            exists = true;
                        }
                    });
                    return exists;
                };
                //validate a api revision file
                self.validateAPI = function(fileid) {
                    var validationObject =
                        {
                            "file-id": fileid
                        };
                    return BackendAPIService.validteAPIreport(validationObject);
                };

                //compare apis
                self.compareAPIS = function(fileids) {
                    var comparisonObject =
                        {
                            "file-ids": fileids
                        };
                    return BackendAPIService.compareAPIReport(comparisonObject);
                };
            }

        ]);
})(angular);