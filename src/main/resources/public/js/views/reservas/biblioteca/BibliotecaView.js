define(['jquery', 'underscore', 'moment', 'backbone', 'App',
        'text!templates/reservas/biblioteca/biblioteca.html'],
    function ($, _, moment, Backbone, App, biblioteca) {

        var bibliotecaView = Backbone.View.extend({

            render: function () {

                $('.menu li').removeClass('active');
                $('.menu li a[href="#"]').parent().addClass('active');
                this.$el.html(biblioteca);
                App.lanzarEventoLoad();
            },

            events: {
                'click #pb-biblioteca-reservar-cubiculo-estudio': 'mostrarCubiculosEstudioHoy'
            },

            mostrarCubiculosEstudioHoy: function () {
                var hoy = moment(new Date()).format("YYYY-MM-DD");
                App.router.navigate('bloques-disponibles?fecha=' + hoy + '&tipoEspacio=CUBICULO_ESTUDIO', true);
            }

        });

        return bibliotecaView;

    });