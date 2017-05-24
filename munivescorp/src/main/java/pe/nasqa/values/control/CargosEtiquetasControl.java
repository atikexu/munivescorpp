package pe.nasqa.values.control;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.log4j.Logger;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pe.dataimagenes.utils.Constantes;
import pe.nasqa.values.model.ClienteModel;
import pe.nasqa.values.model.DistribucionModel;
import pe.nasqa.values.model.MVDinamico;
import pe.nasqa.values.model.MensajeroModel;
import pe.nasqa.values.model.PerfilModel;
import pe.nasqa.values.model.UsuarioModel;
import pe.nasqa.values.model.entity.ChoiceBean;
import pe.nasqa.values.model.entity.Distribucion;
import pe.nasqa.values.model.entity.Estados;
import pe.nasqa.values.model.entity.Mensajero;
import pe.nasqa.values.model.entity.Menu;
import pe.nasqa.values.model.entity.Perfil;
import pe.nasqa.values.model.entity.Usuario;
import pe.nasqa.values.model.entity.UsuarioEstado;
import pe.nasqa.values.model.entity.Valorado;

@Controller
@RequestMapping(value="/cargosetiquetas")
public class CargosEtiquetasControl {
	
	@Autowired
	private DistribucionModel distribucionModel;
	@Autowired
	private PerfilModel perfilModel;
	@Autowired
	private UsuarioModel usuarioModel;
	@Autowired
	private ClienteModel clienteModel;
	@Autowired
	private MensajeroModel mensajeroModel;
	
	@Autowired
	private ImpExpDbZip zip;
	@Autowired
	private SendMail mail;
	
