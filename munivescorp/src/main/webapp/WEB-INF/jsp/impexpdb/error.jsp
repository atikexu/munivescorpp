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
		    	<span><c:if test="${ref == 'import'}">El archivo que intenta cargar es inconsistente.</c:if></span>
	    		<span><c:if test="${ref == 'export'}">El archivo que intenta descargar es inconsistente.</c:if></span>
		    </p>
		    </div>
        </div>
        <%@include file="../include-footer.jsp" %>
    </body>
</html>