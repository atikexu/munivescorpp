package pe.nasqa.values.control;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;





//import pe.nasqa.values.model.ImpExpDbModel;
import pe.nasqa.values.model.MUtilReportFiles;
import pe.nasqa.values.model.ReportesInternosModel;
import pe.nasqa.values.model.entity.DistribucionCoord;
//import pe.nasqa.values.model.entity.ExportVisita;
import pe.nasqa.values.model.entity.Menu;
//import pe.nasqa.values.model.entity.ReporteGNB;
//import pe.nasqa.values.model.entity.ReporteRevistas;
//import pe.nasqa.values.model.entity.Usuario;
import pe.nasqa.values.model.entity.Usuario;

@Controller
@RequestMapping(value="/reportesinternos")
//@SessionAttributes({"USUARIO_INFO","USUARIO_TIPO","USUARIO_CLIENTE"})
@SessionAttributes({"USUARIO_INFO","USUARIO_TIPO","USUARIO_CLIENTE", "CLIENTE_CONFIG_CONSRVPAQ","ID_CLIENTE"})
public class ReportesInternosControl {
	
	@Autowired
	ReportesInternosModel reportesInternosModel;
	
	@Autowired
	ImpExpDbZip zip;
	
	private Logger log = Logger.getLogger(ReportesInternosControl.class);
	
