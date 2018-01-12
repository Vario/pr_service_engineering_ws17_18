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
                    self.id = data.id;
                    self.name = data.name;
                    self.rules = data.rules;
                    /*angular.forEach(data.rules, function (value) {
                        var rule = new APIRuleModel(value);
                        console.log("settingsrule:" + rule.code);
                        self.rules.push(rule);
                    });*/
                }

                APISettingsModel.prototype.getValidPropertyList = function () {
                    return ['id','name','rules'];
                };

                return APISettingsModel;
            }]);
})(angular);