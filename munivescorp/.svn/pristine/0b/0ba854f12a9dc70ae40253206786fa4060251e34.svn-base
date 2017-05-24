<%@include file="../include-param.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="../include-head.jsp" %>
        <%@include file="../include-head-jquery-ui.jsp" %>
        <link rel="stylesheet" href="../static/css/default-forms.css">
        <link rel="stylesheet" href="../static/css/default-grids.css">
    </head>
    <body onload="ActivarTxt()" >
    	<%@include file="../include-menu.jsp" %>
        <div class="content">
            <div class="content-frame-generic">
	          	<h2>Configurar Agencias</h2>
	          	<br/>
	          	<form action="./selectClienteAg.htm" class="form-generic" method="post">
		          	<div >
            			<label  class="label" for="codCliente">Cliente:</label>
            			<select required="required" id="codCliente" name="codCliente" onchange="selectCliente()">
            				<option value="">::SELECCIONAR::</option>
            				<c:forEach items="${RPAdminUserClientes}" var="cliente" >
	            				<option value="${cliente.codCliente}" ${codigoCliente==cliente.codCliente ?"selected='selected'":"" }>${cliente.razonsocial}</option>
	            			</c:forEach> 
            			</select>
            		</div>  
            			<div>
            			<label for="smt"> </label>
						<input type="submit" class="boton-default" style="min-width: 130px;" value=" Buscar " id="smt"  />
					</div>
            	</form>			
       		</div>
       		
       		 <div class="content-frame-generic">
       		 <h2> &nbsp; </h2>
       			
       			
       			<form action="./save_estadoAg.htm" class="form-generic" method="post">
	       			<div style="text-align: center;">
			    		<span id="formAlert">
			    			<c:if test="${success}"><span class="icon-checkmark"></span> ${empty info?"El Empleado fue guardado correctamente.":info }</c:if>
			    			<c:if test="${error}"><span class="icon-notification"></span> Error: ${cause}</c:if>
			    		</span>
			    	</div>
			    	
	       			<div>
	           			<label for="codCliente">C&oacute;digo Cliente:</label>
	           		<input  readonly="readonly" required="required" id="codCliente2" type="text" name="codCliente2" value="${codCliente2}" title="Hacer Click en buscar cliente"  size="26" maxlength="30">
	           		</div>
	       			
	       			<div>
	           			<label for="codigo">C&oacute;digo:</label>
	           			<input readonly="readonly" required="required" id="codigo" type="text" name="codigo" value="${codigo}" title="Ingrese Código"  size="26" maxlength="30">
	           		</div>
	           		<div>
	           			<label for="nombre">Nombre:</label>
	           			<input  required="required" id="nombre" type="text" name="nombre" value="${nombre}" title="Ingrese Nombre"  size="26" maxlength="100">
	           		</div>
	           		<div>
	           			<label for="tipo">Tipo:</label>
	           			<input required="required" id="tipo" type="text" name="tipo" value="${tipo}" title="Ingrese Tipo" size="26" maxlength="30">
	           		</div>
	           		<div>
	           			<label for="direccion">Direcci&oacute;n:</label>
	           			<input required="required" id="direccion" type="text" name="direccion" value="${direccion}" title="Ingrese Dirección personalizado" size="26" maxlength="200">
	           		</div>
	           		<div>
	           			<label for="ubigeo">Ubigeo:</label>
	           			<input required="required" id="ubigeo" type="text" name="ubigeo" value="${ubigeo}" title="Ingrese Ubigeo" size="26" maxlength="6">
	           		</div>
	           		<div>
	           			<label for="departamento">Departamento:</label>
	           			<input  required="required" id="departamento" type="text" name="departamento" value="${departamento}" title="Ingrese departamento"  size="26" maxlength="30">
	           		</div>
	           		<div>
	           			<label for="provincia">Provincia:</label>
	           			<input required="required" id="provincia" type="text" name="provincia" value="${provincia}" title="Ingrese Provincia"  size="26" maxlength="30">
	           		</div>
	           		<div>
	           			<label for="distrito">Distrito:</label>
	           			<input  required="required" id="distrito" type="text" name="distrito" value="${distrito}" title="Ingrese Distrito" size="26" maxlength="30">
	           		</div>
	           		<div>
	           			<label for="coordenadas">Coordenadas:</label>
	           			<input  id="coordenadas" type="text" name="coordenadas" value="${coordenadas}" title="Ingrese Coordenadas" size="26" maxlength="50">
	           		</div>
	           		<div>
            			<label for="estado">Estado</label>
            			<select id="estado" name="estado">
            				<option value="1" ${estado=="1"?"selected='selected'":""}>Activo</option>
            				<option value="0" ${estado=="0"?"selected='selected'":""}>Desactivo</option>
            			</select>
            		</div>
	           		
	           		
	           		<div>
	            			<label for="smt"> </label>
							<input type="submit" class="boton-default" style="min-width: 130px;" value=" Guardar " id="smt" />
					</div>
					
							
       		
       			 </form> 
       		 
       		 </div>
      
       		 
       		 <div >
       		 <br>
            		<div>
            		<h2>Agencias</h2>
	        	<table>
	        		<thead>
	        		<tr>
	        			<td>C&oacute;digo</td>
	        			<td>Nombre </td>
	        			<td>Tipo</td>
	        			<td>Direcci&oacute;n </td>
	        			<td>Ubigeo</td>
	        			<td>Departamento</td>
	        			<td>Provincia</td>
	        			<td>Distrito</td>
	        			<td>Coordenadas</td>
	        			<td>Estado</td>
	        			
	        			
	        		</tr>
	        		</thead>
	        		<tbody>
	        		<c:forEach items="${ListaAgencia}" var="agencia" varStatus="rowCounter">
	        			<tr class='<c:if test="${rowCounter.count % 2 == 0}">on</c:if>'>
	        				<td style="text-align: center;"><c:out value="${agencia.codAgencia}"/></td>
	        				<td><c:out value="${agencia.nombre}"/></td>
	        				<td><c:out value="${agencia.tipo}"/></td>
	        				<td><c:out value="${agencia.direccion}"/></td>
	        				<td><c:out value="${agencia.ubigeo}"/></td>
	        				<td><c:out value="${agencia.departamento}"/></td>
	        				<td><c:out value="${agencia.provincia}"/></td>
	        				<td><c:out value="${agencia.distrito}"/></td>
	        				<td><c:out value="${agencia.coordenadas}"/></td>
	        				<td><c:out value="${agencia.estado}"/></td>
	        				
	        				<td style="width: 10px">
	        				<div>      
       					<form action="./edit_estadoAg.htm" method="post">      	
       						<input readonly="readonly" hidden="" type="text" id="codClienteAgenciaToEdit" value="${agencia.codCliente}" name="codClienteAgenciaToEdit"/>
	        				<input readonly="readonly" hidden="" type="text" id="codAgenciaToEdit" value="${agencia.codAgencia}" name="codAgenciaToEdit"/>
	        				<input readonly="readonly" hidden="" type="text" id="nombreAgenciaToEdit" value="${agencia.nombre}" name="nombreAgenciaToEdit"/>	
							<input readonly="readonly" hidden="" type="text" id="tipoAgenciaToEdit" value="${agencia.tipo}" name="tipoAgenciaToEdit"/>
							<input readonly="readonly" hidden="" type="text" id="direccionAgenciaToEdit" value="${agencia.direccion}" name="direccionAgenciaToEdit"/>
	        				<input readonly="readonly" hidden="" type="text" id="ubigeoAgenciaToEdit" value="${agencia.ubigeo}" name="ubigeoAgenciaToEdit"/>	
							<input readonly="readonly" hidden="" type="text" id="departamentoAgenciaToEdit" value="${agencia.departamento}" name="departamentoAgenciaToEdit"/>
							<input readonly="readonly" hidden="" type="text" id="provinciaAgenciaToEdit" value="${agencia.provincia}" name="provinciaAgenciaToEdit"/>
	        				<input readonly="readonly" hidden="" type="text" id="distritoAgenciaToEdit" value="${agencia.distrito}" name="distritoAgenciaToEdit"/>	
							<input readonly="readonly" hidden="" type="text" id="coordenadasAgenciaToEdit" value="${agencia.coordenadas}" name="coordenadasAgenciaToEdit"/>
							<input readonly="readonly" hidden="" type="text" id="estadoAgenciaToEdit" value="${agencia.estado}" name="estadoAgenciaToEdit"/>
							<input type="submit" class="boton-default" style="min-width: 100px;" value="Editar" id="smt"/>
						</form>	
							</div>
							</td>
	        			</tr>
					</c:forEach>
					
	        		</tbody>
	        		</table>         		
            		</div> 
            		<br>
            		
	          	     
	          	</div>    
       		 <script type="text/javascript">
       		 
       		function ActivarTxt(){
       		//	alert("hola1");   
       			var codCliente = $("#codCliente").val();
       			if ($("#codCliente2").val().length !=0)
       			{
       			$("#codigo").prop('readonly',false);
       			$("#nombre").prop('readonly',false);
       			$("#tipo").prop('readonly',false);
       			$("#direccion").prop('readonly',false);
       			$("#ubigeo").prop('readonly',false);
       			$("#departamento").prop('readonly',false);
       			$("#provincia").prop('readonly',false);
       			$("#distrito").prop('readonly',false);
       			$("#coordenadas").prop('readonly',false);
       			
	        	//$("#codCliente2").val("hola");
	        	//$("#codCliente2").prop('readonly',false);
       			}
       			else
       			{
       				$("#codigo").prop('readonly',true);
       				$("#nombre").prop('readonly',true);
           			$("#tipo").prop('readonly',true);
           			$("#direccion").prop('readonly',true);
           			$("#ubigeo").prop('readonly',true);
           			$("#departamento").prop('readonly',true);
           			$("#provincia").prop('readonly',true);
           			$("#distrito").prop('readonly',true);
           			$("#coordenadas").prop('readonly',true);
       			}
       				
	        	//$("label[for='codCliente']").prop('disabled',true);
	        //	alert("hola2");   
       		}
       		 
       		function selectCliente(){
	        	var codCliente = $("#codCliente").val();
	        	  	
	        	$("#codCliente2").val($("#codCliente").val());
	            //$("#codigo").prop('readonly',false);
	        	ActivarTxt();
	        	
	 			$.ajax({
	 				type: "POST", 
	 				url: "selectClienteAg.htm", 
	 				data: { codCliente: codCliente}, 
	 				cache: false}) 			
	 			 
	 		 }       		 
       		 </script>
        	</div>        
        <%@include file="../include-footer.jsp" %>
    </body>
</html>