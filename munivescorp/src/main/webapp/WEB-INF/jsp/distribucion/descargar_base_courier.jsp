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
	          	<h2>Descargar Base Courier:</h2>
	          	<br/>
	          	<form action="descarga_base_courier.htm" id="contactForm"  class="form-generic" method="POST" onsubmit="return validaFormDownLoad();"> 
	            	<div>
						<label for="ordenProceso">Orden Proceso:</label>
						<input type="text" maxlength="6" required="required" name="ordenProceso" id="ordenProceso"  title="Orden de proceso a descargar" style="width: 120px;" placeholder="Ejem. 168611"/>
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
       		<script>
        $(document).ready(function (){
          $('#ordenProceso').keyup(function (){
            this.value = (this.value + '').replace(/[^0-9]/g, '');
          });
        });
        validaFormDownLoad = function(){
        	
        		$("#smt").attr('disabled','disabled');
           		$("#smt").val('Espere...');
           		return true;
        	
        }
    </script>       		
        	</div>        
        <%@include file="../include-footer.jsp" %>
    </body>
</html>