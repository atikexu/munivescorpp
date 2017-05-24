<%@include file="../include-param.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="../include-head.jsp" %>
        <%@include file="../include-head-jquery-ui.jsp" %>
        <link rel="stylesheet" href="../static/css/default-forms.css">
        <link rel="stylesheet" href="../static/css/default-grids.css">
        <script type="text/javascript">
      
        </script>
    </head>
    <body onload="$('#codBar').select();">
    	<%@include file="../include-menu.jsp" %>
        <div class="content">
<%--         	<c:if test="${nuevo}">      --%>
       		 <div class="content-frame-generic">
       		 <h2>Buscar Documento</h2>
       			<form action="generar_rendicion.htm" class="form-generic" method="post">

            		<div>
						<label for="correo">Buscar Documento:</label>
            			<input name="filter" onkeyup="filter2(this, 'table-Agencia')" type="text">
            			<input readonly="readonly" hidden="" type="text" id="d_idCliente" value="${codCliente}" name="d_idCliente"/>
            			<input readonly="readonly" hidden="" type="text" id="d_fecha" value="${fecha}" name="d_fecha"/>
            			
					</div>

            	</form> 
	
       		 </div>

       		 <div class="content-frame-generic">
	       		 <div id="contTablaAgencia" style="height:200px;weight:100px;overflow:auto;">
							${listaAgencias}
	       			</div>
       		 </div>
       		
<%--        		 </c:if> --%>
<%--      		<c:if test="${generar}"> --%>
       		 <div class="content-frame-generic1">
       		 	<h2>Detalle de Documento</h2>
       		 		<form action="generar_ruta.htm" class="form-generic" method="post">
  				
					<div class="content-frame-generic3">
						<label for="codigo">Código:</label>
            			<input id="campo1" name="campo1" readonly="readonly" type="text" value="${codigo}" title="Codigo" placeholder="" size="15" maxlength="25">
					</div>
					<div class="content-frame-generic3">
						<label for="clasel">Nro. Archivador:</label>
            			<input id="campo7" name="campo7" readonly="readonly" type="text" value="${ClaseDochumelntal}" title="clase" placeholder="" size="15" maxlength="50">
					</div>
					
					<div class="content-frame-generic3">
						<label for="clasel">Nro. Tomo:</label>
            			<input id="campo8" name="campo8" readonly="readonly" type="text" value="${ClaseDochumelntal}" title="clase" placeholder="" size="15" maxlength="50">
					</div>
					
					<div class="content-frame-generic3">
						<label for="descripcion">Detalle:</label>
            			<input id="campo2" name="campo2" readonly="readonly" type="text" value="${descripcion}" title="Descripcion" placeholder="" size="25" maxlength="50">
					</div>
					
					<div class="content-frame-generic3">
						<label for="detalle">Oficina:</label>
            			<input id="campo3" name="campo3" readonly="readonly" type="text" value="${detalle}" title="Detalle" placeholder="" size="25" maxlength="50">
					</div>
					
					<div class="content-frame-generic3">
						<label for="clase">Clase Documental:</label>
            			<textarea id="campo4" name="campo4"  placeholder="">${ClaseDohcumental}</textarea>
					</div>
					
					<div class="content-frame-generic3">
						<label for="clase">Serie Descripción:</label>
						<textarea id="campo5" name="campo5"  placeholder="">${ClaseDohcumental}</textarea>
