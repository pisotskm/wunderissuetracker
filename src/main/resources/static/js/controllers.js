angular.module('issueTrackerApp.controllers',[]).controller('DeveloperListController',
function($scope,$state,popupService,$window,Developer){
    $scope.developers=Developer.query();
    $scope.deleteDeveloper=function(developer){
        if(popupService.showPopup('Really delete this?')){
            developer.$delete(function(){
                $window.location.href='';
            });
        }
    }
}).controller('DeveloperViewController',function($scope,$stateParams,Developer){
    $scope.developer=Developer.get({id:$stateParams.id});
}).controller('DeveloperCreateController',function($scope,$state,$stateParams,Developer){
    $scope.developer=new Developer();
    $scope.addDeveloper=function(){
        $scope.developer.$save(function(){
            $state.go('developers');
        });
    }
}).controller('DeveloperEditController',function($scope,$state,$stateParams,Developer){
    $scope.updateDeveloper=function(){
        $scope.developer.$update(function(){
            $state.go('developers');
        });
    };
    $scope.loadDeveloper=function(){
        $scope.developer=Developer.get({id:$stateParams.id});
    };
    $scope.loadDeveloper();
}).controller('PlanListController', function($scope,Plan){
    $scope.plans=Plan.query();
});