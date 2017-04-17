define(['jquery', 'underscore','moment', 'backbone', 'App',
        'text!templates/espacios/gimnasio.html'],
    function ($, _,moment, Backbone, App, gimnasio) {

        var gimnasioView = Backbone.View.extend({

            render: function () {

                $('.menu li').removeClass('active');
                $('.menu li a[href="#"]').parent().addClass('active');
                this.$el.html(gimnasio);
                App.lanzarEventoLoad();
            },
            events:{
            	'click #hoy': 'mostrarCuposGimnasioHoy'
            },
            mostrarCuposGimnasioHoy: function () {
                var hoy = moment(new Date()).format("YYYY-MM-DD");
                App.router.navigate('bloques-disponibles?fecha=' + hoy + '&tipoEspacio=GIMNASIO', true);
            },

        });

        return gimnasioView;

    });
