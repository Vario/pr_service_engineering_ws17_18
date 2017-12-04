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

                self.name = undefined;
                self.reports = undefined;

                function APIFileModel(data) {
                    var self = this;
                    angular.merge(self, data);
                }

                APIFileModel.prototype.getValidPropertyList = function () {
                    return ['name','reports'];
                };

                return APIFileModel;
            }]);
})(angular);