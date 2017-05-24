<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

                        <li><a class='${fn:substring(menuSelect, 1, 9)=="1"?"on":""}' href='./${(fn:substring(menuSelect, 1, 9)=="4"||fn:substring(menuSelect, 1, 9)=="5")?"?ref=telf":""}'><span class="icon-search"></span>Seleccionar</a></li>
                       <li><a class='${fn:substring(menuSelect, 1, 9)=="6"?"on":""}' href="./coordinacion_masiva.htm"><span class="icon-upload"></span>Carga Coordinaciones BCP</a></li>
                       <li><a class='${fn:substring(menuSelect, 1, 9)=="7"?"on":""}' href="./coordinacion_masivaGen.htm"><span class="icon-upload"></span>Carga Coordinaciones General</a></li>
                       
                        <c:if test="${(SACoordinacionSelect != null)}">
                        <c:if test="${(SACoordinacionSelect.indSit!='2')}">
                        <li><a class='${fn:substring(menuSelect, 1, 9)=="2"?"on":""}' href="./coord_re_form.htm"><span class="icon-pencil"></span>Nueva Coordinaci&oacute;n</a></li>
                        <li><a class='${fn:substring(menuSelect, 1, 9)=="3"?"on":""}' href="./coord_list.htm"><span class="icon-list2"></span>Listar Coordinaciones</a></li>
                        </c:if>
                        </c:if>
                        
                        <sec:authorize access="hasAnyRole('DI_ADMIN','DI_COORDINACION','DI_COORDINACION_TELEF', 'CL_COORDINACION_TELEF' )">
                        <c:if test="${SACoordinacionSelect != null}">
                        <c:if test="${(SACoordinacionSelect.indSit!='2')}">
                        <li><a class='${fn:substring(menuSelect, 1, 9)=="4"?"on":""}' href="./gestt_form.htm"><span class="icon-pencil"></span>Nueva Gest. Telef&oacute;nica</a></li>
                        <li><a class='${fn:substring(menuSelect, 1, 9)=="5"?"on":""}' href="./gestt_list.htm"><span class="icon-list2"></span>Lista Gest. Telef&oacute;nica</a></li>
                        
                        </c:if>
                        </c:if>
                        </sec:authorize>
