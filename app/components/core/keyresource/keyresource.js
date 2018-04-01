angular.module('core.keys', ['ngResource']).factory('Keys', ['$resource', '$httpParamSerializerJQLike','Properties', function ($resource, $httpParamSerializerJQLike, Properties) {

    Properties.get(function(data) {
        console.log(data.url);
        self.url = data.url;
    });

    return {
        restricted: function (token) {
            return $resource('http://localhost:8081/totp/:id',
                {
                    id: '@id'
                },
                {
                    create: {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                            'Authorization' : 'Basic ' + token
                        },
                        transformRequest: transformUrlEncoded
                    },
                    query: {
                        method: 'GET',
                        headers: {
                            'Authorization': 'Basic ' + token
                        },
                        isArray: true,
                        credentials: true,
                        withCredentials: true
                    },
                    delete: {
                        method: 'DELETE',
                        headers: {
                            'Content-Type': 'application/json; charset=UTF-8',
                            'Authorization' : 'Basic ' + token
                        },
                        credentials: true
                    },
                    put: {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json; charset=UTF-8',
                            'Authorization' : 'Basic ' + token
                        },
                        credentials: true,
                        isArray: true
                    }
                }
            );
        }
    };

    function transformUrlEncoded(data) {
        return $httpParamSerializerJQLike(data);
    }
}]);

