define(['jquery', 'underscore', 'material', 'backbone', 'backboneValidation', 'sweetalert', 'noty',
        'mdlJqueryModalDialog', 'moment', 'clndr', 'DefaultRouter', 'Region'],
    function ($, _, material, Backbone, BackboneValidation, sweetAlert, Noty, mdlJqueryModalDialog, moment, clndr,
              DefaultRouter, Region) {
        var rutasPublicas = ['password-change'];
        var app = {
            Models: {},
            Collections: {},
            Routers: {},
            iniciar: function () {
                _.each(_.values(this.Routers), function (Router) {
                    new Router();
                });

                this.mainRegion = new Region({el: '#poli-booking-main-region'});

                this.router = new DefaultRouter();

                Backbone.history.start();

                this.inicializarAutenticacion();

                this.on('loading:start', function () {
                    showLoading();
                });
                this.on('loading:stop', function () {
                    hideLoading();
                });
            },

            arrancarSubAplicacion: function (subAplicacion) {
                var that = this;
                if (that.subaplicacionActual && that.subaplicacionActual.destroy) {
                    this.subaplicacionActual.destroy();
                }

                that.subaplicacionActual = new subAplicacion({region: that.mainRegion});
                return that.subaplicacionActual;
            },

            mensajeExitoso: function (mensaje) {
                var opciones = {
                    title: 'Operación exitosa',
                    type: 'success',
                    text: mensaje,
                    confirmButtonText: 'OK'
                };

                sweetAlert(opciones);
            },

            mensajeError: function (mensaje) {
                var opciones = {
                    title: 'Error',
                    type: 'error',
                    text: mensaje,
                    confirmButtonText: 'OK'
                };

                sweetAlert(opciones);
            },

            pedirConfirmacion: function (mensaje, callback) {
                var opciones = {
                    title: '¿estás seguro?',
                    type: 'warning',
                    text: mensaje,
                    showCancelButton: true,
                    confirmButtonText: 'Sí',
                    confirmButtonColor: '#5cb85c',
                    cancelButtonText: 'No'
                };

                sweetAlert(opciones, function (confirmacion) {
                    callback(confirmacion);
                });
            },

            notificarExito: function (mensaje) {
                new Noty({
                    text: mensaje,
                    layout: 'topRight',
                    theme: 'relax',
                    type: 'success',
                    timeout: 3000
                });
            },

            notificarError: function (mensaje) {
                new Noty({
                    text: mensaje,
                    layout: 'topRight',
                    theme: 'relax',
                    type: 'error',
                    timeout: 3000
                });
            },

            inicializarAutenticacion: function () {
                var autenticacionPersistida = sessionStorage.getItem('auth');

                if (!autenticacionPersistida && !this.esRutaPublica()) {
                    return this.redirigirRutaLogin();
                } else if (autenticacionPersistida) {
                    var elementosAutenticacion = autenticacionPersistida.split(':');
                    var tipo = elementosAutenticacion[0];
                    var token = elementosAutenticacion[1];

                    this.configurarAutenticacion(tipo, token);
                }
            },
            configurarAutenticacion: function (tipo, token) {
                var cadenaAutenticacion = tipo + " " + token;
                this.configurarAutenticacionAjax(cadenaAutenticacion);
            },
            configurarAutenticacionAjax: function (cadenaAutenticacion) {
                var that = this;
                var headers = {};

                if (cadenaAutenticacion) {
                    headers = {
                        Authorization: cadenaAutenticacion
                    };
                }

                Backbone.$.ajaxSetup({
                    statusCode: {
                        401: function () {
                            return that.redirigirRutaLogin();
                        }
                    },
                    headers: headers
                });
            },
            guardarAutenticacion: function (tipo, token) {
                var cadenaAutenticacion = tipo + ":" + token;

                sessionStorage.setItem('auth', cadenaAutenticacion);
                this.configurarAutenticacion(tipo, token);
            },
            limpiarAutenticacion: function () {
                sessionStorage.removeItem('auth');
                sessionStorage.removeItem('authorities');
            },
            guardarRoles: function (authorities) {
                sessionStorage.setItem('authorities', JSON.stringify(authorities));
            },
            getRoles: function () {
                var authorities = sessionStorage.getItem('authorities');
                var roles = new Array();
                if (authorities) {
                    _.each(JSON.parse(authorities), function (authority) {
                        roles.push(authority.authority);
                    });
                }
                return roles;
            },
            logout: function () {
                var that = this;
                Backbone.$.ajax({
                    method: 'GET',
                    url: '/logout',
                    success: function (data) {
                        that.limpiarAutenticacion();
                        that.redirigirRutaLogin();
                    },
                    error: function (jqxhr) {
                        that.limpiarAutenticacion();
                        that.redirigirRutaLogin();
                    }
                });
            },
            estaAutenticado: function () {
                if (sessionStorage.getItem('auth')) {
                    return true;
                } else {
                    return false;
                }
            },
            redirigirRutaLogin: function () {
                window.location.replace('#/login');
            },
            verificarAutorizacion: function () {
                if (this.estaAutenticado()) {
                    return true;
                } else if (!this.esRutaPublica()) {
                    return this.redirigirRutaLogin();
                }
            },
            esRutaPublica: function () {
                var rutaActual = Backbone.history.getFragment();

                return _.contains(rutasPublicas, rutaActual);
            },

            lanzarEventoLoad: function () {
                dispatchEvent(new Event('load'));
            },

            notificarInicioCargue: function () {
                this.trigger('loading:start');
            },

            notificarFinCargue: function () {
                this.trigger('loading:stop');
            }
        }

        _.extend(BackboneValidation.callback, {
            valid: function (view, attr) {
                var $el = view.$('#' + attr);
                if ($el.length === 0) {
                    $el = view.$('[name~=' + attr + ']');
                }

                if ($el.parent().hasClass('input-group')) {
                    $el = $el.parent();
                }

                var $group = $el.closest('.form-group');
                $group.removeClass('has-error')
                    .addClass('has-success');

                var $helpBlock = $el.next('.help-block');
                if ($helpBlock.length === 0) {
                    $helpBlock = $el.children('.help-block');
                }
                $helpBlock.slideUp({
                    done: function () {
                        $helpBlock.remove();
                    }
                });
            },

            invalid: function (view, attr, error) {
                var $el = view.$('#' + attr);
                if ($el.length === 0) {
                    $el = view.$('[name~=' + attr + ']');
                }

                $el.focus();

                var $group = $el.closest('.form-group');
                $group.removeClass('has-success')
                    .addClass('has-error');

                if ($el.parent().hasClass('input-group')) {
                    $el = $el.parent();
                }


                if ($el.next('.help-block').length !== 0) {
                    $el.next('.help-block')[0].innerText = error;
                } else if ($el.children('.help-block').length !== 0) {
                    $el.children('.help-block')[0].innerText = error;
                } else {
                    var $error = $('<div>')
                        .addClass('help-block')
                        .html(error)
                        .hide();

                    if ($el.prop('tagName') === 'div' && !$el.hasClass('input-group')) {
                        $el.append($error);
                    } else {
                        $el.after($error);
                    }

                    $error.slideDown();
                }
            }
        });

        _.extend(app, Backbone.Events);
        return app;
    });
