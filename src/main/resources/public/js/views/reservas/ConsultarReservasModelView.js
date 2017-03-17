'use strict';
define(['jquery', 'underscore', 'backbone', 'App', 'ModelView',
        'text!templates/reservas/consultar-reservas.html'],
    function ($, _, Backbone, App, ModelView, consultarReservasTemplate) {
        var consultarReservasView = ModelView.extend({
            tagName: 'div',
            className: 'miReserva',
            template: function (data) {
                var compiled = _.template(consultarReservasTemplate);
                return compiled(data);
            },
            events: {
                'click button': 'cancelarReserva'
            },
            onRender: function () {
                $('.menu li').removeClass('active');
                $('.menu li a[href="#/mis-reservas"]').parent().addClass(
                    'active');
            },
            cancelarReserva: function () {
                var modeloActual = this.model;
                modeloActual.destroy({
                    success: function (model, response) {
                        App.notificarExito('La reserva se ha eliminado correctamente');
                    },
                    error: function () {
                        App.notificarError('Ha ocurrido un error cancelando la reserva');
                    }
                });
            }
        });

        return consultarReservasView;

    });
