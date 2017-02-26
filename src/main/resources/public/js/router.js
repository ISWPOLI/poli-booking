// Filename: router.js
define([ 'jquery', 'underscore', 'backbone', 'views/home/HomeView',
		'views/login/LoginView', 'views/password/PasswordRecoveryView',
		'views/password/PasswordChangeView' ], function($, _, Backbone,
		HomeView, LoginView, PasswordRecoveryView, PasswordChangeView) {

	var AppRouter = Backbone.Router.extend({
		routes : {
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

		app_router.on('route:defaultAction', function(actions) {
			var homeView = new HomeView();
			homeView.render();
		});

		Backbone.history.start();
	};
	return {
		initialize : initialize
	};
});
