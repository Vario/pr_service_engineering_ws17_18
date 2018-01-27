/**
 * Created by wrichtsfeld on 11.01.18.
 */
(function (angular) {
    'use strict';
    angular.module('apieval.services')
        .service('APIEvalSettingsService', [
            'BackendAPIService',
            'APISettingsModel',
            'APIRuleModel',
            'UpdateAPISettingsModelResponse',
            '$filter',
            '$q',
            function (BackendAPIService, APISettingsModel,APIRuleModel, UpdateAPISettingsModelResponse, $filter, $q) {
                var self = this;
                self.getSetting = function(settingsid) {
                    return BackendAPIService.getSetting(settingsid).then(function (data) {
                        if (data.length <= 0) {
                            return $q.reject();
                        }
                        var setting =  new APISettingsModel(data);
                        return setting;
                    }).catch(function (error) {
                        return null;
                    });
                };

                self.getRules = function() {
                    return BackendAPIService.getRules().then(function (data) {
                        if (data.length <= 0) {
                            return $q.reject();
                        }
                        var rules = [];

                        angular.forEach(data, function (value) {
                            var rule = new APIRuleModel(value);
                            rules.push(rule);
                        });

                        return rules;
                    }).catch(function (error) {
                       return [];
                    });
                };

                self.createSetting = function(apiname, rules) {
                   var model =
                        {
                            "name": name,
                            "rules": rules
                        };
                    return BackendAPIService.postSetting(angular.toJson(model)).then(function (response) {
                        var responseModel = new UpdateAPISettingsModelResponse(response);
                        return responseModel;
                    }).catch(function (error) {
                        return null;
                    });
                };

                self.updateSetting = function(api, rules) {
                    var model =
                        {
                            "name": api.name,
                            "rules": rules
                        };
                    return BackendAPIService.putSetting(api.settingsid,angular.toJson(model)).then(function (response) {
                        var responseModel = new UpdateAPISettingsModelResponse(response);
                        return responseModel;
                    }).catch(function (error) {
                        return null;
                    });
                };
            }
        ]);
})(angular);