<%@include file="../include-param.jsp" %>
<div class="content-form-find">
	<h2>Lista de visitas</h2>
   	<table>
    	<thead>
    		<tr>
    			<td>Hoja de Ruta</td>
    			<td>Mensajero</td>
    			<td>Indicador</td>
    			<td>Descripci&oacute;n</td>
    			<td>Hora</td>
    			<td>Visor</td>
    		</tr>
    	</thead>
    	<tbody>
    		<c:forEach items="${resultadoVisitas}" var="visita" varStatus="rowCounter">
    			<tr class='<c:if test="${rowCounter.count % 2 == 0}">on</c:if>'>
    				<td><c:out value="${visita.nroHoj}"/></td>
    				<td><c:out value="${visita.nomMsj}"/></td>
    				<td><c:out value="${visita.desSit}"/></td>
    				<td><c:out value="${visita.desMot}"/></td>
    				<td><c:out value="${visita.horVis}"/></td>
    				<td><c:out value="${visita.nomUsu}"/></td>
    			</tr>
			</c:forEach>
    	</tbody>
    </table>
</div>
