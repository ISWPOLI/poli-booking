define(['underscore', 'backbone', 'moment', 'App', 'MainLayout', 'HeaderView', 'FooterView',
        'ConsultarReservasCollectionView', 'HomeView', 'LoginView', 'PasswordRecoveryView', 'PasswordChangeView',
        'BibliotecaView', 'ComputadoresView', 'TenisView', 'UsuariosView', 'ConsultarUsuarioView', 'CrearUsuarioView',
        'ActualizarUsuarioView', 'EditarUsuarioView', 'EliminarUsuarioView', 'BloquesDisponiblesView', 'CanchasView',
        'GimnasioView', 'CanchaMultipleView', 'CanchaFutbolTenisView', 'CubiculoEstudioView', 'CubiculoVideoView',
        'ConfirmarReservaView', 'CalendarioEspacioView', 'GenerarBloquesView', 'EliminarBloquesView', 'PruebaGraficaView','PruebaHistoricoView','LaboratoriosView'],
    function (_, Backbone, moment, App, MainLayout, HeaderView, FooterView, ConsultarReservasCollectionView, HomeView,
              LoginView, PasswordRecoveryView, PasswordChangeView, BibliotecaView, ComputadoresView, TenisView,
              UsuariosView, ConsultarUsuarioView, CrearUsuarioView, ActualizarUsuarioView, EditarUsuarioView,
              EliminarUsuarioView, BloquesDisponiblesView, CanchasView, GimnasioView, CanchaMultipleView,
              CanchaFutbolTenisView, CubiculoEstudioView, CubiculoVideoView, ConfirmarReservaView,
              CalendarioEspacioView, GenerarBloquesView, EliminarBloquesView, PruebaGraficaView,PruebaHistoricoView,LaboratorioView) {

        var misReservasController = function (options) {
            var controlador = {
                region: options.region,
                mostrarMisReservas: function (collection) {
                    var reservasCollectionView = new ConsultarReservasCollectionView({
                        collection: collection
                    });

                    this.mostrarEnContent(reservasCollectionView);
                },

                mostrarHome: function () {
                    this.mostrarEnContent(new HomeView());
                },

                mostrarBiblioteca: function () {
                    this.mostrarEnContent(new BibliotecaView());
                },

                mostrarComputadores: function () {
                    this.mostrarEnContent(new ComputadoresView());
                },

                mostrarTenis: function () {
                    this.mostrarEnContent(new TenisView());
                },

                mostrarGimnasioView: function () {
                    this.mostrarEnContent(new GimnasioView());
                },

                mostrarCanchasView: function () {
                    this.mostrarEnContent(new CanchasView());
                },

                mostrarUsuarios: function () {
                    this.mostrarEnContent(new UsuariosView());
                },

                mostrarConsultarUsuario: function () {
                    this.mostrarEnContent(new ConsultarUsuarioView());
                },

                mostrarCrearUsuario: function () {
                    this.mostrarEnContent(new CrearUsuarioView());
                },

                mostrarActualizarUsuario: function () {
                    this.mostrarEnContent(new ActualizarUsuarioView());
                },

                mostrarEditarUsuario: function () {
                    this.mostrarEnContent(new EditarUsuarioView());
                },

                mostrarEliminarUsuario: function () {
                    this.mostrarEnContent(new EliminarUsuarioView());
                },

                mostrarBloquesDisponibles: function (fecha, tipoEspacio) {
                    var bloquesDisponiblesView = new BloquesDisponiblesView();
                    this.mostrarEnContent(bloquesDisponiblesView);
                    bloquesDisponiblesView.fechaSeleccionada = fecha;
                    bloquesDisponiblesView.buscarFechasDisponibles(fecha, tipoEspacio);
                },

                mostrarCanchaMultiple: function () {
                    this.mostrarEnContent(new CanchaMultipleView());
                },

                mostrarCanchaFutbolTenis: function () {
                    this.mostrarEnContent(new CanchaFutbolTenisView());
                },

                mostrarCubiculoEstudio: function () {
                    this.mostrarEnContent(new CubiculoEstudioView());
                },

                mostrarCubiculoVideo: function () {
                    this.mostrarEnContent(new CubiculoVideoView());
                },

                mostrarConfirmarReserva: function (fecha, idBloque) {
                    var vista = new ConfirmarReservaView();
                    vista.fecha = fecha;
                    vista.idBloque = idBloque;

                    var layout = this.armarLayoutBasico();
                    layout.getRegion('content').mostrar(vista);

                    vista.mostrarResumenReserva();
                },

                mostrarCalendarioEspacio: function (tipoEspacio) {
                    var that = this;
                    App.notificarInicioCargue();
                    Backbone.$.ajax({
                        url: '/bloques/bloques-vigentes-por-tipo-espacio',
                        method: 'GET',
                        data: {
                            "tipo-espacio": tipoEspacio
                        },
                        success: function (data) {
                            App.notificarFinCargue();

                            _.each(data, function (bloque) {
                                bloque.date = moment(bloque.dia).format("YYYY-MM-DD")
                            });

                            var vista = new CalendarioEspacioView();
                            vista.eventos = data;
                            vista.tipoEspacio = tipoEspacio;

                            var layout = that.armarLayoutBasico();
                            layout.getRegion('content').mostrar(vista);
                        },
                        error: function (jqxhr) {
                            App.notificarFinCargue();
                            App.mensajeError('Ocurrió un error cargando los bloques horarios disponibles');
                        }
                    });
                },

                mostrarPruebaGrafica: function(){
                	this.mostrarEnContent(new PruebaGraficaView());
                },
                mostrarHistoricoGrafica: function(){
                	this.mostrarEnContent(new PruebaHistoricoView());
                },
                
                mostrarLaboratorio: function(){
                	this.mostrarEnContent(new LaboratoriosView());
                },
                
                generarBloques: function () {
                    this.mostrarEnContent(new GenerarBloquesView());
                },

                eliminarBloques: function () {
                    this.mostrarEnContent(new EliminarBloquesView());
                },

                armarLayoutBasico: function () {
                    var layout = new MainLayout();
                    var headerView = new HeaderView();
                    var footerView = new FooterView();

                    this.region.mostrar(layout);
                    layout.getRegion('header').mostrar(headerView);
                    layout.getRegion('footer').mostrar(footerView);

                    return layout;
                },

                mostrarEnContent: function (vista) {
                    var layout = this.armarLayoutBasico();
                    layout.getRegion('content').mostrar(vista);
                }
            };
            _.extend(controlador, Backbone.Events);

            return controlador;
        };

        return misReservasController;
    });