define(['jquery', 'underscore', 'backbone', 'App',
        'text!templates/espacios/gimnasio.html'],
    function ($, _, Backbone, App, gimnasio) {

        var gimnasioView = Backbone.View.extend({

            render: function () {

                $('.menu li').removeClass('active');
                $('.menu li a[href="#"]').parent().addClass('active');
                this.$el.html(gimnasio);
                App.lanzarEventoLoad();
            }

        });

        return gimnasioView;

    });
