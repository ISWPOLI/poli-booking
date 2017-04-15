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
                'bloques-disponibles?fecha=:fecha&tipoEspacio=:tipoEspacio': 'mostrarBloquesDisponibles',
                'cancha-multiple': 'mostrarCanchaMultiple',
                'cancha-futbolTenis': 'mostrarCanchaFutbolTenis',
                'cubiculo-estudio': 'mostrarCubiculoEstudio',
                'cubiculo-video': 'mostrarCubiculoVideo',
                'confirmar-reserva?fecha=:fecha&idBloque=:idBloque': 'mostrarConfirmarReserva',
                'mis-reservas': 'mostrarMisReservas',
                'calendario-espacio?tipo-espacio=:tipoEspacio': 'mostrarCalendarioEspacio',
                'prueba-grafica':'mostrarPruebaGrafica',
                'generar-bloques': 'generarBloques'
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

            mostrarBloquesDisponibles: function (fecha, tipoEspacio) {
                var reservasApp = App.arrancarSubAplicacion(ReservasApp);
                this.procesarRuta(reservasApp.mostrarBloquesDisponibles, reservasApp, [fecha, tipoEspacio]);
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

            mostrarCalendarioEspacio: function (tipoEspacio) {
                var reservasApp = App.arrancarSubAplicacion(ReservasApp);
                this.procesarRuta(reservasApp.mostrarCalendarioEspacio, reservasApp, [tipoEspacio]);
            },
            
            mostrarPruebaGrafica: function(){
            	var reservasApp = App.arrancarSubAplicacion(ReservasApp);
                this.procesarRuta(reservasApp.mostrarPruebaGrafica, reservasApp);
            },

            generarBloques: function () {
                var reservasApp = App.arrancarSubAplicacion(ReservasApp);
                this.procesarRuta(reservasApp.generarBloques, reservasApp);
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
