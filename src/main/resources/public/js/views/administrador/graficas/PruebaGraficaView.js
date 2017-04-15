define(['jquery', 'underscore', 'backbone', 'App',
        'text!templates/administrador/graficas/pruebaGrafica.html'],
    function ($, _, Backbone, App, pruebaGrafica) {

        var pruebaGraficaView = Backbone.View.extend({

            render: function () {
                this.$el.html(pruebaGrafica);
                App.lanzarEventoLoad();
            }

        });
        return pruebaGraficaView;

    });