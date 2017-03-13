'use strict';
define(['jquery', 'underscore', 'backbone', 'ModelView',
        'text!templates/reservas/consultar-reservas.html'],
    function ($, _, Backbone, ModelView, consultarReservasTemplate) {
        var consultarReservasView = ModelView.extend({
            template: function (data) {
                var compiled = _.template(consultarReservasTemplate);
                return compiled(data);
            },
            onRender: function () {
                $('.menu li').removeClass('active');
                $('.menu li a[href="#/mis-reservas"]').parent().addClass(
                    'active');
            }
        });

        return consultarReservasView;

    });
