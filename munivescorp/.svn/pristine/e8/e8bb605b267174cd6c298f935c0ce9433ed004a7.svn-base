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
            	<h2>Coordinaci&oacute;n</h2>
            	<br/>
           		<div>
           			<span class="label">C&oacute;digo de Cargo</span>
           			<span class="value">${SAConsultaDetalle.codBar }</span>
           		</div>
           		<div>
           			<span class="label">Valorado</span>
           			<span class="value">${SAConsultaDetalle.valNroIde }</span>
           		</div>
           		<div>
           			<span class="label">Destinatario</span>
           			<span class="value">${SAConsultaDetalle.desNomApe }</span>
           		</div>
           		<div>
           			<span class="label">Titular</span>
           			<span class="value">${SAConsultaDetalle.titNomApe }</span>
           		</div>
            </div>
            <div class="content-frame-generic">
            	<h2> &nbsp; </h2>
            	<br/>
            	<div style="text-align: center; ">
	            <!-- 
	            	<sec:authorize access="hasAnyRole('DI_ADMIN','DI_COORDINACION','CL_ADMIN','CL_COORDINACION')">
	            		<c:if test="${(SAConsultaDetalle.indSit!='2')}">
	            		<a class="boton-default" href="../coordinacion/coord_buscar.htm?codBar=${SAConsultaDetalle.codBar }&docIde=&nroRef=&nomDes=">Registrar Nueva Coordinaci&oacute;n</a>
	            		</c:if>
	            	</sec:authorize>
            	-->
	            	<a class="boton-default" href="consulta_detalle_rap.htm?codBar=${SAConsultaDetalle.codBar }">Volver</a>
            	</div>
            	<div style="text-align: center; padding: 20px;">
           			<a class="boton-link" href="consulta_rapida_coord_telf.htm">Ver Gestiones Telef&oacute;nicas</a>
           		</div>
            </div>
            <div class="content-result">
            	<h2>Coordinaciones Realizadas</h2>
	        	<table>
	        		<thead>
	        		<tr>
	        			<td style="min-width: 80px;">Fecha</td>
	        			<td style="min-width: 80px;">Hora</td>
	        			<td>Tipo Dir.</td>
	        			<td class="td_opt">Agencia</td>
	        			<td style="min-width: 120px;">Direcci&oacute;n</td>
	        			<td class="td_opt">Referencia</td>
	        			<td class="td_opt">Observaciones</td>
	        			<td class="td_opt">Usuario</td>
	        		</tr>
	        		</thead>
	        		<tbody>
	        		<c:forEach items="${RPConsultaDetalleCoordinaciones}" var="coord" varStatus="rowCounter">
	        			<tr class='<c:if test="${rowCounter.count % 2 == 0}">on</c:if>'>
	        				<td class="td-center">
		        				<fmt:formatDate var="tempRowFecCoo" value="${coord.fecCoo}" pattern="dd/MM/yyyy"/>
		        				<c:out value="${tempRowFecCoo}"/>
	        				</td>
	        				<td><c:out value="${coord.horCoo}"/></td>
	        				<td><c:out value="${coord.desDir}"/></td>
	        				<td class="td_opt"><c:out value="${coord.nomAge}"/></td>
	        				<td style="text-align: left;"><c:out value="${coord.dirCoo}"/></td>
	        				<td class="td_opt"><c:out value="${coord.refCoo}"/>  <c:out value="${coord.ubiCoo}"/></td>
	        				<td class="td_opt"><c:out value="${coord.obsCoo}"/></td>
	        				<td class="td_opt"><c:out value="${coord.nomUsu}"/></td>
	        			</tr>
					</c:forEach>
						
					<c:forEach items="${RPCoordinacionRegistCoords}" var="coord" varStatus="rowCounter">
						<c:if test="${rowCounter.count == 1}">
						<tr>
							<td colspan="3" class="tdhr">Coordinaciones Recientes:</td>
						</tr>
						</c:if>
	        			<tr class='<c:if test="${rowCounter.count % 2 == 0}">on</c:if>'>
	        				<td class="td-center">
		        				<fmt:formatDate var="tempRowFecCoo" value="${coord.fecCoo}" pattern="dd/MM/yyyy"/>
		        				<c:out value="${tempRowFecCoo}"/>
	        				</td>
	        				<td><c:out value="${coord.horCoo}"/></td>
	        				<td><c:out value="${coord.desDir}"/></td>
	        				<td class="td_opt"><c:out value="${coord.nomAge}"/></td>
	        				<td style="text-align: left;"><c:out value="${coord.dirCoo}"/></td>
	        				<td class="td_opt"><c:out value="${coord.refCoo}"/></td>
	        				<td class="td_opt"><c:out value="${coord.obsCoo}"/></td>
	        				<td class="td_opt"><c:out value="${coord.nomUsu}"/></td>
	        			</tr>
					</c:forEach>
	        		</tbody>
	        	</table>
	        	
	        </div>
        </div>
        <%@include file="../include-footer.jsp" %>
    </body>
</html>