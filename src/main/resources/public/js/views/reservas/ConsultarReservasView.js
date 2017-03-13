'use strict';
define(['jquery', 'underscore', 'backbone', 'ModelView',
        'text!templates/reservas/consultar-reservas.html'],
    function ($, _, Backbone, ModelView, consultarReservasTemplate) {
        var consultarReservasView = ModelView.extend({
            template: function () {
                this.$el.html(consultarReservasTemplate);
            },
            onRender: function () {
                $('.menu li').removeClass('active');
                $('.menu li a[href="#/mis-reservas"]').parent().addClass(
                    'active');
            }
        });

        return consultarReservasView;

    });
