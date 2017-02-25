define([
  'jquery',
  'underscore',
  'backbone',
  'views/sidebar/SidebarView',
  'text!templates/usuario/cambioContrasena.html'
], function($, _, Backbone, SidebarView, cambioContrasena){

  var cambioContrasena = Backbone.View.extend({
    el: $("#page"),

    render: function(){
      
      $('.menu li').removeClass('active');
      $('.menu li a[href="#"]').parent().addClass('active');
      this.$el.html(cambioContrasena);

      var sidebarView = new SidebarView();
      sidebarView.render();
 
    },

    events: {
      "click #cambioContrasenaButton": "Cambio de Contrasena"
    }, 

    cambioContrasena: function(){
    	var x=document.getElementById("pass")
    	var y=document.getElementById("repass")
      alert(x)
    }

  });

  return cambioContrasena;
  
});
