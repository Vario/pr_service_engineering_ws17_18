# RestApiQualityApi.FilesAPIApi

All URIs are relative to *http://quality.rest.patrickbuchner.at/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**filesIdGet**](FilesAPIApi.md#filesIdGet) | **GET** /files/{id} | Get the raw swagger json as uploaded
[**filesPost**](FilesAPIApi.md#filesPost) | **POST** /files | Upload a file


<a name="filesIdGet"></a>
# **filesIdGet**
> Swagger filesIdGet(id)

Get the raw swagger json as uploaded

### Example
```javascript
var RestApiQualityApi = require('rest_api_quality_api');

var apiInstance = new RestApiQualityApi.FilesAPIApi();

var id = "id_example"; // String | 


var callback = function(error, data, response) {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
};
apiInstance.filesIdGet(id, callback);
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  | 

### Return type

[**Swagger**](Swagger.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="filesPost"></a>
# **filesPost**
> FileResponse filesPost(file)

Upload a file

### Example
```javascript
var RestApiQualityApi = require('rest_api_quality_api');

var apiInstance = new RestApiQualityApi.FilesAPIApi();

var file = new RestApiQualityApi.FileRequest(); // FileRequest | The file to upload


var callback = function(error, data, response) {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
};
apiInstance.filesPost(file, callback);
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **file** | [**FileRequest**](FileRequest.md)| The file to upload | 

### Return type

[**FileResponse**](FileResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

