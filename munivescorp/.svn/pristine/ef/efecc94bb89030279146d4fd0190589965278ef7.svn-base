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
            <div class="content-form-generic">
            	<h2>Configurar direcciones</h2>
            	 <form action="guardar_direccion.htm" class="form-generic" method="post"> 
					<div style="text-align: center;">
			    		<span id="formAlert">
			    			<c:if test="${success}"><span class="icon-checkmark"></span> ${empty info?"Configuración guardada correctamente.":info }</c:if>
			    			<c:if test="${error}"><span class="icon-notification"></span> Error: ${cause}</c:if>
			    		</span>
			    	</div>
			    	<div>
            			<input readonly="readonly" hidden="" type="text" id="id1" name="id1" value=""/>
            		</div>
            		<div>
            			<input readonly="readonly" hidden="" type="text" id="id2" name="id2" value=""/>
            		</div>
            		<div>
            			<input readonly="readonly" hidden="" type="text" id="id3" name="id3" value=""/>
            		</div>
            		<div>
            			<input readonly="readonly" hidden="" type="text" id="id4" name="id4" value=""/>
            		</div>
            		<div >
            			<label  class="label" for="codCliente">Cliente:</label>
            			<select required="required" id="codCliente" name="codCliente" onchange="selectCliente()">
            				<option value="">::SELECCIONAR::</option>
            				<c:forEach items="${RPAdminUserClientes}" var="cliente" >
	            				<option value="${cliente.codCliente}" ${codigoCliente==cliente.codCliente ?"selected='selected'":"" }>${cliente.razonsocial}</option>
	            			</c:forEach> 
            			</select>
            		</div>
            		<div >
            			<label  class="label" for="productos">Productos:</label>
            			<select required="required" id="productos" name="productos" onchange="cargarDirecciones()">
            				<option value="">::SELECCIONAR::</option>
            				
            			</select>
            		</div>
            		
            		
            		<div class="vis1">
            			<label  class="label" for="visita1">Visita 1:</label>
            			<select required="required" id="visita1" name="visita1">
            				<option value="">::SELECCIONAR::</option>
            				<option value="1">DOMICILIO</option>
            				<option value="2">LABORAL</option>
            				<option value="3">OPCIONAL</option>
            			</select>
            		</div>
            		
            		<div class="vis2">
            			<label  class="label" for="visita2">Visita 2:</label>
            			<select required="required" id="visita2" name="visita2">
            				<option value="">::SELECCIONAR::</option>
            				<option value="1">DOMICILIO</option>
            				<option value="2">LABORAL</option>
            				<option value="3">OPCIONAL</option>
            			</select>
            		</div>
            		
            		<div class="vis3">
            			<label  class="label" for="visita3">Visita 3:</label>
            			<select id="visita3" name="visita3">
            				<option value="">::SELECCIONAR::</option>
            				<option value="1">DOMICILIO</option>
            				<option value="2">LABORAL</option>
            				<option value="3">OPCIONAL</option>
            			</select>
            		</div>
            		
            		<div class="vis4">
            			<label  class="label" for="visita4">Visita 4:</label>
            			<select id="visita4" name="visita4">
            				<option value="">::SELECCIONAR::</option>
            				<option value="1">DOMICILIO</option>
            				<option value="2">LABORAL</option>
            				<option value="3">OPCIONAL</option>
            			</select>
            		</div>

            
            		<div>
            			<label for="smt"> </label>
						<input type="submit" class="boton-default" style="min-width: 130px;" value="Guardar Configuración" id="smt"/>
					</div>
            	</form>
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
	        $( "#fecEnvio" ).datepicker({
	        	minDate: -60, //60 DIAS ATRAS
	            defaultDate: "0",
	            changeMonth: true,
	            numberOfMonths: 1
	          });
	        
	        function selectCliente(){
	        	var codCliente = $("#codCliente").val();
	        	
	        	$("#visita1").val("");
	        	$("#visita2").val("");
	        	$("#visita3").val("");
	        	$("#visita4").val("");
	 			 //alert(idSituacion);   
	 			$.ajax({
	 				type: "POST", 
	 				url: "cargar_productos.htm", 
	 				data: { codCliente: codCliente} 
	 				}).done(function(data){
	                      $("#productos").html(data.selectProductos);
	                      //$("#productos").val("Seleccionar");
	                   })			
	 			 
	 		 }
	        
	        function cargarDirecciones(){
	        	var codCliente = $("#codCliente").val();
	        	var productos = $("#productos").val();
	 			 //alert(idSituacion);   
	 			 console.log("DATOS: "+codCliente+" "+productos);
	 			$.ajax({
	 				type: "POST", 
	 				url: "cargar_direcciones.htm", 
	 				data: { "codCliente": codCliente,
	 					"productos":productos} 
	 				}).done(function(data){
	 					console.log("DIRECCIONES: "+ data.direc1+" "+data.direc2+" "+data.direc3+" "+data.direc4);
	 						$("div.vis1 select").val(data.direc1);
	 						$("div.vis2 select").val(data.direc2);
	 						$("div.vis3 select").val(data.direc3);
	 						$("div.vis4 select").val(data.direc4);
	 						$("#id1").val(data.id1);
	 						$("#id2").val(data.id2);
	 						$("#id3").val(data.id3);
	 						$("#id4").val(data.id4);
	                   })			
	 		 }  
	        
	        </script>
        </div>
        <%@include file="../include-footer.jsp" %>
    </body>
</html>