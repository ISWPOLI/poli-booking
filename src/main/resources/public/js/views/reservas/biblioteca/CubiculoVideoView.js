define(['jquery', 'underscore', 'backbone', 'App',
        'text!templates/reservas/biblioteca/cubiculoVideo.html'],
    function ($, _, Backbone, App, cubiculoVideo) {

        var cubiculoVideoView = Backbone.View.extend({
            el: $("#page"),

            render: function () {

                $('.menu li').removeClass('active');
                $('.menu li a[href="#"]').parent().addClass('active');
                this.$el.html(cubiculoVideo);
                App.lanzarEventoLoad();
            }

        });

        return cubiculoVideoView;

    });