<%@include file="../include-param.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="../include-head.jsp" %>
        <%@include file="../include-head-jquery-ui.jsp" %>
        <link rel="stylesheet" href="../static/css/default-forms.css">
        <link rel="stylesheet" href="../static/css/default-grids.css">
        <script type="text/javascript">
        	
        </script>
    </head>
    <body ">
    	<%@include file="../include-menu.jsp" %>
        <div class="content">
            <div class="content-form-generic">
            	<h2>Buscar por Hoja Ruta: </h2>
            	<form action="consulta_buscar_hojaruta.htm" class="form-generic" method="post">
            		
            		<div>
            			<label for="codBar">Codigo de Hoja Ruta:</label>
            			<input id="codBar" type="text" name="codBar" value="${codBar }" title="Ingrese codigo de cargo / barras" placeholder="codigo de cargo" size="15" maxlength="16">
            			
            			<input hidden="" id="codBarra" type="text" name="codBarra" value="" title="Ingrese codigo de cargo / barras" placeholder="codigo de cargo" size="15" maxlength="16">
            		</div>

<!--             		<div> -->
<!--             			<label for="smt"> </label> -->
<!-- 						<input type="submit" class="boton-default" style="min-width: 130px;" value=" Buscar " id="smt"/> -->
<!-- 					</div> -->
					
            	</form>
            </div>
<%--             <c:if test="${mostrar}"> --%>
            <div class="content-form-generic">
            	<h2>Codigo Barra: </h2>
            	<form action="consulta_buscar_cod_hojaruta.htm" class="form-generic" method="post">
            		
            		<div>
            			<input hidden="" id="codBar" type="text" name="codBar" value="${codBar }" title="Ingrese codigo de cargo / barras" placeholder="codigo de cargo" size="15" maxlength="16">
            			<label for="piezas">N�mero de piezas</label>
            			<input id="piezas" readonly="readonly" type="text" name="piezas" value="${piezas}" title="Piezas" placeholder="piezas" size="15" maxlength="16">
            		</div>
            		
            		<div>
            			<label for="cargadas">Piezas cargadas</label>
            			<input id="cargadas" readonly="readonly" type="text" name="cargadas" value="${cargados}" title="Cargadas" placeholder="cargadas" size="15" maxlength="16">
            		</div>
            		
            		<div >
            			<label for="codMotivo">Motivo:</label>
            			<select required="required" id="codMotivo" name="codMotivo" onchange="cambiarFocus()">
            				<option value="">::SELECCIONAR::</option>
            				${listaMotivoss}
            			</select>
            		</div>
            		
            		<div>
            			<label for="codBarra">Codigo de Barra</label>
            			<input id="codBarra" type="text" name="codBarra" value="" title="Ingrese codigo de cargo / barras" placeholder="codigo de cargo" size="25" maxlength="25" autofocus>
            		</div>

            		<div>
            			<label for="smt"> </label>
						<input type="submit" class="boton-default" style="min-width: 130px;" value=" Buscar " id="smt"/>
					</div>
					
            	</form>
            </div>
