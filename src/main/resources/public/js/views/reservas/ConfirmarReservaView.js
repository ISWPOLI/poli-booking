define([
  'jquery',
  'underscore',
  'backbone',
  'views/sidebar/SidebarView',
  'text!templates/reservas/confirmarReserva.html'
], function($, _, Backbone, SidebarView, confirmarReserva){

  var confirmarReservaView = Backbone.View.extend({
    el: $("#page"),

    render: function(){
      
      $('.menu li').removeClass('active');
      $('.menu li a[href="#"]').parent().addClass('active');
      this.$el.html(confirmarReserva);

      var sidebarView = new SidebarView();
      sidebarView.render();
 
    }

  });

  return confirmarReservaView;
  
});
