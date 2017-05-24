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
            	<h2>Detalle de Consulta</h2>
            	<br/>
           		<div>
           			<span class="label">C&oacute;digo de Cargo:</span>
           			<span class="value">${SAConsultaDetalle.codBar }</span>
           		</div>
           		<c:if test="${USUARIO_TIPO == 'DI'}">
           		<div>
           			<span class="label">Cliente:</span>
           			<span class="value">${SAConsultaDetalle.nomCli }</span>
           		</div>
           		</c:if>
           		<div>
           			<span class="label">${SAConsultaDetalle.desProVal } :</span>
           			<span class="value">${SAConsultaDetalle.valNroIde }</span>
           		</div>
           		<div>
           			<samp class="info">Emisi&oacute;n por: ${SAConsultaDetalle.valDesEmi }</samp>
           		</div>
           		<div>
           			<span class="label">Destinatario:</span>
           			<span class="value">${SAConsultaDetalle.desNomApe }</span>
           		</div>
           		<div>
           			<span class="label">Titular:</span>
           			<span class="value">${SAConsultaDetalle.titNomApe }</span>
           		</div>
           		<div>
           			<span class="label">Documento de identidad:</span>
					<span class="value">${SAConsultaDetalle.titNumDoc }</span>
				</div>
				<div>
           			<span class="label">Tel&eacute;fono Domiciliario:</span>
					<span class="value">${SAConsultaDetalle.titTlfDom!=0?SAConsultaDetalle.titTlfDom:"" }</span>
				</div>
				<div>
           			<span class="label">Tel&eacute;fono Laboral:</span>
					<span class="value">${SAConsultaDetalle.titTlfLab!=0?SAConsultaDetalle.titTlfLab:"" }</span>
				</div>
				<div>
           			<span class="label">Tel&eacute;fono Opcionales:</span>
					<span class="value">
					${SAConsultaDetalle.titTlfOpc!="0"?SAConsultaDetalle.titTlfOpc:"" }  
					${SAConsultaDetalle.titTlfMov!="0"?SAConsultaDetalle.titTlfMov:"" }  
					${SAConsultaDetalle.md1NumTlf!="0"?SAConsultaDetalle.md1NumTlf:"" }  
					${SAConsultaDetalle.md2NumTlf!="0"?SAConsultaDetalle.md2NumTlf:"" }
					</span>
				</div>
				<div>
           			<span class="label">Direcci&oacute;n Domiciliaria:</span>
					<span class="value">${SAConsultaDetalle.dirDomDes }</span>
				</div>
				<div>
           			<span class="label">Direcci&oacute;n Laboral:</span>
					<span class="value">${SAConsultaDetalle.dirLabDes }</span>
				</div>
				<div>
           			<span class="label">Direcci&oacute;n Opcional:</span>
					<span class="value">${SAConsultaDetalle.dirOpcDes }</span>
				</div>
				<c:if test="${(SAConsultaDetalle.indSit==2) }">
				<div>
					<c:if test="${(RPConsultaDetalleCoordEntregado.indDir==9) }">
           			<span class="label"><span class="icon-location"></span> Direcci&oacute;n de Entrega:</span>
					<span class="value">${RPConsultaDetalleCoordEntregado.dirCoo }</span>
					</c:if>
				</div>
				</c:if>
				
				<c:if test="${(SAConsultaDetalle.md1NomApe!='') }">
				<div>
           			<span class="label">Nombre del Mandatario 1:</span>
					<span class="value">${SAConsultaDetalle.md1NomApe }</span>
				</div>
				<div>
					<c:if test="${(SAConsultaDetalle.md1NumDoc!='') }">
           			<span class="label">Num. Doc. del Mand. 1:</span>
					<span class="value">${SAConsultaDetalle.md1NumDoc }</span>
					</c:if>
				</div>
				<div>
					<c:if test="${(SAConsultaDetalle.md1NumTlf!='') }">
           			<span class="label">Tel&eacute;fono del Mandatario 1:</span>
					<span class="value">${SAConsultaDetalle.md1NumTlf }</span>
					</c:if>
				</div>
				</c:if>
				
				<c:if test="${(SAConsultaDetalle.md2NomApe!='') }">
				<div>
           			<span class="label">Nombre del Mandatario 2:</span>
					<span class="value">${SAConsultaDetalle.md2NomApe }</span>
				</div>
				<div>
					<c:if test="${(SAConsultaDetalle.md2NumDoc!='') }">
           			<span class="label">Num. Doc. del Mand. 2:</span>
					<span class="value">${SAConsultaDetalle.md2NumDoc }</span>
					</c:if>
				</div>
				<div>
					<c:if test="${(SAConsultaDetalle.md2NumTlf!='') }">
           			<span class="label">Tel&eacute;fono del Mandatario 2:</span>
					<span class="value">${SAConsultaDetalle.md2NumTlf }</span>
					</c:if>
				</div>
				</c:if>
				
            </div>
            <div class="content-frame-generic">
            	<h2> &nbsp; </h2>
            	<br/>
           		<div>
           			<span class="label">Fecha de Proceso:</span>
           			<span class="value"><fmt:formatDate value="${SAConsultaDetalle.fecPro}" pattern="dd/MM/yyyy"/></span>
           		</div>
           		<div>
           			<span class="label">Nro. de Proceso (OP):</span>
           			<span class="value"><b>${SAConsultaDetalle.ordPro }</b></span>
           		</div>
           		<div>
           			<span class="label">Situaci&oacute;n:</span>
           			<span class="value"><b>${SAConsultaDetalle.desSit }</b></span>
           		</div>
           		<c:if test="${USUARIO_TIPO == 'DI'}">
           		<div>
           			<span class="label">Estado:</span>
           			<span class="value">${SAConsultaDetalle.desEst }</span>
           		</div>
           		<div>
           			<span class="label">Rendici&oacute;n <c:if test="${SAConsultaDetalle.nroRen!=0 }">Nro. ${SAConsultaDetalle.nroRen }</c:if>: </span>
           			<span class="value"><c:if test="${SAConsultaDetalle.nroRen!=0 }"><fmt:formatDate value="${SAConsultaDetalle.fecRen }" pattern="dd/MM/yyyy"/></c:if></span>
           		</div>
           		<div>
           			<span class="label">Tr&aacute;mite ${SAConsultaDetalle.codMot }: </span>
           			<span class="value">${SAConsultaDetalle.desMot }</span>
           		</div>
           		</c:if>
           		<div>
           			<span class="label">Hoja de Ruta:</span>
           			<span class="value">${SAConsultaDetalle.nroHoj=='0'?'':SAConsultaDetalle.nroHoj }</span>
           		</div>
           		<div>
           			<span class="label">Recibido por:</span>
           			<span class="value">${SAConsultaDetalle.perRec }</span>
           		</div>
           		<div>
           			<span class="label">Vinculo:</span>
           			<span class="value">${SAConsultaDetalle.desVin }</span>
           		</div>
           		<div style="text-align: center; padding: 20px;">
           			<a class="boton-default" href="consulta_coord_rapida.htm">Coordinaciones</a>
           			<a class="boton-default" href="consulta_rapida_coord_telf.htm">Gest. Telef&oacute;nica</a>
           		</div>
           		<c:forEach items="${codBarImgs}" var="imgCargo" >
           		<div>
           			<div style="width: 180px;margin: 0 auto;">
	           			<a style="padding: 5px; cursor: pointer;" target="popup_cargo"
	           			   <c:if test="${imgCargo!='' }">
	           			   href="./consulta_imgcar_zoom.htm?name=${imgCargo}&quality=high&type=png"  
	           			   onClick="window.open('./consulta_imgcar_zoom.htm?name=${imgCargo }', this.target, 'width=600,height=500,location=no,directories=no,menubar=no,status=no'); return false;"
	           			   </c:if>
	           			   >
	           			 <img alt="Cargo" class="imagen" src="./consulta_imgcar.htm?name=${imgCargo}&type=png"> 
	           			</a>
           			</div>           			
           		</div>
           		</c:forEach>
            </div>
            <div class="content-result">
            	<c:if test="${(USUARIO_TIPO=='DI') || ( (USUARIO_TIPO=='CL') && (CLIENTE_CONFIG_CONSRVPAQ=='1') )}">
            	<h2>Sub-Paquetes <c:if test="${(SAConsultaDetalle.codCli=='0007')}" >: ${totalPaquetes} </c:if> </h2>
	        	<table>
	        		<thead>
	        		<tr>
	        			<td>Nro. Pqte/Tarj.</td>
	        			<td class="td_opt">
	        			<c:if test="${(SAConsultaDetalle.codCli=='0007')}" >Cantidad Vales </c:if>
	        			<c:if test="${(SAConsultaDetalle.codCli!='0007')}" >Nro. Ide. </c:if>
	        			</td>
	        			<td class="td_opt">
	        			<c:if test="${(SAConsultaDetalle.codCli=='0007')}" >Codigo Vale </c:if>
	        			<c:if test="${(SAConsultaDetalle.codCli!='0007')}" >Otros Cods.</c:if>
	        			</td>
	        			<td>
	        			<c:if test="${(SAConsultaDetalle.codCli=='0007')}" >Dec. Codigo vale </c:if>
	        			<c:if test="${(SAConsultaDetalle.codCli!='0007')}" >Nombre Dest./Agte.</c:if>
	        			</td>
	        			<c:if test="${(SAConsultaDetalle.codCli!='0007')}" >
	        			<td>Otros Datos</td>
	        			</c:if>
	        			<td>Descripci&oacute;n</td>
	        		</tr>
	        		</thead>
	        		<tbody>
	        		<c:forEach items="${RPConsultaDetallePaquetes}" var="paquete" varStatus="rowCounter">
	        			<tr class='<c:if test="${rowCounter.count % 2 == 0}">on</c:if>'>
	        				<td style="${(idPaquete==paquete.id)?'font-weight: bold;':'' }"><c:out value="${paquete.nroDoc }"/></td>
	        				<td class="td_opt">
	        				<c:if test="${(SAConsultaDetalle.codCli=='0007')}" ><c:out value="${paquete.tlfRef }"/> </c:if>
	        				<c:if test="${(SAConsultaDetalle.codCli!='0007')}" ><c:out value="${paquete.nroIde }"/>.</c:if>	        				
	        				</td>
	        				<td class="td_opt">
	        				<c:out value="${paquete.codOtr}"/>
	        				</td>
	        				<td>	
	        				<c:if test="${(SAConsultaDetalle.codCli=='0007')}" ><c:out value="${paquete.nomOtr }"/> </c:if>
	        				<c:if test="${(SAConsultaDetalle.codCli!='0007')}" ><c:out value="${paquete.nomDes }"/>	</c:if>	 
	        				
	        				</td>
	        				<c:if test="${(SAConsultaDetalle.codCli!='0007')}" >
	        				<td><c:out value="${paquete.nomOtr } ${paquete.tlfRef }"/></td>
	        				</c:if>
	        				<td><c:out value="${paquete.valDes }"/></td>
	        			</tr>
					</c:forEach>
					
	        		</tbody>
	        	</table>
            	</c:if>
            	
            	
            	<h2>Visitas Realizadas</h2>
	        	<table>
	        		<thead>
	        		<tr>
	        			<td>Fecha</td>
	        			<td>Hoja de Ruta</td>
	        			<td class="td_opt">Mensajero</td>
	        			<!--<td>Tramitaci&oacute;n</td>-->   	
	        			<td>Motivo</td>		
	        			<td>Situaci&oacute;n</td>
	        			<td>Hora</td>
	        			<td>Direcci&oacute;n</td>
	        		</tr>
	        		</thead>
	        		<tbody>
	        		<c:forEach items="${RPConsultaDetalleVisitas}" var="visita" varStatus="rowCounter">
	        			<tr class='<c:if test="${rowCounter.count % 2 == 0}">on</c:if>'>
	        				<td class="td-center">
		        				<fmt:formatDate var="tempRowFecHoj" value="${visita.fecHoj}" pattern="dd/MM/yyyy"/>
		        				<c:out value="${tempRowFecHoj}"/>
	        				</td>
	        				<td><c:out value="${visita.nroHoj}"/></td>
	        				<td class="td_opt"><c:out value="${visita.nomMsj}"/></td>
	        				<td><c:out value="${visita.desMot}"/></td>
	        				<td><c:out value="${visita.desSit}"/></td>
	        				<td><c:out value="${visita.horVis}"/></td>
	        				<td><c:out value="${visita.desTip}"/></td>
	        			</tr>
					</c:forEach>
					
	        		</tbody>
	        	</table>
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