define(['backbone'], function (Backbone) {
    var collectionView = Backbone.View.extend({
        initialize: function () {
            this.children = {};

            this.listenTo(this.collection, 'add', this.modelAdded);
            this.listenTo(this.collection, 'remove', this.modelRemoved);
            this.listenTo(this.collection, 'reset', this.render);
        },

        modelAdded: function (model) {
            var view = this.renderModel(model);
            this.$el.append(view.$el);
        },

        modelRemoved: function (model) {
            if (!model) {
                return;
            }

            var view = this.children[model.cid];
            this.closeChildView(view);
        },

        render: function () {
            var that = this;
            this.closeChildren();

            var htmlArray = _.map(this.collection, function (model) {
                var view = that.renderModel(model);
                return view.$el;
            });

            this.$el.html(htmlArray);
            return this;
        },

        renderModel: function (model) {
            var view = new this.modelView({model: model});

            this.children[model.cid] = view;

            this.listenTo(view, 'all', function (eventName) {
                this.trigger('item:' + eventName, view, model);
            });

            view.render();
            return view;
        },

        remove: function () {
            Backbone.View.prototype.remove.call(this);
            this.closeChildren();
        },

        closeChildren: function () {
            var that = this;
            var children = this.children || {};
            _.each(children, function (child) {
                that.closeChildView(child);
            });
        },

        closeChildView: function (view) {
            if (!view) {
                return;
            }

            if (_.isFunction(view.remove)) {
                view.remove();
            }

            this.stopListening(view);

            if (view.model) {
                this.children[view.model.cid] = undefined;
            }
        }
    });

    return collectionView;
});