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
                var tokenFactory = $q.when(null);

                self.setTokenFactory = function (tokenFactoryFunction) {
                    tokenFactory = tokenFactoryFunction;
                };
                self.getTokenFactory = function () {
                    return tokenFactory;
                };

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
                        //Necessary request headers?
                    };
                    return $q(function (resolve, reject) {
                        tokenFactory()
                            .then(function (idToken) {
                                requestHeaders['ID-Token'] = idToken;
                                _.forEach(additionalHeaders, function (headerValue, headerName) {
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
                            });
                    }, console.error);
                }

                // all calls without authorization check
                self.postWithoutAuth = function (path, data, headers) {
                    return backendCall(self, 'POST', path, data, headers);
                };

                self.put = function (path, data, headers) {
                    return backendCall(self, 'PUT', path, data, headers);
                };

                self.getWithoutAuth = function (path, headers) {
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

                self.getAPI = function (id) {
                    return self.get(sprintf('/api/%s/', id));
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
            }]);
})(angular);
