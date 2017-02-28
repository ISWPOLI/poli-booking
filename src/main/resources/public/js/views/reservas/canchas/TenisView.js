define([ 'jquery', 'underscore', 'backbone',
		'text!templates/reservas/canchas/tenis.html' ], function($, _,
		Backbone, tenis) {

	var tenisView = Backbone.View.extend({
		el : $("#page"),

		render : function() {

			$('.menu li').removeClass('active');
			$('.menu li a[href="#"]').parent().addClass('active');
			this.$el.html(tenis);
		}

	});

	return tenisView;

});