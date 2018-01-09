/**
 * Created by wrichtsfeld on 08/01/2018.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.services')
        .factory('APIViolationModel', [
            function () {
                var self = this;

                self.description = undefined;
                self.paths = undefined;
                self.rule_link = undefined;
                self.title = undefined;
                self.violation_type = undefined;

                function APIViolationModel(data) {
                    var self = this;
                    self.description = data.description;
                    self.paths = data.paths;
                    self.rule_link = data.rule_link;
                    self.title = data.title;
                    self.violation_type = data.violation_type;
                }

                APIViolationModel.prototype.getValidPropertyList = function () {
                    return ['description','paths','rule_link','title','violation_type'];
                };

                return APIViolationModel;
            }]);
})(angular);