define(['backbone'], function (Backbone) {
    var defaultRouter = Backbone.Router.extend({
        routes: {
            '': 'defaultRoute'
        },

        defaultRoute: function () {
            this.navigate('#', true);
        }
    });

    return defaultRouter;
});