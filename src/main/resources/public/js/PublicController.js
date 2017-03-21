define(['underscore', 'backbone', 'MainLayout', 'HeaderView', 'LoginView'],
    function (_, Backbone, MainLayout, HeaderView, LoginView) {
        var publicController = function (opciones) {
            var controlador = {
                region: opciones.region,

                mostrarLogin: function () {
                    var layout = new MainLayout();
                    var loginView = new LoginView();
                    var headerView = new HeaderView();

                    this.region.mostrar(layout);
                    layout.getRegion('header').mostrar(headerView);
                    layout.getRegion('content').mostrar(loginView);
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
    });