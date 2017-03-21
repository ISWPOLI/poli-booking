define(['underscore', 'backbone', 'MainLayout', 'HeaderView', 'FooterView', 'LoginView', 'PasswordRecoveryView', 'PasswordChangeView'],
    function (_, Backbone, MainLayout, HeaderView, FooterView, LoginView, PasswordRecoveryView, PasswordChangeView) {
        var publicController = function (opciones) {
            var controlador = {
                region: opciones.region,

                mostrarLogin: function () {
                    this.mostrarEnContent(new LoginView());
                },

                mostrarRecuperacionCuenta: function () {
                    this.mostrarEnContent(new PasswordRecoveryView());
                },

                mostrarCambioPassword: function () {
                    this.mostrarEnContent(new PasswordChangeView());
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

        return publicController;
    }
);