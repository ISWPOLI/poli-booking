define([], function () {
    var region = function (opciones) {
        var vista = {
            el: opciones.el,

            mostrar: function (vista) {
                this.cerrarVista(this.vistaActual);
                this.vistaActual = vista;
                this.abrirVista(vista);

                var App = require('App');
                App.lanzarEventoLoad();
            },

            cerrarVista: function (vista) {
                // if (vista && vista.remove) {
                //     vista.remove();
                // }
            },

            abrirVista: function (vista) {
                this.configurarElemento();

                vista.render();
                this.$el.html(vista.el);

                if (vista.onShow) {
                    vista.onShow();
                }
            },

            configurarElemento: function () {
                if (this.$el) {
                    return;
                }

                this.$el = $(this.el);
            },

            eliminar: function () {
                this.cerrarVista(this.vistaActual);
            }
        };

        return vista;
    };

    return region;

});