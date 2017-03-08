define(
		[ 'jquery', 'underscore', 'backbone',
				'text!templates/password/password-recovery.html' ],
		function($, _, Backbone, recuperacionDeCuentaTemplate) {

			var recuperacionDeCuenta = Backbone.View
					.extend({
						el : $("#page"),

						render : function() {
							this.$el.html(recuperacionDeCuentaTemplate);
							this.fireLoad();
						},

						events : {
							"click #recuperacionDeCuentaButton" : "recuperacionDeCuenta"
						},

						recuperacionDeCuenta : function() {
							var that = this;

							var email = that.$el.find('#email').val();

							var data = {
								email : email
							};

							Backbone.$
									.ajax({
										url : '/user/reset-password',
										type : 'POST',
										data : data,
										success : function() {
											that.showConfirmationMessage();
										},
										error : function(jqxhr) {

											that
													.showErrorMessage('No se encuentra el correo');

										}
									});
						},

						showConfirmationMessage : function() {
							this.clearMessages();
							this.$el
									.find('#token_sent_confirmation')
									.html(
											"Se ha enviado un mensaje al correo suministrado para restablecer la contraseña");
						},

						showErrorMessage : function(message) {
							this.clearMessages();
							this.$el.find('#token_error').html(message);
						},

						clearMessages : function() {
							this.$el.find('#token_sent_confirmation').html('');
							this.$el.find('#token_error').html('');
						}
					});

			return recuperacionDeCuenta;

		});
