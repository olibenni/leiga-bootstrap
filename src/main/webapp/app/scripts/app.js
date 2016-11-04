(function( requirejs ) {

   var require = requirejs.config({
        baseUrl: 'app',
        paths: {}
   });

    define(
        "leiga-application",
        [
            'scripts/api/rest'
        ],

        function(rest){
            // Fetches json data and logs it.
            rest.get("/service/json").then(
                function onResolve(data) {
                    data = JSON.parse(data);
                    console.log(data);
                }
            );
        }
    )
    requirejs(['leiga-application']);
}( requirejs ));