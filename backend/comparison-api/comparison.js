var SwaggerDiff = require('swagger-diff');

var express = require("express");
var bodyParser = require("body-parser");

var app = express();

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

app.post("/comparison", function(req, res){
  var oldSpec = req.body.oldSpec;
  var newSpec = req.body.newSpec;
  //oldSpec = "http://quality.rest.patrickbuchner.at/api/v1/files/d62adc7d-560c-4f75-b00a-bccfbcb899ab";
  //newSpec = "http://quality.rest.patrickbuchner.at/api/v1/files/da5d38b8-3824-4541-87e7-6b96cb033a47";
  //console.log(req);
  config = "./node-modules/swagger-diff/src/defaultConfig.json";
  SwaggerDiff(oldSpec, newSpec, config).then(function (diff) {
    console.log(diff);
    var json = {
                  response : diff
                };
    res.json(json);
  })
});

var server = app.listen(9000, function () {
    console.log("Listening on port %s ...", server.address().port);
});
