/**
 * Created by wrichtsfeld on 04/12/2017.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.navigation')

        .controller('NavigationController', [
            '$scope',
            '$state',
            function ($scope, $state) {
                $scope.$state = $state;
            }]);
})(angular);
