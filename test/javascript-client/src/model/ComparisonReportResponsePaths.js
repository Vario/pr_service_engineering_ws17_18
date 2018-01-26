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
    define(['ApiClient', 'model/Change'], factory);
  } else if (typeof module === 'object' && module.exports) {
    // CommonJS-like environments that support module.exports, like Node.
    module.exports = factory(require('../ApiClient'), require('./Change'));
  } else {
    // Browser globals (root is window)
    if (!root.RestApiQualityApi) {
      root.RestApiQualityApi = {};
    }
    root.RestApiQualityApi.ComparisonReportResponsePaths = factory(root.RestApiQualityApi.ApiClient, root.RestApiQualityApi.Change);
  }
}(this, function(ApiClient, Change) {
  'use strict';




  /**
   * The ComparisonReportResponsePaths model module.
   * @module model/ComparisonReportResponsePaths
   * @version 1.0.0
   */

  /**
   * Constructs a new <code>ComparisonReportResponsePaths</code>.
   * @alias module:model/ComparisonReportResponsePaths
   * @class
   */
  var exports = function() {
    var _this = this;




  };

  /**
   * Constructs a <code>ComparisonReportResponsePaths</code> from a plain JavaScript object, optionally creating a new instance.
   * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
   * @param {Object} data The plain JavaScript object bearing properties of interest.
   * @param {module:model/ComparisonReportResponsePaths} obj Optional instance to populate.
   * @return {module:model/ComparisonReportResponsePaths} The populated <code>ComparisonReportResponsePaths</code> instance.
   */
  exports.constructFromObject = function(data, obj) {
    if (data) {
      obj = obj || new exports();

      if (data.hasOwnProperty('new')) {
        obj['new'] = ApiClient.convertToType(data['new'], [Change]);
      }
      if (data.hasOwnProperty('removed')) {
        obj['removed'] = ApiClient.convertToType(data['removed'], [Change]);
      }
      if (data.hasOwnProperty('changed')) {
        obj['changed'] = ApiClient.convertToType(data['changed'], [Change]);
      }
    }
    return obj;
  }

  /**
   * @member {Array.<module:model/Change>} new
   */
  exports.prototype['new'] = undefined;
  /**
   * @member {Array.<module:model/Change>} removed
   */
  exports.prototype['removed'] = undefined;
  /**
   * @member {Array.<module:model/Change>} changed
   */
  exports.prototype['changed'] = undefined;



  return exports;
}));

