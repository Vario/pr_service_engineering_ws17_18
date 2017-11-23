var app = angular.module("APIEval", []);

app.directive("apiTemplate",function(){
  return{
    templateUrl: "./template/api.html",
    controller : "ApiController",
    controllerAs: "apiCtrl"
  }
});

app.controller("ApiController",["$scope",
  function($scope){
    var self = this
    self.selectedApi = [];
    self.selectedVersion = [];

    self.selectApi = function(api){
      index = self.selectedApi.indexOf(api.name);
      if(index === -1){
        self.selectedApi.push(api.name);
      }else{
        self.selectedApi.splice(index);
      }
      console.log(self.selectedApi)
    };

    self.selectVersion = function(api, version){
      push = true
      data = {"name":api.name, "version":version.name}
      for(i = 0; i < self.selectedVersion.length; i++){
        if(angular.equals(self.selectedVersion[i], data)){
          self.selectedVersion.splice(data);
          push = false
          break;
        }
      }
      if(push) self.selectedVersion.push(data);
      console.log(self.selectedVersion)
    }

    self.doShow = function(api, version){
      show = false
      if(self.selectedApi.indexOf(api.name) !== -1){
        if(version == null) show = true;
        else{
          data = {"name":api.name, "version":version.name}
          for(i = 0; i < self.selectedVersion.length; i++){
            if(angular.equals(self.selectedVersion[i], data)) show = true;
          }
        }
      }
      return show;
    }

    self.data = [
      {
        "name":"api1",
        "version":[
          {
            "name":"v1",
            "file":[
              {
                "name":"1.json"
              },
              {
                "name":"2.json"
              }
            ]
          },
          {
            "name":"v2",
            "file":[
              {
                "name":"3.json"
              }
            ]
          }
        ]
      },
      {
        "name":"api2",
        "version":[
          {
            "name":"v3",
            "file":[
              {
                "name":"4.json"
              },
              {
                "name":"5.json"
              }
            ]
          },
          {
            "name":"v2",
            "file":[
              {
                "name":"2.json"
              }
            ]
          }
        ]
      }
    ];
  }
]);

app.controller("TemplateController", ["$scope",
  function($scope){
    var self = this;
    self.template = "apiTemplate"

    self.setTemplate = function(template){
      self.template = template;
    }
  }
]);

/*app.factory("RestService",["$http","$rootScope",
  function($http, $rootScope){
    var self = this;
    self.Apis = [];

    self.getApis = function(done){
      done(self.Apis)
    }
  }
]);*/
/*
app.controller("templateController",[
  function(){

  }
]);

app.controller("apiController",["$scope", "$location", "$routeParams",
  function($scope, $location, $routeParams){
    console.log("hallo")
    var self = this;
    $scope.test = "hallo";

    $scope.data = [
      "abc"
    ]
  }
]);*/
