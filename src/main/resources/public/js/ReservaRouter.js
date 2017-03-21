define(['jquery', 'underscore', 'backbone', 'App', 'ReservaApp'],
    function ($, _, Backbone, App, ReservasApp) {

        var reservasRouter = Backbone.Router.extend({
            routes: {
                'home': 'mostrarHome',
                'biblioteca': 'mostrarBiblioteca',
                'computadores': 'mostrarComputadores',
                'tenis': 'mostrarTenis',
                'gimnasio': 'mostrarGimnasioView',
                'canchas': 'mostrarCanchasView',
                'usuarios': 'mostrarUsuarios',
                'consultar-usuario': 'mostrarConsultarUsuario',
                'crear-usuario': 'mostrarCrearUsuario',
                'actualizar-usuario': 'mostrarActualizarUsuario',
                'editar-usuario': 'mostrarEditarUsuario',
                'eliminar-usuario': 'mostrarEliminarUsuario',
                'espacios-disponibles': 'mostrarEspaciosDisponibles',
                'cancha-multiple': 'mostrarCanchaMultiple',
                'cancha-futbolTenis': 'mostrarCanchaFutbolTenis',
                'cubiculo-estudio': 'mostrarCubiculoEstudio',
                'cubiculo-video': 'mostrarCubiculoVideo',
                'confirmar-reserva?fecha=:fecha&idBloque=:idBloque': 'mostrarConfirmarReserva',
                'mis-reservas': 'mostrarMisReservas'
            },

            mostrarHome: function () {
                var reservasApp = App.arrancarSubAplicacion(ReservasApp);
                this.procesarRuta(reservasApp.mostrarHome, reservasApp);
            },

            mostrarBiblioteca: function () {
                var reservasApp = App.arrancarSubAplicacion(ReservasApp);
                this.procesarRuta(reservasApp.mostrarBiblioteca, reservasApp);
            },

            mostrarComputadores: function () {
                var reservasApp = App.arrancarSubAplicacion(ReservasApp);
                this.procesarRuta(reservasApp.mostrarComputadores, reservasApp);
            },

            mostrarTenis: function () {
                var reservasApp = App.arrancarSubAplicacion(ReservasApp);
                this.procesarRuta(reservasApp.mostrarTenis, reservasApp);
            },

            mostrarGimnasioView: function () {
                var reservasApp = App.arrancarSubAplicacion(ReservasApp);
                this.procesarRuta(reservasApp.mostrarGimnasioView, reservasApp);
            },

            mostrarCanchasView: function () {
                var reservasApp = App.arrancarSubAplicacion(ReservasApp);
                this.procesarRuta(reservasApp.mostrarCanchasView, reservasApp);
            },

            mostrarUsuarios: function () {
                var reservasApp = App.arrancarSubAplicacion(ReservasApp);
                this.procesarRuta(reservasApp.mostrarUsuarios, reservasApp);
            },

            mostrarConsultarUsuario: function () {
                var reservasApp = App.arrancarSubAplicacion(ReservasApp);
                this.procesarRuta(reservasApp.mostrarConsultarUsuario, reservasApp);
            },

            mostrarCrearUsuario: function () {
                var reservasApp = App.arrancarSubAplicacion(ReservasApp);
                this.procesarRuta(reservasApp.mostrarCrearUsuario, reservasApp);
            },

            mostrarActualizarUsuario: function () {
                var reservasApp = App.arrancarSubAplicacion(ReservasApp);
                this.procesarRuta(reservasApp.mostrarActualizarUsuario, reservasApp);
            },

            mostrarEditarUsuario: function () {
                var reservasApp = App.arrancarSubAplicacion(ReservasApp);
                this.procesarRuta(reservasApp.mostrarEditarUsuario, reservasApp);
            },

            mostrarEliminarUsuario: function () {
                var reservasApp = App.arrancarSubAplicacion(ReservasApp);
                this.procesarRuta(reservasApp.mostrarEliminarUsuario, reservasApp);
            },

            mostrarEspaciosDisponibles: function () {
                var reservasApp = App.arrancarSubAplicacion(ReservasApp);
                this.procesarRuta(reservasApp.mostrarEspaciosDisponibles, reservasApp);
            },

            mostrarCanchaMultiple: function () {
                var reservasApp = App.arrancarSubAplicacion(ReservasApp);
                this.procesarRuta(reservasApp.mostrarCanchaMultiple, reservasApp);
            },

            mostrarCanchaFutbolTenis: function () {
                var reservasApp = App.arrancarSubAplicacion(ReservasApp);
                this.procesarRuta(reservasApp.mostrarCanchaFutbolTenis, reservasApp);
            },

            mostrarCubiculoEstudio: function () {
                var reservasApp = App.arrancarSubAplicacion(ReservasApp);
                this.procesarRuta(reservasApp.mostrarCubiculoEstudio, reservasApp);
            },

            mostrarCubiculoVideo: function () {
                var reservasApp = App.arrancarSubAplicacion(ReservasApp);
                this.procesarRuta(reservasApp.mostrarCubiculoVideo, reservasApp);
            },

            mostrarConfirmarReserva: function (fecha, idBloque) {
                var reservasApp = App.arrancarSubAplicacion(ReservasApp);

                if (App.verificarAutorizacion()) {
                    reservasApp.mostrarConfirmarReserva(fecha, idBloque);
                }
            },

            mostrarMisReservas: function () {
                var reservasApp = App.arrancarSubAplicacion(ReservasApp);
                this.procesarRuta(reservasApp.mostrarMisReservas, reservasApp);
            },

            procesarRuta: function (funcion, contexto, argumentos) {
                if (App.verificarAutorizacion()) {
                    funcion.apply(contexto, argumentos);
                }
            }
        });

        App.Routers.ReservasRouter = reservasRouter;

        return reservasRouter;
    });
