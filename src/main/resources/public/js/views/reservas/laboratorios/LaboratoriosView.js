define(['jquery', 'underscore', 'moment', 'backbone', 'App',
        'text!templates/reservas/laboratorios/laboratorios.html'],
    function ($, _, moment, Backbone, App, laboratorios) {

        var laboratoriosView = Backbone.View.extend({

            render: function () {

                $('.menu li').removeClass('active');
                $('.menu li a[href="#"]').parent().addClass('active');
                this.$el.html(laboratorios);
                App.lanzarEventoLoad();
            },
        });

        return laboratoriosView;

    });