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

                mostrarHome: function () {
                    var reservasController = this.arrancarControlador(ReservaController);
                    reservasController.mostrarHome();
                },

                mostrarBiblioteca: function () {
                    var reservasController = this.arrancarControlador(ReservaController);
                    reservasController.mostrarBiblioteca();
                },

                mostrarComputadores: function () {
                    var reservasController = this.arrancarControlador(ReservaController);
                    reservasController.mostrarComputadores();
                },

                mostrarTenis: function () {
                    var reservasController = this.arrancarControlador(ReservaController);
                    reservasController.mostrarTenis();
                },

                mostrarGimnasioView: function () {
                    var reservasController = this.arrancarControlador(ReservaController);
                    reservasController.mostrarGimnasioView();
                },

                mostrarCanchasView: function () {
                    var reservasController = this.arrancarControlador(ReservaController);
                    reservasController.mostrarCanchasView();
                },

                mostrarUsuarios: function () {
                    var reservasController = this.arrancarControlador(ReservaController);
                    reservasController.mostrarUsuarios();
                },

                mostrarConsultarUsuario: function () {
                    var reservasController = this.arrancarControlador(ReservaController);
                    reservasController.mostrarConsultarUsuario();
                },

                mostrarCrearUsuario: function () {
                    var reservasController = this.arrancarControlador(ReservaController);
                    reservasController.mostrarCrearUsuario();
                },
                

                mostrarActualizarUsuario: function () {
                    var reservasController = this.arrancarControlador(ReservaController);
                    reservasController.mostrarActualizarUsuario();
                },

                mostrarEditarUsuario: function () {
                    var reservasController = this.arrancarControlador(ReservaController);
                    reservasController.mostrarEditarUsuario();
                },

                mostrarEliminarUsuario: function () {
                    var reservasController = this.arrancarControlador(ReservaController);
                    reservasController.mostrarEliminarUsuario();
                },

                mostrarBloquesDisponibles: function (fecha, tipoEspacio) {
                    var reservasController = this.arrancarControlador(ReservaController);
                    reservasController.mostrarBloquesDisponibles(fecha, tipoEspacio);
                },
                mostrarCanchaMultiple: function () {
                    var reservasController = this.arrancarControlador(ReservaController);
                    reservasController.mostrarCanchaMultiple();
                },

                mostrarCanchaFutbolTenis: function () {
                    var reservasController = this.arrancarControlador(ReservaController);
                    reservasController.mostrarCanchaFutbolTenis();
                },

                mostrarCubiculoEstudio: function () {
                    var reservasController = this.arrancarControlador(ReservaController);
                    reservasController.mostrarCubiculoEstudio();
                },

                mostrarCubiculoVideo: function () {
                    var reservasController = this.arrancarControlador(ReservaController);
                    reservasController.mostrarCubiculoVideo();
                },

                mostrarConfirmarReserva: function (fecha, idBloque) {
                    var reservasController = this.arrancarControlador(ReservaController);
                    reservasController.mostrarConfirmarReserva(fecha, idBloque);
                },

                mostrarCalendarioEspacio: function (tipoEspacio) {
                    var reservasController = this.arrancarControlador(ReservaController);
                    reservasController.mostrarCalendarioEspacio(tipoEspacio);
                },
                mostrarPruebaGrafica : function(){
                	 var reservasController = this.arrancarControlador(ReservaController);
                     reservasController.mostrarPruebaGrafica();
                },

                generarBloques: function () {
                    var reservasController = this.arrancarControlador(ReservaController);
                    reservasController.generarBloques();
                },

                generarBloques: function () {
                    var reservasController = this.arrancarControlador(ReservaController);
                    reservasController.generarBloques();
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