/**
 * Created by wrichtsfeld on 12.01.18.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.services')
        .factory('UpdateAPISettingsModelResponse', [
            function () {
                var self = this;
                self.id = undefined;

                function UpdateAPISettingsModelResponse(data) {
                    var self = this;
                    self.id = data.id;
                }

                UpdateAPISettingsModelResponse.prototype.getValidPropertyList = function () {
                    return ['id'];
                };

                return UpdateAPISettingsModelResponse;
            }]);
})(angular);