<%--             </c:if> --%>
            
          </div> 
          
            <div id="contTabla" style="text-align: center; margin-left:100px; margin-right:100px;width: 1700px;overflow:auto;">
        			${listaCodigos}
        	</div>
        	<div style="text-align: center; margin-left:100px;">
	        	<div id="divAviso" style="color:red; display: none;">  
	            			<label for="smt" style="color:red;">Seleccione todos los campos</label>
	            </div>
	            <BR>
	            
	            <c:if test="${aviso}">
		        	<div style="text-align: center">  
							<label for="smt" style="color:red;">Pieza no registrada en hoja de ruta.</label>
		            </div>
	            </c:if>
	            
	            <c:if test="${estadoCarga}">
		        	<div style="text-align: center">  
							<label for="smt" style="color:red;">Hoja de Ruta ya gestionada</label>
		            </div>
	            </c:if>
	            <div>
	            <input id="mensaje" name="mensaje" type="text" value="" title="Mensaje" placeholder="" style="border: none; color: green;">
	            </div>
	            <c:if test="${datos}">
		        	<div>  
		        		<input readonly="readonly" hidden="" type="text" id="id_Ruta" value="${ruta}"  name="id_Ruta"/>
		        		<input readonly="readonly" hidden="" type="text" id="id_size" value="${size}"  name="id_size"/>
		            	<button id="btnGuardar" style="width: 150px;" class="boton-default" >Guardar</button>  
		            </div>
	            </c:if>
	            <input readonly="readonly" hidden="" type="text" id="pqr" value="${pqr}"  name="pqr"/>
            </div>
	   
	        
       <script type="text/javascript">

       $(function($){
           $.datepicker.regional['es'] = {
               closeText: 'Cerrar',
               prevText: '<Ant',
               nextText: 'Sig>',
               currentText: 'Hoy',
               monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
               monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
               dayNames: ['Domingo', 'Lunes', 'Martes', 'Mi�rcoles', 'Jueves', 'Viernes', 'S�bado'],
               dayNamesShort: ['Dom','Lun','Mar','Mi�','Juv','Vie','S�b'],
               dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','S�'],
               weekHeader: 'Sm',
               dateFormat: 'dd/mm/yy',
               firstDay: 1,
               isRTL: false,
               showMonthAfterYear: false,
               yearSuffix: ''
           };
           $.datepicker.setDefaults($.datepicker.regional['es']);
       });
       
       function cambiarFocus(){
    	   $('input[name=codBarra]').focus();
       }
       
       $(document).ready(function (){
    	   var pqr=$("#pqr").val();
    	   console.log("PQR "+pqr)
    	   selectMotivo(1);
    	   selectSituacion(1);
    	   console.log("cantidad: "+$("#id_size").val());
    	   var c=$("#id_size").val();
    	   for(i=1;i<=c;i++){
    	   $( "#fecEntrega"+i ).datepicker({
    	       	minDate: -60, //60 DIAS ATRAS
    	           defaultDate: "0",
    	           changeMonth: true,
    	           numberOfMonths: 1
    	         });
       		}
    	   
    	   if(pqr==2){
    		   guardarpqr();
    	   }
    	   
       });
      
       
       
       function selectSituacion(i){
       	var idSituacion = $("#tipoSituacion"+i).val();
       	var situa = (idSituacion.split("_")[0]);
       	if(situa==2){
       		$("#tipoEstado"+i).hide();
       		
       	}else{
       		$("#tipoEstado"+i).show();
       		
       	}
       	if(situa==1 || situa==3){
       		$("#tipoVinculo"+i).hide();
       		$("#reciPor"+i).hide();
       	}else{
       		$("#tipoVinculo"+i).show();
       		$("#reciPor"+i).show();
       	}
       	console.log("idddddd+ "+situa);
// 			$.ajax({
// 				type: "POST", 
// 				url: "getEstadosMotivos.htm", 
// 				data: { idSituacion: idSituacion}, 
// 				cache: false})
// 				.done(function( data ) {
// 					$("#tipoMotivo"+i).html(data.combo1);
// 		 			$("#tipoEstado"+i).html(data.combo2);
// 				});
		 }
       
       function selectMotivo(i){
          	var idMotivo = $("#tipoMotivo"+i).val();
          	var moti = (idMotivo.split("_")[0]);
          	if(moti==93){
          		$("#tipoDomicilio"+i).hide();
          		$("#tipoVinculo"+i).hide();
          		$("#reciPor"+i).hide();
          		$("#fecEntrega"+i).hide();
          		$("#hora"+i).hide();
          		
          	}else{
          		$("#tipoDomicilio"+i).show();
          		$("#fecEntrega"+i).show();
          		$("#hora"+i).show();
          		
          	}
          	
          	console.log("motivo+ "+moti);
   			
   		 }
       
       $("#btnGuardar").click(function() {//para masivos
    	   var size	= $("#id_size").val();
		   var id_ruta= $("#id_Ruta").val();
		   var idMotivo = $("#tipoMotivo"+1).val();
           var moti = (idMotivo.split("_")[0]);
		   var estado=1;
    	   var listRutas = {
                   rutas : []
               };
            console.log("tama�o de tabla: "+size);   
           for(i = 1; i <= size; i++){
        	   listRutas.rutas.push({
                   "codigo" : $("#codBar"+i).val(),
                   "situacion"            : $("#tipoSituacion"+i).val(),
                   "estado"  : $("#tipoEstado"+i).val(),
                   "motivo"       : $("#tipoMotivo"+i).val(),
                   "domicilio"       : $("#tipoDomicilio"+i).val(),
                   "vinculo"       : $("#tipoVinculo"+i).val(),
                   "recibido"       : $("#reciPor"+i).val(),
                   "fecRec"       : $("#fecEntrega"+i).val(),
                   "hora"       : $("#hora"+i).val(),
                   "comentario"       : $("#coment"+i).val(),
               });
        	   if(moti==93){
        		   if($("#tipoSituacion"+i).val()=="" || $("#tipoMotivo"+i).val()==""){
            		   estado=0;
            	   }
        	   }else{
        		   if($("#tipoSituacion"+i).val()=="" || $("#tipoMotivo"+i).val()=="" || $("#tipoDomicilio"+i).val()=="" || $("#fecEntrega"+i).val()=="" || $("#hora"+i).val()==""){
            		   estado=0;
            	   }
        	   }
        	   
           }
           var jsonRutas  = JSON.stringify(listRutas);
           if(estado==0){
        	   $("#divAviso").show();
        	   console.log("Ingrese todos los campos");
           }else{
        	   console.log("Entro");
        	   $("#divAviso").hide();
               $.ajax({
                  url: "guardar_estados.htm", 
                  type: "POST",  
                  data : { 
                	  jsonRutas : jsonRutas,
                	  id_ruta	: id_ruta,
                          },
                  }).done(function(data){
               	   console.log("actualiz� "+data.cargados);
               	   $("#mensaje").val("Se guard� la gestion de hoja de ruta"); 
               	   $("#btnGuardar").hide();
               	   $("#cargadas").val(data.cargados);
               });
           }
       });
       
       function guardarpqr() {//para masivos
    	   var size	= $("#id_size").val();
		   var id_ruta= $("#id_Ruta").val();
		   var idMotivo = $("#tipoMotivo"+1).val();
           var moti = (idMotivo.split("_")[0]);
		   var estado=1;
    	   var listRutas = {
                   rutas : []
               };
            console.log("tama�o de tabla: "+size);   
           for(i = 1; i <= size; i++){
        	   listRutas.rutas.push({
                   "codigo" : $("#codBar"+i).val(),
                   "situacion"            : $("#tipoSituacion"+i).val(),
                   "estado"  : $("#tipoEstado"+i).val(),
                   "motivo"       : $("#tipoMotivo"+i).val(),
                   "domicilio"       : $("#tipoDomicilio"+i).val(),
                   "vinculo"       : $("#tipoVinculo"+i).val(),
                   "recibido"       : $("#reciPor"+i).val(),
                   "fecRec"       : $("#fecEntrega"+i).val(),
                   "hora"       : $("#hora"+i).val(),
                   "comentario"       : $("#coment"+i).val(),
               });
        	   if(moti==93){
        		   if($("#tipoSituacion"+i).val()=="" || $("#tipoMotivo"+i).val()==""){
            		   estado=0;
            	   }
        	   }else{
        		   if($("#tipoSituacion"+i).val()=="" || $("#tipoMotivo"+i).val()=="" || $("#tipoDomicilio"+i).val()=="" || $("#fecEntrega"+i).val()=="" || $("#hora"+i).val()==""){
            		   estado=0;
            	   }
        	   }
        	   
           }
           var jsonRutas  = JSON.stringify(listRutas);
           if(estado==0){
        	   $("#divAviso").show();
        	   console.log("Ingrese todos los campos");
           }else{
        	   console.log("Entro");
        	   $("#divAviso").hide();
               $.ajax({
                  url: "guardar_estados.htm", 
                  type: "POST",  
                  data : { 
                	  jsonRutas : jsonRutas,
                	  id_ruta	: id_ruta,
                          },
                  }).done(function(data){
               	   console.log("actualiz� "+data.cargados);
               	   $("#mensaje").val("Se guard� la gestion de hoja de ruta"); 
               	   $("#btnGuardar").hide();
               	   $("#cargadas").val(data.cargados);
               });
           }
       }
       
