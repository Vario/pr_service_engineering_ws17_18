/**
 * Created by wrichtsfeld on 01/12/2017.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.directives')
        .directive("dashboardTemplate",function(){
        return{
            templateUrl: "./template/dashboard.html",
            controller : "DashboardController",
            controllerAs: "dabCtrl"
        };
    });
})(angular);