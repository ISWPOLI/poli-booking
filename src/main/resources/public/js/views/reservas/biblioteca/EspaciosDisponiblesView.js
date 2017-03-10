define([ 'jquery', 'underscore', 'backbone',
		'text!templates/reservas/biblioteca/EspaciosDisponibles.html' ], function($, _, Backbone,
		EspaciosDisponibles) {

	var EspaciosDisponiblesView = Backbone.View.extend({
		el : $("#page"),

		render : function() {
			$('.menu li').removeClass('active');
			$('.menu li a[href="#"]').parent().addClass('active');
			this.$el.html(EspaciosDisponibles);
			this.fireLoad();
		}
	});

	return EspaciosDisponiblesView;

});
