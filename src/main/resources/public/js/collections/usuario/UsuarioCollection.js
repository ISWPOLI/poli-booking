define([ 'jquery', 'underscore', 'backbone', 'UsuarioModel' ], function($, _,
		Backbone, UsuarioModel) {
	var UserCollection = Backbone.Collection.extend({
		url : '/api/users',
		model : UsuarioModel,
		parse : function(response, xhr) {
			if (response && response._embedded && response._embedded.users) {
				return response._embedded.users;
			}
		}
	});

	return UserCollection;
});