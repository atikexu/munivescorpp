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
            	<h2>Buscar por Rendición: </h2>
            	<form action="consulta_buscar_hojaruta.htm" class="form-generic" method="post">
            		
            		<div>
            			<label for="codBar">Codigo de Rendición:</label>
            			<input id="codBar" type="text" name="codBar" value="${codBar }" title="Ingrese codigo de cargo / barras" placeholder="codigo de cargo" size="15" maxlength="16">
            		</div>

            		<div>
            			<label for="smt"> </label>
						<input type="submit" class="boton-default" style="min-width: 130px;" value=" Buscar " id="smt"/>
					</div>
					
            	</form>
            </div>
          </div> 
          
            <div id="contTabla" style="text-align: center; margin-left:100px; margin-right:100px;width: 1700px;overflow:auto;">
        			${listaCodigos}
        	</div>
        	<div style="text-align: center; margin-left:100px;">
	        	<div id="divAviso" style="color:red; display: none;">  
	            			<label for="smt" style="color:red;">Seleccione todos los campos</label>
	            </div>
	            <BR>
	            <c:if test="${estadoCarga}">
		        	<div style="text-align: center">  
							<label for="smt" style="color:red;">Hoja de Ruta ya gestionada</label>
		            </div>
	            </c:if>
	            <div>
	            <input id="mensaje" name="mensaje" type="text" value="" title="Mensaje" placeholder="" style="border: none; color: green;">
	            </div>
	            <c:if test="${datos}">
		        	<div>  
		        		<input readonly="readonly" hidden="" type="text" id="id_Ruta" value="${ruta}"  name="id_Ruta"/>
		        		<input readonly="readonly" hidden="" type="text" id="id_size" value="${size}"  name="id_size"/>
		            	<button id="btnGuardar" style="width: 150px;" class="boton-default" >Guardar</button>  
		            </div>
	            </c:if>
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
       
       $(document).ready(function (){
    	   console.log("cantidad: "+$("#id_size").val());
    	   var c=$("#id_size").val();
    	   for(i=1;i<=c;i++){
    	   $( "#fecEntrega"+i ).datepicker({
    	       	minDate: -60, //60 DIAS ATRAS
    	           defaultDate: "0",
    	           changeMonth: true,
    	           numberOfMonths: 1
    	         });
       		}
       });
      
       
       
       function selectSituacion(i){
       	var idSituacion = $("#tipoSituacion"+i).val();
       	var situa = (idSituacion.split("_")[0]);
       	if(situa==2){
       		$("#tipoEstado"+i).hide();
       		
       	}else{
       		$("#tipoEstado"+i).show();
       		
       	}
       	if(situa==1 || situa==3){
       		$("#tipoVinculo"+i).hide();
       		$("#reciPor"+i).hide();
       	}else{
       		$("#tipoVinculo"+i).show();
       		$("#reciPor"+i).show();
       	}
       	console.log("idddddd+ "+situa);
			$.ajax({
				type: "POST", 
				url: "getEstadosMotivos.htm", 
				data: { idSituacion: idSituacion}, 
				cache: false})
				.done(function( data ) {
					$("#tipoMotivo"+i).html(data.combo1);
		 			$("#tipoEstado"+i).html(data.combo2);
				});
		 }
       
       $("#btnGuardar").click(function() {
    	   
    	   var size	= $("#id_size").val();
		   var id_ruta= $("#id_Ruta").val();
		   var estado=1;
    	   var listRutas = {
                   rutas : []
               };
               
           for(i = 1; i <= size; i++){
        	   listRutas.rutas.push({
                   "codigo" : $("#codBar"+i).val(),
                   "situacion"            : $("#tipoSituacion"+i).val(),
                   "estado"  : $("#tipoEstado"+i).val(),
                   "motivo"       : $("#tipoMotivo"+i).val(),
                   "domicilio"       : $("#tipoDomicilio"+i).val(),
                   "vinculo"       : $("#tipoVinculo"+i).val(),
                   "recibido"       : $("#reciPor"+i).val(),
                   "fecRec"       : $("#fecEntrega"+i).val(),
                   "hora"       : $("#hora"+i).val(),
                   "comentario"       : $("#coment"+i).val(),
               });
        	   if($("#tipoSituacion"+i).val()=="" || $("#tipoMotivo"+i).val()=="" || $("#tipoDomicilio"+i).val()=="" || $("#fecEntrega"+i).val()=="" || $("#hora"+i).val()==""){
        		   estado=0;
        	   }
           }
           var jsonRutas  = JSON.stringify(listRutas);
           if(estado==0){
        	   $("#divAviso").show();
        	   console.log("Ingrese todos los campos");
           }else{
        	   console.log("Entro");
        	   $("#divAviso").hide();
               $.ajax({
                  url: "guardar_estados.htm", 
                  type: "POST",  
                  data : { 
                	  jsonRutas : jsonRutas,
                	  id_ruta	: id_ruta,
                          },
                  }).done(function(data){
               	   console.log("datitos");
               	   $("#mensaje").val("Se guardó la gestion de hoja de ruta"); 
               	   $("#btnGuardar").hide();
               });
           }
       });
       </script>
        </div>
        <%@include file="../include-footer.jsp" %>
    </body>
</html>