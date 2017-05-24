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
					<span class="value">${SAConsultaDetalle.titTlfDom!=""?SAConsultaDetalle.titTlfDom:"" }</span>
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
            <div class="content-frame-generic" id="divToHide">
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
           			<a class="boton-default" href="consulta_coord_to_carga.htm">Coordinaciones</a>
           			<c:if test="${visitas}">
	           			<c:if test="${canEdit!='' }">
	           			<a class="boton-default" id="btnCargaGestion">Carga gesti&oacute;n</a>
	           			</c:if>
           			</c:if>
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
	           		 <div class="content" id="divSubidaIMG">
		            	<div class="content-form-generic">
			           		<form class="form-generic" action="subir_img.htm" method="POST" enctype="multipart/form-data">
						    	<div>
						    	<input type="text" id="codBarImg" name="codBarImg" value="${SAConsultaDetalle.codBar }"  hidden=""  readonly="readonly">
						    	</div>
						    	<div>
						    		<span><c:if test="${vacio != null}">El archivo que intenta subir es inconsistente.</c:if></span>
						    		<span><c:if test="${error != null}">Error interno al subir y/o cargar el archivo.</c:if></span>
						    	</div>
								<div style="text-align: center;">
								<!-- <img src="../static/img/icon_zip.gif"><br/> -->
									<input type="file" name="file" id="file" style="width: 320px;" title="Seleccione archivo a subir" placeholder="seleccione archivo"/>
								</div> 	
								<div>
									<label for="smt"> </label>
									<input type="submit" class="boton-default" value="Subir Imagen" id="smt"/>
								</div>
								<div id="divLoading" style="text-align: center; display: none;">
									<img alt="Cargando..." src="../static/img/loader.gif" style="border: 0;">
								</div>								
							</form>
						</div>
					</div>
            </div>
				
            <div class="content-frame-generic" id="divEdit" hidden=false>
            	<h2> &nbsp; </h2>
            	
            	<form id="form_updategestion" action="save_gestion.htm" class="form-generic" method="post">
            	
            	<div>
					<label for="fecEntrega">Fecha Entrega:</label>
					<input type="text" required="required" name="fecEntrega" id="fecEntrega" size="10" maxlength="10" title="Fecha Entrega:" placeholder="Dia/Mes/Año"/>
				</div>
          	 	<div>
            			<label for="hora">Hora:</label>
            			<input id="hora" required="required" type="time" name="hora" value="${hora}" title="Ingrese la hora" placeholder="ingrese la hora de entrega" size="26" maxlength="30">
           		</div>
           		<div>
            			<label class="label" for="tipoBuscar">Situaci&oacute;n:</label>
            			<select required="required" id="tipoSituacion" name="tipoSituacion" onchange="selectSituacion()">
            				<option value="">::SELECCIONAR::</option>
	            			<c:forEach items="${TipoSituacion}" var="estado" >
	            				<option value="${estado.id_estado}">${estado.des_estado}</option>
	            			</c:forEach>        				
            			</select>
           		</div>
            		<div >
            			<label  class="label" for="tipoBuscar">Motivo:</label>
            			<select  required="required" id="tipoMotivo" name="tipoMotivo">
            				<option value="">::SELECCIONAR::</option>
            			</select>
            		</div>
            		<div id="divEstadoChoice">
            			<label class="label" for="tipoBuscar">Estado:</label>
            			<select required="required" id="tipoEstado" name="tipoEstado">
            				<option value="">::SELECCIONAR::</option>
            			</select>
            		</div>
            		<div >
            			<label class="label" for="tipoDomicilio">Tipo Domicilio:</label>
            			<select required="required" id="tipoDomicilio" name="tipoDomicilio">
            				<option value="">::SELECCIONAR::</option>
            				<option value="Domicilio">DOMICILIO</option>
            				<option value="Coordinada">COORDINADA</option>
            				<option value="Laboral">LABORAL</option>
            				<option value="Opcional">OPCIONAL</option>
            			</select>
            		</div>
           		<!--  ${estado.cod_estado == SAConsultaDetalle.indSit ? 'selected="selected"' : ''}  -->
					<div id="divVinculo" hidden="">
					<label class="label" for="tipoVinculo">V&iacute;nculo:</label>
            			<select required="required" id="tipoVinculo" name="tipoVinculo" onchange="selectVinculo()">
            				<option value="">::SELECCIONAR::</option>
	            			<c:forEach items="${TipoVinculo}" var="estado" >
	            				<option value="${estado.id_estado}">${estado.desc_estado_per}</option>
	            			</c:forEach>        				
            			</select>            			
            		</div>
					<div id="divRecibidoPor" hidden="">
            			<label for="reciPor">Recibido Por:</label>
            			<input minlength="3" required="required" id="reciPor" type="text" name="reciPor" value="" title="Ingrese nombre de Destinatario o Titular" placeholder="destinatario o titular" size="26" maxlength="30">
            		</div>
            		<div id="divComentario">
            			<label for="reciPor">Comentario:</label>
            			<textarea id="coment" name="coment" rows="4" cols="50" maxlength="500" placeholder="Escribir un comentario...." ></textarea>
            		</div>             		
            		<div>
            			<label for="smt"> </label>
						<input type="submit" class="boton-default" style="min-width: 130px;" value=" Guardar " id="smt"/>
					</div>
           		</form>
           	</div>
            	
            
                        <div class="content-result">
            	<c:if test="${(USUARIO_TIPO=='DI') || ( (USUARIO_TIPO=='CL') && (CLIENTE_CONFIG_CONSRVPAQ=='1') )}">
            	<h2>Sub-Paquetes</h2>
	        	<table>
	        		<thead>
	        		<tr>
	        			<td>Nro. Pqte/Tarj.</td>
	        			<td class="td_opt">Nro. Ide.</td>
	        			<td class="td_opt">Otros Cods.</td>
	        			<td>Nombre Dest./Agte.</td>
	        			<td>Otros Datos</td>
	        			<td>Descripci&oacute;n</td>
	        		</tr>
	        		</thead>
	        		<tbody>
	        		<c:forEach items="${RPConsultaDetallePaquetes}" var="paquete" varStatus="rowCounter">
	        			<tr class='<c:if test="${rowCounter.count % 2 == 0}">on</c:if>'>
	        				<td style="${(idPaquete==paquete.id)?'font-weight: bold;':'' }"><c:out value="${paquete.nroDoc }"/></td>
	        				<td class="td_opt"><c:out value="${paquete.nroIde }"/></td>
	        				<td class="td_opt"><c:out value="${paquete.codOtr}"/></td>
	        				<td><c:out value="${paquete.nomDes }"/></td>
	        				<td><c:out value="${paquete.nomOtr } ${paquete.tlfRef }"/></td>
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
	        			<td>Tramitaci&oacute;n</td>
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
	        var validate;
	        
	        $(document).ready(function (){
	        	$('#form_updategestion').submit(function (e) {
	        		if(validate){
	        			 var reciPor = $("#reciPor").val();
	        			if(!isNaN(reciPor)){
	        				alert("El campo 'Recibido Por' no puede ser Numerico");
	        				e.preventDefault();
	        			}
	        		}
	        	});
	        });
	        
	        
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
	        $("#detalleVisitas").dialog({ autoOpen: false, modal: true, width: "80%"});
	        function verVisitas(cod_bar){
	        	$.ajax({type: "POST", url: "visitas.htm", data: { codBar: cod_bar}, cache: false}).done(function( html ) { $("#detalleVisitas").html(html); $("#detalleVisitas").dialog( "open" ); });
	        }
	        
	        function selectSituacion(){
	        	var idSituacion = $("#tipoSituacion").val();
	 			 //alert(idSituacion);   
	 			$.ajax({
	 				type: "POST", 
	 				url: "getMotivos.htm", 
	 				data: { idSituacion: idSituacion}, 
	 				cache: false})
	 				.done(function( data ) {
	 					$("#tipoMotivo").html(data.combo1);
	 		 			//$("#tipoEstado").html(data.combo2);
	 		 			if((data.show)=="1"){
	 		 			   $("#divVinculo").show();
	 		 			   $("#divRecibidoPor").show();
	 		 			   $("#tipoVinculo").attr('required');
	 		 			   $("#reciPor").attr('required');
	 		 			   $("#divEstadoChoice").hide();
	 		 			   $("#tipoEstado").removeAttr('required');
	 		 			   validate = true;
	 		 			}else{
	 		 			   $("#divEstadoChoice").show();
	 		 			   $("#tipoEstado").html(data.combo2);
	 		 			   $("#tipoEstado").attr('required');
	 		 			 
	 		 			   $("#divVinculo").hide();
	 		 			   $("#divRecibidoPor").hide();
						   $("#tipoVinculo").val("");
						   $("#tipoVinculo").removeAttr('required');
						   $("#reciPor").val("");
						   $("#reciPor").removeAttr('required');
						   console.log("he");
						   validate = false;
	 		 			}
	 				});
	 			
	 			 
	 		 }
	        
       function selectVinculo(){
       	var id_vinculo = $("#tipoVinculo").val();
       	$.ajax({
				type: "POST", 
				url: "getActionVinculo.htm", 
				data: { id_vinculo: id_vinculo}, 
				cache: false})
				.done(function( data ) {
       		        	
	        	console.log("id_vinculo-"+id_vinculo);
	        	console.log("data.isTitular-"+data.isTitular);
	 			if(data.isTitular=="1"){//TITULAR
	 				$("#reciPor").val(data.titular);
	 				$('#reciPor').attr('readonly', true);
	 			}else if(data.isAgencia=="1"){	 				
	 				$("#reciPor").val(data.agenciaCoord);
	 				$('#reciPor').attr('readonly', true);
	 			}else if(data.isAgencia=="2"){
	 				$("#reciPor").val("");
	 				$("#tipoVinculo").val("");
	 				alert("El cargo no cuenta con una coordinacion de tipo AGENCIA");
	 			}else {
	 				$("#reciPor").val("");
					$("#reciPor").removeAttr('readonly');
	 			}
 			
			})
		}
	
	        console.log("YEAH IM HERE");
	        $("#btnCargaGestion").click(function(){
	        	console.log("CTM");
	            $("#divToHide").hide();
	            $("#divEdit").show();
	        });	     	
	        $( "#fecEntrega" ).datepicker({
	        	minDate: -60, //60 DIAS ATRAS
	            defaultDate: "0",
	            changeMonth: true,
	            numberOfMonths: 1
	          });
	        
	        </script>
        </div>
        <%@include file="../include-footer.jsp" %>
    </body>
</html>