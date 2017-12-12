/**
 * Created by wrichtsfeld on 30/11/2017.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.api', [
        'apieval.services'
    ]);

    angular.module('apieval.api')
        .value('APIEVAL_API_VERSION', 'v1');
})(angular);
