define(
		[ 'jquery', 'underscore', 'backbone', 'text!templates/login/login.html' ],
		function($, _, Backbone, loginTemplate) {

			var login = Backbone.View.extend({
				el : $("#page"),

				render : function() {

					$('.menu li').removeClass('active');
					$('.menu li a[href="#"]').parent().addClass('active');
					this.$el.html(loginTemplate);

				},

				events : {
					"click #loginButton" : "login"
				},

				login : function() {
					var x = document.getElementById("user")
					var y = document.getElementById("pass")
					alert(x)
				}

			});

			return login;

		});
