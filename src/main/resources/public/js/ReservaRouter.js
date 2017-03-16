'use strict';
define([ 'jquery', 'underscore', 'backbone', 'App', 'views/home/HomeView',
		'views/login/LoginView', 'views/password/PasswordRecoveryView',
		'views/password/PasswordChangeView',
		'views/reservas/biblioteca/BibliotecaView',
		'views/reservas/biblioteca/ComputadoresView',
		'views/reservas/canchas/TenisView', 'UsuariosView',
		'ConsultarUsuarioView', 'CrearUsuarioView',
		'views/administrador/usuarios/ActualizarUsuarioView',
		'views/administrador/usuarios/EditarUsuarioView',
		'views/administrador/usuarios/EliminarUsuarioView',
		'views/reservas/biblioteca/EspaciosDisponiblesView',
		'views/reservas/canchas/CanchasView', 
		'views/reservas/gimnasio/GimnasioView',
		'views/reservas/canchas/CanchaMultipleView',
		'views/reservas/canchas/CanchaFutbolTenisView',
		'views/reservas/biblioteca/CubiculoEstudioView',
		'views/reservas/biblioteca/CubiculoVideoView',
		'ReservaApp'

], function($, _, Backbone, App, HomeView, LoginView, PasswordRecoveryView,
		PasswordChangeView, BibliotecaView, ComputadoresView, TenisView,
		UsuariosView, ConsultarUsuarioView, CrearUsuarioView,
		ActualizarUsuarioView, EditarUsuarioView, EliminarUsuarioView,
		EspaciosDisponiblesView, CanchasView, GimnasioView,CanchaMultipleView,
		CanchaFutbolTenisView, CubiculoEstudioView,CubiculoVideoView,ReservasApp) {

	var reservasRouter = Backbone.Router.extend({
		routes : {
			'home' : 'showHome',
			'login' : 'showLogin',
			'password-recovery' : 'showPasswordRecovery',
			'password-change' : 'showPasswordChange',
			'biblioteca' : 'showBiblioteca',
			'computadores' : 'showComputadores',
			'tenis' : 'showTenis',
			'gimnasio' : 'showGimnasioView',
			'canchas' : 'showCanchasView',
			'usuarios' : 'showUsuarios',
			'consultar-usuario' : 'showConsultarUsuario',
			'crear-usuario' : 'showCrearUsuario',
			'actualizar-usuario' : 'showActualizarUsuario',
			'editar-usuario' : 'showEditarUsuario',
			'eliminar-usuario' : 'showEliminarUsuario',
			'espacios-disponibles' : 'showEspaciosDisponibles',
			'cancha-multiple': 'showCanchaMultiple',
			'cancha-futbolTenis': 'showCanchaFutbolTenis',
			'cubiculo-estudio': 'showCubiculoEstudio',
			'cubiculo-video': 'showCubiculoVideo',
			'mis-reservas' : 'mostrarMisReservas'
		},
		showHome : function() {
			if (App.verificarAutorizacion()) {
				var homeView = new HomeView();
				homeView.render();
			}
		},
		showLogin : function() {
			var loginView = new LoginView();
			loginView.render();
		},
		showPasswordRecovery : function() {
			var passwordRecoveryView = new PasswordRecoveryView();
			passwordRecoveryView.render();
		},
		showPasswordChange : function() {
			var passwordChangeView = new PasswordChangeView();
			passwordChangeView.render();
		},
		showBiblioteca : function() {
			if (App.verificarAutorizacion()) {
				var bibliotecaView = new BibliotecaView();
				bibliotecaView.render();
			}
		},
		showComputadores : function() {
			if (App.verificarAutorizacion()) {
				var computadoresView = new ComputadoresView();
				computadoresView.render();
			}
		},
		showUsuarios : function() {
			if (App.verificarAutorizacion()) {
				var usuariosView = new UsuariosView();
				usuariosView.render();
			}
		},
		showConsultarUsuario : function() {
			if (App.verificarAutorizacion()) {
				var consultarUsuarioView = new ConsultarUsuarioView();
				consultarUsuarioView.render();
			}
		},
		showCrearUsuario : function() {
			if (App.verificarAutorizacion()) {
				var crearUsuarioView = new CrearUsuarioView();
				crearUsuarioView.render();
			}
		},
		showActualizarUsuario : function() {
			if (App.verificarAutorizacion()) {
				var actualizarUsuarioView = new ActualizarUsuarioView();
				actualizarUsuarioView.render();
			}
		},
		showEditarUsuario : function() {
			var editarUsuarioView = new EditarUsuarioView();
			editarUsuarioView.render();
		},
		showEliminarUsuario : function() {
			if (App.verificarAutorizacion()) {
				var eliminarUsuarioView = new EliminarUsuarioView();
				eliminarUsuarioView.render();
			}
		},
		showTenis : function() {
			if (App.verificarAutorizacion()) {
				var tenisView = new TenisView();
				tenisView.render();
			}
		},
		showEspaciosDisponibles : function() {
			if (App.verificarAutorizacion()) {
				var espaciosDisponiblesView = new EspaciosDisponiblesView();
				espaciosDisponiblesView.render();
			}
		},
		mostrarMisReservas : function() {
			var reservasApp = App.arrancarSubAplicacion(ReservasApp);
			this.procesarRuta(reservasApp.mostrarMisReservas, reservasApp);
		},
		showCanchasView : function() {
			if (App.verificarAutorizacion()) {
				var canchasView = new CanchasView();
				canchasView.render();
			}
		},
		showGimnasioView: function(){
			if(App.verificarAutorizacion()){
				var gimnasioView= new GimnasioView();
				gimnasioView.render();
			}
		},
		
		showCanchaMultiple: function(){
			if(App.verificarAutorizacion()){
				var canchaMultipleView=new CanchaMultipleView();
				canchaMultipleView.render();
			}
		},
		
		showCanchaFutbolTenis : function(){
			if(App.verificarAutorizacion()){
				var canchaFutbolTenisView= new CanchaFutbolTenisView();
				canchaFutbolTenisView.render();
			}
		},
		
		showCubiculoEstudio : function(){
			if(App.verificarAutorizacion()){
				var cubiculoEstudioView= new CubiculoEstudioView();
				cubiculoEstudioView.render();
			}
		},
		
		showCubiculoVideo : function(){
			if(App.verificarAutorizacion()){
				var cubiculoVideoView= new CubiculoVideoView();
				cubiculoVideoView.render();
			}
		},
		
		
		
		procesarRuta : function(funcion, contexto, argumentos) {
			if (App.verificarAutorizacion()) {
				funcion.apply(contexto, argumentos);
			}
		},
		startApp : function() {
			return App.arrancarSubAplicacion(ReservasApp);
		}
	});

	App.Routers.ReservasRouter = reservasRouter;

	return reservasRouter;
});
