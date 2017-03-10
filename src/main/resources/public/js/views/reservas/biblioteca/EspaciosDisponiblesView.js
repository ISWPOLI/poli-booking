define(
		[ 'jquery', 'underscore', 'backbone',
				'text!templates/reservas/biblioteca/EspaciosDisponibles.html' ],
		function($, _, Backbone, EspaciosDisponibles) {         

			var EspaciosDisponiblesView = Backbone.View
					.extend({
						el : $("#page"),

						render : function() {
							$('.menu li').removeClass('active');
							$('.menu li a[href="#"]').parent().addClass(
									'active');
							this.$el.html(EspaciosDisponibles);
							this.fireLoad();
						},

						buscarFechasDisponibles : function(fecha) {
							var that = this;
							Backbone.$
									.ajax({
										url : '/bloques/consultar-bloques-vigentes',
										success : function(espacios) {
											generarTabla(espacios);
											that.clearError();
										},
										error : function(jqxhr) {
											if (jqxhr.status === 401) {
												that
														.showError('usuario y/o contraseña inválidos');
											} else {
												that.showError('error general');
											}
										}
									});
						},

						generarTabla : function genera_tabla(espacios) {
							// Obtener la referencia del elemento body
							var body = document.getElementById("uno");
							var arr = [ 'Espacio', 'Fecha', "Hora Inicio",
									"Hora Fin", "Disponible" ];

							// Crea un elemento <table> y un elemento <tbody>
							var tabla = document.createElement("table");
							var tHead = document.createElement("thead");
							var tblBody = document.createElement("tbody");

							for (var i = 0; i < 1; i++) {

								var hilera = document.createElement("tr");

								for (var j = 0; j < 5; j++) {

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
							for (var i = 0; i < 5; i++) {
								// Crea las hileras de la tabla
								var hilera = document.createElement("tr");

								for (var j = 0; j < espacios.size(); j++) {
									// Crea un elemento <td> y un nodo de texto,
									// haz que el nodo de
									// texto sea el contenido de <td>, ubica el
									// elemento <td> al final
									// de la hilera de la tabla
									var celda = document.createElement("td");
									celda.classList
											.add('mdl-data-table__cell--non-numeric');
									if (j == 1) {
										//nombre del cubiculo
										
										var textoCelda = document
												.createTextNode("Informacion");
										celda.appendChild(textoCelda);
										hilera.appendChild(celda);
									} else if (j == 2) {
										//fecha o dia
										var textoCelda = document
												.createTextNode("Informacion");
										celda.appendChild(textoCelda);
										hilera.appendChild(celda);

									} else if (j == 3) {
										//hora inicio
										var textoCelda = document
												.createTextNode("Informacion");
										celda.appendChild(textoCelda);
										hilera.appendChild(celda);

									} else if (j == 4) {
										//hora fin
										var textoCelda = document
												.createTextNode("Informacion");
										celda.appendChild(textoCelda);
										hilera.appendChild(celda);

									} else {
										//boton de confirmar
										var boton = document
												.createElement("button");
										boton.classList.add('mdl-button');
										var texto = document
												.createTextNode("Reservar");
										boton.appendChild(texto);
										celda.appendChild(boton);
										hilera.appendChild(celda);
									}
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
						}
					// document.body.get.classList.add('mdl-data-table__cell--non-numeric');

					});
			return EspaciosDisponiblesView;
		});
