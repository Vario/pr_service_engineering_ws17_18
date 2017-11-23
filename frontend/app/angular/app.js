var app = angular.module("APIEval", []);

app.directive("dashboardTemplate",function(){
  return{
    templateUrl: "./template/dashboard.html",
    controller : "DashboardController",
    controllerAs: "dabCtrl"
  }
});

app.directive("infoTemplate",function(){
  return{
    templateUrl: "./template/info.html"/*,
    controller : "InfoController",
    controllerAs: "infoCtrl"*/
  }
});

app.directive("treeTemplate",function(){
  return{
    templateUrl: "./template/tree.html"/*,
    controller : "InfoController",
    controllerAs: "infoCtrl"*/
  }
});

app.factory("ControllerService",["$rootScope",
  function($rootScope){
    var self = this
    self.selectedApi = []
    self.selectedVersion = []
    self.selectedFile = []

    self.setSelect = function(api, version, file, report, done){
      self.selectedApi = api;
      self.selectedVersion = version;
      self.selectedFile = file;
      self.broadcastSelection();
    }

    self.getSelect = function(done){
      done([self.selectedApi, self.selectedVersion, self.selectedFile, self.selectedReport])
    }

    self.broadcastSelection = function(){
        $rootScope.$broadcast('_selection');
    }

    return self;
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
]);*/

app.controller("DashboardController",["ControllerService","$scope",
  function(ControllerService, $scope){
    var self = this
    self.selectedApi = []
    self.selectedVersion = []
    self.selectedFile = []
    self.selectedReport = []

    self.show = function(e){
      if(e.active){
         e.active = false;
         self.selectedApi = [];
         self.selectedVersion = [];
         self.selectedFile = [];
         self.selectedReport = [];
      }else e.active = true;
      if(e.version){
        self.selectedVersion = [];
        self.selectedFile = [];
        self.selectedReport = [];
        for(i = 0; i < e.version.length; i++){
          e.version[i].active = false;
        }
      }
    }

    self.select = function(api, version, file, report){
      if(api) self.selectedApi = api;
      if(version) self.selectedVersion = version;
      if(file) self.selectedFile = file;
      if(report) self.selectedReport = report;
      ControllerService.setSelect(api, version, file, report);
    }

    self.data = [
      {
        "name":"api1",
        "active": false,
        "version":[
          {
            "name":"v1",
            "active" : false,
            "file":[
              {
                "name":"1.json",
                "report" : []
              },
              {
                "name":"2.json",
                "report" : []
              }
            ]
          },
          {
            "name":"v2",
            "active" : false,
            "file":[
              {
                "name":"3.json",
                "report" : []
              }
            ]
          }
        ]
      },
      {
        "name":"api2",
        "active" : false,
        "version":[
          {
            "name":"v3",
            "active" : false,
            "file":[
              {
                "name":"4.json",
                "report":[
                  {
                    "name": "report1",
                  },
                  {
                    "name": "report2"
                  }
                ]
              },
              {
                "name":"5.json",
                "report" : []
              }
            ]
          },
          {
            "name":"v2",
            "active" : false,
            "file":[
              {
                "name":"2.json",
                "report":[
                  {
                    "name": "report6"
                  },
                  {
                    "name": "report5"
                  }
                ]
              }
            ]
          }
        ]
      }
    ];
  }
]);

/*app.controller("InfoController",["ControllerService","$scope",
  function(ControllerService, $scope){
    var self = this
    self.selectedApi = []
    self.selectedVersion = []
    self.selectedFile = []
    self.selectedReport = []

    function getSelect(){
        ControllerService.getSelect(function(data){
          self.selectedApi = data[0]
          self.selectedVersion = data[1]
          self.selectedFile = data[2]
        });
    }

    self.select = function(report){
      self.selectedReport = report
    }

    $scope.$on('_selection', function(args) {
        getSelect();
    });
  }
])*/

app.controller("TemplateController", ["$scope",
  function($scope){
    var self = this;
    self.template = "dashboard"

    self.setTemplate = function(template){
      self.template = template;
    }
  }
]);
