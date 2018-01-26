/**
 * Created by wrichtsfeld on 04/12/2017.
 */
(function (angular) {
    'use strict';
    var ngmod = angular.module('apieval.dashboard', [
        'ui.router',
        'ngSanitize',
        'swaggerUi'
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
                            templateUrl: 'app/modules/dashboard/tree.html'
                        },
                        'infoview@main.dashboard': {
                            templateUrl: 'app/modules/dashboard/info.html'
                        }
                        /*,
                        'swaggerinfo@main.dashboard': {
                            templateUrl: 'app/modules/swaggerui/swaggerinfo.tpl.html'
                        }*/
                    }
                };
            }
        }]);
})(angular);