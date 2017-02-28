define([
  'jquery',
  'underscore',
  'backbone',
  'views/sidebar/SidebarView',
  'text!templates/usuario/tenis.html'
], function($, _, Backbone, SidebarView, tenis){

  var tenisView = Backbone.View.extend({
    el: $("#page"),

    render: function(){
      
      $('.menu li').removeClass('active');
      $('.menu li a[href="#"]').parent().addClass('active');
      this.$el.html(tenis);

      var sidebarView = new SidebarView();
      sidebarView.render();
 
    }

  });

  return tenisView;
  
});
