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
            <div class="content-form-generic">
            	<h2>Buscar por: </h2>
            	 <form action="generar_cargos_codigo_pdf.htm" class="form-generic" method="post"> 
            		<div>
						<input readonly="readonly" id="men" name="men" size="150" type="text" value="" title="Mensaje" placeholder="" style="border: none; color: red;">
					</div>
            		<div>
            			<label for="ordPro">Códigos de Barra (Cargos):</label>
            			<textarea id="ordPro" name="ordPro" rows="8" cols="16" style="width: 140px; height: 150px;" placeholder="Numero separados por espacio, coma o enter.">${ordPro }</textarea>
            		</div>

            		<div>
            			<label for="smt"> </label>
						
					</div>
            	</form>
            	
            	<div>  
            		<button id="btnGenerarPdf" style="width: 150px;" class="boton-default" >Imprimir</button>  
            	</div>
            	
            	
            	<div id="divLoading" style="text-align: center; display: none;">
					<img alt="Cargando..." src="../static/img/loader.gif" style="border: 0;">
				</div>

				<div id="dialog" style="display: none">
				</div>

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
	        $( "#fecProceso" ).datepicker({
	        	minDate: -60, //60 DIAS ATRAS
	            defaultDate: "0",
	            changeMonth: true,
	            numberOfMonths: 1
	          });
	        
	        function selectCliente(){
	        	var codCliente = $("#codCliente").val();
	 			 //alert(idSituacion);   
	 			$.ajax({
	 				type: "POST", 
	 				url: "cargar_productos.htm", 
	 				data: { codCliente: codCliente} 
	 				}).done(function(data){
	                      $("#productos").html(data.selectProductos);
	          
	                   })			
	 			 
	 		 }      
	        
	        $("#btnGenerarPdf").click(function() {
	        	$("#men").val("");
				$("#divLoading").show();
	        	var codigosBarra = $("#ordPro").val();

	        	//console.log("datos: "+producto);
	 			$.ajax({
	 				type: "POST",
	 				url: "generar_cargos_codigo_pdf.htm", 
	 				data: { codigosBarra: codigosBarra
	 					
	 					} 

	 				}).done(function(data){
	 					console.log("vacio "+data.vacio)
	 					if(data.vacio=="1"){
	 						setTimeout("funcionVer()",5500);
	 						console.log("mensaje:pasoo masivo");
	 					}else{
	 						$("#men").val("Códigos de barra no registrados");
	 						$("#divLoading").hide();
	 					}	

	                   });			
	 		 });
	        
	        function funcionVer() {
	        	console.log("MASIVO");
				var name = "pdf/hojaruta/cargos/";
				var fileName = "masivo_pdf.pdf";
				console.log("MASIVO "+name);
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