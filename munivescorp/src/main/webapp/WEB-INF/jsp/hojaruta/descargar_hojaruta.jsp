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
       			<form action="download_hojaruta.htm" class="form-generic" method="post">
	            	<div style="text-align: center;">
			    		<span id="formAlert">
			    			<c:if test="${success}"><span class="icon-checkmark"></span> ${empty info?"busqueda exitosa.":info }</c:if>
			    			<c:if test="${error}"><span class="icon-notification"></span> Error: ${cause}</c:if>
			    		</span>
			    	</div>
			    	<div>
						<label for="fecha">Nro. hoja de ruta</label>
						
					</div>
            		<div>
						<label for="fechade">Fecha del:</label>
						<input required="required" readonly="readonly" type="text"  required="required" name="fechade" id="fechade" size="30" maxlength="30" title="Fecha" placeholder="Dia/Mes/Año" />
					</div>
					<div>
						<label for="fechaa">Fecha a:</label>
						<input required="required" readonly="readonly" type="text"  required="required" name="fechaa" id="fechaa" size="30" maxlength="30" title="Fecha" placeholder="Dia/Mes/Año" />
					</div>
            		
            		<input type="submit" class="boton-default" value="Exportar .xls" id="smt"/>
            		
            		<!--  <div>
            			<label for="smt"> </label>
						<input type="submit" class="boton-default" value="Registrar " id="smt"/>
						<!--  <input type="hidden" name="idUser" value="${RPMensajeroEdit.id_mensajero}">
					</div>-->
            	</form>

       		 </div>
       		 
       		
            		
            		<h2>Descargar hojas de ruta</h2>
	        	
        		
        		<div id="contTabla">
        		
        		</div>
           		<input id="mensaje" name="mensaje" size="150" type="text" value="" title="Mensaje" placeholder="" style="border: none; color: red;">
            		
	          	  
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
	        $( "#fechade" ).datepicker({
	        	minDate: -60, //60 DIAS ATRAS
	            defaultDate: "0",
	            changeMonth: true,
	            numberOfMonths: 1
	          });
	        $( "#fechaa" ).datepicker({
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
       		
       		 </script>
   	       </div> 
        <%@include file="../include-footer.jsp" %>
    </body>
</html>