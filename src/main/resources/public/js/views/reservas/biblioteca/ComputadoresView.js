define(['jquery', 'underscore', 'backbone', 'App',
        'text!templates/reservas/biblioteca/computadores.html'],
    function ($, _, Backbone, App, computadores) {

        var computadoresView = Backbone.View.extend({
            el: $("#page"),

            render: function () {

                $('.menu li').removeClass('active');
                $('.menu li a[href="#"]').parent().addClass('active');
                this.$el.html(computadores);
                App.lanzarEventoLoad();
            }

        });

        return computadoresView;

    });