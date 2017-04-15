define(['backbone', 'moment', 'App', 'text!templates/administrador/bloques/generar-bloques.html'],
    function (Backbone, moment, App, GenerarBloquesTemplate) {
        var generarBloquesView = Backbone.View.extend({
            render: function () {
                this.$el.html(GenerarBloquesTemplate);
            },

            events: {
                'click #pb-gb-btn-generar-bloques': 'generarBloques'
            },

            generarBloques: function (btnGenerarBloques) {
                var that = this;
                var fechaInicial = moment(this.$el.find('#pg-gb-fecha-inicial').val());
                var fechaFinal = moment(this.$el.find('#pg-gb-fecha-final').val());
                var tipoEspacio = this.$el.find('#pg-gb-tipo-espacio').val();

                if (!that.validarInput()) {
                    return;
                }

                Backbone.$.ajax({
                    url: '/bloques/generar-bloques-masivamente',
                    method: 'POST',
                    data: {
                        "tipo-espacio": tipoEspacio,
                        "fecha-inicio": fechaInicial.format('YYYY-MM-DD'),
                        "fecha-fin": fechaFinal.format('YYYY-MM-DD')
                    },
                    success: function (bloques) {
                        App.notificarExito('Los bloques horarios han sido creados correctamente');
                        that.limpiarCampos();
                    },
                    failure: function (error) {
                        App.notificarError('Ha ocurrido un error creando los bloques horarios');
                    }
                });
            },

            validarInput: function (fechaInicial, fechaFinal, tipoEspacio) {
                var that = this;
                var fechaInicial = moment(this.$el.find('#pg-gb-fecha-inicial').val());
                var fechaFinal = moment(this.$el.find('#pg-gb-fecha-final').val());
                var tipoEspacio = this.$el.find('#pg-gb-tipo-espacio').val();

                if (!fechaInicial || !fechaFinal || !tipoEspacio) {
                    App.notificarError('fecha inicial, fecha final y tipo espacio son requeridos');
                    return false;
                }

                if (!fechaInicial.isValid() || !fechaFinal.isValid()) {
                    App.notificarError('fecha inicial y/o fecha final inválidas');
                    return false;
                }

                if (fechaInicial.isAfter(fechaFinal)) {
                    App.notificarError('la fecha inicial debe ser anterior a la fecha final');
                    return false;
                }

                var hoy = moment();
                hoy.set({hour: 0, minute: 0, second: 0, millisecond: 0});
                if (fechaInicial.isBefore(hoy)) {
                    App.notificarError('la fecha inicial debe ser mayor o igual a hoy');
                    return false;
                }

                return true;
            },

            limpiarCampos: function () {
                this.$el.find('#pg-gb-fecha-inicial').val('');
                this.$el.find('#pg-gb-fecha-final').val('');
                this.$el.find('#pg-gb-tipo-espacio').val('');
            }
        });


        return generarBloquesView;
    });