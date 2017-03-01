define([ 'jquery', 'underscore', 'backbone', ], function($, _, Backbone) {
	var UserModel = Backbone.Model.extend({
		urlRoot : '/api/users',
		validate : function(data) {
			if (!data.username || !data.password || !data.fullName
					|| !data.email || !data.roles || !data.active) {
				return "Fallo de validación de campos requeridos";
			}
		},
		parse : function(data) {
			var self = data._links.self.href;
			var selfArray = self.split('/');
			var id = selfArray[selfArray.length - 1];
			data.id = id;
			return data;
		}
	});

	return UserModel;
});