$("#btnGuardarCod").click(function() {//para unicos
    	   
    	   var size	= $("#id_size").val();
		   var id_ruta= $("#id_Ruta").val();
		   var estadoo=1;
			var i=1;
			var codigo = $("#codBar"+i).val();
			var situacion            = $("#tipoSituacion"+i).val();
			//var estado  = $("#tipoEstado"+i).val();
			var motivo       = $("#tipoMotivo"+i).val();
			var domicilio       = $("#tipoDomicilio"+i).val();
			var vinculo       = $("#tipoVinculo"+i).val();
			var recibido      = $("#reciPor"+i).val();
			var fecRec       = $("#fecEntrega"+i).val();
			var hora       = $("#hora"+i).val();
			var comentario      = $("#coment"+i).val();
           
           console.log("codigo: "+codigo+" hora "+hora);
           
           
       	   if($("#tipoSituacion"+i).val()=="" || $("#tipoMotivo"+i).val()=="" || $("#tipoDomicilio"+i).val()=="" || $("#fecEntrega"+i).val()=="" || $("#hora"+i).val()==""){
       		   estadoo=0;
       	   }
           
           //var jsonRutas  = JSON.stringify(listRutas);
           if(estadoo==0){
        	   $("#divAviso").show();
        	   console.log("Ingrese todos los campos");
           }else{
        	   console.log("Entro");
        	   $("#divAviso").hide();
               $.ajax({
                  url: "guardar_estados_Xcodbar.htm", 
                  type: "POST",  
                  data : { 
                	  id_ruta	: id_ruta,
                	  codigo : codigo,
                	  situacion : situacion,
                	  estado : estado,
                	  motivo : motivo,
                	  domicilio : domicilio,
                	  vinculo : vinculo,
                	  recibido : recibido,
                	  fecRec : fecRec,
                	  hora : hora,
                	  comentario : comentario
                          },
                  }).done(function(data){
               	   console.log("datitos");
               	   $("#mensaje").val("Se guard� la gestion de hoja de ruta"); 
               	   $("#btnGuardar").hide();
               });
           }
       });
       </script>
        </div>
        <%@include file="../include-footer.jsp" %>
    </body>
