<%@include file="../include-param.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="../include-head.jsp" %>
        <link rel="stylesheet" href="../static/css/default-forms.css">
        <link rel="stylesheet" href="../static/css/default-grids.css">
    </head>
    <body>
    	<%@include file="../include-menu.jsp" %>
        <div class="content">
        	<div class="content-frame-generic" style="width: 50%;">
            	<h2>Detalles</h2>
            	<br/>
           		<div>
           			<span class="label">C&oacute;digo de Cargo</span>
           			<span class="value">${SACoordinacionSelect.codBar }</span>
           		</div>
           		<div>
           			<span class="label">Valorado</span>
           			<span class="value">${SACoordinacionSelect.valNroIde }</span>
           		</div>
           		<div>
           			<span class="label">Destinatario</span>
           			<span class="value">${SACoordinacionSelect.desNomApe }</span>
           		</div>
           		<div>
           			<span class="label">Titular</span>
           			<span class="value">${SACoordinacionSelect.titNomApe }</span>
           		</div>
            </div>
            <div class="content-form-generic" style="width: 100%; overflow: auto;">
            	<h2 style="padding-bottom: 12px;">Lista de Gestiones Telef&oacute;nicas Registradas</h2>
            	<table>
	        		<thead>
	        		<tr>
	        			<td>Fecha</td>
	        			<td>Hora</td>
	        			<td>Tel&eacute;fono</td>
	        			<td>Resultado</td>
	        			<td>Observacion</td>
	        			<td>Usuario</td>
	        			<td style="min-width: 40px;"><span class="icon-equalizer"></span></td>
	        		</tr>
	        		</thead>
	        		<tbody>
	        		<c:forEach items="${RPConsultaDetalleCoordTelfs}" var="telf" varStatus="rowCounter">
	        			<tr class='<c:if test="${rowCounter.count % 2 == 0}">on</c:if>'>
	        				<td>
		        				<fmt:formatDate var="tempRowFecReg" value="${telf.fecReg}" pattern="dd/MM/yyyy"/>
		        				<c:out value="${tempRowFecReg}"/>
	        				</td>
	        				<td><c:out value="${telf.horReg}"/></td>
	        				<td><c:out value="${telf.nroTlf}"/></td>
	        				<td><c:out value="${telf.desMot}"/></td>
	        				<td><c:out value="${telf.detObs}"/></td>
	        				<td><c:out value="${telf.nomUsu}"/></td>
	        				<td></td>
	        			</tr>
					</c:forEach>
	        		<c:forEach items="${RPCoordinacionSelectCoordTelfs}" var="telf" varStatus="rowCounter">
	        			<c:if test="${rowCounter.count == 1}">
						<tr>
							<td colspan="3" class="tdhr">Registros de Llamadas Recientes:</td>
						</tr>
						</c:if>
	        			<tr class='<c:if test="${rowCounter.count % 2 == 0}">on</c:if>'>
	        				<td>
		        				<fmt:formatDate var="tempRowFecReg" value="${telf.fecReg}" pattern="dd/MM/yyyy"/>
		        				<c:out value="${tempRowFecReg}"/>
	        				</td>
	        				<td><c:out value="${telf.horReg}"/></td>
	        				<td><c:out value="${telf.nroTlf}"/></td>
	        				<td><c:out value="${telf.desMot}"/></td>
	        				<td><c:out value="${telf.detObs}"/></td>
	        				<td><c:out value="${telf.nomUsu}"/></td>
	        				<td>
		        				<a href='gestt_edit.htm?idCoordTelf=<c:out value="${telf.id}"/>' title="Editar"><span class="icon-pencil"></span></a>
		        				<a href='gestt_remove.htm?codBar=${SACoordinacionSelect.codBar }&idCoordTelf=<c:out value="${telf.id}"/>' title="Borrar" onclick="return confirm('¿Esta seguro de Borrar la Gestión Telefónica?');"><span class="icon-bin2"></span></a>
	        				</td>
	        			</tr>
					</c:forEach>
	        		</tbody>
	        	</table>
	        	<p class="font-s">OJO: Las gestiones telef&oacute;nicas est&aacute;n a la espera de ser descargadas, una vez realizada la descarga &eacute;stos ya no estar&aacute;n en esta lista. </p>
            </div>
        </div>
        <%@include file="../include-footer.jsp" %>
    </body>
</html>