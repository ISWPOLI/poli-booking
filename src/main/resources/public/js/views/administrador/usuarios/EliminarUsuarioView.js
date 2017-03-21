define(['jquery', 'underscore', 'backbone', 'App',
        'text!templates/administrador/usuarios/eliminar-usuario.html',
        'UsuarioCollection'],
    function ($, _, Backbone, App, eliminarUsuario, UsuarioCollection) {

        var eliminarUsuarioView = Backbone.View.extend({


            events: {
                "click #boton-eliminar-usuario": "eliminarUsuario"
            },

            render: function () {
                $('.menu li').removeClass('active');
                $('.menu li a[href="#"]').parent().addClass('active');
                this.$el.html(eliminarUsuario);
                App.lanzarEventoLoad();
            },

            eliminarUsuario: function () {
                var username = this.$el.find('#username').val();

                if (!username) {
                    alert('el usuario es requerido');
                    return;
                }

                var usuarios = new UsuarioCollection();
                usuarios.fetch({
                    success: function (e) {

                        var usuarioParaBorrar = e.where({
                            username: username
                        });
                        if (usuarioParaBorrar && usuarioParaBorrar.length > 0) {
                            usuarioParaBorrar[0].destroy({
                                success: function () {
                                    alert('usuario eliminado exitosamente');
                                }
                            });
                        } else {
                            alert('usuario no encontrado');
                        }

                    }
                });
            }

        });

        return eliminarUsuarioView;

    });