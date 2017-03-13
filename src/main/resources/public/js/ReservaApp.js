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
                    var reservasController = this.startController(MisReservasController);
                    reservasController.mostrarMisReservas(collection);
                },

                startController: function (controller) {
                    if (this.currentController &&
                        this.currentController instanceof controller) {
                        return this.currentController;
                    }

                    if (this.currentController && this.currentController.destroy) {
                        this.currentController.destroy();
                    }

                    this.currentController = new controller({region: this.region});
                    return this.currentController;
                }
            }
        };

        return reservasApp;
    });