/**
 * Created by wrichtsfeld on 04/12/2017.
 */
/**
 * Created by wrichtsfeld on 30/11/2017.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.services')
        .factory('APIFileModel', [
            function () {
                var self = this;

                self.id = undefined;
                self.file = undefined;
                self.reports = undefined;
                self.expanded = undefined;

                function APIFileModel(data) {
                    var self = this;
                    angular.merge(self, data);
                }

                APIFileModel.prototype.getValidPropertyList = function () {
                    return ['id','file','reports'];
                };

                return APIFileModel;
            }]);
})(angular);