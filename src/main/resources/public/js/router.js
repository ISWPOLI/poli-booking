'use strict';
define([ 'jquery', 'underscore', 'backbone', , 'views/home/HomeView',
		'views/login/LoginView', 'views/password/PasswordRecoveryView',
		'views/password/PasswordChangeView', 'SessionManager','views/usuario/BibliotecaView' ], function($, _,
		Backbone, HomeView, LoginView, PasswordRecoveryView,
		PasswordChangeView, SessionManager, BibliotecaView) {

	var AppRouter = Backbone.Router.extend({
		routes : {
			'home' : 'home',
			'login' : 'login',
			'password-recovery' : 'password-recovery',
			'password-change' : 'password-change',
			'biblioteca' : 'biblioteca',
			'canchas':'canchas',
			'gimnasio':'gimnasio',
			'actualizarUsuario' : 'actualizarUsuario',
			'editarUsuario' : 'editarUsuario',
			'eliminarUsuario' : 'eliminarUsuario',
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
		
		app_router.on('route:canchas', function(actions) {
			if (SessionManager.checkAuthorization()) {
				var CanchasView = new canchasView();
				CanchasView.render();
			}
		});
		
		app_router.on('route:gimnasio', function(actions) {
			if (SessionManager.checkAuthorization()) {
				var GimnasioView = new gimnasioView();
				GimnasioView.render();
			}
		});

		app_router.on('route:actualizarUsuario', function(actions) {

			var ActualizarUsuarioView = new actualizarUsuarioView();
			ActualizarUsuarioView.render();
		});
		app_router.on('route:editarUsuario', function(actions) {

			var EditarUsuarioView = new editarUsuarioView();
			EditarUsuarioView.render();

		});
		app_router.on('route:eliminarUsuario', function(actions) {
			var EliminarUsuarioView = new eliminarUsuarioView();
			EliminarUsuarioView.render();

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
