define([
  'jquery',
  'underscore',
  'backbone',
  'views/sidebar/SidebarView',
  'text!templates/usuario/canchas.html'
], function($, _, Backbone, SidebarView, canchas){

  var canchasView = Backbone.View.extend({
    el: $("#page"),

    render: function(){
      
      $('.menu li').removeClass('active');
      $('.menu li a[href="#"]').parent().addClass('active');
      this.$el.html(canchas);

      var sidebarView = new SidebarView();
      sidebarView.render();
 
    }

  });

  return canchasView;
  
});