</html>














<%-- <%@include file="../include-param.jsp" %> --%>
<!-- <!DOCTYPE html> -->
<!-- <html lang="en"> -->
<!--     <head> -->
<%--         <%@include file="../include-head.jsp" %> --%>
<%--         <%@include file="../include-head-jquery-ui.jsp" %> --%>
<!--         <link rel="stylesheet" href="../static/css/default-forms.css"> -->
<!--         <link rel="stylesheet" href="../static/css/default-grids.css"> -->
<!--         <script type="text/javascript"> -->
        	
<!--         </script> -->
<!--     </head> -->
<!--     <body onload="$('#codBar').select();"> -->
<%--     	<%@include file="../include-menu.jsp" %> --%>
<!--         <div class="content"> -->
<!--             <div class="content-form-generic"> -->
<!--             	<h2>Buscar por Hoja Ruta: </h2> -->
<!--             	<form action="consulta_buscar_hojaruta.htm" class="form-generic" method="post"> -->
            		
<!--             		<div> -->
<!--             			<label for="codBar">Codigo de Hoja Ruta:</label> -->
<%--             			<input id="codBar" type="text" name="codBar" value="${codBar }" title="Ingrese codigo de cargo / barras" placeholder="codigo de cargo" size="15" maxlength="16"> --%>
<%--             			<input hidden="" id="codBarra" type="text" name="codBarra" value="${codBar }" title="Ingrese codigo de cargo / barras" placeholder="codigo de cargo" size="25" maxlength="25"> --%>
<!--             		</div> -->

<!--             		<div> -->
<!--             			<label for="smt"> </label> -->
<!-- 						<input type="submit" class="boton-default" style="min-width: 130px;" value=" Buscar " id="smt"/> -->
<!-- 					</div> -->
<!--             	</form> -->
<!--             </div> -->
<!--             <div class="content-form-generic" style="text-align: left;"> -->
<!--             	<img class="info-generic-img" style="border: 0;" src="../static/img/img_consultas.png"> -->
<!--             </div>	 -->
<!-- 	   </div> -->
	        
<!-- 	        <script type="text/javascript"> -->

<!-- 	        </script> -->
<!--         </div> -->
<%--         <%@include file="../include-footer.jsp" %> --%>
<!--     </body> -->
<!-- </html> -->