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
            <c:if test="${empty error }">
            <c:if test="${upGestionCount > 0}">
	            <h2>Actualizaci&oacute;n Completada!</h2>
	            <br>
			    <p>La carga manual de <b>${upGestionCount}</b> gestiones se ha finalizado correctamente.</p>
		    </c:if>
		    <br><br>
	        <a class="boton-default" href="gestion_form.htm">Nueva Actualizaci&oacute;n</a>
		    </c:if>
			<br>
			<c:if test="${not empty error }">
		    	<p style="color: red;">Error en la actualizaci&oacute;n de gestiones.</p>
		    </c:if>    
		    </div>
        </div>
        <%@include file="../include-footer.jsp" %>
    </body>
</html>