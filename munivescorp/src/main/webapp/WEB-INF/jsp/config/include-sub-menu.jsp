<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

                        <li><a class='${fn:substring(menuSelect, 1, 9)=="1"?"on":""}' href=""><span class="icon-pencil"></span>Nuevo Registro</a></li>
                        <li><a class='${fn:substring(menuSelect, 1, 9)=="2"?"on":""}' href=""><span class="icon-list2"></span>Listar Coordinaciones</a></li>
                        <li><a class='${fn:substring(menuSelect, 1, 9)=="3"?"on":""}' href=""><span class="icon-stack"></span>Descargar Coordinaciones / DB</a></li>
                        <li><a class='${fn:substring(menuSelect, 1, 9)=="4"?"on":""}' href=""><span class="icon-stack"></span>Descargar Coordinaciones / DB</a></li>
