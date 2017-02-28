define([ 'jquery', 'underscore', 'backbone',
		'text!templates/administrador/editarUsuario.html' ], function($, _,
		Backbone, editarUsuario) {

	var editarUsuarioView = Backbone.View.extend({
		el : $("#page"),

		render : function() {

			$('.menu li').removeClass('active');
			$('.menu li a[href="#"]').parent().addClass('active');
			this.$el.html(editarUsuario);
		}

	});

	return editarUsuarioView;

});
