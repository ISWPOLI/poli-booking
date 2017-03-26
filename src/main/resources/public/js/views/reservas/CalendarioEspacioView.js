define(['backbone', 'clndr', 'text!CalendarioTemplate'], function (Backbone, clndr, CalendarioTemplate) {
    var cubiculoCalendarioView = Backbone.View.extend({
        render: function () {
            this.$el.html(CalendarioTemplate);
            this.generarCalendario(this.$el.find('.pb-calendar'));
            return this;
        },

        generarCalendario: function (calendarioElement) {
            var moment = require('moment');
            moment.locale('es');
            var mesActual = moment().format('YYYY-MM');

            var eventos = [
                {
                    title: 'Multi-Day Event',
                    date: mesActual + '-14'
                }, {
                    endDate: mesActual + '-23',
                    date: mesActual + '-21'
                }, {
                    date: mesActual + '-27',
                    title: 'Single Day Event'
                }
            ];


            var calendario = calendarioElement.clndr({
                template: CalendarioTemplate,
                events: eventos,
                clickEvents: {
                    click: function (target) {

                    },
                    today: function () {

                    },
                    nextMonth: function () {
                    },
                    previousMonth: function () {

                    },
                    onMonthChange: function () {

                    },
                    nextYear: function () {

                    },
                    previousYear: function () {

                    },
                    onYearChange: function () {

                    },
                    nextInterval: function () {

                    },
                    previousInterval: function () {

                    },
                    onIntervalChange: function () {

                    }
                },
                showAdjacentMonths: true,
                adjacentDaysChangeMonth: false
            });
        }
    });

    return cubiculoCalendarioView;
});