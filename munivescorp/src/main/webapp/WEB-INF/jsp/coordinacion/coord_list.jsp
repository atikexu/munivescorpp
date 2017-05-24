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
            	<h2 style="padding-bottom: 12px;">Lista de Coordinaciones Registradas</h2>
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
	        			<td style="min-width: 40px;"><span class="icon-equalizer"></span></td>
	        		</tr>
	        		</thead>
	        		<tbody>
	        		<c:forEach items="${RPConsultaDetalleCoordinaciones}" var="coord" varStatus="rowCounter">
	        			<tr class='<c:if test="${rowCounter.count % 2 == 0}">on</c:if>'>
	        				<td>
		        				<fmt:formatDate var="tempRowFecCoo" value="${coord.fecCoo}" pattern="dd/MM/yyyy"/>
		        				<c:out value="${tempRowFecCoo}"/>
	        				</td>
	        				<td><c:out value="${coord.horCoo}"/></td>
	        				<td><c:out value="${coord.desDir}"/></td>
	        				<td><c:out value="${coord.nomAge}"/></td>
	        				<td style="text-align: left;"><c:out value="${coord.dirCoo}"/></td>
	        				<td class="td_opt"><c:out value="${coord.refCoo}"/>  <c:out value="${coord.ubiCoo}"/></td>
	        				<td class="td_opt"><c:out value="${coord.obsCoo}"/></td>
	        				<td class="td_opt"><c:out value="${coord.nomUsu}"/></td>
	        				<c:if test="${menuActivo}">
	        				<td>
		        				<a href='coord_edit.htm?idCoord=<c:out value="${coord.id}"/>' title="Editar"><span class="icon-pencil"></span></a>
		        				<a href='coord_remove.htm?codBar=${SACoordinacionSelect.codBar }&idCoord=<c:out value="${coord.id}"/>' title="Borrar" onclick="return confirm('¿Esta seguro de Borrar ${coord.id} la Coordinacion?');"><span class="icon-bin2"></span></a>
	        				</td>
	        				</c:if>	
	        				<td></td>
	        			</tr>
					</c:forEach>
<%-- 	        		<c:forEach items="${RPCoordinacionSelectCoords}" var="coord" varStatus="rowCounter"> 
		        		<c:if test="${rowCounter.count == 1}">
						<tr>
							<td colspan="3" class="tdhr">Coordinaciones Recientes:</td>
						</tr>
						</c:if>
	        			<tr class='<c:if test="${rowCounter.count % 2 == 0}">on</c:if>'>
	        				<td>
		        				<fmt:formatDate var="tempRowFecCoo" value="${coord.fecCoo}" pattern="dd/MM/yyyy"/>
		        				<c:out value="${tempRowFecCoo}"/>
	        				</td>
	        				<td><c:out value="${coord.horCoo}"/></td>
	        				<td><c:out value="${coord.desDir}"/></td>
	        				<td><c:out value="${coord.nomAge}"/></td>
	        				<td style="text-align: left;"><c:out value="${coord.dirCoo}"/></td>
	        				<td class="td_opt"><c:out value="${coord.refCoo}"/></td>
	        				<td class="td_opt"><c:out value="${coord.obsCoo}"/></td>
	        				<td class="td_opt"><c:out value="${coord.nomUsu}"/></td>
	        				
	        				<td>
		        				<a href='coord_edit.htm?idCoord=<c:out value="${coord.id}"/>' title="Editar"><span class="icon-pencil"></span></a>
		        				<a href='coord_remove.htm?codBar=${SACoordinacionSelect.codBar }&idCoord=<c:out value="${coord.id}"/>' title="Borrar" onclick="return confirm('¿Esta seguro de Borrar ${coord.id} la Coordinacion?');"><span class="icon-bin2"></span></a>
	        				</td>
	        			</tr>
						</c:forEach>--%>
	        		</tbody>
	        	</table>
	        	<p class="font-s">OJO: Las coordinaciones estan a la espera de ser programadas, una vez realizada la programacion &eacute;stos ya no estar&aacute;n en esta lista. </p>
            </div>
        </div>
        <%@include file="../include-footer.jsp" %>
    </body>
</html>