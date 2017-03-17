define(['CollectionView', 'ReservaCollection', 'ConsultarReservasModelView'],
    function (CollectionView, ReservaCollection, ConsultarReservasModelView) {
        var consultarReservasCollectionView = CollectionView.extend({
            tagName: 'div',
            className: 'listaMisReservas',
            modelView: ConsultarReservasModelView
        });

        return consultarReservasCollectionView;
    });