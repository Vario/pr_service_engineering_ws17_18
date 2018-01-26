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

(function(factory) {
  if (typeof define === 'function' && define.amd) {
    // AMD. Register as an anonymous module.
    define(['ApiClient', 'model/Api', 'model/ApiTitle', 'model/ApplicationError', 'model/Change', 'model/ChangeItem', 'model/ComparisonReportRequest', 'model/ComparisonReportResponse', 'model/ComparisonReportResponsePaths', 'model/FileRequest', 'model/FileResponse', 'model/Revision', 'model/RevisionReports', 'model/Rule', 'model/Setting', 'model/SettingsCreationRequest', 'model/SettingsId', 'model/SettingsListItem', 'model/Swagger', 'model/SwaggerDiffRequest', 'model/VersionRequest', 'model/Violation', 'model/ViolationReportRequest', 'model/ViolationReportResponse', 'model/ViolationsCount', 'api/APIsAPIApi', 'api/FilesAPIApi', 'api/ReportsAPIApi', 'api/RulesAPIApi', 'api/SettingsAPIApi'], factory);
  } else if (typeof module === 'object' && module.exports) {
    // CommonJS-like environments that support module.exports, like Node.
    module.exports = factory(require('./ApiClient'), require('./model/Api'), require('./model/ApiTitle'), require('./model/ApplicationError'), require('./model/Change'), require('./model/ChangeItem'), require('./model/ComparisonReportRequest'), require('./model/ComparisonReportResponse'), require('./model/ComparisonReportResponsePaths'), require('./model/FileRequest'), require('./model/FileResponse'), require('./model/Revision'), require('./model/RevisionReports'), require('./model/Rule'), require('./model/Setting'), require('./model/SettingsCreationRequest'), require('./model/SettingsId'), require('./model/SettingsListItem'), require('./model/Swagger'), require('./model/SwaggerDiffRequest'), require('./model/VersionRequest'), require('./model/Violation'), require('./model/ViolationReportRequest'), require('./model/ViolationReportResponse'), require('./model/ViolationsCount'), require('./api/APIsAPIApi'), require('./api/FilesAPIApi'), require('./api/ReportsAPIApi'), require('./api/RulesAPIApi'), require('./api/SettingsAPIApi'));
  }
}(function(ApiClient, Api, ApiTitle, ApplicationError, Change, ChangeItem, ComparisonReportRequest, ComparisonReportResponse, ComparisonReportResponsePaths, FileRequest, FileResponse, Revision, RevisionReports, Rule, Setting, SettingsCreationRequest, SettingsId, SettingsListItem, Swagger, SwaggerDiffRequest, VersionRequest, Violation, ViolationReportRequest, ViolationReportResponse, ViolationsCount, APIsAPIApi, FilesAPIApi, ReportsAPIApi, RulesAPIApi, SettingsAPIApi) {
  'use strict';

  /**
   * API_for_determing_the_Quality_of_REST_APIs.<br>
   * The <code>index</code> module provides access to constructors for all the classes which comprise the public API.
   * <p>
   * An AMD (recommended!) or CommonJS application will generally do something equivalent to the following:
   * <pre>
   * var RestApiQualityApi = require('index'); // See note below*.
   * var xxxSvc = new RestApiQualityApi.XxxApi(); // Allocate the API class we're going to use.
   * var yyyModel = new RestApiQualityApi.Yyy(); // Construct a model instance.
   * yyyModel.someProperty = 'someValue';
   * ...
   * var zzz = xxxSvc.doSomething(yyyModel); // Invoke the service.
   * ...
   * </pre>
   * <em>*NOTE: For a top-level AMD script, use require(['index'], function(){...})
   * and put the application logic within the callback function.</em>
   * </p>
   * <p>
   * A non-AMD browser application (discouraged) might do something like this:
   * <pre>
   * var xxxSvc = new RestApiQualityApi.XxxApi(); // Allocate the API class we're going to use.
   * var yyy = new RestApiQualityApi.Yyy(); // Construct a model instance.
   * yyyModel.someProperty = 'someValue';
   * ...
   * var zzz = xxxSvc.doSomething(yyyModel); // Invoke the service.
   * ...
   * </pre>
   * </p>
   * @module index
   * @version 1.0.0
   */
  var exports = {
    /**
     * The ApiClient constructor.
     * @property {module:ApiClient}
     */
    ApiClient: ApiClient,
    /**
     * The Api model constructor.
     * @property {module:model/Api}
     */
    Api: Api,
    /**
     * The ApiTitle model constructor.
     * @property {module:model/ApiTitle}
     */
    ApiTitle: ApiTitle,
    /**
     * The ApplicationError model constructor.
     * @property {module:model/ApplicationError}
     */
    ApplicationError: ApplicationError,
    /**
     * The Change model constructor.
     * @property {module:model/Change}
     */
    Change: Change,
    /**
     * The ChangeItem model constructor.
     * @property {module:model/ChangeItem}
     */
    ChangeItem: ChangeItem,
    /**
     * The ComparisonReportRequest model constructor.
     * @property {module:model/ComparisonReportRequest}
     */
    ComparisonReportRequest: ComparisonReportRequest,
    /**
     * The ComparisonReportResponse model constructor.
     * @property {module:model/ComparisonReportResponse}
     */
    ComparisonReportResponse: ComparisonReportResponse,
    /**
     * The ComparisonReportResponsePaths model constructor.
     * @property {module:model/ComparisonReportResponsePaths}
     */
    ComparisonReportResponsePaths: ComparisonReportResponsePaths,
    /**
     * The FileRequest model constructor.
     * @property {module:model/FileRequest}
     */
    FileRequest: FileRequest,
    /**
     * The FileResponse model constructor.
     * @property {module:model/FileResponse}
     */
    FileResponse: FileResponse,
    /**
     * The Revision model constructor.
     * @property {module:model/Revision}
     */
    Revision: Revision,
    /**
     * The RevisionReports model constructor.
     * @property {module:model/RevisionReports}
     */
    RevisionReports: RevisionReports,
    /**
     * The Rule model constructor.
     * @property {module:model/Rule}
     */
    Rule: Rule,
    /**
     * The Setting model constructor.
     * @property {module:model/Setting}
     */
    Setting: Setting,
    /**
     * The SettingsCreationRequest model constructor.
     * @property {module:model/SettingsCreationRequest}
     */
    SettingsCreationRequest: SettingsCreationRequest,
    /**
     * The SettingsId model constructor.
     * @property {module:model/SettingsId}
     */
    SettingsId: SettingsId,
    /**
     * The SettingsListItem model constructor.
     * @property {module:model/SettingsListItem}
     */
    SettingsListItem: SettingsListItem,
    /**
     * The Swagger model constructor.
     * @property {module:model/Swagger}
     */
    Swagger: Swagger,
    /**
     * The SwaggerDiffRequest model constructor.
     * @property {module:model/SwaggerDiffRequest}
     */
    SwaggerDiffRequest: SwaggerDiffRequest,
    /**
     * The VersionRequest model constructor.
     * @property {module:model/VersionRequest}
     */
    VersionRequest: VersionRequest,
    /**
     * The Violation model constructor.
     * @property {module:model/Violation}
     */
    Violation: Violation,
    /**
     * The ViolationReportRequest model constructor.
     * @property {module:model/ViolationReportRequest}
     */
    ViolationReportRequest: ViolationReportRequest,
    /**
     * The ViolationReportResponse model constructor.
     * @property {module:model/ViolationReportResponse}
     */
    ViolationReportResponse: ViolationReportResponse,
    /**
     * The ViolationsCount model constructor.
     * @property {module:model/ViolationsCount}
     */
    ViolationsCount: ViolationsCount,
    /**
     * The APIsAPIApi service constructor.
     * @property {module:api/APIsAPIApi}
     */
    APIsAPIApi: APIsAPIApi,
    /**
     * The FilesAPIApi service constructor.
     * @property {module:api/FilesAPIApi}
     */
    FilesAPIApi: FilesAPIApi,
    /**
     * The ReportsAPIApi service constructor.
     * @property {module:api/ReportsAPIApi}
     */
    ReportsAPIApi: ReportsAPIApi,
    /**
     * The RulesAPIApi service constructor.
     * @property {module:api/RulesAPIApi}
     */
    RulesAPIApi: RulesAPIApi,
    /**
     * The SettingsAPIApi service constructor.
     * @property {module:api/SettingsAPIApi}
     */
    SettingsAPIApi: SettingsAPIApi
  };

  return exports;
}));
