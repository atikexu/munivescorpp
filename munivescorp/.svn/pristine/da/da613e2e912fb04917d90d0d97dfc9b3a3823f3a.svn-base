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
	          	<h2>Configurar Estados</h2>
	          	<br/>
	          	<form action="./selectCliente.htm" class="form-generic" method="post">
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
						<input type="submit" class="boton-default" style="min-width: 130px;" value=" Buscar " id="smt"/>
					</div>
            	</form>			
       		</div>
       		 <div class="content-frame-generic">
       		 <h2> &nbsp; </h2>
       			<form action="./save_estado.htm" class="form-generic" method="post">
       			<div>
           			<label for="codEstado">C&oacute;digo:</label>
           			<input hidden="" readonly="readonly" required="required" id="idEstado" type="text" name="idEstado" value="${idEstado}" title="Ingrese nombre de Destinatario o Titular"  size="26" maxlength="30">
           			<input readonly="readonly" required="required" id="codEstado" type="text" name="codEstado" value="${codEstado}" title="Ingrese nombre de Destinatario o Titular"  size="26" maxlength="30">
           		</div>
           		<div>
           			<label for="nomGenerico">Nombre Gen&eacute;ico:</label>
           			<input readonly="readonly" required="required" id="nomGenerico" type="text" name="nomGenerico" value="${nomGenerico}" title="Ingrese nombre de Destinatario o Titular" size="26" maxlength="30">
           		</div>
           		<div>
           			<label for="nomPerso">Nombre Personalizado:</label>
           			<input required="required" id="nomPerso" type="text" name="nomPerso" value="${nomPerso}" title="Ingrese nombre personalizado" size="26" maxlength="50">
           		</div>
           		<div>
            			<label for="smt"> </label>
						<input type="submit" class="boton-default" style="min-width: 130px;" value=" Guardar " id="smt"/>
					</div>
       		</form>
       		 </div>
       		 <div >
       		 <br>
            		<div>
            		<h2>Situaci&oacute;n</h2>
	        	<table>
	        		<thead>
	        		<tr>
	        			<td>C&oacute;digo</td>
	        			<td>Nombre Gen&eacute;rico</td>
	        			<td>Nombre Personalizado</td>
	        			
	        		</tr>
	        		</thead>
	        		<tbody>
	        		<c:forEach items="${TipoSituacion}" var="estado" varStatus="rowCounter">
	        			<tr class='<c:if test="${rowCounter.count % 2 == 0}">on</c:if>'>
	        				<td style="text-align: center;"><c:out value="${estado.cod_estado}"/></td>
	        				<td><c:out value="${estado.des_estado}"/></td>
	        				<td><c:out value="${estado.desc_estado_per}"/></td>
	        				<td style="width: 10px">
	        				<div>      
       					<form action="./edit_estado.htm" method="post">      	
	        				<input readonly="readonly" hidden="" type="text" id="idEstadoToEdit" value="${estado.id_estado}" name="idEstadoToEdit"/>
	        				<input readonly="readonly" hidden="" type="text" id="codEstadoToEdit" value="${estado.cod_estado}" name="codEstadoToEdit"/>	
							<input readonly="readonly" hidden="" type="text" id="desEstadoToEdit" value="${estado.des_estado}" name="desEstadoToEdit"/>
							<input readonly="readonly" hidden="" type="text" id="desPerEstadoToEdit" value="${estado.desc_estado_per}" name="desPerEstadoToEdit"/>
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
            		<div>
            		<h2>Motivos</h2>
				        	<table>
	        		<thead>
	        		<tr>
	        			<td>C&oacute;digo</td>
	        			<td>Nombre Gen&eacute;rico</td>
	        			<td>Nombre Personalizado</td>
	        		</tr>
	        		</thead>
	        		<tbody>
	        		<c:forEach items="${TipoMotivo}" var="motivo" varStatus="rowCounter">
	        			<tr class='<c:if test="${rowCounter.count % 2 == 0}">on</c:if>'>
	        				<td style="text-align: center;"><c:out value="${motivo.cod_estado}"/></td>
	        				<td><c:out value="${motivo.des_estado}"/></td>
	        				<td><c:out value="${motivo.desc_estado_per}"/></td>
	        				<td style="width: 10px">
	        				<div>	
								<form action="./edit_estado.htm" method="post">      	
	        				<input readonly="readonly" hidden="" type="text" id="idEstadoToEdit" value="${motivo.id_estado}" name="idEstadoToEdit"/>
	        				<input readonly="readonly" hidden="" type="text" id="codEstadoToEdit" value="${motivo.cod_estado}" name="codEstadoToEdit"/>	
							<input readonly="readonly" hidden="" type="text" id="desEstadoToEdit" value="${motivo.des_estado}" name="desEstadoToEdit"/>
							<input readonly="readonly" hidden="" type="text" id="desPerEstadoToEdit" value="${motivo.desc_estado_per}" name="desPerEstadoToEdit"/>
							<input type="submit" class="boton-default" style="min-width: 100px;" value="Editar" id="smt"/>
						</form>	
							</div>
							</td>
	        			</tr>
					</c:forEach>
					
	        		</tbody>
	        		</table>         		
            		</div>       	
	          	     
	          	</div>    
       		 <script type="text/javascript">
       		function selectCliente(){
	        	var codCliente = $("#codCliente").val();
	 			 //alert(idSituacion);   
	 			$.ajax({
	 				type: "POST", 
	 				url: "selectCliente.htm", 
	 				data: { codCliente: codCliente}, 
	 				cache: false}) 			
	 			 
	 		 }       		 
       		 </script>
        	</div>        
        <%@include file="../include-footer.jsp" %>
    </body>
</html>