define([ 'jquery', 'underscore', 'backbone',
		'text!templates/usuario/gimnasio.html' ], function($, _, Backbone,
		gimnasio) {

	var gimnasioView = Backbone.View.extend({
		el : $("#page"),

		render : function() {

			$('.menu li').removeClass('active');
			$('.menu li a[href="#"]').parent().addClass('active');
			this.$el.html(gimnasio);
		}

	});

	return gimnasioView;

});
