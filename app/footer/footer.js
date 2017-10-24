/**
 * Created by emrahsoytekin on 24.10.2017.
 */
'use strict';

angular.module('myApp.footer',['core.keys'])

.component('footer',{

    templateUrl: 'footer/footer.html',
    controller: 'Footer'
})
.controller('Footer', ['AuthenticationService','$location','$rootScope',function(AuthenticationService,$location,$rootScope) {

    var self = this;


    self.logout = AuthenticationService.logout;

    $rootScope.$watch('currentPage', function (newPage, oldPage){
        self.showFooter = newPage !== 'login';

    });

}]);
