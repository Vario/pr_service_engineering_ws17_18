var SwaggerDiff = require('swagger-diff');

var express = require("express");
var bodyParser = require("body-parser");

var app = express();

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

app.post("/comparison", function(req, res){
  //var oldSpec = req.body.oldSpec;
  //var newSpec = req.body.newSpec;
  var oldSpec = "http://quality.rest.patrickbuchner.at/api/v1/files/92165178-ac8c-4758-a8ee-f11787da6bf1"
  var newSpec = "http://quality.rest.patrickbuchner.at/api/v1/files/ce8028d8-56a0-4097-8841-facd4fb547c9"

  /*
    3-error
    2-warning
    1-info
    0-ignore
  */
  /*
    major     ==    1.0.0 -> 2.0.0
    minor     ==    1.0.0 -> 1.1.0
    patch     ==    1.0.0 -> 1.0.1
    unchanged ==    1.0.0 -> 1.0.0
  */
  var config = {
    "changes": {
      "breaks": {
        "major" : 2,
        "minor" : 3,
        "patch" : 3,
        "unchanged": 3
      },
      "smooths": {
        "major" : 1,
        "minor" : 1,
        "patch" : 2,
        "unchanged" : 3
      }
    }
  };

  SwaggerDiff(oldSpec, newSpec, config).then(function (diff) {
    //console.log(diff);
    diff_errors = diff.errors;
    diff_warnings = diff.warnings;
    diff_infos = diff.infos;

    errors = [];
    warnings = [];
    infos = [];

    for(var i = 0; i < diff_errors.length; i++){
      var change = diff_errors[i];
      //console.log(change);
      if(change.path == undefined) change.path = "";
      if(change.method == undefined) change.method = "";
      var object = {
        ruleId : change.ruleId,
        message : change.message,
        path : change.path,
        method : change.method
      }
      errors.push(object);
    }

    for(var i = 0; i < diff_warnings.length; i++){
      var change = diff_warnings[i];
      //console.log(change);
      if(change.path == undefined) change.path = "";
      if(change.method == undefined) change.method = "";
      var object = {
        ruleId : change.ruleId,
        message : change.message,
        path : change.path,
        method : change.method
      }
      warnings.push(object);
    }

    for(var i = 0; i < diff_infos.length; i++){
      var change = diff_infos[i];
      //console.log(change);
      if(change.path == undefined) change.path = "";
      if(change.method == undefined) change.method = "";
      var object = {
        ruleId : change.ruleId,
        message : change.message,
        path : change.path,
        method : change.method
      }
      infos.push(object);
    }

    var json = {
      errors : errors,
      warnings : warnings,
      infos : infos
    }
    res.json(json);
  })
});

var server = app.listen(9000, function () {
    console.log("Listening on port %s ...", server.address().port);
});
