<%@include file="../include-param.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="../include-head.jsp" %>
        <link rel="stylesheet" href="../static/css/default-forms.css">
        <script type="text/javascript">
        validaFormUp = function(){
        	if($("#file").val().trim().length>0){
        		$("#smt").attr('disabled','disabled');
        		$("#smt").val('Espere...');
        		$("#divLoading").show();
        		return true;
        	}else{
        		alert("Seleccione [ARCHIVO].ZIP DE CARGA");
        		return false;
        	}
        }
        </script>
    </head>
    <body>
    	<%@include file="../include-menu.jsp" %>
        <div class="content">
            <div class="content-form-generic">
            <h2>Subir Archivo.ZIP de Imagenes</h2>
		    <form class="form-generic" action="cargo_upload.htm" method="POST" enctype="multipart/form-data" onsubmit="return validaFormUp();">
		    	<div>
		    		<span><c:if test="${vacio != null}">El archivo que intenta subir es inconsistente.</c:if></span>
		    		<span><c:if test="${error != null}">Error interno al subir y/o cargar el archivo.</c:if></span>
		    	</div>
				<div style="text-align: center;">
					<img src="../static/img/icon_zip.gif"><br/>
					<input type="file" name="file" value="" id="file" style="width: 320px;" title="Seleccione archivo a subir" placeholder="seleccione archivo"/>
				</div>
				<div>
					<label for="llave">Llave:</label>
					<input type="text" name="llave" value="" id="llave" size="8" maxlength="12" title="Ingrese aqui la llave" placeholder="contraseña"/>
				</div>
				<div>
					<label for="smt"> </label>
					<input type="submit" class="boton-default" value="Subir Archivo ZIP" id="smt"/>
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