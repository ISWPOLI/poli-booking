define([ 'jquery', 'underscore', 'backbone',
		'text!templates/usuario/canchas.html' ], function($, _, Backbone,
		canchas) {

	var canchasView = Backbone.View.extend({
		el : $("#page"),

		render : function() {

			$('.menu li').removeClass('active');
			$('.menu li a[href="#"]').parent().addClass('active');
			this.$el.html(canchas);
			this.fireLoad();
		}

	});

	return canchasView;

});
