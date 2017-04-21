define(['backbone', 'moment', 'App', 'text!templates/administrador/bloques/eliminar-bloques.html'],
    function (Backbone, moment, App, EliminarBloquesTemplate) {
        var eliminarBloquesView = Backbone.View.extend({
            render: function () {
                this.$el.html(EliminarBloquesTemplate);
            },

            events: {
                'click #pb-eb-btn-eliminar-bloques': 'eliminarBloques'
            },

            eliminarBloques: function (btnEliminarBloques) {
                var that = this;
                if (!that.validarInput()) {
                    return;
                }

                App.pedirConfirmacion('¿está seguro de eliminar estos bloques? tenga en cuenta que se eliminarán ' +
                    'también todas las reservas asociadas a esos bloques', that.eliminarBloquesCallback, that);
            },

            eliminarBloquesCallback: function () {
                var that = this;
                var fechaInicial = moment(that.$el.find('#pb-eb-fecha-inicial').val());
                var fechaFinal = moment(that.$el.find('#pb-eb-fecha-final').val());
                var tipoEspacio = that.$el.find('#pb-eb-tipo-espacio').val();

                App.notificarInicioCargue();
                Backbone.$.ajax({
                    url: '/bloques/eliminar-bloques-masivamente',
                    method: 'POST',
                    data: {
                        "tipo-espacio": tipoEspacio,
                        "fecha-inicio": fechaInicial.format('YYYY-MM-DD'),
                        "fecha-fin": fechaFinal.format('YYYY-MM-DD')
                    },
                    success: function (bloques) {
                        App.notificarFinCargue();
                        App.notificarExito('Los bloques horarios han sido eliminados correctamente');
                        that.limpiarCampos();
                    },
                    failure: function (error) {
                        App.notificarFinCargue();
                        App.notificarError('Ha ocurrido un error eliminando los bloques horarios');
                    }
                });
            },

            validarInput: function (fechaInicial, fechaFinal, tipoEspacio) {
                var that = this;
                var fechaInicial = moment(this.$el.find('#pb-eb-fecha-inicial').val());
                var fechaFinal = moment(this.$el.find('#pb-eb-fecha-final').val());
                var tipoEspacio = this.$el.find('#pb-eb-tipo-espacio').val();

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
                this.$el.find('#pb-eb-fecha-inicial').val('');
                this.$el.find('#pb-eb-fecha-final').val('');
                this.$el.find('#pb-eb-tipo-espacio').val('');
            }
        });


        return eliminarBloquesView;
    });