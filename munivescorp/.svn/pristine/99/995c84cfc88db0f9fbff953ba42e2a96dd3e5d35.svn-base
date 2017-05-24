<%@include file="../include-param.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="../include-head.jsp" %>
        <%@include file="../include-head-jquery-ui.jsp" %>
        <link rel="stylesheet" href="../static/css/default-forms.css">
        <link rel="stylesheet" href="../static/css/default-grids.css">
    </head>
    <body onload="$('#codBar').select();">
    	<%@include file="../include-menu.jsp" %>
        <div class="content">
            <div class="content-form-generic">
            	<h2>Buscar por:</h2>
            	<form action="coord_buscar.htm" class="form-generic" method="post">
            		<div>
            			<label for="codBar">Codigo de Cargo:</label>
            			<input id="codBar" type="text" name="codBar" value="${codBar }" title="Ingrese codigo de cargo / barras" placeholder="codigo de cargo" size="15" maxlength="16">
            		</div>
            		<div>
            			<label for="nroRef">Nro. Tarjeta / Cta / Pqte.:</label>
            			<input id="nroRef" type="text" name="nroRef" value="${nroRef }" title="Ingrese Nro de Tarjeta / Cuenta" placeholder="tarjeta / cuenta" size="16" maxlength="30">
            		</div>
            		<div>
            			<label for="docIde">DNI/RUC/Otros:</label>
            			<input id="docIde" type="text" name="docIde" value="${docIde }" title="Ingrese documento" placeholder="dni / otros" size="12" maxlength="30">
            		</div>
            		<div>
            			<label for="nomDes">Nombre de Dest./Titular/Mand.:</label>
            			<input id="nomDes" type="text" name="nomDes" value="${nomDes }" title="Ingrese nombre de Destinatario o Titular" placeholder="destinatario o titular" size="26" maxlength="30">
            		</div>
            		<div>
            			<label for="smt"> </label>
						<input type="submit" class="boton-default" style="min-width: 130px;" value="Buscar" id="smt"/>
						<c:if test="${not empty ref }">
						<input type="hidden" name="ref" value="${ref }">
						</c:if>
					</div>
            	</form>
            </div>
            <div class="content-form-generic" style="text-align: left;">
            	<img class="info-generic-img" style="border: 0;" src="../static/img/img_reg_coord.png">
            </div>
            <div class="content-result">
            	<h3>Resultados de b&uacute;squeda:</h3>
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
	        			<td>Seleccion</td>
	        		</tr>
	        		</thead>
	        		<tbody>
	        		<c:forEach items="${resultadoSeleccion}" var="dist" varStatus="rowCounter">
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
	        					<c:if test="${(dist.indSit!='2')}">
	        					<a href='${not empty ref?"gestt_buscar":"coord_form" }.htm?codBar=<c:out value="${dist.codBar}"/>'><samp class=" icon-zoom-in"></samp> Seleccionar </a>
	        					</c:if>
        					</td>
	        			</tr>
					</c:forEach>
	        		</tbody>
	        	</table>
	        </div>
        </div>
        <%@include file="../include-footer.jsp" %>
    </body>
</html>