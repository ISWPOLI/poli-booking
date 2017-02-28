define([
  'jquery',
  'underscore',
  'backbone',
  'views/sidebar/SidebarView',
  'text!templates/usuario/biblioteca.html'
], function($, _, Backbone, SidebarView, biblioteca){

  var bibliotecaView = Backbone.View.extend({
    el: $("#page"),

    render: function(){
      
      $('.menu li').removeClass('active');
      $('.menu li a[href="#"]').parent().addClass('active');
      this.$el.html(biblioteca);

      var sidebarView = new SidebarView();
      sidebarView.render();
 
    }

  });

  return bibliotecaView;
  
});
