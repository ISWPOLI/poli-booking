'use strict';
define([ 'jquery', 'underscore', 'material', 'backbone', 'router',
		'SessionManager' ], function($, _, material, Backbone, Router,
		SessionManager) {

	var App = {
		initialize : function() {
			Backbone.View.prototype.fireLoad = function() {
				dispatchEvent(new Event('load'));
			};
			Router.initialize();
			SessionManager.initializeAuth();
		}
	}
	return App;
});
