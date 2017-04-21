define(['jquery', 'underscore', 'App', 'ModelView', 'text!HeaderTemplate'],
    function ($, _, App, ModelView, HeaderTemplate) {
        var headerView = ModelView.extend({
            template: function (data) {
                var compiled = _.template(HeaderTemplate);
                return compiled(data);
            },
            enlacesPublicos: [],
            enlacesPrivados: [{
                href: '#/home',
                valor: 'Inicio'
            }, {
                href: '#/usuarios',
                valor: 'Usuarios',
                rol: 'ROLE_ADMIN'
            }, {
                href: '#/generar-bloques',
                valor: 'Generar Bloques',
                rol: 'ROLE_ADMIN'
            }, {
                href: '#/eliminar-bloques',
                valor: 'Eliminar Bloques',
                rol: 'ROLE_ADMIN'
            }, {
                href: '#/mis-reservas',
                valor: 'Mis Reservas',
                rol: 'ROLE_STUDENT'
            }, {
                href: '#/logout',
                valor: 'Cerrar Sesión'
            }],
            onRender: function () {
                var that = this;
                var listaEnlaces = that.$el.find('#poli-booking-navigation-bar');

                this.recalcularEnlaces();
            },
            calcularEnlacesPermitidos: function () {
                var roles = App.getRoles();
                var enlaces = new Array();
                _.each(this.enlacesPublicos, function (enlace) {
                    enlaces.push(enlace);
                });
                if (App.estaAutenticado()) {
                    _.each(this.enlacesPrivados, function (enlace) {
                        if (enlace.rol) {
                            if (_.contains(roles, enlace.rol)) {
                                enlaces.push(enlace);
                            }
                        } else {
                            enlaces.push(enlace);
                        }
                    });
                }
                return enlaces;
            },
            recalcularEnlaces: function () {
                var listaEnlaces = this.$el.find('#poli-booking-navigation-bar');
                this.borrarEnlaces();
                var enlaces = this.calcularEnlacesPermitidos();

                _.each(enlaces, function (enlace) {
                    var anchor = $('<a>', {
                        text: enlace.valor,
                        title: enlace.valor,
                        href: enlace.href,
                        class: 'mdl-navigation__link'
                    });
                    listaEnlaces.append(anchor);
                });

            },
            borrarEnlaces: function () {
                var listaEnlaces = this.$el.find('#poli-booking-navigation-bar');
                listaEnlaces.empty();
            }

        });

        return headerView;
    });