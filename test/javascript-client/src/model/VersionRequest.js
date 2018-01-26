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
    define(['ApiClient', 'model/Revision'], factory);
  } else if (typeof module === 'object' && module.exports) {
    // CommonJS-like environments that support module.exports, like Node.
    module.exports = factory(require('../ApiClient'), require('./Revision'));
  } else {
    // Browser globals (root is window)
    if (!root.RestApiQualityApi) {
      root.RestApiQualityApi = {};
    }
    root.RestApiQualityApi.VersionRequest = factory(root.RestApiQualityApi.ApiClient, root.RestApiQualityApi.Revision);
  }
}(this, function(ApiClient, Revision) {
  'use strict';




  /**
   * The VersionRequest model module.
   * @module model/VersionRequest
   * @version 1.0.0
   */

  /**
   * Constructs a new <code>VersionRequest</code>.
   * @alias module:model/VersionRequest
   * @class
   */
  var exports = function() {
    var _this = this;



  };

  /**
   * Constructs a <code>VersionRequest</code> from a plain JavaScript object, optionally creating a new instance.
   * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
   * @param {Object} data The plain JavaScript object bearing properties of interest.
   * @param {module:model/VersionRequest} obj Optional instance to populate.
   * @return {module:model/VersionRequest} The populated <code>VersionRequest</code> instance.
   */
  exports.constructFromObject = function(data, obj) {
    if (data) {
      obj = obj || new exports();

      if (data.hasOwnProperty('number')) {
        obj['number'] = ApiClient.convertToType(data['number'], 'String');
      }
      if (data.hasOwnProperty('revisions')) {
        obj['revisions'] = ApiClient.convertToType(data['revisions'], [Revision]);
      }
    }
    return obj;
  }

  /**
   * API Versioning
   * @member {String} number
   */
  exports.prototype['number'] = undefined;
  /**
   * @member {Array.<module:model/Revision>} revisions
   */
  exports.prototype['revisions'] = undefined;



  return exports;
}));


