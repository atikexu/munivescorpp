<%@include file="../include-param.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="../include-head.jsp" %>
        <%@include file="../include-head-jquery-ui.jsp" %>
        <link rel="stylesheet" href="../static/css/default-forms.css">
        <link rel="stylesheet" href="../static/css/default-grids.css">
    </head>
    <body>
    	<%@include file="../include-menu.jsp" %>
        <div class="content">
            <div class="content-frame-generic">
	          	<h2>Descargar Coordinaciones:</h2>
	          	<br/>
	          	<form action="descargar_coordinaciones.htm" id="contactForm"  class="form-generic" method="POST"> 
	            	<div>
						<label for="fecCoordinacion">Fecha:</label>
						<input readonly="readonly" type="text" required="required" name="fecCoordinacion" id="fecCoordinacion" size="10" maxlength="10" title="Fecha de las coordinaciones a descargar" style="width: 120px;" placeholder="Dia/Mes/Año"/>
					</div>
					<div>    
            			<label for="smt"> </label>
						<input type="submit" class="boton-default" style="min-width: 130px;" value="Descargar" id="smt"/>
					</div>
	            	<div>	            	
						<label>${msjBusqueda}</label>
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
	        $( "#fecCoordinacion" ).datepicker({
	        	minDate: -60, //60 DIAS ATRAS
	            defaultDate: "0",
	            changeMonth: true,
	            numberOfMonths: 1
	          });
	     //   function getPDF(){
	    //    	console.log("faq 1");
	    //  	    var fec = document.getElementById("fecCoordinacion").value;
	    //    	console.log(fec);
	   //     	$.ajax({
	   //     		type:'POST',
	  //      		url: 'descargar_coordinaciones.htm',
	   //     		data: {fecCoordinacion : fec},
	   //     		cache: false
	   //     	}).done(function(){
	   //     		console.log("END");
	   //     	})
	  //      	console.log("faq 2");
	  //      }
	        </script>
        	</div>        
        <%@include file="../include-footer.jsp" %>
    </body>
</html>