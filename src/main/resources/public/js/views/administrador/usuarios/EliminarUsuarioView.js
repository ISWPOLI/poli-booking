define([ 'jquery', 'underscore', 'backbone',
		'text!templates/administrador/usuarios/eliminar-usuario.html' ],
		function($, _, Backbone, eliminarUsuario) {

			var eliminarUsuarioView = Backbone.View.extend({
				el : $("#page"),

				render : function() {

					$('.menu li').removeClass('active');
					$('.menu li a[href="#"]').parent().addClass('active');
					this.$el.html(eliminarUsuario);
				}

			});

			return eliminarUsuarioView;

		});