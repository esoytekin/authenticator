angular.module('core.keys', ['ngResource']).factory('Keys', ['$resource', '$httpParamSerializerJQLike', function ($resource, $httpParamSerializerJQLike) {
    // return $resource('components/resources/keys.json')

    return {
        restricted: function (token) {
            token = window.localStorage.getItem('authData');
            return $resource('http://emrahs.duckdns.org:8080/totp-api/rest/totp/:id',
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
                        credentials: true
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

