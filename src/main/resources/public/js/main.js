require
    .config({
        paths: {
            text: '../bower_components/requirejs-text/text',
            jquery: '../bower_components/jquery/dist/jquery.min',
            underscore: '../bower_components/underscore/underscore-min',
            backbone: '../bower_components/backbone/backbone',
            backboneValidation: '/bower_components/backbone.validation/dist/backbone-validation-amd-min',
            App: './app',
            ReservasApp: './ReservasApp',
            material: '/bower_components/material-design-lite/material.min',
            sweetalert: '/bower_components/sweetalert/dist/sweetalert.min',
            noty: '/bower_components/noty/js/noty/packaged/jquery.noty.packaged',
            templates: '../templates',
            DefaultRouter: './DefaultRouter',
            ModelView: './views/common/ModelView',
            CollectionView: './views/common/CollectionView',
            Layout: './views/common/Layout',
            Region: './views/common/Region',
            SessionManager: './SessionManager',
            UsuariosView: './views/administrador/usuarios/UsuariosView',
            ConsultarUsuarioView: './views/administrador/usuarios/ConsultarUsuarioView',
            CrearUsuarioView: './views/administrador/usuarios/CrearUsuarioView',
            UsuarioModel: './models/usuario/UsuarioModel',
            UsuarioCollection: './collections/usuario/UsuarioCollection',
            RolModel: './models/usuario/RolModel',
            RolCollection: './collections/usuario/RolCollection',
            ConsultarReservasView: './views/reservas/ConsultarReservasView'
        }
    });

require(['app', 'ReservasRouter'], function (App, ReservasRouter) {
    App.start();
});
