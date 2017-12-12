/**
 * Created by wrichtsfeld on 30/11/2017.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.services')
        .service('APIEvalService', [
            'BackendAPIService',
            '$filter',
            '$q',
            function (BackendAPIService, $filter, $q) {
                var self = this;

                self.getAllAPIs = function() {

                    return BackendAPIService.getAPIs().then(function (data) {
                        console.log('returned apis' + data);
                        if (data.length <= 0) {
                            return $q.reject();
                        }
                        return data;
                    });
                };

                self.postNewAPIs = function(api) {
                    return BackendAPIService.postAPIfile(api).then(function () {
                        console.log('api sent -> reload');
                    });
                };
            }

        ]);
})(angular);