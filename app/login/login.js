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
.controller('LoginCtrl', ['$injector',function($injector) {

    var authenticationService = $injector.get('AuthenticationService');
    var $location = $injector.get("$location");
    var $rootScope = $injector.get("$rootScope");
    var authentication = $injector.get("Authentication");

    var self = this;

    self.showLogin = true;


    $rootScope.currentPage = 'login';

    self.login = function(event) {
        event.preventDefault();
        var username = self.username;
        var password = self.password;
        if (!username || !password){
            alert("fill the required fields");
            return;
        }

        authenticationService.setCredentials(username,password);
        $location.path("#!/view1");
        $rootScope.currentPage = null;
    };

    self.showRegister = function(){
        self.showLogin = false;
    };

    self.register = function(){
        console.log(self.user);
        authentication.create(self.user, function successfull(response){
            AuthenticationService.setCredentials(self.user.lgUsername,self.user.lgPassword);
            $location.path("#!/view1");
            $rootScope.currentPage = null;

        }, function error(response){
            console.log(response);

        });
    }

}]);

