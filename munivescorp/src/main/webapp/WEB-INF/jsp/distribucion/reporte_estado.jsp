<%@include file="../include-param.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="../include-head.jsp" %>
        <%@include file="../include-head-jquery-ui.jsp" %>
        <link rel="stylesheet" href="../static/css/default-forms.css">
        <link rel="stylesheet" href="../static/css/default-grids.css">
    </head>
    <body onload="$('#ordPro').select();">
    	<%@include file="../include-menu.jsp" %>
        <div class="content">
            <div class="content-form-generic">
            	<h2>B&uacute;squeda </h2>
            	<form action="reporte_estado_op.htm" class="form-generic" method="post">
            		<div>
            			<label for="ordPro">Orden Proceso (Nro. Recepci&oacute;n):</label>
            			<input id="ordPro" type="text" name="ordPro" value="${ordPro }" title="Ingrese Nro de Recepcion o Nro de Orden de Proceso" placeholder="Ejem:150125" size="15" maxlength="6">
            		</div>
            		<div>
            			<label for="smt"> </label>
						<input type="submit" class="boton-default" style="min-width: 130px;" value="Consultar" id="smt"/>
					</div>
            	</form>
            </div>
            <div class="content-form-generic">
            	<div class="content-space-generic">
            	<h3>Orden Proceso</h3>
            	<c:if test="${not empty RPOdenProceso}">
            	<table>
            		<thead>
            			<tr>
            				<td>OP</td>
            				<td>Cliente</td>
            				<td>Producto</td>
            				<td>Fecha Proceso</td>
            				<td>Total Env&iacute;os</td>
            			</tr>
            		</thead>
            		<tbody>
            			<tr>
            				<td><h1>${RPOdenProceso.op }</h1></td>
            				<td>${RPOdenProceso.cliente }</td>
            				<td>${RPOdenProceso.producto }</td>
            				<td>
            					<fmt:formatDate var="tempFecPro" value="${RPOdenProceso.fecha}" pattern="dd/MM/yyyy"/>
	        					<c:out value="${tempFecPro}"/>
           					</td>
           					<td><h1>${cantTotal }</h1></td>
            			</tr>
            		</tbody>
            	</table>
            	</c:if>
            	</div>
            </div>
            <div style="width: 100%; overflow: hidden;"></div>
            <div class="content-form-generic">
            	<div class="content-space-generic">
            	<h3>Estado por tipo de Gesti&oacute;n</h3>
	        	<c:if test="${not empty RPOdenProcesoAvance}">
            	<table>
            		<thead>
            			<tr>
            				<td>Tipo de Gesti&oacute;n</td>
            				<td># Valorado</td>
            				<td>% Valorado</td>
            			</tr>
            		</thead>
            		<tbody>
            		<c:forEach items="${RPOdenProcesoAvance}" var="avance" varStatus="rowCounter">
            			<tr class='<c:if test="${rowCounter.count % 2 == 0}">on</c:if>'>
            				<td>${avance.gestion }</td>
            				<td>${avance.cantidad }</td>
            				<td>
            					<fmt:formatNumber type="PERCENT" value="${(avance.cantidad/RPOdenProcesoAvanceTotal)}" />
            				</td>
            			</tr>
           			</c:forEach>
            		</tbody>
            		<tfoot>
            			<tr>
            				<td style="text-align: right;">TOTAL:</td>
            				<td>${cantTotal }</td>
            				<td>100%</td>
            			</tr>
            		</tfoot>
            	</table>
            	<br/>
            	<img style="width:100%; max-width: 450px;" src="http://chart.apis.google.com/chart?cht=p&chs=450x200&chd=t:${RPOdenProcesoAvanceImgValue}&chxt=x&chxl=0:${RPOdenProcesoAvanceImgLabel}">
            	</c:if>
            	</div>
	        </div>
	        <div class="content-form-generic">
	        	<div class="content-space-generic">
            	<h3>Estado de Gesti&oacute;n de Im&aacute;genes</h3>
	        	<c:if test="${not empty RPOdenProcesoCargos}">
            	<table>
            		<thead>
            			<tr>
            				<td>Gesti&oacute;n Im&aacute;gen</td>
            				<td># Im&aacute;genes</td>
            				<td>% Im&aacute;genes</td>
            			</tr>
            		</thead>
            		<tbody>
            		<c:forEach items="${RPOdenProcesoCargos}" var="cargos" varStatus="rowCounter">
            			<tr class='<c:if test="${rowCounter.count % 2 == 0}">on</c:if>'>
            				<td>${cargos.gestion} ${(cargos.cargos=="0")?("Sin Imagen"):("Con Imagen") }</td>
            				<td>${cargos.cantidad }</td>
            				<td>
            					<fmt:formatNumber type="PERCENT" value="${(cargos.cantidad/RPOdenProcesoCargosTotal)}" />
            				</td>
            			</tr>
           			</c:forEach>
            		</tbody>
            		<tfoot>
            			<tr>
            				<td style="text-align: right;">TOTAL:</td>
            				<td>${cantTotalCargos }</td>
            				<td>100%</td>
            			</tr>
            		</tfoot>
            	</table>
            	<br/>
            	<img style="width:100%; max-width: 450px;" src="http://chart.apis.google.com/chart?cht=p&chs=450x200&chd=t:${RPOdenProcesoCargosImgValue}&chxt=x&chxl=0:${RPOdenProcesoCargosImgLabel}">
            	</c:if>
            	</div>
	        </div>
	        
        </div>
        <%@include file="../include-footer.jsp" %>
    </body>
</html>