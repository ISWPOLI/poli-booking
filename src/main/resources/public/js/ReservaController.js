define(['underscore', 'backbone', 'MainLayout', 'HeaderView', 'FooterView', 'ConsultarReservasCollectionView', 'HomeView',
        'LoginView', 'PasswordRecoveryView', 'PasswordChangeView', 'BibliotecaView', 'ComputadoresView', 'TenisView',
        'UsuariosView', 'ConsultarUsuarioView', 'CrearUsuarioView', 'ActualizarUsuarioView', 'EditarUsuarioView',
        'EliminarUsuarioView', 'EspaciosDisponiblesView', 'CanchasView', 'GimnasioView', 'CanchaMultipleView',
        'CanchaFutbolTenisView', 'CubiculoEstudioView', 'CubiculoVideoView', 'ConfirmarReservaView'],
    function (_, Backbone, MainLayout, HeaderView, FooterView, ConsultarReservasCollectionView, HomeView, LoginView, PasswordRecoveryView,
              PasswordChangeView, BibliotecaView, ComputadoresView, TenisView,
              UsuariosView, ConsultarUsuarioView, CrearUsuarioView,
              ActualizarUsuarioView, EditarUsuarioView, EliminarUsuarioView,
              EspaciosDisponiblesView, CanchasView, GimnasioView, CanchaMultipleView,
              CanchaFutbolTenisView, CubiculoEstudioView, CubiculoVideoView, ConfirmarReservaView) {
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

                mostrarEspaciosDisponibles: function () {
                    var espaciosDisponiblesView = new EspaciosDisponiblesView();
                    this.mostrarEnContent(espaciosDisponiblesView);

                    var date = new Date();

                    var day = date.getDate();
                    var allMonth = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'];
                    var month = allMonth[date.getMonth()];
                    var year = date.getFullYear();
                    var fechaFormateada = '' + year + '-' + month + '-' + (day + 1) + '';
                    espaciosDisponiblesView.fechaSeleccionada = fechaFormateada;
                    espaciosDisponiblesView.buscarFechasDisponibles(fechaFormateada, "1");
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