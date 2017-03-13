'use strict';
define(['jquery', 'underscore', 'backbone', 'App', 'views/home/HomeView',
    'views/login/LoginView', 'views/password/PasswordRecoveryView',
    'views/password/PasswordChangeView', 'SessionManager',
    'views/reservas/biblioteca/BibliotecaView',
    'views/reservas/biblioteca/ComputadoresView',
    'views/reservas/canchas/TenisView', 'UsuariosView',
    'ConsultarUsuarioView', 'CrearUsuarioView',
    'views/administrador/usuarios/ActualizarUsuarioView',
    'views/administrador/usuarios/EditarUsuarioView',
    'views/administrador/usuarios/EliminarUsuarioView',
    'views/reservas/biblioteca/EspaciosDisponiblesView',
    'ConsultarReservasView'

], function ($, _, Backbone, App, HomeView, LoginView, PasswordRecoveryView,
             PasswordChangeView, SessionManager, BibliotecaView, ComputadoresView,
             TenisView, UsuariosView, ConsultarUsuarioView, CrearUsuarioView,
             ActualizarUsuarioView, EditarUsuarioView, EliminarUsuarioView,
             EspaciosDisponiblesView, ConsultarReservasView) {

    var reservasRouter = Backbone.Router.extend({
        routes: {
            'home': 'showHome',
            'login': 'showLogin',
            'password-recovery': 'showPasswordRecovery',
            'password-change': 'showPasswordChange',
            'biblioteca': 'showBiblioteca',
            'computadores': 'showComputadores',
            'tenis': 'showTenis',
            'gimnasio': 'gimnasio',
            'usuarios': 'showUsuarios',
            'consultar-usuario': 'showConsultarUsuario',
            'crear-usuario': 'showCrearUsuario',
            'actualizar-usuario': 'showActualizarUsuario',
            'editar-usuario': 'showEditarUsuario',
            'eliminar-usuario': 'showEliminarUsuario',
            'espacios-disponibles': 'showEspaciosDisponibles',
            'mis-reservas': 'showMisReservas',
            '*actions': 'defaultAction'
        },
        showHome: function () {
            if (SessionManager.checkAuthorization()) {
                var homeView = new HomeView();
                homeView.render();
            }
        },
        showLogin: function () {
            var loginView = new LoginView();
            loginView.render();
        },
        showPasswordRecovery: function () {
            var passwordRecoveryView = new PasswordRecoveryView();
            passwordRecoveryView.render();
        },
        showPasswordChange: function () {
            var passwordChangeView = new PasswordChangeView();
            passwordChangeView.render();
        },
        showBiblioteca: function () {
            if (SessionManager.checkAuthorization()) {
                var bibliotecaView = new BibliotecaView();
                bibliotecaView.render();
            }
        },
        showComputadores: function () {
            if (SessionManager.checkAuthorization()) {
                var computadoresView = new ComputadoresView();
                computadoresView.render();
            }
        },
        showUsuarios: function () {
            if (SessionManager.checkAuthorization()) {
                var usuariosView = new UsuariosView();
                usuariosView.render();
            }
        },
        showConsultarUsuario: function () {
            if (SessionManager.checkAuthorization()) {
                var consultarUsuarioView = new ConsultarUsuarioView();
                consultarUsuarioView.render();
            }
        },
        showCrearUsuario: function () {
            if (SessionManager.checkAuthorization()) {
                var crearUsuarioView = new CrearUsuarioView();
                crearUsuarioView.render();
            }
        },
        showActualizarUsuario: function () {
            if (SessionManager.checkAuthorization()) {
                var actualizarUsuarioView = new ActualizarUsuarioView();
                actualizarUsuarioView.render();
            }
        },
        showEditarUsuario: function () {
            var editarUsuarioView = new EditarUsuarioView();
            editarUsuarioView.render();
        },
        showEliminarUsuario: function () {
            if (SessionManager.checkAuthorization()) {
                var eliminarUsuarioView = new EliminarUsuarioView();
                eliminarUsuarioView.render();
            }
        },
        showTenis: function () {
            if (SessionManager.checkAuthorization()) {
                var tenisView = new TenisView();
                tenisView.render();
            }
        },
        showEspaciosDisponibles: function () {
            if (SessionManager.checkAuthorization()) {
                var espaciosDisponiblesView = new EspaciosDisponiblesView();
                espaciosDisponiblesView.render();
            }
        },
        showMisReservas: function () {
            if (SessionManager.checkAuthorization()) {
                var consultarReservasView = new ConsultarReservasView({
                    model: null,
                    el: '#page'
                });
                consultarReservasView.render();
            }
        },
        startApp: function () {
            var App = require('App');
            return App.startSubApplication(ReservasApp);
        }

    });

    App.Routers.ReservasRouter = reservasRouter;

    return reservasRouter;
});
