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
            <h2>Cambiar Contrase�a</h2>
		    <form class="form-login" action="passwd_save.htm" method="POST" onsubmit="return verifyForm();">
		    	<div>
		    		<span><c:if test="${not empty error}">${cause }</c:if></span>
		    		<span><c:if test="${not empty success}">${info }</c:if></span>
		    	</div>
				<div>
					<label for="contrasena_act">Contrase�a Actual</label>
					<input type="password" name="contrasena_act" value="" id="contrasena_act" autocorrect="off" title="Contrase�a Actual" placeholder="contrase�a actual"/>
				</div>
				<div>
					<p style="font-size: 0.8em;color: blue; text-align: left;">La nueva contrase�a debe tener como <b>m&iacute;nimo 8 caracteres</b>. Use numeros, letras mayusculas y minusculas. Evite usar caracteres especiales. </p>
				</div>
				<div>
					<label for="contrasena">Contrase�a Nueva</label>
					<input type="password" name="contrasena" value="" id="contrasena" autocorrect="off" title="Contrase�a Nueva" placeholder="contrase�a nueva"/>
				</div>
				<div>
					<label for="contrasena_rep">Repita Contrase�a</label>
					<input type="password" name="contrasena_rep" value="" id="contrasena_rep" autocorrect="off" title="Repita Contrase�a" placeholder="repita su contrase�a"/>
				</div>
				<div>
					<span id="formAlert"></span>
				</div>
				<div>
					<input type="submit" class="boton-default" value="Cambiar Contrase�a" id="smt"/>
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
			$("#formAlert").html("Repita su contrase�a correctamente.");
		}else if($("#contrasena").val().length<8){
			$("#formAlert").html("Su contrase�a debe tener como <b>minimo 8 caracteres</b>. Use Numeros, letras mayusculas y minusculas.");
		}else{
			$("#formAlert").html("Ingrese su contrase�a actual y nueva.");
		}
		return false;
	}
}

</script>
    </body>
</html>