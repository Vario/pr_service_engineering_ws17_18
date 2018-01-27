/**
 * Created by wrichtsfeld on 11.01.18.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.services')
        .factory('APISettingsModel', [
            'APIRuleModel',
            function (APIRuleModel) {
                var self = this;

                self.id = undefined;
                self.name = undefined;
                self.rules = undefined;

                function APISettingsModel(data) {
                    var self = this;
                    angular.merge(self, data);
                }

                APISettingsModel.prototype.getValidPropertyList = function () {
                    return ['id','name','rules'];
                };

                return APISettingsModel;
            }]);
})(angular);