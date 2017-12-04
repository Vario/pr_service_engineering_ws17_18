/**
 * Created by wrichtsfeld on 30/11/2017.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.services')
        .factory('APIModel', [
            function () {
                var self = this;

                self.versions = undefined;
                self.active = undefined;
                self.name = undefined;

                function APIModel(data) {
                    var self = this;
                    angular.merge(self, data);
                }

                APIModel.prototype.getValidPropertyList = function () {
                    return ['version','name','active'];
                };

                return APIModel;
            }]);
})(angular);