define([
  'jquery',
  'underscore',
  'backbone',
  'views/sidebar/SidebarView',
  'text!templates/usuario/gimnasio.html'
], function($, _, Backbone, SidebarView, gimnasio){

  var gimnasioView = Backbone.View.extend({
    el: $("#page"),

    render: function(){
      
      $('.menu li').removeClass('active');
      $('.menu li a[href="#"]').parent().addClass('active');
      this.$el.html(gimnasio);

      var sidebarView = new SidebarView();
      sidebarView.render();
 
    }

  });

  return gimnasioView;
  
});
