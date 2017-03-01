define([ 'jquery', 'underscore', 'backbone', 'RolModel' ], function($, _,
		Backbone, RolModel) {
	var rolCollection = Backbone.Collection.extend({
		url : '/api/roles',
		model : RolModel,
		parse : function(response, xhr) {
			if (response && response._embedded && response._embedded.roles) {
				return response._embedded.roles;
			}
		}
	});

	return rolCollection;
});