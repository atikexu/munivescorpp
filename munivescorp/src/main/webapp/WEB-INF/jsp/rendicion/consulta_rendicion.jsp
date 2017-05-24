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
       			<form action="pdf_rendicion.htm" class="form-generic" method="post">
	            	<!--<div style="text-align: center;">
			    		<span id="formAlert">
			    			<c:if test="${success}"><span class="icon-checkmark"></span> ${empty info?"El Empleado fue guardado correctamente.":info }</c:if>
			    			<c:if test="${error}"><span class="icon-notification"></span> Error: ${cause}</c:if>
			    		</span>
			    	</div>-->
            		<div>
            			<input readonly="readonly" hidden="" type="text" id="id" value="${t_id}"  name="id"/>
            			<input readonly="readonly" hidden="" type="text" id="t_fecha" value="${t_fecha}"  name="t_fecha"/>
            			<input readonly="readonly" hidden="" type="text" id="t_cliente" value="${t_cliente}"  name="t_cliente"/>
            			<input readonly="readonly" hidden="" type="text" id="id_mensajero" value="${t_mensajero}"  name="id_mensajero"/>
            			<input readonly="readonly" hidden="" type="text" id="t_piezas" value="${t_piezas}"  name="t_piezas"/>
            			<input readonly="readonly" hidden="" type="text" id="t_estado" value="${t_estado}"  name="t_estado"/>
            			<input readonly="readonly" hidden="" type="text" id="t_nroRendicion" value="${t_nroRendicion}"  name="t_nroRendicion"/>
            		</div>
            		<div>
						<label for="fecha">Fecha: </label>
						<input required="required" readonly="readonly" type="text" value="${t_fecha}" required="required" name="fecha" id="fecha" size="30" maxlength="30" title="Fecha" placeholder="Dia/Mes/A�o"  disabled/>
					</div>
					<div>
            			<label for="cliente">Cliente: </label>
            			<input id="cliente" name="cliente" type="text" value="${t_cliente}" title="Cliente" placeholder="cliente" size="30" maxlength="30" disabled/>
            		</div>
            		
            		<div>
            			<label for="piezas">Piezas: </label>
            			<input id="piezas" name="piezas" type="text" value="${t_piezas}" title="Ruta" placeholder="ruta" size="30" maxlength="30" disabled/>
            		</div>
            		
            		<div>
						<label for="codigo">Codigo</label>
            			<input id="campo1" name="campo1" readonly="readonly" type="text" value="${codigo}" title="Codigo" placeholder="" size="15" maxlength="25">
					</div>
					
					<div>
						<label for="descripcion">Descripci�n</label>
            			<input id="campo2" name="campo2" readonly="readonly" type="text" value="${descripcion}" title="Descripcion" placeholder="" size="25" maxlength="50">
					</div>
					
					<div>
						<label for="detalle">Detalle</label>
            			<input id="campo3" name="campo3" readonly="readonly" type="text" value="${detalle}" title="Detalle" placeholder="" size="35" maxlength="50">
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
       		
					<input readonly="readonly" hidden="" type="text" id="idRendicion" name="idRendicion" value="${t_id}" name="estadoToEdit"/>
					<div >
            			<label for="codMotivo">Motivo:</label>
            			<select required="required" id="codMotivo" name="codMotivo">
            				<option value="">::SELECCIONAR::</option>
            				${listaMotivoss}
            			</select>
            		</div>
					<div>
            			<label for="codigo">Codigo de barra:</label>
            			<input id="codigo" name="codigo" type="text" value="" title="Codigo" placeholder="codigo" size="25" maxlength="25" onchange="agregarCodBar();" style="color:blue;" autofocus>
            		</div>
            			
            		<div>  
            			<button id="btnAgregarCod" style="width: 150px;" class="boton-default" >Agregar</button>  
            		</div>
       		 </div>
 			</c:if>

        	<div id="contTabla">
        			${ListaPiezas}
        	</div>
        	<!--  
        	<c:if test="${generar}">
	           	<div>  
	            	<button id="btnCerrarRendicion" class="boton-default" >Cerrar rendicion</button>  
	            </div>		
            </c:if>	
            -->
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
	        $( "#fecha" ).datepicker({
	        	minDate: -360, //60 DIAS ATRAS
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
	 					//console.log("ENTRO"+data.tablita);
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
	 					//console.log("ENTRO"+data.tablita);
	 					$("#contTabla").html(data.tablita);	
	 					$("#mensaje").val(data.mensaje);
	 					console.log("mensaje: "+data.mensaje);
	                   })			
	 		 });
			
			function btn_eliminar(id_ren_det){
				var idRendicion = $("#idRendicion").val();
	 			$.ajax({
	 				type: "POST",
	 				url: "eliminar_rendicion_cod.htm", 
	 				data: { id_ren_det: id_ren_det,
	 					idRendicion:idRendicion
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
				var idRendicion = $("#idRendicion").val();
	        	var codigoB = $("#codigo").val();
	        	var fecha = $("#fecha").val();
	        	var motivo = $("#codMotivo").val();
	        	var nroRendicion = $("#t_nroRendicion").val();
	        	console.log("datos: "+idRendicion+" "+codigo+" "+fecha);
	 			$.ajax({
	 				type: "POST",
	 				url: "listar_rendicion_codF.htm", 
	 				data: { idRendicion: idRendicion,
	 					codigoB: codigoB,
	 					fecha: fecha,
	 					motivo: motivo,
	 					nroRendicion: nroRendicion} 

	 				}).done(function(data){
	 					console.log("ENTRO"+data.tablita);
	 					$("#contTabla").html(data.tablita);	
	 					$("#mensaje").val(data.mensaje);
	 					console.log("mensaje: "+data.mensaje);
	 					$("#codigo").val("");
	 					$("#piezas").val(data.piezas);
	                   });	
			}
	 		 
	 		$("#btnCerrarRendicion").click(function() {
	        	var idRendicion = $("#idRendicion").val();
	        	console.log("datos: "+idRendicion);
	 			$.ajax({
	 				type: "POST",
	 				url: "cerrar_rendicion_cod.htm", 
	 				data: { idRendicion: idRendicion
	 					} 

	 				}).done(function(data){
	 					console.log("ENTRO"+data.tablita);
	 					$("#contTabla").html(data.tablita);	
	 					$("#mensaje").val(data.mensaje);
	 					console.log("mensaje: "+data.mensaje);
	 					div = document.getElementById("divGenerar");
	 	                div.style.display = "none";
	                   });			
	 		 });
	 		
	 		$("#btnImprimir").click(function() {
				$("#divLoading").show();
	        	var idRendicion = $("#idRendicion").val();
	        	console.log("datos: "+idRendicion);
	 			$.ajax({
	 				type: "POST",
	 				url: "pdf_rendicion_nuevo.htm", 
	 				data: { idRendicion: idRendicion
	 					} 

	 				}).done(function(data){
	 					setTimeout("funcionVer("+data.cadena+")",5500);
	 					console.log("mensaje: "+data.mensaje);

	                   });			
	 		 });
	 		function funcionVer(cadena) {
				//var name = "nasqa.values/pdf/rendicion/";
				
				var name = "pdf/rendicion/";
				
				var fileName = "rendicion"+cadena+".pdf";
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
		                   console.log("ruta: "+object);
		                   $("#divLoading").hide();
		                }
		            });
            }
       		 </script>
   	       </div> 
        <%@include file="../include-footer.jsp" %>
    </body>
</html>