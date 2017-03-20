define(['jquery', 'underscore', 'backbone', 'App',
        'text!templates/reservas/confirmarReserva.html'],
    function ($, _, Backbone, App, confirmarReserva) {

        var ConfirmarReservaView = Backbone.View.extend({
            el: $("#page"),

            render: function () {
                $('.menu li').removeClass('active');
                $('.menu li a[href="#"]').parent().addClass('active');
                this.$el.html(confirmarReserva);
                App.lanzarEventoLoad();
            }
        });

        return ConfirmarReservaView;

    });
