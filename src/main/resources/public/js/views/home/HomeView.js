define(['jquery', 'underscore', 'backbone', 'App',
        'text!templates/home/home-template.html'],
    function ($, _, Backbone, App, homeTemplate) {

        var HomeView = Backbone.View.extend({
            el: $("#page"),

            render: function () {
                $('.menu li').removeClass('active');
                $('.menu li a[href="#"]').parent().addClass('active');
                this.$el.html(homeTemplate);
                App.lanzarEventoLoad();
            }
        });

        return HomeView;

    });
