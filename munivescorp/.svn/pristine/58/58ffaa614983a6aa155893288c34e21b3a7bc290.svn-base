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
        <link href="../static/css/select2.min.css" rel="stylesheet" />
		<script src="../static/js/select2.min.js"></script>
    </head>
    <body onload="$('#codBar').select();">
    	<%@include file="../include-menu.jsp" %>
        <div class="content">         
       		 <div class="content-frame-generic">
       		 <h2> &nbsp; </h2>
       			<form action="nueva_ruta.htm" class="form-generic" method="post">
	            	<div style="text-align: center;">
			    		<span id="formAlert">
			    			<c:if test="${success}"><span class="icon-checkmark"></span> ${empty info?"busqueda exitosa.":info }</c:if>
			    			<c:if test="${error}"><span class="icon-notification"></span> Error: ${cause}</c:if>
			    		</span>
			    	</div>
            		<div>
						<label for="fecha">Fecha:</label>
						<input required="required" readonly="readonly" type="text" required="required" name="fecha" id="fecha" size="10" maxlength="10" title="Fecha" style="width: 120px;" placeholder="Dia/Mes/Año" onchange="listarRutas()"/>
					</div>
					<div >
            			<label  class="label" for="choiceMensajero">Mensajero:</label>
            			<select required="required" id="choiceMensajero" name="choiceMensajero" style="width: 200px;" onchange="listarRutas()">
            				<option value="">::SELECCIONAR::</option>
            				<c:forEach items="${ListaMensajeros}" var="mensajero" >
	            				<option value="${mensajero.idMensajero}" ${choiceMensajero==mensajero.idMensajero ?"selected='selected'":"" }>${mensajero.apellidoPat} ${mensajero.apellidoMat} ${mensajero.nombres}</option>
	            			</c:forEach> 
            			</select>
            		</div>
            		
            		<button id="btnCrearRuta" style="width: 200px;" class="boton-default" >Crear ruta</button>
            		
            		<!--  <div>
            			<label for="smt"> </label>
						<input type="submit" class="boton-default" value="Registrar " id="smt"/>
						<!--  <input type="hidden" name="idUser" value="${RPMensajeroEdit.id_mensajero}">
					</div>-->
            	</form>

       		 </div>
       		 
       		
            		
            		<h2>Consulta Hoja de Ruta</h2>
	        	
        		
        		<div id="contTabla">
        		
        		</div>
           		<input id="mensaje" name="mensaje" size="150" type="text" value="" title="Mensaje" placeholder="" style="border: none; color: red;">
            		
	          	  
       		 <script type="text/javascript">

       		$("#choiceMensajero").select2();

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
	 					//console.log("ENTRO"+data.tablita);
	 					$("#contTabla").html(data.tablita);	

	                   })			
	 		 }
			
			$("#btnEliminar").click(function() {
				alert("dfdf");
                var idR      =$("#id").val();
                console.log("entro eliminar ruat"+idR);
                        $.ajax({
                           url: "eliminar_hoja_ruta.htm", 
                           type: "POST",  
                           data : { 
                                    "idR" : idR
                                   },
                           }).done(function(data){
                        	   $("#contTabla").html(data.tablita);	  
                        });
            }); 
			
			function btn_eliminarRuta(id_rut){
				var fec = $("#fecha").val();
	        	var idMen = $("#choiceMensajero").val();
				console.log("RUTA: "+id_rut)
	 			$.ajax({
	 				type: "POST",
	 				url: "eliminar_hoja_ruta.htm", 
	 				data: { id_rut: id_rut,
	 					fec:fec,
	 					idMen:idMen
	 					} 

	 				}).done(function(data){
	 					//console.log("ENTRO"+data.tablita);
	 					$("#contTabla").html(data.tablita);	
	 					$("#mensaje").val(data.mensaje);
	 					//console.log("mensaje: "+data.mensaje);
	                   })	
			}
       		
       		 </script>
   	       </div> 
        <%@include file="../include-footer.jsp" %>
    </body>
</html>