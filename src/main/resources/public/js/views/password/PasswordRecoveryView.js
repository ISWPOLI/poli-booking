define([ 'jquery', 'underscore', 'backbone',
		'text!templates/password/password-recovery.html' ], function($, _,
		Backbone, recuperacionDeCuentaTemplate) {

	var recuperacionDeCuenta = Backbone.View.extend({
		el : $("#page"),

		render : function() {

			$('.menu li').removeClass('active');
			$('.menu li a[href="#"]').parent().addClass('active');
			this.$el.html(recuperacionDeCuentaTemplate);
		},

		events : {
			"click #recuperacionDeCuentaButton" : "Recuperacion de la cuenta"
		},

		recuperacionDeCuenta : function() {
			var x = document.getElementById("pass")
			var y = document.getElementById("repass")
			alert(x)
		}

	});

	return recuperacionDeCuenta;

});
