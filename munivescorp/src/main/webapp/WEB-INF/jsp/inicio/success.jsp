<%@include file="../include-param.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="../include-head.jsp" %>
        <link rel="stylesheet" href="../static/css/default-forms.css">
    </head>
    <body>
    	<%@include file="../include-menu.jsp" %>
        <div class="content">
        	<div class="content-form-login center">
            <h2>Nueva Cuenta</h2>
		    <form class="form-login" action="try-register.htm" method="POST">
		    	<div>
		    		<span><c:if test="${param.error != null}">Los datos ingresados no coinciden, ingrese nuevamente</c:if></span>
		    	</div>
		    	<div>
		    		<span><c:if test="${param.success != null}">Usuario fue registrado correctamente, en breve los estaremos dando de alta. </c:if></span>
		    	</div>
		    	<div class="center font-l field-color1">Datos de Usuario</div>
			    <div style="text-align: left;">
					<label for="usuario">Usuario</label>
					<input type="text" name="username" value="${username }" id="usuario" size="20" style="width: 120px; margin-left: 12px;" maxlength="20" title="Ingrese aqui su Usuario" placeholder="ingrese su usuario"/>
				</div>
				<div style="text-align: left;">
					<label for="contrasena">Contraseña</label>
					<input type="password" name="password" value="" id="contrasena" size="20" style="width: 140px; margin-left: 12px;" maxlength="20" autocorrect="off" title="Ingrese aqui su Contraseña" placeholder="ingrese su contraseña"/>
				</div>
				<div style="text-align: left;">
					<label for="contrasena_r">Repita</label>
					<input type="password" name="password_r" value="" id="contrasena_r" size="20" style="width: 140px; margin-left: 12px;" maxlength="20" autocorrect="off" title="Repita su Contraseña" placeholder="repita su contraseña"/>
				</div>
				<div class="center font-l field-color2" style="padding-top: 24px;">Datos Personales</div>
				<div style="text-align: left;">
					<label for="nombres">Nombres</label>
					<input type="text" name="nombres" value="${nombres }" id="nombres" size="20" style="width: 150px; margin-left: 12px;" maxlength="20" title="Ingrese aqui su Nombre" placeholder="nombres"/>
				</div>
				<div style="text-align: left;">
					<label for="apellidos">Apellidos</label>
					<input type="text" name="apellidos" value="${apellidos }" id="apellidos" size="20" style="width: 150px; margin-left: 12px;" maxlength="20" title="Ingrese aqui su Apellido" placeholder="apellidos"/>
				</div>
				<div style="text-align: left;">
					<label for="correo">Correo</label>
					<input type="email" name="correo" value="${correo }" id="correo" size="30" style="width: 140px; margin-left: 12px;" maxlength="40" title="Ingrese aqui su Correo" placeholder="micorreo@ejmplo.com"/>
				</div>
				<div style="text-align: left;">
					<label for="telefono">Tel&eacute;fono</label>
					<input type="tel" name="telefono" value="${telefono }" id="telefono" size="20" style="width: 120px; margin-left: 12px;" maxlength="20" title="Ingrese aqui su Telefono" placeholder="+51 954989898"/>
				</div>
				<div style="text-align: left;">
					<label for="area">&Aacute;rea</label>
					<input type="text" name="area" value="${area }" id="area" size="30" style="width: 160px; margin-left: 12px;" maxlength="50" title="Ingrese su Area en el que trabaja" placeholder="Ejemplo: Distribución"/>
				</div>
				<div style="text-align: left;">
					<label for="Cargo">Cargo</label>
					<input type="text" name="cargo" value="${cargo }" id="cargo" size="30" style="width: 150px; margin-left: 12px;" maxlength="50" title="Ingrese su Cargo" placeholder="Ejemplo: Jefe de Cuentas"/>
				</div>
				<div style="padding-top: 24px;">
					<input type="submit" class="boton-default" value="Registrar Cuenta" id="smt"/>
				</div>
		    </form>
		    </div>
        </div>
        <%@include file="../include-footer.jsp" %>
    </body>
</html>