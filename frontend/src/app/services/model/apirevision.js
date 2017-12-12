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
            'APIReportModel',
            '$filter',
            function (APIReportModel, $filter) {
                var self = this;

                self.id = undefined;
                self.file = undefined;
                self.reports = undefined;
                self.expanded = undefined;
                self.timestamp = undefined;

                function APIRevisionModel(data) {
                    var self = this;
                   // angular.merge(self, data);
                    self.file = data.file;
                    self.id = data.id;
                    self.expanded = false;
                    self.reports = [];

                    self.timestamp = $filter('date')(self.id,"dd/MM/yyyy h:mm:ss a");
                    angular.forEach(data.reports, function (value) {
                        var rep = new APIReportModel(value);
                        self.reports.push(rep);
                    });
                }

                APIRevisionModel.prototype.getValidPropertyList = function () {
                    return ['id','file','reports'];
                };

                return APIRevisionModel;
            }]);
})(angular);