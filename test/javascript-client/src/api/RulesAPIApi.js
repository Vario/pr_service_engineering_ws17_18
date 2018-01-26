/**
 * REST-API Quality API
 * API for determing the Quality of REST APIs
 *
 * OpenAPI spec version: 1.0.0
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 *
 * Swagger Codegen version: 2.2.3
 *
 * Do not edit the class manually.
 *
 */

(function(root, factory) {
  if (typeof define === 'function' && define.amd) {
    // AMD. Register as an anonymous module.
    define(['ApiClient', 'model/ApplicationError', 'model/Rule'], factory);
  } else if (typeof module === 'object' && module.exports) {
    // CommonJS-like environments that support module.exports, like Node.
    module.exports = factory(require('../ApiClient'), require('../model/ApplicationError'), require('../model/Rule'));
  } else {
    // Browser globals (root is window)
    if (!root.RestApiQualityApi) {
      root.RestApiQualityApi = {};
    }
    root.RestApiQualityApi.RulesAPIApi = factory(root.RestApiQualityApi.ApiClient, root.RestApiQualityApi.ApplicationError, root.RestApiQualityApi.Rule);
  }
}(this, function(ApiClient, ApplicationError, Rule) {
  'use strict';

  /**
   * RulesAPI service.
   * @module api/RulesAPIApi
   * @version 1.0.0
   */

  /**
   * Constructs a new RulesAPIApi. 
   * @alias module:api/RulesAPIApi
   * @class
   * @param {module:ApiClient} apiClient Optional API client implementation to use,
   * default to {@link module:ApiClient#instance} if unspecified.
   */
  var exports = function(apiClient) {
    this.apiClient = apiClient || ApiClient.instance;


    /**
     * Callback function to receive the result of the rulesGet operation.
     * @callback module:api/RulesAPIApi~rulesGetCallback
     * @param {String} error Error message, if any.
     * @param {Array.<module:model/Rule>} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Get all available rules
     * @param {module:api/RulesAPIApi~rulesGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Array.<module:model/Rule>}
     */
    this.rulesGet = function(callback) {
      var postBody = null;


      var pathParams = {
      };
      var queryParams = {
      };
      var headerParams = {
      };
      var formParams = {
      };

      var authNames = [];
      var contentTypes = ['application/json'];
      var accepts = ['application/json'];
      var returnType = [Rule];

      return this.apiClient.callApi(
        '/rules', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, callback
      );
    }
  };

  return exports;
}));
