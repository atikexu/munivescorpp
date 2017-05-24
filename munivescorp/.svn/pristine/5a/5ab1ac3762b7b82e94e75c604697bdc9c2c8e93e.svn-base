<%@include file="../include-param.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="../include-head.jsp" %>
        <%@include file="../include-head-jquery-ui.jsp" %>
        <link rel="stylesheet" href="../static/css/default-forms.css">
        <link rel="stylesheet" href="../static/css/default-grids.css">
        <script type="text/javascript">
        	changeForm=function(obj){
        		if(obj.value=="0"){
        			$("#divInputCodBar").show();
        			$("#divInputDocIde").show();
        		}else if(obj.value=="PQ"){
        			$("#divInputCodBar").hide();
        			$("#divInputDocIde").hide();
        		}
        	}
        </script>
    </head>
    <body onload="$('#codBar').select();">
    	<%@include file="../include-menu.jsp" %>
        <div class="content">
            <div class="content-form-generic">
            	<h2>Buscar por: </h2>
            	<form action="busqueda_rapida.htm" class="form-generic" method="post">
            		<c:if test="${(USUARIO_TIPO=='DI') || ( (USUARIO_TIPO=='CL') && (CLIENTE_CONFIG_CONSRVPAQ=='1') )}">
            		<div style="font-style: italic;">
            			<label for="tipoBuscar">Tipo de B&uacute;squeda:</label>
            			<select id="tipoBuscar" name="tipoBuscar" onchange="changeForm(this);">
            				<option value="0" ${tipoBuscar=="0"?"selected='selected'":"" }>Por Env&iacute;os</option>
            				<option value="PQ" ${tipoBuscar=="PQ"?"selected='selected'":"" }>Por Sub-Paquetes</option>
            			</select>
            		</div>
            		<div style="height: 10px;">&nbsp;</div>
            		</c:if>
            		<div id="divInputCodBar" style="${tipoBuscar=='PQ'?'display:none;':'' }">
            			<label for="codBar">Codigo de Cargo:</label>
            			<input id="codBar" type="text" name="codBar" value="${codBar }" title="Ingrese codigo de cargo / barras" placeholder="codigo de cargo" size="15" maxlength="16">
            		</div>
            		<div>
            			<!-- <label for="nroRef">Nro. Tarjeta / Cta / Pqte.:</label>-->
            			<label for="nroRef">No. de Pedido:</label>
            			<input id="nroRef" type="text" name="nroRef" value="${nroRef }" title="Ingrese Nro de Tarjeta / Cuenta" placeholder="tarjeta / cuenta" size="16" maxlength="30">
            		</div>
            		<div id="divInputDocIde" style="${tipoBuscar=='PQ'?'display:none;':'' }">
            			<label for="docIde">DNI/RUC/Otros:</label>
            			<input id="docIde" type="text" name="docIde" value="${docIde }" title="Ingrese documento" placeholder="dni / otros" size="12" maxlength="30">
            		</div>
            		<!-- 
            		<div>
            			<label for="nomDes">Nombre de Dest./Titular/Mand.:</label>
            			<input id="nomDes" type="text" name="nomDes" value="${nomDes }" title="Ingrese nombre de Destinatario o Titular" placeholder="destinatario o titular" size="26" maxlength="30">
            		</div>
            		-->
            		<div>
            			<label for="smt"></label>
						<input type="submit" class="boton-default" style="min-width: 130px;" value=" Buscar" id="smt"/>
					</div>
            	</form>
            	<div>
       			<label style="color: red;" for="smt">${mensajeBusqueda }</label>				
			</div>
            </div>
            
            <div class="content-form-generic" style="text-align: left;">
            	<img class="info-generic-img" style="border: 0;" src="../static/img/img_consultas.png">
            </div>
            <div class="content-result">
            	<h3>Resultados de b&uacute;squeda:</h3>
            	<c:if test="${not empty resultadoBusqueda }">
	        	<table>
	        		<thead>
	        		<tr>
	        			<td class="td_opt" style="max-width: 60px;">Fecha de Recepci&oacute;n</td>
	        			<c:if test="${USUARIO_TIPO!='CL'}">
	        			<td>Cliente</td>
	        			</c:if>
	        			<td style="max-width: 100px;">Codigo</td>
	        			<td>Destinatario</td>
	        			<td style="max-width: 60px;" class="td_opt">Documento</td>
	        			<td>Detalles</td>
	        		</tr>
	        		</thead>
	        		<tbody>
	        		<c:forEach items="${resultadoBusqueda}" var="dist" varStatus="rowCounter">
	        			<tr class='<c:if test="${rowCounter.count % 2 == 0}">on</c:if>'>
	        				<td class="td_opt td-center">
		        				<fmt:formatDate var="tempRowFecPro" value="${dist.fecPro}" pattern="dd/MM/yyyy"/>
		        				<c:out value="${tempRowFecPro}"/>
	        				</td>
	        				<c:if test="${USUARIO_TIPO!='CL'}">
	        				<td><c:out value="${dist.nomCli}"/></td>
	        				</c:if>
	        				<td><c:out value="${dist.codBar}"/></td>
	        				<td><c:out value="${dist.titNomApe}"/></td>
	        				<td class="td_opt"><c:out value="${dist.titNumDoc}"/></td>
	        				<td class="td-center">
	        					<a href='consulta_detalle_rap.htm?codBar=<c:out value="${dist.codBar}"/>'><samp class=" icon-zoom-in"></samp> Ver detalles</a>
