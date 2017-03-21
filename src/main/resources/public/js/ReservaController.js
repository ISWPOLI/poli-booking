define(['underscore', 'backbone', 'MainLayout', 'HeaderView', 'FooterView', 'ConsultarReservasCollectionView', 'HomeView',
        'LoginView', 'PasswordRecoveryView', 'PasswordChangeView', 'BibliotecaView', 'ComputadoresView', 'TenisView',
        'UsuariosView', 'ConsultarUsuarioView', 'CrearUsuarioView', 'ActualizarUsuarioView', 'EditarUsuarioView',
        'EliminarUsuarioView', 'EspaciosDisponiblesView', 'CanchasView', 'GimnasioView', 'CanchaMultipleView',
        'CanchaFutbolTenisView', 'CubiculoEstudioView', 'CubiculoVideoView'],
    function (_, Backbone, MainLayout, HeaderView, FooterView, ConsultarReservasCollectionView, HomeView, LoginView, PasswordRecoveryView,
              PasswordChangeView, BibliotecaView, ComputadoresView, TenisView,
              UsuariosView, ConsultarUsuarioView, CrearUsuarioView,
              ActualizarUsuarioView, EditarUsuarioView, EliminarUsuarioView,
              EspaciosDisponiblesView, CanchasView, GimnasioView, CanchaMultipleView,
              CanchaFutbolTenisView, CubiculoEstudioView, CubiculoVideoView) {
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
                    this.mostrarEnContent(new EspaciosDisponiblesView());
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