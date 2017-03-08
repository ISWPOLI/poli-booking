require
		.config({
			paths : {
				text : '../bower_components/requirejs-text/text',
				jquery : '../bower_components/jquery/dist/jquery.min',
				underscore : '../bower_components/underscore/underscore-min',
				backbone : '../bower_components/backbone/backbone',
				material: '/bower_components/material-design-lite/material.min',
				templates : '../templates',
				SessionManager : './SessionManager',
				UsuariosView : './views/administrador/usuarios/UsuariosView',
				ConsultarUsuarioView : './views/administrador/usuarios/ConsultarUsuarioView',
				CrearUsuarioView : './views/administrador/usuarios/CrearUsuarioView',
				UsuarioModel : './models/usuario/UsuarioModel',
				UsuarioCollection : './collections/usuario/UsuarioCollection',
				RolModel : './models/usuario/RolModel',
				RolCollection : './collections/usuario/RolCollection',
			}
		});

require([ 'app' ], function(App) {
	App.initialize();
});
