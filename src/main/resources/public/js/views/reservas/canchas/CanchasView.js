define(['jquery', 'underscore', 'moment', 'backbone', 'App',
        'text!templates/espacios/canchas.html'],
    function ($, _, moment, Backbone, App, canchas) {

        var canchasView = Backbone.View.extend({

            render: function () {

                $('.menu li').removeClass('active');
                $('.menu li a[href="#"]').parent().addClass('active');
                this.$el.html(canchas);
                App.lanzarEventoLoad();
            },
            events: {
                'click #pb-canchas-reservar-cancha-tenis': 'mostrarCanchaTenisHoy',
                'click #pb-canchas-reservar-cancha-multiple': 'mostrarCanchaMultipleHoy',
                'click #pb-canchas-reservar-canchas-futbol-tenis': 'mostratCanchasFutbolTenisHoy'
            },

            mostrarCanchaTenisHoy: function () {
                var hoy = moment().format("YYYY-MM-DD");
                App.router.navigate('bloques-disponibles?fecha=' + hoy + '&tipoEspacio=CUBICULO', true);
            },

            mostrarCanchaMultipleHoy: function () {
                var hoy = moment(new Date()).format("YYYY-MM-DD");
                App.router.navigate('bloques-disponibles?fecha=' + hoy + '&tipoEspacio=CUBICULO', true);
            },
            mostrarCanchasFutbolTenisHoy: function () {
                var hoy = moment(new Date()).format("YYYY-MM-DD");
                App.router.navigate('bloques-disponibles?fecha=' + hoy + '&tipoEspacio=CUBICULO', true);
            }

        });

        return canchasView;

    });
