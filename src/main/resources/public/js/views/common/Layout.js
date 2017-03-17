define(['ModelView'], function (ModelView) {
    var layout = ModelView.extend({

        // metodo sobreescrito de Backbone.View
        render: function () {
            this.cerrarRegiones();

            var respuestaRenderPadre = ModelView.prototype.render.call(this);

            this.configurarRegiones();
            return respuestaRenderPadre;
        },

        // metodo sobreescrito de Backbone.View
        remove: function (options) {
            ModelView.prototype.remove.call(this, options);
            this.cerrarRegiones();
        },

        configurarRegiones: function () {
            var that = this;
            var regionesDefinidas = this.regiones || {};

            if (!this._regiones) {
                this._regiones = {};
            }

            _.each(regionesDefinidas, function (selectorDelElemento, nombre) {
                var $el = that.$(selectorDelElemento);
                this._regiones[nombre] = new Region({el: $el});
            })
        },

        getRegion: function (nombreRegion) {
            var regiones = this._regiones || {};
            return regiones[nombreRegion];
        },


        cerrarRegiones: function () {
            var regions = this._regiones || {};

            _.each(regions, function (region) {
                if (region && region.eliminar) {
                    region.eliminar();
                }
            });
        }
    });

    return layout;
});