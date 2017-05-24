<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

                        <li><a class='${fn:substring(menuSelect, 1, 9)=="1"?"on":""}' href="./"><span class="icon-search"></span>Buscar</a></li>
                        <c:if test="${SAConsultaDetalle != null}">
                        <li><a class='${fn:substring(menuSelect, 1, 9)=="2"?"on":""}' href="./consulta_ver_detalle.htm"><span class="icon-list"></span>Detalles</a></li>
                        <li><a class='${fn:substring(menuSelect, 1, 9)=="3"?"on":""}' href="./consulta_coord.htm"><span class="icon-list"></span>Coordinaciones</a></li>
                        <li><a class='${fn:substring(menuSelect, 1, 9)=="4"?"on":""}' href="./consulta_coord_telf.htm"><span class="icon-list"></span>Reg. Telef&oacute;nicas</a></li>
                        
                        </c:if>