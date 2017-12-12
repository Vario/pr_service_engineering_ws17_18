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
                        console.log('returned apis' + data);
                        if (data.length <= 0) {
                            return $q.reject();
                        }
                        var apis = self.getApiModelsMapped(data);
                        return apis;
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
                    return BackendAPIService.postAPIfile(api).then(function () {
                        console.log('api sent -> reload');
                    });
                };
            }

        ]);
})(angular);