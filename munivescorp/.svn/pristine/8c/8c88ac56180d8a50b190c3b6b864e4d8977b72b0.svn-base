<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
	<sec:authorize access="hasRole('DI_ADMIN')">
          <li><a class='${fn:substring(menuSelect, 1, 9)=="1"?"on":""}' href="./"><span class="icon-home3"></span>Index</a></li>
          <li><a class='${fn:substring(menuSelect, 1, 9)=="2"?"on":""}' href="./user_main.htm"><span class="icon-users"></span>Usuarios</a></li>
          <li><a class='${fn:substring(menuSelect, 1, 9)=="3"?"on":""}' href="./clip_main.htm"><span class="icon-address-book"></span>Clientes & Productos</a></li>
          <li><a class='${fn:substring(menuSelect, 1, 9)=="4"?"on":""}' href="./flst_main.htm"><span class="icon-folder"></span>File Storage</a></li>
          <li><a class='${fn:substring(menuSelect, 1, 9)=="6"?"on":""}' href="./man_mensajeros.htm"><span class="icon-users"></span>Mantenimiento Mensajeros</a></li>
           <li><a class='${fn:substring(menuSelect, 1, 9)=="7"?"on":""}' href="./man_agencias.htm"><span class="icon-users"></span>Mantenimiento Agencias</a></li>
    </sec:authorize>
   	<sec:authorize access="hasAnyRole('DI_ADMIN','DI_CONFIG_EST')">
          <li><a class='${fn:substring(menuSelect, 1, 9)=="5"?"on":""}' href="./cofig_estados.htm"><span class="icon-cog"></span>Config Estados</a></li>
	</sec:authorize>