define(['jquery', 'underscore', 'backbone', 'App',
        'text!templates/reservas/biblioteca/biblioteca.html'],
    function ($, _, Backbone, App, biblioteca) {

        var bibliotecaView = Backbone.View.extend({
            el: $("#page"),

            render: function () {

                $('.menu li').removeClass('active');
                $('.menu li a[href="#"]').parent().addClass('active');
                this.$el.html(biblioteca);
                App.lanzarEventoLoad();
            }

        });

        return bibliotecaView;

    });