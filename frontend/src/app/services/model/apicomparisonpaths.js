/**
 * Created by wrichtsfeld on 20.01.18.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.services')
        .factory('APIComparisonPathsModel', [
            function () {
                var self = this;

                self.new = undefined;
                self.removed = undefined;
                self.changed = undefined;

                function APIComparisonPathsModel(data) {
                    var self = this;
                    self.new = new APIComparisonChangeModel(data.new);
                    self.removed = new APIComparisonChangeModel(data.removed);
                    self.changed = new APIComparisonChangeModel(data.changed);
                }

                APIComparisonPathsModel.prototype.getValidPropertyList = function () {
                    return ['new','removed','changed'];
                };

                return APIComparisonPathsModel;
            }]);
})(angular);