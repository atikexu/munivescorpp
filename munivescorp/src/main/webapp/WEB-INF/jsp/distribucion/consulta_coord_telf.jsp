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
            	<h2>Gesti&oacute;n Telef&oacute;nica</h2>
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
           		<div style="text-align: center;">
           			<sec:authorize access="hasAnyRole('DI_ADMIN','DI_COORDINACION_TELEF')">
           				<c:if test="${(SAConsultaDetalle.indSit!='2')}">
	            		<a class="boton-default" href="../coordinacion/gestt_buscar.htm?codBar=${SAConsultaDetalle.codBar }">Registrar Nueva Gesti&oacute;n Telef.</a>
	            		</c:if>
	            	</sec:authorize>
           			<a class="boton-default" href="consulta_detalle.htm?codBar=${SAConsultaDetalle.codBar }">Volver</a>
           		</div>
           		<div style="text-align: center; padding: 20px;">
           			<a class="boton-link" href="consulta_coord.htm">Ver Coordinaciones</a>
           		</div>
            </div>
            <div class="content-result">
            	<h2>Gestiones Telef&oacute;nicas Realizadas</h2>
	        	<table>
	        		<thead>
	        		<tr>
	        			<td style="min-width: 100px;">Fecha</td>
	        			<td style="min-width: 100px;">Hora</td>
	        			<td>Tel&eacute;fono</td>
	        			<td>Motivo</td>
	        			<td>Observaci&oacute;n</td>
	        			<td>Usuario</td>
	        		</tr>
	        		</thead>
	        		<tbody>
	        		<c:forEach items="${RPConsultaDetalleCoordTelfs}" var="telf" varStatus="rowCounter">
	        			<tr class='<c:if test="${rowCounter.count % 2 == 0}">on</c:if>'>
	        				<td class="td-center">
		        				<fmt:formatDate var="tempRowFecReg" value="${telf.fecReg}" pattern="dd/MM/yyyy"/>
		        				<c:out value="${tempRowFecReg}"/>
	        				</td>
	        				<td><c:out value="${telf.horReg}"/></td>
	        				<td><c:out value="${telf.nroTlf}"/></td>
	        				<td><c:out value="${telf.desMot}"/></td>
	        				<td><c:out value="${telf.detObs}"/></td>
	        				<td><c:out value="${telf.nomUsu}"/></td>
	        			</tr>
					</c:forEach>
						
					<c:forEach items="${RPCoordinacionRegistCoordTelfs}" var="telf" varStatus="rowCounter">
						<c:if test="${rowCounter.count == 1}">
						<tr>
							<td colspan="3" class="tdhr">Registros de Llamasdas Recientes:</td>
						</tr>
						</c:if>
	        			<tr class='<c:if test="${rowCounter.count % 2 == 0}">on</c:if>'>
	        				<td class="td-center">
		        				<fmt:formatDate var="tempRowFecReg" value="${telf.fecReg}" pattern="dd/MM/yyyy"/>
		        				<c:out value="${tempRowFecReg}"/>
	        				</td>
	        				<td><c:out value="${telf.horReg}"/></td>
	        				<td><c:out value="${telf.nroTlf}"/></td>
	        				<td><c:out value="${telf.desMot}"/></td>
	        				<td><c:out value="${telf.detObs}"/></td>
	        				<td><c:out value="${telf.nomUsu}"/></td>
	        			</tr>
					</c:forEach>
					
	        		</tbody>
	        	</table>
	        </div>
        </div>
        <%@include file="../include-footer.jsp" %>
    </body>
</html>