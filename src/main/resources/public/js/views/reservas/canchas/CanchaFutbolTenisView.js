define(['jquery', 'underscore', 'backbone', 'App',
        'text!templates/reservas/canchas/canchaFutolTenis.html'],
    function ($, _, Backbone, App, computadores) {

        var canchaFutbolTenisView = Backbone.View.extend({
            el: $("#page"),

            render: function () {

                $('.menu li').removeClass('active');
                $('.menu li a[href="#"]').parent().addClass('active');
                this.$el.html(canchaFutolTenis);
                App.lanzarEventoLoad();
            }

        });

        return canchaFutbolTenisView;

    });