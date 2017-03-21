define(['jquery', 'underscore', 'backbone', 'App',
        'text!templates/reservas/confirmarReserva.html'],
    function ($, _, Backbone, App, confirmarReserva) {

        var ConfirmarReservaView = Backbone.View.extend({

            render: function () {
                $('.menu li').removeClass('active');
                $('.menu li a[href="#"]').parent().addClass('active');
                this.$el.html(confirmarReserva);
                App.lanzarEventoLoad();
            },

            mostrarResumenReserva: function () {
                var fecha = this.fecha;
                var idBloque = this.idBloque;


            },

            generarTabla: function () {
                // Obtener la referencia del elemento body
                var body = document.getElementById("uno");
                var arr = ['Espacio', 'Fecha', "Hora Inicio", "Hora Fin"];

                // Crea un elemento <table> y un elemento <tbody>
                var tabla = document.createElement("table");
                var tHead = document.createElement("thead");
                var tblBody = document.createElement("tbody");

                for (var i = 0; i < 1; i++) {

                    var hilera = document.createElement("tr");

                    for (var j = 0; j < 4; j++) {

                        var celda = document.createElement("th");
                        celda.classList.add('mdl-data-table__cell--non-numeric');
                        var textoTitulo = document.createTextNode(arr[j]);
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
                        // Crea un elemento <td> y un nodo de texto, haz que el nodo de
                        // texto sea el contenido de <td>, ubica el elemento <td> al final
                        // de la hilera de la tabla
                        var celda = document.createElement("td");
                        celda.classList.add('mdl-data-table__cell--non-numeric');
                        var textoCelda = document.createTextNode("Informacion");
                        celda.appendChild(textoCelda);
                        hilera.appendChild(celda);
                    }

                    // agrega la hilera al final de la tabla (al final del elemento tblbody)
                    tblBody.appendChild(hilera);
                }

                // posiciona el <tbody> debajo del elemento <table>
                tabla.appendChild(tHead);
                tabla.appendChild(tblBody);
                // appends <table> into <body>
                body.appendChild(tabla);
                // modifica el atributo "border" de la tabla y lo fija a "2";
                tabla.setAttribute("class", "mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp is-upgraded")
            }
        });

        return ConfirmarReservaView;

    });
