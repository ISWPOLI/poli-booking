define(['jquery', 'underscore', 'backbone', 'App',
        'text!templates/password/password-change.html'],
    function ($, _, Backbone, App, cambioContrasenaTemplate) {

        var cambioContrasena = Backbone.View.extend({
            render: function () {

                $('.menu li').removeClass('active');
                $('.menu li a[href="#"]').parent().addClass('active');
                this.$el.html(cambioContrasenaTemplate);
                App.lanzarEventoLoad();
            },

            events: {
                "click #password-change-button": "changePassword"
            },

            changePassword: function () {
                var that = this;

                var password = that.$el.find('#password').val();
                var confirmPassword = that.$el.find('#confirm-password').val();

                if (password !== confirmPassword) {
                    alert('las contraseñas no coinciden');
                    return;
                }

                Backbone.$.ajax({
                    url: '/user/save-password',
                    type: 'POST',
                    data: {
                        "new-password": password
                    },
                    success: function () {
                        window.location.replace('#/home');
                    },
                    error: function () {
                        alert('Ha ocurrido un error');
                    }
                });
            }

        });

        return cambioContrasena;

    });
