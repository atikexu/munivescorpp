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
       			<form action="nueva_rendicion.htm" class="form-generic" method="post">
					<div style="text-align: center;">
			    		<span id="formAlert">
			    			<c:if test="${success}"><span class="icon-checkmark"></span> ${empty info?"busqueda exitosa.":info }</c:if>
			    			<c:if test="${error}"><span class="icon-notification"></span> Error: ${cause}</c:if>
			    		</span>
			    	</div>
            		<div>
						<label for="fecha">Fecha:</label>
						<input required="required" readonly="readonly" type="text" required="required" name="fecha" id="fecha" size="10" maxlength="10" title="Fecha" style="width: 120px;" placeholder="Dia/Mes/Año" onchange="listarRendicion();"/>
					</div>
					<div >
            			<label  class="label" for="codCliente">Cliente:</label>
            			<select required="required" id="codCliente" name="codCliente" onchange="listarRendicion();" data-live-search="true">
            				<option value="">::SELECCIONAR::</option>
            				<c:forEach items="${RPAdminUserClientes}" var="cliente" >
	            				<option value="${cliente.codCliente}" ${codCliente==cliente.codCliente ?"selected='selected'":"" }>${cliente.razonsocial}</option>
	            			</c:forEach> 
            			</select>
            		</div>
            		
            		<button id="btnCrearRendicion" style="width: 200px;" class="boton-default" >Nueva rendición</button>

            	</form>
       		 </div>
       		 <h2>Consulta Rendición</h2>
 		</div>
            		
				<!-- style="height:500px;hoverflow:auto;" -->
        		<div id="contTabla" style="text-align: center; margin-left:100px; margin-right:100px;width: 1700px;overflow:auto;">
        		
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
	        	minDate: -360, //60 DIAS ATRAS
	            defaultDate: "0",
	            changeMonth: true,
	            numberOfMonths: 1
	          });
       		 
			function listarRendicion(){
	        	var fec = $("#fecha").val();
	        	var idCliente = $("#codCliente").val(); 
	 			 console.log("DATOS: "+fec+" "+idCliente);
	 			$.ajax({
	 				type: "POST",
	 				url: "listar_rendicion.htm", 
	 				data: { fec: fec,
	 					idCliente:idCliente} 

	 				}).done(function(data){
	 					//console.log("ENTRO"+data.tablita);
	 					$("#contTabla").html(data.tablita);	
	                   })			
	 		 }
			
			/*$("#btnEliminar").click(function() {
				alert("dfdf");
                var idR      =$("#id").val();
                console.log("entro eliminar ruat"+idR);
                        $.ajax({
                           url: "eliminar_rendicion.htm", 
                           type: "POST",  
                           data : { 
                                    "idR" : idR
                                   },
                           }).done(function(data){
                        	   $("#contTabla").html(data.tablita);	  
                        });
            }); */
			
			function btn_eliminarRendicion(id_rendicion){
				var fecha = $("#fecha").val();
	        	var idClente = $("#codCliente").val();
				console.log("RENDICION: "+id_rendicion)
	 			$.ajax({
	 				type: "POST",
	 				url: "eliminar_rendicion.htm", 
	 				data: { id_rendicion: id_rendicion,
	 					fecha:fecha,
	 					idClente:idClente
	 					} 

	 				}).done(function(data){
	 					//console.log("ENTRO"+data.tablita);
	 					$("#contTabla").html(data.tablita);	
	 					//$("#mensaje").val(data.mensaje);
	 					//console.log("mensaje: "+data.mensaje);
	                   })	
			}
       		
       		 </script>
   	       
        <%@include file="../include-footer.jsp" %>
    </body>
</html>