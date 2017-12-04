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

                self.name = undefined;
                self.active = undefined;
                self.files = undefined;

                function APIVersionModel(data) {
                    var self = this;
                    angular.merge(self, data);
                }

                APIVersionModel.prototype.getValidPropertyList = function () {
                    return ['name','active','files'];
                };

                return APIVersionModel;
            }]);
})(angular);