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
    define(['ApiClient'], factory);
  } else if (typeof module === 'object' && module.exports) {
    // CommonJS-like environments that support module.exports, like Node.
    module.exports = factory(require('../ApiClient'));
  } else {
    // Browser globals (root is window)
    if (!root.RestApiQualityApi) {
      root.RestApiQualityApi = {};
    }
    root.RestApiQualityApi.SwaggerDiffRequest = factory(root.RestApiQualityApi.ApiClient);
  }
}(this, function(ApiClient) {
  'use strict';




  /**
   * The SwaggerDiffRequest model module.
   * @module model/SwaggerDiffRequest
   * @version 1.0.0
   */

  /**
   * Constructs a new <code>SwaggerDiffRequest</code>.
   * @alias module:model/SwaggerDiffRequest
   * @class
   */
  var exports = function() {
    var _this = this;



  };

  /**
   * Constructs a <code>SwaggerDiffRequest</code> from a plain JavaScript object, optionally creating a new instance.
   * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
   * @param {Object} data The plain JavaScript object bearing properties of interest.
   * @param {module:model/SwaggerDiffRequest} obj Optional instance to populate.
   * @return {module:model/SwaggerDiffRequest} The populated <code>SwaggerDiffRequest</code> instance.
   */
  exports.constructFromObject = function(data, obj) {
    if (data) {
      obj = obj || new exports();

      if (data.hasOwnProperty('oldSpec')) {
        obj['oldSpec'] = ApiClient.convertToType(data['oldSpec'], 'String');
      }
      if (data.hasOwnProperty('newSpec')) {
        obj['newSpec'] = ApiClient.convertToType(data['newSpec'], 'String');
      }
    }
    return obj;
  }

  /**
   * @member {String} oldSpec
   */
  exports.prototype['oldSpec'] = undefined;
  /**
   * @member {String} newSpec
   */
  exports.prototype['newSpec'] = undefined;



  return exports;
}));


