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
        		alert("Seleccione [ARCHIVO].txt de Coordinaciones (11 columnas).\n  - Columna1: Codigo Referencia\n  - Columna2: Fecha coordinacion\n  - Columna3: Hora....");
        		return false;
        	}
        }
        
        
        </script>
    </head>
    <body>
    	<%@include file="../include-menu.jsp" %>
        <div class="content">
            
            <div class="content-form-generic">
            <h2>Subir Archivo.txt de Coordinaciones General</h2>
		    <form class="form-generic" action="upload_coordinaciongen.htm" action="" method="POST" enctype="multipart/form-data" onsubmit="return validaFormUp();">
		    	<div>
		    		<span><c:if test="${vacio != null}">El archivo que intenta subir es inconsistente.</c:if></span>
		    		<span><c:if test="${error == '1'}">Error interno al subir y/o cargar el archivo.</c:if></span> 	<br/>
		    		<span><c:if test="${error == '2'}">Error al subir y/o cargar el archivo. Debe seleccionar .txt </c:if></span> 	<br/>
		    		<span><c:if test="${error2 == '3'}">El Archivo que esta intentando cargar ya esta ingresado. Solo puede cargar una vez. </c:if></span> 	<br/>
		    	</div>
				
				<div >
            			<div>
            			
            			<label  class="label" for="codCliente">Cliente:</label>
            			<select required="required" id="codCliente" name="codCliente">
            				<option value="">::SELECCIONAR::</option>
            				<c:forEach items="${RPAdminUserClientes}" var="cliente" >
	            				<option value="${cliente.codCliente}" ${codigoCliente==cliente.codCliente ?"selected='selected'":"" }>${cliente.razonsocial}</option>
	            			</c:forEach> 
            			</select>
            			</div>
            			<br/><br/><br/>
            			<div id="contenidoALlenar">
            			</div>
            			
            		</div>  
            		
				
				<div style="">
					<p>
					Seleccione archivo de texto plano separado con tabuladores,<br> 
					Que debe tener 11 columnas :<br>
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
				<div>
		    		<span><c:if test="${msg != null}">
		    	    		<p> <c:out value="${msg}"/><p>
		    	  	  </c:if>
		    		</span>
		    		
		    	</div>
				
		    </form>
		    </div>
        </div>
        <%@include file="../include-footer.jsp" %>
    </body>
</html>