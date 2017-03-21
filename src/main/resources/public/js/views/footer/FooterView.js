define(['ModelView', 'text!FooterTemplate'], function (ModelView, FooterTemplate) {
    var footerView = ModelView.extend({
        template: function (data) {
            var compiled = _.template(FooterTemplate);
            return compiled(data);
        }
    });

    return footerView;
});