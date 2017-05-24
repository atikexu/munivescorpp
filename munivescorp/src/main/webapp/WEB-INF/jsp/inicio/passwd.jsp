<%@include file="../include-param.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="../include-head.jsp" %>
        <link rel="stylesheet" href="../static/css/default-forms.css">
		<link href='../static/css/strength.css' rel='stylesheet' type='text/css'>
		<script type="text/javascript" src="../static/js/strength.js"></script>
    </head>
    <body>
    	<%@include file="../include-menu.jsp" %>
        <div class="content">
        	<div class="content-form-login center">
            <h2>Cambiar Contraseņa</h2>
		    <form class="form-login" action="passwd_save.htm" method="POST" onsubmit="return verifyForm();">
		    	<div>
		    		<span><c:if test="${not empty error}">${cause }</c:if></span>
		    		<span><c:if test="${not empty success}">${info }</c:if></span>
		    	</div>
				<div>
					<label for="contrasena_act">Contraseņa Actual</label>
					<input type="password" name="contrasena_act" value="" id="contrasena_act" autocorrect="off" title="Contraseņa Actual" placeholder="contraseņa actual"/>
				</div>
				<div>
					<p style="font-size: 0.8em;color: blue; text-align: left;">La nueva contraseņa debe tener como <b>m&iacute;nimo 8 caracteres</b>. Use numeros, letras mayusculas y minusculas. Evite usar caracteres especiales. </p>
				</div>
				<div>
					<label for="contrasena">Contraseņa Nueva</label>
					<input type="password" name="contrasena" value="" id="contrasena" autocorrect="off" title="Contraseņa Nueva" placeholder="contraseņa nueva"/>
				</div>
				<div>
					<label for="contrasena_rep">Repita Contraseņa</label>
					<input type="password" name="contrasena_rep" value="" id="contrasena_rep" autocorrect="off" title="Repita Contraseņa" placeholder="repita su contraseņa"/>
				</div>
				<div>
					<span id="formAlert"></span>
				</div>
				<div>
					<input type="submit" class="boton-default" value="Cambiar Contraseņa" id="smt"/>
				</div>
		    </form>
		    </div>
        </div>
        <%@include file="../include-footer.jsp" %>
        
<script>

$(document).ready(function($) {
	
	$('#contrasena').strength({
        strengthClass: 'strength',
        strengthMeterClass: 'strength_meter',
        strengthButtonClass: 'button_strength',
        strengthButtonText: '',
        strengthButtonTextToggle: ''
    });

});

verifyForm=function(){
	if($("#contrasena_act").val()!="" && 
			$("#contrasena").val()!="" && 
			$("#contrasena").val()==$("#contrasena_rep").val() &&
			$("#contrasena").val().length>7){
		$("#smt").attr('disabled','disabled');
		$("#smt").val('Espere...');
		return true;
	}else{
		if($("#contrasena").val()!=$("#contrasena_rep").val()){
			$("#formAlert").html("Repita su contraseņa correctamente.");
		}else if($("#contrasena").val().length<8){
			$("#formAlert").html("Su contraseņa debe tener como <b>minimo 8 caracteres</b>. Use Numeros, letras mayusculas y minusculas.");
		}else{
			$("#formAlert").html("Ingrese su contraseņa actual y nueva.");
		}
		return false;
	}
}

</script>
    </body>
</html>