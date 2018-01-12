/**
 * Created by wrichtsfeld on 08/01/2018.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.services')
        .factory('APIViolationsCountModel', [
            function () {
                var self = this;

                self.may = undefined;
                self.hint = undefined;
                self.should = undefined;
                self.must = undefined;
                self.could = undefined;
                function APIViolationsCountModel(data) {
                    var self = this;
                    self.may = data.may;
                    self.hint = data.hint;
                    self.should = data.should;
                    self.could = data.could;
                    self.must = data.must;

                    // angular.merge(self, data);
                }

                APIViolationsCountModel.prototype.getValidPropertyList = function () {
                    return ['may','hint','should','must','could'];
                };

                return APIViolationsCountModel;
            }]);
})(angular);