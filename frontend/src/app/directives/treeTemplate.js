/**
 * Created by wrichtsfeld on 01/12/2017.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.directives')
        .directive("treeTemplate",function(){
            return{
                templateUrl: "./template/tree.html"/*,
                 controller : "InfoController",
                 controllerAs: "infoCtrl"*/
            };
        });
})(angular);