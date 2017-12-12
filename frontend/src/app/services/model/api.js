/**
 * Created by wrichtsfeld on 30/11/2017.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.services')
        .factory('APIModel', [
            'APIVersionModel',
            function (APIVersionModel) {
                var self = this;
                self.id = undefined;
                self.versions = undefined;
                self.expanded = undefined;
                self.name = undefined;
                self.description = undefined;

                function APIModel(data) {
                    var self = this;
                    //angular.merge(self, data);
                    console.log(data);
                    self.name = data['api-id'];
                    self.versions = [];
                    self.expanded = false;
                    self.description = "";
                    angular.forEach(data.versions, function (value) {
                        var version = new APIVersionModel(value);
                        self.versions.push(version);
                    });
                }

                APIModel.prototype.getValidPropertyList = function () {
                    return ['id','version','name','expanded', 'description'];
                };

                return APIModel;
            }]);
})(angular);