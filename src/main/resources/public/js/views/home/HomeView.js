define(['jquery', 'underscore', 'backbone', 'App', 'ModelView',
        'text!templates/home/home-template.html'],
    function ($, _, Backbone, App, ModelView, homeTemplate) {

        var HomeView = ModelView.extend({

            template: function (data) {
                var compiled = _.template(homeTemplate);
                return compiled(data);
            }
        });

        return HomeView;

    });
