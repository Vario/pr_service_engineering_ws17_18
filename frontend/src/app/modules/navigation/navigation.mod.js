/**
 * Created by wrichtsfeld on 04/12/2017.
 */
(function (angular) {
    'use strict';
    var ngmod = angular.module('apieval.navigation', [
        'apieval.services',
        'ui.router'
    ]);

    ngmod.config([
        '$stateProvider',
        function ($stateProvider) {
            $stateProvider
                .state(createMainState());

            function createMainState() {
                return {
                    name: 'main',
                    url: '/main',
                    views: {
                        '': {
                            templateUrl: 'app/modules/navigation/main.html',
                            controller: 'NavigationController'
                        },
                        'header@main': {
                            templateUrl: 'app/modules/navigation/header.tpl.html'
                        }
                    }
                };
            }
        }
    ]);
})(angular);
