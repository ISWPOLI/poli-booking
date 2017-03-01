define([ 'jquery', 'underscore', 'backbone',
		'text!templates/administrador/usuarios/consultar-usuario.html',
		'UsuarioCollection' ], function($, _, Backbone,
		consultarUsuarioTemplate, UsuarioCollection) {

	var consultarUsuarioView = Backbone.View.extend({
		el : $("#page"),

		events : {
			"click #sendConsultarDetalleUsuario" : "consultarDetalleUsuario"
		},

		render : function() {
			$('.menu li').removeClass('active');
			$('.menu li a[href="#/usuarios"]').parent().addClass('active');
			this.$el.html(consultarUsuarioTemplate);
		},

		consultarDetalleUsuario : function() {
			var that = this;
			var username = this.$el.find('#username').val();

			var usuarios = new UsuarioCollection();
			usuarios.fetch({
				success : function(e) {
					var user = e.where({
						username : username
					})[0];

					if (user) {

						var roleLink = user.get('_links').roles.href;

						Backbone.$.ajax({
							url : roleLink,
							success : function(roles) {
								that.$el.find('#full-name').val(
										user.get('username'));
								that.$el.find('#email').val(user.get('email'));
								that.$el.find('#role').val(
										roles._embedded.roles[0].type);
							}
						});
					} else {
						alert('usuario no encontrado');
					}
				}
			});
		}
	});

	return consultarUsuarioView;

});