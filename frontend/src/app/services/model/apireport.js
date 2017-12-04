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

                self.name = undefined;

                function APIReportModel(data) {
                    var self = this;
                    angular.merge(self, data);
                }

                APIReportModel.prototype.getValidPropertyList = function () {
                    return ['name'];
                };

                return APIReportModel;
            }]);
})(angular);