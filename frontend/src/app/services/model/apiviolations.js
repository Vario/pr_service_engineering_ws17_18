/**
 * Created by wrichtsfeld on 08/01/2018.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.services')
        .factory('APIViolationsModel', [
            function () {
                var self = this;

                self.message = undefined;
                self.violations = undefined;
                self.violations_count = undefined;

                function APIViolationsModel(data) {
                    var self = this;
                    angular.merge(self, data);
                    /*self.messge = data.message;
                    self.violations_count = new APIViolationsCountModel(value);
                    angular.forEach(data.violations, function (value) {
                        var violation = new APIViolationModel(value);
                        self.violations.push(violation);
                    });*/
                }

                APIViolationsModel.prototype.getValidPropertyList = function () {
                    return ['message','violations','violations_count'];
                };

                return APIViolationsModel;
            }]);
})(angular);