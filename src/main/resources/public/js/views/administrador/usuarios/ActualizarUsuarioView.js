define([ 'jquery', 'underscore', 'backbone',
		'text!templates/administrador/usuarios/actualizar-usuario.html' ],
		function($, _, Backbone, actualizarUsuario) {

			var actualizarUsuarioView = Backbone.View.extend({
				el : $("#page"),

				render : function() {

					$('.menu li').removeClass('active');
					$('.menu li a[href="#"]').parent().addClass('active');
					this.$el.html(actualizarUsuario);
				}

			});

			return actualizarUsuarioView;

		});