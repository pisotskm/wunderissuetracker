angular.module('issueTrackerApp.services',[]).factory('Developer',function($resource){
    return $resource('http://localhost:8080/developers/:id',{id:'@id'},{
        update: {
            method: 'PUT'
        }
    });
}).factory('Plan',function($resource){
    return $resource('http://localhost:8080/plan');
}).service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
});