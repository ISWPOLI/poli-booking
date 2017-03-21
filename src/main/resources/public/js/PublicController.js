define(['underscore', 'backbone', 'MainLayout', 'HeaderView', 'FooterView', 'LoginView'],
    function (_, Backbone, MainLayout, HeaderView, FooterView, LoginView) {
        var publicController = function (opciones) {
            var controlador = {
                region: opciones.region,

                mostrarLogin: function () {
                    var layout = new MainLayout();
                    var loginView = new LoginView();
                    var headerView = new HeaderView();
                    var footerView = new FooterView();

                    this.region.mostrar(layout);
                    layout.getRegion('header').mostrar(headerView);
                    layout.getRegion('content').mostrar(loginView);
                    layout.getRegion('footer').mostrar(footerView);
                },

                mostrarRecuperacionCuenta: function () {

                },

                mostrarCambioPassword: function () {

                }
            };
            _.extend(controlador, Backbone.Events);

            return controlador;
        };

        return publicController;
    }
);