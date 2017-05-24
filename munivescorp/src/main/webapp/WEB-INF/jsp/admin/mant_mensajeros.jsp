<%@include file="../include-param.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="../include-head.jsp" %>
        <%@include file="../include-head-jquery-ui.jsp" %>
        <link rel="stylesheet" href="../static/css/default-forms.css">
        <link rel="stylesheet" href="../static/css/default-grids.css">
        <script type="text/javascript">
        $(function() {
            $( "#accordion" ).accordion({
                collapsible: true, heightStyle: "content"
            });
        });
        
        function valida(e){
            tecla = (document.all) ? e.keyCode : e.which;
            if (tecla==8){
                return true;
            }
            patron =/[0-9]/;
            tecla_final = String.fromCharCode(tecla);
            return patron.test(tecla_final);
        }
        
              
        </script>
    </head>
    <body>
    	<%@include file="../include-menu.jsp" %>
        <div class="content">         
       		 <div class="content-frame-generic">
       		 <h2> &nbsp; </h2>
       			<form action="mensajero_save.htm" class="form-generic" method="post">
	            	<div style="text-align: center;">
			    		<span id="formAlert">
			    			<c:if test="${success}"><span class="icon-checkmark"></span> ${empty info?"El Empleado fue guardado correctamente.":info }</c:if>
			    			<c:if test="${error}"><span class="icon-notification"></span> Error: ${cause}</c:if>
			    		</span>
			    	</div>
            		<div>
            			<input readonly="readonly" hidden="" type="text" id="idMensajero" value="${not empty RPMensajeroEdit?RPMensajeroEdit.id:id }"  name="idMensajero"/>
            		</div>
            		<div>
            			<label for="codigo">Codigo:</label>
            			<input id="codigo" name="codigo" type="text" value="${not empty RPMensajeroEdit?RPMensajeroEdit.codigo:codigo }" title="Codigo" placeholder="codigo" size="10" maxlength="15">
            		</div>
            		<div>
            			<label for="nombres">Nombres:</label>
            			<input id="nombres" name="nombres" type="text" value="${not empty RPMensajeroEdit?RPMensajeroEdit.nombres:nombres }" title="Nombres" placeholder="Nombres" size="20" maxlength="50">
            		</div>
            		<div>
            			<label for="apellido_pat">Apellido Paterno:</label>
            			<input id="apellido_pat" name="apellido_pat" type="text" value="${not empty RPMensajeroEdit?RPMensajeroEdit.apellido_pat:apellido_pat }" title="Apellido Paterno" placeholder="Apellido Paterno" size="25" maxlength="50">
            		</div>
            		<div>
            			<label for="apellido_mat">Apellido Materno:</label>
            			<input id="apellido_mat" name="apellido_mat" type="text" value="${not empty RPMensajeroEdit?RPMensajeroEdit.apellido_mat:apellido_mat }" title="Apellido Materno" placeholder="Apellido Materno" size="25" maxlength="50">
            		</div>
            		<div>
            			<label for="dni">DNI:</label>
            			<input id="dni" name="dni" type="text" value="${not empty RPMensajeroEdit?RPMensajeroEdit.dni:dni }" title="Dni" placeholder="DNI" size="8" onkeypress="return valida(event)" maxlength="8">
            		</div>
            		<div>
            			<label for="correo">Correo:</label>
            			<input id="correo" name="correo" type="text" value="${not empty RPMensajeroEdit?RPMensajeroEdit.correo:correo }" title="Correo" placeholder="correo@ejemplo.com" size="25" maxlength="50">
            		</div>
            		<div>
            			<label for="telefono">Tel&eacute;fono:</label>
            			<input id="telefono" name="telefono" type="text" value="${not empty RPMensajeroEdit?RPMensajeroEdit.telefono:telefono }" title="Telefono" placeholder="Telefono" size="10" onkeypress="return valida(event)" maxlength="10">
            		</div>
            		<div>
            			<label for="empresa">Empresa:</label>
            			<input id="empresa" name="empresa" type="text" value="${not empty RPMensajeroEdit?RPMensajeroEdit.empresa:empresa }" title="Empresa" placeholder="Empresa" size="25" maxlength="50">
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
						<input type="submit" class="boton-default" value="Registrar " id="smt"/>
						<!--  <input type="hidden" name="idUser" value="${RPMensajeroEdit.id_mensajero}">-->
					</div>
            	</form>
       		 </div>
       		 
       		
            		
            		<h2>Mensajeros</h2>
	        	<table id="tablaMensajeros">
	        		<thead>
	        		<tr>
	        			<td>C&oacute;digo</td>
	        			<td>Mensajero</td>
	        			<td>Empresa</td>	        			
	        		</tr>
	        		</thead>
		        		<tbody>
		        		<c:forEach items="${listaMensajeros}" var="mensajero" varStatus="rowCounter">
		        		<tr class='<c:if test="${rowCounter.count % 2 == 0}">on</c:if>'>
		        			<td><c:out value="${mensajero.codMensajero}"/></td>
		        			<td><c:out value="${mensajero.apellidoPat} ${mensajero.apellidoMat} ${mensajero.nombres}"/></td>
		        			<td><c:out value="${mensajero.empresa}"/></td>
		        			<td style="width: 10px">
	        				<div>      
	       					<form action="./edit_mensajero.htm" method="post">      	
		        				<input readonly="readonly" hidden="" type="text" id="idMensajeroToEdit" value="${mensajero.idMensajero}" name="idMensajeroToEdit"/>
		        				<input readonly="readonly" hidden="" type="text" id="codMensajeroToEdit" value="${mensajero.codMensajero}" name="codMensajeroToEdit"/>	
								<input readonly="readonly" hidden="" type="text" id="nombresToEdit" value="${mensajero.nombres}" name="nombresToEdit"/>
								<input readonly="readonly" hidden="" type="text" id="apellidoPatToEdit" value="${mensajero.apellidoPat}" name="apellidoPatToEdit"/>
								<input readonly="readonly" hidden="" type="text" id="apellidoMatToEdit" value="${mensajero.apellidoMat}" name="apellidoMatToEdit"/>
								<input readonly="readonly" hidden="" type="text" id="dniToEdit" value="${mensajero.dni}" name="dniToEdit"/>
								<input readonly="readonly" hidden="" type="text" id="correoToEdit" value="${mensajero.correo}" name="correoToEdit"/>
								<input readonly="readonly" hidden="" type="text" id="telefonoToEdit" value="${mensajero.telefono}" name="telefonoToEdit"/>
								<input readonly="readonly" hidden="" type="text" id="empresaToEdit" value="${mensajero.empresa}" name="empresaToEdit"/>
								<input readonly="readonly" hidden="" type="text" id="estadoToEdit" value="${mensajero.estado}" name="estadoToEdit"/>
								<input type="submit" class="boton-default" style="min-width: 100px;" value="Editar" id="smt"/>
							</form>	
							</div>
							</td>	        			
							
	        			</tr>
	        			</c:forEach>
		        		</tbody>
        		</table>         		
           		
            		
	          	  
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