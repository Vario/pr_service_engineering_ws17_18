/**
 * Created by wrichtsfeld on 30/11/2017.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.api')
        .service('BackendAPIService', [
            '$http',
            '$q',
            '$host',
            'APIEVAL_API_VERSION',
            function ($http, $q, $host, APIEVAL_API_VERSION) {
                var self = this;
                var apiVersion = APIEVAL_API_VERSION;
                //##########################################################
                //##########################################################
                // Base backend calls
                //##########################################################
                //##########################################################
                function addApiVersionToPathIfNotExisting(path) {
                    if (!(/^\/?[1-9]\//i).test(path)) {
                        // API version not provided -> use the configured one
                        if (path.indexOf('/') === 0) {
                            path = '/' + apiVersion + path;
                        } else {
                            path = '/' + apiVersion + '/' + path;
                        }
                    }
                    return path;
                }

                function backendCall(backend, method, path, data, additionalHeaders) {
                    path = addApiVersionToPathIfNotExisting(path);

                    var requestHeaders = {
                        'Content-Type': 'application/json'
                    };
                    return $q(function (resolve, reject) {

                        angular.forEach(additionalHeaders, function (headerValue, headerName) {
                            requestHeaders[headerName] = headerValue;
                        });
                        $http({
                            url: $host.apiURL(path),
                            method: method,
                            headers: requestHeaders,
                            data: data
                        }).then(function (resp) {
                            if (!!resp && resp.status !== 200) {
                                reject(resp.data);
                            } else {
                                resolve(resp.data);
                            }
                        }, function (resp) {
                            reject(resp.data);
                        });
                    }, console.error);
                }


                // all calls without authorization check
                self.post = function (path, data, headers) {
                    return backendCall(self, 'POST', path, data, headers);
                };

                self.put = function (path, data, headers) {
                    return backendCall(self, 'PUT', path, data, headers);
                };

                self.get = function (path, headers) {
                    return backendCall(self, 'GET', path, null, headers);
                };

                self.delete = function (path, headers) {
                    return backendCall(self, 'DELETE', path, undefined, headers);
                };

                //##########################################################
                //##########################################################
                //List of Backend calls
                //##########################################################
                //##########################################################

                /*
                self.getAPI = function (id) {
                    return self.get(sprintf('/apis/%s/', id));
                };

                self.updateAPI = function (id, version, data) {
                    return self.put(sprintf('/api/%s?version=%s', id, version), data);
                };

                self.postExample = function (arg) {
                    return self.post('/api', {param: arg});
                };

                self.deleteAPI = function(id, version) {
                    return self.delete(sprintf('/api/%s?version=%s', id, version));
                };
                */
                self.getAPIs = function () {
                    return self.get('/apis');
                };

                self.updateAPITitle = function (apiid, title) {
                    return self.put('/apis/' + apiid + "/title",title);
                };

                self.updateAPISetting = function (apiid, setting) {
                    return self.put('/apis/' + apiid + "/settings",setting);
                };



                self.postAPIfile = function (file) {
                    return self.post('/files',file);
                };

                self.validteAPIreport = function (validation) {
                    return self.post('/reports/violation',validation);
                };

                self.compareAPIReport = function (apiids) {
                    return self.post('/reports/comparison',apiids);
                };

                self.getRules = function() {
                    return self.get('/rules');
                };

                self.getSettings = function() {
                    return self.get('/settings');
                };

                self.getSetting = function(id) {
                    return self.get('/settings/' + id);
                };

                self.postSetting = function (settings) {
                    console.log("create settings: " + settings);
                    return self.post('/settings',settings);
                };

                self.putSetting = function (id, settings) {
                    console.log("update settings: " + settings);
                    return self.put('/settings/' + id,settings);
                };

            }]);
})(angular);
