define([
  'jquery',
  'underscore',
  'backbone',
  'views/sidebar/SidebarView',
  'text!templates/usuario/login.html'
], function($, _, Backbone, SidebarView, login){

  var login = Backbone.View.extend({
    el: $("#page"),

    render: function(){
      
      $('.menu li').removeClass('active');
      $('.menu li a[href="#"]').parent().addClass('active');
      this.$el.html(login);

      var sidebarView = new SidebarView();
      sidebarView.render();
 
    },

    events: {
      "click #loginButton": "login"
    }, 

    login: function(){
    	var x=document.getElementById("user")
    	var y=document.getElementById("pass")
      alert(x)
    }

  });

  return login;
  
});
