define(
    [

    ],

    function () {
        function createRequest() {
            var result = null;
            result = new XMLHttpRequest();
            if (typeof result.overrideMimeType != 'undefined') {
                result.overrideMimeType('text/xml');
            }
            return result;
        }

        function rest(method, url, data, resolve, reject) {
            var request = createRequest();

            // Response from server
            request.onreadystatechange = function() {
                if (request.readyState != 4) return; // Not there yet
                if (request.status != 200) {
                    // Request failed, handle request failure
                    reject(Error('Error fetching data. Reason: ' + request.statusText));
                    return;
                }

                // Request successful, handle response
                resolve(request.response);
            };

            request.open(method, url, true);
            request.send(data);
        }

        return {

            get : function(url, data) {
                return new Promise(function(resolve, reject) {
                    rest("GET", url, data || {}, resolve, reject);
                });
            },

            put : function(url, data) {
                return new Promise(function(resolve, reject) {
                    rest("PUT", url, data, resolve, reject)
                });
            }

        };
    }
);