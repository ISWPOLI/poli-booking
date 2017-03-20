define(['App', 'ReservaCollection', 'ReservaController'],
    function (App, ReservaCollection, ReservaController) {
        var reservasApp = function (opciones) {
            return {
                region: opciones.region,

                mostrarMisReservas: function () {
                    var that = this;
                    App.trigger('loading:start');

                    var reservaCollection = new ReservaCollection();
                    reservaCollection.fetch({
                        success: function (collection) {
                            that.mostrarMisReservasController(collection);
                            App.trigger('loading:stop');
                        },
                        fail: function (collection, response) {
                            App.notificarError("Ha ocurrido un error cargando las reservas");
                            App.trigger('loading:stop');
                        }
                    });
                },

                mostrarMisReservasController: function (collection) {
                    var reservasController = this.arrancarControlador(ReservaController);
                    reservasController.mostrarMisReservas(collection);
                },

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

        return reservasApp;
    });