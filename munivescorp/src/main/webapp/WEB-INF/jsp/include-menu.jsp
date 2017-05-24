<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
		<div class="menu">
            <header>
                <div class="logo-content-img">
                    <a href="../inicio/"><img class="logo-img" alt="values" title="values" src="../static/img/cabecera_ALAIZ.PNG"></a>
                </div>
                <div class="logo-content-icon">
                    <a href="#" onclick="refreshMenu();"><img class="logo-icon" alt="T" src="../static/img/cabecera_ALAIZ.PNG"></a>
                    <a href="#" class="menu-btn"><span class="icon-menu"></span>&nbsp;</a>
                </div>
                <nav>
                    <div>
                    	<sec:authorize access="isAnonymous()">
                        <a href="../inicio/login.htm"><span class="icon-enter"></span> Ingresar</a>
                        &nbsp;
<!--                         <a href="../inicio/register.htm"><span class="icon-user-plus"></span> Solicitar Cuenta</a> -->
<!--                         <a href="../inicio/consulta_rapida.htm"><span class="icon-user-plus"></span> Consulta Rapida</a> -->
                        </sec:authorize>
                        <sec:authorize access="isAuthenticated()">
                        <c:if test="${USUARIO_TIPO=='CL'}">
	           				<span style="color: #00a2ff;">${USUARIO_CLIENTE.razonsocial }</span>
			           	</c:if>
			           	<span class="spanUsuario" style="color: #999;">
			           		${USUARIO_INFO.nombres} ${USUARIO_INFO.apellidos} <!-- sec:authentication property="authorities"/ -->
			           	</span>
			           	
                        <a ><span class="icon-user"></span> <sec:authentication property="name"/></a>
                        &nbsp;
                        <a href="../inicio/passwd_form.htm"><span class="icon-key"></span> Cambiar Contraseña</a>
   						<a href="../inicio/logout"><span class="icon-exit"></span> Cerrar Sesion</a>
						</sec:authorize>
                    </div>
                    <sec:authorize access="isAnonymous()">
                    <div class="ul-content">
                   		<h2 style="display: block; padding: 12px;">Consulta de Ubicación y Estado de Documento</h2>
                   	</div>
                   	</sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                    <div class="ul-content">
                    <ul>
                    	<li><a class='${fn:substring(menuSelect, 0, 1)=="A"?"on":""}' href="../inicio/"><span class="icon-home3"></span>Inicio</a></li>
                    	<sec:authorize access="hasAnyRole('DI_ADMIN','DI_CONSULTAS',  'CL_ADMIN','CL_CONSULTAS')">
                        <li><a class='${fn:substring(menuSelect, 0, 1)=="R"?"on":""}' href="../consultaalaiz/"><span class="icon-search"></span>Consulta de Documentos</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasAnyRole('DI_ADMIN','DI_REPORTES',  'CL_ADMIN','CL_REPORTES')">
                        <li><a class='${fn:substring(menuSelect, 0, 1)=="C"?"on":""}' href="../distribucion/?reporte=true"><span class="icon-stats-bars"></span>Reportes / OP</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasAnyRole('DI_ADMIN','DI_COORDINACION','DI_COORDINACION_TELEF',  'CL_ADMIN','CL_COORDINACION','CL_COORDINACION_TELEF')">
                        <li><a class='${fn:substring(menuSelect, 0, 1)=="D"?"on":""}' href="../coordinacion/"><span class="icon-pushpin"></span>Coordinaciones</a></li>
                        </sec:authorize>
                         <sec:authorize access="hasAnyRole('DI_ADMIN',  'CL_ADMIN')"> 
                        <li><a class='${fn:substring(menuSelect, 0, 1)=="E"?"on":""}' href="../usuario/"><span class="icon-stack"></span>Usuarios</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasAnyRole('DI_ADMIN','DI_OPERADOR')">
                        <li><a class='${fn:substring(menuSelect, 0, 1)=="F"?"on":""}' href="../impexpdb/"><span class="icon-database"></span>Data</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasAnyRole('DI_ADMIN','DI_OPERADOR_DIGIT')">
                        <li><a class='${fn:substring(menuSelect, 0, 1)=="G"?"on":""}' href="../cargaimg/"><span class="icon-images"></span>Im&aacute;genes</a></li>
                        </sec:authorize>
                    	<sec:authorize access="hasAnyRole('DI_ADMIN','DI_CONFIG_EST')">
                        <li><a class='${fn:substring(menuSelect, 0, 1)=="K"?"on":""}' href="../admin/"><span class="icon-cog"></span>Config</a></li>
                        </sec:authorize>                        
                        <sec:authorize access="hasAnyRole('DI_ADMIN','DI_CONSULTAS','CL_ADMIN','CL_CONSULTAS','DI_COURIER')">
                        <li><a class='${fn:substring(menuSelect, 0, 1)=="L"?"on":""}' href="../distribucion/?carga=true"><span class="icon-search"></span>Carga Gestiones</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasAnyRole('DI_ADMIN','DI_CONSULTAS','CL_ADMIN','CL_CONSULTAS','DI_COURIER')">
                        <li><a class='${fn:substring(menuSelect, 0, 1)=="M"?"on":""}' href="../cargosetiquetas/"><span class="icon-search"></span>Cargos y Etiquetas</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasAnyRole('DI_ADMIN','DI_CONSULTAS','DI_COURIER')">
                        <li><a class='${fn:substring(menuSelect, 0, 1)=="N"?"on":""}' href="../hojaruta/"><span class="icon-search"></span>Hoja de Ruta</a></li>

                        </sec:authorize>
                        <sec:authorize access="hasAnyRole('DI_ADMIN','DI_CONSULTAS','CL_ADMIN','CL_CONSULTAS','DI_COURIER')">
                        <li><a class='${fn:substring(menuSelect, 0, 1)=="P"?"on":""}' href="../datobase/"><span class="icon-search"></span>Data Base</a></li>

                        </sec:authorize>
                        <sec:authorize access="hasAnyRole('DI_ADMIN','DI_CONSULTAS','DI_COURIER')">
                        <li><a class='${fn:substring(menuSelect, 0, 1)=="O"?"on":""}' href="../rendicion/"><span class="icon-search"></span>Rendicion</a></li>

                        </sec:authorize>   
                        <sec:authorize access="hasAnyRole('DI_ADMIN','DI_CONSULTAS','DI_COURIER')">
                        <li><a class='${fn:substring(menuSelect, 0, 1)=="Q"?"on":""}' href="../reportesinternos/"><span class="icon-search"></span>Reportes Internos</a></li>

                        </sec:authorize>               
                    </ul>
                    </div>
                    <div class="sub-nav-content">
                    <ul class="sub-nav">
                    	<% if(request.getAttribute("menuSelect").toString().startsWith("A")){ %>
                    		<%@include file="inicio/include-sub-menu.jsp" %>
                    	<% }else if(request.getAttribute("menuSelect").toString().startsWith("B")){ %>
                    		<%@include file="distribucion/include-sub-menu-consulta.jsp" %>
                    	<% }else if(request.getAttribute("menuSelect").toString().startsWith("C")){ %>
                    		<%@include file="distribucion/include-sub-menu-reporte.jsp" %>
                    	<% }else if(request.getAttribute("menuSelect").toString().startsWith("D")){ %>
                    		<%@include file="coordinacion/include-sub-menu.jsp" %>
                    	<% }else if(request.getAttribute("menuSelect").toString().startsWith("E")){ %>
                    		<%@include file="usuario/include-sub-menu.jsp" %>
                    	<% }else if(request.getAttribute("menuSelect").toString().startsWith("F")){ %>
                    		<%@include file="impexpdb/include-sub-menu.jsp" %>
                    	<% }else if(request.getAttribute("menuSelect").toString().startsWith("G")){ %>
	                		<%@include file="cargaimg/include-sub-menu.jsp" %>
	                	<% }else if(request.getAttribute("menuSelect").toString().startsWith("K")){ %>
                    		<%@include file="admin/include-sub-menu.jsp" %>
                    	<% }else if(request.getAttribute("menuSelect").toString().startsWith("L")){ %>
                		<%@include file="distribucion/include-sub-menu-carga.jsp" %>
                		<% }else if(request.getAttribute("menuSelect").toString().startsWith("M")){ %>
                    		<%@include file="cargosetiquetas/include-sub-menu.jsp" %>
                    	<% }else if(request.getAttribute("menuSelect").toString().startsWith("N")){ %>
                    		<%@include file="hojaruta/include-sub-menu.jsp" %>
                		<% }else if(request.getAttribute("menuSelect").toString().startsWith("O")){ %>
                			<%@include file="rendicion/include-sub-menu.jsp" %>
						<% }else if(request.getAttribute("menuSelect").toString().startsWith("P")){ %>
                			<%@include file="datobase/include-sub-menu.jsp" %>                	
            			<% }else if(request.getAttribute("menuSelect").toString().startsWith("Q")){ %>
                    		<%@include file="reportesinternos/include-sub-menu.jsp" %>                	
                		<% } %>
                    </ul>
                    </div>
                    </sec:authorize>
                </nav>
            </header>
        </div>
