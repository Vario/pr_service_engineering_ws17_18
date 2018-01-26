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
    root.RestApiQualityApi.SettingsId = factory(root.RestApiQualityApi.ApiClient);
  }
}(this, function(ApiClient) {
  'use strict';




  /**
   * The SettingsId model module.
   * @module model/SettingsId
   * @version 1.0.0
   */

  /**
   * Constructs a new <code>SettingsId</code>.
   * @alias module:model/SettingsId
   * @class
   */
  var exports = function() {
    var _this = this;


  };

  /**
   * Constructs a <code>SettingsId</code> from a plain JavaScript object, optionally creating a new instance.
   * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
   * @param {Object} data The plain JavaScript object bearing properties of interest.
   * @param {module:model/SettingsId} obj Optional instance to populate.
   * @return {module:model/SettingsId} The populated <code>SettingsId</code> instance.
   */
  exports.constructFromObject = function(data, obj) {
    if (data) {
      obj = obj || new exports();

      if (data.hasOwnProperty('id')) {
        obj['id'] = ApiClient.convertToType(data['id'], 'String');
      }
    }
    return obj;
  }

  /**
   * @member {String} id
   */
  exports.prototype['id'] = undefined;



  return exports;
}));


