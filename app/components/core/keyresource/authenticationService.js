/**
 * Created by emrahsoytekin on 23.10.2017.
 */
angular.module('core.keys').service('AuthenticationService',['$rootScope',function($rootScope){

    this.setCredentials = function setCredentials(username, password){
        var authData = btoa(username+":"+password);
        $rootScope.globals = {
            currentUser:{
                username: username,
                authData: authData
            }
        };

        window.localStorage.setItem('user', username);
        window.localStorage.setItem('authData',authData);

    };

    this.logout = function logout(){
        window.localStorage.removeItem('user');
        window.localStorage.removeItem('authData');
        $rootScope.globals = null;
    }

}]);