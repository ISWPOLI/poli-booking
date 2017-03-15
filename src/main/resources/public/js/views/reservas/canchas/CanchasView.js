define(['jquery', 'underscore', 'backbone', 'App',
        'text!templates/espacios/canchas.html'],
    function ($, _, Backbone, App, canchas) {

        var canchasView = Backbone.View.extend({
            el: $("#page"),

            render: function () {

                $('.menu li').removeClass('active');
                $('.menu li a[href="#"]').parent().addClass('active');
                this.$el.html(canchas);
                App.lanzarEventoLoad();
            }

        });

        return canchasView;

    });
