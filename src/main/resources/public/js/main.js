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
            MisReservasController: './MisReservasController'
        }
    });

require(['App', 'ReservaRouter'], function (App, ReservasRouter) {
    App.iniciar();
});
