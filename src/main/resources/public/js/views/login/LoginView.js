'use strict';
define(['jquery', 'underscore', 'backbone', 'App', 'text!templates/login/login.html'],
    function ($, _, Backbone, App, loginTemplate) {
        var login = Backbone.View.extend({
            render: function () {
                $('.menu li').removeClass('active');
                $('.menu li a[href="#/login"]').parent().addClass('active');
                this.$el.html(loginTemplate);
                App.lanzarEventoLoad();
            },

            events: {
                "click #loginButton": "login", 
                "keypress #password": "login2"
            },

            login2: function (event) {
                var that = this;              
                if(event.which==13){
                	var username = that.$el.find('#username').val();
                	var password = that.$el.find('#password').val();
                	var authString = that.buildAuthString(username, password);
                	that.sendAuthenticationRequest(authString);
                }
            },
            login: function (event) {
                var that = this;
                event.preventDefault();

                var username = that.$el.find('#username').val();
                var password = that.$el.find('#password').val();

                var authString = that.buildAuthString(username, password);
                that.sendAuthenticationRequest(authString);
            },

            sendAuthenticationRequest: function (authString) {
                var that = this;
                Backbone.$.ajax({
                    url: '/user',
                    headers: {
                        Authorization: 'Basic ' + authString
                    },
                    success: function (data) {
                        that.clearError();
                        App.guardarRoles(data.authorities);
                        App.guardarAutenticacion('Basic', authString);
                        window.location.replace('#/home');
                    },
                    error: function (jqxhr) {
                        if (jqxhr.status === 401) {
                            that.showError('usuario y/o contraseña inválidos');
                        } else {
                            that.showError('error general');
                        }
                    }
                });
            },

            buildAuthString: function (username, password) {
                return btoa(username + ":" + password);
            },

            showError: function (message) {
                this.$el.find('#auth_error_message').html(message);
            },

            clearError: function (message) {
                this.$el.find('#auth_error_message').html('');
            }

        });

        return login;

    });
