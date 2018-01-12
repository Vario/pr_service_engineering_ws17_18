/**
 * Created by wrichtsfeld on 12.01.18.
 */

(function (angular) {
    'use strict';
    angular.module('apieval.sanitize', [
        'ngSanitize'
    ]);

    angular.module('apieval.sanitize')
        .config(function($sceDelegateProvider) {
            $sceDelegateProvider.resourceUrlWhitelist([
                // Allow same origin resource loads.
                'self',
                // Allow loading from our assets domain.  Notice the difference between * and **.
                'http://petstore.swagger.io/v2/**'
            ]);

            // The blacklist overrides the whitelist so the open redirect here is blocked.
            $sceDelegateProvider.resourceUrlBlacklist([
                //'http://myapp.example.com/clickThru**'
            ]);
        });
})(angular);
