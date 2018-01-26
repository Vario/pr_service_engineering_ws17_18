# RestApiQualityApi.SettingsAPIApi

All URIs are relative to *http://quality.rest.patrickbuchner.at/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**settingsGet**](SettingsAPIApi.md#settingsGet) | **GET** /settings | Get all available setting sets
[**settingsIdGet**](SettingsAPIApi.md#settingsIdGet) | **GET** /settings/{id} | Get a settings set
[**settingsIdPut**](SettingsAPIApi.md#settingsIdPut) | **PUT** /settings/{id} | Update a Settings Set
[**settingsPost**](SettingsAPIApi.md#settingsPost) | **POST** /settings | Create a new Settings Set


<a name="settingsGet"></a>
# **settingsGet**
> [SettingsListItem] settingsGet()

Get all available setting sets

### Example
```javascript
var RestApiQualityApi = require('rest_api_quality_api');

var apiInstance = new RestApiQualityApi.SettingsAPIApi();

var callback = function(error, data, response) {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
};
apiInstance.settingsGet(callback);
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**[SettingsListItem]**](SettingsListItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="settingsIdGet"></a>
# **settingsIdGet**
> Setting settingsIdGet(id)

Get a settings set

### Example
```javascript
var RestApiQualityApi = require('rest_api_quality_api');

var apiInstance = new RestApiQualityApi.SettingsAPIApi();

var id = "id_example"; // String | Settings ID


var callback = function(error, data, response) {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
};
apiInstance.settingsIdGet(id, callback);
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| Settings ID | 

### Return type

[**Setting**](Setting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="settingsIdPut"></a>
# **settingsIdPut**
> Setting settingsIdPut(id, settings)

Update a Settings Set

### Example
```javascript
var RestApiQualityApi = require('rest_api_quality_api');

var apiInstance = new RestApiQualityApi.SettingsAPIApi();

var id = "id_example"; // String | Settings ID

var settings = new RestApiQualityApi.SettingsCreationRequest(); // SettingsCreationRequest | Report Creation


var callback = function(error, data, response) {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
};
apiInstance.settingsIdPut(id, settings, callback);
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| Settings ID | 
 **settings** | [**SettingsCreationRequest**](SettingsCreationRequest.md)| Report Creation | 

### Return type

[**Setting**](Setting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="settingsPost"></a>
# **settingsPost**
> Setting settingsPost(settings)

Create a new Settings Set

### Example
```javascript
var RestApiQualityApi = require('rest_api_quality_api');

var apiInstance = new RestApiQualityApi.SettingsAPIApi();

var settings = new RestApiQualityApi.SettingsCreationRequest(); // SettingsCreationRequest | Report Creation


var callback = function(error, data, response) {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
};
apiInstance.settingsPost(settings, callback);
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **settings** | [**SettingsCreationRequest**](SettingsCreationRequest.md)| Report Creation | 

### Return type

[**Setting**](Setting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

