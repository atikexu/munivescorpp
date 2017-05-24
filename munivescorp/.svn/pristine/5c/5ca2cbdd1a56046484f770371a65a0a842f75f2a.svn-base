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
        	if($("#fecha").val().length==10){
        		$("#smt").attr('disabled','disabled');
           		$("#smt").val('Espere...');
           		setTimeout("funcionVer()",2000);
           		return true;
        	}else{
        		alert("Ingrese Rango de Fechas correctamente.");
        		return false;
        	}
        }
        function funcionVer() {
        	$("#smt").val('Exportar .xls');
        	$("#smt").attr('disabled',false);
        }
        </script>
    </head>
    <body>
    	<%@include file="../include-menu.jsp" %>
        <div class="content">
            <div class="content-form-generic">
            <h2>Descargar gestiones</h2>
		    <form class="form-generic" action="download_gestiones.htm" method="POST" onsubmit="return validaFormDownLoad();">
		    	<div>
		    		<span><c:if test="${vacio != null}">Las fechas ingresada no son inconsistentes.</c:if></span>
		    		<span><c:if test="${error != null}">Error interno al descargar el archivo.</c:if></span>
		    	</div>
		    	<div style="padding-left: 100px;">
		    		<p><span class="icon-attachment"></span> Descargar Reporte</p>
		    		<br/>
		    	</div>
		    	<div>

					<label for="fromDate">Fecha :</label>
					<input type="text" name="fecha" value="" id="fecha" size="10" maxlength="10" title="Fecha:" placeholder="Dia/Mes/Año"/>
				</div>
				<div>
					<label for="smt"> </label>
					<input type="submit" class="boton-default" value="Exportar .xls" id="smt"/>
				</div>
		    </form>
		    
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
	        
	        $("#btnPrueba").click(function() {
                var fecha      =$("#fecha").val();
                        $.ajax({
                           url: "download_gestiones.htm", 
                           type: "POST",  
                           data : { 
                                    fecha : fecha
                                   },
                           }).done(function(data){
                        	   console.log("descargooo");
                        	   //$("#contTabla").html(data.tablita);	  
                        });
            }); 
        </script>
    </body>
</html>