define(['jquery', 'underscore', 'material', 'backbone', 'backboneValidation', 'sweetalert', 'noty', 'ReservasApp', 'DefaultRouter', 'Region'], function ($, _, material, Backbone, BackboneValidation, sweetAlert, Noty, ReservasApp, DefaultRouter, Region) {
    var publicRoutes = ['password-change'];
    var app = {
        Models: {},
        Collections: {},
        Routers: {},
        start: function () {
            Backbone.View.prototype.fireLoad = function() {
                dispatchEvent(new Event('load'));
            };

            _.each(_.values(App.Routers), function (Router) {
                new Router();
            });

            App.mainRegion = new Region({el: '#page'});

            App.router = new DefaultRouter();

            Backbone.history.start();

            //App.initializeAuth();
        },

        startSubApplication: function (SubApplication) {
            if (App.currentSubapp && App.currentSubapp.destroy) {
                App.currentSubapp.destroy();
            }

            App.currentSubapp = new SubApplication({region: App.mainRegion});
            return App.currentSubapp;
        },

        successMessage: function (message) {
            var options = {
                title: 'Operación exitosa',
                type: 'success',
                text: message,
                confirmButtonText: 'OK'
            };

            sweetAlert(options);
        },

        errorMessage: function (message) {
            var options = {
                title: 'Error',
                type: 'error',
                text: message,
                confirmButtonText: 'OK'
            };

            sweetAlert(options);
        },

        askConfirmation: function (message, callback) {
            var options = {
                title: '¿estás seguro?',
                type: 'warning',
                text: message,
                showCancelButton: true,
                confirmButtonText: 'Sí',
                confirmButtonColor: '#5cb85c',
                cancelButtonText: 'No'
            };

            sweetAlert(options, function (isConfirm) {
                callback(isConfirm);
            });
        },

        notifySuccess: function (message) {
            new Noty({
                text: message,
                layout: 'topRight',
                theme: 'relax',
                type: 'success',
                timeout: 3000
            });
        },

        notifyError: function (message) {
            new Noty({
                text: message,
                layout: 'topRight',
                theme: 'relax',
                type: 'error',
                timeout: 3000
            });
        },

        initializeAuth: function () {
            var authConfig = sessionStorage.getItem('auth');

            if (!authConfig && !this.isPublicRoute()) {
                return this.redirectToLogin();
            } else if (authConfig) {
                var authElements = authConfig.split(':');
                var type = authElements[0];
                var token = authElements[1];

                this.setAuth(type, token);
            }
        },
        setAuth: function (type, token) {
            var authString = type + " " + token;
            this.setupAjax(authString);
        },
        setupAjax: function (authString) {
            var headers = {};

            if (authString) {
                headers = {
                    Authorization: authString
                };
            }

            Backbone.$.ajaxSetup({
                statusCode: {
                    401: function () {
                        return this.redirectToLogin();
                    }
                },
                headers: headers
            });
        },
        saveAuth: function (type, token) {
            var authConfig = type + ":" + token;

            sessionStorage.setItem('auth', authConfig);
            this.setAuth(type, token);
        },
        isAuthenticated: function () {
            if (sessionStorage.getItem('auth')) {
                return true;
            } else {
                return false;
            }
        },
        redirectToLogin: function () {
            window.location.replace('#/login');
        },
        checkAuthorization: function () {
            if (this.isAuthenticated()) {
                return true;
            } else {
                return this.redirectToLogin();
            }
        },
        isPublicRoute: function () {
            var currentRoute = Backbone.history.getFragment();
            ;

            return _.contains(publicRoutes, currentRoute);
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

    window.App = app;

    return app;
});
