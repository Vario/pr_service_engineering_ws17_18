/**
 * Created by wrichtsfeld on 04/12/2017.
 */
/**
 * Created by wrichtsfeld on 30/11/2017.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.services')
        .factory('APIVersionModel', [
            function () {
                var self = this;

                self.number = undefined;
                self.expanded = undefined;
                self.revisions = undefined;

                function APIVersionModel(data) {
                    var self = this;
                    angular.merge(self, data);
                }

                APIVersionModel.prototype.getValidPropertyList = function () {
                    return ['number','expanded','revisions'];
                };

                return APIVersionModel;
            }]);
})(angular);