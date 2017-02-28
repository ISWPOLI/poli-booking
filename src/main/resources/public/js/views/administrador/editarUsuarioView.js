define([
  'jquery',
  'underscore',
  'backbone',
  'views/sidebar/SidebarView',
  'text!templates/administrador/editarUsuario.html'
], function($, _, Backbone, SidebarView, editarUsuario){

  var editarUsuarioView = Backbone.View.extend({
    el: $("#page"),

    render: function(){
      
      $('.menu li').removeClass('active');
      $('.menu li a[href="#"]').parent().addClass('active');
      this.$el.html(editarUsuario);

      var sidebarView = new SidebarView();
      sidebarView.render();
 
    }

  });

  return editarUsuarioView;
  
});
