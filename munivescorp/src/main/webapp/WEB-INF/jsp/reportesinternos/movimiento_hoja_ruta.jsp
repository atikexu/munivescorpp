<%@include file="../include-param.jsp" %>
<!DOCTYPE html>
<c:set var="nl" value="
"/>
<html lang="en">
    <head>
        <%@include file="../include-head.jsp" %>
        <%@include file="../include-head-jquery-ui.jsp" %>
        <link rel="stylesheet" href="../static/css/default-forms.css">
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
        
        validaFormDownLoad1 = function(){
        	if($("#fromDate1").val().length==10 && $("#toDate1").val().length==10){
        		$("#smt1").attr('disabled','disabled');
           		$("#smt1").val('Espere...');
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
            <h2>Bajar Reporte Movimiento en Hoja de Ruta</h2>
		    <form class="form-generic" action="download_movimiento_hoja_ruta.htm" method="POST" onsubmit="return validaFormDownLoad();">
		    	<div>
		    		<span><c:if test="${vacio != null}">Las fechas ingresada no son inconsistentes.</c:if></span>
		    		<span><c:if test="${error != null}">Error interno al descargar el archivo.</c:if></span>
		    	</div>
		    	<div style="padding-left: 100px;">
		    		<p><span class="icon-attachment"></span> Descargar Reporte</p>
		    		<br/>
		    	</div>
		    	<div>
					<label for="fromDate">Fecha (Desde):</label>
					<input type="text" name="fromDate" value="" id="fromDate" size="10" maxlength="10" title="Desde la fecha:" placeholder="Dia/Mes/Año"/>
				</div>
				<div>
					<label for="toDate">Fecha (Hasta):</label>
					<input type="text" name="toDate" value="" id="toDate" size="10" maxlength="10" title="Hasta la fecha:" placeholder="Dia/Mes/Año"/>
				</div>			
				<div>
					<label for="smt"> </label>
					<input type="submit" class="boton-default" value="Exportar .xls" id="smt"/>
				</div>
		    </form>
		    
		    <form class="form-generic" action="download_bcp_scc.htm" method="POST" onsubmit="return validaFormDownLoad1();">
		    	<div>
		    		<span><c:if test="${vacio != null}">Las fechas ingresada no son inconsistentes.</c:if></span>
		    		<span><c:if test="${error != null}">Error interno al descargar el archivo.</c:if></span>
		    	</div>
		    	
		    	<div style="padding-left: 100px;">
		    		<p><span class="icon-attachment"></span> Descargar Reporte SCC</p>
		    		<br/>
		    	</div>
		    	<div>
					<label for="fromDate">Fecha (Desde):</label>
					<input type="text" name="fromDate1" value="" id="fromDate1" size="10" maxlength="10" title="Desde la fecha:" placeholder="Dia/Mes/Año"/>
				</div>
				<div>
					<label for="toDate">Fecha (Hasta):</label>
					<input type="text" name="toDate1" value="" id="toDate1" size="10" maxlength="10" title="Hasta la fecha:" placeholder="Dia/Mes/Año"/>
				</div>		
				<div>
					<label for="smt1"> </label>
					<input type="submit" class="boton-default" value="Exportar a SCC(BCP)" id="smt1"/>
				</div>
		    </form>
		    </div>
        </div>
        <%@include file="../include-footer.jsp" %>
        <script type="text/javascript">
	        $( "#fromDate" ).datepicker({        	
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
	        
	        $( "#fromDate1" ).datepicker({        	
	            defaultDate: "+1",
	            changeMonth: true,
	            numberOfMonths: 1,
	            onClose: function( selectedDate ) {
	              $( "#toDate1" ).datepicker( "option", "minDate", selectedDate );
	            }
	          });
	        $( "#toDate1" ).datepicker({        	
	            defaultDate: "+1",
	            changeMonth: true,
	            numberOfMonths: 1,
	            onClose: function( selectedDate ) {
	              $( "#fromDate1" ).datepicker( "option", "maxDate", selectedDate );
	            }
	          });
        </script>
    </body>
</html>