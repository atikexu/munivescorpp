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
    <body onload="$('#codBar').select();">
    	<%@include file="../include-menu.jsp" %>
        <div class="content">         
       		 <div class="content-frame-generic">
       		 <h2> &nbsp; </h2>
       			<form action="pdf_ruta.htm" class="form-generic" method="post">
            		<div>
            			<input readonly="readonly" hidden="" type="text" id="id" value="${t_id}"  name="id"/>
            			<input readonly="readonly" hidden="" type="text" id="id_fecha" value="${t_fecha}"  name="id_fecha"/>
            			<input readonly="readonly" hidden="" type="text" id="id_ruta" value="${t_ruta}"  name="id_ruta"/>
            			<input readonly="readonly" hidden="" type="text" id="id_mensajero" value="${t_mensajero}"  name="id_mensajero"/>
            			<input readonly="readonly" hidden="" type="text" id="id_piezas" value="${t_piezas}"  name="id_piezas"/>
            			<input readonly="readonly" hidden="" type="text" id="id_estado" value="${t_estado}"  name="id_estado"/>
            			<input readonly="readonly" hidden="" type="text" id="id_idmensajero" value="${t_idmensajero}"  name="id_idmensajero"/>
            			<input readonly="readonly" hidden="" type="text" id="id_zona" value="${t_zona}"  name="id_zona"/>
            		</div>
            		<div>
						<label for="fecha">Fecha: </label>
						<input required="required" readonly="readonly" type="text" value="${t_fecha}" required="required" name="fecha" id="fecha" size="30" maxlength="30" title="Fecha" placeholder="Dia/Mes/Año"  disabled/>
					</div>
					<div>
            			<label for="ruta">Ruta: </label>
            			<input id="ruta" name="ruta" type="text" value="${t_ruta}" title="Ruta" placeholder="ruta" size="30" maxlength="30" disabled/>
            		</div>
					<div>
            			<label for="mensajero">Mensajero: </label>
            			<input id="mensajero" name="mensajero" type="text" value="${t_mensajero}" title="Mensajero" placeholder="mensajero" size="30" maxlength="30" disabled/>
            		</div>
            		
            		<div>
            			<label for="piezas">Piezas: </label>
            			<input id="piezas" name="piezas" type="text" value="${t_piezas}" title="Ruta" placeholder="ruta" size="30" maxlength="30" disabled/>
            		</div>
            		<input type="submit" class="boton-default" value="Imprimir" style="display: none"/>
            	</form>
					
            		<button id="btnImprimir" style="width: 150px;" class="boton-default" >Imprimir</button>
            		
            		<div id="divLoading" style="text-align: center; display: none;">
					<img alt="Cargando..." src="../static/img/loader.gif" style="border: 0;">
					</div>  
            	
       		 </div>
       		 
       		 <c:if test="${generar}">
       		 <div id="divGenerar" class="content-frame-generic">
       		 	<h2>Agregar</h2>
       				<input readonly="readonly" id="mensaje" name="mensaje" size="150" type="text" value="" title="Mensaje" placeholder="" style="border: none; color: red;">
       		
					<input readonly="readonly" hidden="" type="text" id="idRuta" name="idRuta" value="${t_id}" name="estadoToEdit"/>
					<div>
            			<label for="codigo">Codigo de barra:</label>
            			<input id="codigo" name="codigo" type="text" value="" title="Codigo" placeholder="codigo" size="25" maxlength="25" onchange="agregarCodBar();" style="color:blue;" autofocus>
            		</div>
            			
            		<div>  
            			<button id="btnAgregarCod" style="width: 150px;" class="boton-default" >Agregar</button>  
            		</div>
       		 </div>
 			</c:if>
 			<div></div>
			
        	<div id="contTabla">
        			${ListaPiezas}
        	</div>
        	
        	<c:if test="${cerrar}">
	           	<div>  
	            	<button id="btnCerrarHoja" style="width: 150px;" class="boton-default" >Cerrar hoja ruta</button>  
	            </div>		
            </c:if>	
            <div id="dialog" style="display: none">
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
	                dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
	                dayNamesShort: ['Dom','Lun','Mar','Mié','Juv','Vie','Sáb'],
	                dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sá'],
	                weekHeader: 'Sm',
	                dateFormat: 'dd/mm/yy',
	                firstDay: 1,
	                isRTL: false,
	                showMonthAfterYear: false,
	                yearSuffix: ''
	            };
	            $.datepicker.setDefaults($.datepicker.regional['es']);
	        });    
	        $( "#fecha" ).datepicker({
	        	minDate: -60, //60 DIAS ATRAS
	            defaultDate: "0",
	            changeMonth: true,
	            numberOfMonths: 1
	          });
       		 
			function listarRutas(){
	        	var fec = $("#fecha").val();
	        	var idMen = $("#choiceMensajero").val(); 
	 			 console.log("DATOS: "+fec+" "+idMen);
	 			$.ajax({
	 				type: "POST",
	 				url: "listar_ruta.htm", 
	 				data: { fec: fec,
	 					idMen:idMen} 

	 				}).done(function(data){
	 					$("#contTabla").html(data.tablita);	
	                   })			
	 		 }   
			
			$("#btnCrearRutaCod").click(function() {
	        	var idRuta = $("#idRuta").val();
	        	var codigo = $("#codigo").val();
	        	var fecha = $("#fecha").val();
	        	console.log("datos: "+idRuta+" "+codigo+" "+fecha);
	 			$.ajax({
	 				type: "POST",
	 				url: "listar_ruta_cod.htm", 
	 				data: { idRuta: idRuta,
	 					codigo: codigo,
	 					fecha: fecha} 

	 				}).done(function(data){
	 					$("#contTabla").html(data.tablita);	
	 					$("#mensaje").val(data.mensaje);
	 					console.log("mensaje: "+data.mensaje);
	                   })			
	 		 });
			
			function btn_eliminar(id_rut){
				var idRuta = $("#idRuta").val();
	 			$.ajax({
	 				type: "POST",
	 				url: "eliminar_ruta_cod.htm", 
	 				data: { id_rut: id_rut,
	 					idRuta:idRuta
	 					} 

	 				}).done(function(data){
	 					$("#contTabla").html(data.tablita);	
	 					$("#piezas").val(data.piezas);	
	                   })	
			}
			
			/*$("#btnAgregarCod").click(function() {
	        	var idRuta = $("#idRuta").val();
	        	var codigo = $("#codigo").val();
	        	var fecha = $("#fecha").val();
	        	console.log("datos: "+idRuta+" "+codigo+" "+fecha);
	 			$.ajax({
	 				type: "POST",
	 				url: "listar_ruta_codF.htm", 
	 				data: { idRuta: idRuta,
	 					codigo: codigo,
	 					fecha: fecha} 

	 				}).done(function(data){
	 					console.log("ENTRO"+data.tablita);
	 					$("#contTabla").html(data.tablita);	
	 					$("#mensaje").val(data.mensaje);
	 					console.log("mensaje: "+data.mensaje);
	                   });			
	 		 });*/
			
			function agregarCodBar(){
				var idRuta = $("#idRuta").val();
	        	var codigoB = $("#codigo").val();
	        	var fecha = $("#fecha").val();
	        	var hoja = $("#ruta").val();
	        	var mensajero = $("#mensajero").val();
	        	var idmensajero = $("#id_idmensajero").val();
	        	var zona = $("#id_zona").val();
	        	console.log("datos: "+idRuta+" "+codigo+" "+fecha);
	 			$.ajax({
	 				type: "POST",
	 				url: "listar_ruta_codF.htm", 
	 				data: { idRuta: idRuta,
	 					codigoB: codigoB,
	 					fecha: fecha,
	 					hoja: hoja,
	 					mensajero: mensajero,
	 					zona:zona,
	 					idmensajero:idmensajero} 

	 				}).done(function(data){
	 					if(!data.mensaje_coor==""){
	 						alert(data.mensaje_coor);
	 					}
	 					$("#contTabla").html(data.tablita);	
	 					$("#mensaje").val(data.mensaje);
	 					console.log("mensaje: "+data.mensaje);
	 					$("#codigo").val("");
	 					$("#piezas").val(data.piezas);
	                   });	
			}
	 		 
	 		$("#btnCerrarHoja").click(function() {
	        	var idRuta = $("#idRuta").val();
	        	var nroHoja = $("#ruta").val();
	        	console.log("datos: "+idRuta);
	 			$.ajax({
	 				type: "POST",
	 				url: "cerrar_ruta_cod.htm", 
	 				data: { idRuta: idRuta,
	 					nroHoja: nroHoja
	 					} 

	 				}).done(function(data){
	 					console.log("ENTRO"+data.tablita);
	 					$("#contTabla").html(data.tablita);	
	 					$("#mensaje").val(data.mensaje);
	 					console.log("mensaje: "+data.mensaje);
	 					$("#btnCerrarHoja").hide();
// 	 					div = document.getElementById("divGenerar");
// 	 	                div.style.display = "none";
	                   });			
	 		 });
	 		
	 		$("#btnImprimir").click(function() {
				$("#divLoading").show();
	        	var idRuta = $("#id").val();
	        	console.log("datos: "+idRuta);
	 			$.ajax({
	 				type: "POST",
	 				url: "pdf_ruta_nuevo.htm", 
	 				data: { idRuta: idRuta
	 					} 

	 				}).done(function(data){
	 					setTimeout("funcionVer("+data.cadena+")",5500);
	 					console.log("mensaje: "+data.mensaje);

	                   });			
	 		 });
	 		function funcionVer(cadena) {
				//var name = "nasqa.values/pdf/hojaruta/";
				
				var name = "pdf/hojaruta/";
				var fileName = "hojaruta"+cadena+".pdf";
					$("#dialog").dialog({
		                modal: true,
		                title: fileName,
		                width: 820,
		                height: 800,
		                /*buttons: {
		                    Close: function () {
		                        $(this).dialog('cerrar');
		                    }
		                },*/
		                open: function () {
		                    var object = "<object data=\"{FileName}\" type=\"application/pdf\" width=\"800px\" height=\"700px\">";
		                    object += "If you are unable to view file, you can download from <a href = \"{FileName}\">here</a>";
		                    object += " or download <a target = \"_blank\" href = \"http://get.adobe.com/reader/\">Adobe PDF Reader</a> to view the file.";
		                    object += "</object>";
		                    object = object.replace(/{FileName}/g, "/" + name+fileName);
		                    $("#dialog").html(object);
		                   $("#divLoading").hide();
		                }
		            });
            }
       		 </script>
   	       </div> 
        <%@include file="../include-footer.jsp" %>
    </body>
</html>