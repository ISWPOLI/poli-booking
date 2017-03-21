define(['jquery', 'underscore', 'backbone', 'App',
        'text!templates/reservas/canchas/canchaFutbolTenis.html'],
    function ($, _, Backbone, App, canchaFutbolTenis) {

        var canchaFutbolTenisView = Backbone.View.extend({

            render: function () {

                $('.menu li').removeClass('active');
                $('.menu li a[href="#"]').parent().addClass('active');
                this.$el.html(canchaFutbolTenis);
                App.lanzarEventoLoad();
            }

        });

        return canchaFutbolTenisView;

    });