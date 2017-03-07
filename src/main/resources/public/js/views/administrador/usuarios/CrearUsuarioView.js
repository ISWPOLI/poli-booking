define([ 'jquery', 'underscore', 'backbone',
		'text!templates/administrador/usuarios/crear-usuario.html',
		'UsuarioCollection', 'UsuarioModel', 'RolCollection' ], function($, _,
		Backbone, crearUsuarioTemplate, UsuarioCollection, UsuarioModel,
		RolCollection) {

	var crearUsuarioView = Backbone.View.extend({
		el : $("#page"),

		events : {
			"click #send" : "crearUsuario"
		},

		render : function() {
			$('.menu li').removeClass('active');
			$('.menu li a[href="#/users"]').parent().addClass('active');
			this.$el.html(crearUsuarioTemplate);
			this.fireLoad();
		},

		crearUsuario : function() {
			var that = this;

			var username = that.$el.find('#username').val();
			var password = that.$el.find('#password').val();
			var firstNames = that.$el.find('#first-names').val();
			var lastNames = that.$el.find('#last-names').val();
			var email = that.$el.find('#email').val();
			var role = that.$el.find('#role').val();

			var data = {
				username : username,
				password : password,
				fullName : firstNames + ' ' + lastNames,
				email : email,
				active : true
			};

			var roles = new RolCollection();
			roles.fetch({
				success : function(e) {
					var roleResource = e.where({
						type : role
					});
					var roleLink = roleResource[0].get('_links').self.href;
					data.roles = [ roleLink ];

					var usuario = new UsuarioModel(data);

					if (usuario.isValid()) {

						var usuarios = new UsuarioCollection([ usuario ]);
						usuarios.create(usuario, {
							success : function(data) {
								window.location.replace('/#/usuarios');
							}
						});
					} else {
						alert('Fallo de validación de datos requeridos');
					}
				}
			});

		}

	});

	return crearUsuarioView;

});