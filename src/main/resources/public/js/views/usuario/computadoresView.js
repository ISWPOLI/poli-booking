define([
  'jquery',
  'underscore',
  'backbone',
  'views/sidebar/SidebarView',
  'text!templates/usuario/computadores.html'
], function($, _, Backbone, SidebarView, computadores){

  var computadoresView = Backbone.View.extend({
    el: $("#page"),

    render: function(){
      
      $('.menu li').removeClass('active');
      $('.menu li a[href="#"]').parent().addClass('active');
      this.$el.html(homeTemplate);

      var sidebarView = new SidebarView();
      sidebarView.render();
 
    }

  });

  return computadoresView;
  
});