	@RequestMapping(value={"/index.htm"}) 
	public String index(Model model, HttpServletRequest request){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.Q1.name()));
		model.addAttribute("menuSelect", Menu.Q1.name());
		return "reportesinternos/index";
	}
	//reporte1
	@RequestMapping(value={"/gestion_piezas.htm"}) 
	public String gestionPiezas(Model model, HttpServletRequest request){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.Q2.name()));
		model.addAttribute("menuSelect", Menu.Q2.name());
		return "reportesinternos/gestion_piezas";
	}
	//reporte2
	@RequestMapping(value={"/resultado_gestion.htm"}) 
	public String resultadoGestion(Model model, HttpServletRequest request){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.Q3.name()));
		model.addAttribute("menuSelect", Menu.Q3.name());
		return "reportesinternos/resultado_gestion";
	}
	//reporte3
	@RequestMapping(value={"/resumen_hoja_ruta.htm"}) 
	public String resumenHojaRuta(Model model, HttpServletRequest request){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.Q4.name()));
		model.addAttribute("menuSelect", Menu.Q4.name());
		return "reportesinternos/resumen_hoja_ruta";
	}
	//reporte4
	@RequestMapping(value={"/movimiento_hoja_ruta.htm"}) 
	public String movimientoHojaRuta(Model model, HttpServletRequest request){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.Q5.name()));
		model.addAttribute("menuSelect", Menu.Q5.name());
		return "reportesinternos/movimiento_hoja_ruta";
	}
	//reporte5
	@RequestMapping(value={"/movimiento_mensajero.htm"}) 
	public String movimientoMensajero(Model model, HttpServletRequest request){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.Q6.name()));
		model.addAttribute("menuSelect", Menu.Q6.name());
		return "reportesinternos/movimiento_mensajero";
	}
	//reporte6
	@RequestMapping(value={"/situacion_despacho_provincia.htm"}) 
	public String situacionDespachoProvincia(Model model, HttpServletRequest request){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.Q7.name()));
		model.addAttribute("menuSelect", Menu.Q7.name());
		return "reportesinternos/situacion_despacho_provincia";
	}		
	//reporte7
	@RequestMapping(value={"/piezas_rendidas.htm"}) 
	public String piezasRendidas(Model model, HttpServletRequest request){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.Q8.name()));
		model.addAttribute("menuSelect", Menu.Q8.name());
		return "reportesinternos/piezas_rendidas";
	}
	//reporte8
	@RequestMapping(value={"/seguimiento_coordinaciones.htm"}) 
	public String seguimientoCoordinaciones(Model model, HttpServletRequest request){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.Q9.name()));
		model.addAttribute("menuSelect", Menu.Q9.name());
		return "reportesinternos/seguimiento_coordinaciones";
	}
	//reporte9
	@RequestMapping(value={"/ubicacion_coordinaciones.htm"}) 
	public String ubicacionCoordinaciones(Model model, HttpServletRequest request){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.Q10.name()));
		model.addAttribute("menuSelect", Menu.Q10.name());
		return "reportesinternos/ubicacion_coordinaciones";
	}//reporte10
	@RequestMapping(value={"/gestion_usuario.htm"}) 
	public String gestionUsuario(Model model, HttpServletRequest request){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.Q11.name()));
		model.addAttribute("menuSelect", Menu.Q11.name());
		return "reportesinternos/gestion_usuario";
	}
	
	//download1
	@RequestMapping(value={"/download_gestion_piezas.htm"} )
	public String downloadGestionPiezas(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response) throws IOException{
		System.out.println("-------descarga_gestion_piezas-------");

		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.Q2.name()));
		model.addAttribute("menuSelect", Menu.Q2.name());
		String view="reportesinternos/gestion_piezas";
		try {			
			String fechadel = request.getParameter("fromDate");
			String fechaa = request.getParameter("toDate");
			
			System.out.println("fecha: "+fechadel+" "+fechaa);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        Date convertedDel = new Date();
	        Date convertedA = new Date();
	        convertedDel = dateFormat.parse(fechadel);
	        convertedA = dateFormat.parse(fechaa);
	        List<Object> listaGestiones = reportesInternosModel.reporteGestionPiezas(fechadel,fechaa);
			//List<Object> listaGestiones = hojaRutaModel.gestionesBeetrack(fechadel,fechaa);
			System.out.println("tamaaÒo: "+listaGestiones.size());
			if(listaGestiones.size() > 0){
				//System.out.println("--"+fecha);
				final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
				final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "gestion_piezas_"+System.currentTimeMillis()+".xls";
				String path = temperotyFilePath+File.separator;
				System.out.println(path);
				
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition","attachment;filename="+fileName);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				
				MUtilReportFiles reportFiles = new MUtilReportFiles();
				reportFiles.createExcelGestionPiezas(path, fileName, listaGestiones, response.getOutputStream());
				model.addAttribute("archi", "true");
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		
		System.out.println("Done");
		return view;
	}
	
	//download2
	@RequestMapping(value={"/download_resultado_gestion.htm"} )
	public String downloadResultadoGestion(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response) throws IOException{
		System.out.println("-------descarga_resultado_gestion-------");

		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.Q3.name()));
		model.addAttribute("menuSelect", Menu.Q3.name());
		String view="reportesinternos/resultado_gestion";
		try {			
			String fechadel = request.getParameter("fromDate");
			String fechaa = request.getParameter("toDate");
			
			System.out.println("fecha: "+fechadel+" "+fechaa);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        Date convertedDel = new Date();
	        Date convertedA = new Date();
	        convertedDel = dateFormat.parse(fechadel);
	        convertedA = dateFormat.parse(fechaa);
	        List<Object> listaGestiones = reportesInternosModel.reporteResultadoGestion(fechadel,fechaa);
			//List<Object> listaGestiones = hojaRutaModel.gestionesBeetrack(fechadel,fechaa);
			System.out.println("tamaaÒo: "+listaGestiones.size());
			if(listaGestiones.size() > 0){
				//System.out.println("--"+fecha);
				final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
				final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "resultado_gestion_"+System.currentTimeMillis()+".xls";
				String path = temperotyFilePath+File.separator;
				System.out.println(path);
				
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition","attachment;filename="+fileName);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				
				MUtilReportFiles reportFiles = new MUtilReportFiles();
				reportFiles.createExcelResultadoGestion(path, fileName, listaGestiones, response.getOutputStream());
				model.addAttribute("archi", "true");
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		
		System.out.println("Done");
		return view;
	}
	
	//download3
		@RequestMapping(value={"/download_resumen_hoja_ruta.htm"} )
		public String downloadResumenHojaRuta(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
									HttpServletRequest request,  HttpServletResponse response) throws IOException{
			System.out.println("-------descarga_resumen_hoja_ruta-------");

			model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.Q4.name()));
			model.addAttribute("menuSelect", Menu.Q4.name());
			String view="reportesinternos/resumen_hoja_ruta";
			try {			
				String fechadel = request.getParameter("fromDate");
				String fechaa = request.getParameter("toDate");
				
				System.out.println("fecha: "+fechadel+" "+fechaa);
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		        Date convertedDel = new Date();
		        Date convertedA = new Date();
		        convertedDel = dateFormat.parse(fechadel);
		        convertedA = dateFormat.parse(fechaa);
		        List<Object> listaGestiones = reportesInternosModel.reporteResumenHojaRuta(fechadel,fechaa);
				//List<Object> listaGestiones = hojaRutaModel.gestionesBeetrack(fechadel,fechaa);
				System.out.println("tamaaÒo: "+listaGestiones.size());
				if(listaGestiones.size() > 0){
					//System.out.println("--"+fecha);
					final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
					final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
					final String temperotyFilePath = tempDirectory.getAbsolutePath();
					String fileName = "resumen_hoja_ruta_"+System.currentTimeMillis()+".xls";
					String path = temperotyFilePath+File.separator;
					System.out.println(path);
					
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition","attachment;filename="+fileName);
					response.setHeader("Cache-Control", "no-cache");
					response.setHeader("Cache-Control", "max-age=0");
					
					MUtilReportFiles reportFiles = new MUtilReportFiles();
					reportFiles.createExcelResumenHojaRuta(path, fileName, listaGestiones, response.getOutputStream());
					model.addAttribute("archi", "true");
				}
			} catch (Exception e) {
				model.addAttribute("error", "true");
				e.printStackTrace();
			}
			
			System.out.println("Done");
			return view;
		}	
	
	//download4
	@RequestMapping(value={"/download_movimiento_hoja_ruta.htm"} )
	public String downloadMovimientoHojaRuta(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response) throws IOException{
		System.out.println("-------descarga_movimiento_hoja_ruta-------");

		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.Q5.name()));
		model.addAttribute("menuSelect", Menu.Q5.name());
		String view="reportesinternos/movimiento_hoja_ruta";
		try {			
			String fechadel = request.getParameter("fromDate");
			String fechaa = request.getParameter("toDate");
			
			System.out.println("fecha: "+fechadel+" "+fechaa);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        Date convertedDel = new Date();
	        Date convertedA = new Date();
	        convertedDel = dateFormat.parse(fechadel);
	        convertedA = dateFormat.parse(fechaa);
	        List<Object> listaGestiones = reportesInternosModel.reporteMovimientoHojaRuta(fechadel,fechaa);
			//List<Object> listaGestiones = hojaRutaModel.gestionesBeetrack(fechadel,fechaa);
			System.out.println("tamaaÒo: "+listaGestiones.size());
			if(listaGestiones.size() > 0){
				//System.out.println("--"+fecha);
				final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
				final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "movimiento_hoja_ruta_"+System.currentTimeMillis()+".xls";
				String path = temperotyFilePath+File.separator;
				System.out.println(path);
				
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition","attachment;filename="+fileName);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				
				MUtilReportFiles reportFiles = new MUtilReportFiles();
				reportFiles.createExcelMovimientoHojaRuta(path, fileName, listaGestiones, response.getOutputStream());
				model.addAttribute("archi", "true");
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		
		System.out.println("Done");
		return view;
	}
	
	//download5
	@RequestMapping(value={"/download_movimiento_mensajero.htm"} )
	public String downloadMovimientoMensajero(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response) throws IOException{
		System.out.println("-------descarga_movimiento_mensajero-------");

		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.Q6.name()));
		model.addAttribute("menuSelect", Menu.Q6.name());
		String view="reportesinternos/movimiento_mensajero";
		try {			
			String fechadel = request.getParameter("fromDate");
			String fechaa = request.getParameter("toDate");
			
			System.out.println("fecha: "+fechadel+" "+fechaa);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        Date convertedDel = new Date();
	        Date convertedA = new Date();
	        convertedDel = dateFormat.parse(fechadel);
	        convertedA = dateFormat.parse(fechaa);
	        List<Object> listaGestiones = reportesInternosModel.reporteMovimientoMensajero(fechadel,fechaa);
			//List<Object> listaGestiones = hojaRutaModel.gestionesBeetrack(fechadel,fechaa);
			System.out.println("tamaaÒo: "+listaGestiones.size());
			if(listaGestiones.size() > 0){
				//System.out.println("--"+fecha);
				final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
				final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "movimiento_mensajero_"+System.currentTimeMillis()+".xls";
				String path = temperotyFilePath+File.separator;
				System.out.println(path);
				
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition","attachment;filename="+fileName);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				
				MUtilReportFiles reportFiles = new MUtilReportFiles();
				reportFiles.createExcelMovimientoMensajero(path, fileName, listaGestiones, response.getOutputStream());
				model.addAttribute("archi", "true");
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		
		System.out.println("Done");
		return view;
	}
	
	//download6
	@RequestMapping(value={"/download_situacion_despacho_provincia.htm"} )
	public String downloadSituacionDespachoProvincia(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response) throws IOException{
		System.out.println("-------descarga_situacion_despacho_provincia-------");

		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.Q7.name()));
		model.addAttribute("menuSelect", Menu.Q7.name());
		String view="reportesinternos/situacion_despacho_provincia";
		try {			
			String fechadel = request.getParameter("fromDate");
			String fechaa = request.getParameter("toDate");
			
			System.out.println("fecha: "+fechadel+" "+fechaa);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        Date convertedDel = new Date();
	        Date convertedA = new Date();
	        convertedDel = dateFormat.parse(fechadel);
	        convertedA = dateFormat.parse(fechaa);
	        List<Object> listaGestiones = reportesInternosModel.reporteSituacionDespachoProvincia(fechadel,fechaa);
			//List<Object> listaGestiones = hojaRutaModel.gestionesBeetrack(fechadel,fechaa);
			System.out.println("tamaaÒo: "+listaGestiones.size());
			if(listaGestiones.size() > 0){
				//System.out.println("--"+fecha);
				final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
				final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "situacion_despacho_provincia_"+System.currentTimeMillis()+".xls";
				String path = temperotyFilePath+File.separator;
				System.out.println(path);
				
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition","attachment;filename="+fileName);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				
				MUtilReportFiles reportFiles = new MUtilReportFiles();
				reportFiles.createExcelSituacionDespachoProvincia(path, fileName, listaGestiones, response.getOutputStream());
				model.addAttribute("archi", "true");
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		
		System.out.println("Done");
		return view;
	}	
		
	//download7
	@RequestMapping(value={"/download_piezas_rendidas.htm"} )
	public String downloadPiezasRendidas(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response) throws IOException{
		System.out.println("-------descarga_piezas_rendidas-------");

		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.Q8.name()));
		model.addAttribute("menuSelect", Menu.Q8.name());
		String view="reportesinternos/piezas_rendidas";
		try {			
			String fechadel = request.getParameter("fromDate");
			String fechaa = request.getParameter("toDate");
			
			System.out.println("fecha: "+fechadel+" "+fechaa);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        Date convertedDel = new Date();
	        Date convertedA = new Date();
	        convertedDel = dateFormat.parse(fechadel);
	        convertedA = dateFormat.parse(fechaa);
	        List<Object> listaGestiones = reportesInternosModel.reportePiezasRendidas(fechadel,fechaa);
			//List<Object> listaGestiones = hojaRutaModel.gestionesBeetrack(fechadel,fechaa);
			System.out.println("tamaaÒo: "+listaGestiones.size());
			if(listaGestiones.size() > 0){
				//System.out.println("--"+fecha);
				final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
				final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "piezas_rendidas_"+System.currentTimeMillis()+".xls";
				String path = temperotyFilePath+File.separator;
				System.out.println(path);
				
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition","attachment;filename="+fileName);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				
				MUtilReportFiles reportFiles = new MUtilReportFiles();
				reportFiles.createExcelPiezasRendidas(path, fileName, listaGestiones, response.getOutputStream());
				model.addAttribute("archi", "true");
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		
		System.out.println("Done");
		return view;
	}	
	
	//download8
	@RequestMapping(value={"/download_seguimiento_coordinaciones.htm"} )
	public String downloadSeguimientoCoordinaciones(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response) throws IOException{
		System.out.println("-------descarga_Seguimiento_Coordinaciones-------");

		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.Q9.name()));
		model.addAttribute("menuSelect", Menu.Q9.name());
		String view="reportesinternos/seguimiento_coordinaciones";
		try {			
			String fechadel = request.getParameter("fromDate");
			String fechaa = request.getParameter("toDate");
			
			System.out.println("fecha: "+fechadel+" "+fechaa);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        Date convertedDel = new Date();
	        Date convertedA = new Date();
	        convertedDel = dateFormat.parse(fechadel);
	        convertedA = dateFormat.parse(fechaa);
	        List<Object> listaGestiones = reportesInternosModel.reporteSeguimientoCoordinaciones(fechadel,fechaa);
			//List<Object> listaGestiones = hojaRutaModel.gestionesBeetrack(fechadel,fechaa);
			System.out.println("tamaaÒo: "+listaGestiones.size());
			if(listaGestiones.size() > 0){
				//System.out.println("--"+fecha);
				final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
				final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "seguimiento_coordinaciones_"+System.currentTimeMillis()+".xls";
				String path = temperotyFilePath+File.separator;
				System.out.println(path);
				
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition","attachment;filename="+fileName);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				
				MUtilReportFiles reportFiles = new MUtilReportFiles();
				reportFiles.createExcelSeguimientoCoordinaciones(path, fileName, listaGestiones, response.getOutputStream());
				model.addAttribute("archi", "true");
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		
		System.out.println("Done");
		return view;
	}
	
	// download9
		@RequestMapping(value={"/listar_coord_cod.htm"})
		public void listaRutaCod(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req, HttpServletResponse response,
				@RequestParam("codigoB") String codigo,
				@RequestParam("fecha") String fecha
				){
			response.setContentType("application/json");
	        JSONObject outPut = new JSONObject(); 
	        String mensaje="";
	        String tablita="";
	        String codBar_Rendicion="";
	       
			try{  
				
				String direc_rendicion="";
				
				List<Object> distribucionCoord = reportesInternosModel.datosDistribucionCoord(codigo,fecha);
				
				if(distribucionCoord==null){
					mensaje="CÛdigo de barra sin coordinaciones.";
				}
				if(mensaje.equals("")){
					mensaje="Coordinaciones: "+distribucionCoord.size();
					tablita=getHTMLTablaCoordCod(distribucionCoord);
					outPut.put("lista", distribucionCoord.size());
					if(distribucionCoord.size()>0){
						Iterator itr = distribucionCoord.iterator();
						String alertas="";
				        while(itr.hasNext()){
				           Object[] obj = (Object[]) itr.next(); 
				           alertas+=obj[4]+"_";
				        }
				        outPut.put("alertas", alertas);
					}
					outPut.put("tablita", tablita);
					
				}else{

				}
				outPut.put("mensaje", mensaje);
		       response.getWriter().write(outPut.toString());
			}catch(Exception e){
				e.printStackTrace();
			}

		}
	
	//download10
	@RequestMapping(value={"/download_gestion_usuario.htm"} )
	public String downloadGestionUsuario(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response) throws IOException{
		System.out.println("-------descarga_Gestion_Usuario-------");

		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.Q11.name()));
		model.addAttribute("menuSelect", Menu.Q11.name());
		String view="reportesinternos/gestion_usuario";
		try {			
			String fechadel = request.getParameter("fromDate");
			String fechaa = request.getParameter("toDate");
			
			System.out.println("fecha: "+fechadel+" "+fechaa);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        Date convertedDel = new Date();
	        Date convertedA = new Date();
	        convertedDel = dateFormat.parse(fechadel);
	        convertedA = dateFormat.parse(fechaa);
	        List<Object> listaGestiones = reportesInternosModel.reporteGestionUsuario(fechadel,fechaa);
			//List<Object> listaGestiones = hojaRutaModel.gestionesBeetrack(fechadel,fechaa);
			System.out.println("tamaaÒo: "+listaGestiones.size());
			if(listaGestiones.size() > 0){
				//System.out.println("--"+fecha);
				final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
				final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "gestion_usuario_"+System.currentTimeMillis()+".xls";
				String path = temperotyFilePath+File.separator;
				System.out.println(path);
				
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition","attachment;filename="+fileName);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				
				MUtilReportFiles reportFiles = new MUtilReportFiles();
				reportFiles.createExcelGestionUsuario(path, fileName, listaGestiones, response.getOutputStream());
				model.addAttribute("archi", "true");
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		
		System.out.println("Done");
		return view;
	}
	
	//downloadSCC
		@RequestMapping(value={"/download_bcp_scc.htm"} )
		public String downloadBcpScc(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
									HttpServletRequest request,  HttpServletResponse response) throws IOException{
			System.out.println("-------descarga_BCP_SCC-------");

			model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.Q5.name()));
			model.addAttribute("menuSelect", Menu.Q5.name());
			String view="reportesinternos/movimiento_hoja_ruta";
			try {			
				String fechadel = request.getParameter("fromDate1");
				String fechaa = request.getParameter("toDate1");
				
				System.out.println("fecha: "+fechadel+" "+fechaa);

		        List<Object> listaGestiones = reportesInternosModel.reporteBcpScc(fechadel,fechaa);
				//List<Object> listaGestiones = hojaRutaModel.gestionesBeetrack(fechadel,fechaa);
				System.out.println("tamaaÒo: "+listaGestiones.size());
				if(listaGestiones.size() > 0){
					//System.out.println("--"+fecha);
					final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
					final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
					final String temperotyFilePath = tempDirectory.getAbsolutePath();
					String fileName = "bcp_scc_"+System.currentTimeMillis()+".xls";
					String path = temperotyFilePath+File.separator;
					System.out.println(path);
					
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition","attachment;filename="+fileName);
					response.setHeader("Cache-Control", "no-cache");
					response.setHeader("Cache-Control", "max-age=0");
					
					MUtilReportFiles reportFiles = new MUtilReportFiles();
					reportFiles.createExcelBCPSCC(path, fileName, listaGestiones, response.getOutputStream());
					model.addAttribute("archi", "true");
				}
			} catch (Exception e) {
				model.addAttribute("error", "true");
				e.printStackTrace();
			}
			
			System.out.println("Done");
			return view;
		}
		
		public String getHTMLTablaCoordCod(List<Object> listaCoord){
			Integer estado;
	        String outPut = 
	                            "<table id=\"table-consulta-2\" >" +
	                                "<thead>" +
	                                    "<tr>" +
	                                        "<td>Cargo</td>" +
	                                        "<td>Destino</td>" +
	                                        "<td>Postal</td>" +
	                                        "<td>DirecciÛn</td>" +
	                                    "</tr>" +
	                                "</thead>" +
	                                "<tbody>";
	        
	        Iterator itr = listaCoord.iterator();
	        while(itr.hasNext()){
	           Object[] obj = (Object[]) itr.next();    
	           outPut +=    "<tr class='<c:if test=\"${rowCounter.count % 2 == 0}\">on</c:if>'>" +
	                            "<td>"+(obj[0]!=null?obj[0]:"")+"</td>" +
	                            "<td>"+(obj[1]!=null?obj[1]:"")+"</td>" +
	                            "<td>"+(obj[2]!=null?obj[2]:"")+"</td>" +
	                            "<td>"+(obj[3]!=null?obj[3]:"")+"</td>" +
	                         "</tr>";          
	        }         
	                outPut +=       "</tbody>" +
	                            "</table>";
	         return outPut;
	    }
	/*
	@RequestMapping(value={"/upload.htm"}, method = RequestMethod.POST)
	public String upload(Model model, @RequestParam("file") MultipartFile file, HttpServletRequest request){
		String view="impexpdb/index";
		String path =  request.getSession().getServletContext().getRealPath("dinamic/upload");
		try {
			
			byte[] bytes = file.getBytes();
			String dirDestino = CVDinamico.getDateInFormat("yyyy-MM-dd_hhmm");
			File dir = new File(path + File.separator + dirDestino);
			if (!dir.exists())dir.mkdirs();
			
			log.debug("Carpeta de Carga: "+dir);
			
			File uploadFile = new File(dir.getAbsolutePath(), file.getOriginalFilename());
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile));
			stream.write(bytes);
			stream.close();
			
			String llave = request.getParameter("llave")==null?"":request.getParameter("llave");
			
			List<String> unZipFiles = zip.unZipFileZip4j(uploadFile, (llave.length()==0?null:llave));
			
			model.addAttribute("upFiles", unZipFiles);
			model.addAttribute("upDir", dirDestino);
			
			uploadFile.delete();
			
			if(impExpDbModel.importFiles(dir.getAbsolutePath(), unZipFiles)){
				view="impexpdb/import_loader";
				log.debug("Data Importada a DB");
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
			model.addAttribute("error", "true");
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.F1.name()));
		model.addAttribute("menuSelect", Menu.F1.name());
		return view;
	}
	
	
	
		
	@RequestMapping(value={"/import_load.htm"}, method = RequestMethod.POST)
	public String import_load(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		String view="impexpdb/error";
		try {
			//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			impExpDbModel.loadDb(usuarioInfo.getId());
			log.debug("La carga fue realizado correctamente por Usuario: "+usuarioInfo.getNombres());
			view="impexpdb/success";
		} catch (Exception e) {
			model.addAttribute("error", "true");
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.F1.name()));
		model.addAttribute("menuSelect", Menu.F1.name());
		model.addAttribute("ref", "import");
		return view;
	}
	
	@RequestMapping(value={"/export_form.htm"}, method = RequestMethod.GET)
	public String export_form(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.F1.name()));
		model.addAttribute("menuSelect", Menu.F2.name());
		return "impexpdb/export_form";
	}
	
	@RequestMapping(value={"/export_visitas.htm"}, method = RequestMethod.GET)
	public String export_visitas(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.F1.name()));
		model.addAttribute("menuSelect", Menu.F4.name());
		return "impexpdb/export_visitas";
	}
	
	@RequestMapping(value={"/import_report.htm"}, method = RequestMethod.GET)
	public String import_report(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.F1.name()));
		model.addAttribute("menuSelect", Menu.F5.name());
		return "impexpdb/subir_reporte_gnb";
	}
		
	@RequestMapping(value={"/download_visitas.htm"} )
	public String download_visitas(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response){
		System.out.println("-------download_visitas-------");
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.F1.name()));
		model.addAttribute("menuSelect", Menu.F4.name());
		String view="impexpdb/export_visitas";
		try {			
			String fec_inicio = request.getParameter("fromDate");
			String fec_fin = request.getParameter("toDate");
			String reDescargar = request.getParameter("checkDescargado")==null?"NO":"SI";
			List<ExportVisita> listaVisita = impExpDbModel.getVisitasFromUI(CVDinamico.getDateFromString(fec_inicio, "dd/MM/yyyy"), CVDinamico.getDateFromString(fec_fin, "dd/MM/yyyy"),reDescargar);
			if(listaVisita.size() > 0){
				System.out.println("--"+fec_inicio);
				System.out.println("--"+fec_fin);
				final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
				final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "Export_visitas_"+System.currentTimeMillis()+".txt";
				String path = temperotyFilePath+File.separator+fileName;
				System.out.println("SIZE VISITAS:"+listaVisita.size());
				System.out.println("path VISITAS:"+path);
				
				response.setContentType("text/plain");
				response.setHeader("Content-Disposition","attachment;filename="+fileName);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				System.out.println("-------------download TXT----------");
				
			
				createTXTVisitas(listaVisita, path);
				java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
				baos = convertPDFToByteArrayOutputStream(path);
				OutputStream os = response.getOutputStream();
		        baos.writeTo(os);
		        os.flush();
				File file = new File(path);
				file.delete();
				
				for(ExportVisita visi : listaVisita){
					impExpDbModel.updateVisita(visi.getId_visita(), "0");// 0 = Ya fue Descargado
				}
				os.close();
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		model.addAttribute("succes", "true");
		System.out.println("Done");
		return view;
	}	

	private static final String ORIGINAL = "¡·…ÈÕÌ”Û⁄˙‹¸";
    private static final String REPLACEMENT = "AaEeIiOoUuUu";
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public String stripAccents(String str) {
		if (str == null) {
		    return null;
		}
		char[] array = str.toCharArray();
		for (int index = 0; index < array.length; index++) {
		    int pos = ORIGINAL.indexOf(array[index]);
		    if (pos > -1) {
		        array[index] = REPLACEMENT.charAt(pos);
		    }
		}
		return new String(array);
	}

	public String deleteCabecera(File originalFile, String path){
		File f = new File(path,"FINAL_"+originalFile.getName());
		try{
			FileWriter w = new FileWriter(f,true);
			BufferedWriter bw = new BufferedWriter(w);
			PrintWriter wr = new PrintWriter(bw);					
			FileInputStream fis = null;
	        InputStreamReader isr = null;
	        BufferedReader br = null;
	        fis = new FileInputStream(originalFile);
            isr = new InputStreamReader(fis, "Cp1252");
            br = new BufferedReader(isr);
			String codigo="";
			int indice = 0;			
			while (( codigo = br.readLine()) != null){				
				if(indice == 0){
					String [] strSplit = codigo.split("\\t");
					System.out.println("TAMA—O DE LA CABECERA->"+strSplit.length);
					if(strSplit.length==25){
						
						//VALIDACION DE CABECERA
						if(	strSplit[0].trim().toUpperCase().replaceAll("\u00A0", "").equals("FECHA INGRESO") && 
							strSplit[1].trim().toUpperCase().replaceAll("\u00A0", "").equals("CARGO") && 
							stripAccents(strSplit[2].trim().toUpperCase()).replaceAll("\u00A0", "").equals("INSTITUCION") && 
							strSplit[3].trim().toUpperCase().replaceAll("\u00A0", "").equals("DOCUMENTO") && 
							strSplit[4].trim().toUpperCase().replaceAll("\u00A0", "").equals("NRO_TDC") && 
							strSplit[5].trim().toUpperCase().replaceAll("\u00A0", "").equals("APELL_NOM_CLIENTE") && 
							strSplit[6].trim().toUpperCase().replaceAll("\u00A0", "").equals("CERTIFICADO") && 
							strSplit[7].trim().toUpperCase().replaceAll("\u00A0", "").equals("TIPO_VALE") && 
							strSplit[8].trim().toUpperCase().replaceAll("\u00A0", "").equals("COD_VALE") && 
							strSplit[9].trim().toUpperCase().replaceAll("\u00A0", "").equals("PUNTAJE") && 
							strSplit[10].trim().toUpperCase().replaceAll("\u00A0", "").equals("FECHA_ENTREGA") && 
							stripAccents(strSplit[11].trim().toUpperCase()).replaceAll("\u00A0", "").equals("FECHA_ACTUALIZACION_TABLA") && 
							strSplit[12].trim().toUpperCase().replaceAll("\u00A0", "").equals("CERTIFICADO") && 
							stripAccents(strSplit[13].trim().toUpperCase()).replaceAll("\u00A0", "").equals("DIRECCION") && 
							strSplit[14].trim().toUpperCase().replaceAll("\u00A0", "").equals("DISTRITO") && 
							(strSplit[15].trim().toUpperCase().replaceAll("\u00A0", "").equals("PROVINCIA") || 
							strSplit[15].trim().toUpperCase().replaceAll("\u00A0", "").equals("PRIVINCIA")) &&
							strSplit[16].trim().toUpperCase().replaceAll("\u00A0", "").equals("DEPARTAMENTO") && 
							stripAccents(strSplit[17].trim().toUpperCase()).replaceAll("\u00A0", "").equals("MOVIL") &&
							strSplit[18].trim().toUpperCase().replaceAll("\u00A0", "").equals("DOCUMENTO_RECEPTOR") &&
							strSplit[19].trim().toUpperCase().replaceAll("\u00A0", "").equals("NOMBRE_RECEPTOR") &&
							strSplit[20].trim().toUpperCase().replaceAll("\u00A0", "").equals("EMAIL") &&
							strSplit[21].trim().toUpperCase().replaceAll("\u00A0", "").equals("LIFEMILES") &&
							stripAccents(strSplit[22].trim().toUpperCase()).replaceAll("\u00A0", "").equals("UBICACION_ACTUAL")&&
							stripAccents(strSplit[23].trim().toUpperCase()).replaceAll("\u00A0", "").equals("FECHA_ULTIMA") &&
							stripAccents(strSplit[24].trim().toUpperCase()).replaceAll("\u00A0", "").equals("RESULTADO_ULTIMO")){
							indice ++;
							System.out.println("CABECERA CORECTA!");
							continue;
							}else{System.out.println("CABECERA INCORRECTA!");
								 throw new Exception(strSplit[0].trim().toUpperCase().replaceAll("\u00A0", "")+"|"+
											strSplit[1].trim().toUpperCase().replaceAll("\u00A0", "")+"|"+
											stripAccents(strSplit[2].trim().toUpperCase()).replaceAll("\u00A0", "")+"|"+
											strSplit[3].trim().toUpperCase().replaceAll("\u00A0", "")+"|"+ 
											strSplit[4].trim().toUpperCase().replaceAll("\u00A0", "")+"|"+
											strSplit[5].trim().toUpperCase().replaceAll("\u00A0", "")+"|"+
											strSplit[6].trim().toUpperCase().replaceAll("\u00A0", "")+ "|"+
											strSplit[7].trim().toUpperCase().replaceAll("\u00A0", "")+"|"+
											strSplit[8].trim().toUpperCase().replaceAll("\u00A0", "")+"|"+
											strSplit[9].trim().toUpperCase().replaceAll("\u00A0", "")+ "|"+
											strSplit[10].trim().toUpperCase().replaceAll("\u00A0", "")+"|"+
											stripAccents(strSplit[11].trim().toUpperCase()).replaceAll("\u00A0", "")+"|"+
											strSplit[12].trim().toUpperCase().replaceAll("\u00A0", "")+"|"+
											stripAccents(strSplit[13].trim().toUpperCase()).replaceAll("\u00A0", "")+"|"+
											strSplit[14].trim().toUpperCase().replaceAll("\u00A0", "")+"|"+
											strSplit[15].trim().toUpperCase().replaceAll("\u00A0", "")+"|"+
											strSplit[15].trim().toUpperCase().replaceAll("\u00A0", "")+"|"+
											strSplit[16].trim().toUpperCase().replaceAll("\u00A0", "")+"|"+
											stripAccents(strSplit[17].trim().toUpperCase()).replaceAll("\u00A0", "")+"|"+
											strSplit[18].trim().toUpperCase().replaceAll("\u00A0", "")+"|"+
											strSplit[19].trim().toUpperCase().replaceAll("\u00A0", "")+"|"+
											strSplit[20].trim().toUpperCase().replaceAll("\u00A0", "")+"|"+
											strSplit[21].trim().toUpperCase().replaceAll("\u00A0", "")+"|"+
											stripAccents(strSplit[22].trim().toUpperCase().replaceAll("\u00A0", ""))+"|"+
											stripAccents(strSplit[23].trim().toUpperCase()).replaceAll("\u00A0", "")+"|"+
											stripAccents(strSplit[24].trim().toUpperCase()).replaceAll("\u00A0", ""));									
							}
						
					}else{System.out.println("NO SON 25 CAMPOS");
						return "ERROR";
					}
				}else{
					wr.println(codigo);
				}
				indice ++;				
			}
			
			wr.close();
			bw.close();
			br.close();
            isr.close();
            fis.close();

			
		}catch(Exception e){
			try{
				File error = new File(path,"ERROR.txt");			
				PrintWriter wr = new PrintWriter(error);	
				wr.println(e.getMessage());
				
				for(StackTraceElement err  : e.getStackTrace()){
					wr.println(err.toString());
				}
				wr.close();
			}catch(Exception ed){
				ed.printStackTrace();
			}
			e.printStackTrace();
			return "ERROR";
		}
		System.out.println("SALIDA: "+f.getAbsolutePath());
		return f.getAbsolutePath();
	}
	
	
	@RequestMapping(value={"/upload_report.htm"}, method = RequestMethod.POST)
	public String upload_report(Model model, @RequestParam("file") MultipartFile file, HttpServletRequest request, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		String view="impexpdb/subir_reporte_gnb";
		String path =  request.getSession().getServletContext().getRealPath("dinamic/upload");
		try {
			
			byte[] bytes = file.getBytes();
			
			String dirDestino = CVDinamico.getDateInFormat("yyyy-MM-dd_hhmm");
			File dir = new File(path + File.separator + dirDestino);
			if (!dir.exists())dir.mkdirs();

			log.debug("Carpeta de Carga: "+dir);
			System.out.println("-->"+dir);
			File uploadFile = new File(dir.getAbsolutePath(), file.getOriginalFilename());
			System.out.println("-->"+uploadFile);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile));
			stream.write(bytes);
			stream.close();
			model.addAttribute("file", file.getOriginalFilename());

				if(impExpDbModel.importReportGNB(uploadFile)){
					import_reporte_gnb_load(model, usuarioInfo);
					view="impexpdb/success";
					log.debug("Data Importada a DB");
				}else{
					view="impexpdb/subir_reporte_gnb";
					log.debug("Error Al subir los Datos, Archivo inconsistente");
					throw new Exception("ERROR EN LA FUNCION:"+uploadFile.getAbsolutePath());
				}
			
				uploadFile.delete();

		} catch (Exception e) {
			log.error(e.getMessage());
			
			try{
				File error = new File(path,"ERROR.txt");			
				PrintWriter wr = new PrintWriter(error);	
				wr.println(e.getMessage());
				
				for(StackTraceElement err  : e.getStackTrace()){
					wr.println(err.toString());
				}
				wr.close();
			}catch(Exception ed){
				ed.printStackTrace();
			}
			
			view="impexpdb/subir_reporte_gnb";
			//model.addAttribute("error", "true");
			model.addAttribute("vacio", "true");
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.F1.name()));
		model.addAttribute("menuSelect", Menu.F6.name());
		return view;
	}
	
	@RequestMapping(value={"/import_reporte_gnb_load.htm"}, method = RequestMethod.POST)
	public String import_reporte_gnb_load(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		String view="impexpdb/error";
		try {
			//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			impExpDbModel.loadBD_reporte_gnb();
			log.debug("La carga fue realizado correctamente");
			view="impexpdb/success";
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.F1.name()));
		model.addAttribute("menuSelect", Menu.F6.name());
		model.addAttribute("ref", "import");
		return view;
	}
	
	
	public File createTXTVisitas(List<ExportVisita> listaVisita,String path){
		 File f = new File(path);//RUTA DONDE GUARDAR EL TXT CON LA RELACION DE ARCHIVOS FALLADOS     
         try{                
                FileWriter w = new FileWriter(f,true);
                BufferedWriter bw = new BufferedWriter(w);
                PrintWriter wr = new PrintWriter(bw);     
                for(ExportVisita visita : listaVisita){
                    wr.println(
                    		((visita.getFecha_hoja()!=null) ? converFecDDmmYY(visita.getFecha_hoja()): "")+"|"+
            				((visita.getCodigo_mensajero()!=null) ? visita.getCodigo_mensajero() : "")+"|"+
                    		((visita.getCodigo_barras()!=null) ? visita.getCodigo_barras() : "")+"|"+
                    		((visita.getCodigo_motivo()!=null) ? visita.getCodigo_motivo() : "")+"|"+
                    		((visita.getTipo_direccion()!=null) ? visita.getTipo_direccion() : "")+"|"+
                    		((visita.getHora_visita()!=null) ? visita.getHora_visita().replace(":", "")+"00" : "")+"|"+
                    		((visita.getCodigo_vinculo()!=null) ? visita.getCodigo_vinculo() : "")+"|"+
                    		((visita.getPersona_recibida()!=null) ? visita.getPersona_recibida():"")+"|"+
                    		((visita.getId_visita()!=null) ? visita.getId_visita():"")+"|"+
                    		((visita.getFec_creacion()!=null) ? visita.getFec_creacion().substring(0, 10):"")); 
                }                
                wr.close();
                bw.close();                
         }catch(IOException e){
                e.printStackTrace();
         }
         return f;
	}
	public String converFecDDmmYY(String fecha){
		try{
			if(fecha!=""){
				String fechaSplit [] = fecha.split("-");
				fecha = fechaSplit[2]+"/"+fechaSplit[1]+"/"+fechaSplit[0];				
			}
		}catch(Exception e){
			fecha = "";
			e.printStackTrace();
		}
		return fecha;
	}
		
	private static java.io.ByteArrayOutputStream convertPDFToByteArrayOutputStream(String fileName) {	
		InputStream inputStream = null;
		java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
		try {
	
			inputStream = new FileInputStream(fileName);	
			byte[] buffer = new byte[1024];
			baos = new java.io.ByteArrayOutputStream();
	
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesRead);
			}
	
		} catch (java.io.FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return baos;
	}
	
	@RequestMapping(value={"/download.htm"}, method = RequestMethod.POST)
	public String download(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest request){
		String view="impexpdb/error";
		String pathRoot =  request.getSession().getServletContext().getRealPath("dinamic/download");
		try {
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String reDescargar = request.getParameter("reDescargar")==null?"NO":"SI";
			String password = request.getParameter("llave").equals("")?null:request.getParameter("llave");
			
			String dirDestino = CVDinamico.getDateInFormat("yyyy-MM-dd_hhmmss");
			String sufijoName = CVDinamico.getDateInFormat("yyyyMMdd_hhmmss");
			List<String> toZipFiles = new ArrayList<String>();
			toZipFiles.add("RCOO"+sufijoName);
			toZipFiles.add("RSEG"+sufijoName);
			
			File path = new File(pathRoot, dirDestino);
			//path.setWritable(true);
			path.mkdirs();
			path.setWritable(true, false);
			
			String zipFile = null;
			
			boolean resDao = false;
			if(CVDinamico.isDate(fromDate) && CVDinamico.isDate(toDate)){
				resDao = impExpDbModel.exportFiles(path.getPath(), toZipFiles, fromDate, toDate, reDescargar);
				if(resDao){
					zipFile = zip.zipFilesZip4j(path, password);
				}
				if(zipFile!=null){
					log.debug("La exportacion ("+zipFile+") fue realizado correctamente por Usuario: "+usuarioInfo.getNombres());
					view="impexpdb/success";
					model.addAttribute("downZipFile", zipFile);
					model.addAttribute("downDirFile", dirDestino);
				}
			}else{
				model.addAttribute("error", "true");
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.F1.name()));
		model.addAttribute("menuSelect", Menu.F2.name());
		model.addAttribute("ref", "export");
		return view;
	}
	
	@RequestMapping(value={"/cargos_form.htm"}, method = RequestMethod.GET)
	public String cargos_form(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.F1.name()));
		model.addAttribute("menuSelect", Menu.F3.name());
		return "impexpdb/cargos_form";
	}
	
	@RequestMapping(value={"/gestion_form.htm"}, method = RequestMethod.GET)
	public String gestion_form(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.F1.name()));
		model.addAttribute("menuSelect", Menu.F3.name());
		return "impexpdb/gestion_form";
	}
	@RequestMapping(value={"/gestion_upload.htm"}, method = RequestMethod.POST)
	public String gestion_upload(Model model, @RequestParam("file") MultipartFile file, HttpServletRequest request){
		String view="impexpdb/index";
		String path =  request.getSession().getServletContext().getRealPath("dinamic/upload");
		try {
			
			byte[] bytes = file.getBytes();
			String fileName="gestion_"+CVDinamico.getDateInFormat("yyyy-MM-dd_hhmm")+file.getOriginalFilename();
			File uploadFile = new File(path, fileName);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile));
			stream.write(bytes);
			stream.close();
			
			String llave = request.getParameter("llave")==null?"":request.getParameter("llave");
			
			int gestinCount = impExpDbModel.updateGestion(uploadFile.getAbsolutePath(), null);
			model.addAttribute("upGestionFile", file.getOriginalFilename());
			model.addAttribute("upGestionCount", gestinCount);
			
			uploadFile.delete();
			
			view="impexpdb/gestion_info";
			log.debug("Data Gestion Actualizada");
			
		} catch (Exception e) {
			log.error(e.getMessage());
			model.addAttribute("error", "true");
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.F1.name()));
		model.addAttribute("menuSelect", Menu.F3.name());
		return view;
	}
	
	@RequestMapping(value={"/descarga_gestiones.htm"})
	public String descargaGestiones(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req){
		String view="impexpdb/descarga_gestiones";
		try {
			
			System.out.println("entro a gestiones");
			
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.F1.name()));
		model.addAttribute("menuSelect", Menu.F6.name());
		return view;
	}
	
	@RequestMapping(value={"/download_gestiones.htm"} )
	public String downloadGestiones(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response){
		System.out.println("-------descarga_gestiones-------");
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.F1.name()));
		model.addAttribute("menuSelect", Menu.F6.name());
		String view="impexpdb/descarga_gestiones";
		try {			
			String fecha = request.getParameter("fecha");	
			
			System.out.println("fecha: "+fecha);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        Date convertedDate = new Date();
	        convertedDate = dateFormat.parse(fecha);

			List<Object> listaGestiones = impExpDbModel.getGestiones(convertedDate);			
			if(listaGestiones.size() > 0){
				System.out.println("--"+fecha);
				final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
				final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "gestiones_"+System.currentTimeMillis()+".xls";
				String path = temperotyFilePath+File.separator;
				System.out.println(path);
				
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition","attachment;filename="+fileName);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				
				MUtilReportFiles reportFiles = new MUtilReportFiles();
				reportFiles.createExcelGestiones(path, fileName, listaGestiones, response.getOutputStream());
				model.addAttribute("archi", "true");
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		
		System.out.println("Done");
		return view;
	}	*/

}
