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

                self.getAllAPIs = function() {
                    return BackendAPIService.getAPIs().then(function (data) {
                        if (data.length <= 0) {
                            //return $q.reject();
                        }
                        var apis = self.getApiModelsMapped(data);
                        return apis;
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

                self.postNewAPIs = function(api) {
                    //remove all \n
                    var newapi = api.replace(/(\r\n|\n|\r)/gm,"");
                    var model =
                        {
                            "title": "",
                            "version": "",
                            "swagger": newapi,
                            "settings-id": ""
                        };

                    console.log(angular.toJson(model));
                    return BackendAPIService.postAPIfile(angular.toJson(model)).then(function () {
                        console.log('api sent -> reload');
                    });
                };

                self.validateAPI = function(fileids) {
                    var validationObject =
                        {
                            "type": "validation",
                            "file-ids": fileids
                        };
                    return BackendAPIService.postAPIreport(validationObject).then(function () {
                        console.log('api validation sent -> reload');
                    });
                };
            }

        ]);
})(angular);