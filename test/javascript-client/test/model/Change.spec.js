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
    // AMD.
    define(['expect.js', '../../src/index'], factory);
  } else if (typeof module === 'object' && module.exports) {
    // CommonJS-like environments that support module.exports, like Node.
    factory(require('expect.js'), require('../../src/index'));
  } else {
    // Browser globals (root is window)
    factory(root.expect, root.RestApiQualityApi);
  }
}(this, function(expect, RestApiQualityApi) {
  'use strict';

  var instance;

  beforeEach(function() {
    instance = new RestApiQualityApi.Change();
  });

  var getProperty = function(object, getter, property) {
    // Use getter method if present; otherwise, get the property directly.
    if (typeof object[getter] === 'function')
      return object[getter]();
    else
      return object[property];
  }

  var setProperty = function(object, setter, property, value) {
    // Use setter method if present; otherwise, set the property directly.
    if (typeof object[setter] === 'function')
      object[setter](value);
    else
      object[property] = value;
  }

  describe('Change', function() {
    it('should create an instance of Change', function() {
      // uncomment below and update the code to test Change
      //var instane = new RestApiQualityApi.Change();
      //expect(instance).to.be.a(RestApiQualityApi.Change);
    });

    it('should have the property endpoint (base name: "endpoint")', function() {
      // uncomment below and update the code to test the property endpoint
      //var instane = new RestApiQualityApi.Change();
      //expect(instance).to.be();
    });

    it('should have the property description (base name: "description")', function() {
      // uncomment below and update the code to test the property description
      //var instane = new RestApiQualityApi.Change();
      //expect(instance).to.be();
    });

    it('should have the property parameter (base name: "parameter")', function() {
      // uncomment below and update the code to test the property parameter
      //var instane = new RestApiQualityApi.Change();
      //expect(instance).to.be();
    });

    it('should have the property returntype (base name: "returntype")', function() {
      // uncomment below and update the code to test the property returntype
      //var instane = new RestApiQualityApi.Change();
      //expect(instance).to.be();
    });

  });

}));
