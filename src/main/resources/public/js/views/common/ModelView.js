define(['jquery', 'underscore', 'backbone', 'App'], function ($, _, Backbone, App) {
    var modelView = Backbone.View.extend({

        // metodo sobreescrito de Backbone.View
        render: function () {
            var data = this.serializarData();
            var htmlRenderizado;

            if (_.isFunction(this.template)) {
                htmlRenderizado = this.template(data);
            } else if (_.isString(this.template)) {
                var templateCompilado = this.compilarTemplate();
                htmlRenderizado = templateCompilado(data);
            }

            this.$el.html(htmlRenderizado);

            if (this.onRender) {
                App.lanzarEventoLoad();
                this.onRender();
            }

            return this;
        },

        serializarData: function () {
            var data;

            if (this.model) {
                data = this.model.toJSON();
            }

            return data;
        },

        compilarTemplate: function () {
            var $el = $(this.template);
            return _.template($el.html())
        }
    });

    return modelView;
});