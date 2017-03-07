define([ 'jquery', 'underscore', 'backbone',
		'text!templates/password/password-change.html' ], function($, _,
		Backbone, cambioContrasenaTemplate) {

	var cambioContrasena = Backbone.View.extend({
		el : $("#page"),

		render : function() {

			$('.menu li').removeClass('active');
			$('.menu li a[href="#"]').parent().addClass('active');
			this.$el.html(cambioContrasenaTemplate);
			this.fireLoad();
		},

		events : {
			"click #password-change-button" : "changePassword"
		},

		changePassword : function() {
			var that = this;

			var password = that.$el.find('#password').val();
			var confirmPassword = that.$el.find('#confirm-password').val();

			if (password !== confirmPassword) {
				alert('las contraseñas no coinciden');
				return;
			}

			Backbone.$.ajax({
				url : '/user/save-password',
				type : 'POST',
				data : {
					"new-password" : password
				},
				success : function() {
					window.location.replace('#/home');
				},
				error : function() {
					alert('Ha ocurrido un error');
				}
			});
		}

	});

	return cambioContrasena;

});
