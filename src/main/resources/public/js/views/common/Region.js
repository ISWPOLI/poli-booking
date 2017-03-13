define([], function () {
    var regionView = function (options) {
        var view = {
            el: options.el,

            show: function (view) {
                this.closeView(this.currentView);
                this.currentView = view;
                this.openView(view);
            },

            closeView: function (view) {
                if (view && view.remove) {
                    view.remove();
                }
            },

            openView: function (view) {
                this.ensureEl();

                view.render();
                this.$el.html(view.el);

                if (view.onShow) {
                    view.onShow();
                }
            },

            ensureEl: function () {
                if (this.$el) {
                    return;
                }

                this.$el = $(this.el);
            },

            remove: function () {
                this.closeView(this.currentView);
            }
        };
    };

    return regionView;

});