<%@include file="../include-param.jsp" %>
<!DOCTYPE html>
<c:set var="nl" value="
"/>
<html lang="en">
    <head>
        <%@include file="../include-head.jsp" %>
        <%@include file="../include-head-jquery-ui.jsp" %>
        <link rel="stylesheet" href="../static/css/default-forms.css">
        <link rel="stylesheet" href="../static/css/default-grids.css">  
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
        
        
        validaFormDownLoad = function(){
        	if($("#fromDate").val().length==10 && $("#toDate").val().length==10){
        		$("#smt").attr('disabled','disabled');
           		$("#smt").val('Espere...');
           		return true;
        	}else{
        		alert("Ingrese Rango de Fechas correctamente.");
        		return false;
        	}
        }
        </script>
    </head>
    <body>
    	<%@include file="../include-menu.jsp" %>
        <div class="content">
            <div class="content-form-generic">
            <h2>Ubicación de Coordinaciones</h2>
		    <form class="form-generic" action="" method="POST" onsubmit="return validaFormDownLoad();">
		    	<div>
		    		<span><c:if test="${vacio != null}">Las fechas ingresada no son inconsistentes.</c:if></span>
		    		<span><c:if test="${error != null}">Error interno al descargar el archivo.</c:if></span>
		    	</div>

		    	<div>
					<label for="fromDate">Fecha Coordinación:</label>
					<input type="text" name="fecha" value="" required="required" id="fecha" size="10" maxlength="10" title="Desde la fecha:" placeholder="Dia/Mes/Año"/>
				</div>
				<div>
            			<label for="codigo">Codigo de barra:</label>
            			<input id="codigo" name="codigo" type="text" value="" title="Codigo" placeholder="codigo" size="25" maxlength="25" onchange="agregarCodBar();" autofocus>
            	</div>			

		    </form>
			    <div>
			    	<button id="btnNuevaHoja" style="width: 200px;" class="boton-default" >Aceptar</button>
			    </div>
		    </div>
		    <div class="content-form-generic">
		    	<form class="form-generic" action="">
<!-- 		    	<div> -->
<!--             			<label for="txtBuscadas">Total Buscadas:</label> -->
<!--             			<input id="txtBuscadas" name="txtBuscadas" type="text" value="" title="Buscadas" placeholder="Buscadas" size="25" maxlength="25" > -->
<!--             	</div> -->
            	<div>
            			<label for="txtAlertas">Total Alertas:</label>
            			<input id="txtAlertas" name="txtAlertas" type="text" value="" title="Alertas" placeholder="Alertas" size="25" maxlength="25" >
            	</div>
            	
            	
            	</form>
		    </div>
		    <div>
       			<input readonly="readonly" id="mensaje" name="mensaje" size="150" type="text" value="" title="Mensaje" placeholder="" style="border: none; color: red;">
       		</div>
		    <div id="contTabla">
       		
       		</div>
		    
        </div>
        
        <%@include file="../include-footer.jsp" %>
        <script type="text/javascript">
        
       
        
	        $( "#fecha" ).datepicker({        	
	            defaultDate: "+1",
	            changeMonth: true,
	            numberOfMonths: 1,
	            onClose: function( selectedDate ) {
	              $( "#toDate" ).datepicker( "option", "minDate", selectedDate );
	            }
	          });
	        $( "#toDate" ).datepicker({        	
	            defaultDate: "+1",
	            changeMonth: true,
	            numberOfMonths: 1,
	            onClose: function( selectedDate ) {
	              $( "#fromDate" ).datepicker( "option", "maxDate", selectedDate );
	            }
	          });
	        
	        
	        function agregarCodBar(){
	        	$("#mensaje").val("");
	        	var codigoB = $("#codigo").val();
	        	var fecha = $("#fecha").val();
	        	if(fecha==""){
	        		$("#mensaje").val("Ingrese fecha");
	        	}else{
	 			$.ajax({
	 				type: "POST",
	 				url: "listar_coord_cod.htm", 
	 				data: {codigoB: codigoB,
	 					fecha: fecha} 

	 				}).done(function(data){
	 					if(!data.mensaje_coor==""){
	 						alert(data.mensaje_coor);
	 					}
	 					$("#contTabla").html(data.tablita);	
	 					$("#mensaje").val(data.mensaje);
	 					$("#txtAlertas").val(data.lista);
	 					var alertas=data.alertas;
	 					var list=data.lista;
	 					for(i=0;i<list;i++){
	 						var situa = (alertas.split("_")[i]);
	 						alert("Coordinación para la hora : "+situa);
	 					}
	 					console.log("mensaje: "+data.mensaje);
	 					$("#codigo").val("");
	                   });	
	        	}
			}
        </script>
    </body>
</html>