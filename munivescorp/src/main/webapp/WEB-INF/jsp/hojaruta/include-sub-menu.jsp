<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

	<li><a class='${fn:substring(menuSelect, 1, 9)=="1"?"on":""}' href="./index.htm"><span class="icon-search"></span>Index</a></li>
    <li><a class='${fn:substring(menuSelect, 1, 9)=="2"?"on":""}' href="./configurar_direccion.htm"><span class="icon-search"></span>Configurar Direccion</a></li>
    <li><a class='${fn:substring(menuSelect, 1, 9)=="3"?"on":""}' href="./creacion_hojaruta.htm"><span class="icon-search"></span>Creación Hoja Ruta</a></li>
    <li><a class='${fn:substring(menuSelect, 1, 9)=="4"?"on":""}' href="./descargar_hojaruta.htm"><span class="icon-search"></span>Descargar Hoja Ruta</a></li>


    