	private Logger log = Logger.getLogger(CargosEtiquetasControl.class);
	static Connection conn = null;
	@RequestMapping(value="/index.htm")
	public String inicio(Model model){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.M1.name()));
		model.addAttribute("menuSelect", Menu.M1.name());
		return "cargosetiquetas/index";
	}
	 
	@RequestMapping(value={"/generar_cargos.htm"})
	public String generarCargos(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req){
		String view="cargosetiquetas/generar_cargos";
		try {
			
			user_load(model);
			
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.M1.name()));
		model.addAttribute("menuSelect", Menu.M2.name());
		return view;
	}
	
	@RequestMapping(value={"/generar_cargos_codigo.htm"})
	public String generarCargosCodigo(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req){
		String view="cargosetiquetas/generar_cargos_codigo";
		try {
			
			user_load(model);
			
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.M1.name()));
		model.addAttribute("menuSelect", Menu.M3.name());
		return view;
	}
	
	@RequestMapping(value={"/generar_cargos_pdf.htm"})
	public void generarCargosPdf(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req, HttpServletResponse response,
			@RequestParam("fecProceso") String fecProceso,
			@RequestParam("cliente") String cliente,
			@RequestParam("producto") String producto
			){
		System.out.println("Fecha proceso: "+fecProceso);
		response.setContentType("application/json");
        JSONObject outPut = new JSONObject(); 
        String cadena="";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String nombreJasper="";
        String nombreArchivo="";
        Date convertedDate = new Date();
		try{
			convertedDate = dateFormat.parse(fecProceso);
			cadena= ""+System.currentTimeMillis();
			if(cliente.equals("0035") && producto.equals("02")){//bcp
				nombreJasper="BCP_TC.jrxml";
				nombreArchivo="bcp_tc.pdf";
			}
			if(cliente.equals("0026") && producto.equals("91")){//orbis
				nombreJasper="ORBIS.jrxml";
				nombreArchivo="orbis.pdf";
			}
			if(cliente.equals("0007") && producto.equals("60")){//gnb vales
				nombreJasper="GNB_VALES.jrxml";
				nombreArchivo="gnb_vales.pdf";
			}
			if(cliente.equals("0007") && producto.equals("50")){//gnb token
				nombreJasper="GNB_TOKEN.jrxml";
				nombreArchivo="gnb_token.pdf";
			}
			if(cliente.equals("0007") && producto.equals("89")){//gnb tarjeta
				nombreJasper="GNB_DEBITO.jrxml";
				nombreArchivo="gnb_debito.pdf";
			}
			if(cliente.equals("0007") && producto.equals("02")){//gnb tarjeta
				nombreJasper="GNB_DEBITO.jrxml";
				nombreArchivo="gnb_credito.pdf";
			}
			if(cliente.equals("0031") && producto.equals("07")){//revistas el comercio
				nombreJasper="REVISTAS.jrxml";
				nombreArchivo="revistas.pdf";
			}
			if(cliente.equals("0038") && producto.equals("41")){//herbalife
				nombreJasper="HERBALIFE.jrxml";
				nombreArchivo="herbalife.pdf";
			}
			if(cliente.equals("0040") && producto.equals("02")){//bbva
				nombreJasper="BBVA.jrxml";
				nombreArchivo="bbva.pdf";
			}
			if(cliente.equals("0026") && producto.equals("41")){//orbis
				nombreJasper="ORBIS_PAQUETE.jrxml";
				nombreArchivo="orbis_paquete.pdf";
			}
			generarPdfCargos(convertedDate,cliente,producto,nombreJasper,nombreArchivo,req);
			
			outPut.put("mensaje", "se creo pdf");
			outPut.put("nombreArchivo", nombreArchivo);
			outPut.put("cadena", cadena);
	       response.getWriter().write(outPut.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value={"/generar_cargos_codigo_pdf.htm"})
	public void generarCargosCodigoPdf(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req, HttpServletResponse response,
			@RequestParam("codigosBarra") String codigosBarra
			){
		System.out.println("Codigos: "+codigosBarra);
		response.setContentType("application/json");
        JSONObject outPut = new JSONObject(); 
        String mensaje="";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String vacio="";
        Date convertedDate = new Date();
		try{
			String tebca = "";
			String servitebca = "";
			String cencosud = "";
			String gnb = "";
			String commerce = "";
			String orbis = "";
			String elcomercio = "";
			String bcp = "";
			String dataimagenes = "";
			String interbank = "";
			String herbalife = "";
			String afpprofuturo = "";
			String bbva = "";
			String tevaperu = "";
			List<String> listaCodigos=MVDinamico.stringSplitToArray(codigosBarra);
			if(listaCodigos.size()>0){
				vacio=generarPdfCargosCodigo(listaCodigos, req);
			}else{
				mensaje="Ingrese c�digos";
			}
			outPut.put("vacio", vacio);
			outPut.put("mensaje", "se creo pdf");
			outPut.put("mensaje", mensaje);
	       response.getWriter().write(outPut.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value={"/generar_etiquetas.htm"})
	public String generarEtiquetas(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req){
		String view="cargosetiquetas/generar_etiquetas";
		try {
			
			user_load(model);
			
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.M1.name()));
		model.addAttribute("menuSelect", Menu.M4.name());
		return view;
	}
	
	@RequestMapping(value={"/generar_etiquetas_pdf.htm"},method = RequestMethod.POST)
	public void generarEtiquetasPdf(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req, HttpServletResponse response,
			@RequestParam("fecProceso") String fecProceso
			//@RequestParam("cliente") String cliente,
			//@RequestParam("producto") String producto
			){
		System.out.println("Fecha proceso: "+fecProceso);
		response.setContentType("application/json");
        JSONObject outPut = new JSONObject(); 
        String cadena="";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String nombreJasper="";
        String nombreArchivo="";
        Date convertedDate = new Date();
        String path =  req.getSession().getServletContext().getRealPath("dinamic/upload");
		System.out.println("srv "+ path);
		try{
			convertedDate = dateFormat.parse(fecProceso);
			cadena= ""+System.currentTimeMillis();
			
			nombreJasper="COORDINACIONES.jrxml";
			nombreArchivo="coord.pdf";
			
			generarPdfCoordinacion(convertedDate,"","",nombreJasper,nombreArchivo,req);
			
			outPut.put("mensaje", "se creo pdf coord");
			outPut.put("nombreArchivo", nombreArchivo);
			outPut.put("cadena", cadena);
	       response.getWriter().write(outPut.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value={"/generar_cargo.htm"}, method = RequestMethod.POST)
	public String mensajeroSave(Model model, ModelMap modelMap, HttpServletRequest req){
		String view="cargosetiquetas/generar_cargos";
		String mensaje="";
		try {
			user_load(model);
		

			String fecEnvio=req.getParameter("fecEnvio");
			String cliente=req.getParameter("cliente");
			String producto=req.getParameter("producto");

			Mensajero mensajero = new Mensajero();

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.M1.name()));
		model.addAttribute("menuSelect", Menu.M2.name());
		return view;
	}
	
	@RequestMapping(value={"/generar_etiqueta.htm"}, method = RequestMethod.POST)
	public String generarEtiqueta(Model model, ModelMap modelMap, HttpServletRequest req){
		String view="cargosetiquetas/generar_etiquetas";
		String mensaje="";
		try {
			user_load(model);
		
			String fecEnvio=req.getParameter("fecEnvio");
			String cliente=req.getParameter("cliente");
			String producto=req.getParameter("producto");

			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.M1.name()));
		model.addAttribute("menuSelect", Menu.M3.name());
		return view;
	}
	
	public void generarPdfCargos(Date fecProceso, String cliente, String producto, String nombreJasper,String nombreArchivo, HttpServletRequest req){
		try{

			try {
	            Class.forName("org.postgresql.Driver");
	        }
	        catch (ClassNotFoundException e) {
	            System.out.println("postgres JDBC Driver not found.");
	        }
	        try {
	            conn = DriverManager.getConnection(Constantes.POSTGRESQL_CONEC,Constantes.POSTGRESQL_USUARIO, Constantes.POSTGRESQL_PASSWORD);
	            conn.setAutoCommit(false);
	        }
	        catch (SQLException e) {
	            System.out.println("Error de conexi�n: " + e.getMessage());
	        }

			System.out.println("entro para generar reportee");
			JasperReport repor = null;
			//System.out.println("entro: "+Constantes.RUTA_JASPER+nombreJasper);
			try {
			//repor = JasperCompileManager.compileReport(Constantes.RUTA_JASPER+nombreJasper);
			repor = JasperCompileManager.compileReport(req.getServletContext().getRealPath("/WEB-INF/jasper/"+nombreJasper));	
				
			}catch(JRException ex){
				System.out.println(ex.toString());
			}
			Map parametro = new HashMap();

			System.out.println("fecha "+fecProceso+ "nombre: "+cliente+" producto "+producto);
			parametro.put("fecha",fecProceso);
			parametro.put("nombre",cliente);
			parametro.put("producto",producto);
	        JasperPrint jPrint = JasperFillManager.fillReport(repor, parametro,conn);
	        jPrint.setProperty("net.sf.jasperreports.expo rt.character.encoding","ISO-8859-1");
	        //JasperExportManager.exportReportToPdfFile(jPrint,Constantes.RUTA_PDF+"hojaruta\\"+nombreArchivo);
	        //JasperExportManager.exportReportToPdfFile(jPrint,req.getServletContext().getRealPath("/pdf/hojaruta/"+nombreArchivo+".pdf"));
	        JasperExportManager.exportReportToPdfFile(jPrint,req.getServletContext().getRealPath("/pdf/hojaruta/"+nombreArchivo));
	        System.out.println("creo pdf "+nombreArchivo);

		}catch(JRException ex){
            System.out.println(ex.toString());
        }
	}
	
	public void generarPdfCoordinacion(Date fecProceso, String cliente, String producto, String nombreJasper,String nombreArchivo, HttpServletRequest req){
		try{

			try {
	            Class.forName("org.postgresql.Driver");
	        }
	        catch (ClassNotFoundException e) {
	            System.out.println("postgres JDBC Driver not found.");
	        }
	        try {
	            conn = DriverManager.getConnection(Constantes.POSTGRESQL_CONEC,Constantes.POSTGRESQL_USUARIO, Constantes.POSTGRESQL_PASSWORD);
	            conn.setAutoCommit(false);
	        }
	        catch (SQLException e) {
	            System.out.println("Error de conexi�n: " + e.getMessage());
	        }

			System.out.println("entro para generar reportee");
			JasperReport repor = null;
			//System.out.println("entro: "+Constantes.RUTA_JASPER+nombreJasper);
			try {
			//repor = JasperCompileManager.compileReport(Constantes.RUTA_JASPER+nombreJasper);	
			repor = JasperCompileManager.compileReport(req.getServletContext().getRealPath("/WEB-INF/jasper/"+nombreJasper));
				
			}catch(JRException ex){
				System.out.println(ex.toString());
			}
			Map parametro = new HashMap();

			System.out.println("fecha "+fecProceso+ "nombre: "+cliente+" producto "+producto);
			parametro.put("fecha",fecProceso);
			parametro.put("nombre",cliente);
			parametro.put("producto",producto);
	        JasperPrint jPrint = JasperFillManager.fillReport(repor, parametro,conn);
	        jPrint.setProperty("net.sf.jasperreports.expo rt.character.encoding","ISO-8859-1");
	        //JasperExportManager.exportReportToPdfFile(jPrint,Constantes.RUTA_PDF+"hojaruta\\"+nombreArchivo);
	        //JasperExportManager.exportReportToPdfFile(jPrint,req.getServletContext().getRealPath("/pdf/hojaruta/"+nombreArchivo+".pdf"));
	        JasperExportManager.exportReportToPdfFile(jPrint,req.getServletContext().getRealPath("/pdf/coord/"+nombreArchivo));
	        System.out.println("creo pdf "+nombreArchivo);

		}catch(JRException ex){
            System.out.println(ex.toString());
        }
	}
	
	public String generarPdfCargosCodigo(List<String> listaCodigos, HttpServletRequest req){
		String mensaje="";
		try{

			try {
	            Class.forName("org.postgresql.Driver");
	        }
	        catch (ClassNotFoundException e) {
	            System.out.println("postgres JDBC Driver not found.");
	        }
	        try {
	            conn = DriverManager.getConnection(Constantes.POSTGRESQL_CONEC,Constantes.POSTGRESQL_USUARIO, Constantes.POSTGRESQL_PASSWORD);
	            conn.setAutoCommit(false);
	        }
	        catch (SQLException e) {
	            System.out.println("Error de conexi�n: " + e.getMessage());
	        }

			System.out.println("entro para generar reporte");
			JasperReport repor;

//				File fichero1 = new File(Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_bcp.pdf");
//	            File fichero2 = new File(Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_orbis.pdf");
//	            File fichero3 = new File(Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_gnbdebito.pdf");
//	            File fichero4 = new File(Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_gnbvales.pdf");
//	            File fichero5 = new File(Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_gnbtoken.pdf");
//	            File fichero6 = new File(Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_revistas.pdf");
//	            File fichero7 = new File(Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_herbalife.pdf");
//	            File fichero8 = new File(Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_bbva.pdf");
//	            
//	            File fichero = new File(Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_pdf.pdf");
				
				File fichero1 = new File(req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_bcp.pdf"));
	            File fichero2 = new File(req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_orbis.pdf"));
	            File fichero3 = new File(req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_gnbdebito.pdf"));
	            File fichero4 = new File(req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_gnbvales.pdf"));
	            File fichero5 = new File(req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_gnbtoken.pdf"));
	            File fichero6 = new File(req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_revistas.pdf"));
	            File fichero7 = new File(req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_herbalife.pdf"));
	            File fichero8 = new File(req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_bbva.pdf"));
	            File fichero9 = new File(req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_orbis_paquete.pdf"));
	            
	            File fichero = new File(req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_pdf.pdf"));

	            if(fichero1.exists()){System.out.println("fichero1.exists()");
	                fichero1.delete();
	            }
	            if(fichero2.exists()){System.out.println("fichero2.exists()");
	                fichero2.delete();
	            }
	            if(fichero3.exists()){System.out.println("fichero3.exists()");
	                fichero3.delete();
	            }
	            if(fichero4.exists()){System.out.println("fichero4.exists()");
	                fichero4.delete();
	            }
	            if(fichero5.exists()){System.out.println("fichero5.exists()");
	                fichero5.delete();
	            }
	            if(fichero6.exists()){System.out.println("fichero6.exists()");
                	fichero6.delete();
	            }
	            if(fichero7.exists()){System.out.println("fichero7.exists()");
            		fichero7.delete();
	            }
	            if(fichero8.exists()){System.out.println("fichero8.exists()");
        			fichero8.delete();
	            }
	            if(fichero9.exists()){System.out.println("fichero9.exists()");
    				fichero9.delete();
	            }
	            if(fichero.exists()){System.out.println("fichero.exists()");
	            	fichero.delete();
	            }

			String codigo = "";
			List<String> bcp=new ArrayList<String>();
			List<String> tebca=new ArrayList<String>();
			List<String> servitebca=new ArrayList<String>();
			List<String> cencosud=new ArrayList<String>();
			List<String> gnb_tarjeta=new ArrayList<String>();
			List<String> gnb_vales=new ArrayList<String>();
			List<String> gnb_token=new ArrayList<String>();
			List<String> commerce=new ArrayList<String>();
			List<String> orbis=new ArrayList<String>();
			List<String> orbis_paquete=new ArrayList<String>();
			List<String> elcomercio=new ArrayList<String>();
			List<String> dataimagenes=new ArrayList<String>();
			List<String> interbank=new ArrayList<String>();
			List<String> herbalife=new ArrayList<String>();
			List<String> afpprofuturo=new ArrayList<String>();
			List<String> bbva=new ArrayList<String>();
			List<String> tevaperu=new ArrayList<String>();
			
			for (int i = 0; i < listaCodigos.size(); i++) {
				System.out.println("codigo barra: "+listaCodigos.get(i));
				Distribucion distribucion= distribucionModel.getDistribucionXCodBar(listaCodigos.get(i));
				if(distribucion!=null){
					if(distribucion.getCodCli().equals("0035") && distribucion.getCodProVal().equals("02")){//BCP TC
						bcp.add(distribucion.getCodBar());
					}
					if(distribucion.getCodCli().equals("0026") && distribucion.getCodProVal().equals("91")){//ORBIS TP
						orbis.add(distribucion.getCodBar());
					}
					if(distribucion.getCodCli().equals("0007") && distribucion.getCodProVal().equals("02")){//GNB TC NATURAL
						gnb_tarjeta.add(distribucion.getCodBar());
					}
					if(distribucion.getCodCli().equals("0007") && distribucion.getCodProVal().equals("89")){//GNB TD REEMBOSO
						gnb_tarjeta.add(distribucion.getCodBar());
					}
					if(distribucion.getCodCli().equals("0007") && distribucion.getCodProVal().equals("60")){//GNB VALES
						gnb_vales.add(distribucion.getCodBar());
					}
					if(distribucion.getCodCli().equals("0007") && distribucion.getCodProVal().equals("50")){//GNB TOKEN
						gnb_token.add(distribucion.getCodBar());
					}
					if(distribucion.getCodCli().equals("0031") && distribucion.getCodProVal().equals("07")){//el comercio
						elcomercio.add(distribucion.getCodBar());
					}
					if(distribucion.getCodCli().equals("0038") && distribucion.getCodProVal().equals("41")){//Herbalife
						herbalife.add(distribucion.getCodBar());
					}
					if(distribucion.getCodCli().equals("0040") && distribucion.getCodProVal().equals("02")){//bbva
						bbva.add(distribucion.getCodBar());
					}
					if(distribucion.getCodCli().equals("0026") && distribucion.getCodProVal().equals("41")){//ORBIS PAQUETE
						orbis_paquete.add(distribucion.getCodBar());
					}
				}

			}

			if(bcp.size()>0){
				//repor = JasperCompileManager.compileReport(Constantes.RUTA_JASPER+"MASIVO_BCP_TC.jrxml");
				repor = JasperCompileManager.compileReport(req.getServletContext().getRealPath("/WEB-INF/jasper/MASIVO_BCP_TC.jrxml"));
				Map<String, Object> parametro = new HashMap<String, Object>();
				parametro.put("codigoss",bcp);
		        JasperPrint jPrint = JasperFillManager.fillReport(repor, parametro,conn);
		        jPrint.setProperty("net.sf.jasperreports.expo rt.character.encoding","ISO-8859-1");
		        //JasperExportManager.exportReportToPdfFile(jPrint,Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_bcp.pdf");
		        JasperExportManager.exportReportToPdfFile(jPrint,req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_bcp.pdf"));
		        System.out.println("creo pdf bcp");
		        mensaje="1";
			}
			if(orbis.size()>0){
				//repor = JasperCompileManager.compileReport(Constantes.RUTA_JASPER+"MASIVO_ORBIS.jrxml");
				repor = JasperCompileManager.compileReport(req.getServletContext().getRealPath("/WEB-INF/jasper/MASIVO_ORBIS.jrxml"));
				Map<String, Object> parametro = new HashMap<String, Object>();
				parametro.put("codigoss",orbis);
		        JasperPrint jPrint = JasperFillManager.fillReport(repor, parametro,conn);
		        jPrint.setProperty("net.sf.jasperreports.expo rt.character.encoding","ISO-8859-1");
		        //JasperExportManager.exportReportToPdfFile(jPrint,Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_orbis.pdf");
		        JasperExportManager.exportReportToPdfFile(jPrint,req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_orbis.pdf"));
		        System.out.println("creo pdf orbis");
		        mensaje="1";
			}
			if(gnb_tarjeta.size()>0){
				//repor = JasperCompileManager.compileReport(Constantes.RUTA_JASPER+"MASIVO_GNB_DEBITO.jrxml");
				repor = JasperCompileManager.compileReport(req.getServletContext().getRealPath("/WEB-INF/jasper/MASIVO_GNB_DEBITO.jrxml"));
				Map<String, Object> parametro = new HashMap<String, Object>();
				parametro.put("codigoss",gnb_tarjeta);
		        JasperPrint jPrint = JasperFillManager.fillReport(repor, parametro,conn);
		        jPrint.setProperty("net.sf.jasperreports.expo rt.character.encoding","ISO-8859-1");
		        //JasperExportManager.exportReportToPdfFile(jPrint,Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_gnbdebito.pdf");
		        JasperExportManager.exportReportToPdfFile(jPrint,req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_gnbdebito.pdf"));
		        System.out.println("creo pdf gnb tarjeta");
		        mensaje="1";
			}
			if(gnb_vales.size()>0){
				//repor = JasperCompileManager.compileReport(Constantes.RUTA_JASPER+"MASIVO_GNB_VALES.jrxml");
				repor = JasperCompileManager.compileReport(req.getServletContext().getRealPath("/WEB-INF/jasper/MASIVO_GNB_VALES.jrxml"));
				Map<String, Object> parametro = new HashMap<String, Object>();
				parametro.put("codigoss",gnb_vales);
		        JasperPrint jPrint = JasperFillManager.fillReport(repor, parametro,conn);
		        jPrint.setProperty("net.sf.jasperreports.expo rt.character.encoding","ISO-8859-1");
		        //JasperExportManager.exportReportToPdfFile(jPrint,Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_gnbvales.pdf");
		        JasperExportManager.exportReportToPdfFile(jPrint,req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_gnbvales.pdf"));
		        System.out.println("creo pdf gnb vales");
		        mensaje="1";
			}
			if(gnb_token.size()>0){
				//repor = JasperCompileManager.compileReport(Constantes.RUTA_JASPER+"MASIVO_GNB_TOKEN.jrxml");
				repor = JasperCompileManager.compileReport(req.getServletContext().getRealPath("/WEB-INF/jasper/MASIVO_GNB_TOKEN.jrxml"));
				Map<String, Object> parametro = new HashMap<String, Object>();
				parametro.put("codigoss",gnb_token);
		        JasperPrint jPrint = JasperFillManager.fillReport(repor, parametro,conn);
		        jPrint.setProperty("net.sf.jasperreports.expo rt.character.encoding","ISO-8859-1");
		        //JasperExportManager.exportReportToPdfFile(jPrint,Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_gnbtoken.pdf");
		        JasperExportManager.exportReportToPdfFile(jPrint,req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_gnbtoken.pdf"));
		        System.out.println("creo pdf gnb token");
		        mensaje="1";
			}
			if(elcomercio.size()>0){
				//repor = JasperCompileManager.compileReport(Constantes.RUTA_JASPER+"MASIVO_REVISTAS.jrxml");
				repor = JasperCompileManager.compileReport(req.getServletContext().getRealPath("/WEB-INF/jasper/MASIVO_REVISTAS.jrxml"));
				Map<String, Object> parametro = new HashMap<String, Object>();
				parametro.put("codigoss",elcomercio);
		        JasperPrint jPrint = JasperFillManager.fillReport(repor, parametro,conn);
		        jPrint.setProperty("net.sf.jasperreports.expo rt.character.encoding","ISO-8859-1");
		        //JasperExportManager.exportReportToPdfFile(jPrint,Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_revistas.pdf");
		        JasperExportManager.exportReportToPdfFile(jPrint,req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_revistas.pdf"));
		        System.out.println("creo pdf revistas");
		        mensaje="1";
			}
			if(herbalife.size()>0){
				//repor = JasperCompileManager.compileReport(Constantes.RUTA_JASPER+"MASIVO_HERBALIFE.jrxml");
				repor = JasperCompileManager.compileReport(req.getServletContext().getRealPath("/WEB-INF/jasper/MASIVO_HERBALIFE.jrxml"));
				Map<String, Object> parametro = new HashMap<String, Object>();
				parametro.put("codigoss",herbalife);
		        JasperPrint jPrint = JasperFillManager.fillReport(repor, parametro,conn);
		        jPrint.setProperty("net.sf.jasperreports.expo rt.character.encoding","ISO-8859-1");
		        //JasperExportManager.exportReportToPdfFile(jPrint,Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_herbalife.pdf");
		        JasperExportManager.exportReportToPdfFile(jPrint,req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_herbalife.pdf"));
		        System.out.println("creo pdf herbalife");
		        mensaje="1";
			}
			if(bbva.size()>0){
				//repor = JasperCompileManager.compileReport(Constantes.RUTA_JASPER+"MASIVO_BBVA.jrxml");
				repor = JasperCompileManager.compileReport(req.getServletContext().getRealPath("/WEB-INF/jasper/MASIVO_BBVA.jrxml"));
				Map<String, Object> parametro = new HashMap<String, Object>();
				parametro.put("codigoss",bbva);
		        JasperPrint jPrint = JasperFillManager.fillReport(repor, parametro,conn);
		        jPrint.setProperty("net.sf.jasperreports.expo rt.character.encoding","ISO-8859-1");
		        //JasperExportManager.exportReportToPdfFile(jPrint,Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_bbva.pdf");
		        JasperExportManager.exportReportToPdfFile(jPrint,req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_bbva.pdf"));
		        System.out.println("creo pdf bbva");
		        mensaje="1";
			}
			if(orbis_paquete.size()>0){
				//repor = JasperCompileManager.compileReport(Constantes.RUTA_JASPER+"MASIVO_ORBIS.jrxml");
				repor = JasperCompileManager.compileReport(req.getServletContext().getRealPath("/WEB-INF/jasper/MASIVO_ORBIS_PAQUETE.jrxml"));
				Map<String, Object> parametro = new HashMap<String, Object>();
				parametro.put("codigoss",orbis_paquete);
		        JasperPrint jPrint = JasperFillManager.fillReport(repor, parametro,conn);
		        jPrint.setProperty("net.sf.jasperreports.expo rt.character.encoding","ISO-8859-1");
		        //JasperExportManager.exportReportToPdfFile(jPrint,Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_orbis.pdf");
		        JasperExportManager.exportReportToPdfFile(jPrint,req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_orbis_paquete.pdf"));
		        System.out.println("creo pdf orbis paquete");
		        mensaje="1";
			}

			PDFMergerUtility pdfunir = new PDFMergerUtility();
			
			if(fichero1.exists()){System.out.println("fichero1.exists()");
				//pdfunir.addSource(Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_bcp.pdf");
				pdfunir.addSource(req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_bcp.pdf"));
	        }
	        if(fichero2.exists()){System.out.println("fichero2.exists()");
	        	//pdfunir.addSource(Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_orbis.pdf");
	        	pdfunir.addSource(req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_orbis.pdf"));
	        }
	        if(fichero3.exists()){System.out.println("fichero3.exists()");
	        	//pdfunir.addSource(Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_gnbdebito.pdf");
	        	pdfunir.addSource(req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_gnbdebito.pdf"));
	        }
	        if(fichero4.exists()){System.out.println("fichero4.exists()");
	        	//pdfunir.addSource(Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_gnbvales.pdf");
	        	pdfunir.addSource(req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_gnbvales.pdf"));
	        }
	        if(fichero5.exists()){System.out.println("fichero5.exists()");
	        	//pdfunir.addSource(Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_gnbtoken.pdf");
	        	pdfunir.addSource(req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_gnbtoken.pdf"));
	        }
	        if(fichero6.exists()){System.out.println("fichero6.exists()");
        		//pdfunir.addSource(Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_revistas.pdf");
        		pdfunir.addSource(req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_revistas.pdf"));
	        }
	        if(fichero7.exists()){System.out.println("fichero7.exists()");
    			//pdfunir.addSource(Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_herbalife.pdf");
    			pdfunir.addSource(req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_herbalife.pdf"));
	        }
	        if(fichero8.exists()){System.out.println("fichero8.exists()");
				//pdfunir.addSource(Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_bbva.pdf");
				pdfunir.addSource(req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_bbva.pdf"));
	        }
	        if(fichero9.exists()){System.out.println("fichero9.exists()");
			//pdfunir.addSource(Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_bbva.pdf");
				pdfunir.addSource(req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_orbis_paquete.pdf"));
	        }
	        //pdfunir.setDestinationFileName(Constantes.RUTA_PDF+"hojaruta\\cargos\\masivo_pdf.pdf");
	        pdfunir.setDestinationFileName(req.getServletContext().getRealPath("/pdf/hojaruta/cargos/masivo_pdf.pdf"));
	        
	        try {
	        	try {
	        		System.out.println("ENTROOOOOOOOOOO");
					pdfunir.mergeDocuments();
				} catch (COSVisitorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        } catch (IOException ioe) {
	        	ioe.toString();
	        }

		}catch(JRException ex){
            System.out.println(ex.toString());
        }
		return mensaje;
	}
	
	@RequestMapping(value={"/cargar_productos.htm"})
	public void cargarProductos(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req, HttpServletResponse response,
			@RequestParam("codCliente") String codCliente){
		response.setContentType("application/json");
        JSONObject outPut = new JSONObject(); 
		try{  
			String  selectProductos="";
			user_load(model);
			List<Valorado> valorado = distribucionModel.getProductoXCliente(codCliente);
			System.out.println("LISTA PRODUCTOS:"+valorado.size());
			model.addAttribute("valorado", valorado);

	       for(Valorado val : valorado){
		       selectProductos+=getHTMLDinamicOption(val.getCodTipo().getCodTipo(), val.getNombre());
	       }
	       outPut.put("selectProductos", selectProductos);
	       response.getWriter().write(outPut.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value={"/listar_coordinaciones.htm"})
	public void listaRuta(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req, HttpServletResponse response,
			@RequestParam("fec") String fec){
		response.setContentType("application/json");
        JSONObject outPut = new JSONObject(); 
        System.out.println("fecha: "+fec);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String mensaje="";
        String tablita="";
        Date convertedDate = new Date();
		try{  
			user_load(model);
			if(fec.equals("")){
				mensaje="Ingrese fecha";
			}

			if(mensaje.equals("")){
				
			convertedDate = dateFormat.parse(fec);
			List<Object> coordinaciones =distribucionModel.listaCoordinacionesFecha(convertedDate);
			System.out.println("LISTA RUTAS:"+coordinaciones.size());
			
				if(coordinaciones.size()>0){
				tablita=getHTMLTablaCoordinaciones(coordinaciones);
				outPut.put("tablita", tablita);
		       }else{
		    	outPut.put("tablita", "No hay coordinaciones");
		       }
			}else{
				
			}
	       
	       response.getWriter().write(outPut.toString());
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	public static String getHTMLTablaCoordinaciones(List<Object> listaCoordinaciones){
        String outPut = 
                            "<table id=\"table-consulta-2\" >" +
                                "<thead>" +
                                    "<tr>" +
                                        "<td width=\"300\">Cliente</td>" +
                                        "<td width=\"400\">Documento</td>" +
                                        "<td>Total</td>" +
                                        "<td>Entregadas</td>" +
                                        "<td>Diferencia</td>" +
                                    "</tr>" +
                                "</thead>" +
                                "<tbody>";
        Iterator itr = listaCoordinaciones.iterator();
        while(itr.hasNext()){
           Object[] obj = (Object[]) itr.next();           
           outPut +=    "<tr class='<c:if test=\"${rowCounter.count % 2 == 0}\">on</c:if>'>";
           					if(obj[2].toString().equals("Total")){
           						outPut +=   "<td bgcolor=\"#ccf2ff\">"+obj[1]+"</td>"+
           									"<td bgcolor=\"#ccf2ff\">"+obj[2]+"</td>"+
	           								"<td bgcolor=\"#ccf2ff\">"+obj[4]+"</td>" +
	           	                            "<td bgcolor=\"#ccf2ff\">"+obj[3]+"</td>" +
	           	                            "<td bgcolor=\"#ccf2ff\">"+obj[5]+"</td>";
           					}else{
           						outPut +=   "<td>"+obj[1]+"</td>"+
	       									"<td>"+obj[2]+"</td>"+
	           								"<td>"+obj[4]+"</td>" +
	           	                            "<td>"+obj[3]+"</td>" +
	           	                            "<td>"+obj[5]+"</td>";
           					}
           					outPut +="</tr>";          
        }         
                outPut +=       "</tbody>" +
                            "</table>";
         return outPut;
    }
	
	public void user_load(Model model){
		model.addAttribute("RPAdminUserPerfiles", perfilModel.findAll());
		model.addAttribute("RPAdminUserUsuarios", usuarioModel.findAll());
		model.addAttribute("RPAdminUserClientes", distribucionModel.getClientes());
	}
	
	public static String getHTMLDinamicOption(String value, String descripcion){
        return  "<option value=\""+value+"\" >"+descripcion+"</option>";
    }
}
