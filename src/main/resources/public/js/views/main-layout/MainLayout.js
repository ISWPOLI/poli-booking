define(['Layout', 'text!templates/layouts/main-layout.html'], function (Layout, MainLayoutTemlate) {
    var mainLayout = Layout.extend({
        template: MainLayoutTemlate,
        regiones: {
            header: '#poli-booking-header',
            drawer: '#poli-booking-drawer',
            footer: '#poli-booking-footer',
            content: '#poli-booking-content'
        }
    });

    return mainLayout;

});