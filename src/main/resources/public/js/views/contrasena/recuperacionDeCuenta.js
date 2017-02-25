define([
  'jquery',
  'underscore',
  'backbone',
  'views/sidebar/SidebarView',
  'text!templates/usuario/recuperacionDeCuenta.html'
], function($, _, Backbone, SidebarView, recuperacionDeCuenta){

  var recuperacionDeCuenta = Backbone.View.extend({
    el: $("#page"),

    render: function(){
      
      $('.menu li').removeClass('active');
      $('.menu li a[href="#"]').parent().addClass('active');
      this.$el.html(recuperacionDeCuenta);

      var sidebarView = new SidebarView();
      sidebarView.render();
 
    },

    events: {
      "click #recuperacionDeCuentaButton": "Recuperacion de la cuenta"
    }, 

    recuperacionDeCuenta: function(){
    	var x=document.getElementById("pass")
    	var y=document.getElementById("repass")
      alert(x)
    }

  });

  return recuperacionDeCuenta;
  
});
