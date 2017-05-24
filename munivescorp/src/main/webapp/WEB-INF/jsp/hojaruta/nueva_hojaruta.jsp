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
       			<form action="generar_ruta.htm" class="form-generic" method="post">
            		<div>
						<label for="fecha">Fecha:</label>
						<input required="required" readonly="readonly" type="text" value="${not empty RPRuta?RPRuta.fecha:fecha}" required="required" name="fecha" id="fecha" size="10" maxlength="10" title="Fecha" style="width: 120px;" placeholder="Dia/Mes/A�o"/>
					</div>
					<div>
            			<label for="ruta">Ruta:</label>
            			<input readonly="readonly" id="ruta" name="ruta" type="text" value="${not empty RPRuta?RPRuta.ruta:ruta}" title="Ruta" placeholder="ruta" size="10" maxlength="10">
            		</div>
					<div >
            			<label  class="label" for="choiceMensajero">Mensajero:</label>
            			<select required="required" id="choiceMensajero" name="choiceMensajero">
            				<option value="">::SELECCIONAR::</option>
            				<c:forEach items="${ListaMensajeros}" var="mensajero" >
	            				<option value="${mensajero.idMensajero}" ${choiceMensajero==mensajero.idMensajero?"selected='selected'":""}>${mensajero.apellidoPat} ${mensajero.apellidoMat} ${mensajero.nombres}</option>
	            			</c:forEach> 
            			</select>
            		</div>
            		
            		<div >
            			<label  class="label" for="choiceZona">Zona:</label>
            			<select required="required" id="choiceZona" name="choiceZona">
            				<option value="">::SELECCIONAR::</option>
            				<c:forEach items="${ListaZonas}" var="zonas" >
	            				<option value="${zonas.codigo}" ${choiceZona==zonas.codigo?"selected='selected'":""}>${zonas.descripcion}</option>
	            			</c:forEach> 
            			</select>
            		</div>
            		<c:if test="${botonGenerar}">
            		<input type="submit" class="boton-default" value="Generar"/>
            		</c:if>
            	</form>
            	
            	<c:if test="${botonNueva}">
            	<div id="divNuevaHoja"> 
            		<form action="nueva_ruta.htm" class="form-generic" method="post">
            		<select required="required" id="choiceMensajero" name="choiceMensajero" hidden="">
            				<option value="">::SELECCIONAR::</option>
            				<c:forEach items="${ListaMensajeros}" var="mensajero" >
	            				<option value="${mensajero.idMensajero}" ${choiceMensajero==mensajero.idMensajero?"selected='selected'":""}>${mensajero.apellidoPat} ${mensajero.apellidoMat} ${mensajero.nombres}</option>
	            			</c:forEach> 
            			</select> 
            		<input required="required" readonly="readonly" type="text" value="${not empty RPRuta?RPRuta.fecha:fecha}" required="required" name="fecha" id="fecha" size="10" maxlength="10" title="Fecha" style="width: 120px;" placeholder="Dia/Mes/A�o" hidden=""/>
            		<button id="btnNuevaHoja" style="width: 200px;" class="boton-default" >Nueva Hoja Ruta</button>
            		 </form> 
            	</div>
            	</c:if>
            	
       		 </div>
     		<c:if test="${generar}">
       		 <div class="content-frame-generic">
       		 	<h2>Agregar</h2>
       		 		<form action="generar_ruta.htm" class="form-generic" method="post">
            		<div>
						<label for="fecha">Fecha:</label>
						<input required="required" readonly="readonly" type="text" value="${not empty RPRuta?RPRuta.fecha:fecha}" required="required" name="fecha" id="fecha" size="10" maxlength="10" title="Fecha" style="width: 120px;" placeholder="Dia/Mes/A�o" onchange="listarRutas()" disabled/>
					</div>
					<div>
            			<label for="ruta">Ruta:</label>
            			<input id="ruta" name="ruta" type="text" value="${not empty RPRuta?RPRuta.ruta:ruta}" title="Ruta" placeholder="ruta" size="10" maxlength="10" disabled>
            		</div>
					<div>
            			<label  class="label" for="choiceMensajero">Mensajero:</label>
            			<select required="required" id="choiceMensajero" name="choiceMensajero" onchange="listarRutas()" disabled>
            				<option value="">::SELECCIONAR::</option>
            				<c:forEach items="${ListaMensajeros}" var="mensajero" >
	            				<option value="${mensajero.idMensajero}" ${choiceMensajero==mensajero.idMensajero?"selected='selected'":""}>${mensajero.apellidoPat} ${mensajero.apellidoMat} ${mensajero.nombres}</option>
	            			</c:forEach> 
            			</select>
            		</div>
            		<div >
            			<label  class="label" for="choiceZona">Zona:</label>
            			<select required="required" id="choiceZona1" name="choiceZona1" disabled>
            				<option value="">::SELECCIONAR::</option>
            				<c:forEach items="${ListaZonas}" var="zonas" >
	            				<option value="${zonas.codigo}" ${choiceZona==zonas.codigo?"selected='selected'":""}>${zonas.descripcion}</option>
	            			</c:forEach> 
            			</select>
            		</div>
            		
            	</form>
            	

       		 		<div id="divGenerar">
       				<input readonly="readonly" id="mensaje" name="mensaje" size="150" type="text" value="" title="Mensaje" placeholder="" style="border: none; color: red;">
       		
					<input readonly="readonly" hidden="" type="text" id="idRuta" name="idRuta" value="${idRuta}" name="estadoToEdit"/>
					<div>
            			<label for="codigo">Codigo de barra:</label>
            			<input id="codigo" name="codigo" type="text" value="" title="Codigo" placeholder="codigo" size="25" maxlength="25" onchange="agregarCodBar();" autofocus>
            		</div>
            			
            		<div>  
            			<button id="btnAgregarCod" style="width: 150px;" class="boton-default" >Agregar</button>  
            		</div>
            		</div>
       		 </div>
       		 <div id="divImprimir" style="text-align: center; display: none;">  
            	<button id="btnImprimir" style="width: 150px;" class="boton-default" >Imprimir</button>  
            </div>
            <div id="divLoading" style="text-align: center; display: none;">
					<img alt="Cargando..." src="../static/img/loader.gif" style="border: 0;">
			</div>
       		 <div id="contTabla">
       		
       		</div>
       		
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
	 					//console.log("ENTRO"+data.tablita);
	 					$("#contTabla").html(data.tablita);	
	                   })			
	 		 }  
			
			function testAgregar(){
				alert(".l.");
			}
			
			$("#btnCrearRuta").click(function() {
                var idConsulta      =$("#inputId").val();
                var asunto          =$("#inputAsunto").val();
                var descripcion     =$("#inputDescripcion").val();
                        $.ajax({
                           url: "nueva_ruta.htm", 
                           type: "POST",  
                           data : { 
                                    "idConsulta" : idConsulta,
                                    "asunto" : asunto,
                                    "descripcion" : descripcion,
                                    
                                   },
                           }).done(function(data){
                        	   console.log("datitos");
                                            
                        });
            });    
			
			/*$("#btnAgregarCod").click(function() {
	        	var idRuta = $("#idRuta").val();
	        	var codigoB = $("#codigo").val();
	        	var fecha = $("#fecha").val();
	        	var hoja = $("#ruta").val();
	        	var mensajero = $("#choiceMensajero").val();
	        	var zona = $("#choiceZona").val();
	        	console.log("datos: "+idRuta+" "+codigo+" "+fecha);
	 			$.ajax({
	 				type: "POST",
	 				url: "listar_ruta_cod.htm", 
	 				data: { idRuta: idRuta,
	 					codigoB: codigoB,
	 					fecha: fecha,
	 					hoja: hoja,
	 					mensajero: mensajero,
	 					zona:zona} 

	 				}).done(function(data){
	 					console.log("ENTRO"+data.tablita);
	 					$("#contTabla").html(data.tablita);	
	 					$("#mensaje").val(data.mensaje);
	 					console.log("mensaje: "+data.mensaje);
	 					$("#codigo").val("");
	                   });			
	 		 });*/
			
			function agregarCodBar(){
				var idRuta = $("#idRuta").val();
	        	var codigoB = $("#codigo").val();
	        	var fecha = $("#fecha").val();
	        	var hoja = $("#ruta").val();
	        	var mensajero = $("#choiceMensajero").val();
	        	var zona = $("#choiceZona").val();
	        	console.log("datos: "+idRuta+" "+codigo+" "+fecha);
	 			$.ajax({
	 				type: "POST",
	 				url: "listar_ruta_cod.htm", 
	 				data: { idRuta: idRuta,
	 					codigoB: codigoB,
	 					fecha: fecha,
	 					hoja: hoja,
	 					mensajero: mensajero,
	 					zona:zona} 

	 				}).done(function(data){
	 					if(!data.mensaje_coor==""){
	 						alert(data.mensaje_coor);
	 					}
	 					$("#contTabla").html(data.tablita);	
	 					$("#mensaje").val(data.mensaje);
	 					console.log("mensaje: "+data.mensaje);
	 					$("#codigo").val("");
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
	 					//div = document.getElementById("divGenerar");
	 	                //div.style.display = "none";
	 	               $("#divImprimir").show();
	 	              $("#btnCerrarHoja").hide();
	                   });			
	 		 });
			
			$("#btnImprimir").click(function() {
				$("#divLoading").show();
	        	var idRuta = $("#idRuta").val();
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
	                   })	
			}
			
			function funcionVer(cadena) {
			//	var name = "nasqa.values/pdf/hojaruta/"; // para el local
			
				var name = "pdf/hojaruta/";   //pra crear el root war 
				var fileName = "hojaruta"+cadena+".pdf";
					$("#dialog").dialog({
		                modal: true,
		                title: fileName,
		                width: 820,
		                height: 800,
		                closeButton:true,

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