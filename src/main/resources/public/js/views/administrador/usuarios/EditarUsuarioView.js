define(['jquery', 'underscore', 'backbone', 'App',
        'text!templates/administrador/usuarios/editar-usuario.html'],
    function ($, _, Backbone, App, editarUsuario) {

        var editarUsuarioView = Backbone.View.extend({


            render: function () {

                $('.menu li').removeClass('active');
                $('.menu li a[href="#"]').parent().addClass('active');
                this.$el.html(editarUsuario);
                App.lanzarEventoLoad();
            }

        });

        return editarUsuarioView;

    });