<%-- 	        					<a href='javascript:verVisitas("<c:out value="${dist.codBar}"/>")'><samp class=" icon-zoom-in"></samp> Ver</a> --%>
        					</td>
	        			</tr>
					</c:forEach>
	        		</tbody>
	        	</table>
	        	</c:if>
	        	
	        	<c:if test="${not empty resultadoBusquedaPaquete }">
	        	<table>
	        		<thead>
	        		<tr>
	        			<td class="td_opt" style="max-width: 60px;">Codigo de Barras</td>
	        			<td style="max-width: 100px;">Nro. Pqte/Tarj./Otro</td>
	        			<td class="td_opt" style="max-width: 60px;">Otro Cod./Telf.</td>
	        			<td>Nombre Dest./Agte.</td>
	        			<td>Detalles</td>
	        		</tr>
	        		</thead>
	        		<tbody>
	        		<c:forEach items="${resultadoBusquedaPaquete}" var="paquete" varStatus="rowCounter">
	        			<tr class='<c:if test="${rowCounter.count % 2 == 0}">on</c:if>'>
	        				<td class="td_opt td-center">${paquete.codBar }</td>
	        				<td>${paquete.nroDoc } <i style="font-size: 0.8em;"> ${paquete.nroIde }<i/></td>
	        				<td class="td_opt"><c:out value="${paquete.codOtr}"/></td>
	        				<td>${paquete.nomDes} <i style="font-size: 0.8em;">${paquete.nomOtr }<i/></td>
	        				<td class="td-center">
	        					<a href='consulta_detalle.htm?codBar=<c:out value="${paquete.codBar}"/>&idPaquete=<c:out value="${paquete.id}"/>'><samp class=" icon-zoom-in"></samp> Ver detalles</a>
        					</td>
	        			</tr>
					</c:forEach>
	        		</tbody>
	        	</table>
	        	</c:if>
	        </div>
	        <div id="detalleVisitas" class="content-modal-generic" title="Detalle de Visitas"></div>
	        <script type="text/javascript">
	        $("#detalleVisitas").dialog({ autoOpen: false, modal: true, width: "80%"});
	        function verVisitas(cod_bar){
	        	$.ajax({type: "POST", url: "visitas.htm", data: { codBar: cod_bar}, cache: false}).done(function( html ) { $("#detalleVisitas").html(html); $("#detalleVisitas").dialog( "open" ); });
	        }
	        </script>
        </div>
        <%@include file="../include-footer.jsp" %>
    </body>
</html>