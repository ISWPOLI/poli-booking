define([ 'jquery', 'underscore', 'backbone',
		'text!templates/reservas/biblioteca/computadores.html' ], function($,
		_, Backbone, computadores) {

	var computadoresView = Backbone.View.extend({
		el : $("#page"),

		render : function() {

			$('.menu li').removeClass('active');
			$('.menu li a[href="#"]').parent().addClass('active');
			this.$el.html(computadores);
			this.fireLoad();
		}

	});

	return computadoresView;

});