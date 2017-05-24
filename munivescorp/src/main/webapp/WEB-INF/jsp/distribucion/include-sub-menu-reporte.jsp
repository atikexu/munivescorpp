<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

           <li><a class='${fn:substring(menuSelect, 1, 9)=="1"?"on":""}' href="./?reporte=true"><span class="icon-stats-bars"></span>Estado Orden Proceso</a></li>
            <li><a class='${fn:substring(menuSelect, 1, 9)=="2"?"on":""}' href="./reporte_form.htm"><span class="icon-database"></span>Descarga de Reportes / OP</a></li>
            <c:if test="${(ID_CLIENTE  == '0007') || (USUARIO_TIPO  == 'DI')}">
            <li><a class='${fn:substring(menuSelect, 1, 9)=="3"?"on":""}' href="./reporte_form_r2.htm"><span class="icon-download"></span>Reporte TC GNB</a></li>
            
                    
             <li><a class='${fn:substring(menuSelect, 1, 9)=="4"?"on":""}' href="./download_report_gnbvales.htm"><span class="icon-download"></span>Reporte GNB Vales</a></li>
                          <li><a class='${fn:substring(menuSelect, 1, 9)=="5"?"on":""}' href="./download_report_gnbtoken.htm"><span class="icon-download"></span>Reporte GNB Token</a></li>

            </c:if>
            <c:if test="${(ID_CLIENTE  == '0035') || (USUARIO_TIPO  == 'DI')}">
            <li><a class='${fn:substring(menuSelect, 1, 9)=="6"?"on":""}' href="./download_report_bcptc.htm"><span class="icon-download"></span>Reporte TC BCP</a></li>
            </c:if>
             <c:if test="${(ID_CLIENTE  == '0038') || (USUARIO_TIPO  == 'DI')}">
            <li><a class='${fn:substring(menuSelect, 1, 9)=="7"?"on":""}' href="./download_report_herbalife.htm"><span class="icon-download"></span>Reporte Herbalife</a></li>
            </c:if>
             <c:if test="${(ID_CLIENTE  == '0035') || (USUARIO_TIPO  == 'DI')}">
            <li><a class='${fn:substring(menuSelect, 1, 9)=="8"?"on":""}' href="./download_report_bcpdetallegen.htm"><span class="icon-download"></span>Reporte Detalle General BCP</a></li>
            </c:if>
             <c:if test="${(ID_CLIENTE  == '0026') || (USUARIO_TIPO  == 'DI')}">
            <li><a class='${fn:substring(menuSelect, 1, 9)=="9"?"on":""}' href="./download_report_orbis.htm"><span class="icon-download"></span>Reporte Orbis General</a></li>
            </c:if>
            
              <c:if test="${(ID_CLIENTE  == '0026') || (USUARIO_TIPO  == 'DI')}">
            <li><a class='${fn:substring(menuSelect, 1, 9)=="10"?"on":""}' href="./download_report_orbisrz.htm"><span class="icon-download"></span>Reporte Orbis Rezagadas</a></li>
            </c:if>
            
             <c:if test="${(ID_CLIENTE  == '0031') || (USUARIO_TIPO  == 'DI')}">
            <li><a class='${fn:substring(menuSelect, 1, 9)=="11"?"on":""}' href="./download_report_revistas.htm"><span class="icon-download"></span>Reporte Revistas</a></li>
            </c:if>
            
              <c:if test="${(ID_CLIENTE  == '0040') || (USUARIO_TIPO  == 'DI')}">
            <li><a class='${fn:substring(menuSelect, 1, 9)=="12"?"on":""}' href="./download_report_bbvapend.htm"><span class="icon-download"></span>Reporte BBVA Pendiente</a></li>
               <li><a class='${fn:substring(menuSelect, 1, 9)=="13"?"on":""}' href="./download_report_bbvaentr.htm"><span class="icon-download"></span>Reporte BBVA Entregadas</a></li>
                 <li><a class='${fn:substring(menuSelect, 1, 9)=="14"?"on":""}' href="./download_report_bbvaworkf.htm"><span class="icon-download"></span>Reporte BBVA WorkFlow</a></li>
            </c:if>
            
              <c:if test="${ (USUARIO_TIPO  == 'DI')}">
            <li><a class='${fn:substring(menuSelect, 1, 9)=="15"?"on":""}' href="./download_report_enviosprov.htm"><span class="icon-download"></span>Reporte Envios Prov.</a></li>
            </c:if>
            
              <c:if test="${ (USUARIO_TIPO  == 'DI')}">
            <li><a class='${fn:substring(menuSelect, 1, 9)=="16"?"on":""}' href="./download_report_coorddia.htm"><span class="icon-download"></span>Reporte Coordinaciones Rango Dia</a></li>
            </c:if>