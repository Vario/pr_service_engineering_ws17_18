/**
 * Created by wrichtsfeld on 04/12/2017.
 */
/**
 * Created by wrichtsfeld on 30/11/2017.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.services')
        .factory('APIRevisionModel', [
            '$filter',
            'APIViolationReportModel',
            'APIComparisonReportModel',
            function ($filter,APIViolationReportModel,APIComparisonReportModel) {
                var self = this;

                self.id = undefined;
                self.file = undefined;
                self.violationreports = undefined;
                self.comparisonreport = undefined;
                self.expanded = undefined;
                self.timestamp = undefined;
                self.checked = undefined;
                self.apifileurl = undefined;

                function APIRevisionModel(data) {
                    var self = this;
                   self.file = data.file;
                    self.id = data.id;
                    self.expanded = false;
                    self.checked = false;
                    self.violationreport = data.reports.violation; //APIViolationReportModel(data.reports.violation);
                    self.comparisonreport = data.reports.comparison; //APIComparisonReportModel(data.reports.comparison);
                    self.apifileurl = data.url;
                    self.timestamp = $filter('date')(self.id,"dd/MM/yyyy h:mm:ss a");
                }

                APIRevisionModel.prototype.getValidPropertyList = function () {
                    return ['id','file','reports', 'checked','apifileurl','violationreports','comparisonreports'];
                };

                return APIRevisionModel;
            }]);
})(angular);