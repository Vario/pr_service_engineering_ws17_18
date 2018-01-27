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
                self.settingsid = undefined;
                self.edit = undefined;

                function APIModel(data) {
                    var self = this;
                    //angular.merge(self, data);
                    self.id = data['api-id'];
                    self.name = data.title;
                    self.settingsid = data['settings-id'];
                    self.versions = [];
                    self.expanded = false;
                    self.description = "";
                    //self.edit = false;
                    angular.forEach(data.versions, function (value) {
                        var version = new APIVersionModel(value);
                        self.versions.push(version);
                    });
                }

                APIModel.prototype.getValidPropertyList = function () {
                    return ['id','version','name','expanded', 'description','settingsid'];
                };

                return APIModel;
            }]);
})(angular);