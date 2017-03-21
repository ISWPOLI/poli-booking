define(['jquery', 'underscore', 'backbone', 'App',
        'text!templates/administrador/usuarios/usuarios.html',
        'UsuarioCollection', 'UsuarioModel', 'RolCollection'],
    function ($, _, Backbone, App, usuariosTemplate) {

        var usuariosView = Backbone.View.extend({

            render: function () {
                $('.menu li').removeClass('active');
                $('.menu li a[href="#/usuarios"]').parent().addClass('active');
                this.$el.html(usuariosTemplate);
                App.lanzarEventoLoad();
            }
        });

        return usuariosView;

    });