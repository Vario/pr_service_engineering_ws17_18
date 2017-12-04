/**
 * Created by wrichtsfeld on 30/11/2017.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.api')
    .factory('$host', [
        '$window',

        function ($window) {
            function Host() {
            }

            function contains(text, search) {
                return text.indexOf(search) > -1;
            }

            Host.apiHost = function () {
                if (!!this.host) {
                    return this.host;
                }
                return "https://api.apieval.com"; //backend api url
            };

            Host.setApiHost = function (host) {
                this.host = host;
            };

            Host.apiURL = function (path) {
                if (path.indexOf("http") === 0) {
                    return path;
                }
                return this.apiHost() + makeUrlPathAbsolute(path);
            };

            function makeUrlPathAbsolute(path) {
                var hasPrefix = (path.indexOf("/") === 0);
                return (hasPrefix ? "" : "/") + path;
            }

            return Host;
        }
    ]);
})(angular);