/**
 * Created by wrichtsfeld on 20.01.18.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.services')
        .factory('APIComparisonReportModel', [
            function () {
                var self = this;

                self.type = undefined;
                self.violations = undefined;

                function APIComparisonReportModel(data) {
                    var self = this;
                    angular.merge(self, data);
                    /*self.type = data.type;
                     self.violations = new APIViolationsModel(data.violations);*/
                }

                APIComparisonReportModel.prototype.getValidPropertyList = function () {
                    return ['type','violations'];
                };

                return APIComparisonReportModel;
            }]);
})(angular);