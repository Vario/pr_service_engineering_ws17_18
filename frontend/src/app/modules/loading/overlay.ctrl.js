/**
 * Created by wrichtsfeld on 01/12/2017.
 * Controller which handles the loading overlay to "block" UI
 */
(function (angular) {
    'use strict';
    var ngmod = angular.module('apieval.loading');
    ngmod.controller('overlayController', ['$scope',
        function ($scope) {
            var loads = 0;
            $scope.loading = {
                inc: function () {
                    loads++;
                },
                dec: function () {
                    loads--;
                },
                isLoading: function () {
                    return loads > 0;
                }
            };
            $scope.errors = [];
        }]);
})(angular);
