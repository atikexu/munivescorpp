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
		    <form class="form-generic" action="import_load.htm" method="POST" onsubmit="return validaFormLoad();">
		    	<div>
					<label for="info">Carpeta</label>
					<input type="text" name="info" value="${upDir}" id="info" title="Carpeta de carga" readonly="readonly"/>
				</div>
		    	<div>
		    		<label>Archivos Cargados:</label>
	    			<textarea rows="5" cols="20" readonly="readonly"><c:forEach items="${upFiles}" var="file">${file}${nl}</c:forEach></textarea>
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