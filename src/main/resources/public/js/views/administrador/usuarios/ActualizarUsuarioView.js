define(['jquery', 'underscore', 'backbone',
    'text!templates/administrador/usuarios/actualizar-usuario.html',
    'UsuarioCollection', 'RolCollection'], function ($, _, Backbone, actualizarUsuario,
                                                     UsuarioCollection, RolCollection) {

    var actualizarUsuarioView = Backbone.View.extend({
            el: $("#page"),

            usuarioSeleccionado: null,

            events: {
                "click #consultarUsuarioActualizacion": "consultarDetalleUsuario",
                "click #saveActualizarUsuario": "actualizarUsuario"
            },

            render: function () {

                $('.menu li').removeClass('active');
                $('.menu li a[href="#/usuarios"]').parent().addClass('active');
                this.$el.html(actualizarUsuario);
            },

            consultarDetalleUsuario: function () {
                var that = this;
                var username = this.$el.find('#username').val();

                var usuarios = new UsuarioCollection();
                usuarios.fetch({
                    success: function (e) {
                        var user = e.where({
                            username: username
                        })[0];
                        that.usuarioSeleccionado = user;

                        if (that.usuarioSeleccionado) {
                            var roleLink = user.get('_links').roles.href;

                            Backbone.$.ajax({
                                url: roleLink,
                                success: function (roles) {
                                    that.$el.find('#full-name').val(
                                        user.get('fullName'));
                                    that.$el.find('#email').val(user.get('email'));
                                    that.$el.find('#role').val(
                                        roles._embedded.roles[0].type);
                                }
                            });
                        } else {
                            alert('usuario no encontrado');
                        }
                    }
                });
            },

            actualizarUsuario: function () {
                var that = this;

                var username = that.$el.find('#username').val();
                var fullName = that.$el.find('#full-name').val();
                var email = that.$el.find('#email').val();
                var role = that.$el.find('#role').val();

                var roles = new RolCollection();
                roles.fetch({
                    success: function (e) {
                        var roleResource = e.where({
                            type: role
                        });
                        if (!roleResource[0]) {
                            alert('No se encontró el rol');
                            return;
                        }
                        var roleLink = roleResource[0].get('_links').self.href;

                        Backbone.$.ajax({
                            url: roleLink,
                            success: function (roles) {
                                that.usuarioSeleccionado.set({'fullName': fullName});
                                that.usuarioSeleccionado.set({'email': email});
                                that.usuarioSeleccionado.set({'roles': [roles._links.self.href]});

                                that.usuarioSeleccionado.save(that.usuarioSeleccionado.attributes, {
                                    patch: true,
                                    success: function () {
                                        window.location.replace('#/usuarios');
                                    },
                                    error: function () {
                                        alert('ocurrió un error');
                                    }
                                });
                            }

                        });
                    }

                });
            }

        })
        ;

    return actualizarUsuarioView;
})
;