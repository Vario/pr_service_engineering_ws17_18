/**
 * Created by wrichtsfeld on 20.01.18.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.services')
        .factory('APIComparisonModel', [
            function () {
                var self = this;

                self.fileids = undefined;
                self.paths = undefined;

                function APIComparisonModel(data) {
                    var self = this;
                    self.fileids = data["file-ids"];
                    self.paths = new APIComparisonPathsModel(data.paths);
                }

                APIComparisonModel.prototype.getValidPropertyList = function () {
                    return ['fileids','paths'];
                };

                return APIComparisonModel;
            }]);
})(angular);