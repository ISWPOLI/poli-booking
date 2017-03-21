define(['underscore', 'backbone', 'MainLayout', 'HeaderView', 'FooterView', 'ConsultarReservasCollectionView', 'HomeView'],
    function (_, Backbone, MainLayout, HeaderView, FooterView, ConsultarReservasCollectionView, HomeView) {
        var misReservasController = function (options) {
            var controlador = {
                region: options.region,
                mostrarMisReservas: function (collection) {
                    var reservasCollectionView = new ConsultarReservasCollectionView({
                        collection: collection
                    });

                    this.mostrarEnContent(reservasCollectionView);
                },

                mostrarHome: function () {
                    this.mostrarEnContent(new HomeView());
                },

                armarLayoutBasico: function () {
                    var layout = new MainLayout();
                    var headerView = new HeaderView();
                    var footerView = new FooterView();

                    this.region.mostrar(layout);
                    layout.getRegion('header').mostrar(headerView);
                    layout.getRegion('footer').mostrar(footerView);

                    return layout;
                },

                mostrarEnContent: function (vista) {
                    var layout = this.armarLayoutBasico();
                    layout.getRegion('content').mostrar(vista);
                }
            };
            _.extend(controlador, Backbone.Events);

            return controlador;
        };

        return misReservasController;
    });