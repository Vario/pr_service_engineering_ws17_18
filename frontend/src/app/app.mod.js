/**
 * Created by wrichtsfeld on 30/11/2017.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.webapp',
        [
            'apieval.navigation',
            'apieval.apieval',
            'apieval.services',
            'apieval.dashboard',
            'apieval.loading',
            'apieval.directives',
            'apieval.sanitize',
            'ngFileUpload',
            'ngDialog',
            'ngSanitize'
        ]);
})(angular);