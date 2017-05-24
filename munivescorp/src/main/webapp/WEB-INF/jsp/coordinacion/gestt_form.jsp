<%@include file="../include-param.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="../include-head.jsp" %>
<%--         <%@include file="../include-head-jquery-ui.jsp" %> --%>
        <link rel="stylesheet" href="../static/css/default-forms.css">
        <link rel="stylesheet" href="../static/css/default-grids.css">
        <script type="text/javascript" src="../static/js/date-en-US.js"></script>
        <script type="text/javascript" src="../static/js/jquery.ui.timeselect.js"></script>
        <script type="text/javascript">
        
        validaFormGestionTelf = function(){
        	if($("#horReg").val()!="" && $("#nroTlf").val()!="" && $("#fecReg").val().length==10){
        		$("#smt").attr('disabled','disabled');
        		return true;
        	}else{
        		$("#formAlert").html(" Seleccione Fecha, Hora y Telef. ");
        		return false;
        	}
        }
        onChangeCodMotivo = function(){
       		$('#desMot').val($('#codMot option:selected').text())
       		if($('#codMot option:selected').text().trim()=="COORDINACION"){
       			$("#aTempLinkCoordinar").show();
       		}else{
       			$("#aTempLinkCoordinar").hide();
       		}
        }
        onFormCoord = function(){
        	if($("#nroTlf").val()!=""){
        		$("#tempTelfRef").val($("#nroTlf").val());
            	document.fm_form_coord.submit();
        	}else{
        		$("#formAlert").html(" Seleccione un Teléfono valido. ");
        	}
        }
        </script>
    </head>
    <body>
    	<%@include file="../include-menu.jsp" %>
        <div class="content">
        	<div class="content-frame-generic">
            	<h2>Detalles</h2>
            	<br/>
           		<div>
           			<span class="label">C&oacute;digo de Cargo</span>
           			<span class="value">${SACoordinacionSelect.codBar }</span>
           		</div>
           		<c:if test="${USUARIO_TIPO == 'DI'}">
           		<div>
           			<span class="label">Cliente:</span>
           			<span class="value">${SACoordinacionSelect.nomCli }</span>
           		</div>
           		</c:if>
           		<div>
           			<span class="label">${SACoordinacionSelect.desProVal }</span>
           			<span class="value">${SACoordinacionSelect.valNroIde }</span>
           		</div>
           		<div>
           			<samp class="info">Emisi&oacute;n por: ${SACoordinacionSelect.valDesEmi }</samp>
           		</div>
           		<div>
           			<span class="label">Destinatario</span>
           			<span class="value">${SACoordinacionSelect.desNomApe }</span>
           		</div>
           		<div>
           			<span class="label">Titular</span>
           			<span class="value">${SACoordinacionSelect.titNomApe }</span>
           		</div>
           		<div>
           			<span class="label">Documento de identidad</span>
					<span class="value">${SACoordinacionSelect.titNumDoc }</span>
				</div>
				<div>
           			<span class="label">Tel&eacute;fono Domiciliario</span>
					<span class="value">${SACoordinacionSelect.titTlfDom!=0?SACoordinacionSelect.titTlfDom:"" }</span>
				</div>
				<div>
           			<span class="label">Tel&eacute;fono Laboral</span>
					<span class="value">${SACoordinacionSelect.titTlfLab!=0?SACoordinacionSelect.titTlfLab:"" }</span>
				</div>
				<div>
           			<span class="label">Tel&eacute;fono Opcionales</span>
					<span class="value">
					${SACoordinacionSelect.titTlfOpc!="0"?SACoordinacionSelect.titTlfOpc:"" }  
					${SACoordinacionSelect.titTlfMov!="0"?SACoordinacionSelect.titTlfMov:"" }  
					${SACoordinacionSelect.md1NumTlf!="0"?SACoordinacionSelect.md1NumTlf:"" }  
					${SACoordinacionSelect.md2NumTlf!="0"?SACoordinacionSelect.md2NumTlf:"" }
					</span>
				</div>
				
				<div>
           			<span class="label">Direcci&oacute;n Domiciliaria</span>
					<span class="value">${SACoordinacionSelect.dirDomDes }</span>
				</div>
				<div>
           			<span class="label">Direcci&oacute;n Laboral</span>
					<span class="value">${SACoordinacionSelect.dirLabDes }</span>
				</div>
				<div>
           			<span class="label">Direcci&oacute;n Opcional</span>
					<span class="value">${SACoordinacionSelect.dirOpcDes }</span>
				</div>
				
				<c:if test="${(SAConsultaDetalle.md1NomApe!='') }">
				<div>
           			<span class="label">Nombre del Mandatario 1:</span>
					<span class="value">${SAConsultaDetalle.md1NomApe }</span>
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
					<c:if test="${(SAConsultaDetalle.md2NumTlf!='') }">
           			<span class="label">Tel&eacute;fono del Mandatario 2:</span>
					<span class="value">${SAConsultaDetalle.md2NumTlf }</span>
					</c:if>
				</div>
				</c:if>
            </div>
            <div class="content-form-generic">
            	<c:if test="${(SACoordinacionSelect.indSit=='2') || (SACoordinacionSelect.indEst=='4')}">
            	<h2>Entregado</h2>
            	No es posible registrar m&aacute;s coordinaciones ni gestiones telef&oacute;nicas.
            	</c:if>
            	
            	<c:if test="${success}">
            	<h3><span class="icon-checkmark"></span> El registro fue guardado correctamente.</h3>
            	<div id="divTempNuevaSelect" style="padding: 12px;">
            		<a href="./?ref=telf" class="boton-default"><span class="icon-search"></span> Seleccionar Nuevo Destinatario</a>
            	</div>
            	<div id="divTempNuevaCoord" style="padding: 12px;">
            		<a href="./gestt_form.htm" class="boton-default">Registrar Nueva Llamada</a>
            		<br><br>
            	</div>
            	
            	<script type="text/javascript">
            		if(window.name=='form_coord'){
            			$("#divTempNuevaSelect").hide();
            			$("#divTempNuevaCoord").hide();
            			setTimeout(function(){window.close();},3*1000);
            		}
            	</script>
            	</c:if>
            	
            	<c:if test="${(SACoordinacionSelect.indSit!='2') && (SACoordinacionSelect.indEst!='4') && (not success)}">
     
            	<h2>
					${empty SARegistroCoordTelfEdit?"<span class='icon-file-empty'></span> Registro de ":"<span class='icon-pencil'></span> Editar " }
            		Gesti&oacute;n Telef&oacute;nica
           		</h2>
            	<form action="${empty SARegistroCoordTelfEdit?"gestt_save.htm":"gestt_update.htm" }" class="form-generic" method="post" onsubmit="return validaFormGestionTelf();">
	            	<div style="text-align: center;">
			    		<span id="formAlert"><c:if test="${success}"><span class="icon-checkmark"></span> El registro fue guardado correctamente.</c:if></span>
			    	</div>
            		<div>
            			<c:if test="${not empty SARegistroCoordTelfEdit }">
            				<fmt:formatDate var="tempFecReg" value="${SARegistroCoordTelfEdit.fecReg}" pattern="dd/MM/yyyy"/>
						</c:if>
						<fmt:formatDate var="tempNewFecReg" value="${RPNowDate}" pattern="dd/MM/yyyy"/>
            			<label for="fecReg">Fecha</label>
            			<input id="fecReg" type="text" name="fecReg" value="${not empty SARegistroCoordTelfEdit?tempFecReg:tempNewFecReg }" title="Fecha de entrega" placeholder="fecha" size="10" readonly="readonly">
            		</div>
            		<div>
            			<fmt:formatDate var="tempNewHorReg" value="${RPNowDate}" pattern="hh:mm a"/>
            			<label for="horReg">Hora</label>
            			<input type="text" id="horReg" name="horReg" value="${not empty SARegistroCoordTelfEdit?SARegistroCoordTelfEdit.horReg:tempNewHorReg}" title="Hora, ejemplo: 12:30" size="8" maxlength="12" placeholder="HH:MM" readonly="readonly">
            			<!--  >i class="font-s">Ejem: 3:30PM</i -->
            		</div>
            		<div>
            			<label for="nroTlf">Tel&eacute;fono</label>
            			<select id="tipTlf" name="tipTlf" onchange="if(this.value=='9'){$('#nroTlf').val('');$('#nroTlf').removeAttr('readonly');$('#nroTlf').select();}else{ $('#nroTlf').val($('#tempNroTelf'+this.value).val()); $('#nroTlf').attr('readonly','readonly');}">
            				<option value="1">Domiciliario</option>
            				<option value="2">Laboral</option>
            				<option value="3">Opcional</option>
            				<option value="4">Celular</option>
            				<option value="9">Otro</option>
            			</select>
            			<input id="nroTlf" type="text" name="nroTlf" value="${not empty SARegistroCoordTelfEdit?SARegistroCoordTelfEdit.nroTlf:SACoordinacionSelect.titTlfDom }" title="Telefono" placeholder="telefono" size="9" maxlength="12">
            			<input type="hidden" id="tempNroTelf1" name="tempTelf1" value="${SACoordinacionSelect.titTlfDom }">
            			<input type="hidden" id="tempNroTelf2" name="tempTelf2" value="${SACoordinacionSelect.titTlfLab }">
            			<input type="hidden" id="tempNroTelf3" name="tempTelf3" value="${SACoordinacionSelect.titTlfOpc }">
            			<input type="hidden" id="tempNroTelf4" name="tempTelf4" value="${SACoordinacionSelect.titTlfMov }">
            		</div>

            		<div>
            			<label for="codMot">Motivo</label>
            			<select id="codMot" name="codMot" onchange="onChangeCodMotivo();">
            			<c:forEach items="${RPCoordinacionSelectCoordTelfIndicadorGestionTelfs}" var="indicador" varStatus="rowCounter">
	            			<c:if test="${rowCounter.count == 1}">
	            				<c:set var="tempDesMot" value="${indicador.desGesTel}"></c:set>
	            			</c:if>
	        				<option value="${indicador.codGesTel}" ${SARegistroCoordTelfEdit.codMot==indicador.codGesTel?"selected='selected'":""}>${indicador.desGesTel}</option>
        				</c:forEach>
            			</select>
            			<input type="hidden" id="desMot" name="desMot" value="${not empty SARegistroCoordTelfEdit?SARegistroCoordTelfEdit.desMot:tempDesMot }">
            			<a id="aTempLinkCoordinar" class="boton-link" style="display: none;" href="#" onclick="onFormCoord();">Coordinar</a>
            		</div>
            		<div>
            			<label for="detObs">Observaciones:</label>
            			<textarea id="detObs" name="detObs" rows="2" cols="20">${SARegistroCoordTelfEdit.detObs }</textarea>
            		</div>
            		<div>
            			<label for="indSeg">Realizado por: </label>
            			<c:if test="${USUARIO_TIPO=='CL' }">
            				<input type="text" name="indDes" value="${USUARIO_CLIENTE.razonsocial }" readonly="readonly">
            				<input type="hidden" name="indSeg" value="2">
            			</c:if>
            			<c:if test="${USUARIO_TIPO!='CL' }">
            			<select id="indSeg" name="indSeg">
            				<option value="1" ${SARegistroCoordTelfEdit.indSeg=="1"?"selected='selected'":""}>Dataimagenes</option>
            				<option value="3" ${SARegistroCoordTelfEdit.indSeg=="3"?"selected='selected'":""}>Call Center</option>
            			</select>
            			</c:if>
            		</div>
            		<div>
            			<label for="smt"> </label>
						<input type="submit" class="boton-default" value="Registrar " id="smt"/>
						<input type="hidden" name="idCoordTelf" value="${SARegistroCoordTelfEdit.id}">
						<input type="hidden" name="codBar" value="${SACoordinacionSelect.codBar }">
						<input type="hidden" name="idDistribucion" value="${SACoordinacionSelect.id }">
					</div>
            	</form>
            	<form action="./coord_re_form.htm" name="fm_form_coord" target="form_coord">
            		<input type="hidden" name="telfRef" id="tempTelfRef">
            	</form>
            	
            	</c:if>
            </div>
            <div class="content-form-generic">
            <c:if test="${(SACoordinacionSelect.indSit!='2') && (SACoordinacionSelect.indEst!='4')}">
            <h2 style="padding-bottom: 12px;">Gestiones Telef&oacute;nicas Registradas</h2>
            	<table>
	        		<thead>
	        		<tr>
	        			<td>Fecha</td>
	        			<td>Hora</td>
	        			<td>Tel&eacute;fono</td>
	        			<td>Resultado</td>
	        			<td><span class="icon-equalizer"></span></td>
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
	        				<td>
		        				<a href='gestt_edit.htm?idCoordTelf=<c:out value="${telf.id}"/>' title="Editar"><span class="icon-pencil"></span></a>
		        				<a href='gestt_remove.htm?codBar=${SACoordinacionSelect.codBar }&idCoordTelf=<c:out value="${telf.id}"/>' title="Borrar" onclick="return confirm('¿Esta seguro de Borrar la Gestión Telefónica?');"><span class="icon-bin2"></span></a>
	        				</td>
	        			</tr>
					</c:forEach>
	        		</tbody>
	        	</table>
	        	<p class="font-s">OJO: Para visualizar a mayor detalles las gestiones telef&oacute;nicas, elija la opcion "Listar Gest. Telef&oacute;nicas" situada en la parte superior.</p>
        	</c:if>
            </div>
        </div>
        <%@include file="../include-footer.jsp" %>
        <script type="text/javascript">
        //$("#fecReg").datepicker({minDate: 0, maxDate: 60 });
        //$("#fecReg").datepicker({beforeShowDay: $.datepicker.noWeekends, minDate: 0, maxDate: 60 });
        //$("#horReg").timeselect({'step': 10, maxResults: 12, autocompleteSettings: {autoFocus: true} });
        </script>
    </body>
</html>