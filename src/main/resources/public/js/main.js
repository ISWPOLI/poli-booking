require.config({
	paths : {
		text : '../bower_components/requirejs-text/text',
		jquery : '../bower_components/jquery/jquery.min',
		underscore : '../bower_components/underscore/underscore-min',
		backbone : '../bower_components/backbone/backbone',
		templates : '../templates',
		SessionManager : './SessionManager',

		UsuariosView : './views/administrador/usuarios/UsuariosView',
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
