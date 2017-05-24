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
        	<c:if test="${nuevo}">     
       		 <div class="content-frame-generic">
       		 <h2> &nbsp; </h2>
       			<form action="generar_rendicion.htm" class="form-generic" method="post">
       				<div style="text-align: center;">
			    		<span id="formAlert">
			    			<c:if test="${success}"><span class="icon-checkmark"></span> ${empty info?"El Empleado fue guardado correctamente.":info }</c:if>
			    			<c:if test="${error}"><span class="icon-notification"></span> Error: ${cause}</c:if>
			    		</span>
			    	</div> 
            		<div>
						<label for="fecha">Fecha:</label>
						<input required="required" type="text" value="${not empty RPRuta?RPRuta.fecha:fecha}" required="required" name="fecha" id="fecha" size="10" maxlength="10" title="Fecha" style="width: 120px;" placeholder="Dia/Mes/A�o"/ disabled>
					</div>

					<div >
            			<label  class="label" for="codCliente">Cliente:</label>
            			<select required="required" id="codCliente" name="codCliente" disabled>
            				<option value="">::SELECCIONAR::</option>
            				<c:forEach items="${RPAdminUserClientes}" var="cliente" >
	            				<option value="${cliente.codCliente}" ${codCliente==cliente.codCliente ?"selected='selected'":"" }>${cliente.razonsocial}</option>
	            			</c:forEach> 
            			</select>
            		</div>
            		
            		<div>
						<label for="correo">Buscar en tabla:</label>
            			<input name="filter" onkeyup="filter2(this, 'table-Agencia')" type="text">
            			<input readonly="readonly" hidden="" type="text" id="d_idCliente" value="${codCliente}" name="d_idCliente"/>
            			<input readonly="readonly" hidden="" type="text" id="d_fecha" value="${fecha}" name="d_fecha"/>
            			
					</div>
					<div id="contTablaAgencia" style="height:200px;overflow:auto;">
						${listaAgencias}
       				</div>
					<div>
						<label for="codigo">C�digo:</label>
            			<input id="campo1" name="campo1" readonly="readonly" type="text" value="${codigo}" title="Codigo" placeholder="" size="15" maxlength="25">
					</div>
					
					<div>
						<label for="descripcion">Descripcion:</label>
            			<input id="campo2" name="campo2" readonly="readonly" type="text" value="${descripcion}" title="Descripcion" placeholder="" size="25" maxlength="50">
					</div>
					
					<div>
						<label for="detalle">Detalle:</label>
            			<input id="campo3" name="campo3" readonly="readonly" type="text" value="${detalle}" title="Detalle" placeholder="" size="25" maxlength="50">
					</div>
            		
            		<input type="submit" class="boton-default" value="Generar"/>
            	</form> 
       		 </div>
       		 </c:if>
     		<c:if test="${generar}">
       		 <div class="content-frame-generic">
       		 	<h2>Agregar</h2>
       		 		<form action="generar_ruta.htm" class="form-generic" method="post">
            		<div>
						<label for="fecha">Fecha:</label>
						<input required="required" type="text" value="${not empty RPRuta?RPRuta.fecha:fecha}" required="required" name="fecha" id="fecha" size="10" maxlength="10" title="Fecha" style="width: 120px;" placeholder="Dia/Mes/A�o"/ disabled>
					</div>

					<div >
            			<label  class="label" for="codCliente">Cliente:</label>
            			<select required="required" id="codCliente" name="codCliente" disabled>
            				<option value="">::SELECCIONAR::</option>
            				<c:forEach items="${RPAdminUserClientes}" var="cliente" >
	            				<option value="${cliente.codCliente}" ${codCliente==cliente.codCliente ?"selected='selected'":"" }>${cliente.razonsocial}</option>
	            			</c:forEach> 
            			</select>
            		</div>
            		
            		<div>
            			<input readonly="readonly" hidden="" type="text" id="d_idCliente" value="${codCliente}" name="d_idCliente"/>
            			<input readonly="readonly" hidden="" type="text" id="d_fecha" value="${fecha}" name="d_fecha"/>
					</div>
					<div>
						<label for="codigo">C�digo:</label>
            			<input id="campo1" name="campo1" readonly="readonly" type="text" value="${codigo}" title="Codigo" placeholder="" size="15" maxlength="25">
					</div>
					
					<div>
						<label for="descripcion">Descripci�n:</label>
            			<input id="campo2" name="campo2" readonly="readonly" type="text" value="${descripcion}" title="Descripcion" placeholder="" size="25" maxlength="50">
					</div>
					
					<div>
						<label for="detalle">Detalle:</label>
            			<input id="campo3" name="campo3" readonly="readonly" type="text" value="${detalle}" title="Detalle" placeholder="" size="35" maxlength="50">
					</div>
            	</form>
       		 		<div id="divGenerar">
       				<input readonly="readonly" id="mensaje" name="mensaje" size="150" type="text" value="" title="Mensaje" placeholder="" style="border: none; color: red;">
       		
					<input readonly="readonly" hidden="" type="text" id="idRendicion" name="idRendicion" value="${idRendicion}"/>
					<input readonly="readonly" hidden="" type="text" id="d_nroRendicion" value="${nroRendicion}" name="d_nroRendicion"/>
					<div >
            			<label for="codMotivo">Motivo:</label>
            			<select required="required" id="codMotivo" name="codMotivo">
            				<option value="">::SELECCIONAR::</option>
            				${listaMotivoss}
            			</select>
            		</div>
					<div>
            			<label for="codigo">Codigo de barra:</label>
            			<input id="codigoBarra" name="codigoBarra" type="text" value="" title="Codigo" placeholder="codigo" size="25" maxlength="25" onchange="agregarCodBar();" autofocus>
            		</div>
            			
            		<div>  
            			<button id="btnAgregarCod" style="width: 150px;" class="boton-default" >Agregar</button>  
            		</div>
            		</div>
            		<div id="divImprimir" style="text-align: center;">  
            			<button id="btnImprimir" style="width: 150px;" class="boton-default" >Imprimir</button>  
            		</div>
            		<div id="divLoading" style="text-align: center; display: none;">
					<img alt="Cargando..." src="../static/img/loader.gif" style="border: 0;">
			</div>
       		 </div>
       		 
       		 <div id="contTabla">
       		
       		</div>
       		<!--  
           	<div>  
            	<button id="btnCerrarRendicion" class="boton-default" >Cerrar Rendicion</button>  
            </div>
            -->
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
	        	minDate: -360, //60 DIAS ATRAS
	            defaultDate: "0",
	            changeMonth: true,
	            numberOfMonths: 1
	          });
       		 
	        function filter2 (phrase, _id){
       			var words = phrase.value.toLowerCase().split(" ");
       			var table = document.getElementById(_id);
       			var ele;
       			for (var r = 1; r < table.rows.length; r++){
       				ele = table.rows[r].innerHTML.replace(/<[^>]+>/g,"");
       			        var displayStyle = 'none';
       			        for (var i = 0; i < words.length; i++) {
       				    if (ele.toLowerCase().indexOf(words[i])>=0)
       					displayStyle = '';
       				    else {
       					displayStyle = 'none';
       					break;
       				    }
       			        }
       				table.rows[r].style.display = displayStyle;
       			}
       		} 
			
			$("#btnVerAgencias").click(function() {
				console.log("entro:agencias");
                var idCliente      =$("#codCliente").val();
                        $.ajax({
                           url: "listar_agencias.htm", 
                           type: "POST",  
                           data : { 
                                    "idCliente" : idCliente
                                   },
                           }).done(function(data){
                        	   $("#contTablaAgencia").html(data.tablita);	
                        	   console.log(data.tablita);
                                            
                        });
            }); 
			
			function verAgencias() {
				console.log("entro:agencias "+ $("#codCliente").val());
                var idCliente      =$("#codCliente").val();
                        $.ajax({
                           url: "listar_agencias.htm", 
                           type: "POST",  
                           data : { 
                                    "idCliente" : idCliente
                                   },
                           }).done(function(data){
                        	   $("#contTablaAgencia").html(data.tablita);	
                        	   console.log(data.tablita);
                                            
                        });
			}
			
			$(".boton").click(function(){
   	            var valores="";
   	 			var i=1;
   	            $(this).parents("tr").find("td").each(function(){
   	                valores=$(this).html()+"\n";
   	                $("#campo"+i).val(valores);	
   	                i++;
   	            });
   	        });
			
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
	        	var idRendicion = $("#idRendicion").val();
	        	var codCliente = $("#codCliente").val();
	        	var fecha = $("#fecha").val();
	        	var codBar = $("#codigoBarra").val();
	        	var motivo = $("#codMotivo").val();
	        	var nroRendicion = $("#d_nroRendicion").val();
	        	console.log("datos: "+idRendicion+" "+codCliente+" "+fecha+" "+motivo);
	 			$.ajax({
	 				type: "POST",
	 				url: "listar_rendicion_cod.htm", 
	 				data: { idRendicion: idRendicion,
	 					codCliente: codCliente,
	 					fecha: fecha,
	 					codBar: codBar,
	 					motivo: motivo,
	 					nroRendicion: nroRendicion
	 					} 

	 				}).done(function(data){
	 					console.log("ENTRO"+data.tablita);
	 					$("#contTabla").html(data.tablita);	
	 					$("#mensaje").val(data.mensaje);
	 					console.log("mensaje: "+data.mensaje);
	 					$("#codigoBarra").val("");
	                   });			
	 		 });*/
			
			function agregarCodBar(){
				var idRendicion = $("#idRendicion").val();
	        	var codCliente = $("#codCliente").val();
	        	var fecha = $("#fecha").val();
	        	var codBar = $("#codigoBarra").val();
	        	var motivo = $("#codMotivo").val();
	        	var nroRendicion = $("#d_nroRendicion").val();
	        	console.log("datos: "+idRendicion+" "+codCliente+" "+fecha+" "+motivo);
	 			$.ajax({
	 				type: "POST",
	 				url: "listar_rendicion_cod.htm", 
	 				data: { idRendicion: idRendicion,
	 					codCliente: codCliente,
	 					fecha: fecha,
	 					codBar: codBar,
	 					motivo: motivo,
	 					nroRendicion: nroRendicion
	 					} 

	 				}).done(function(data){
	 					console.log("ENTRO"+data.tablita);
	 					$("#contTabla").html(data.tablita);	
	 					$("#mensaje").val(data.mensaje);
	 					console.log("mensaje: "+data.mensaje);
	 					$("#codigoBarra").val("");
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
	 	               $("#divImprimir").show();
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
	                   })	
			}
			
			function funcionVer(cadena) {
				var name = "pdf/rendicion/";
				var fileName = "rendicion"+cadena+".pdf";
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