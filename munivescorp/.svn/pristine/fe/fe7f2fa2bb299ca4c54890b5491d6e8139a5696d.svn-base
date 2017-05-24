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
        validaFormUser = function(){
        	if($("#username").val().length>3 && $("#nombres").val().trim().length>2 && $("#correo").val().trim().length>5){
        		$("#smt").attr('disabled','disabled');
        		$("#smt").val('Espere...');
        		return true;
        	}else{
        		if($("#username").val().length<=3){
        			$("#formAlert").html("Ingrese usuario mayor a 4 caracteres.");
        		}else{
        			$("#formAlert").html("Ingrese sus datos correctamente, sus nombres y correo son obligatorios.");
        		}
        		return false;
        	}
        }
        
        </script>
    </head>
    <body>
    	<%@include file="../include-menu.jsp" %>
        <div class="content">
        	<div class="content-form-generic">
            	<h2>
					${empty RPAdminUserEdit?"<span class='icon-file-empty'></span> Registrar ":"<span class='icon-pencil'></span> Editar " }
            		Usuario
           		</h2>
            	<form action="${empty RPAdminUserEdit?"user_save.htm":"user_update.htm" }" class="form-generic" method="post" onsubmit="return validaFormUser();">
	            	<div style="text-align: center;">
			    		<span id="formAlert">
			    			<c:if test="${success}"><span class="icon-checkmark"></span> ${empty info?"El usuario fue guardado correctamente.":info }</c:if>
			    			<c:if test="${error}"><span class="icon-notification"></span> Error: ${cause}</c:if>
			    		</span>
			    	</div>
            		<div>
            			<label for="idCliente">Grupo:</label>
            			<select id="idCliente" name="idCliente">
            				<option value="0" ${empty RPAdminUserEdit?"selected='selected'":"" }>Dataimagenes SAC</option>
            				
            				<c:forEach items="${RPAdminUserClientes}" var="cliente" varStatus="rowCounter">
	        				<c:if test="${cliente.estado == 'ON'}">
	        					<option value='<c:out value="${cliente.codCliente }"/>' ${RPAdminUserEdit.idCliente==cliente.codCliente?"selected='selected'":"" }><c:out value="${cliente.razonsocial}"/></option>
	        				</c:if>
							</c:forEach>
            			</select>
            		</div>
            		<div>
            			<label for="idPerfil">Perfil:</label>
            			<select id="idPerfil" name="idPerfil" multiple="multiple" size="10">
            				<c:forEach items="${RPAdminUserPerfiles}" var="perfil" varStatus="rowCounter">
	        				<c:if test="${perfil.perfilEstado == 'ON'}">
	        					<option value='<c:out value="${perfil.id }"/>' ${RPAdminUserEdit.id==perfil.id?"selected='selected'":"" }><c:out value="${perfil.perfilNombre }"/></option>
	        				</c:if>
							</c:forEach>
            			</select>
            		</div>
            		
            		<div>
            			<label for="username">Usuario:</label>
            			<input id="username" name="username" type="text" value="${not empty RPAdminUserEdit?RPAdminUserEdit.username:username }" title="Nombre de usuario" placeholder="usuario" size="20" ${not empty RPAdminUserEdit?"readonly='readonly'":"" }>
            		</div>
            		<div>
            			<label for="codigo">Codigo DI:</label>
            			<input id="codigo" name="codigo" type="text" value="${not empty RPAdminUserEdit?RPAdminUserEdit.codigo:codigo }" title="Codigo DI" placeholder="codigo DI" size="10">
            		</div>
            		<div>
            			<label for="nombres">Nombres:</label>
            			<input id="nombres" name="nombres" type="text" value="${not empty RPAdminUserEdit?RPAdminUserEdit.nombres:nombres }" title="Nombres" placeholder="Nombres" size="20">
            		</div>
            		<div>
            			<label for="apellidos">Apellidos:</label>
            			<input id="apellidos" name="apellidos" type="text" value="${not empty RPAdminUserEdit?RPAdminUserEdit.apellidos:apellidos }" title="Apellidos" placeholder="Apellidos" size="25">
            		</div>
            		<div>
            			<label for="correo">Correo:</label>
            			<input id="correo" name="correo" type="text" value="${not empty RPAdminUserEdit?RPAdminUserEdit.correo:correo }" title="Correo" placeholder="correo@ejemplo.com" size="25">
            		</div>
            		<div>
            			<label for="telefono">Tel&eacute;fono:</label>
            			<input id="telefono" name="telefono" type="text" value="${not empty RPAdminUserEdit?RPAdminUserEdit.telefono:telefono }" title="Telefono" placeholder="Telefono" size="15">
            		</div>
            		<div>
            			<label for="area">&Aacute;rea ref.:</label>
            			<input id="area" name="area" type="text" value="${not empty RPAdminUserEdit?RPAdminUserEdit.area:area }" title="Area referencial" placeholder="Area referencial" size="20">
            		</div>
            		<div>
            			<label for="cargo">Cargo ref.:</label>
            			<input id="cargo" name="cargo" type="text" value="${not empty RPAdminUserEdit?RPAdminUserEdit.cargo:cargo }" title="Cargo" placeholder="Cargo" size="20">
            		</div>
            		<div>
            			<label for="estado">Estado</label>
            			<select id="estado" name="estado">
            				<option value="ACTIVE" ${RPAdminUserEdit.estado=="ACTIVE"?"selected='selected'":""}>Activo</option>
            				<option value="INACTIVE" ${RPAdminUserEdit.estado=="INACTIVE"?"selected='selected'":""}>Desactivo</option>
            			</select>
            		</div>
            		<div>
            			<label for="smt"> </label>
						<input type="submit" class="boton-default" value="Registrar " id="smt"/>
						<input type="hidden" name="idUser" value="${RPAdminUserEdit.id}">
					</div>
            	</form>
            </div>
            <div class="content-frame-generic">
           		<div id="accordion">
           				<h3>Grupo Dataimagenes</h3>
       					<div style="padding: 0; max-height: 400px;">
       						<div style="width: 98%; margin: 5px auto;">
    						<table>
		           				<thead>
		           					<tr>
		           						<td style="min-width: 100px;">Usuario</td>
		           						<td>Nombres y Apellidos</td>
		           						<td>Perfiles</td>
		           					</tr>
		           				</thead>
		           				<tbody>
				          			<c:forEach items="${RPAdminUserUsuarios}" var="usuario" varStatus="rowCounter">
				          			<c:if test="${ (usuario.tipo=='DI') }">
				          			
				          			<tr class='<c:if test="${rowCounter.count % 2 == 0}">on</c:if>'>
				          				<td style="font-size: .9em;">
				          					${usuario.username}
				          					<p style="font-size: .9em;">
				          					<a href="./user_edit.htm?username=${usuario.username}"><span class="icon-pencil"></span>Editar</a> |  
				          					<a href="./user_passwd.htm?username=${usuario.username}" onclick="return confirm('¿Esta seguro de Re-establecer la clave y enviar por correo?');"><span class="icon-key"></span>Reset</a>
				          					</p>
			          					</td>
				          				<td>
				          					${usuario.nombres} ${usuario.apellidos}
				          					<br>
				          					<span style="font-size: .7em;">${usuario.correo}</span>
				          				</td>
				          				<td style="font-size: .7em;">
				          				<c:forEach items="${usuario.perfilSet}" var="perfil" varStatus="rowCounter">
				          				${fn:substring(perfil.perfilNombre, 0, 9)} 
				          				</c:forEach>
				          				</td>
									</tr>
									
									</c:if>
									</c:forEach>
		           				</tbody>
		           			</table>
       						</div>
       					</div>
           		
           			<c:set var="grupo" value="0"></c:set>
           			<c:forEach items="${RPAdminUserClientes}" var="cliente" varStatus="rowCounter">
           				<c:set var="grupo" value="${cliente.codCliente }"></c:set>
       					<h3>Grupo ${cliente.razonsocial }</h3>
       					<div style="padding: 0; max-height: 400px;">
       						<div style="width: 98%; margin: 5px auto;">
    						<table>
		           				<thead>
		           					<tr>
		           						<td style="min-width: 100px;">Usuario</td>
		           						<td>Nombres y Apellidos</td>
		           						<td>Perfiles</td>
		           					</tr>
		           				</thead>
		           				<tbody>
				          			<c:forEach items="${RPAdminUserUsuarios}" var="usuario" varStatus="rowCounter">
				          			<c:if test="${ (grupo == usuario.idCliente) && (usuario.tipo=='CL') }">
				          			
				          			<tr class='<c:if test="${rowCounter.count % 2 == 0}">on</c:if>'>
				          				<td style="font-size: .9em;">
				          					${usuario.username}
				          					<p style="font-size: .9em;">
				          					<a href="./user_edit.htm?username=${usuario.username}"><span class="icon-pencil"></span>Editar</a> |  
				          					<a href="./user_passwd.htm?username=${usuario.username}" onclick="return confirm('¿Esta seguro de Re-establecer la clave y enviar por correo?');"><span class="icon-key"></span>Reset</a>
				          					</p>
			          					</td>
				          				<td>
				          					${usuario.nombres} ${usuario.apellidos}
				          					<br>
				          					<span style="font-size: .7em;">${usuario.correo}</span>
				          				</td>
				          				<td style="font-size: .7em;">
				          				<c:forEach items="${usuario.perfilSet}" var="perfil" varStatus="rowCounter">
				          				${fn:substring(perfil.perfilNombre, 0, 9)} 
				          				</c:forEach>
				          				</td>
									</tr>
									
									</c:if>
									</c:forEach>
		           				</tbody>
		           			</table>
       						</div>
       					</div>
					</c:forEach>
						
           		</div>
           		
           		
            </div>
        </div>
        <%@include file="../include-footer.jsp" %>
    </body>
</html>