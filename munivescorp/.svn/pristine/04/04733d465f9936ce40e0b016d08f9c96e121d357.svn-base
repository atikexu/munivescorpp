<%@include file="../include-param.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="../include-head.jsp" %>
        <link rel="stylesheet" href="../static/css/default-forms.css">
    </head>
    <body>
    	<%@include file="../include-menu.jsp" %>
        <div class="content">
            <div class="content-form-generic center">
            
            <c:if test="${ref == 'cargo'}">
	            <h2>Publicaci&oacute;n Completada!</h2>
			    <p>La publicacion de im&aacute;genes(cargos) se ha finalizado correctamente.</p>
		    </c:if>
		    
		    <c:if test="${ref == 'documento'}">
	            <h2>Publicaci&oacute;n Completada!</h2>
			    <p>La publicacion de im&aacute;genes(documentos) se ha finalizado correctamente.</p>
		    </c:if>
		    <form class="form-generic">
		    <div>
	    		<label>Resultado:</label>
		    	<textarea rows="10" cols="28">${logCarga}</textarea>
		    </div>
		    </form>
		    <a href="../distribucion/?carga=true" class="boton-default">Finalizar</a>
		    </div>
        </div>
        <%@include file="../include-footer.jsp" %>
    </body>
</html>