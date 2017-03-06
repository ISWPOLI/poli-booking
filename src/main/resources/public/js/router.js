'use strict';
define([ 'jquery', 'underscore', 'backbone', 'views/home/HomeView',
		'views/login/LoginView', 'views/password/PasswordRecoveryView',
		'views/password/PasswordChangeView', 'SessionManager',
		'views/reservas/biblioteca/BibliotecaView',
		'views/reservas/biblioteca/ComputadoresView',
		'views/reservas/canchas/TenisView', 'UsuariosView',
		'ConsultarUsuarioView', 'CrearUsuarioView',
		'views/administrador/usuarios/ActualizarUsuarioView',
		'views/administrador/usuarios/EditarUsuarioView',
		'views/administrador/usuarios/EliminarUsuarioView'

], function($, _, Backbone, HomeView, LoginView, PasswordRecoveryView,
		PasswordChangeView, SessionManager, BibliotecaView, ComputadoresView,
		TenisView, UsuariosView, ConsultarUsuarioView, CrearUsuarioView,
		ActualizarUsuarioView, EditarUsuarioView, EliminarUsuarioView) {

	var AppRouter = Backbone.Router.extend({
		routes : {
			'home' : 'home',
			'login' : 'login',
			'password-recovery' : 'password-recovery',
			'password-change' : 'password-change',
			'biblioteca' : 'biblioteca',
			'computadores' : 'computadores',
			'tenis' : 'tenis',
			'gimnasio' : 'gimnasio',
			'usuarios' : 'usuarios',
			'consultar-usuario' : 'consultar-usuario',
			'crear-usuario' : 'crear-usuario',
			'actualizar-usuario' : 'actualizar-usuario',
			'editar-usuario' : 'editar-usuario',
			'eliminar-usuario' : 'eliminar-usuario',
			'*actions' : 'defaultAction'
		}
	});

	var initialize = function() {

		var app_router = new AppRouter;

		app_router.on('route:login', function(actions) {
			var loginView = new LoginView();
			loginView.render();
		});

		app_router.on('route:password-recovery', function(actions) {
			var passwordRecoveryView = new PasswordRecoveryView();
			passwordRecoveryView.render();
		});

		app_router.on('route:password-change', function(actions) {
			var passwordChangeView = new PasswordChangeView();
			passwordChangeView.render();
		});

		app_router.on('route:home', function(actions) {
			if (SessionManager.checkAuthorization()) {
				var homeView = new HomeView();
				homeView.render();
			}
		});

		app_router.on('route:biblioteca', function(actions) {
			if (SessionManager.checkAuthorization()) {
				var bibliotecaView = new BibliotecaView();
				bibliotecaView.render();
			}
		});

		app_router.on('route:computadores', function(actions) {
			if (SessionManager.checkAuthorization()) {
				var computadoresView = new ComputadoresView();
				computadoresView.render();
			}
		});

		app_router.on('route:usuarios', function(actions) {
			if (SessionManager.checkAuthorization()) {
				var usuariosView = new UsuariosView();
				usuariosView.render();
			}
		});
		app_router.on('route:consultar-usuario', function(actions) {
			if (SessionManager.checkAuthorization()) {
				var consultarUsuarioView = new ConsultarUsuarioView();
				consultarUsuarioView.render();
			}
		});
		app_router.on('route:crear-usuario', function(actions) {
			if (SessionManager.checkAuthorization()) {
				var crearUsuarioView = new CrearUsuarioView();
				crearUsuarioView.render();
			}
		});
		app_router.on('route:actualizar-usuario', function(actions) {
			if (SessionManager.checkAuthorization()) {
				var actualizarUsuarioView = new ActualizarUsuarioView();
				actualizarUsuarioView.render();
			}
		});
		app_router.on('route:editar-usuario', function(actions) {
			var editarUsuarioView = new EditarUsuarioView();
			editarUsuarioView.render();

		});
		app_router.on('route:eliminar-usuario', function(actions) {
			if (SessionManager.checkAuthorization()) {
				var eliminarUsuarioView = new EliminarUsuarioView();
				eliminarUsuarioView.render();
			}
		});

		app_router.on('route:tenis', function(actions) {
			if (SessionManager.checkAuthorization()) {
				var tenisView = new TenisView();
				tenisView.render();
			}
		});

		app_router.on('route:defaultAction', function(actions) {
			if (SessionManager.checkAuthorization()) {
				var homeView = new HomeView();
				homeView.render();
			}
		});

		Backbone.history.start();
	};
	return {
		initialize : initialize
	};
});
