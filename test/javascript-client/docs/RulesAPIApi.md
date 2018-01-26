# RestApiQualityApi.RulesAPIApi

All URIs are relative to *http://quality.rest.patrickbuchner.at/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**rulesGet**](RulesAPIApi.md#rulesGet) | **GET** /rules | Get all available rules


<a name="rulesGet"></a>
# **rulesGet**
> [Rule] rulesGet()

Get all available rules

### Example
```javascript
var RestApiQualityApi = require('rest_api_quality_api');

var apiInstance = new RestApiQualityApi.RulesAPIApi();

var callback = function(error, data, response) {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
};
apiInstance.rulesGet(callback);
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**[Rule]**](Rule.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

