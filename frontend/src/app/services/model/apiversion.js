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
            'APIRevisionModel',
            function (APIRevisionModel) {
                var self = this;

                self.number = undefined;
                self.expanded = undefined;
                self.revisions = undefined;

                function APIVersionModel(data) {
                    var self = this;
                    //angular.merge(self, data);
                    self.revisions = [];
                    self.expanded = false;
                    self.number = data.number;
                    angular.forEach(data.revisions, function (value) {
                        var rev = new APIRevisionModel(value);
                        self.revisions.push(rev);
                    });
                }

                APIVersionModel.prototype.getValidPropertyList = function () {
                    return ['number','expanded','revisions'];
                };

                return APIVersionModel;
            }]);
})(angular);