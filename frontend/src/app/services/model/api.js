/**
 * Created by wrichtsfeld on 30/11/2017.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.services')
        .factory('APIModel', [
            function () {
                var self = this;
                self.id = undefined;
                self.versions = undefined;
                self.expanded = undefined;
                self.name = undefined;
                self.description = undefined;
                function APIModel(data) {
                    var self = this;
                    angular.merge(self, data);
                }

                APIModel.prototype.getValidPropertyList = function () {
                    return ['id','version','name','expanded', 'description'];
                };

                return APIModel;
            }]);
})(angular);