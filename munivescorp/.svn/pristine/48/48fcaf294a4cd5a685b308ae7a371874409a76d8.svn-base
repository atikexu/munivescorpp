<%@include file="../include-param.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="../include-head.jsp" %>
        <%@include file="../include-head-jquery-ui.jsp" %>
        <link rel="stylesheet" href="../static/css/default-forms.css">
        <link rel="stylesheet" href="../static/css/default-grids.css">
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
        validaFormBusquedaOp = function(){
        	if($("#ordPro").val().length>=4){
        		$("#smt_op").attr('disabled','disabled');
           		$("#smt_op").val('Espere...');
           		return true;
        	}else{
        		$("#msg_op").html("Ingrese Numeros de OP, ejemp: 132421,132424");
        		return false;
        	}
        }
        validaFormBusquedaFecha = function(){
        	if($("#fromDate").val().length==10 && $("#toDate").val().length==10){
        		$("#smt_fecha").attr('disabled','disabled');
           		$("#smt_fecha").val('Espere...');
           		return true;
        	}else{
        		$("#msg_fecha").html("Ingrese Rango de Fechas correctamente.");
        		return false;
        	}
        }
        validaFormDownLoad = function(){
        	var fields = $("input[type='checkbox'][name='ordPro']").serializeArray(); 
        	if(fields.length>0){
        		$("#smt_gen_files").attr('disabled','disabled');
           		$("#smt_gen_files").val('Espere...');
           		$("#divLoading").show();
           		document.fm_down.submit();
        	}else{
        		alert("Seleccione al menos una OP.");
        		return false;
        	}
        }
        </script>
    </head>
    <body onload="$('#ordPro').select();">
    	<%@include file="../include-menu.jsp" %>
        <div class="content">
           <!-- 
            <div class="content-form-generic">
            	<h2>B&uacute;squeda por OP - r2</h2>
            	<form action="reporte_busqueda_op_r2.htm" class="form-generic" method="post" onsubmit="return validaFormBusquedaOp();">
            		<div style="text-align: center;">
            			<span id="msg_op"></span>
            		</div>
            		<div>
            			<label for="ordPro">Orden Proceso (Nro. Recepci&oacute;n):</label>
            			<textarea id="ordPro" name="ordPro" rows="8" cols="16" style="width: 140px; height: 150px;" placeholder="Numero separados por espacio, coma o enter.">${ordPro }</textarea>
            		</div>
            		<div>
            			<label for="smt"> </label>
						<input type="submit" class="boton-default" style="min-width: 130px;" value="Consultar" id="smt_op"/>
					</div>
            	</form>
            </div>
            -->
            
            <div class="content-form-generic">
            	<h2>B&uacute;squeda por Fechas</h2>
            	<form action="downloadReportGNB.htm" class="form-generic" method="post" onsubmit="return validaFormBusquedaFecha();">
            		<div style="text-align: center;">
            			<span id="msg_fecha"></span>
            		</div>
            		<div style="text-align: center; padding: 12px;">
            			<p class="font-s">Se buscar&aacute; todas las OP comprendidos en el rango de fecha que ingrese.</p>
            		</div>
            		<div>
						<label for="fromDate">Fecha (Desde):</label>
						<input type="text" name="fromDate" value="${fromDate }" id="fromDate" size="10" maxlength="10" title="Desde la fecha:" placeholder="Dia/Mes/Año"/>
					</div>
					<div>
						<label for="toDate">Fecha (Hasta):</label>
						<input type="text" name="toDate" value="${toDate }" id="toDate" size="10" maxlength="10" title="Hasta la fecha:" placeholder="Dia/Mes/Año"/>
					</div>
            		<div>
            			<label for="smt"> </label>
						<input type="submit" class="boton-default" style="min-width: 130px;" value="Exportar" id="smt_fecha"/>
					</div>
            	</form>
            </div>
            <div style="width: 100%; overflow: hidden;"></div>
            <div class="content-form-generic" style="width: 100%;">
            	<div class="content-space-generic">
            	<c:if test="${not empty RPListOdenProceso}">
            	<h3>Selecciones las OP que desea descargar.</h3>
	        	<form action="reporte_download_r2.htm" method="post" name="fm_down">
            	<table>
            		<thead>
            			<tr>
            				<!-- <td><span onclick="checkAll()" class="icon-checkmark"></span></td>-->
            				<td><input type="checkbox" id="selecctall"/></td>
            				<td>Orden de Proceso</td>
            				<td>Cliente</td>
            				<td>Producto</td>
            				<td>Fecha de Proceso</td>
            				<td>Total de Env&iacute;os</td>
            			</tr>
            		</thead>
            		<tbody>
            		<c:forEach items="${RPListOdenProceso}" var="op" varStatus="rowCounter">
            			<tr onclick="if($('#ordPro${rowCounter.count }').attr('checked')){$('#ordPro${rowCounter.count }').attr('checked', false);}else{$('#ordPro${rowCounter.count }').attr('checked', true);}" 
            				class='<c:if test="${rowCounter.count % 2 == 0}">on</c:if>'>
            				<td><input type="checkbox" class="checkOrd" name="ordPro" id="ordPro${rowCounter.count }" ${empty ordPro?"":"checked='checked'" } value="${op.op }"> </td>
            				<td>${op.op }</td>
            				<td>${op.cliente }</td>
            				<td>${op.producto }</td>
            				<td>
            					<fmt:formatDate var="tempFecPro" value="${op.fecha}" pattern="dd/MM/yyyy"/>
	        					<c:out value="${tempFecPro}"/>
            				</td>
            				<td>${op.total }</td>
            			</tr>
           			</c:forEach>
            		</tbody>
            	</table>
            	<div style="padding: 0px; text-align: left;">
            		<table>
            			<tr>
            				<td style="line-height: 180%; padding-left: 6px;">
            					Contabilizar: 
            					<input type="checkbox" id="contaVisita" name="contaVisita" value="true" checked="checked">
            					<label for="contaVisita">visitas, </label>
            					
            					<input type="checkbox" id="contaCoord" name="contaCoord" value="true" checked="checked">
            					<label for="contaCoord">coordinaciones, </label>
            					
            					<input type="checkbox" id="contaCoordTelf" name="contaCoordTelf" value="true" checked="checked">
            					<label for="contaCoordTelf">gestiones telef&oacute;nicas.</label>
            					
            					<b>Opcionales:</b><br>
            					<input type="checkbox" id="opcionales" name="datosOpcionales" value="true">
            					<label for="opcionales">Incluir datos de Opcionales del titular.</label>
            					<br>
            					<input type="checkbox" id="mandatarios" name="datosMandatarios" value="true">
            					<label for="mandatarios">Incluir datos de Mandatarios.</label>
            					<br>
            				</td>
            				<td style="line-height: 180%; padding: 6px; max-width: 200px; font-size: 0.85em;" class="opt">
            					Seleccione la cantidad maxima de Visitas, Coordinaciones y Gestiones telef&oacute;nicas a buscar e incluir en el reporte.
            				</td>
            				<td style="line-height: 180%; padding-left: 6px;">
            					<b>Incluir:</b><br/>
            					<span class="font-s">En orden del m&aacute;s reciente al m&aacute;s antiguo (1-N).</span><br/>
            					<select id="visitas" name="cantVisita">
            						<option value="1">Hasta 1</option>
            						<option value="2">Hasta 2</option>
            						<option value="3" selected="selected">Hasta 3</option>
            						<option value="4">Hasta 4</option>
            						<option value="5">Hasta 5</option>
            						<option value="6">Hasta 6</option>
            						<option value="7">Hasta 7</option>
            					</select>
            					&uacute;ltimas Visitas<br>
            					
            					<select id="coods" name="cantCoord">
            						<option value="1">Hasta 1</option>
            						<option value="2">Hasta 2</option>
            						<option value="3" selected="selected">Hasta 3</option>
            						<option value="4">Hasta 4</option>
            						<option value="5">Hasta 5</option>
            						<option value="6">Hasta 6</option>
            						<option value="7">Hasta 7</option>
            					</select>
            					&uacute;ltimas Coordinaciones<br>
            					
            					<select id="coodsTelf" name="cantCoordTelf">
            						<option value="1">Hasta 1</option>
            						<option value="2">Hasta 2</option>
            						<option value="3" selected="selected">Hasta 3</option>
            						<option value="4">Hasta 4</option>
            						<option value="5">Hasta 5</option>
            						<option value="6">Hasta 6</option>
            						<option value="7">Hasta 7</option>
            					</select>
            					&uacute;ltimas Gestiones Telef&oacute;nicas
            					<br>
            				</td>
            			</tr>
            		</table>
            		
            	</div>
            	<div style="padding: 6px;">
            		<input type="button" class="boton-default" style="min-width: 130px;" value="Generar Reportes" id="smt_gen_files" onclick="validaFormDownLoad();"/>
            	</div>
            	<div id="divLoading" style="text-align: center; display: none;">
					<img alt="Cargando..." src="../static/img/loader.gif" style="border: 0;">
				</div>
            	</form>
            	</c:if>
            	<div style="padding: 6px;">
            		<c:if test="${not empty RPfileXLSName }">
            		<a class="boton-default" href="../dinamic/download/${RPdirName }/${RPfileXLSName }" target="_blank">
            			<img src="../static/img/icon_excel.png" style="border: 0;">
            			Descargar EXCEL.zip
            		<a/>
            		<br/>
            		<span class="font-s">
            			La clave/contraseña para abrir el zip es su <b>"usuario"</b>.
            		</span>
            		</c:if>
            		&nbsp;
            		<c:if test="${not empty RPfileTXTName }">
            		<a class="boton-default" href="../dinamic/download/${RPdirName }/${RPfileTXTName }" target="_blank">
            			<img src="../static/img/icon_text.png" style="border: 0;">
            			Descargar TXT.zip
           			<a/>
            		</c:if>
            		
            	</div>
            	<c:if test="${empty RPListOdenProceso && empty RPListOdenProceso && empty RPfileXLSName && empty RPfileTXTName}">
            		Sin resultados de busqueda.
            	</c:if>
            	</div>
	        </div>	        
        </div>
        <%@include file="../include-footer.jsp" %>
        <script type="text/javascript">    
        
        $('#selecctall').click(function(event) {  //on click 
            if(this.checked) { // check select status
                $('.checkOrd').each(function() { //loop through each checkbox
                    this.checked = true;  //select all checkboxes with class "checkbox1"               
                });
            }else{
                $('.checkOrd').each(function() { //loop through each checkbox
                    this.checked = false; //deselect all checkboxes with class "checkbox1"                       
                });         
            }
        });
        
        $( "#fromDate" ).datepicker({        	
            defaultDate: "0",
            changeMonth: true,
            numberOfMonths: 1,
            onClose: function( selectedDate ) {
              $( "#toDate" ).datepicker( "option", "minDate", selectedDate );
            }
          });
        $( "#toDate" ).datepicker({        	
            defaultDate: "0",
            changeMonth: true,
            numberOfMonths: 1,
            onClose: function( selectedDate ) {
              $( "#fromDate" ).datepicker( "option", "maxDate", selectedDate );
            }
          });
        </script>
    </body>
</html>