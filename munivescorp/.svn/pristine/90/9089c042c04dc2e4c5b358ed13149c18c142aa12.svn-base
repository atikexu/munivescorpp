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
       			<!--  <form action="mensajero_save.htm" class="form-generic" method="post">-->
	            	<div style="text-align: center;">
			    		<span id="formAlert">
			    			<c:if test="${success}"><span class="icon-checkmark"></span> ${empty info?"El Empleado fue guardado correctamente.":info }</c:if>
			    			<c:if test="${error}"><span class="icon-notification"></span> Error: ${cause}</c:if>
			    		</span>
			    	</div>
            		<div>
            			<input readonly="readonly" hidden="" type="text" id="idMensajero" value="${not empty RPMensajeroEdit?RPMensajeroEdit.id:id }"  name="idMensajero"/>
            		</div>
            		<div>
						<label for="fecha">Fecha:</label>
						<input readonly="readonly" type="text" required="required" name="fecha" id="fecha" size="10" maxlength="10" title="Fecha" style="width: 120px;" placeholder="Dia/Mes/Año" onchange="listarRutas()"/>
					</div>
					<div >
            			<label  class="label" for="choiceMensajero">Mensajero:</label>
            			<select required="required" id="choiceMensajero" name="choiceMensajero" onchange="listarRutas()">
            				<option value="">::SELECCIONAR::</option>
            				<c:forEach items="${ListaMensajeros}" var="mensajero" >
	            				<option value="${mensajero.idMensajero}">${mensajero.apellidoPat} ${mensajero.apellidoMat} ${mensajero.nombres}</option>
	            			</c:forEach> 
            			</select>
            		</div>
            		
            		<!--  <div>
            			<label for="smt"> </label>
						<input type="submit" class="boton-default" value="Registrar " id="smt"/>
						<!--  <input type="hidden" name="idUser" value="${RPMensajeroEdit.id_mensajero}">
					</div>-->
            	<!--</form>-->
       		 </div>
       		 
       		
            		
            		<h2>Hoja de Ruta</h2>
	        	<table id="tablaRutas">
	        		<thead>
	        		<tr>
	        			<td>Fecha</td>
	        			<td>Ruta</td>
	        			<td>Mensajero</td>
	        			<td>Piezas</td>
	        			<td>Situación</td>	
	        			<td>Zona</td>        			
	        		</tr>
	        		</thead>
		        		<tbody>
		        		<c:forEach items="${ListaRutas}" var="rutas" varStatus="rowCounter">
		        		<tr class='<c:if test="${rowCounter.count % 2 == 0}">on</c:if>'>
		        			<td><c:out value="${rutas.fecha}"/></td>
		        			<td><c:out value="${rutas.ruta}"/></td>
		        			<td><c:out value="${rutas.idMensajero}"/></td>
		        			<td><c:out value="${rutas.piezas}"/></td>
		        			<td><c:out value="${rutas.situacion}"/></td>
		        			<td><c:out value="${rutas.zona}"/></td>
		        			<!--<td style="width: 10px">
	        				  <div>      
	       					<form action="./edit_mensajero.htm" method="post">      	
		        				<input readonly="readonly" hidden="" type="text" id="idMensajeroToEdit" value="${rutas.idRuta}" name="idMensajeroToEdit"/>
		        				<input readonly="readonly" hidden="" type="text" id="codMensajeroToEdit" value="${rutas.idRuta}" name="codMensajeroToEdit"/>	
								<input readonly="readonly" hidden="" type="text" id="nombresToEdit" value="${rutas.idRuta}" name="nombresToEdit"/>
								<input readonly="readonly" hidden="" type="text" id="apellidoPatToEdit" value="${rutas.idRuta}" name="apellidoPatToEdit"/>
								<input readonly="readonly" hidden="" type="text" id="apellidoMatToEdit" value="${rutas.idRuta}" name="apellidoMatToEdit"/>
								<input readonly="readonly" hidden="" type="text" id="dniToEdit" value="${rutas.idRuta}" name="dniToEdit"/>
								<input readonly="readonly" hidden="" type="text" id="correoToEdit" value="${rutas.idRuta}" name="correoToEdit"/>
								<input readonly="readonly" hidden="" type="text" id="telefonoToEdit" value="${rutas.idRuta}" name="telefonoToEdit"/>
								<input readonly="readonly" hidden="" type="text" id="empresaToEdit" value="${rutas.idRuta}" name="empresaToEdit"/>
								<input readonly="readonly" hidden="" type="text" id="estadoToEdit" value="${rutas.idRuta}" name="estadoToEdit"/>
								<input type="submit" class="boton-default" style="min-width: 100px;" value="Editar" id="smt"/>
							</form>	
							</div>
							</td>	-->        			
							
	        			</tr>
	        			</c:forEach>
		        		</tbody>
        		</table>         		
           		
            		
	          	  
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
	 				url: "lista_rutas.htm", 
	 				data: { fec: fec,
	 					idMen:idMen} 

	 				}).done(function(data){
	 					//console.log("ENTRO"+data);
	 						
	                   })			
	 		 }  
       		
       		 </script>
   	       </div> 
        <%@include file="../include-footer.jsp" %>
    </body>
</html>