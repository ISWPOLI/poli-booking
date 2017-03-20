define(['underscore', 'backbone', 'ConsultarReservasCollectionView'], function (_, Backbone, ConsultarReservasCollectionView) {
    var misReservasController = function (options) {
        var controlador = {
            region: options.region,
            mostrarMisReservas: function (collection) {
                var reservasCollectionView = new ConsultarReservasCollectionView({
                    collection: collection
                });

                this.region.mostrar(reservasCollectionView);
            }
        };
        _.extend(controlador, Backbone.Events);

        return controlador;
    };

    return misReservasController;
});