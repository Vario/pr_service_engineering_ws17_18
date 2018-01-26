# RestApiQualityApi.APIsAPIApi

All URIs are relative to *http://quality.rest.patrickbuchner.at/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apisGet**](APIsAPIApi.md#apisGet) | **GET** /apis | Get all available documents
[**apisIdSettingsPut**](APIsAPIApi.md#apisIdSettingsPut) | **PUT** /apis/{id}/settings | Update the Settings Set of an existing API
[**apisIdTitlePut**](APIsAPIApi.md#apisIdTitlePut) | **PUT** /apis/{id}/title | Change the title of an API


<a name="apisGet"></a>
# **apisGet**
> [Api] apisGet()

Get all available documents

### Example
```javascript
var RestApiQualityApi = require('rest_api_quality_api');

var apiInstance = new RestApiQualityApi.APIsAPIApi();

var callback = function(error, data, response) {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
};
apiInstance.apisGet(callback);
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**[Api]**](Api.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="apisIdSettingsPut"></a>
# **apisIdSettingsPut**
> SettingsId apisIdSettingsPut(id, opts)

Update the Settings Set of an existing API

### Example
```javascript
var RestApiQualityApi = require('rest_api_quality_api');

var apiInstance = new RestApiQualityApi.APIsAPIApi();

var id = "id_example"; // String | 

var opts = { 
  'settingsId': new RestApiQualityApi.SettingsId() // SettingsId | 
};

var callback = function(error, data, response) {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
};
apiInstance.apisIdSettingsPut(id, opts, callback);
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  | 
 **settingsId** | [**SettingsId**](SettingsId.md)|  | [optional] 

### Return type

[**SettingsId**](SettingsId.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="apisIdTitlePut"></a>
# **apisIdTitlePut**
> ApiTitle apisIdTitlePut(id, opts)

Change the title of an API

### Example
```javascript
var RestApiQualityApi = require('rest_api_quality_api');

var apiInstance = new RestApiQualityApi.APIsAPIApi();

var id = "id_example"; // String | 

var opts = { 
  'title': new RestApiQualityApi.ApiTitle() // ApiTitle | 
};

var callback = function(error, data, response) {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
};
apiInstance.apisIdTitlePut(id, opts, callback);
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  | 
 **title** | [**ApiTitle**](ApiTitle.md)|  | [optional] 

### Return type

[**ApiTitle**](ApiTitle.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

