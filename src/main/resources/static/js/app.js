var app = angular.module('issueTrackerApp',[
    'ngResource',
    'ui.router',
    'issueTrackerApp.controllers',
    'issueTrackerApp.services'
]);

app.config(function($stateProvider,$httpProvider){
    $stateProvider.state('developers',{
        url:'/developers',
        templateUrl:'partials/developers/developers.html',
        controller:'DeveloperListController'
    }).state('viewDeveloper',{
       url:'/developers/:id/view',
       templateUrl:'partials/developers/developer-view.html',
       controller:'DeveloperViewController'
    }).state('newDeveloper',{
        url:'/developers/new',
        templateUrl:'partials/developers/developer-add.html',
        controller:'DeveloperCreateController'
    }).state('editDeveloper',{
        url:'/developers/:id/edit',
        templateUrl:'partials/developers/developer-edit.html',
        controller:'DeveloperEditController'
    }).state('plans',{
        url:'/plans',
        templateUrl:'partials/plans/plans.html',
        controller:'PlanListController'
    });
}).run(function($state){
   $state.go('developers');
});