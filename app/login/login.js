/**
 * Created by emrahsoytekin on 23.10.2017.
 */
'use strict';

angular.module('myApp.login',['ngRoute','core.keys'])

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/login', {
        template : '<login-ctrl></login-ctrl>'
    });
}])

.component('loginCtrl',{

    templateUrl: 'login/login.html',
    controller: 'LoginCtrl'
})
.controller('LoginCtrl', ['AuthenticationService',function(AuthenticationService) {

    var self = this;

    self.login = function(event) {
        event.preventDefault();
        var username = self.username;
        var password = self.password;
        if (!username || !password){
            alert("fill the required fields");
            return;
        }

        AuthenticationService.setCredentials(username,password);
        window.location.href="#!/view1";
    }

}]);

