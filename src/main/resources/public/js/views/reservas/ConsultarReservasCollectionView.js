define(['CollectionView', 'ReservaCollection', 'ConsultarReservasModelView'],
    function (CollectionView, ReservaCollection, ConsultarReservasModelView) {
        var consultarReservasCollectionView = CollectionView.extend({
            modelView: ConsultarReservasModelView
        });

        return consultarReservasCollectionView;
    });