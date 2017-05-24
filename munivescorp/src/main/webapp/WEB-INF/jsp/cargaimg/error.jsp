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
            <div class="content-form-up center">
            <h2>Error</h2>
		    <p>
		    	<span><c:if test="${ref == 'cargo'}">Las imagenes de cargos que intenta publicar son inconsistentes.</c:if></span>
	    		<span><c:if test="${ref == 'documento'}">Las imagenes de documentos que intenta public son inconsistentes.</c:if></span>
		    </p>
		    </div>
        </div>
        <%@include file="../include-footer.jsp" %>
    </body>
</html>