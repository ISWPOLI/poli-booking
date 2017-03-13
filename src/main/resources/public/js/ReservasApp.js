define([], function () {
    var reservasApp = function (options) {
        return {
            region: options.region,

            startController: function (Controller) {
                if (this.currentController &&
                    this.currentController instanceof Controller) {
                    return this.currentController;
                }

                if (this.currentController && this.currentController.destroy) {
                    this.currentController.destroy();
                }

                this.currentController = new Controller({region: this.region});
                return this.currentController;
            }
        }
    };

    return reservasApp;
});