<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

	<li><a class='${fn:substring(menuSelect, 1, 9)=="1"?"on":""}' href="./index.htm"><span class="icon-search"></span>Index</a></li>
    <li><a class='${fn:substring(menuSelect, 1, 9)=="2"?"on":""}' href="./gestion_piezas.htm"><span class="icon-download"></span>Gestión de Piezas</a></li>
    <li><a class='${fn:substring(menuSelect, 1, 9)=="3"?"on":""}' href="./resultado_gestion.htm"><span class="icon-download"></span>Resultado de Gestión</a></li>
    <li><a class='${fn:substring(menuSelect, 1, 9)=="4"?"on":""}' href="./resumen_hoja_ruta.htm"><span class="icon-download"></span>Resumen de Hoja de Ruta</a></li>
    <li><a class='${fn:substring(menuSelect, 1, 9)=="5"?"on":""}' href="./movimiento_hoja_ruta.htm"><span class="icon-download"></span>Movimiento en Hoja de Ruta</a></li>
    <li><a class='${fn:substring(menuSelect, 1, 9)=="6"?"on":""}' href="./movimiento_mensajero.htm"><span class="icon-download"></span>Movimiento por Mensajero</a></li>
    <li><a class='${fn:substring(menuSelect, 1, 9)=="7"?"on":""}' href="./situacion_despacho_provincia.htm"><span class="icon-download"></span>Situación Despacho a Provincia</a></li>
    <li><a class='${fn:substring(menuSelect, 1, 9)=="8"?"on":""}' href="./piezas_rendidas.htm"><span class="icon-download"></span>Piezas Rendidas</a></li>
    <li><a class='${fn:substring(menuSelect, 1, 9)=="9"?"on":""}' href="./seguimiento_coordinaciones.htm"><span class="icon-download"></span>Piezas Coordinadas/Seguimiento de Coordinaciones</a></li>
    <li><a class='${fn:substring(menuSelect, 1, 9)=="10"?"on":""}' href="./ubicacion_coordinaciones.htm"><span class="icon-download"></span>Piezas Coordinadas/Ubicación de Coordinaciones</a></li>
    <li><a class='${fn:substring(menuSelect, 1, 9)=="11"?"on":""}' href="./gestion_usuario.htm"><span class="icon-download"></span>Gestión por Usuario</a></li>
    



    