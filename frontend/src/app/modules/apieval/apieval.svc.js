/**
 * Created by wrichtsfeld on 30/11/2017.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.services')
        .service('APIEvalService', [
            'BackendAPIService',
            '$filter',
            '$q',
            function (BackendAPIService, $filter, $q) {
                var self = this;

                self.getAllAPIs = function () {
                    return $q(function (resolve) {
                        var result = [
                            {
                                "name": "api1",
                                "selected": false,
                                "versions": [
                                    {
                                        "name": "v1",
                                        "selected": false,
                                        "files": [
                                            {
                                                "name": "1.json",
                                                "reports": []
                                            },
                                            {
                                                "name": "2.json",
                                                "reports": []
                                            }
                                        ]
                                    },
                                    {
                                        "name": "v2",
                                        "selected": false,
                                        "files": [
                                            {
                                                "name": "3.json",
                                                "report": []
                                            }
                                        ]
                                    }
                                ]
                            },
                            {
                                "name": "api2",
                                "selected": false,
                                "versions": [
                                    {
                                        "name": "v3",
                                        "selected": false,
                                        "files": [
                                            {
                                                "name": "4.json",
                                                "reports": [
                                                    {
                                                        "name": "report1"
                                                    },
                                                    {
                                                        "name": "report2"
                                                    }
                                                ]
                                            },
                                            {
                                                "name": "5.json",
                                                "reports": []
                                            }
                                        ]
                                    },
                                    {
                                        "name": "v2",
                                        "selected": false,
                                        "files": [
                                            {
                                                "name": "2.json",
                                                "reports": [
                                                    {
                                                        "name": "report6"
                                                    },
                                                    {
                                                        "name": "report5"
                                                    }
                                                ]
                                            }
                                        ]
                                    }
                                ]
                            }
                        ];

                        resolve(result);
                    });
                };
            }

        ]);
})(angular);