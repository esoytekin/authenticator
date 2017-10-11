'use strict';

angular.module('myApp.view2', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view2', {
    template : '<view2-ctrl></view2-ctrl>'
  });
}])

.component('view2Ctrl',{
    templateUrl: 'view2/view2.html',
    controller: 'View2Ctrl'
})

.controller('View2Ctrl', ['Keys',function(Keys) {
    var totpObj = new TOTP();
    var self = this;



    self.save = function(event){
        event.preventDefault();
    
        if (!self.txtSite || !self.txtSecret){
            alert("fill the required fields");
            return;
        }

        try {
            totpObj.getOTP(self.txtSecret);


            var obj = {"site": self.txtSite, "secret":self.txtSecret };

            // self.keys = addItem(obj);

            Keys.create(obj, function(response){
                window.location.href="#!/view1";
            });

        }catch(e){
            alert(e);
            console.log(e);
        
        }

        self.txtSite = "";
        self.txtSecret = "";

    
    }

}]);

var addItem = function(obj){
        var elems = window.localStorage.getItem("keys");
        var keys = JSON.parse(elems);

        keys.push(obj);
        window.localStorage.setItem("keys",JSON.stringify(keys));
        return keys;


};
