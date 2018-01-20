/**
 * Created by wrichtsfeld on 20.01.18.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.services')
        .factory('APIComparisonChangeModel', [
            function () {
                var self = this;

                self.endpoint = undefined;
                self.description = undefined;
                self.parameter = undefined;
                self.returntype = undefined;

                function APIComparisonChangeModel(data) {
                    var self = this;
                    angular.merge(self, data);
                }

                APIComparisonChangeModel.prototype.getValidPropertyList = function () {
                    return ['endpoint','description','parameter','returntype'];
                };

                return APIComparisonModel;
            }]);
})(angular);