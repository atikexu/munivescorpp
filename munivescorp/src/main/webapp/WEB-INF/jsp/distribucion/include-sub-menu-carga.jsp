<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    <li><a class='${fn:substring(menuSelect, 1, 9)=="1"?"on":""}' href="../distribucion/?carga=true"><span class="icon-search"></span>Buscar</a></li>
    <c:if test="${SAConsultaDetalle != null}">
    <li><a class='${fn:substring(menuSelect, 1, 9)=="2"?"on":""}' href="./carga_ver_detalle.htm"><span class="icon-list"></span>Detalles</a></li>
    </c:if>
    <li><a class='${fn:substring(menuSelect, 1, 9)=="3"?"on":""}' href="../distribucion/?descarga=true"><span class="icon-search"></span>Descarga Coordinaciones</a></li>
    <li><a class='${fn:substring(menuSelect, 1, 9)=="4"?"on":""}' href="../distribucion/?descarga_base_courier=true"><span class="icon-search"></span>Descarga Base Courier</a></li>
    <li><a class='${fn:substring(menuSelect, 1, 9)=="5"?"on":""}' href="./buscar_hojaruta.htm"><span class="icon-search"></span>Buscar Hoja Ruta</a></li>


    