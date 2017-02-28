'use strict';
define([ 'jquery', 'underscore', 'backbone' ], function($, _, Backbone) {

	var publicRoutes = [ 'password-change' ];

	var App = {
		initializeAuth : function() {
			var authConfig = sessionStorage.getItem('auth');

			if (!authConfig && !this.isPublicRoute()) {
				return this.redirectToLogin();
			} else if (authConfig) {
				var authElements = authConfig.split(':');
				var type = authElements[0];
				var token = authElements[1];

				this.setAuth(type, token);
			}
		},
		setAuth : function(type, token) {
			var authString = type + " " + token;
			this.setupAjax(authString);
		},
		setupAjax : function(authString) {
			var headers = {};

			if (authString) {
				headers = {
					Authorization : authString
				};
			}

			Backbone.$.ajaxSetup({
				statusCode : {
					401 : function() {
						return this.redirectToLogin();
					}
				},
				headers : headers
			});
		},
		saveAuth : function(type, token) {
			var authConfig = type + ":" + token;

			sessionStorage.setItem('auth', authConfig);
			this.setAuth(type, token);
		},
		isAuthenticated : function() {
			if (sessionStorage.getItem('auth')) {
				return true;
			} else {
				return false;
			}
		},
		redirectToLogin : function() {
			window.location.replace('#/login');
		},
		checkAuthorization : function() {
			if (this.isAuthenticated()) {
				return true;
			} else {
				return this.redirectToLogin();
			}
		},
		isPublicRoute : function() {
			var currentRoute = Backbone.history.getFragment();
			;

			return _.contains(publicRoutes, currentRoute);
		}
	}
	return App;
});
