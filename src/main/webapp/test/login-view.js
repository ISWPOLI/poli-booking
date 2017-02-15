var LoginView = Backbone.View.extend({
	el : '#container',
	initialize : function() {
		this.render();
	},
	events : {
		"click #loginButton" : "login"
	},
	login : function() {
		var that = this;
		var username = $(this.el).find('#username').val();
		var password = $(this.el).find('#password').val();

		$.ajaxSetup({
			beforeSend : function(xhr) {
				xhr.setRequestHeader("Authorization", "Basic "
						+ btoa(username + ":" + password));
			}
		});
		$.ajax({
			type : "GET",
			url : "/user",
			async : false,
			success : function(data) {
				var authenticated = data.authenticated;
				if (authenticated) {
					that.showSuccessMessage();
				} else {
					that.showErrorMessage();
				}
			},
			error : function() {
				that.showErrorMessage();
			}
		});
	},
	showErrorMessage : function() {
		$('#authentication_error_message').html(
				"usuario y/o contraseña inválidos");
	},
	showSuccessMessage : function() {
		this.clearErrorMessage();
		alert('bienvenido');
	},
	clearErrorMessage : function() {
		$('#authentication_error_message').html('');
	}
});

var loginView = new LoginView();