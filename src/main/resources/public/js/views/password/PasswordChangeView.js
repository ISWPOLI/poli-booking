define([ 'jquery', 'underscore', 'backbone',
		'text!templates/password/password-change.html' ], function($, _,
		Backbone, cambioContrasenaTemplate) {

	var cambioContrasena = Backbone.View.extend({
		el : $("#page"),

		render : function() {

			$('.menu li').removeClass('active');
			$('.menu li a[href="#"]').parent().addClass('active');
			this.$el.html(cambioContrasenaTemplate);
		},

		events : {
			"click #cambioContrasenaButton" : "Cambio de Contrasena"
		},

		cambioContrasena : function() {
			var x = document.getElementById("pass")
			var y = document.getElementById("repass")
			alert(x)
		}

	});

	return cambioContrasena;

});
