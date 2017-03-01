define([ 'jquery', 'underscore', 'backbone', ], function($, _, Backbone) {
	var UserModel = Backbone.Model.extend({
		urlRoot : '/api/users',
		validate : function(data) {
			if (!data.username || !data.password || !data.fullName
					|| !data.email || !data.roles || !data.active) {
				return "Fallo de validación de campos requeridos";
			}
		}
	});

	return UserModel;
});