<%--             			<input id="campo5" name="campo5" readonly="readonly" type="text" value="${ClaseDohcumental}" title="clase" placeholder="" size="25" maxlength="50"> --%>
					</div>
					
					<div class="content-frame-generic3">
						<label for="clase">Empresa:</label>
            			<input id="campo6" name="campo6" readonly="readonly" type="text" value="${ClaseDochumental}" title="clase" placeholder="" size="25" maxlength="50">
					</div>

					<div class="content-frame-generic3">
						<label for="clasel">Referencia:</label>
            			<input id="campo9" name="campo9" readonly="readonly" type="text" value="${ClaseDochumelntal}" title="clase" placeholder="" size="25" maxlength="50">
					</div>
					
					<div class="content-frame-generic3">
						<label for="clasel">Original:</label>
            			<input id="campo10" name="campo10" readonly="readonly" type="text" value="${ClaseDochumelntal}" title="clase" placeholder="" size="25" maxlength="50">
					</div>
					
					<div class="content-frame-generic3">
						<label for="clasel">Copia:</label>
            			<input id="campo11" name="campo11" readonly="readonly" type="text" value="${ClaseDochumelntal}" title="clase" placeholder="" size="25" maxlength="50">
					</div>
					
					<div class="content-frame-generic3">
						<label for="clase">Observación:</label>
						<textarea id="campo12" name="campo12"  placeholder="">${ClaseDohcumental}</textarea>
<%--             			<input id="campo5" name="campo5" readonly="readonly" type="text" value="${ClaseDohcumental}" title="clase" placeholder="" size="25" maxlength="50"> --%>
					</div>
            	</form>
       		 		

            		<div id="divLoading" style="text-align: center; display: none;">
					<img alt="Cargando..." src="../static/img/loader.gif" style="border: 0;">
			</div>
       		 </div>
       		 
       		 <div id="contTabla">
       		
       		</div>
       		
       		 
       		<!--  
           	<div>  
            	<button id="btnCerrarRendicion" class="boton-default" >Cerrar Rendicion</button>  
            </div>
            -->
