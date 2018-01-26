# RestApiQualityApi.ReportsAPIApi

All URIs are relative to *http://quality.rest.patrickbuchner.at/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**reportsComparisonPost**](ReportsAPIApi.md#reportsComparisonPost) | **POST** /reports/comparison | Create a new comparison report
[**reportsViolationPost**](ReportsAPIApi.md#reportsViolationPost) | **POST** /reports/violation | Create a new violation report


<a name="reportsComparisonPost"></a>
# **reportsComparisonPost**
> ComparisonReportResponse reportsComparisonPost(file)

Create a new comparison report

### Example
```javascript
var RestApiQualityApi = require('rest_api_quality_api');

var apiInstance = new RestApiQualityApi.ReportsAPIApi();

var file = new RestApiQualityApi.ComparisonReportRequest(); // ComparisonReportRequest | Report Creation


var callback = function(error, data, response) {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
};
apiInstance.reportsComparisonPost(file, callback);
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **file** | [**ComparisonReportRequest**](ComparisonReportRequest.md)| Report Creation | 

### Return type

[**ComparisonReportResponse**](ComparisonReportResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="reportsViolationPost"></a>
# **reportsViolationPost**
> ViolationReportResponse reportsViolationPost(file)

Create a new violation report

### Example
```javascript
var RestApiQualityApi = require('rest_api_quality_api');

var apiInstance = new RestApiQualityApi.ReportsAPIApi();

var file = new RestApiQualityApi.ViolationReportRequest(); // ViolationReportRequest | Report Creation


var callback = function(error, data, response) {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
};
apiInstance.reportsViolationPost(file, callback);
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **file** | [**ViolationReportRequest**](ViolationReportRequest.md)| Report Creation | 

### Return type

[**ViolationReportResponse**](ViolationReportResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

