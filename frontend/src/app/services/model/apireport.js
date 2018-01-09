/**
 * Created by wrichtsfeld on 04/12/2017.
 */
/**
 * Created by wrichtsfeld on 30/11/2017.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.services')
        .factory('APIReportModel', [
            function () {
                var self = this;

                self.type = undefined;
                self.violations = undefined;

                function APIReportModel(data) {
                    var self = this;
                    angular.merge(self, data);
                    /*self.type = data.type;
                    self.violations = new APIViolationsModel(data.violations);*/
                }

                APIReportModel.prototype.getValidPropertyList = function () {
                    return ['type','violations'];
                };

                return APIReportModel;
            }]);
})(angular);