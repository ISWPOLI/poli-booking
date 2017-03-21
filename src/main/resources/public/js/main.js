require
    .config({
        paths: {
            // librerias externas
            text: '../bower_components/requirejs-text/text',
            jquery: '../bower_components/jquery/dist/jquery.min',
            underscore: '../bower_components/underscore/underscore-min',
            backbone: '../bower_components/backbone/backbone',
            backboneValidation: '/bower_components/backbone.validation/dist/backbone-validation-amd-min',
            material: '/bower_components/material-design-lite/material.min',
            sweetalert: '/bower_components/sweetalert/dist/sweetalert.min',
            noty: '/bower_components/noty/js/noty/packaged/jquery.noty.packaged',

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
            DrawerView: './views/drawer/DrawerView',
            DrawerTemplate: '../templates/drawer/drawer.html',
            FooterView: './views/footer/FooterView',
            FooterTemplate: '../templates/footer/footer.html',

            // public
            PublicApp: './PublicApp',
            PublicController: './PublicController',
            PublicRouter: './PublicRouter',
            LoginView: './views/login/LoginView',

            // usuarios
            UsuarioModel: './models/usuario/UsuarioModel',
            RolModel: './models/usuario/RolModel',
            UsuarioCollection: './collections/usuario/UsuarioCollection',
            RolCollection: './collections/usuario/RolCollection',
            UsuariosView: './views/administrador/usuarios/UsuariosView',
            ConsultarUsuarioView: './views/administrador/usuarios/ConsultarUsuarioView',
            CrearUsuarioView: './views/administrador/usuarios/CrearUsuarioView',

            // reservas
            ReservaApp: './ReservaApp',
            ReservaModel: './models/reserva/ReservaModel',
            ReservaCollection: './collections/reserva/ReservaCollection',
            ConsultarReservasModelView: './views/reservas/ConsultarReservasModelView',
            ConsultarReservasCollectionView: './views/reservas/ConsultarReservasCollectionView',
            ReservaController: './ReservaController'
        }
    });

require(['App', 'ReservaRouter', 'PublicRouter'], function (App, ReservasRouter, PublicRouter) {
    App.iniciar();
});
