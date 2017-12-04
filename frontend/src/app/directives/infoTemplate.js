/**
 * Created by wrichtsfeld on 01/12/2017.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.directives')
        .directive("infoTemplate",function(){
            return{
                templateUrl: "./modules/apieval/info.html"/*,
                 controller : "InfoController",
                 controllerAs: "infoCtrl"*/
            };
        });
})(angular);