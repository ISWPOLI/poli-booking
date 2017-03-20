define(['underscore', 'backbone', 'MainLayout', 'LoginView'],
    function (_, Backbone, MainLayout, LoginView) {
        var publicController = function (opciones) {
            var controlador = {
                region: opciones.region,

                mostrarLogin: function () {
                    var layout = new MainLayout();
                    var loginView = new LoginView();

                    this.region.mostrar(layout);
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