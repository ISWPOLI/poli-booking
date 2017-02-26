'use strict';
define([ 'jquery', 'underscore', 'backbone', 'router', 'SessionManager' ],
		function($, _, Backbone, Router, SessionManager) {

			var App = {
				initialize : function() {
					Router.initialize();
					SessionManager.initializeAuth();
				}
			}
			return App;
		});
