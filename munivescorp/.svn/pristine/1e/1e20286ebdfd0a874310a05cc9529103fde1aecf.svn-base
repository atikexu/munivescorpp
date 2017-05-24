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
        		alert("Seleccione [ARCHIVO].txt de Gestiones (3 columnas).\n  - Columna1: Codigo de barras\n  - Columna2: Codigo de Situacion\n  - Columna3: Codigo de Estado");
        		return false;
        	}
        }
        </script>
    </head>
    <body>
    	<%@include file="../include-menu.jsp" %>
        <div class="content">
            <div class="content-form-generic">
            <h2>Subir Archivo.txt de Gestiones</h2>
		    <form class="form-generic" action="gestion_upload.htm" method="POST" enctype="multipart/form-data" onsubmit="return validaFormUp();">
		    	<div>
		    		<span><c:if test="${vacio != null}">El archivo que intenta subir es inconsistente.</c:if></span>
		    		<span><c:if test="${error != null}">Error interno al subir y/o cargar el archivo.</c:if></span>
		    	</div>
				<div style="">
					<p>
					Seleccione archivo de texto plano separado con tabuladores,<br> 
					Que debe tener 3 columnas como:<br>
					<i style="font-size: 0.8em;">
					&nbsp;&nbsp;Columna 1 : Codigo de barras<br>
					&nbsp;&nbsp;Columna 1 : Codigo de situaci&oacute;n (1:Pendiente,2:Entregado y 3:Imposible)<br>
					&nbsp;&nbsp;Columna 1 : Codigo de estado (1:En oficina, 2:En hoja de ruta y 3:Rendido)
					</i>
					</p>
					<br/>
					<input type="file" name="file" value="" id="file" style="width: 320px;" title="Seleccione archivo a subir" placeholder="seleccione archivo"/>
					<br><br>
				</div>
				<div>
					<label for="smt"> </label>
					<input type="submit" class="boton-default" value="Subir Archivo TXT" id="smt"/>
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