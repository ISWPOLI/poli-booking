define([ 'jquery', 'underscore', 'backbone',
		'text!templates/administrador/usuarios/usuarios.html',
		'UsuarioCollection', 'UsuarioModel', 'RolCollection' ], function($, _,
		Backbone, usuariosTemplate) {

	var usuariosView = Backbone.View.extend({
		el : $("#page"),

		render : function() {
			$('.menu li').removeClass('active');
			$('.menu li a[href="#/usuarios"]').parent().addClass('active');
			this.$el.html(usuariosTemplate);
			this.fireLoad();
		}
	});

	return usuariosView;

});