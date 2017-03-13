define(['jquery', 'underscore', 'backbone'], function ($, _, Backbone) {
    var modelView = Backbone.View.extend({
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
                this.onRender();
            }
            this.fireLoad();

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