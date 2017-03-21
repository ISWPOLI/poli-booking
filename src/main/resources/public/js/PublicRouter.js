define(['backbone', 'App', 'PublicApp'],
    function (Backbone, App, PublicApp) {

        var publicRouter = Backbone.Router.extend({
            routes: {
                'login': 'mostrarLogin',
                'logout': 'logout',
                'password-recovery': 'mostrarRecuperacionCuenta',
                'password-change': 'mostrarCambioPassword'
            },
            mostrarLogin: function () {
                var publicApp = this.getApp();
                publicApp.mostrarLogin();
            },
            logout: function () {
                App.logout();
            },
            mostrarRecuperacionCuenta: function () {
                var publicApp = this.getApp();
                publicApp.mostrarRecuperacionCuenta();
            },
            mostrarCambioPassword: function () {
                var publicApp = this.getApp();
                publicApp.mostrarCambioPassword();
            },
            getApp: function () {
                return App.arrancarSubAplicacion(PublicApp);
            }
        });

        App.Routers.PublicRouter = publicRouter;

        return publicRouter;
    });
