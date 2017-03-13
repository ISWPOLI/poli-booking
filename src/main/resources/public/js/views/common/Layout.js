define(['ModelView'], function (ModelView) {
    var layout = ModelView.extend({
        render: function () {
            this.closeRegions();

            var result = ModelView.prototype.render.call(this);

            this.configureRegions();
            return result;
        },

        configureRegions: function () {
            var that = this;
            var regionDefinitions = this.regions || {};

            if (!this._regions) {
                this._regions = {};
            }

            _.each(regionDefinitions, function (selector, name) {
                var $el = that.$(selector);
                this._regions[name] = new Region({el: $el});
            })
        },

        getRegion: function (regionName) {
            var regions = this._regions || {};
            return regions[regionName];
        },

        remove: function (options) {
            ModelView.prototype.remove.call(this, options);
            this.closeRegions();
        },

        closeRegions: function () {
            var regions = this._regions || {};

            _.each(regions, function (region) {
                if (region && region.remove) {
                    region.remove();
                }
            });
        }
    });

    return layout;
});