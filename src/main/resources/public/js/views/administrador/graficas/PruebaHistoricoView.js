define(
		[ 'jquery', 'underscore', 'backbone', 'App',
				'text!templates/administrador/graficas/pruebaHistorico.html' ],
		function($, _, Backbone, App, pruebaHistorico) {

			var pruebaHistoricoView = Backbone.View
					.extend({
					
						render : function() {
							this.$el.html(pruebaHistorico);
							App.lanzarEventoLoad();
							this.datosHistorico();
						},
						
						

						datosHistorico : function() {
							Backbone.$
									.ajax({
										url : '/reservas/mis-reservas/historico-grafica',
										type : 'GET',

										success : function(data) {
											 google.charts.load('current', {'packages': ['line']});
											    google.charts.setOnLoadCallback(drawChart);
											    
										function drawChart(){
											        var dataG = new google.visualization.DataTable();
											        dataG.addColumn('string', 'Dia');
											        dataG.addColumn('number', 'Cubiculos Video');
											        dataG.addColumn('number', 'Cubiculos Estudio');
											        dataG.addColumn('number', 'Cancha Multiple');
                                                    dataG.addColumn('number', 'Cancha Tenis');
                                                    dataG.addColumn('number', 'Gimnasio');
                                                    dataG.addColumn('number', 'Cancha Futbol');

											       
											var arr =[];
											var nCubiculoVideo = 0;
											var nCubiculoEstudio = 0;
											var nCanchaMultiple = 0;
                                            var nCanchaTenis=0;
                                            var nGimnasio=0;
                                            var nCanchaFutbol=0;
											// var diaActual = new Date();
											var diaActual = data[0]["bloque"]["dia"];
                                            var date=new Date(diaActual);
                                            arr[0]=new Array(7);
                                            arr[0][0]=date.toLocaleDateString();
											//console.log(diaActual);
                                            var cont =0;
											for ( var i in data) {
												if (diaActual != data[i]["bloque"]["dia"]) {

//													console.log(nCubiculoVideo
//															+ " -  "
//															+ nCubiculoEstudio
//															+ " - "
//															+ nCanchaMultiple);
                                                    arr[cont][1]=nCubiculoVideo;
                                                    arr[cont][2]=nCubiculoEstudio;
                                                    arr[cont][3]=nCanchaMultiple;
                                                    arr[cont][4]=nCanchaTenis;
                                                    arr[cont][5]=nGimnasio;
                                                    arr[cont][6]=nCanchaFutbol;
                                                    cont++;
													diaActual = data[i]["bloque"]["dia"];
                                                    date =new Date(diaActual);
                                                    arr[cont]=new Array(7);
                                                    arr[cont][0]=date.toLocaleDateString();
//													console
//													.log(diaActual);
													var nCubiculoVideo = 0;
                                                    var nCubiculoEstudio = 0;
                                                    var nCanchaMultiple = 0;
                                                    var nCanchaTenis=0;
                                                    var nGimnasio=0;
                                                    var nCanchaFutbol=0;

												}
												if (data[i]["bloque"]["espacio"]["tipoEspacio"]["nombre"] == "CUBICULO_VIDEO") {
													nCubiculoVideo++;
//													console.log(nCubiculoVideo);
												} else if (data[i]["bloque"]["espacio"]["tipoEspacio"]["nombre"] == "CUBICULO_ESTUDIO") {
													nCubiculoEstudio++;
//													console
//															.log(nCubiculoEstudio);
												} else if (data[i]["bloque"]["espacio"]["tipoEspacio"]["nombre"] == "CANCHA_MULTIPLE") {
													nCanchaMultiple++;
//													console
//															.log(nCanchaMultiple);
												}else if(data[i]["bloque"]["espacio"]["tipoEspacio"]["nombre"]== "CANCHA_TENIS"){
                                                    nCanchaTenis++;
                                                }else if(data[i]["bloque"]["espacio"]["tipoEspacio"]["nombre"]== "GIMNASIO"){
                                                    nGimnasio++;
                                                }else if(data[i]["bloque"]["espacio"]["tipoEspacio"]["nombre"]== "CANCHA_FUTBOL"){
                                                    nCanchaFutbol++;
                                                }
											}


                                             //cont++;
                                             arr[cont]=new Array(7);
                                             date=new Date(diaActual);
                                            arr[cont][0]=date.toLocaleDateString();
                                            arr[cont][1]=nCubiculoVideo;
                                            arr[cont][2]=nCubiculoEstudio;
                                            arr[cont][3]=nCanchaMultiple;
                                            arr[cont][4]=nCanchaTenis;
                                            arr[cont][5]=nGimnasio;
                                            arr[cont][6]=nCanchaFutbol;
                                             
                                             dataG.addRows(arr);

//											console.log(diaActual);
//											console.log(nCubiculoVideo
//													+ " -  "
//													+ nCubiculoEstudio
//													+ " - "
//													+ nCanchaMultiple);
											
											var options = {
													chart: {
														title: 'Historico de reservas',
														subtitle: '#Reservas vs Dia'
													},
													width: 900,
													height: 500
											};


											
											var chart = new google.charts.Line(document.getElementById('prueba'));
											
											chart.draw(dataG, options);
											

										}},
										error : function(jqxhr) {
											if (jqxhr.status === 401) {
												App
														.notificarError('no hay espacios');
											} else {
												App
														.notificarError('error general');
											}
										}
									});
						}

					});
			return pruebaHistoricoView;

		});