<%--  			</c:if> --%>

 			<div id="dialog" style="display: none">
			</div>
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
	        $( "#fecha" ).datepicker({
	        	minDate: -360, //60 DIAS ATRAS
	            defaultDate: "0",
	            changeMonth: true,
	            numberOfMonths: 1
	          });
       		 
	        function filter2 (phrase, _id){
       			var words = phrase.value.toLowerCase().split(" ");
       			var table = document.getElementById(_id);
       			var ele;
       			for (var r = 1; r < table.rows.length; r++){
       				ele = table.rows[r].innerHTML.replace(/<[^>]+>/g,"");
       			        var displayStyle = 'none';
       			        for (var i = 0; i < words.length; i++) {
       				    if (ele.toLowerCase().indexOf(words[i])>=0)
       					displayStyle = '';
       				    else {
       					displayStyle = 'none';
       					break;
       				    }
       			        }
       				table.rows[r].style.display = displayStyle;
       			}
       		} 
			
			$("#btnVerAgencias").click(function() {
				console.log("entro:agencias");
                var idCliente      =$("#codCliente").val();
                        $.ajax({
                           url: "listar_agencias.htm", 
                           type: "POST",  
                           data : { 
                                    "idCliente" : idCliente
                                   },
                           }).done(function(data){
                        	   $("#contTablaAgencia").html(data.tablita);	
                        	   console.log(data.tablita);
                                            
                        });
            }); 
			
			function verAgencias() {
				console.log("entro:agencias "+ $("#codCliente").val());
                var idCliente      =$("#codCliente").val();
                        $.ajax({
                           url: "listar_agencias.htm", 
                           type: "POST",  
                           data : { 
                                    "idCliente" : idCliente
                                   },
                           }).done(function(data){
                        	   $("#contTablaAgencia").html(data.tablita);	
                        	   console.log(data.tablita);
                                            
                        });
			}
			
			$(".boton").click(function(){
   	            var valores="";
   	 			var i=1;
   	            $(this).parents("tr").find("td").each(function(){
   	                valores=$(this).html()+"\n";
   	                $("#campo"+i).val(valores);	
   	                i++;
   	            });
   	        });
			
			$("#btnCrearRuta").click(function() {
                var idConsulta      =$("#inputId").val();
                var asunto          =$("#inputAsunto").val();
                var descripcion     =$("#inputDescripcion").val();
                        $.ajax({
                           url: "nueva_ruta.htm", 
                           type: "POST",  
                           data : { 
                                    "idConsulta" : idConsulta,
                                    "asunto" : asunto,
                                    "descripcion" : descripcion,
                                    
                                   },
                           }).done(function(data){
                        	   console.log("datitos");
                                            
                        });
            });    

			function agregarCodBar(){
				var idRendicion = $("#idRendicion").val();
	        	var codCliente = $("#codCliente").val();
	        	var fecha = $("#fecha").val();
	        	var codBar = $("#codigoBarra").val();
	        	var motivo = $("#codMotivo").val();
	        	var nroRendicion = $("#d_nroRendicion").val();
	        	console.log("datos: "+idRendicion+" "+codCliente+" "+fecha+" "+motivo);
	 			$.ajax({
	 				type: "POST",
	 				url: "listar_rendicion_cod.htm", 
	 				data: { idRendicion: idRendicion,
	 					codCliente: codCliente,
	 					fecha: fecha,
	 					codBar: codBar,
	 					motivo: motivo,
	 					nroRendicion: nroRendicion
	 					} 

	 				}).done(function(data){
	 					console.log("ENTRO"+data.tablita);
	 					$("#contTabla").html(data.tablita);	
	 					$("#mensaje").val(data.mensaje);
	 					console.log("mensaje: "+data.mensaje);
	 					$("#codigoBarra").val("");
	                   });			
			}
			
			$("#btnCerrarRendicion").click(function() {
	        	var idRendicion = $("#idRendicion").val();
	        	console.log("datos: "+idRendicion);
	 			$.ajax({
	 				type: "POST",
	 				url: "cerrar_rendicion_cod.htm", 
	 				data: { idRendicion: idRendicion
	 					} 

	 				}).done(function(data){
	 					console.log("ENTRO"+data.tablita);
	 					$("#contTabla").html(data.tablita);	
	 					$("#mensaje").val(data.mensaje);
	 					console.log("mensaje: "+data.mensaje);
	 					div = document.getElementById("divGenerar");
	 	                div.style.display = "none";
	 	               $("#divImprimir").show();
	                   });			
	 		 });
			
			$("#btnImprimir").click(function() {
				$("#divLoading").show();
	        	var idRendicion = $("#idRendicion").val();
	        	console.log("datos: "+idRendicion);
	 			$.ajax({
	 				type: "POST",
	 				url: "pdf_rendicion_nuevo.htm", 
	 				data: { idRendicion: idRendicion
	 					} 

	 				}).done(function(data){
	 					setTimeout("funcionVer("+data.cadena+")",5500);
	 					console.log("mensaje: "+data.mensaje);

	                   });			
	 		 });
			
			function btn_eliminar(id_ren_det){
				var idRendicion = $("#idRendicion").val();
	 			$.ajax({
	 				type: "POST",
	 				url: "eliminar_rendicion_cod.htm", 
	 				data: { id_ren_det: id_ren_det,
	 					idRendicion:idRendicion
	 					} 
	 				}).done(function(data){
	 					$("#contTabla").html(data.tablita);	
	                   })	
			}
			
			function funcionVer(cadena) {
				var name = "pdf/rendicion/";
				var fileName = "rendicion"+cadena+".pdf";
					$("#dialog").dialog({
		                modal: true,
		                title: fileName,
		                width: 820,
		                height: 800,
		                closeButton:true,
		                open: function () {
		                    var object = "<object data=\"{FileName}\" type=\"application/pdf\" width=\"800px\" height=\"700px\">";
		                    object += "If you are unable to view file, you can download from <a href = \"{FileName}\">here</a>";
		                    object += " or download <a target = \"_blank\" href = \"http://get.adobe.com/reader/\">Adobe PDF Reader</a> to view the file.";
		                    object += "</object>";
		                    object = object.replace(/{FileName}/g, "/" + name+fileName);
		                    $("#dialog").html(object);
		                   console.log("ruta: "+object);
		                   $("#divLoading").hide();
		                }
		            });
            }

       		 </script>
   	       </div> 
        <%@include file="../include-footer.jsp" %>
    </body>
</html>