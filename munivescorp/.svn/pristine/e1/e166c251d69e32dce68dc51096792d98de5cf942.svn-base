<%@include file="../include-param.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="../include-head.jsp" %>
        <%@include file="../include-head-jquery-ui.jsp" %>
        <link rel="stylesheet" href="../static/css/default-forms.css">
        <link rel="stylesheet" href="../static/css/default-grids.css">
    </head>
    <body>
    	<%@include file="../include-menu.jsp" %>
      <div class="content">
            <div class="content-frame-generic">
	          	<h2>Carga Archivo</h2>
	          	<br/>
	          	
	          	
	          	<form action="./cargar_productos.htm" class="form-generic" method="post">
		          	<div >
            			<div>
            			
            			<label  class="label" for="codCliente">Cliente:</label>
            			<select required="required" id="codCliente" name="codCliente" onchange="selectCliente()">
            				<option value="">::SELECCIONAR::</option>
            				<c:forEach items="${RPAdminUserClientes}" var="cliente" >
	            				<option value="${cliente.codCliente}" ${codigoCliente==cliente.codCliente ?"selected='selected'":"" }>${cliente.razonsocial}</option>
	            			</c:forEach> 
            			</select>
            			</div>
            			<div>
            		     <label  class="label" for="productos">Productos:</label>
                           <select required="required" id="productos" name="productos" onchange ="selectProductos()">
                                  <option value="">::SELECCIONAR::</option>
                                  
                           </select>
							</div>
            			
            			<div id="contenidoALlenar">
            			</div>
            			<label style="display: none" id="labelHolaMundo" class="labels" >hola Mundo</label>
            			<label style="display: none" id="labelHolaMundo2" class="labels" >hola Mundo2</label>
            		</div>  
            		
					
					
            	</form>			
       		</div>
       	<div> 
       	 <div class="content">
            <h2>Subir Archivo</h2>
		  <form class="form-generic" action="./upload_reportSRV.htm" method="POST" enctype="multipart/form-data" onsubmit="return validaFormUp();">
		<!--      <form class="form-generic"  method="POST" enctype="multipart/form-data" onsubmit="return validaFormUp();">  
		     -->
		    	<div>
		    
		    		<span><c:if test="${vacio != null}">El archivo que intenta subir es inconsistente.</c:if></span> 	<br/>
		    		<span><c:if test="${error == '1'}">Error interno al subir y/o cargar el archivo.</c:if></span> 	<br/>
		    		<span><c:if test="${error == '2'}">Error al subir y/o cargar el archivo. Debe seleccionar .xlsx o txt </c:if></span> 	<br/>
		    		<span><c:if test="${error2 == '3'}">El Archivo que esta intentando cargar ya esta ingresado. Solo puede cargar una vez. </c:if></span> 	<br/>
		    		
		    	</div>
				<div style="">
					
					<br/>
					 <input style="display: none" name="RutaTexto" id="RutaTexto" type="text" value="Por favor, ingresa aquí"/>
					<input type="file" name="file" value="" id="file" style="width: 320px;" title="Seleccione archivo a subir" placeholder="seleccione archivo"/>
					<br><br>
				</div>
				<div>
					<label for="smt"> </label>
				 	<input type="submit" class="boton-default" value="Subir Archivo" id="smt"/>
				<!--	<button id="btnSave2" style="width: 300px;" class="boton-default" >Subir Archivo</button> -->
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
       		 <div >
       		 <br>
            		<br>
            		 
	          	</div>    
       		 <script type="text/javascript">
       		function selectCliente()
       		{
	        	var codCliente = $("#codCliente").val();
	        	var Error = $("#verror").val();
	        	$("#RutaTexto").val("");
	        
	        	
	        //	alert(Error);
	        	
	        //--	$("#ingresatexto").val(codCliente);
	 			 //alert(idSituacion);   
	 			$.ajax({
	 				type: "POST", 
	 				url: "cargar_productos.htm", 
                    data: { codCliente: codCliente} 
    			            }).done(function(data){
	           		$("#productos").html(data.selectProductos);

       											 })                   
	 			 
	 		 }
       		
       	// ----- Carlos Ponte ---------------------------------	
     		$("#btnSave2").click(function() {

	        	var codCliente = $("#codCliente").val();
	        	var nomArchivo = $("#file").val();
	        	var nomcliente = $("#codCliente").val();
	        	//console.log("codTabla"+codTabla);
	        	alert("boton 22   "+ codCliente);   
	        	alert("boton 22   "+ codCliente);
	 			$.ajax({
	 				type: "POST", 
	 				url: "selecArchivoFTP.htm", 
	 				data: { codigoCli: codCliente,
	 					nombreArc : nomArchivo}, 
	 						async : false
	 			}).done(function(data){
	 				console.log(data);
	 				if(data.estado===true){
	 					console.log(data.nombres);
	 				}else{
	 					alert("ERROR");
	 				} 			
	 			 
	 			}); 
       		
     		}); 
       	
       	
       	
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
       	
     		 function selectProductos() {
     			 
     			var codTabla = $("#productos").val();     
	        	$("#RutaTexto").val(codTabla);
	        	//alert("boton 22   "+ codTabla);
     		 }
     		 
       	function selectTablas(){
	        	var codTabla = $("#codTabla").val();     
	        	$("#RutaTexto").val(codTabla);
	        	//alert("codTabla"+codTabla);
	 			console.log("codTabla"+codTabla);
	 			//window.location.replace("user_main.htm"); DIRECCIONAR A OTRA PAGINA
	 			//window.location.reload(); REFRESCAR TU PAGINA
	 		 /*
	 			$("#labelHolaMundo").show();
	 			$(".labels").show();
	 			*/
	 			//$("#contenidoALlenar").html("<input type=\"submit\" class=\"boton-default\" style=\"min-width: 130px;\" value=\" Buscar \" id=\"smt\"/>");
	 		
	 			$.ajax({
	 				type:'POST',
	 				url: 'metodo_prueba.htm',
	 				data: {nombresEntrantes: codCliente,
	 						id_nombre : codTabla},
	 				async : false
	 			}).done(function(data){
	 				console.log(data);
	 				if(data.estado===true){
	 					console.log(data.nombres);
	 				}else{
	 					alert("ERROR");
	 				}
	 			//	window.location.replace(data.ruta);
	 			});
	 			
	 		 }       		 
     
       		 </script>
        	</div>        
        <%@include file="../include-footer.jsp" %>
    </body>
</html>