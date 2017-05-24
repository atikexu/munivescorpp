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
            <c:if test="${ref == 'import'}">
	            <h2>Carga Completada!</h2>
			    <p>La carga de datos se ha finalizado correctamente.</p>
		    </c:if>
		    
		    <c:if test="${ref == 'export'}">
	            <h2>Descarga Lista!</h2>
			    <p>El archivo.zip de descarga de datos est&aacute; lista:</p>
			    <img src="../static/img/icon_zip.gif"> ${downZipFile }
			    <br/>
			    <br/>
			    <a class="boton-default" href="../dinamic/download/${downDirFile}/${downZipFile}">Descargar ${downZipFile }</a>
		    </c:if>
		    </div>
        </div>
        <%@include file="../include-footer.jsp" %>
    </body>
</html>