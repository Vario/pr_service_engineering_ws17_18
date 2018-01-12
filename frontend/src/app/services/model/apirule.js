/**
 * Created by wrichtsfeld on 11.01.18.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.services')
        .factory('APIRuleModel', [
            function () {
                var self = this;

                self.code = undefined;
                self.title = undefined;
                self.type = undefined;
                self.url = undefined;
                self.checked = undefined;

                function APIRuleModel(data) {
                    var self = this;
                    angular.merge(self, data);
                    self.checked = false;
                }

                APIRuleModel.prototype.getValidPropertyList = function () {
                    return ['code','title','type','url','checked'];
                };

                return APIRuleModel;
            }]);
})(angular);