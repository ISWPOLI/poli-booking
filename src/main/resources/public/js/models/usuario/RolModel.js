define([ 'jquery', 'underscore', 'backbone', ], function($, _, Backbone) {
	var rolModel = Backbone.Model.extend({
		urlRoot : '/api/roles'
	});

	return rolModel;
});