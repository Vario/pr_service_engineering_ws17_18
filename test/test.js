var RestApiQualityApi = require("./javascript-client")

var apiInstance = new RestApiQualityApi.FilesAPIApi();

var file = new RestApiQualityApi.FileRequest({});

var callback = function(error, data, response) {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
};
apiInstance.filesPost(file, callback);
