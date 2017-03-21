define(['App', 'PublicController'],
    function (App, PublicController) {
        var publicApp = function (opciones) {
            return {
                region: opciones.region,

                mostrarLogin: function () {
                    App.notificarInicioCargue();

                    this.getControlador().mostrarLogin();

                    App.notificarFinCargue();
                },

                mostrarRecuperacionCuenta: function () {
                    App.notificarInicioCargue();

                    this.getControlador().mostrarRecuperacionCuenta();

                    App.notificarFinCargue();
                },

                mostrarCambioPassword: function () {
                    App.notificarInicioCargue();

                    this.getControlador().mostrarCambioPassword();

                    App.notificarFinCargue();
                },

                getControlador: function () {
                    return this.arrancarControlador(PublicController);
                },

                // TODO: mover este método para que se reutilizado por herencia en lugar de definirlo en cada app
                arrancarControlador: function (controlador) {
                    if (this.controladorActual &&
                        this.controladorActual instanceof controlador) {
                        return this.controladorActual;
                    }

                    if (this.controladorActual && this.controladorActual.destroy) {
                        this.controladorActual.destroy();
                    }

                    this.controladorActual = new controlador({region: this.region});
                    return this.controladorActual;
                }
            }
        };

        return publicApp;
    });