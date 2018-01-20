/**
 * Created by wrichtsfeld on 04/12/2017.
 */
/**
 * Created by wrichtsfeld on 30/11/2017.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.services')
        .factory('APIViolationReportModel', [
            function () {
                var self = this;

                self.violations = undefined;
                self.violations_count = undefined;
                self.message = undefined
                function APIViolationReportModel(data) {
                    var self = this;
                    console.log(data);
                    angular.merge(self, data);
                  // self.violations = new APIViolationsModel(data.violations);*/
                }

                APIViolationReportModel.prototype.getValidPropertyList = function () {
                    return ['violations_count','violations','message'];
                };

                return APIViolationReportModel;
            }]);
})(angular);