require
    .config({
        paths: {
            // librerias externas
            text: '../bower_components/requirejs-text/text',
            jquery: '../bower_components/jquery/dist/jquery.min',
            underscore: '../bower_components/underscore/underscore-min',
            backbone: '../bower_components/backbone/backbone-min',
            backboneValidation: '/bower_components/backbone.validation/dist/backbone-validation-amd-min',
            material: '/bower_components/material-design-lite/material.min',
            sweetalert: '/bower_components/sweetalert/dist/sweetalert.min',
            noty: '/bower_components/noty/js/noty/packaged/jquery.noty.packaged',
            mdlJqueryModalDialog: '/bower_components/mdl-jquery-modal-dialog/mdl-jquery-modal-dialog',
            moment: '/bower_components/moment/min/moment-with-locales.min',
            clndr: '/bower_components/clndr/clndr.min',

            // aplicación general
            App: './app',
            templates: '../templates',
            DefaultRouter: './DefaultRouter',
            ModelView: './views/common/ModelView',
            CollectionView: './views/common/CollectionView',
            Layout: './views/common/Layout',
            Region: './views/common/Region',

            MainLayout: './views/main-layout/MainLayout',
            HeaderView: './views/header/HeaderView',
            HeaderTemplate: '../templates/header/header.html',
            FooterView: './views/footer/FooterView',
            FooterTemplate: '../templates/footer/footer.html',

            // public
            PublicApp: './PublicApp',
            PublicController: './PublicController',
            PublicRouter: './PublicRouter',
            LoginView: './views/login/LoginView',
            PasswordRecoveryView: './views/password/PasswordRecoveryView',
            PasswordChangeView: './views/password/PasswordChangeView',

            // usuarios
            UsuarioModel: './models/usuario/UsuarioModel',
            RolModel: './models/usuario/RolModel',
            UsuarioCollection: './collections/usuario/UsuarioCollection',
            RolCollection: './collections/usuario/RolCollection',
            UsuariosView: './views/administrador/usuarios/UsuariosView',
            ConsultarUsuarioView: './views/administrador/usuarios/ConsultarUsuarioView',
            CrearUsuarioView: './views/administrador/usuarios/CrearUsuarioView',
            ActualizarUsuarioView: './views/administrador/usuarios/ActualizarUsuarioView',
            EditarUsuarioView: './views/administrador/usuarios/EditarUsuarioView',
            EliminarUsuarioView: './views/administrador/usuarios/EliminarUsuarioView',
            PruebaGraficaView: './views/administrador/graficas/PruebaGraficaView',

            // reservas
            HomeView: './views/home/HomeView',
            ReservaApp: './ReservaApp',
            ReservaModel: './models/reserva/ReservaModel',
            ReservaCollection: './collections/reserva/ReservaCollection',
            ConsultarReservasModelView: './views/reservas/ConsultarReservasModelView',
            ConsultarReservasCollectionView: './views/reservas/ConsultarReservasCollectionView',
            ReservaController: './ReservaController',
            BibliotecaView: './views/reservas/biblioteca/BibliotecaView',
            ComputadoresView: './views/reservas/biblioteca/ComputadoresView',
            CubiculoEstudioView: './views/reservas/biblioteca/CubiculoEstudioView',
            CubiculoVideoView: './views/reservas/biblioteca/CubiculoVideoView',
            TenisView: './views/reservas/canchas/TenisView',
            CanchasView: './views/reservas/canchas/CanchasView',
            CanchaMultipleView: './views/reservas/canchas/CanchaMultipleView',
            CanchaFutbolTenisView: './views/reservas/canchas/CanchaFutbolTenisView',
            GimnasioView: './views/reservas/gimnasio/GimnasioView',
            BloquesDisponiblesView: './views/reservas/biblioteca/BloquesDisponiblesView',
            ConfirmarReservaView: './views/reservas/ConfirmarReservaView',
            EspaciosDisponiblesTemplate: '../templates/reservas/biblioteca/EspaciosDisponibles.html',

            //calendario
            CalendarioTemplate: '../templates/calendario/calendario.html',
            CalendarioEspacioView: './views/reservas/CalendarioEspacioView',

            // bloques
            GenerarBloquesView: './views/administrador/bloques/GenerarBloquesView'
        }
    });

require(['App', 'ReservaRouter', 'PublicRouter', 'text!CalendarioTemplate'], function (App, ReservasRouter, PublicRouter, CalendarioTemplate) {
    App.iniciar();
});
