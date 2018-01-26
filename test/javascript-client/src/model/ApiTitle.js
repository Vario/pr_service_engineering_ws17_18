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
    root.RestApiQualityApi.ApiTitle = factory(root.RestApiQualityApi.ApiClient);
  }
}(this, function(ApiClient) {
  'use strict';




  /**
   * The ApiTitle model module.
   * @module model/ApiTitle
   * @version 1.0.0
   */

  /**
   * Constructs a new <code>ApiTitle</code>.
   * @alias module:model/ApiTitle
   * @class
   */
  var exports = function() {
    var _this = this;


  };

  /**
   * Constructs a <code>ApiTitle</code> from a plain JavaScript object, optionally creating a new instance.
   * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
   * @param {Object} data The plain JavaScript object bearing properties of interest.
   * @param {module:model/ApiTitle} obj Optional instance to populate.
   * @return {module:model/ApiTitle} The populated <code>ApiTitle</code> instance.
   */
  exports.constructFromObject = function(data, obj) {
    if (data) {
      obj = obj || new exports();

      if (data.hasOwnProperty('title')) {
        obj['title'] = ApiClient.convertToType(data['title'], 'String');
      }
    }
    return obj;
  }

  /**
   * @member {String} title
   */
  exports.prototype['title'] = undefined;



  return exports;
}));


