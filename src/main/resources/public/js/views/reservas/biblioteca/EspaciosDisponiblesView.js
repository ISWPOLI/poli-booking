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
              var date= new Date();
              var day= date.getDate();
              var allMonth=['01','02','03','04','05','06','07','08','09','10','11','12'];
              var month=allMonth[date.getMonth()];
              var year= date.getFullYear();
              this.buscarFechasDisponibles(''+year+'-'+month+'-'+day+'',"1");
              this.fireLoad();
            },

            buscarFechasDisponibles : function(fecha, id) {
              var that = this;
              
              Backbone.$
                  .ajax({
                    url : '/bloques/consultar-bloques-vigentes',
                    type : 'GET',
                    data:{
                      "dia" : fecha ,
                      "id-espacio" : id,
                    },
                    success : function(espacios) {
                      that.generarTabla(espacios);
                    },
                    error : function(jqxhr) {
                      if (jqxhr.status === 401) {
                        that.showError('no hay espacios');
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
              for (var i = 0; i < espacios.length; i++) {
                // Crea las hileras de la tabla
                var hilera = document.createElement("tr");

                for (var j = 0; j <=5; j++) {
                  
                  var celda = document.createElement("td");
                  celda.classList.add('mdl-data-table__cell--non-numeric');
                  if (j == 1) {
                    //nombre del cubiculo
                    
                    var textoCelda = document
                        .createTextNode(espacios[i]["espacio"]["nombre"]);
                    celda.appendChild(textoCelda);
                    hilera.appendChild(celda);
                  } else if (j == 2) {
                    //fecha o dia
                    var t=espacios[i]["dia"];
                    var date=new Date(t);
                    var day= date.getDate();
                    var allMonth=['01','02','03','04','05','06','07','08','09','10','11','12'];
                    var month=allMonth[date.getMonth()];
                    var year= date.getFullYear();
                    var textoCelda = document
                        .createTextNode(''+year+'-'+month+'-'+day);
                    celda.appendChild(textoCelda);
                    hilera.appendChild(celda);

                  } else if (j == 3) {
                    //hora inicio
                    t=espacios[i]["tiempoInicio"];
                        date=new Date(t);
                        minutos=date.getMinutes();
                        horas=date.getHours();
                        segundos=date.getSeconds();
                        var n=date.toLocaleTimeString();
                    var textoCelda = document
                        .createTextNode(''+horas+''+':'+minutos+':'+segundos);
                    var textoCelda = document
                        .createTextNode(n);
                    celda.appendChild(textoCelda);
                    hilera.appendChild(celda);

                  } else if (j == 4) {
                    //hora fin
                     t=espacios[i]["tiempoFin"];
                        date=new Date(t);
                        minutos=date.getMinutes();
                        horas=date.getHours();
                        segundos=date.getSeconds();
                    var textoCelda = document
                        .createTextNode(''+horas+''+':'+minutos+':'+segundos);
                    var n=date.toLocaleTimeString();
                    var textoCelda = document
                        .createTextNode(n);
                    celda.appendChild(textoCelda);
                    hilera.appendChild(celda);

                  } else if(j==5) {
                    
                    //boton de confirmar
                    var boton = document
                        .createElement("button");
                    boton.classList.add('mdl-button');
                    var texto = document
                        .createTextNode("Reservar");
                    var n=espacios[i]["id"];
                    boton.setAttribute("id","btn-reservar-"+n);
//                    var alerta= alerta_function(n);
//                    boton.addEventListener("click",function alerta_function(n){
//                        alert(n);
//                    }(n));
                    boton.appendChild(texto);
                    celda.appendChild(boton);
                    var x=celda.getElementById("btn-reservar-"+n);
                    x.addEventListener(click,alerta_function(n));
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
                      "mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp is-upgraded");
            
              function alerta_function(n){
                  alert(n);
                }
            }
            
          // document.body.get.classList.add('mdl-data-table__cell--non-numeric');

          });
      return EspaciosDisponiblesView;
    });
