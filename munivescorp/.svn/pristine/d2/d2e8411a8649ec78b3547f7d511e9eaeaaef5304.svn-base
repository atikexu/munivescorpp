<%@include file="../include-param.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="../include-head.jsp" %>
        <%@include file="../include-head-jquery-ui.jsp" %>
        <link rel="stylesheet" href="../static/css/default-forms_no_select.css"> 
        <link rel="stylesheet" href="../static/css/default-grids.css">
        <link href="../static/search_select/jquery-customselect.css" rel="stylesheet">
        
        <script type="text/javascript">
        $(function($){
            $.datepicker.regional['es'] = {
                closeText: 'Cerrar',
                prevText: '<Ant',
                nextText: 'Sig>',
                currentText: 'Hoy',
                monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
                monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
                dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
                dayNamesShort: ['Dom','Lun','Mar','Mié','Juv','Vie','Sáb'],
                dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sá'],
                weekHeader: 'Sm',
                dateFormat: 'dd/mm/yy',
                firstDay: 1,
                isRTL: false,
                showMonthAfterYear: false,
                yearSuffix: ''
            };
            $.datepicker.setDefaults($.datepicker.regional['es']);
        });
        validaFormCoord = function(){
        	if($("#fecCoo").val().length==10 && $("#dirCoo").val().trim().length>0){
        		$("#smt").attr('disabled','disabled');
        		$("#smt").val('Espere...');
        		return true;
        	}else{
        		if($("#fecCoo").val().length!=10){
        			$("#formAlert").html("Seleccione FECHA COORDINADA");
        		}else{
        			$("#formAlert").html("Ingrese o Seleccione DIRECCI&Oacute;N");
        		}
        		return false;
        	}
        }
        onChangeLugar = function(){
        	if($("#indLug").val()=='1'){
        		$('#divDirAgencia').hide();
        		$('#divDirDestinatario').show(); 
        		$('#divDirDestinatario').select();
        		$('#dirCoo').val(''); 
       			$('#dirCoo').attr('readonly','readonly');
        		onChangeDirDest();
       		}else if($("#indLug").val()=='2'){ 
       			$('#divDirDestinatario').hide(); 
       			$('#divDirAgencia').show(); 
       			$('#divDirAgencia').select(); 
       			$('#dirCoo').val(''); 
       			$('#dirCoo').attr('readonly','readonly');
       			onChangeDirAgente();
       			console.log("khe");
       			$('#codAge').toggle();       
       	      	$('#codAge').attr('size',0);
       		}else{
       			//...
       		}
        }
        onChangeDirDest = function(){
        	if($("#indDir").val()=='9'){
        		$('#dirCoo').val(''); 
        		$('#dirCoo').removeAttr('readonly'); 
        		$('#dirCoo').select();
        		$('#ubiCoo').val('');
        	}else{ 
        		$('#dirCoo').val($('#indDirTmp'+$("#indDir").val()).val()); 
        		$('#dirCoo').attr('readonly','readonly');
        		$('#ubiCoo').val($('#ubiDirTmp'+$("#indDir").val()).val()); 
        	}
        }
        onChangeDirAgente = function(){
        	if($("#codAge").val() != undefined && $("#codAge").val() != null){
        		$('#dirCoo').attr('readonly','readonly');
        		$('#dirCoo').val($("#codAge").val()+" - "+$('#nombreAgenteTmp'+$("#codAge").val()).val());        		
        		$('#ubiCoo').val($('#ubigeoAgenteTmp'+$("#codAge").val()).val());
        		
        		$('#nomAge').val($('#codAge option:selected').text())
        	}else{ 
        		$('#dirCoo').val(''); 
        		$('#dirCoo').attr('readonly','readonly');
        		
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
					<span class="value">${SACoordinacionSelect.titTlfDom!=""?SACoordinacionSelect.titTlfDom:"" }</span>
				</div>
				<div>
           			<span class="label">Tel&eacute;fono Laboral</span>
					<span class="value">${SACoordinacionSelect.titTlfLab!=0?SACoordinacionSelect.titTlfLab:"" }</span>
				</div>
				<div>
           			<span class="label">Tel&eacute;fono Opcionales</span>
					<span class="value">
					${SACoordinacionSelect.titTlfOpc!=""?SACoordinacionSelect.titTlfOpc:"" }  
					${SACoordinacionSelect.titTlfMov!=""?SACoordinacionSelect.titTlfMov:"" }  
					${SACoordinacionSelect.md1NumTlf!=""?SACoordinacionSelect.md1NumTlf:"" }  
					${SACoordinacionSelect.md2NumTlf!=""?SACoordinacionSelect.md2NumTlf:"" }
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
            <div class="content-form-generic">
            	<c:if test="${(SACoordinacionSelect.indSit=='2') || (SACoordinacionSelect.indEst=='4')}">
            	<h2>Entregado</h2>
            	No es posible registrar m&aacute;s coordinaciones ni gestiones telef&oacute;nicas.
            	</c:if>
            	
            	<c:if test="${success}">
            	<h3><span class="icon-checkmark"></span> La coordinacion fue guardada correctamente.</h3>
            	<div id="divTempNuevaSelect" style="padding: 12px;">
            		<a href="./" class="boton-default"><span class="icon-search"></span> Seleccionar Nuevo Destinatario</a>
            	</div>
            	<div id="divTempNuevaCoord" style="padding: 12px;">
            		<a href="./coord_re_form.htm" class="boton-default">Registrar Nueva Coordinaci&oacute;n</a>
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
					${empty RPRegistroCoordEdit?"<span class='icon-file-empty'></span> Registro de ":"<span class='icon-pencil'></span> Editar " }
            		Coordinaci&oacute;n
           		</h2>
            	<form action="${empty RPRegistroCoordEdit?"coord_save.htm":"coord_update.htm" }" class="form-generic" method="post" onsubmit="return validaFormCoord();">
	            	<div style="text-align: center;">
			    		<span id="formAlert">
			    			<c:if test="${success}"><span class="icon-checkmark"></span> La coordinacion fue guardada correctamente.</c:if>
			    			<c:if test="${error}"><span class="icon-notification"></span> Error los datos ingresado son incorrecto, vuelva a intentar.</c:if>
			    		</span>
			    	</div>
            		<div>
            			<c:if test="${not empty RPRegistroCoordEdit }">
          					<fmt:formatDate var="tempFecCoo" value="${RPRegistroCoordEdit.fecCoo}" pattern="dd/MM/yyyy"/>
						</c:if>
            			<label for="fecCoo">Fecha Coordinada</label>
            			<input id="fecCoo" type="text" name="fecCoo" value="${tempFecCoo }" title="Fecha de entrega" readonly="readonly" placeholder="fecha" size="10">
            			<input id="fecCooPos" type="hidden" name="fecCooPos" value="${SACoordinacionSelect.dirDomPos }">
            			<input id="fecFeriados" type="hidden" name="fecFeriados" value='<c:forEach items="${RPCoordinacionFeriados}" var="feriado" varStatus="rowCounter">${rowCounter.count > 1?",":""}${feriado.fecha}</c:forEach>'>
            		</div>
            		<div>
            			<label for="horCoo">Hora</label>
            			<select id="horCoo" name="horCoo">
            				<option value="9AM-12AM" ${RPRegistroCoordEdit.horCoo=="9AM-12AM"?"selected='selected'":""}>9AM-12AM</option>
            				<option value="10AM-1PM" ${RPRegistroCoordEdit.horCoo=="10AM-1PM"?"selected='selected'":""}>10AM-1PM</option>
            				<option value="11AM-2AM" ${RPRegistroCoordEdit.horCoo=="11AM-2AM"?"selected='selected'":""}>11AM-2AM</option>
            				<option value="12PM-3PM" ${RPRegistroCoordEdit.horCoo=="12PM-3PM"?"selected='selected'":""}>12PM-3PM</option>
            				<option value="1PM-4PM" ${RPRegistroCoordEdit.horCoo=="1PM-4PM"?"selected='selected'":""}>1PM-4PM</option>
            				<option value="1PM-4PM" ${RPRegistroCoordEdit.horCoo=="1PM-4PM"?"selected='selected'":""}>1PM-4PM</option>
            				<option value="2PM-5PM" ${RPRegistroCoordEdit.horCoo=="2PM-5PM"?"selected='selected'":""}>2PM-5PM</option>
            				<option value="3PM-6PM" ${RPRegistroCoordEdit.horCoo=="3PM-6PM"?"selected='selected'":""}>3PM-6PM</option>
            			</select>
            			<!--  input type="text" name="horCoo" title="Hora de entrega" placeholder="hora" size="2" placeholder="HH:MM" -->
            		</div>
            		<div>
            			<label for="indLug">Lugar</label>
            			<select id="indLug" name="indLug" onchange="onChangeLugar();">
            				<option value="1" ${RPRegistroCoordEdit.indLug=="1"?"selected='selected'":""}>Dir. Destinatario</option>
            				<option value="2" ${RPRegistroCoordEdit.indLug=="2"?"selected='selected'":""}>Dir. Agencia</option>
            				<!--  option value="3" ${RPRegistroCoordEdit.indLug=="3"?"selected='selected'":""}>Of. Dataimagenes</option -->
            			</select>
            		</div>
            		<div id="divDirDestinatario" style="${not empty RPRegistroCoordEdit ? (RPRegistroCoordEdit.indLug == 1?"display: block;":"display: none;"):"" }">
            			<label for="indDir">Tipo Dir.</label>
            			<select id="indDir" name="indDir" onchange="onChangeDirDest();">
            				<optgroup label="Registrados">
            				<option value="1" ${RPRegistroCoordEdit.indDir=="1"?"selected='selected'":""}>Direcci&oacute;n Domiciliaria</option>
            				<option value="2" ${RPRegistroCoordEdit.indDir=="2"?"selected='selected'":""}>Direcci&oacute;n Laboral</option>
            				<option value="3" ${RPRegistroCoordEdit.indDir=="3"?"selected='selected'":""}>Direcci&oacute;n Opcional</option>
            				</optgroup>
            				<optgroup label="Nuevo">
            				<option value="9" ${RPRegistroCoordEdit.indDir=="9"?"selected='selected'":""}>Direcci&oacute;n Coordinada</option>
            				</optgroup>
            			</select>
            			<input id="indDirTmp1" name="indDirTmp1" value="${SACoordinacionSelect.dirDomDes }" type="hidden">
            			<input id="indDirTmp2" name="indDirTmp2" value="${SACoordinacionSelect.dirLabDes }" type="hidden">
            			<input id="indDirTmp3" name="indDirTmp3" value="${SACoordinacionSelect.dirOpcDes }" type="hidden">
            			
            			<input id="ubiDirTmp1" name="ubiDirTmp1" value="${SACoordinacionSelect.dirDomUbi }" type="hidden">
            			<input id="ubiDirTmp2" name="ubiDirTmp2" value="${SACoordinacionSelect.dirLabUbi }" type="hidden">
            			<input id="ubiDirTmp3" name="ubiDirTmp3" value="${SACoordinacionSelect.dirOpcUbi }" type="hidden">
            		</div>
            		<div id="divDirAgencia" style="${RPRegistroCoordEdit.indLug == 2?"display: block;":"display: none;" }     margin-left: 189px;
    						width: 217px;">
            			<!-- <label for="codAge">Dir. Agencias</label> -->
            			<select id="codAge" class="custom-select" data-live-search="true" name="codAge" onchange="onChangeDirAgente();">
            				<option value="" selected="selected" disabled="disabled"/>::::SELECCIONAR AGENCIA::::  <strong>V</strong></option>
            				<c:forEach items="${RPCoordinacionClienteAgencias}" var="agencia" varStatus="rowCounter">
		        				<c:if test="${rowCounter.count == 1}">
		        					<c:set var="tempValDesAge" value="${agencia.nombre}"></c:set>
		        				</c:if>
		        				<option value='<c:out value="${agencia.codAgencia}"/>' ${RPRegistroCoordEdit.codAge==agencia.codAgencia?"selected='selected'":"" }><c:out value="${agencia.codAgencia}"/> - <c:out value="${agencia.nombre}"/></option>
							</c:forEach>
            			</select>
            			<input type="hidden" id="nomAge" name="nomAge" value="${empty RPRegistroCoordEdit?tempValDesAge:RPRegistroCoordEdit.nomAge }">
            			
            			<c:forEach items="${RPCoordinacionClienteAgencias}" var="agencia" varStatus="rowCounter">
            			<input id='desDirAgenteTmp<c:out value="${agencia.codAgencia }"/>' name="desDirAgenteTmp" value="${agencia.direccion }" type="hidden"/>
            			<input id='ubigeoAgenteTmp<c:out value="${agencia.codAgencia }"/>' name="ubigeoAgenteTmp" value="${agencia.ubigeo }" type="hidden" />
            			<input id='nombreAgenteTmp<c:out value="${agencia.codAgencia }"/>' name="nombreAgenteTmp" value="${agencia.nombre }" type="hidden" />
            			<input id='codigoAgenteTmp<c:out value="${agencia.codAgencia }"/>' name="codigoAgenteTmp" value="${agencia.codAgencia }" type="hidden" />
            			</c:forEach>
            		</div>
            		<div>
            			<label for="dirCoo">Direcci&oacute;n</label>
            			<textarea id="dirCoo" name="dirCoo" rows="2" cols="20" readonly="readonly" title="Direccion para entrega" placeholder="direccion">${not empty RPRegistroCoordEdit ?RPRegistroCoordEdit.dirCoo:SACoordinacionSelect.dirDomDes}</textarea>
            			<!-- input id="dirCoo" type="text" name="dirCoo" value="${SACoordinacionSelect.dirDomDes }" title="Direccion para entrega" placeholder="direccion" size="32" -->
            		</div>
            		<div>
            			<label for="refCoo">Referencia</label>
            			<input id="refCoo" type="text" name="refCoo" value="${RPRegistroCoordEdit.refCoo }" title="Referencia de direccion" placeholder="referencia" size="30">
            		</div>
            		<div>
            			<label for="obsCoo">Observaciones:</label>
            			<textarea id="obsCoo" name="obsCoo" rows="2" cols="20">${RPRegistroCoordEdit.obsCoo }</textarea>
            		</div>
            		<div>
            			<c:if test="${USUARIO_TIPO == 'DI'}">
            			<label for="indPri">Prioridad</label>
            			<select id="indPri" name="indPri">
            				<option value="1" ${RPRegistroCoordEdit.indPri=="1"?"selected='selected'":""}>Normal</option>
            				<option value="2" ${RPRegistroCoordEdit.indPri=="2"?"selected='selected'":""}>Express</option>
            			</select>
            			</c:if>
            			<c:if test="${USUARIO_TIPO == 'CL'}">
            			<input type="hidden" name="indPri" value="1">
            			</c:if>
            		</div>
            		<div>
            			<label for="tlfCoo">Cel./Tel&eacute;fono</label>
            			<input id="tlfCoo" type="text" name="tlfCoo" value="${empty telfRef?RPRegistroCoordEdit.tlfCoo:telfRef }" title="Telefono de contacto" placeholder="telefono" size="10">
            		</div>
            		<div>
            			<label for="indLla">Llamada</label>
            			<select id="indLla" name="indLla">
            				<option value="2" ${RPRegistroCoordEdit.indLla=="2"?"selected='selected'":""}>Saliente</option>
            				<option value="1" ${RPRegistroCoordEdit.indLla=="1"?"selected='selected'":""}>Entrante</option>
            			</select>
            		</div>
            		<div>
            			<label for="smt"> </label>
						<input type="submit" class="boton-default" value="Registrar " id="smt"/>
						<input type="hidden" name="idCoord" value="${RPRegistroCoordEdit.id}">
						<input type="hidden" name="indUsu" value='${USUARIO_TIPO=="CL"?"2":"1"}'>
						<input type="hidden" id="ubiCoo" name="ubiCoo" value="${empty RPRegistroCoordEdit?SACoordinacionSelect.dirDomUbi:RPRegistroCoordEdit.ubiCoo }">
						<input type="hidden" name="nroTlf" value="${SACoordinacionSelect.titTlfDom }">
						<input type="hidden" name="codBar" value="${SACoordinacionSelect.codBar }">
						<input type="hidden" name="idDistribucion" value="${SACoordinacionSelect.id }">
					</div>
            	</form>
            	
            	</c:if>
            </div>
            <div class="content-form-generic">
            <c:if test="${(SACoordinacionSelect.indSit!='2') && (SACoordinacionSelect.indEst!='4')}">

            	<h2 style="padding-bottom: 12px;">Coordinaciones Registradas</h2>
            	<table>
	        		<thead>
	        		<tr>
	        			<td>Fecha</td>
	        			<td>Hora</td>
	        			<td>Direcci&oacute;n</td>
	        			<td>Observaciones</td>
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
	        				<td class="td_opt"><c:out value="${coord.obsCoo}"/></td>
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
	        				<td><c:out value="${coord.obsCoo}"/></td>
	        				<td>
	        				<c:if test="${can_delete_coord == 1}">
		        				<a href='coord_edit.htm?idCoord=<c:out value="${coord.id}"/>' title="Editar"><span class="icon-pencil"></span></a>
		        				<a href='coord_remove.htm?codBar=${SACoordinacionSelect.codBar }&idCoord=<c:out value="${coord.id}"/>' title="Borrar" onclick="return confirm('¿Esta seguro de Borrar la Coordinacion?');"><span class="icon-bin2"></span></a>
	        				</c:if>
	        				</td>
	        			</tr>
					</c:forEach>--%>
	        		</tbody>
	        	</table>
	        	<p class="font-s">OJO: Para visualizar a mayor detalles las coordinaciones, elija la opcion "Listar Coordinaciones" situada en la parte superior.</p>
	        	
        	</c:if>
            </div>
        </div>
        <%@include file="../include-footer.jsp" %>
       
     	<script src="../static/search_select/jquery-customselect.js"></script>
        <script type="text/javascript">        	
        
        
      	
        $("#codAge").customselect();    
        
        	      
        $( '#fecCoo' ).datepicker({
        	 minDate: (($("#fecCooPos").val().substring(0,1)=="L" || $("#fecCooPos").val().substring(0,2)=="C0")?"+1":"+2"), //Pone la fecha mínima como el día siguiente
        	 maxDate: 60, //Pone la fecha máxima como 10 días a partir de hoy
        	 beforeShowDay: noFinesDeSemanaNiFestivos //nuestra función que identifica fines de semana y festivos
        	});

        var diasFestivos = [];
        	diasFestivos = $("#fecFeriados").val().split(',');
                
        function noFinesDeSemanaNiFestivos(date) {
        	if(date.getDay() == 0) {
	      		var noWeekend = $.datepicker.noWeekends(date, diasFestivos);
	      		noWeekend[2] = 'No se permite el ingreso del día Domingo como fecha de entrega';
	      		return noWeekend[0] ? festivo(date) : noWeekend;
      		}else return festivo(date);
       	}
        
        function festivo(date) {
        	
        	//Se toma cada dato de la fecha, ya que el mes viene de 0 a 11, se le suma 1
        	var m = date.getMonth() + 1, d = date.getDate(), y = date.getFullYear();
        	//Si el mes es menor a 10 se concatena un 0 antes para que de el formato
        	if(m < 10) m = '0' + m; 
        	//Si el día es menor a 10 se concatena un 0 antes para que de el formato
        	if(d < 10) d = '0' + d; 
        	for (i = 0; i < diasFestivos.length; i++) {
        		if($.inArray(d + '/' + m + '/' + y, diasFestivos) != -1) {
        			return [false, "festivos", 'No se permite el ingreso de días feriados como fecha de entrega'];
        		}
        	}
        	return [true, ''];
       	}
        </script>
    </body>
</html>