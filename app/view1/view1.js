'use strict';

angular.module('myApp.view1', ['ngRoute','core.keys'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view1', {
    template : '<view1-ctrl></view1-ctrl>'
  });
}])

.component('view1Ctrl',{

    templateUrl: 'view1/view1.html',
    controller: 'View1Ctrl'
})

.controller('View1Ctrl', ['$interval','$scope','Keys','$http','AuthenticationService','$rootScope',function($interval,$scope,Keys,$http,AuthenticationService, $rootScope) {
    var self = this;
    var totpObj = new TOTP();

    if (!window.localStorage.getItem('user'))
        window.location.href="#!/login";

    var authData = window.localStorage.getItem('authData');

    getFromRemote(self, Keys, totpObj, authData);



    $interval(function(){
        updateKeys(self.keys);
    }, 1000);



    self.delete = function(){

        var selectedItems = self.keys.filter(function(elem,index,self){return elem.selected});
        if (!confirm("Delete " + selectedItems.length + " items?" )){
            return;
        }

        self.keys = self.keys.filter(function(elem,index,self){
            return !selectedItems.includes(elem);
        });
        angular.forEach(selectedItems,function(item){
            Keys.restricted().delete({id: item.id}).$promise.then(function(response){
                console.log(response);
            });
        });
        /*
        var notDeletedItems = self.keys.filter(function(elem,index,self){return !elem.selected;});
        window.localStorage.setItem("keys",JSON.stringify(notDeletedItems));
        self.keys = notDeletedItems;
        if(self.keys.length < 1)
            window.location.href="#!/view2";
            */

    }

    self.checked = function(item){

        self.selected = self.keys.filter(function(elem,index,self){return elem.selected}).length>0;
    }

    self.copy = function(item){


        var lblInfo;
        if( copyToClipboard(item.totp)) {
            lblInfo = "Copied.";
        } else {
            lblInfo ="Couldn't copy!";
        }

        self.secs = 3;
        self.showInfoLabel = true;
        var labelTimer = $interval(function(){
            self.secs--;
            if (self.secs == 0){
                self.showInfoLabel = false;
                $interval.cancel(labelTimer);

            }

        },1000);

    };


    self.getFromLocalStorage = function() {
        if (!window.localStorage.getItem("keys")) {
            Keys.query().$promise.then(function(results){
                if (results.length < 1){
                    window.location.href="#!/view2";
                } else {
                    window.localStorage.setItem("keys",JSON.stringify(results));
                    angular.forEach(results,function(result){
                        result.time = totpObj.getTime();
                        result.totp = totpObj.getOTP(result.secret);

                    });

                    self.keys= results;

                }
            });


        } else {
            var elems = window.localStorage.getItem("keys");
            self.keys = JSON.parse(elems);

            if (self.keys.length < 1)
            {
                window.location.href="#!/view2";
            }else
                updateKeys(self.keys);
        }

    };


    self.logout = function(){
        AuthenticationService.logout();
        window.location.href="#!/login";
    }



}]);

var updateKeys = function(keys){
    var totpObj = new TOTP();
        angular.forEach(keys,function(result){
            result.time = totpObj.getTime();
            result.totp = totpObj.getOTP(result.secret);
            if (result.time === 30){
                result.totp = totpObj.getOTP(result.secret);
            }

        });

}

var addItem = function(obj){
        var elems = window.localStorage.getItem("keys");
        var keys = JSON.parse(elems);

        keys.push(obj);
        window.localStorage.setItem("keys",JSON.stringify(keys));
        return keys;


};

var getFromRemote = function(self, Keys, totpObj,authData) {
    Keys.restricted(authData).query().$promise.then(function(results){
        console.log(results);
        if (results.length < 1){
            window.location.href="#!/view2";
        } else {
            angular.forEach(results,function(result){
                result.time = totpObj.getTime();
                result.totp = totpObj.getOTP(result.secret);

            });
            self.keys= results;

        }
    }, function error(response){
        console.log(response.statusText);
        self.logout();
        window.location.href="#!/login";
    });

};


function copyToClipboard(elem) {
	  // create hidden text element, if it doesn't already exist
    var targetId = "_hiddenCopyText_";
    var origSelectionStart, origSelectionEnd;
        // must use a temporary form element for the selection and copy
    var target = document.createElement("textarea");
    target.style.position = "absolute";
    target.style.left = "-9999px";
    target.style.top = "0";
    target.id = targetId;
    document.body.appendChild(target);
    target.textContent = elem;
    // select the content
    var currentFocus = document.activeElement;
    target.focus();
    target.setSelectionRange(0, target.value.length);
    
    // copy the selection
    var succeed;
    try {
    	  succeed = document.execCommand("copy");
    } catch(e) {
        succeed = false;
    }
    // restore original focus
    if (currentFocus && typeof currentFocus.focus === "function") {
        currentFocus.focus();
    }
    
        // clear temporary content
    target.textContent = "";
    return succeed;
}

