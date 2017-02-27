'use strict';
define([ 'jquery', 'underscore', 'backbone', 'views/home/HomeView',
		'views/login/LoginView', 'views/password/PasswordRecoveryView',
		'views/password/PasswordChangeView', 'SessionManager' ], function($, _,
		Backbone, HomeView, LoginView, PasswordRecoveryView,
		PasswordChangeView, SessionManager) {

	var AppRouter = Backbone.Router.extend({
		routes : {
			'home' : 'home',
			'login' : 'login',
			'password-recovery' : 'password-recovery',
			'password-change' : 'password-change',
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
