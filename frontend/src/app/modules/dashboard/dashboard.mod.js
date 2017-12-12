/**
 * Created by wrichtsfeld on 04/12/2017.
 */
(function (angular) {
    'use strict';
    var ngmod = angular.module('apieval.dashboard', [
        'ui.router'
    ]);

    ngmod.config([
        '$stateProvider',
        '$urlRouterProvider',
        function ($stateProvider, $urlRouterProvider) {
            $stateProvider
                .state(createDashboardState());

            $urlRouterProvider.otherwise(function ($injector, $location) {
                var url = '/main/dashboard';
                return url;
            });

            function createDashboardState() {
                return {
                    name: 'main.dashboard',
                    url: '/dashboard',
                    views: {
                        '': {
                            templateUrl: 'app/modules/dashboard/dashboard.tpl.html',
                            controller: 'DashboardController'
                        },
                        'treeview@main.dashboard': {
                            templateUrl: 'app/modules/apieval/tree.html'
                        },
                        'infoview@main.dashboard': {
                            templateUrl: 'app/modules/apieval/info.html'
                        },
                        'apiupload@main.dashboard': {
                            templateUrl: 'app/modules/files/fileupload.tpl.html'
                        },
                        'apiupload@main.dashboard.infoview': {
                            templateUrl: 'app/modules/files/fileupload.tpl.html'
                        }
                    }

                };
            }
        }]);
})(angular);