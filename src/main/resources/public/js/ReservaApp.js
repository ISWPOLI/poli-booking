define(['App', 'ReservaCollection', 'MisReservasController'],
    function (App, ReservaCollection, MisReservasController) {
        var reservasApp = function (options) {
            return {
                region: options.region,

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
                    var reservasController = this.arrancarControlador(MisReservasController);
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