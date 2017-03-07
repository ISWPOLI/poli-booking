define([ 'jquery', 'underscore', 'backbone',
		'text!templates/reservas/biblioteca/biblioteca.html' ], function($, _,
		Backbone, biblioteca) {

	var bibliotecaView = Backbone.View.extend({
		el : $("#page"),

		render : function() {

			$('.menu li').removeClass('active');
			$('.menu li a[href="#"]').parent().addClass('active');
			this.$el.html(biblioteca);
			this.fireLoad();
		}

	});

	return bibliotecaView;

});