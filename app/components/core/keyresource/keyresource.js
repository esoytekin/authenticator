angular.module('core.keys',['ngResource']).factory('Keys',['$resource','$httpParamSerializerJQLike', function($resource,$httpParamSerializerJQLike){
    // return $resource('components/resources/keys.json')
    return $resource('http://emrahs.duckdns.org/rpitemp/set_onOff.php?param=totp',
        {},
        {
            create: {
                method: 'POST',
                headers: {'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'},
                transformRequest: transformUrlEncoded
            }
        }
        );

    function transformUrlEncoded(data) {
        return $httpParamSerializerJQLike(data);
    }
}]);

