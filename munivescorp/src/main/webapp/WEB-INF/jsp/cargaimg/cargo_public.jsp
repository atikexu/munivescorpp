<%@include file="../include-param.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="../include-head.jsp" %>
        <link rel="stylesheet" href="../static/css/default-forms.css">
        <script type="text/javascript">
        validaFormLoad = function(){
       		$("#smt").attr('disabled','disabled');
       		$("#smt").val('Espere...');
       		$("#divLoading").show();
       		return true;
        }
        </script>
    </head>
    <body>
    	<%@include file="../include-menu.jsp" %>
        <div class="content">
            <div class="content-form-generic">
            <h2>Publicar Im&aacute;genes de Cargos</h2>
		    <form class="form-generic" action="cargo_public.htm" method="POST" onsubmit="return validaFormLoad();">
		    	<div>
					<label for="info">Carpeta</label>
					<input type="text" name="info" value="${upDir}" id="info" title="Carpeta de carga" readonly="readonly"/>
				</div>
		    	<div>
		    		<label>Resumen:</label>
	    			<textarea rows="5" cols="20" readonly="readonly">${upLog }</textarea>
		    	</div>
				<div>
					<label for="smt"> </label>
					<input type="submit" class="boton-default" value="Publicar Imagenes" id="smt"/>
					<input type="hidden" name="upDir" value="${upDir }">
				</div>
				<div id="divLoading" style="text-align: center; display: none;">
					<img alt="Cargando..." src="../static/img/loader.gif" style="border: 0;">
				</div>
		    </form>
		    </div>
        </div>
        <%@include file="../include-footer.jsp" %>
    </body>
</html>