<%@include file="../include-param.jsp" %>
<!DOCTYPE html>
<c:set var="nl" value="
"/>
<html lang="en">
    <head>
        <%@include file="../include-head.jsp" %>
        <link rel="stylesheet" href="../static/css/default-forms.css">
        <script type="text/javascript">
        validaFormLoad = function(){
       		$("#smt").attr('disabled','disabled');
       		$("#smt").val('Espere...');
       		return true;
        }
        </script>
    </head>
    <body>
    	<%@include file="../include-menu.jsp" %>
        <div class="content">
            <div class="content-form-generic">
            <h2>Cargar a Base de Datos</h2>
		    <form class="form-generic" action="import_reporte_gnb_load.htm" method="POST" onsubmit="return validaFormLoad();">		    	
		    	<div>
		    		<label>Archivos Cargados:</label>
	    			<textarea rows="5" cols="20" readonly="readonly">
	    			${file}
	    			</textarea>
		    	</div>
				<div>
					<label for="smt"> </label>
					<input type="submit" class="boton-default" value="Cargar a DB" id="smt"/>
				</div>
		    </form>
		    </div>
        </div>
        <%@include file="../include-footer.jsp" %>
    </body>
</html>