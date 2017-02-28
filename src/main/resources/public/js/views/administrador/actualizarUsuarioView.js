define([
  'jquery',
  'underscore',
  'backbone',
  'views/sidebar/SidebarView',
  'text!templates/administrador/actualizarUsuario.html'
], function($, _, Backbone, SidebarView, actualizarUsuario){

  var actualizarUsuarioView = Backbone.View.extend({
    el: $("#page"),

    render: function(){
      
      $('.menu li').removeClass('active');
      $('.menu li a[href="#"]').parent().addClass('active');
      this.$el.html(actualizarUsuario);

      var sidebarView = new SidebarView();
      sidebarView.render();
 
    }

  });

  return actualizarUsuarioView;
  
});
