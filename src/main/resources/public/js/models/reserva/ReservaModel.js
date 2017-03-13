define(['backbone'], function (Backbone) {
    var reservaModel = Backbone.Model.extend({
        urlRoot: '/reservas/mis-reservas',
        parse: function (data) {
            if (data.bloque.tiempoInicio) {
                data.bloque.tiempoInicio = new Date(data.bloque.tiempoInicio);
            }
            if (data.bloque.tiempoFin) {
                data.bloque.tiempoFin = new Date(data.bloque.tiempoFin);
            }
            if (data.bloque.dia) {
                data.bloque.dia = new Date(data.bloque.dia);
            }
            if (data.fechaReserva) {
                data.fechaReserva = new Date(data.fechaReserva);
            }
            return data;
        }
    });

    return reservaModel;
});