define(
    ['jquery', 'underscore', 'backbone', 'App',
        'text!templates/reservas/confirmarReserva.html'],
    function ($, _, Backbone, App, confirmarReserva) {

        var ConfirmarReservaView = Backbone.View
            .extend({

                render: function () {
                    $('.menu li').removeClass('active');
                    $('.menu li a[href="#"]').parent().addClass(
                        'active');
                    this.$el.html(confirmarReserva);
                    App.lanzarEventoLoad();
                },
                events: {
                    "click #confirmar": "confirmarFuncion"
                },


                confirmarFuncion: function () {
                    var idBloque = this.idBloque;
                    var that = this;

                    App.notificarInicioCargue();
                    Backbone.$.ajax({
                        url: '/reservas/mis-reservas',
                        type: 'POST',
                        data: {
                            "idBloque": idBloque,
                        },
                        success: function () {
                            App.notificarFinCargue();
                            window.location.replace('/#/mis-reservas');
                        },
                        error: function (jqxhr) {
                            App.notificarFinCargue();
                            if (jqxhr.status === 401) {
                                that.showError('no hay espacios');
                            } else {
                                that.showError('error general');
                            }
                        }
                    });
                },

                mostrarResumenReserva: function () {
                    var fecha = this.fecha;
                    var idBloque = this.idBloque;

                    var that = this;

                    Backbone.$.ajax({
                        url: '/bloques/consultar-bloque',
                        type: 'GET',
                        data: {
                            "idBloque": idBloque,
                        },
                        success: function (espacio) {
                            that.generarTabla(espacio);
                        },
                        error: function (jqxhr) {
                            if (jqxhr.status === 401) {
                                that.showError('no hay espacios');
                            } else {
                                that.showError('error general');
                            }
                        }
                    });

                },

                generarTabla: function (espacio) {
                    // Obtener la referencia del elemento body

                    var body = document.getElementById("uno");
                    var arr = ['Espacio', 'Fecha', "Hora Inicio",
                        "Hora Fin"];

                    // Crea un elemento <table> y un elemento <tbody>
                    var tabla = document.createElement("table");
                    var tHead = document.createElement("thead");
                    var tblBody = document.createElement("tbody");

                    for (var i = 0; i < 1; i++) {

                        var hilera = document.createElement("tr");

                        for (var j = 0; j < 4; j++) {

                            var celda = document.createElement("th");
                            celda.classList
                                .add('mdl-data-table__cell--non-numeric');
                            var textoTitulo = document
                                .createTextNode(arr[j]);
                            celda.appendChild(textoTitulo);
                            hilera.appendChild(celda);

                        }
                        tHead.appendChild(hilera);

                    }

                    // Crea las celdas
                    for (var i = 0; i < 1; i++) {
                        // Crea las hileras de la tabla
                        var hilera = document.createElement("tr");

                        for (var j = 0; j < 4; j++) {
                            // Crea un elemento <td> y un nodo de texto,
                            // haz que el nodo de
                            // texto sea el contenido de <td>, ubica el
                            // elemento <td> al final
                            // de la hilera de la tabla
                            var celda = document.createElement("td");
                            celda.classList
                                .add('mdl-data-table__cell--non-numeric');
                            var textoCelda;


                            if (j == 0) {
                                var nombre = espacio.espacio.nombre;
                                textoCelda = document
                                    .createTextNode(nombre);
                            }
                            if (j == 1) {
                                var date = new Date(espacio["dia"]);
                                textoCelda = document
                                    .createTextNode(date
                                        .toLocaleDateString());
                            }
                            if (j == 2) {
                                var date = new Date(
                                    espacio["tiempoInicio"]);
                                textoCelda = document
                                    .createTextNode(date
                                        .toLocaleTimeString());
                            }
                            if (j == 3) {
                                var date = new Date(
                                    espacio["tiempoFin"]);
                                textoCelda = document
                                    .createTextNode(date
                                        .toLocaleTimeString());
                            }
                            celda.appendChild(textoCelda);
                            hilera.appendChild(celda);

                        }

                        // agrega la hilera al final de la tabla (al
                        // final del elemento tblbody)
                        tblBody.appendChild(hilera);
                    }

                    // posiciona el <tbody> debajo del elemento <table>
                    tabla.appendChild(tHead);
                    tabla.appendChild(tblBody);
                    // appends <table> into <body>
                    body.appendChild(tabla);
                    // modifica el atributo "border" de la tabla y lo
                    // fija a "2";
                    tabla
                        .setAttribute(
                            "class",
                            "mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp is-upgraded")
                },


            });

        return ConfirmarReservaView;

    });
