<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

	<li><a class='${fn:substring(menuSelect, 1, 10)=="1"?"on":""}' href="./index.htm"><span class="icon-search"></span>Index</a></li>
    <li><a class='${fn:substring(menuSelect, 1, 10)=="2"?"on":""}' href="./carga_archivo.htm"><span class="icon-search"></span>Carga</a></li>
    <li><a class='${fn:substring(menuSelect, 1, 10)=="3"?"on":""}' href="./creacion_hojaruta.htm"><span class="icon-search"></span>Consulta</a></li>


    