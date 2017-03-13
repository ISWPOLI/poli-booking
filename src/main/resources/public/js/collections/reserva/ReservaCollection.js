define(['backbone', 'ReservaModel'], function (Backbone, ReservaModel) {
    var reservaCollection = Backbone.Collection.extend({
        url: '/reservas/mis-reservas',
        model: ReservaModel
    });

    return reservaCollection;
});