angular.module('core.user', ['ngResource']).factory('User', ['$resource', '$httpParamSerializerJQLike', function ($resource, $httpParamSerializerJQLike) {
    // return $resource('components/resources/keys.json')

    return {
        restricted: function (token) {
            return $resource('http://emrahs.duckdns.org:8080/totp-api/rest/user/:id',
                {
                    id: '@id'
                },
                {
                    query: {
                        method: 'GET',
                        headers: {
                            'Authorization': 'Basic ' + token
                        },
                        isArray: false,
                        credentials: true
                    }
                }
            );
        }
    };

    function transformUrlEncoded(data) {
        return $httpParamSerializerJQLike(data);
    }
}]);

