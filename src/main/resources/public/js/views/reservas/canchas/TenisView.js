define(['jquery', 'underscore', 'backbone', 'App',
        'text!templates/reservas/canchas/tenis.html'],
    function ($, _, Backbone, App, tenis) {

        var tenisView = Backbone.View.extend({

            render: function () {

                $('.menu li').removeClass('active');
                $('.menu li a[href="#"]').parent().addClass('active');
                this.$el.html(tenis);
                App.lanzarEventoLoad();
            }

        });

        return tenisView;

    });