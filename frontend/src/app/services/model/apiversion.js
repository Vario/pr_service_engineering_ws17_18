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
                self.checked = undefined;

                function APIVersionModel(data) {
                    var self = this;
                    self.revisions = [];
                    self.expanded = false;
                    self.checked = false;
                    self.number = data.id;
                    //iterrate through revisions and create models
                    angular.forEach(data.revisions, function (value) {
                        var rev = new APIRevisionModel(value);
                        self.revisions.push(rev);
                    });
                }

                APIVersionModel.prototype.getValidPropertyList = function () {
                    return ['number','expanded','revisions', 'checked'];
                };

                return APIVersionModel;
            }]);
})(angular);