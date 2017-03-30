define(['backbone', 'App', 'clndr', 'text!CalendarioTemplate'],
    function (Backbone, App, clndr, CalendarioTemplate) {
        var cubiculoCalendarioView = Backbone.View.extend({
            eventos: [],

            tipoEspacio: '',

            render: function () {
                this.$el.html(CalendarioTemplate);
                this.generarCalendario(this.$el.find('.pb-calendar'));
                return this;
            },

            generarCalendario: function (calendarioElement) {
                var that = this;
                var moment = require('moment');
                moment.locale('es');
                var mesActual = moment().format('YYYY-MM');

                var calendario = calendarioElement.clndr({
                    template: CalendarioTemplate,
                    events: this.eventos,
                    dateParameter: 'dia',
                    clickEvents: {
                        click: function (target) {
                            if (target.events && target.events.length > 0) {
                                var fecha = target.date.format('YYYY-MM-DD');
                                var ruta = '/bloques-disponibles?fecha=' + fecha + '&tipoEspacio=' + that.tipoEspacio;

                                App.router.navigate(ruta, true);
                            } else {
                                App.notificarError('No hay bloques horarios disponibles ese día');
                            }
                        }
                    },
                    showAdjacentMonths: false,
                    adjacentDaysChangeMonth: false
                });
            }
        });

        return cubiculoCalendarioView;
    });