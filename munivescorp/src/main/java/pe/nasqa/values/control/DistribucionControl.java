package pe.nasqa.values.control;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.persistence.Column;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BarcodeInter25;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.ImageEncodeParam;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.JPEGEncodeParam;
import com.sun.media.jai.codec.PNGEncodeParam;
import com.sun.media.jai.codec.SeekableStream;
import com.sun.media.jai.codec.TIFFDecodeParam;

import pe.dataimagenes.utils.Constantes;
import pe.dataimagenes.utils.PDFUtil;
import pe.nasqa.values.dao.DistribucionDao;
import pe.nasqa.values.model.CargaImgModel;
import pe.nasqa.values.model.CoordinacionModel;
import pe.nasqa.values.model.CoordinacionTelfModel;
import pe.nasqa.values.model.DistribucionModel;
import pe.nasqa.values.model.HojaRutaDetalleModel;
import pe.nasqa.values.model.HojaRutaModel;
import pe.nasqa.values.model.ImpExpDbModel;
import pe.nasqa.values.model.MUtilReportFiles;
import pe.nasqa.values.model.RendicionDetalleModel;
import pe.nasqa.values.model.RendicionModel;
import pe.nasqa.values.model.entity.ChoiceBean;
import pe.nasqa.values.model.entity.Cliente;
import pe.nasqa.values.model.entity.CordinacionPDF;
import pe.nasqa.values.model.entity.Distribucion;
import pe.nasqa.values.model.entity.DistribucionCoord;
import pe.nasqa.values.model.entity.DistribucionCoordTelf;
import pe.nasqa.values.model.entity.DistribucionPaquete;
import pe.nasqa.values.model.entity.DistribucionVisita;
import pe.nasqa.values.model.entity.Estados;
import pe.nasqa.values.model.entity.ExportBaseCourier;
import pe.nasqa.values.model.entity.ExportVisita;
import pe.nasqa.values.model.entity.HojaRuta;
import pe.nasqa.values.model.entity.HojaRutaDetalle;
import pe.nasqa.values.model.entity.Menu;
import pe.nasqa.values.model.entity.OrdenProceso;
import pe.nasqa.values.model.entity.OrdenProcesoAvance;
import pe.nasqa.values.model.entity.OrdenProcesoCargos;
import pe.nasqa.values.model.entity.RegistroCoord;
import pe.nasqa.values.model.entity.RegistroCoordTelf;
import pe.nasqa.values.model.entity.Rendicion;
import pe.nasqa.values.model.entity.ReporteBBVAentr;
import pe.nasqa.values.model.entity.ReporteBBVApend;
import pe.nasqa.values.model.entity.ReporteBBVAworkf;
import pe.nasqa.values.model.entity.ReporteBCPdetalleGen;
import pe.nasqa.values.model.entity.ReporteBCPtc;
import pe.nasqa.values.model.entity.ReporteDistribucionJoin;
import pe.nasqa.values.model.entity.ReporteEnvioProv;
import pe.nasqa.values.model.entity.ReporteCoordinacionDia;

import pe.nasqa.values.model.entity.ReporteGNB;
import pe.nasqa.values.model.entity.ReporteGNBtoken;
import pe.nasqa.values.model.entity.ReporteGNBvales;
import pe.nasqa.values.model.entity.ReporteHerbalife;
import pe.nasqa.values.model.entity.ReporteOrbinGestion;
import pe.nasqa.values.model.entity.ReporteRevistas;
import pe.nasqa.values.model.entity.Usuario;
import pe.nasqa.values.model.entity.Valorado;

@Controller
@RequestMapping(value="/distribucion")

@SessionAttributes({"USUARIO_INFO","USUARIO_TIPO","SAConsultaDetalle","ID_CLIENTE"})
public class DistribucionControl {
	
	@Autowired
	private DistribucionModel distribucionModel;
	
	@Autowired
	private CoordinacionModel coordinacionModel;
	
	@Autowired
	private CoordinacionTelfModel coordinacionTelfModel;
	
	@Autowired
	private CargaImgModel cargaImgModel;
	
	@Autowired
	private HojaRutaDetalleModel hojaRutaDetalleModel;
	
	@Autowired
	private HojaRutaModel hojaRutaModel;
	
	@Autowired
	private RendicionModel rendicionModel;
	
	@Autowired
	private RendicionDetalleModel rendicionDetalleModel;
	
	@Autowired
	ImpExpDbModel impExpDbModel;
	
	@Autowired
	ImpExpDbZip zip;
	
	private Logger log = Logger.getLogger(DistribucionControl.class);
	//private Document document;
	
	@RequestMapping(value={"/index.htm"}) 
	public String index(Model model, HttpServletRequest req){
		System.out.println("ENTRO: "+req.getParameter("reporte"));
		String view="distribucion/consulta_form";
		
		if(req.getParameter("reporte")!=null){
			model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
			model.addAttribute("menuSelect", Menu.C1.name());
			view="distribucion/reporte_estado";
		}else if(req.getParameter("carga")!=null){
			model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.L1.name()));
			model.addAttribute("menuSelect", Menu.L1.name());
			view="distribucion/carga_gestion_form";	
		}else if(req.getParameter("descarga")!=null){			
			model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.L1.name()));
			model.addAttribute("menuSelect", Menu.L3.name());
			view="distribucion/descargar_cordinaciones";				
		}else if(req.getParameter("descarga_base_courier")!=null){			
			model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.L1.name()));
			model.addAttribute("menuSelect", Menu.L4.name());
			view="distribucion/descargar_base_courier";	
			
		}else{
			
			model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.B1.name()));
			model.addAttribute("menuSelect", Menu.B1.name());
			view="distribucion/consulta_form";		
			
		}
		
		return view;
	}
	
	@RequestMapping(value={"/consulta_buscar.htm"}) 
	public String buscar(Model model, HttpServletRequest req, 
			@ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
			@RequestParam("codBar") String codBar, 
			@RequestParam("nroRef") String nroRef, 
			@RequestParam("docIde") String docIde, 
			@RequestParam("nomDes") String nomDes){
		
		String view = "distribucion/consulta_form";
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.B1.name()));
		model.addAttribute("menuSelect", Menu.B1.name());
		
		String tipoBuscar = req.getParameter("tipoBuscar")==null?"0":req.getParameter("tipoBuscar");
		
		model.addAttribute("codBar", codBar);
		model.addAttribute("nroRef", nroRef);
		model.addAttribute("docIde", docIde);
		model.addAttribute("nomDes", nomDes);
		model.addAttribute("tipoBuscar", tipoBuscar);
		
		int totalPaquetes = 0;
		if(codBar.length()>0 || nroRef.length()>0 || docIde.length()>0 || nomDes.length()>2){
			
			String codCliente=null;
			if(usuarioInfo.getTipo().equals(CVConstante.USUARIO_TIPO_CLIENTE)){
				codCliente = usuarioInfo.getIdCliente();				
			}
			if(tipoBuscar.equals("0")){
				List<Distribucion> listaResultado = distribucionModel.findDistCodName(codBar, nroRef, docIde, nomDes, codCliente);
				if(listaResultado!=null){
					if(listaResultado.size()>1){
						model.addAttribute("resultadoBusqueda", listaResultado);
					}else if(listaResultado.size()==1){
						model.addAttribute("codBar", codBar);
						model.addAttribute("menuSelect", Menu.B2.name());
						model.addAttribute("SAConsultaDetalle", listaResultado.get(0));
						
						List<DistribucionVisita> distribucionVisitas = distribucionModel.findVisitas(listaResultado.get(0).getCodBar());
						List<DistribucionPaquete> distribucionPaquetes = distribucionModel.findPaquetes(listaResultado.get(0).getCodBar());
						int cantPaq = 0;
						for(DistribucionPaquete disPa : distribucionPaquetes ){
							cantPaq = 0;
							try{
								if(!disPa.getTlfRef().equals("")){
									cantPaq = Integer.parseInt(disPa.getTlfRef());
								}								
								System.out.println("disPa:"+disPa.getTlfRef());
							}catch(NumberFormatException n){
								cantPaq = 0;
							}
							totalPaquetes = totalPaquetes + cantPaq;
						}
						model.addAttribute("totalPaquetes", totalPaquetes);						
						model.addAttribute("RPConsultaDetalleVisitas", distribucionVisitas);
						model.addAttribute("RPConsultaDetallePaquetes", distribucionPaquetes);
						model.addAttribute("RPConsultaDetalleCoordEntregado", distribucionModel.findDireccionEntrega(distribucionVisitas));
						/**23/05/2016_czavalacas**/
						List<String> imagenesName = new ArrayList<String>();
						if(listaResultado.get(0).getImgCarNom()!=null){
							System.out.println("listaResultado.get(0).getImgCarNom():"+listaResultado.get(0).getImgCarNom());
							String ruta = cargaImgModel.cargoBuscarPathBase(listaResultado.get(0).getImgCarNom());
							System.out.println("--ruta:"+ruta);
							if(ruta!=null){
							File carpetaOrigen = new File(ruta);
							System.out.println("--carpetaOrigen:"+carpetaOrigen.getAbsolutePath());
							String nameFile = listaResultado.get(0).getImgCarNom();
							if(!nameFile.equals(""))
								System.out.println("null.....");
								imagenesName =  getFilesByname(carpetaOrigen, nameFile);
							}else{
								System.out.println("else null.....");
								imagenesName.add("");
							}
							System.out.println("RUTA BASE DE CARGO:"+ruta+"-");
						}
						model.addAttribute("codBarImgs", imagenesName);
						/******************************/
						if(listaResultado.get(0).getIndSit().equals("2")){//2 = ENTREGADO
							model.addAttribute("canEdit", "1");//NO SE PEUDE EDITAR ES ENTREGADO
						}else{
							model.addAttribute("canEdit", "");//NO SE PEUDE EDITAR ES ENTREGADO
						}
						//view = "redirect:/distribucion/consulta_detalle.htm";
						view = "distribucion/consulta_detalle";
					}
				}
			}else if(tipoBuscar.equals("PQ")){
				List<DistribucionPaquete> listaResultadoPaquete = distribucionModel.findPaqueteCodName(nroRef, nomDes, codCliente);
				if(listaResultadoPaquete!=null){
					if(listaResultadoPaquete.size()>1){
						model.addAttribute("resultadoBusquedaPaquete", listaResultadoPaquete);
					}else if(listaResultadoPaquete.size()==1){
						Distribucion distribucion = distribucionModel.getByCodBar(listaResultadoPaquete.get(0).getCodBar(), codCliente);
						model.addAttribute("codBar", codBar);
						model.addAttribute("idPaquete", listaResultadoPaquete.get(0).getId());
						model.addAttribute("menuSelect", Menu.B2.name());
						model.addAttribute("SAConsultaDetalle", distribucion);
						
						List<DistribucionVisita> distribucionVisitas = distribucionModel.findVisitas(distribucion.getCodBar());
						model.addAttribute("RPConsultaDetalleVisitas", distribucionVisitas);
						model.addAttribute("RPConsultaDetallePaquetes", listaResultadoPaquete.get(0));
						model.addAttribute("RPConsultaDetalleCoordEntregado", distribucionModel.findDireccionEntrega(distribucionVisitas));
						
						model.addAttribute("RPConsultaDetallePaquetes", listaResultadoPaquete);						
						/**23/05/2016_czavalacas**/
						List<String> imagenesName = new ArrayList<String>();
						if(distribucion.getImgCarNom()!=null){
							System.out.println("listaResultado.get(0).getImgCarNom():"+distribucion.getImgCarNom());
							String ruta = cargaImgModel.cargoBuscarPathBase(distribucion.getImgCarNom());
							System.out.println("--ruta:"+ruta);
							if(ruta!=null){
							File carpetaOrigen = new File(ruta);
							System.out.println("--carpetaOrigen:"+carpetaOrigen.getAbsolutePath());
							String nameFile = distribucion.getImgCarNom();
							if(!nameFile.equals(""))
								System.out.println("null.....");
								imagenesName =  getFilesByname(carpetaOrigen, nameFile);
							}else{
								System.out.println("else null.....");
								imagenesName.add("");
							}
							System.out.println("RUTA BASE DE CARGO:"+ruta+"-");
						}
						model.addAttribute("codBarImgs", imagenesName);
						/******************************/
						if(distribucion.getIndSit().equals("2")){//2 = ENTREGADO
							model.addAttribute("canEdit", "1");//NO SE PEUDE EDITAR ES ENTREGADO
						}else{
							model.addAttribute("canEdit", "");//NO SE PEUDE EDITAR ES ENTREGADO
						}
						//view = "redirect:/distribucion/consulta_detalle.htm";
						view = "distribucion/consulta_detalle";
					}
				}
			}
			
			
		}
		return view;
	}
	
	@RequestMapping(value={"/download_report_gnb.htm"}, method = RequestMethod.GET)
	public String download_report_gnb(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C4.name());
		return "impexpdb/descarga_reporte_gnb";
	}
	

	@RequestMapping(value={"/download_report.htm"} )
	public String download_report_gnb(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response){
		System.out.println("-------download_reporte-------");
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C4.name());
		String view="impexpdb/descarga_reporte_gnb";
		try {			
			String fec_inicio = request.getParameter("fromDate");
			String fec_fin = request.getParameter("toDate");			
			List<ReporteGNB> listaVisita = impExpDbModel.getReporteGNB(fec_inicio, fec_fin);			
			if(listaVisita.size() > 0){
				System.out.println("--"+fec_inicio);
				System.out.println("--"+fec_fin);
				final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
				final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "Reporte_vales_"+System.currentTimeMillis()+".xls";
				String path = temperotyFilePath+File.separator;
				System.out.println(path);
				
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition","attachment;filename="+fileName);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				
				MUtilReportFiles reportFiles = new MUtilReportFiles();
				reportFiles.createExcelReporteValesGNB(path, fileName, listaVisita, response.getOutputStream());
			
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		System.out.println("Done");
		return view;
	}	
	
	//--------------------------- CP  30-11-16 ------------
	@RequestMapping(value={"/download_report_bcptc.htm"}, method = RequestMethod.GET)
	public String download_report_bcptc(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C6.name());
		return "impexpdb/descarga_reporte_bcptc";
	}
	
	@RequestMapping(value={"/download_reportbcptc.htm"} )
	public String download_report_bcptc(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response){
		System.out.println("-------download_reporte-------");
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C6.name());
		String view="impexpdb/descarga_reporte_bcptc";
		try {			
			String fec_inicio = request.getParameter("fromDate");
			String fec_fin = request.getParameter("toDate");			
			List<ReporteBCPtc> listaVisita = impExpDbModel.getReporteBCPtc(fec_inicio, fec_fin);			
			if(listaVisita.size() > 0){
				System.out.println("--"+fec_inicio);
				System.out.println("--"+fec_fin);
				final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
				final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "Reporte_bcptc_"+System.currentTimeMillis()+".xls";
				String path = temperotyFilePath+File.separator;
				System.out.println(path);
				
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition","attachment;filename="+fileName);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				
				MUtilReportFiles reportFiles = new MUtilReportFiles();
				reportFiles.createExcelReporteBCPtc(path, fileName, listaVisita, response.getOutputStream());
			
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		System.out.println("Done");
		return view;
	}	


	@RequestMapping(value={"/download_report_herbalife.htm"}, method = RequestMethod.GET)
	public String download_report_herbalife(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C7.name());
		return "impexpdb/descarga_reporte_herbalife";
	}
	
	@RequestMapping(value={"/download_reportherbalife.htm"} )
	public String download_report_herbalife(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response){
		System.out.println("-------download_reporte-------");
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C7.name());
		String view="impexpdb/descarga_reporte_herbalife";
		try {			
			String fec_inicio = request.getParameter("fromDate");
			String fec_fin = request.getParameter("toDate");			
			List<ReporteHerbalife> listaVisita = impExpDbModel.getReporteHerbalife(fec_inicio, fec_fin);			
			if(listaVisita.size() > 0){
				System.out.println("--"+fec_inicio);
				System.out.println("--"+fec_fin);
				final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
				final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "Reporte_herbalife_"+System.currentTimeMillis()+".xls";
				String path = temperotyFilePath+File.separator;
				System.out.println(path);
				
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition","attachment;filename="+fileName);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				
				MUtilReportFiles reportFiles = new MUtilReportFiles();
				reportFiles.createExcelReporteHerbalife(path, fileName, listaVisita, response.getOutputStream());
			
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		System.out.println("Done");
		return view;
	}	




	@RequestMapping(value={"/download_report_bbvapend.htm"}, method = RequestMethod.GET)
	public String download_report_bbvapend(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C12.name());
		return "impexpdb/descarga_reporte_bbvapend";
	}
	
	@RequestMapping(value={"/download_reportbbvapendXls.htm"} )
	public String download_reportbbvapendXls(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response){
		System.out.println("-------download_reporte-------");
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C12.name());
		String view="impexpdb/descarga_reporte_bbvapend";
		try {			
			String fec_inicio = request.getParameter("fromDate");
			String fec_fin = request.getParameter("toDate");			
			List<ReporteBBVApend> listaVisita = impExpDbModel.getReporteBBVApend(fec_inicio, fec_fin);			
			if(listaVisita.size() > 0){
				System.out.println("--"+fec_inicio);
				System.out.println("--"+fec_fin);
				final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
				final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "Reporte_pendiente_"+System.currentTimeMillis()+".xls";
				String path = temperotyFilePath+File.separator;
				System.out.println(path);
				
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition","attachment;filename="+fileName);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				
				model.addAttribute("finall", "true");
				model.addAttribute("msg", "Carga Satisfactoria...");
				System.out.println("Carga satisfactoria..............");
				
				MUtilReportFiles reportFiles = new MUtilReportFiles();
				reportFiles.createExcelReporteBBVApend(path, fileName, listaVisita, response.getOutputStream());
				
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		System.out.println("Done bcp pendiente");
		
	
		
		return view;
	}	


	@RequestMapping(value={"/download_report_bbvaentr.htm"}, method = RequestMethod.GET)
	public String download_report_bbvaentr(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C13.name());
		return "impexpdb/descarga_reporte_bbvaentr";
	}
	
	@RequestMapping(value={"/download_reportbbvaentr.htm"} )
	public String download_report_bbvaentr(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response){
		System.out.println("-------download_reporte-------");
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C13.name());
		String view="impexpdb/descarga_reporte_bbvaentr";

		try {			
			String fec_inicio = request.getParameter("fromDate");
			String fec_fin = request.getParameter("toDate");
			String reDescargar = request.getParameter("checkDescargado")==null?"NO":"SI";
			List<ReporteBBVAentr> listaR = impExpDbModel.getReporteBBVAentr(fec_inicio, fec_fin);			

			
//			List<ExportVisita> listaVisita = impExpDbModel.getVisitasFromUI(CVDinamico.getDateFromString(fec_inicio, "dd/MM/yyyy"), CVDinamico.getDateFromString(fec_fin, "dd/MM/yyyy"),reDescargar);
			if(listaR.size() > 0){
				System.out.println("--"+fec_inicio);
				System.out.println("--"+fec_fin);
				final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
				final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "Export_BBVAEntre_"+System.currentTimeMillis()+".txt";
				String path = temperotyFilePath+File.separator+fileName;
				System.out.println("SIZE :"+listaR.size());
				System.out.println("path :"+path);
				
				response.setContentType("text/plain");
				response.setHeader("Content-Disposition","attachment;filename="+fileName);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				System.out.println("-------------download TXT----------");
				
			
				createTXTBBVAentr(listaR, path);
				java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
				baos = convertPDFToByteArrayOutputStream(path);
				OutputStream os = response.getOutputStream();
		        baos.writeTo(os);
		        os.flush();
				File file = new File(path);
				file.delete();
				
			//	for(ReporteBBVAworkf  visi : listaVisita){
				//	impExpDbModel.updateVisita(visi.getId_visita(), "0");// 0 = Ya fue Descargado
		//		}
				os.close();
				System.out.println("-------------download os close----------");
				response.getOutputStream().flush();
				response.getOutputStream().close();
				System.out.println("-------------download getout close----------");
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		System.out.println("Done");
		return view;

		
		/*
		try {			
			String fec_inicio = request.getParameter("fromDate");
			String fec_fin = request.getParameter("toDate");			
			List<ReporteBBVAentr> listaVisita = impExpDbModel.getReporteBBVAentr(fec_inicio, fec_fin);			
			if(listaVisita.size() > 0){
				System.out.println("--"+fec_inicio);
				System.out.println("--"+fec_fin);
				final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
				final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "Reporte_entrega_"+System.currentTimeMillis()+".xls";
				String path = temperotyFilePath+File.separator;
				System.out.println(path);
				
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition","attachment;filename="+fileName);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				
				MUtilReportFiles reportFiles = new MUtilReportFiles();
				reportFiles.createExcelReporteBBVAentr(path, fileName, listaVisita, response.getOutputStream());
			
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		System.out.println("Done");
		return view;
		*/
		
	}	


	@RequestMapping(value={"/download_report_enviosprov.htm"}, method = RequestMethod.GET)
	public String download_report_enviosprov(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C15.name());
		return "impexpdb/descarga_reporte_envioprov";
	}
	
	@RequestMapping(value={"/download_reportenvioprov.htm"} )
	public String download_report_enviosprov(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response){
		System.out.println("-------download_reporte-------");
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C15.name());
		String view="impexpdb/descarga_reporte_envioprov";
		try {			
			String fec_inicio = request.getParameter("fromDate");
			String fec_fin = request.getParameter("toDate");			
			List<ReporteEnvioProv> listaVisita = impExpDbModel.getReporteEnvioProv(fec_inicio, fec_fin);			
			if(listaVisita.size() > 0){
				System.out.println("--"+fec_inicio);
				System.out.println("--"+fec_fin);
				final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
				final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "Reporte_envioprov_"+System.currentTimeMillis()+".xls";
				String path = temperotyFilePath+File.separator;
				System.out.println(path);
				
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition","attachment;filename="+fileName);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				
				MUtilReportFiles reportFiles = new MUtilReportFiles();
				reportFiles.createExcelReporteEnvioProv(path, fileName, listaVisita, response.getOutputStream());
			
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		System.out.println("Done");
		return view;
	}	


	@RequestMapping(value={"/download_report_coorddia.htm"}, method = RequestMethod.GET)
	public String download_report_coorddia(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C16.name());
		return "impexpdb/descarga_reporte_coorddia";
	}
	
	@RequestMapping(value={"/download_reportcoorddia.htm"} )
	public String download_report_coorddia(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response){
		System.out.println("-------download_reporte-------");
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C16.name());
		String view="impexpdb/descarga_reporte_envioprov";
		try {			
			String fec_inicio = request.getParameter("fromDate");
			String fec_fin = request.getParameter("toDate");			
			List<ReporteCoordinacionDia> listaVisita = impExpDbModel.getReporteCoordinacionDia(fec_inicio, fec_fin);			
			if(listaVisita.size() > 0){
				System.out.println("--"+fec_inicio);
				System.out.println("--"+fec_fin);
				final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
				final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "Reporte_coorddia_"+System.currentTimeMillis()+".xls";
				String path = temperotyFilePath+File.separator;
				System.out.println(path);
				
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition","attachment;filename="+fileName);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				
				MUtilReportFiles reportFiles = new MUtilReportFiles();
				reportFiles.createExcelReporteCoordincionDia(path, fileName, listaVisita, response.getOutputStream());
			
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		System.out.println("Done");
		return view;
	}	


	

	
	public File createTXTBBVAentr(List<ReporteBBVAentr> listaR,String path){
		 File f = new File(path);//RUTA DONDE GUARDAR EL TXT CON LA RELACION DE ARCHIVOS FALLADOS     
        try{                
               FileWriter w = new FileWriter(f,true);
               BufferedWriter bw = new BufferedWriter(w);
               PrintWriter wr = new PrintWriter(bw);     
               for(ReporteBBVAentr visita : listaR){
                   wr.println(
                		   
                		   ((visita.getCval_nro_ide()!=null) ? String.format("%1$-20s",visita.getCval_nro_ide().trim())  : "")+
                				   ((visita.getCtit_nom_ape()!=null) ? String.format("%1$-54s",visita.getCtit_nom_ape()) : "")+
                				   ((visita.getCcodclitit()!=null) ? String.format("%1$-8s",visita.getCcodclitit().trim()) : "")+
                				   ((visita.getCtit_num_doc()!=null) ? String.format("%1$-12s", visita.getCtit_num_doc().trim()) : "")+
                				   ((visita.getCval_fec_emi()!=null) ? String.format("%1$-12s",converFecDDmmYY(visita.getCval_fec_emi())) : "")+
                				   ((visita.getCfec_pro()!=null) ? String.format("%1$-12s",converFecDDmmYY(visita.getCfec_pro())) : "")+
                				   ((visita.getCfech_entr_ult_ges()!=null) ?String.format("%1$-12s", converFecDDmmYY(visita.getCfech_entr_ult_ges().trim())) : "")+
                				   ((visita.getCnroctt()!=null) ?String.format("%1$-20s", visita.getCnroctt().trim()) : "")+
                				   ((visita.getClocalidad()!=null) ? String.format("%1$-2s",visita.getClocalidad().trim()) : "")+
                				   ((visita.getCcodigo_situacion()!=null) ? String.format("%1$-3s",visita.getCcodigo_situacion().trim()) : "")+
                				   ((visita.getCind_act()!=null) ? String.format("%1$-3s",visita.getCind_act().trim()) : "")+
                				   ((visita.getCindval()!=null) ? String.format("%1$-2s",visita.getCindval().trim()) : "")+
                				   ((visita.getCindtjtbco()!=null) ? String.format("%1$-2s",visita.getCindtjtbco().trim()) : "")+
                				   ((visita.getClugarentr()!=null) ? String.format("%1$-2s",visita.getClugarentr().trim()) : "")+
                				   ((visita.getCofiges()!=null) ? String.format("%1$-4s",visita.getCofiges().trim()) : "")+
                				   ((visita.getCtiptjttit()!=null) ? String.format("%1$-4s",visita.getCtiptjttit().trim()) : "")+
                				   ((visita.getCcodref()!=null) ? String.format("%1$-8s",visita.getCcodref().trim()) : "")+
                				   ((visita.getCnrorcc()!=null) ? String.format("%1$-20s",visita.getCnrorcc().trim ()) : "")+
                				   
                				   ((visita.getCcodresul1()!=null) ? String.format("%1$-3s",visita.getCcodresul1().trim()) : "")+
                				   ((visita.getCcodresul2()!=null) ? String.format("%1$-3s",visita.getCcodresul2().trim()) : "")+
                				   
                				   ((visita.getCval_tip()!=null) ? String.format("%1$-1s",visita.getCval_tip().trim()) : "")+
                				   ((visita.getChora_ult()!=null) ? String.format("%1$-6s",visita.getChora_ult().trim()) : "")+
                				   ((visita.getCtip_carpoder()!=null) ? String.format("%1$-1s",visita.getCtip_carpoder().trim()) : "")+
                				   ((visita.getCindfast()!=null) ? String.format("%1$-1s",visita.getCindfast().trim()) : "")+
                				   ((visita.getCbascodubi()!=null) ? String.format("%1$-8s",visita.getCbascodubi().trim()) : "")+
                				   ((visita.getCaltcodubi()!=null) ? String.format("%1$-8s",visita.getCaltcodubi().trim()) : "")+
                				   ((visita.getCnroval()!=null) ? String.format("%1$-2s",visita.getCnroval().trim()) : "")
                   		
                		   )
                   		
                   		;
                   
               }                
               wr.close();
               bw.close();                
        }catch(IOException e){
               e.printStackTrace();
        }
        return f;
	}

	
	
	@RequestMapping(value={"/download_report_bbvaworkf.htm"}, method = RequestMethod.GET)
	public String download_report_bbvaworkf(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C14.name());
		return "impexpdb/descarga_reporte_bbvaworkf";
	}
	
	@RequestMapping(value={"/download_reportbbvaworkf.htm"} )
	public String download_report_bbvaworkf(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response){
		System.out.println("-------download_reporte-------");
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C14.name());
		String view="impexpdb/descarga_reporte_bbvaworkf";
	
		try {			
			String fec_inicio = request.getParameter("fromDate");
			String fec_fin = request.getParameter("toDate");
			String reDescargar = request.getParameter("checkDescargado")==null?"NO":"SI";
			List<ReporteBBVAworkf> listaVisita = impExpDbModel.getReporteBBVAworkf(fec_inicio, fec_fin);			

			
//			List<ExportVisita> listaVisita = impExpDbModel.getVisitasFromUI(CVDinamico.getDateFromString(fec_inicio, "dd/MM/yyyy"), CVDinamico.getDateFromString(fec_fin, "dd/MM/yyyy"),reDescargar);
			if(listaVisita.size() > 0){
				System.out.println("--"+fec_inicio);
				System.out.println("--"+fec_fin);
				final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
				final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "Export_BBVAWorkf_"+System.currentTimeMillis()+".txt";
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
				
			//	for(ReporteBBVAworkf  visi : listaVisita){
				//	impExpDbModel.updateVisita(visi.getId_visita(), "0");// 0 = Ya fue Descargado
		//		}
				os.close();
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		System.out.println("Done");
		return view;
	}	
	

	public File createTXTVisitas(List<ReporteBBVAworkf > listaVisita,String path){
		 File f = new File(path);//RUTA DONDE GUARDAR EL TXT CON LA RELACION DE ARCHIVOS FALLADOS     
        try{                
               FileWriter w = new FileWriter(f,true);
               BufferedWriter bw = new BufferedWriter(w);
               PrintWriter wr = new PrintWriter(bw);     
               for(ReporteBBVAworkf visita : listaVisita){
                   wr.println(
                		   
                		   ((visita.getCval_nro_ide()!=null) ? String.format("%1$-20s",visita.getCval_nro_ide().trim())  : "")+
                				   ((visita.getCtit_nom_ape()!=null) ? String.format("%1$-54s",visita.getCtit_nom_ape()) : "")+
                				   ((visita.getCcodclitit()!=null) ? String.format("%1$-8s",visita.getCcodclitit().trim()) : "")+
                				   ((visita.getCtit_num_doc()!=null) ? String.format("%1$-12s", visita.getCtit_num_doc().trim()) : "")+
                				   ((visita.getCval_fec_emi()!=null) ? String.format("%1$-12s",converFecDDmmYY(visita.getCval_fec_emi())) : "")+
                				   ((visita.getCval_fec_pro()!=null) ? String.format("%1$-12s",converFecDDmmYY(visita.getCval_fec_pro())) : "")+
                				   ((visita.getCfec_ultima_visita()!=null) ?String.format("%1$-12s", converFecDDmmYY(visita.getCfec_ultima_visita().trim())) : "")+
                				   ((visita.getCnroctt()!=null) ?String.format("%1$-20s", visita.getCnroctt().trim()) : "")+
                				   ((visita.getClocalidad()!=null) ? String.format("%1$-2s",visita.getClocalidad().trim()) : "")+
                				   ((visita.getCcodigo_situacion()!=null) ? String.format("%1$-3s",visita.getCcodigo_situacion().trim()) : "")+
                				   ((visita.getCdetsit()!=null) ? String.format("%1$-2s",visita.getCdetsit().trim()) : "")+
                				   ((visita.getCindsit()!=null) ? String.format("%1$-3s",visita.getCindsit().trim()) : "")+
                				   ((visita.getCindval()!=null) ? String.format("%1$-2s",visita.getCindval().trim()) : "")+
                				   ((visita.getCindtjtbco()!=null) ? String.format("%1$-2s",visita.getCindtjtbco().trim()) : "")+
                				   ((visita.getClugarentr()!=null) ? String.format("%1$-2s",visita.getClugarentr().trim()) : "")+
                				   ((visita.getCofiges()!=null) ? String.format("%1$-4s",visita.getCofiges().trim()) : "")+
                				   ((visita.getCtiptjttit()!=null) ? String.format("%1$-4s",visita.getCtiptjttit().trim()) : "")+
                				   ((visita.getCcodref()!=null) ? String.format("%1$-8s",visita.getCcodref().trim()) : "")+
                				   ((visita.getCnrorcc()!=null) ? String.format("%1$-20s",visita.getCnrorcc().trim ()) : "")+
                				   ((visita.getCval_tip()!=null) ? String.format("%1$-1s",visita.getCval_tip().trim()) : "")+
                				   ((visita.getChor_vis()!=null) ? String.format("%1$-6s",visita.getChor_vis().trim()) : "")+
                				   ((visita.getCtip_carpoder()!=null) ? String.format("%1$-1s",visita.getCtip_carpoder().trim()) : "")+
                				   ((visita.getCindfast1()!=null) ? String.format("%1$-2s",visita.getCindfast1().trim()) : "")+
                				   ((visita.getCbascodubi()!=null) ? String.format("%1$-8s",visita.getCbascodubi().trim()) : "")+
                				   ((visita.getCaltcodubi()!=null) ? String.format("%1$-8s",visita.getCaltcodubi().trim()) : "")
                   		
                		   )
                   		
                   		;
                   
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
	
	
	
	
	@RequestMapping(value={"/download_report_gnbvales.htm"}, method = RequestMethod.GET)
	public String download_report_gnbvales(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C4.name());
		return "impexpdb/descarga_reporte_gnbvales";
	}
	
	@RequestMapping(value={"/download_reportgnbvales.htm"} )
	public String download_report_gnbvales(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response){
		System.out.println("-------download_reporte-------");
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C4.name());
		String view="impexpdb/descarga_reporte_gnbvales";
		try {			
			String fec_inicio = request.getParameter("fromDate");
			String fec_fin = request.getParameter("toDate");			
			List<ReporteGNBvales> listaVisita = impExpDbModel.getReporteGNBvales(fec_inicio, fec_fin);			
			if(listaVisita.size() > 0){
				System.out.println("--"+fec_inicio);
				System.out.println("--"+fec_fin);
				final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
				final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "Reporte_vales_"+System.currentTimeMillis()+".xls";
				String path = temperotyFilePath+File.separator;
				System.out.println(path);
				
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition","attachment;filename="+fileName);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				
				MUtilReportFiles reportFiles = new MUtilReportFiles();
				reportFiles.createExcelReporteGNBvales(path, fileName, listaVisita, response.getOutputStream());
			
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		System.out.println("Done");
		return view;
	}	
	
	

	@RequestMapping(value={"/download_report_gnbtoken.htm"}, method = RequestMethod.GET)
	public String download_report_gnbtoken(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C5.name());
		return "impexpdb/descarga_reporte_gnbtoken";
	}
	
	@RequestMapping(value={"/download_reportgnbtoken.htm"} )
	public String download_report_gnbtoken(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response){
		System.out.println("-------download_reporte token GNB-------");
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C5.name());
		String view="impexpdb/descarga_reporte_gnbtoken";
		try {			
			String fec_inicio = request.getParameter("fromDate");
			String fec_fin = request.getParameter("toDate");			
			List<ReporteGNBtoken> listaVisita = impExpDbModel.getReporteGNBtoken(fec_inicio, fec_fin);			
			if(listaVisita.size() > 0){
				System.out.println("--"+fec_inicio);
				System.out.println("--"+fec_fin);
				final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
				final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "Reporte_token_"+System.currentTimeMillis()+".xls";
				String path = temperotyFilePath+File.separator;
				System.out.println(path);
				
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition","attachment;filename="+fileName);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				
				MUtilReportFiles reportFiles = new MUtilReportFiles();
				reportFiles.createExcelReporteGNBtoken(path, fileName, listaVisita, response.getOutputStream());
			
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		System.out.println("Done");
		return view;
	}	


	@RequestMapping(value={"/download_report_bcpdetallegen.htm"}, method = RequestMethod.GET)
	public String download_report_bcpdetallegen(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C8.name());
		return "impexpdb/descarga_reporte_bcpdetallegen";
	}
	
	@RequestMapping(value={"/download_reportbcpdetallegen.htm"} )
	public String download_report_bcpdetallegen(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response){
		System.out.println("-------download_reporte-------");
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C8.name());
		String view="impexpdb/descarga_reporte_bcpdetallegen";
		try {			
			String fec_inicio = request.getParameter("fromDate");
			String fec_fin = request.getParameter("toDate");			
			List<ReporteBCPdetalleGen> listaVisita = impExpDbModel.getReporteBCPdetallegen(fec_inicio, fec_fin);			
			if(listaVisita.size() > 0){
				System.out.println("--"+fec_inicio);
				System.out.println("--"+fec_fin);
				final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
				final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "Reporte_Detallegen_"+System.currentTimeMillis()+".xls";
				String path = temperotyFilePath+File.separator;
				System.out.println(path);
				
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition","attachment;filename="+fileName);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				
				MUtilReportFiles reportFiles = new MUtilReportFiles();
				reportFiles.createExcelReporteBCPdetalleGen(path, fileName, listaVisita, response.getOutputStream());
			
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		System.out.println("Done");
		return view;
	}	

	@RequestMapping(value={"/download_report_orbis.htm"}, method = RequestMethod.GET)
	public String download_report_orbis(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C9.name());
		return "impexpdb/descarga_reporte_orbis";
	}
	
	@RequestMapping(value={"/download_reportorbis.htm"} )
	public String download_report_orbis(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response){
		System.out.println("-------download_reporte-------");
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C9.name());
		String view="impexpdb/descarga_reporte_orbis";
		try {			
			String fec_inicio = request.getParameter("fromDate");
			String fec_fin = request.getParameter("toDate");			
			List<ReporteOrbinGestion> listaVisita = impExpDbModel.getReporteoOrbinGestion(fec_inicio, fec_fin,1);			
			if(listaVisita.size() > 0){
				System.out.println("--"+fec_inicio);
				System.out.println("--"+fec_fin);
				final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
				final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "Reporte_orGen_"+System.currentTimeMillis()+".xls";
				String path = temperotyFilePath+File.separator;
				System.out.println(path);
				
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition","attachment;filename="+fileName);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				
				MUtilReportFiles reportFiles = new MUtilReportFiles();
				reportFiles.createExcelReporteOrbis(path, fileName, listaVisita, response.getOutputStream());
			
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		System.out.println("Done");
		return view;
	}	


	@RequestMapping(value={"/download_report_orbisrz.htm"}, method = RequestMethod.GET)
	public String download_report_orbisrz(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C10.name());
		return "impexpdb/descarga_reporte_orbisrz";
	}
	
	@RequestMapping(value={"/download_reportorbisrz.htm"} )
	public String download_report_orbisrz(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response){
		System.out.println("-------download_reporte-------");
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C10.name());
		String view="impexpdb/descarga_reporte_orbisrz";
		try {			
			String fec_inicio = request.getParameter("fromDate");
			String fec_fin = request.getParameter("toDate");			
			List<ReporteOrbinGestion> listaVisita = impExpDbModel.getReporteoOrbinGestion(fec_inicio, fec_fin,0);			
			if(listaVisita.size() > 0){	
				System.out.println("--"+fec_inicio);
				System.out.println("--"+fec_fin);
				final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
				final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "Reporte_Rez_"+System.currentTimeMillis()+".xls";
				String path = temperotyFilePath+File.separator;
				System.out.println(path);
				
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition","attachment;filename="+fileName);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				
				MUtilReportFiles reportFiles = new MUtilReportFiles();
				reportFiles.createExcelReporteOrbis(path, fileName, listaVisita, response.getOutputStream());
			
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		System.out.println("Done");
		return view;
	}	


	
	
	@RequestMapping(value={"/download_report_revistas.htm"}, method = RequestMethod.GET)
	public String download_report_revistas(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C11.name());
		return "impexpdb/descarga_reporte_revistas";
	}
	
	@RequestMapping(value={"/download_reportrevistas.htm"} )
	public String download_report_revistas(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response){
		System.out.println("-------download_reporte-------");
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C11.name());
		String view="impexpdb/descarga_reporte_revistas";
		try {			
			String fec_inicio = request.getParameter("fromDate");
			String fec_fin = request.getParameter("toDate");			
			List<ReporteRevistas> listaVisita = impExpDbModel.getReporteRevistas(fec_inicio, fec_fin);			
			if(listaVisita.size() > 0){
				System.out.println("--"+fec_inicio);
				System.out.println("--"+fec_fin);
				final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
				final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "Reporte_Revistas_"+System.currentTimeMillis()+".xls";
				String path = temperotyFilePath+File.separator;
				System.out.println(path);
				
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition","attachment;filename="+fileName);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				
				MUtilReportFiles reportFiles = new MUtilReportFiles();
				reportFiles.createExcelReporteRevistas(path, fileName, listaVisita, response.getOutputStream());
			
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		System.out.println("Done");
		return view;
	}	

	
	
	
	//--------------------------- CP  30-11-16 ------------
	
	public File createReporteGNB(List<ReporteGNB> listaReporte,String path){
		 File f = new File(path);   
       try{                
              FileWriter w = new FileWriter(f,true);
              BufferedWriter bw = new BufferedWriter(w);
              PrintWriter wr = new PrintWriter(bw);     
              //CABECERA
              wr.println("FECHA INGRESO"+"\t"+"CARGO"+"\t"+"INSTITUCION"+"\t"+"DOCUMENTO"+"\t"+"NRO_TDC"+"\t"+"APELL_NOM_CLIENTE"+"\t"+
           		   	  "CERTIFICADO"+"\t"+"TIPO_VALE"+"\t"+"COD_VALE"+"\t"+"PUNTAJE"+"\t"+"FECHA_ENTREGA"+"\t"+"FECHA_ACTUALIZACION_TABLA"+"\t"+
           		      "CERTIFICADO"+"\t"+"DIRECCION"+"\t"+"DISTRITO"+"\t"+"PROVINCIA"+"\t"+"DEPARTAMENTO"+"\t"+"MOVIL"+"\t"+"DOCUMENTO_RECEPTOR"+"\t"+
           		   	  "NOMBRE_RECEPTOR"+"\t"+"EMAIL"+"\t"+"LIFEMILES"+"\t"+"UBICACION_ACTUAL"+"\t"+"FECHA_ULTIMA"+"\t"+"RESULTADO_ULTIMO");
              for(ReporteGNB repo : listaReporte){            	   
                  wr.println(
                  		((repo.getFecha_ingreso()!=null) ? repo.getFecha_ingreso() : "")+"\t"+
          				((repo.getCargo()!=null) ? repo.getCargo() : "")+"\t"+
                  		((repo.getInstitucion()!=null) ? repo.getInstitucion() : "")+"\t"+
                  		((repo.getDocumento()!=null) ? repo.getDocumento() : "")+"\t"+
                  		((repo.getNro_tdc()!=null) ? repo.getNro_tdc() : "")+"\t"+
                  		((repo.getNombres_apellidos()!=null) ? repo.getNombres_apellidos() : "")+"\t"+
                  		((repo.getCertificado_1()!=null) ? repo.getCertificado_1() : "")+"\t"+
                  		((repo.getTipo_vale()!=null) ? repo.getTipo_vale() : "")+"\t"+
                  		((repo.getCodigo_vale()!=null) ? repo.getCodigo_vale() : "")+"\t"+                   		
                  		((repo.getPuntaje()!=null) ? repo.getPuntaje() : "")+"\t"+
                  		((repo.getFecha_entrega()!=null) ? repo.getFecha_entrega() : "")+"\t"+
                  		((repo.getFecha_actualizacion_tabla()!=null) ? repo.getFecha_actualizacion_tabla() : "")+"\t"+
                  		((repo.getCertificado_2()!=null) ? repo.getCertificado_2() : "")+"\t"+
                  		((repo.getDireccion()!=null) ? repo.getDireccion() : "")+"\t"+
                  		((repo.getDistrito()!=null) ? repo.getDistrito() : "")+"\t"+
                  		((repo.getProvincia()!=null) ? repo.getProvincia() : "")+"\t"+
                  		((repo.getDepartamento()!=null) ? repo.getDepartamento() : "")+"\t"+                   		
						((repo.getTelofono_movil()!=null) ? repo.getTelofono_movil() : "")+"\t"+
						((repo.getDocumento_receptor()!=null) ? repo.getDocumento_receptor() : "")+"\t"+
						((repo.getNombre_receptor()!=null) ? repo.getNombre_receptor() : "")+"\t"+
						((repo.getEmail()!=null) ? repo.getEmail() : "")+"\t"+
						((repo.getLifemiles()!=null) ? repo.getLifemiles() : "")+"\t"+
						((repo.getUbicacion_actual()!=null) ? repo.getUbicacion_actual() : "")+"\t"+
						((repo.getFecha_ultima()!=null) ? repo.getFecha_ultima() : "")+"\t"+
						((repo.getResultado_ultimo()!=null) ? repo.getResultado_ultimo() : "")); 
              }                
              wr.close();
              bw.close();                
       }catch(IOException e){
              e.printStackTrace();
       }
       return f;
	}
	
	
		
	@RequestMapping(value={"/consulta_buscar_gestion.htm"}) 
	public String buscarGestionToCarga(Model model, HttpServletRequest req, 
			@ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
			@RequestParam("codBar") String codBar, 
			@RequestParam("nroRef") String nroRef, 
			@RequestParam("docIde") String docIde, 
			@RequestParam("nomDes") String nomDes){
		
		System.out.println("-------------------INIT--------------------");
		String view = "distribucion/carga_gestion_form";
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.L1.name()));
		model.addAttribute("menuSelect", Menu.L1.name());
		
		String tipoBuscar = req.getParameter("tipoBuscar")==null?"0":req.getParameter("tipoBuscar");
		
		model.addAttribute("codBar", codBar);
		model.addAttribute("nroRef", nroRef);
		model.addAttribute("docIde", docIde);
		model.addAttribute("nomDes", nomDes);
		model.addAttribute("tipoBuscar", tipoBuscar);
		
		if(codBar.length()>0 || nroRef.length()>0 || docIde.length()>0 || nomDes.length()>2){
			
			String codCliente=null;
			if(usuarioInfo.getTipo().equals(CVConstante.USUARIO_TIPO_CLIENTE)){
				codCliente = usuarioInfo.getIdCliente();
			}
			if(tipoBuscar.equals("0")){
				List<Distribucion> listaResultado = distribucionModel.findDistCodName(codBar, nroRef, docIde, nomDes, codCliente);
				if(listaResultado!=null){
					if(listaResultado.size()>1){
						model.addAttribute("resultadoBusqueda", listaResultado);
					}else if(listaResultado.size()==1){
						model.addAttribute("codBar", codBar);
						model.addAttribute("menuSelect", Menu.L2.name());
						model.addAttribute("SAConsultaDetalle", listaResultado.get(0));
						System.out.println("entro1 ");
						/**23/05/2016_czavalacas**/
						List<String> imagenesName = new ArrayList<String>();
						if(listaResultado.get(0).getImgCarNom()!=null){
							System.out.println("listaResultado.get(0).getImgCarNom():"+listaResultado.get(0).getImgCarNom());
							String ruta = cargaImgModel.cargoBuscarPathBase(listaResultado.get(0).getImgCarNom());
							System.out.println("--ruta:"+ruta);
							if(ruta!=null){
							File carpetaOrigen = new File(ruta);
							System.out.println("--carpetaOrigen:"+carpetaOrigen.getAbsolutePath());
							String nameFile = listaResultado.get(0).getImgCarNom();
							if(!nameFile.equals(""))
								System.out.println("null.....");
								imagenesName =  getFilesByname(carpetaOrigen, nameFile);
							}else{
								System.out.println("else null.....");
								imagenesName.add("");
							}
							System.out.println("RUTA BASE DE CARGO:"+ruta+"-");
						}
						if( listaResultado.get(0).getIndSit().equals("2")){//2 = ENTREGADO
							model.addAttribute("canEdit", "");//NO SE PEUDE EDITAR ES ENTREGADO
						}else{
							model.addAttribute("canEdit", "1");//NO SE PEUDE EDITAR ES ENTREGADO
						}
						model.addAttribute("codBarImgs", imagenesName);
						/******************************/
			            HttpSession session = req.getSession();   
			            session.setAttribute("beanOriginal", listaResultado.get(0));

						List<DistribucionVisita> distribucionVisitas = distribucionModel.findVisitas(listaResultado.get(0).getCodBar());
						List<DistribucionPaquete> distribucionPaquetes = distribucionModel.findPaquetes(listaResultado.get(0).getCodBar());
						model.addAttribute("RPConsultaDetalleVisitas", distribucionVisitas);
						Integer numVisitas=distribucionVisitas.size();
						System.out.println("VISITAS: "+numVisitas);
						if(numVisitas>0){
							model.addAttribute("visitas", "true");
						}
						if(distribucionVisitas.size()>0){
							session.setAttribute("ultimaVisita", distribucionVisitas.get(0));
						}
						model.addAttribute("RPConsultaDetallePaquetes", distribucionPaquetes);
						model.addAttribute("RPConsultaDetalleCoordEntregado", distribucionModel.findDireccionEntrega(distribucionVisitas));
												
						//List<Estados> estados = distribucionModel.getEstadosToComboBox("1", null,listaResultado.get(0).getCodCli());
						List<Estados> estados = distribucionModel.getEstadosMotivosToComboBox("1", null,null);
						model.addAttribute("TipoSituacion", estados);
												
						List<Estados> vinculo = distribucionModel.getEstadosToComboBox("4", null,null);					    
						model.addAttribute("TipoVinculo", vinculo);
						System.out.println("llenar combos");
						view = "distribucion/consulta_detalle_carga";
					}
				}
			}else if(tipoBuscar.equals("PQ")){
				List<DistribucionPaquete> listaResultadoPaquete = distribucionModel.findPaqueteCodName(nroRef, nomDes, codCliente);
				if(listaResultadoPaquete!=null){
					if(listaResultadoPaquete.size()>1){
						model.addAttribute("resultadoBusquedaPaquete", listaResultadoPaquete);
					}else if(listaResultadoPaquete.size()==1){
						Distribucion distribucion = distribucionModel.getByCodBar(listaResultadoPaquete.get(0).getCodBar(), codCliente);
						model.addAttribute("codBar", codBar);
						model.addAttribute("idPaquete", listaResultadoPaquete.get(0).getId());
						model.addAttribute("menuSelect", Menu.L2.name());
						model.addAttribute("SAConsultaDetalle", distribucion);
						
						/**23/05/2016_czavalacas**/
						List<String> imagenesName = new ArrayList<String>();
						if(distribucion.getImgCarNom()!=null){
							System.out.println("listaResultado.get(0).getImgCarNom():"+distribucion.getImgCarNom());
							String ruta = cargaImgModel.cargoBuscarPathBase(distribucion.getImgCarNom());
							System.out.println("--ruta:"+ruta);
							if(ruta!=null){
							File carpetaOrigen = new File(ruta);
							System.out.println("--carpetaOrigen:"+carpetaOrigen.getAbsolutePath());
							String nameFile = distribucion.getImgCarNom();
							if(!nameFile.equals(""))
								System.out.println("null.....");
								imagenesName =  getFilesByname(carpetaOrigen, nameFile);
							}else{
								System.out.println("else null.....");
								imagenesName.add("");
							}
							System.out.println("RUTA BASE DE CARGO:"+ruta+"-");
						}
						model.addAttribute("codBarImgs", imagenesName);
						if( distribucion.getIndSit().equals("2")){//2 = ENTREGADO
							model.addAttribute("canEdit", "");//NO SE PEUDE EDITAR ES ENTREGADO
						}else{
							model.addAttribute("canEdit", "1");//NO SE PEUDE EDITAR ES ENTREGADO
						}
						/******************************/
						
						List<DistribucionVisita> distribucionVisitas = distribucionModel.findVisitas(distribucion.getCodBar());
						model.addAttribute("RPConsultaDetalleVisitas", distribucionVisitas);
						model.addAttribute("RPConsultaDetallePaquetes", listaResultadoPaquete.get(0));
						model.addAttribute("RPConsultaDetalleCoordEntregado", distribucionModel.findDireccionEntrega(distribucionVisitas));
						
						model.addAttribute("RPConsultaDetallePaquetes", listaResultadoPaquete);
						
						List<Estados> estados = distribucionModel.getEstadosToComboBox("1", null,distribucion.getCodCli() );					    
						model.addAttribute("TipoSituacion", estados);
						
						List<Estados> vinculo = distribucionModel.getEstadosToComboBox("4", null,null);					    
						model.addAttribute("TipoVinculo", vinculo);
						//view = "redirect:/distribucion/consulta_detalle.htm";
						view = "distribucion/consulta_detalle_carga";
					}
				}
			}
			
			
		}
		return view;
	}
	
	public List<String> getFilesByname(File carpetaOrigen, String fileName){
		List<String> files = new ArrayList<String>();		
		String codigoBarra = cargaImgModel.getCodBarFromFileName(fileName);
		File [] lsFiles = carpetaOrigen.listFiles();
		System.out.println("lsFiles:"+lsFiles.length);
		if(codigoBarra!=null){
			int sizeFileOrigen = codigoBarra.length();
			for(File file : lsFiles){
				if(file.getName().substring(0, sizeFileOrigen).equals(codigoBarra)){
					files.add(file.getName());
				}
			}
		}else{
			files.add("");
		}
		return files;
	}
	
	@RequestMapping(value={"/consulta_detalle.htm"}) 
	public String detalles(Model model, HttpServletRequest req, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, @RequestParam("codBar") String codBar){
		
		String view = "distribucion/consulta_form";
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.B1.name()));
		model.addAttribute("menuSelect", Menu.B1.name());
		String idPaquete=req.getParameter("idPaquete");
		String codCliente=null;
		if(usuarioInfo.getTipo().equals(CVConstante.USUARIO_TIPO_CLIENTE)){
			codCliente = usuarioInfo.getIdCliente();
		}
		Distribucion distribucion = distribucionModel.getByCodBar(codBar,codCliente);
		/**23/05/2016_czavalacas**/
		List<String> imagenesName = new ArrayList<String>();
		if(distribucion.getImgCarNom()!=null){
			System.out.println("listaResultado.get(0).getImgCarNom():"+distribucion.getImgCarNom());
			String ruta = cargaImgModel.cargoBuscarPathBase(distribucion.getImgCarNom());
			System.out.println("--ruta:"+ruta);
			if(ruta!=null){
			File carpetaOrigen = new File(ruta);
			System.out.println("--carpetaOrigen:"+carpetaOrigen.getAbsolutePath());
			String nameFile = distribucion.getImgCarNom();
			if(!nameFile.equals(""))
				System.out.println("null.....");
				imagenesName =  getFilesByname(carpetaOrigen, nameFile);
			}else{
				System.out.println("else null.....");
				imagenesName.add("");
			}
			System.out.println("RUTA BASE DE CARGO:"+ruta+"-");
		}
		model.addAttribute("codBarImgs", imagenesName);
		/******************************/		
		if(distribucion!=null){
			view = "distribucion/consulta_detalle";
			model.addAttribute("menuSelect", Menu.B2.name());
			model.addAttribute("SAConsultaDetalle", distribucion);
			
			List<DistribucionVisita> distribucionVisitas = distribucionModel.findVisitas(distribucion.getCodBar());
			model.addAttribute("RPConsultaDetalleVisitas", distribucionVisitas);
			model.addAttribute("RPConsultaDetalleCoordEntregado", distribucionModel.findDireccionEntrega(distribucionVisitas));
			
			List<DistribucionPaquete> distribucionPaquetes = distribucionModel.findPaquetes(distribucion.getCodBar());
			model.addAttribute("RPConsultaDetallePaquetes", distribucionPaquetes);
			model.addAttribute("idPaquete", idPaquete);
		}
		return view;
	}
	
	@RequestMapping(value={"/consulta_imgcar.htm"}, method={RequestMethod.GET}) 
	public @ResponseBody byte[] imagenCargo(Model model, HttpServletRequest req)throws IOException{
		
		String name=req.getParameter("name");
		String type=CVConstante.IMAGE_TYPE_JPG;
		float quality=CVConstante.IMAGE_QUALITY_LOW;
		int size=CVConstante.IMAGE_SIZE_ICON;
		
		String qlty=req.getParameter("quality")==null?"":req.getParameter("quality");
		if(qlty.toLowerCase().equals("low")){
			quality=CVConstante.IMAGE_QUALITY_LOW;
			size=CVConstante.IMAGE_SIZE_ICON;
		}else if(qlty.toLowerCase().equals("medium")){
			quality=CVConstante.IMAGE_QUALITY_MEDIUM;
			size=CVConstante.IMAGE_SIZE_MEDIUM;
		}else if(qlty.toLowerCase().equals("high")){
			quality=CVConstante.IMAGE_QUALITY_HIGH;
			size=CVConstante.IMAGE_SIZE_FULL;
		}
		
		String typeOut=req.getParameter("type")==null?"":req.getParameter("type");
		if(typeOut.toLowerCase().equals(CVConstante.IMAGE_TYPE_JPG)){
			type=CVConstante.IMAGE_TYPE_JPG;
		}else if(typeOut.toLowerCase().equals(CVConstante.IMAGE_TYPE_PNG)){
			type=CVConstante.IMAGE_TYPE_PNG;
		}
		System.out.println(":::::::::::name"+name);
		String pathFile=cargaImgModel.cargoBuscarPath(name);
		File tifFile;
		if(pathFile!=null){
			tifFile = new File(pathFile);
			System.out.println("path1:"+pathFile);
		}else{
			String pathDefault =  req.getSession().getServletContext().getRealPath("static/img");
			System.out.println("path2:"+pathDefault);
			tifFile = new File(pathDefault,CVConstante.DEFAULT_IMAGE_CARGO);
		}
		//System.out.println(tifFile.getAbsolutePath());
		SeekableStream tiffStream = new FileSeekableStream(tifFile);
        TIFFDecodeParam tiffParam = null;
        ImageDecoder imgDec = ImageCodec.createImageDecoder("tiff", tiffStream, tiffParam);
        RenderedImage renderImg = imgDec.decodeAsRenderedImage(0);

        File jpgFile = new File(tifFile.getParent(),tifFile.getName().toLowerCase().replace(".tif", ".jpg"));
        File pngFile = new File(jpgFile.getParent(), jpgFile.getName().toLowerCase().replace(".jpg", ".png"));
        
        FileOutputStream fosJpg = new FileOutputStream(jpgFile);
        JPEGEncodeParam jpgParam = new JPEGEncodeParam();
        jpgParam.setQuality(quality);
        ImageEncoder imgEnc = ImageCodec.createImageEncoder("jpeg", fosJpg, jpgParam);
        imgEnc.encode(renderImg);
        fosJpg.flush();
        fosJpg.close();
        
        InputStream resultStream = null;
        if(type.equals(CVConstante.IMAGE_TYPE_JPG)){
        	BufferedImage bufferedImage = ImageIO.read(jpgFile);
            ImageIO.write(ImageDraw.resizeImageCargo(bufferedImage, size), "jpg", jpgFile);
            
        	resultStream = new FileInputStream(jpgFile);
        }else if(type.equals(CVConstante.IMAGE_TYPE_PNG)){
            BufferedImage bufferedImage = ImageIO.read(jpgFile);
            //ImageIO.write(bufferedImage, "png", pngFile);
            ImageIO.write(ImageDraw.resizeImageCargo(bufferedImage, size), "png", pngFile);
            
        	resultStream = new FileInputStream(pngFile);
        }
        
		if(resultStream!=null){
			try {
			    return IOUtils.toByteArray(resultStream);
			}catch (Exception e){
				e.printStackTrace();
			}finally {
			    resultStream.close();
			    if(type.equals(CVConstante.IMAGE_TYPE_JPG)){
			    	jpgFile.delete();
			    }else if(type.equals(CVConstante.IMAGE_TYPE_PNG)){
			    	jpgFile.delete();
			    	pngFile.delete();
			    }
			}
		}
		return null;
	}
	
	@RequestMapping(value={"/consulta_imgcar_zoom.htm"}) 
	public String imagenCargoZoom(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, @RequestParam("name") String name){
		
		String view = "distribucion/consulta_detalle_cargo";
		model.addAttribute("name", name);
		return view;
	}
	
	@RequestMapping(value={"/consulta_ver_detalle.htm"}) 
	public String recuperaDetalles(Model model, ModelMap modelMap){
		
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.B1.name()));
		model.addAttribute("menuSelect", Menu.B2.name());
		
		Distribucion distribucion = (Distribucion)modelMap.get("SAConsultaDetalle");
		/**23/05/2016_czavalacas**/
		List<String> imagenesName = new ArrayList<String>();
		if(distribucion.getImgCarNom()!=null){
			System.out.println("listaResultado.get(0).getImgCarNom():"+distribucion.getImgCarNom());
			String ruta = cargaImgModel.cargoBuscarPathBase(distribucion.getImgCarNom());
			System.out.println("--ruta:"+ruta);
			if(ruta!=null){
			File carpetaOrigen = new File(ruta);
			System.out.println("--carpetaOrigen:"+carpetaOrigen.getAbsolutePath());
			String nameFile = distribucion.getImgCarNom();
			if(!nameFile.equals(""))
				System.out.println("null.....");
				imagenesName =  getFilesByname(carpetaOrigen, nameFile);
			}else{
				System.out.println("else null.....");
				imagenesName.add("");
			}
			System.out.println("RUTA BASE DE CARGO:"+ruta+"-");
		}
		model.addAttribute("codBarImgs", imagenesName);
		/******************************/		
		List<DistribucionVisita> distribucionVisitas = distribucionModel.findVisitas(distribucion.getCodBar());
		model.addAttribute("RPConsultaDetalleVisitas", distribucionVisitas);
		Integer numVisitas=distribucionVisitas.size();

		model.addAttribute("RPConsultaDetalleCoordEntregado", distribucionModel.findDireccionEntrega(distribucionVisitas));
		List<DistribucionPaquete> distribucionPaquetes = distribucionModel.findPaquetes(distribucion.getCodBar());
		model.addAttribute("RPConsultaDetallePaquetes", distribucionPaquetes);
		return "distribucion/consulta_detalle";
	}
	
	@RequestMapping(value={"/carga_ver_detalle.htm"}) 
	public String recuperaDetallesCarga(Model model, ModelMap modelMap){
		
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.L1.name()));
		model.addAttribute("menuSelect", Menu.L2.name());
		
		Distribucion distribucion = (Distribucion)modelMap.get("SAConsultaDetalle");
		
		/**23/05/2016_czavalacas**/
		List<String> imagenesName = new ArrayList<String>();
		if(distribucion.getImgCarNom()!=null){
			System.out.println("listaResultado.get(0).getImgCarNom():"+distribucion.getImgCarNom());
			String ruta = cargaImgModel.cargoBuscarPathBase(distribucion.getImgCarNom());
			System.out.println("--ruta:"+ruta);
			if(ruta!=null){
			File carpetaOrigen = new File(ruta);
			System.out.println("--carpetaOrigen:"+carpetaOrigen.getAbsolutePath());
			String nameFile = distribucion.getImgCarNom();
			if(!nameFile.equals(""))
				System.out.println("null.....");
				imagenesName =  getFilesByname(carpetaOrigen, nameFile);
			}else{
				System.out.println("else null.....");
				imagenesName.add("");
			}
			System.out.println("RUTA BASE DE CARGO:"+ruta+"-");
		}
		model.addAttribute("codBarImgs", imagenesName);
		if( distribucion.getIndSit().equals("2")){//2 = ENTREGADO
			model.addAttribute("canEdit", "");//NO SE PEUDE EDITAR ES ENTREGADO
		}else{
			model.addAttribute("canEdit", "1");//NO SE PEUDE EDITAR ES ENTREGADO
		}
		/******************************/		
		
		List<DistribucionVisita> distribucionVisitas = distribucionModel.findVisitas(distribucion.getCodBar());
		model.addAttribute("RPConsultaDetalleVisitas", distribucionVisitas);

		model.addAttribute("RPConsultaDetalleCoordEntregado", distribucionModel.findDireccionEntrega(distribucionVisitas));
		List<DistribucionPaquete> distribucionPaquetes = distribucionModel.findPaquetes(distribucion.getCodBar());
		model.addAttribute("RPConsultaDetallePaquetes", distribucionPaquetes);
		List<Estados> estados = distribucionModel.getEstadosToComboBox("1", null, distribucion.getCodCli());					    
		model.addAttribute("TipoSituacion", estados);
		List<Estados> vinculo = distribucionModel.getEstadosToComboBox("4", null,null);					    
		model.addAttribute("TipoVinculo", vinculo);
		return "distribucion/consulta_detalle_carga";
	}
	
	
	@RequestMapping(value={"/consulta_coord.htm"}) 
	public String coordinaciones(Model model, ModelMap modelMap){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.B1.name()));
		model.addAttribute("menuSelect", Menu.B3.name());
		
		Distribucion distribucion = (Distribucion)modelMap.get("SAConsultaDetalle");
		
		List<DistribucionCoord> distribucionCoordinaciones = distribucionModel.findCoordinaciones(distribucion.getCodBar());
		model.addAttribute("RPConsultaDetalleCoordinaciones", distribucionCoordinaciones);
		
		List<RegistroCoord> distribucionCoords = coordinacionModel.buscarPorCodBar(distribucion.getCodBar());
		model.addAttribute("RPCoordinacionRegistCoords", distribucionCoords);
		return "distribucion/consulta_coord";
	}
	
	@RequestMapping(value={"/consulta_coord_to_carga.htm"}) 
	public String coordinaciones_to_carga(Model model, ModelMap modelMap){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.L1.name()));
		model.addAttribute("menuSelect", Menu.L2.name());
		
		Distribucion distribucion = (Distribucion)modelMap.get("SAConsultaDetalle");
		
		List<DistribucionCoord> distribucionCoordinaciones = distribucionModel.findCoordinaciones(distribucion.getCodBar());
		if(distribucionCoordinaciones.size()>0){
			int id_downliad = distribucionCoordinaciones.get(0).getId();
			model.addAttribute("canDownload", id_downliad);
		}
		model.addAttribute("RPConsultaDetalleCoordinaciones", distribucionCoordinaciones);
		
		List<RegistroCoord> distribucionCoords = coordinacionModel.buscarPorCodBar(distribucion.getCodBar());
		model.addAttribute("RPCoordinacionRegistCoords", distribucionCoords);
		return "distribucion/consulta_coord_carga";
	}
	
	@RequestMapping(value={"/consulta_coord_telf.htm"}) 
	public String gestionesTelefonicas(Model model, ModelMap modelMap){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.B1.name()));
		model.addAttribute("menuSelect", Menu.B4.name());
		
		Distribucion distribucion = (Distribucion)modelMap.get("SAConsultaDetalle");
		
		List<DistribucionCoordTelf> distribucionCoordTelfs = distribucionModel.findCoordinacionesTelf(distribucion.getCodBar());
		model.addAttribute("RPConsultaDetalleCoordTelfs", distribucionCoordTelfs);
		
		List<RegistroCoordTelf> registroCoordTelfs = coordinacionTelfModel.buscarPorCodBar(distribucion.getCodBar());
		model.addAttribute("RPCoordinacionRegistCoordTelfs", registroCoordTelfs);
		
		return "distribucion/consulta_coord_telf";
	}
	
	
	//REPORTES
	
	@RequestMapping(value={"/reporte_estado_op.htm"}) 
	public String reporteEstadoOP(Model model, ModelMap modelMap, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, @RequestParam("ordPro") String ordPro){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C1.name());
		
		String codCliente=null;
		if(usuarioInfo.getTipo().equals(CVConstante.USUARIO_TIPO_CLIENTE)){
			codCliente = usuarioInfo.getIdCliente();
		}
		if(ordPro.toString().length()>=4){
			OrdenProceso ordenProceso = distribucionModel.findOpInfo(ordPro,codCliente);
			model.addAttribute("RPOdenProceso", ordenProceso);
			System.out.println("Reporte - ordPro:"+ordPro+"-codCliente:"+codCliente);
			List<OrdenProcesoAvance> ordenProcesoAvance = distribucionModel.findOpAvance(ordPro,codCliente);
			model.addAttribute("RPOdenProcesoAvance", ordenProcesoAvance);
			int cantTotal = 0;
			for(OrdenProcesoAvance opAdvance : ordenProcesoAvance){
				cantTotal = cantTotal + opAdvance.getCantidad();
			}
			model.addAttribute("cantTotal", cantTotal);
			model.addAttribute("RPOdenProcesoAvanceTotal", distribucionModel.findOpAvanceTotal(ordenProcesoAvance));
			Map<String,String> ordenProcesoAvanceImg = distribucionModel.findOpAvanceStringImage(ordenProcesoAvance);
			model.addAttribute("RPOdenProcesoAvanceImgLabel", ordenProcesoAvanceImg.get("label"));
			model.addAttribute("RPOdenProcesoAvanceImgValue", ordenProcesoAvanceImg.get("value"));
			
			List<OrdenProcesoCargos> ordenProcesoCargos = distribucionModel.findOpCargos(ordPro,codCliente);
			model.addAttribute("RPOdenProcesoCargos", ordenProcesoCargos);
			int cantTotalCargos = 0;
			for(OrdenProcesoCargos obCargos : ordenProcesoCargos){
				cantTotalCargos = cantTotalCargos + obCargos.getCantidad();
			}
			System.out.println("cargos: "+ordenProcesoCargos.size());
			model.addAttribute("cantTotalCargos", cantTotalCargos);
			model.addAttribute("RPOdenProcesoCargosTotal", distribucionModel.findOpCargosTotal(ordenProcesoCargos));
			Map<String,String> ordenProcesoCargosImg = distribucionModel.findOpCargosStringImage(ordenProcesoCargos);
			model.addAttribute("RPOdenProcesoCargosImgLabel", ordenProcesoCargosImg.get("label"));
			model.addAttribute("RPOdenProcesoCargosImgValue", ordenProcesoCargosImg.get("value"));
		}
		return "distribucion/reporte_estado";
	}
	
	@RequestMapping(value={"/reporte_form.htm"}) 
	public String reporteDownForm(Model model){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C2.name());
		
		
		return "distribucion/reporte_form";
	}
	
	@RequestMapping(value={"/reporte_form_r2.htm"}) 
	public String reporteDownFormRadar2(Model model){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C3.name());
		
		
		return "distribucion/reporte_form_v2";
	}
	
	@RequestMapping(value={"/reporte_busqueda_op.htm"}) 
	public String reporteBusquedaOps(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, @RequestParam("ordPro") String ordPro){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C2.name());
		
		String codCliente=null;
		if(usuarioInfo.getTipo().equals(CVConstante.USUARIO_TIPO_CLIENTE)){
			codCliente = usuarioInfo.getIdCliente();
		}
		
		if(ordPro.toString().length()>=4){
			List<OrdenProceso> ops = distribucionModel.findOpsInfo(ordPro, codCliente);
			model.addAttribute("RPListOdenProceso", ops);
		}
		model.addAttribute("ordPro", ordPro);
		
		return "distribucion/reporte_form";
	}
	
	@RequestMapping(value={"/reporte_busqueda_op_r2.htm"}) 
	public String reporteBusquedaOpsR2(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, @RequestParam("ordPro") String ordPro){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C3.name());
		
		String codCliente=null;
		if(usuarioInfo.getTipo().equals(CVConstante.USUARIO_TIPO_CLIENTE)){
			codCliente = usuarioInfo.getIdCliente();
		}
		
		if(ordPro.toString().length()>=4){
			List<OrdenProceso> ops = distribucionModel.findOpsInfo(ordPro, codCliente);
			model.addAttribute("RPListOdenProceso", ops);
		}
		model.addAttribute("ordPro", ordPro);
		
		return "distribucion/reporte_form_v2";
	}
	
	@RequestMapping(value={"/reporte_busqueda_fecha.htm"}) 
	public String reporteBusquedaFecha(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C2.name());
		
		String codCliente=null;
		if(usuarioInfo.getTipo().equals(CVConstante.USUARIO_TIPO_CLIENTE)){
			codCliente = usuarioInfo.getIdCliente();
		}
		
		if(fromDate.toString().length()==10 && toDate.toString().length()==10 && (CVDinamico.isDate(fromDate) && CVDinamico.isDate(toDate))){
			List<OrdenProceso> ops = distribucionModel.findOpsInfo(CVDinamico.getDateFromString(fromDate, "dd/MM/yyyy"), CVDinamico.getDateFromString(toDate, "dd/MM/yyyy"), codCliente);
			model.addAttribute("RPListOdenProceso", ops);
		}
		model.addAttribute("fromDate", fromDate);
		model.addAttribute("toDate", toDate);
		
		return "distribucion/reporte_form";
	}
		
	@RequestMapping(value={"/reporte_download.htm"}) 
	public String reporteDownload(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C2.name());
		
		String codCliente=null;
		if(usuarioInfo.getTipo().equals(CVConstante.USUARIO_TIPO_CLIENTE)){
			codCliente = usuarioInfo.getIdCliente();
		}
		
		boolean contaVisita = req.getParameter("contaVisita")!=null;
		boolean contaCoord = req.getParameter("contaCoord")!=null;
		boolean contaCoordTelf = req.getParameter("contaCoordTelf")!=null;
		boolean datoOpcional = req.getParameter("datosOpcionales")!=null;
		boolean datoMandatario = req.getParameter("datosMandatarios")!=null;
		
		int cantVisita = Integer.parseInt(req.getParameter("cantVisita"));
		int cantCoord = Integer.parseInt(req.getParameter("cantCoord"));
		int cantCoordTelf = Integer.parseInt(req.getParameter("cantCoordTelf"));
				
		String[] ordPro = req.getParameterValues("ordPro")==null?new String[0]:req.getParameterValues("ordPro");
		String pathRoot =  req.getSession().getServletContext().getRealPath("dinamic/download");
		String dirName = CVDinamico.getDateInFormat("yyyy-MM-dd_hhmm");
		String fileName = "REPORTE"+CVDinamico.getDateInFormat("yyyy-MM-dd_hhmm");
		
		File dir = new File(pathRoot, dirName);
		dir.mkdirs();
		dir.setWritable(true, false);
		
		//model.addAttribute("RPDistribucionJoinList", "distribucionJoinList");
		Map<String, String> reportFiles = distribucionModel.generateFilesReporteDistOPs(ordPro, codCliente, usuarioInfo.getId(), dir.getAbsolutePath(), fileName, datoOpcional, datoMandatario, cantVisita, cantCoord, cantCoordTelf);
		model.addAttribute("RPdirName", dirName);
		String zipFile = zip.zipFileZip4j(dir, reportFiles.get("xls"), usuarioInfo.getUsername());
		model.addAttribute("RPfileXLSName", zipFile);
		//model.addAttribute("RPfileTXTName", reportFiles.get("txt"));
		
		if(contaVisita)model.addAttribute("RPcontaVisita", contaVisita);
		if(contaCoord)model.addAttribute("RPcontaCoord", contaCoord);
		if(contaCoordTelf)model.addAttribute("RPcontaCoordTelf", contaCoordTelf);
		if(datoOpcional)model.addAttribute("RPdatoOpcional", datoOpcional);
		if(datoMandatario)model.addAttribute("RPdatoMandatario", datoMandatario);
		model.addAttribute("RPcantVisita", cantVisita);
		model.addAttribute("RPcantCoord", cantCoord);
		model.addAttribute("RPcantCoordTelf", cantCoordTelf);
		
		return "distribucion/reporte_form";
	}
	
	/** 
	 * METODO SOLO PARA CLIENTE GNB
	 */
	@RequestMapping(value={"/reporte_busqueda_fecha_r2.htm"}) 
	public String reporteBusquedaFechaR2(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C3.name());
		
		String codCliente=null;
		if(usuarioInfo.getTipo().equals(CVConstante.USUARIO_TIPO_CLIENTE)){
			codCliente = usuarioInfo.getIdCliente();
		}
		//HARCODEAMOS PARA GNB
		if(codCliente==null){
			codCliente = CVConstante.COD_CLIENTE_GNB;
		}
		//////////////////////
		if(fromDate.toString().length()==10 && toDate.toString().length()==10 && (CVDinamico.isDate(fromDate) && CVDinamico.isDate(toDate))){
			List<OrdenProceso> ops = distribucionModel.findOpsInfo(CVDinamico.getDateFromString(fromDate, "dd/MM/yyyy"), CVDinamico.getDateFromString(toDate, "dd/MM/yyyy"), codCliente);
			model.addAttribute("RPListOdenProceso", ops);
		}
		model.addAttribute("fromDate", fromDate);
		model.addAttribute("toDate", toDate);
		
		return "distribucion/reporte_form_v2";
	}
	
	@RequestMapping(value={"/downloadReportGNB.htm"}) 
	public String downloadExcelReporteGNB(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, HttpServletRequest req){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C3.name());
		
		String codCliente=null;
		if(usuarioInfo.getTipo().equals(CVConstante.USUARIO_TIPO_CLIENTE)){
			codCliente = usuarioInfo.getIdCliente();
		}
		//HARCODEAMOS PARA GNB
		if(codCliente==null){
			codCliente = CVConstante.COD_CLIENTE_GNB;// HARCODEAMOS CODIGO 
		}		
		//////////////////////
		boolean contaVisita = req.getParameter("contaVisita")!=null;
		boolean contaCoord = req.getParameter("contaCoord")!=null;
		boolean contaCoordTelf = req.getParameter("contaCoordTelf")!=null;
		boolean datoOpcional = req.getParameter("datosOpcionales")!=null;
		boolean datoMandatario = req.getParameter("datosMandatarios")!=null;
		/*
		int cantVisita = Integer.parseInt(req.getParameter("cantVisita"));
		int cantCoord = Integer.parseInt(req.getParameter("cantCoord"));
		int cantCoordTelf = Integer.parseInt(req.getParameter("cantCoordTelf"));
		*/
		int cantVisita = 6;
		int cantCoord = 6;
		int cantCoordTelf = 6;
		
		String[] ordPro = {};
		if(fromDate.toString().length()==10 && toDate.toString().length()==10 && (CVDinamico.isDate(fromDate) && CVDinamico.isDate(toDate))){
			ordPro = distribucionModel.findOpsInfoUnicos(CVDinamico.getDateFromString(fromDate, "dd/MM/yyyy"), CVDinamico.getDateFromString(toDate, "dd/MM/yyyy"), codCliente);
		}
		if(ordPro.length==0){
			return "distribucion/reporte_form_v2";
		}
		String pathRoot =  req.getSession().getServletContext().getRealPath("dinamic/download");
		String dirName = CVDinamico.getDateInFormat("yyyy-MM-dd_hhmm");
		String fileName = "REPORTE"+CVDinamico.getDateInFormat("yyyy-MM-dd_hhmm");
		
		File dir = new File(pathRoot, dirName);
		dir.mkdirs();
		dir.setWritable(true, false);
		
		//model.addAttribute("RPDistribucionJoinList", "distribucionJoinList");
		System.out.println("-----REPORT 2-------");
		Map<String, String> reportFiles = distribucionModel.generateFilesReporteDistOPs_R2(ordPro, codCliente, usuarioInfo.getId(), dir.getAbsolutePath(), fileName, datoOpcional, datoMandatario, cantVisita, cantCoord, cantCoordTelf);
		model.addAttribute("RPdirName", dirName);
		String zipFile = zip.zipFileZip4j(dir, reportFiles.get("xls"), usuarioInfo.getUsername());
		model.addAttribute("RPfileXLSName", zipFile);
		//model.addAttribute("RPfileTXTName", reportFiles.get("txt"));
		
		if(contaVisita)model.addAttribute("RPcontaVisita", contaVisita);
		if(contaCoord)model.addAttribute("RPcontaCoord", contaCoord);
		if(contaCoordTelf)model.addAttribute("RPcontaCoordTelf", contaCoordTelf);
		if(datoOpcional)model.addAttribute("RPdatoOpcional", datoOpcional);
		if(datoMandatario)model.addAttribute("RPdatoMandatario", datoMandatario);
		model.addAttribute("RPcantVisita", cantVisita);
		model.addAttribute("RPcantCoord", cantCoord);
		model.addAttribute("RPcantCoordTelf", cantCoordTelf);
		return "distribucion/reporte_form_v2";
	}
	
	@RequestMapping(value={"/reporte_download_r2.htm"}) 
	public String reporteDownload_R2(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.C1.name()));
		model.addAttribute("menuSelect", Menu.C3.name());
		
		String codCliente=null;
		if(usuarioInfo.getTipo().equals(CVConstante.USUARIO_TIPO_CLIENTE)){
			codCliente = usuarioInfo.getIdCliente();
		}
		//HARCODEAMOS PARA GNB
		if(codCliente==null){
			codCliente = CVConstante.COD_CLIENTE_GNB;// HARCODEAMOS CODIGO 
		}
		//////////////////////
		boolean contaVisita = req.getParameter("contaVisita")!=null;
		boolean contaCoord = req.getParameter("contaCoord")!=null;
		boolean contaCoordTelf = req.getParameter("contaCoordTelf")!=null;
		boolean datoOpcional = req.getParameter("datosOpcionales")!=null;
		boolean datoMandatario = req.getParameter("datosMandatarios")!=null;
		
		int cantVisita = Integer.parseInt(req.getParameter("cantVisita"));
		int cantCoord = Integer.parseInt(req.getParameter("cantCoord"));
		int cantCoordTelf = Integer.parseInt(req.getParameter("cantCoordTelf"));
		
		String[] ordPro = req.getParameterValues("ordPro")==null?new String[0]:req.getParameterValues("ordPro");
		String pathRoot =  req.getSession().getServletContext().getRealPath("dinamic/download");
		String dirName = CVDinamico.getDateInFormat("yyyy-MM-dd_hhmm");
		String fileName = "REPORTE"+CVDinamico.getDateInFormat("yyyy-MM-dd_hhmm");
		
		File dir = new File(pathRoot, dirName);
		dir.mkdirs();
		dir.setWritable(true, false);
		
		//model.addAttribute("RPDistribucionJoinList", "distribucionJoinList");
		System.out.println("-----REPORT 2-------");
		Map<String, String> reportFiles = distribucionModel.generateFilesReporteDistOPs_R2(ordPro, codCliente, usuarioInfo.getId(), dir.getAbsolutePath(), fileName, datoOpcional, datoMandatario, cantVisita, cantCoord, cantCoordTelf);
		model.addAttribute("RPdirName", dirName);
		String zipFile = zip.zipFileZip4j(dir, reportFiles.get("xls"), usuarioInfo.getUsername());
		model.addAttribute("RPfileXLSName", zipFile);
		//model.addAttribute("RPfileTXTName", reportFiles.get("txt"));
		
		if(contaVisita)model.addAttribute("RPcontaVisita", contaVisita);
		if(contaCoord)model.addAttribute("RPcontaCoord", contaCoord);
		if(contaCoordTelf)model.addAttribute("RPcontaCoordTelf", contaCoordTelf);
		if(datoOpcional)model.addAttribute("RPdatoOpcional", datoOpcional);
		if(datoMandatario)model.addAttribute("RPdatoMandatario", datoMandatario);
		model.addAttribute("RPcantVisita", cantVisita);
		model.addAttribute("RPcantCoord", cantCoord);
		model.addAttribute("RPcantCoordTelf", cantCoordTelf);
		
		return "distribucion/reporte_form_v2";
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
	
	@RequestMapping(value={"/downloadUnoPDFCoord.htm"}) 
	public void downloadUnoPDF(HttpServletRequest request, HttpServletResponse response) throws IOException{
		try {
			String id_cordinacion = request.getParameter("param");
			CordinacionPDF cordinacionBean = distribucionModel.getCordinacionPDFUno(id_cordinacion);
			List<CordinacionPDF> listaCordinaciones = new ArrayList<CordinacionPDF>();
			listaCordinaciones.add(cordinacionBean);
			System.out.println("id_cordinacion:"+request.getParameter("param"));
			System.out.println("getNombre_cliente:"+cordinacionBean.getNombre_cliente());		
			response.setContentType("application/pdf");		
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Cache-Control", "max-age=0");		
			System.out.println("-------------download PDF----------");
			
			final javax.servlet.ServletContext servletContext = request.getSession()
					.getServletContext();
			final File tempDirectory = (File) servletContext
					.getAttribute("javax.servlet.context.tempdir");
			final String temperotyFilePath = tempDirectory.getAbsolutePath();
			String fileName = "Generate_Report_"+System.currentTimeMillis()+".pdf";
			response.setHeader("Content-disposition", "attachment; " +
					"filename="+ fileName);
			Document document = new Document(PageSize.A4);
			PdfWriter writer;			
				
			writer = PdfWriter.getInstance(document, new FileOutputStream(temperotyFilePath+"\\"+fileName));
			document.open();			
			buildPDF(writer, document,listaCordinaciones);		
		
			System.out.println("FUCK 3	");
			document.close();		
			java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
			baos = convertPDFToByteArrayOutputStream(temperotyFilePath+"\\"+fileName);
			OutputStream os = response.getOutputStream();
	        baos.writeTo(os);
	        os.flush();
	        File pdf  = new File(temperotyFilePath+"\\"+fileName);
	        pdf.delete();
		} catch (DocumentException de) {
			throw new IOException(de.getMessage());
		}
	}
	
	public void buildPDF(PdfWriter writer, Document document, List<CordinacionPDF> listaCordinaciones) throws IOException, DocumentException{
		try{
		/******coordenadas de lineas******/
		int posX = 15;
		int posY = 570;
		/**********************************/
		/******POSISIONES X marging-left ********/
		int posXInicial = 20;			
		/****************************************/
		/************VARIACION ENTRE BLOQUES********/
		int valorPosXReal = 0;
		int valorPorYReal = 0;
		/******************************/
		/************VARIACION ENTRE BLOQUES********/
		int variacionX = 297;
		int variacionY = 267;
		/******************************/
		boolean bloque1= true;
		boolean bloque2= false;
		boolean bloque3= false;
		boolean bloque4= false;
		boolean bloque5= false;
		boolean bloque6= false;
		
		for(CordinacionPDF beanCordinacion : listaCordinaciones ){
			System.out.println("id_coordinacion:"+beanCordinacion.getId_coordinacion());
			if(bloque1){
				valorPosXReal = 0;
				valorPorYReal = 0;
				bloque1 = false;
				bloque2 = true;
			}else if(bloque2){
				valorPosXReal = 0 + variacionX;
				valorPorYReal = 0;
				bloque2 = false;
				bloque3 = true;
			}else if(bloque3){
				valorPosXReal = 0;
				valorPorYReal = 0+variacionY;
				bloque3 = false;
				bloque4 = true;
			}else if(bloque4){
				valorPosXReal = 0 + variacionX;
				valorPorYReal = 0 + variacionY; 
				bloque4 = false;
				bloque5 = true;
			}else if(bloque5){
				valorPosXReal = 0;
				valorPorYReal = 0 + variacionY + variacionY; 
				bloque5 = false;
				bloque6 = true;
			}else if(bloque6){
				valorPosXReal = 0 + variacionX;
				valorPorYReal = 0 + variacionY + variacionY; 
				bloque6 = false;
				bloque1 = true;
			}
			
			/************BORDES***********  BLOQUE 1*/
			PDFUtil.pintarLinea(writer, posX +valorPosXReal, posY-valorPorYReal, Constantes.anchoCargo, false, Constantes.colorLineasGuia);
			PDFUtil.pintarLinea(writer, posX +valorPosXReal , posY-valorPorYReal, Constantes.altoCargo, true, Constantes.colorLineasGuia);
			
			PDFUtil.pintarLinea(writer, posX+ valorPosXReal, posY-valorPorYReal + Constantes.altoCargo, Constantes.anchoCargo, false, Constantes.colorLineasGuia2);
			PDFUtil.pintarLinea(writer, posX+valorPosXReal  + Constantes.anchoCargo, posY-valorPorYReal, Constantes.altoCargo, true, Constantes.colorLineasGuia2);
			/************* 1er row */
			PDFUtil.absText(writer, beanCordinacion.getNombre_cliente().toUpperCase(), posXInicial +valorPosXReal, 825-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
			PDFUtil.absText(writer, beanCordinacion.getNombre_producto().toUpperCase(), 150 +valorPosXReal, 825-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
			/************* 2do row */
			PDFUtil.absText(writer, "Cargo "+beanCordinacion.getFecha_proceso(), posXInicial +valorPosXReal , 810-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
			/************* 3er row */
			BarcodeInter25 cb25 = new BarcodeInter25();
			if(beanCordinacion.getCodigo_barras().length()%2==0){
				cb25.setCode(beanCordinacion.getCodigo_barras());
			}else{
				cb25.setCode("0"+beanCordinacion.getCodigo_barras());
			}			
			Image imgcb25 = cb25.createImageWithBarcode( writer.getDirectContent(), null, null);			 
			imgcb25.setAbsolutePosition(posXInicial + valorPosXReal, 775-valorPorYReal);
			imgcb25.scaleAbsolute(Constantes.anchoCBH, Constantes.altoCBH);
			document.add(imgcb25);
			
			PDFUtil.absText(writer, beanCordinacion.getNumero_tarjeta(), 190 +valorPosXReal, 790-valorPorYReal, false, Constantes.sizeTextPriRei, Constantes.colorTexto);
			/************* 4er row */
			PDFUtil.absText(writer, "Destinatario:", posXInicial +valorPosXReal, 760-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
			/************* 4er row */
			PDFUtil.absText(writer, beanCordinacion.getNombre_persona().toUpperCase(), posXInicial +valorPosXReal, 750-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
			/************* 5to row */
			PDFUtil.absText(writer, "Tel�fono:", posXInicial +valorPosXReal, 735-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
			PDFUtil.absText(writer, beanCordinacion.getTelefono_persona()!=null?beanCordinacion.getTelefono_persona():"", posXInicial +valorPosXReal, 725-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
			/************* 6to row  TENDRA 3 ESPACIOS DISPONIBLES*/ 
			PDFUtil.absText(writer, "Direccion Coordinada:", posXInicial +valorPosXReal, 710-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
			String direccion = beanCordinacion.getDireccion_coordinada().toUpperCase();
			int lenghtDirecc = direccion.length();	
						
			if(lenghtDirecc > 72){//1ER ROW
				PDFUtil.absText(writer, direccion.substring(0,72),  posXInicial + valorPosXReal , 700-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
				if(lenghtDirecc > 144){//2DO ROW
					PDFUtil.absText(writer, direccion.substring(72,143)+".",  posXInicial +valorPosXReal , 690-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);					
				}else{
					PDFUtil.absText(writer, direccion.substring(72,direccion.length()),  posXInicial +valorPosXReal , 690-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
				}
			}else{
				PDFUtil.absText(writer, direccion,  posXInicial + valorPosXReal , 700-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
			}
			/*PDFUtil.absText(writer, "AV ANDRES TICONO NRO 193 OFICINA 201", posXInicial + valorPosXReal , 700-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
			PDFUtil.absText(writer, "A", posXInicial +valorPosXReal , 690-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
			PDFUtil.absText(writer, "A", posXInicial +valorPosXReal, 680-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
			*/
			/************* 7mo row */ 
			PDFUtil.absText(writer, "Distrito:", posXInicial +valorPosXReal , 675-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
			PDFUtil.absText(writer, "--", posXInicial +valorPosXReal , 665-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
			PDFUtil.absText(writer, "Fecha Coordinada:", 150 +valorPosXReal , 675-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
			PDFUtil.absText(writer, beanCordinacion.getFecha_coordinada()+" "+beanCordinacion.getHora_coordinada(), 150 +valorPosXReal, 665-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
			
			/************* 8Vo row  TENDRA EL RESTO DE ESPACIOS DISPONIBLES EL MAXIMO LENGHT POR FILA ES 72*/
			String Observacion =  beanCordinacion.getObservacion_coordinada().toUpperCase();
			PDFUtil.absText(writer, "Observaciones:", posXInicial +valorPosXReal , 650-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
			System.out.println("lenght:"+Observacion.length());	
			int lenghtObser = Observacion.length();	
			
			if(lenghtObser > 72){//1ER ROW
				PDFUtil.absText(writer, Observacion.substring(0,72), posXInicial + valorPosXReal, 640-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
				if(lenghtObser > 144){//2DO ROW
					PDFUtil.absText(writer, Observacion.substring(72,144), posXInicial +valorPosXReal, 630-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
					if(lenghtObser > 216){//3ER ROW
						PDFUtil.absText(writer, Observacion.substring(144,215)+".", posXInicial +valorPosXReal , 620-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
					}else{
						PDFUtil.absText(writer, Observacion.substring(144,Observacion.length()), posXInicial +valorPosXReal , 620-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
					}
				}else{
					PDFUtil.absText(writer, Observacion.substring(72,Observacion.length()), posXInicial +valorPosXReal, 630-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
				}
			}else{
				PDFUtil.absText(writer, Observacion, posXInicial + valorPosXReal, 640-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
			}		
			/*
			PDFUtil.absText(writer, Observacion.substring(0,72), posXInicial + valorPosXReal, 620-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
			PDFUtil.absText(writer, "BAJO", posXInicial +valorPosXReal, 610-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
			PDFUtil.absText(writer, "A", posXInicial +valorPosXReal , 610-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
			PDFUtil.absText(writer, "ALTURA DE LA CUADRA 5", posXInicial +valorPosXReal, 600-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
			*/
			/*************************/
			/*****REFERENCIAS***/
			String referencia = beanCordinacion.getReferencia_coordinada().toUpperCase();
			int lenghtRefencia = referencia.length();
			if(lenghtRefencia > 72){//1ER ROW
				PDFUtil.absText(writer, referencia.substring(0,72), posXInicial + valorPosXReal, 610-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
				if(lenghtRefencia > 144){//2DO ROW
					PDFUtil.absText(writer, referencia.substring(72,144), posXInicial +valorPosXReal, 600-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
					if(lenghtRefencia > 216){//3ER ROW
						PDFUtil.absText(writer, referencia.substring(144,215)+".", posXInicial +valorPosXReal , 590-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
					}else{
						PDFUtil.absText(writer, referencia.substring(144,referencia.length()), posXInicial +valorPosXReal , 590-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
					}
				}else{
					PDFUtil.absText(writer, referencia.substring(72,referencia.length()), posXInicial +valorPosXReal, 600-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
				}
			}else{
				PDFUtil.absText(writer, referencia, posXInicial + valorPosXReal, 610-valorPorYReal, false, Constantes.sizeTextGeneral, Constantes.colorTexto);
			}		
			
			if(bloque1){//CADA 6 IMPRIME HOJA NUEVA
				document.newPage();
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	
	@RequestMapping(value={"/descarga_base_courier.htm"}) 
	public String descargarBaseCourier(Model model, HttpServletRequest request, 
									HttpServletResponse response,	
									@ModelAttribute("USUARIO_INFO") Usuario usuarioInfo) throws IOException{
		System.out.println("-descarga_base_courier-");		
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.L1.name()));
		model.addAttribute("menuSelect", Menu.L4.name());	
		
		String op = request.getParameter("ordenProceso");
		List <ExportBaseCourier> listBase = new ArrayList<ExportBaseCourier>();
		String codigo_mensajero = usuarioInfo.getCodigo_courier();		
		if(codigo_mensajero!=null){				
			String  [] cod_msj = codigo_mensajero.split("_");
			for(String str : cod_msj){
				listBase.addAll(distribucionModel.getBaseCourier(op, str));
			}
		}
		System.out.println("-op:"+op);
		System.out.println("-codigo_mensajero:"+codigo_mensajero);
        if(listBase.size()>0){
        	try{
        	System.out.println("listBase:"+listBase.size());
			response.setContentType("application/vnd.ms-excel");
	        response.setHeader("Content-Disposition", "attachment; filename=Excel_Prueba.xls");
			
			HSSFWorkbook workbook = new HSSFWorkbook();
			
			HSSFSheet worksheet = workbook.createSheet("POI Worksheet");
	        HSSFCellStyle cellStyle2 = workbook.createCellStyle();
	        cellStyle2 = workbook.createCellStyle();
	        HSSFFont font2 = workbook.createFont();//Create font
	        font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//Make font bold
	        cellStyle2.setFont(font2);
	        // index from 0,0... cell A1 is cell(0,0)
	       
	        
	        /************** CABECERA **************/
	        HSSFRow row1 = worksheet.createRow((short) 0);
	        HSSFCell cellA = row1.createCell((short) 0);
	        cellA.setCellValue("HOJA DE RUTA");
	        cellA.setCellStyle(cellStyle2);
	        //worksheet.autoSizeColumn((short) 0);
	        HSSFCell cellB = row1.createCell((short) 1);
	        cellB.setCellValue("FECHA DE HOJA DE RUTA");
	        cellB.setCellStyle(cellStyle2);
	        //worksheet.autoSizeColumn((short) 1);
	        HSSFCell cellC = row1.createCell((short) 2);
	        cellC.setCellValue("CARGO");
	        cellC.setCellStyle(cellStyle2);        
	        //worksheet.autoSizeColumn((short) 2);
	        HSSFCell cellD = row1.createCell((short) 3);
	        cellD.setCellValue("DESTINATARIO");
	        cellD.setCellStyle(cellStyle2);
	        //worksheet.autoSizeColumn((short) 3);
	        HSSFCell cellE = row1.createCell((short) 4);
	        cellE.setCellValue("DIRECCION BASICA");
	        cellE.setCellStyle(cellStyle2);
	        //worksheet.autoSizeColumn((short) 4);
	        HSSFCell cellF = row1.createCell((short) 5);
	        cellF.setCellValue("DIRECCION ALTERNA 1");
	        cellF.setCellStyle(cellStyle2);
	        //worksheet.autoSizeColumn((short) 5);
	        HSSFCell cellG = row1.createCell((short) 6);
	        cellG.setCellValue("DIRECCION ALTERNA 2");
	        cellG.setCellStyle(cellStyle2);
	        //worksheet.autoSizeColumn((short) 6);
	        HSSFCell cell7 = row1.createCell((short) 7);
	        cell7.setCellValue("DIRECCION ALTERNA 3");
	        cell7.setCellStyle(cellStyle2);
	        /************ DATOS ************/
	        int indexRow = 1; // Inicio en la FIla 1       
	   //     System.out.println("-op 0");
	        for(ExportBaseCourier base :  listBase){
	    //    	 System.out.println("a)-op "+indexRow);
	        	 HSSFRow row2 = worksheet.createRow((short) indexRow);
	      //  	 System.out.println("b)-op "+indexRow);
	        	 HSSFCell cellA2 = row2.createCell((short) 0);
	       // 	 System.out.println("c)-op "+indexRow);
	             cellA2.setCellValue(base.getHoja_ruta());
	      //       System.out.println("d)-op "+indexRow);
	             //worksheet.autoSizeColumn((short) 0);
	             HSSFCell cellB2 = row2.createCell((short) 1);
	             cellB2.setCellValue(base.getFecha_hoja());
	       //      System.out.println("e)-op "+indexRow);
	            // worksheet.autoSizeColumn((short) 1);
	             HSSFCell cellC2 = row2.createCell((short) 2);
	             cellC2.setCellValue(base.getCodigo_barra());
	            
	             HSSFCell cellD2 = row2.createCell((short) 3);
	             cellD2.setCellValue(base.getTitular());
	        //     System.out.println("a)-op "+indexRow);
	             HSSFCell cellE2 = row2.createCell((short) 4);
	             cellE2.setCellValue(base.getDireccion_basica());
	          
	             HSSFCell cellF2 = row2.createCell((short) 5);
	             cellF2.setCellValue(base.getDireccion_alterna_1());
	          //   System.out.println("a)-op "+indexRow);
	             HSSFCell cellG2 = row2.createCell((short) 6);
	             cellG2.setCellValue(base.getDireccion_alterna_2());   
	             
	             HSSFCell cellH2 = row2.createCell((short) 7);
	             cellH2.setCellValue(base.getDireccion_alterna_3()); 
	            // System.out.println("z)-op "+indexRow);
	             indexRow++;
	        	 
	        }
	        worksheet.autoSizeColumn((short) 0);
	        worksheet.autoSizeColumn((short) 1);
	        worksheet.autoSizeColumn((short) 2);
	        worksheet.autoSizeColumn((short) 3);
	        worksheet.autoSizeColumn((short) 4);
	        worksheet.autoSizeColumn((short) 5);
	        worksheet.autoSizeColumn((short) 6);
	        worksheet.autoSizeColumn((short) 7);
	        workbook.write(response.getOutputStream()); // Write workbook to response.
	        response.getOutputStream().close();
        	}catch(Exception e){
	        	e.printStackTrace();
	        }
        }  else{
        	model.addAttribute("msjBusqueda","No se encontraron resultados.");
        }
        return "distribucion/descargar_base_courier";
	}
	
	@RequestMapping(value={"/descargar_coordinaciones.htm"}) 
	public String descargarCoordinaciones(Model model, HttpServletRequest request, HttpServletResponse response,	
											@ModelAttribute("USUARIO_INFO") Usuario usuarioInfo) throws IOException{		
		System.out.println("USUARIO codigo_courier:"+usuarioInfo.getCodigo_courier());
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.L1.name()));
		model.addAttribute("menuSelect", Menu.L3.name());
		String view ="distribucion/descargar_cordinaciones";			
		System.out.println("fecCoordinacion:"+request.getParameter("fecCoordinacion"));
		String fec_coo = request.getParameter("fecCoordinacion");
		List<CordinacionPDF> listaCordinaciones = new ArrayList<CordinacionPDF>();
		String codigo_mensajero = usuarioInfo.getCodigo_courier();		
		if(codigo_mensajero!=null){
			if(fec_coo!=null){				
				String  [] cod_msj = codigo_mensajero.split("_");
	
				for(String str : cod_msj){
					listaCordinaciones.addAll(distribucionModel.getCordinacionPDFVarios(CVDinamico.getDateFromString(fec_coo, "dd/MM/yyyy"), str));
				}				
				//listaCordinaciones = distribucionModel.getCordinacionPDFVarios(CVDinamico.getDateFromString(fec_coo, "dd/MM/yyyy"), cod_msj);			
			}
		}
		try {
			if(listaCordinaciones.size()>0){
				System.out.println("listaCordinaciones:"+listaCordinaciones.size());		
				response.setContentType("application/pdf");		
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				System.out.println("-------------download PDF----------");
					
				final javax.servlet.ServletContext servletContext = request.getSession()
						.getServletContext();
				final File tempDirectory = (File) servletContext
						.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "Generate_Report_"+System.currentTimeMillis()+".pdf";
				response.setHeader("Content-disposition", "attachment; " +
						"filename="+ fileName);
				Document document = new Document(PageSize.A4);
				PdfWriter writer;
				
				System.out.println("temperotyFilePath:"+temperotyFilePath);
				writer = PdfWriter.getInstance(document, new FileOutputStream(temperotyFilePath+"\\"+fileName));
				document.open();			
				buildPDF(writer, document,listaCordinaciones);				
			
				System.out.println("FUCK 3	");
				document.close();		
				java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
				baos = convertPDFToByteArrayOutputStream(temperotyFilePath+"\\"+fileName);
				OutputStream os = response.getOutputStream();
		        baos.writeTo(os);
		        os.flush();
		        System.out.println("FUCK 4	");
		        File pdf  = new File(temperotyFilePath+"\\"+fileName);
		        pdf.delete();
		        view ="";
  			}
		} catch (DocumentException de) {
			throw new IOException(de.getMessage());
		} catch (Exception e){
			e.printStackTrace();
		}
		return view;
	}
	
	@RequestMapping(value={"/getMotivos.htm"}) 
	public void drawComboBox(HttpServletRequest req, HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		response.setContentType("application/json");
		PrintWriter pw = response.getWriter();
		String idSituacion = req.getParameter("idSituacion");
		System.out.println("TipoSituacion:"+idSituacion);
		Estados estadoID = distribucionModel.getEstadoByIdEstado(idSituacion);
		String desc_estado = estadoID.getDes_estado();
		System.out.println("desc_estado:"+desc_estado);
		String show = "0";
		if(desc_estado.equals("ENTREGADO") || desc_estado.equals("ENTREGA")){
			show = "1";
		}		
		HttpSession session = req.getSession();   
        Distribucion beanOriginal = (Distribucion) session.getAttribute("beanOriginal");
        System.out.println("beanOriginal.getCodCli():"+beanOriginal.getCodCli());
		//List<Estados> motivos = distribucionModel.getEstadosToComboBox("2", idSituacion,beanOriginal.getCodCli());	//2 = motivo		
		//List<Estados> estados = distribucionModel.getEstadosToComboBox("3", idSituacion, beanOriginal.getCodCli());	//3 = situacion
		List<Estados> motivos = distribucionModel.getEstadosMotivosToComboBox("2", estadoID.getCod_estado(),null);	//2 = motivo		
		List<Estados> estados = distribucionModel.getEstadosMotivosToComboBox("3", estadoID.getCod_estado(), null);	//3 = situacion
		String view = "<option value=\"\">::SELECCIONAR::</option>";
		for(Estados motivo : motivos){
			view += "<option value=\""+motivo.getId_estado()+"\">"+motivo.getDes_estado()+"</option>";
		}
		String view2= "<option value=\"\">::SELECCIONAR::</option>";
		for(Estados estado : estados){
			view2 += "<option value=\""+estado.getId_estado()+"\">"+estado.getDes_estado()+"</option>";	
		}
		System.out.println("Estados: "+view);
		System.out.println("Estados: "+view2);
		JSONObject combo = new JSONObject();
		combo.put("combo1", view);
		combo.put("combo2", view2);
		combo.put("show", show);		
		response.getWriter().write(combo.toString());
	}
	
	@RequestMapping(value={"/getActionVinculo.htm"}) 
	public void getvinculoAction(HttpServletRequest req, HttpServletResponse response) throws IOException{
		try{
			response.setContentType("application/json");
			HttpSession session = req.getSession();
			Estados estado = distribucionModel.getEstadoByIdEstado(req.getParameter("id_vinculo"));
		JSONObject combo = new JSONObject();
		System.out.println("ESTADO:"+estado.getDes_estado());
		if(estado.getDes_estado().equals("TITULAR")){
			combo.put("isTitular", "1");
			Distribucion distribucion = (Distribucion) session.getAttribute("beanOriginal");
			combo.put("titular", distribucion.getTitNomApe());
		}else{
			combo.put("isTitular", "0");
		}
		
		if(estado.getDes_estado().equals("AGENCIA")){
			Distribucion distribucion = (Distribucion) session.getAttribute("beanOriginal");
			RegistroCoord regCoord = coordinacionModel.getLastCoordinacionByCodBar(distribucion.getCodBar());
			if(regCoord!=null){
				combo.put("isAgencia", "1");
				combo.put("agenciaCoord", regCoord.getNomAge());
			}else{
				combo.put("isAgencia", "2");
			}
		
		}else{
			combo.put("isAgencia", "0");
		}
		response.getWriter().write(combo.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value={"/save_gestion.htm"})
	public String saveGestion(Model model, HttpServletRequest req, 
			@ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
			
			@RequestParam("fecEntrega") String fecEntrega, 
			@RequestParam("hora") String hora, 
			@RequestParam("tipoSituacion") String tipoSituacion, 
			@RequestParam("tipoMotivo") String tipoMotivo, 
			@RequestParam("tipoEstado") String tipoEstado, 
			@RequestParam("tipoDomicilio") String tipoDomicilio, 
			@RequestParam("reciPor") String reciPor,
			@RequestParam("tipoVinculo") String tipoVinculo,
			@RequestParam("coment") String comentario){
		System.out.println("AQUIIIIIIIIIIIIIIIII- hora:        "+hora);
		String view = "distribucion/consulta_detalle_carga";
		try{
			System.out.println("AQUIIIIIIIIIIIIIIIII- horaaaaaa:        "+hora);
			model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.L1.name()));
			model.addAttribute("menuSelect", Menu.L2.name());
			HttpSession session = req.getSession();
			Distribucion distribucion = (Distribucion) session.getAttribute("beanOriginal");		
			System.out.println("CODIGO DE BARRA: "+distribucion.getCodBar());
			DistribucionVisita beanVisita = new DistribucionVisita();
			beanVisita= distribucionModel.getDistribucionVisitaXCodBar(distribucion.getCodBar());
			//beanVisita.setIdImport(0); 
			//beanVisita.setCodBar(distribucion.getCodBar());
			
			beanVisita.setHorVis(hora);
			
			/*DistribucionVisita ultimaVisita= (DistribucionVisita)session.getAttribute("ultimaVisita");
			System.out.println("AQUIIIIIIIIIIIIIIIII- hora:        "+ultimaVisita.getNroHoj());
			beanVisita.setNroHoj(((ultimaVisita.getNroHoj()	==	null) ? 0 : ultimaVisita.getNroHoj()));		
			beanVisita.setNomMsj(ultimaVisita.getNomMsj());
			beanVisita.setCodMsj(ultimaVisita.getCodMsj());*/
		
			
			if(!fecEntrega.equals("")){
				beanVisita.setFecHoj(CVDinamico.getDateFromString(fecEntrega, "dd/MM/yyyy"));
				distribucion.setFecEnt(CVDinamico.getDateFromString(fecEntrega, "dd/MM/yyyy"));
				distribucion.setFecHoj(CVDinamico.getDateFromString(fecEntrega, "dd/MM/yyyy"));
			}
			
			if(!tipoVinculo.equals("")){
				Estados vinculo = distribucionModel.getEstadoByIdEstado(tipoVinculo);				
				distribucion.setPerRec(reciPor);
				distribucion.setDesVin(vinculo.getDes_estado());
				distribucion.setIndVin(vinculo.getCod_estado());				
			}else{
				distribucion.setPerRec("");
				distribucion.setDesVin("");
				distribucion.setIndVin("");
			}
			//beanVisita.setCodMsj(0);
			//beanVisita.setNomMsj(mensajero);
			
			if(!tipoSituacion.equals("")){				
			//	System.out.println("tipoSituacion:"+tipoSituacion);
				Estados situacion = distribucionModel.getEstadoByIdEstado(tipoSituacion);
			//	System.out.println("situacion:"+situacion.getDes_estado());
				/*if(situacion.getCod_cliente().equals("0026")){System.out.println("CLIENTE ORBIS");
				//	DISTRIBUCION VISITA INSERT
					beanVisita.setIndSit(situacion.getCod_estado());
					beanVisita.setDesSit(situacion.getDes_estado());
				//	DISTRIBUCION UPDATE
					distribucion.setIndSit(situacion.getCod_estado());
					distribucion.setDesSit(situacion.getDes_estado());	
				}else{*/
				//	DISTRIBUCION VISITA INSERT
					beanVisita.setIndSit(situacion.getCod_estado());
					beanVisita.setDesSit(situacion.getDes_estado());
				//	DISTRIBUCION UPDATE
					distribucion.setIndSit(situacion.getCod_estado());
					distribucion.setDesSit(situacion.getDes_estado());	
				//}
		
				
				//ORBIS = 0026
			}
			if(!tipoMotivo.equals("")){
				//System.out.println("tipoMotivo:"+tipoMotivo);
				Estados motivo = distribucionModel.getEstadoByIdEstado(tipoMotivo);
				//System.out.println("motivo:"+motivo.getDes_estado());
				
					//DISTRIBUCION VISITA INSERT
					beanVisita.setCodMot(motivo.getCod_estado());
					beanVisita.setDesMot(motivo.getDes_estado());
					//DISTRIBUCION UPDATE
					distribucion.setCodMot(motivo.getCod_estado());
					distribucion.setDesMot(motivo.getDes_estado());	
				
			}
			if(!tipoEstado.equals("")){
				//System.out.println("tipoEstado:"+tipoEstado);
				Estados estado = distribucionModel.getEstadoByIdEstado(tipoEstado);
				//System.out.println("estado:"+estado.getDes_estado());	
				//if(estado.getCod_cliente().equals("0026")){System.out.println("CLIENTE ORBIS");
					distribucion.setIndEst(estado.getCod_estado());
					distribucion.setDesEst(estado.getDes_estado());	
				/*}else{
					distribucion.setIndEst(estado.getCod_estado());
					distribucion.setDesEst(estado.getDesc_estado_per());	
				}*/
			}
			//beanVisita.setHorVis("122500");
			if(!tipoDomicilio.equals("")){
				beanVisita.setDesTip(tipoDomicilio);
				if(tipoDomicilio.toUpperCase().equals("DOMICILIO")){
					beanVisita.setTipDir("1");
				}else if(tipoDomicilio.toUpperCase().equals("COORDINADA")){
					beanVisita.setTipDir("9");
				}else if(tipoDomicilio.toUpperCase().equals("LABORAL")){
					beanVisita.setTipDir("2");
				}else if(tipoDomicilio.toUpperCase().equals("OPCIONAL")){
					beanVisita.setTipDir("3");
				}
			}
			
			//beanVisita.setCodUsu("");
			//beanVisita.setNomUsu("");
			//beanVisita.setUsuCre(0);	
			beanVisita.setFecCre(new Date());
			beanVisita.setFromUi("1");
			beanVisita.setDescargado("1");System.out.println("comentario:"+comentario);
			beanVisita.setComentario(comentario);
	    	distribucionModel.actualizarDistribucion(distribucion);
	    	distribucionModel.update(beanVisita);
			//distribucionModel.guardar(beanVisita);
	    	HojaRuta hojaRuta=hojaRutaModel.getHojaRutaXNroHoja(beanVisita.getNroHoj());
	    	
	    	HojaRutaDetalle hd = new HojaRutaDetalle();
			hd=hojaRutaDetalleModel.getDetalleXCodBarIdRendicion(hojaRuta.getIdRuta(),distribucion.getCodBar());
			hd.setCarga(1);
			hojaRutaDetalleModel.update(hd);
			
			List<DistribucionVisita> distribucionVisitas = distribucionModel.findVisitas(distribucion.getCodBar());
			model.addAttribute("RPConsultaDetalleVisitas", distribucionVisitas);
			Integer numVisitas=distribucionVisitas.size();
			if(numVisitas>0){
				model.addAttribute("visitas","true");
			}
			model.addAttribute("RPConsultaDetalleCoordEntregado", distribucionModel.findDireccionEntrega(distribucionVisitas));
			List<DistribucionPaquete> distribucionPaquetes = distribucionModel.findPaquetes(distribucion.getCodBar());
			model.addAttribute("RPConsultaDetallePaquetes", distribucionPaquetes);
			List<Estados> estados = distribucionModel.getEstadosToComboBox("1", null, distribucion.getCodCli());					    
			model.addAttribute("TipoSituacion", estados);
			List<Estados> vinculo = distribucionModel.getEstadosToComboBox("4", null,null);					    
			model.addAttribute("TipoVinculo", vinculo);
			/**23/05/2016_czavalacas**/
			List<String> imagenesName = new ArrayList<String>();
			if(distribucion.getImgCarNom()!=null){				
				String ruta = cargaImgModel.cargoBuscarPathBase(distribucion.getImgCarNom());				
				if(ruta!=null){
				File carpetaOrigen = new File(ruta);				
				String nameFile = distribucion.getImgCarNom();
				if(!nameFile.equals(""))
					System.out.println("null.....");
					imagenesName =  getFilesByname(carpetaOrigen, nameFile);
				}else{
					System.out.println("else null.....");
					imagenesName.add("");
				}
				System.out.println("RUTA BASE DE CARGO:"+ruta+"-");
			}
			model.addAttribute("codBarImgs", imagenesName);
			if(distribucion.getIndSit().equals("2")){//2 = ENTREGADO
				model.addAttribute("canEdit", "");//NO SE PEUDE EDITAR ES ENTREGADO
			}else{
				model.addAttribute("canEdit", "1");//NO SE PEUDE EDITAR ES ENTREGADO
			}
			/******************************/		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return view;
	}	

	@RequestMapping(value={"/subir_img.htm"}, method = RequestMethod.POST)
	public String subirImagen(Model model, @RequestParam("file") MultipartFile file, HttpServletRequest request,
			@RequestParam("codBarImg") String codBarImg){
		String view="cargaimg/error_2";		
		String path =  request.getSession().getServletContext().getRealPath("dinamic/upload");
		System.out.println("--path:"+path);		
		
		try {			
			String logCarga="";
			byte[] bytes = file.getBytes();
			String dirDestino = "CARGOS"+CVDinamico.getDateInFormat("yyyy-MM-dd_hhmm");
			System.out.println("dirDestino:"+dirDestino);
			File dir = new File(path,dirDestino);
			if (!dir.exists())dir.mkdirs();
			
			log.info("Carpeta de Carga (Img-Cargos): "+dir);
			File uploadFile = new File(dir.getAbsolutePath(), file.getOriginalFilename());
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile));
			stream.write(bytes);
			stream.close();	 
			
			if(dirDestino!=null){								
				if(dir.exists()){
					File [] files = dir.listFiles();
					System.out.println("codBarImg:"+codBarImg);
					logCarga = cargaImgModel.cargoPublicarUno(dir.getAbsolutePath(), files[0].getName(),codBarImg);
					System.out.println("logCarga:"+dirDestino);					
				}
			}
			dir.delete();
			model.addAttribute("logCarga", logCarga);
			
			view="cargaimg/success_2";
		} catch (Exception e) {
			model.addAttribute("error", "true");
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.L1.name()));
		model.addAttribute("menuSelect", Menu.L2.name());
		model.addAttribute("ref", "cargo");
		
		return view;
	}
	
	
	@RequestMapping(value={"/buscar_hojaruta.htm"} )
	public String buscarHojaRuta(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response){
		System.out.println("-------buscar hoja ruta-------");
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.L1.name()));
		model.addAttribute("menuSelect", Menu.L5.name());
		String view="distribucion/buscar_hojaruta";
		try {			
			System.out.println("COD USUARIO: "+usuarioInfo.getCodigo());
			
			List<Estados> listMotivos=distribucionModel.getEstadosMotivos("2", null,null);
			String view2="";
			for(Estados estado : listMotivos){
				view2 += "<option value=\""+estado.getCod_estado()+"_"+estado.getDes_estado()+"_"+estado.getIdPadre()+"_"+"\">"+estado.getDes_estado()+"</option>";
			}
			model.addAttribute("listaMotivoss", view2);
			
			
			
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		System.out.println("Done");
		return view;
	}	
	
	
	@RequestMapping(value={"/consulta_buscar_hojaruta.htm"} )
	public String consultaBuscarHojaRuta(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response,
								@RequestParam("codBar") String codBarRuta,
								@RequestParam("codBarra") String codBarRutaHoja){
		System.out.println("-------consulta buscar hoja ruta-------");
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.L1.name()));
		model.addAttribute("menuSelect", Menu.L5.name());
		String tablaCodBar="";
		String view="distribucion/consulta_buscar_hojaruta";
		String situacion="";
		String vinc="";
		String tablita="";
		try {
			
				String codigoHojaRuta=hojaRutaModel.getCodHojaRutaxCod(codBarRutaHoja);			
				codBarRuta=codigoHojaRuta;
			
			
			HojaRuta hojaRuta= new HojaRuta();
			System.out.println("codigobarra: "+codBarRuta);
			
			hojaRuta = 	hojaRutaModel.getHojaRutaXCodBarRuta(codBarRuta);
            
            if(hojaRuta!=null){
            
            	List<Object> lista = hojaRutaDetalleModel.listaCargados(hojaRuta.getIdRuta());
            	
            	Integer cargados=lista.size();
            	
	            System.out.println("ESTADO CARGAR;: "+hojaRuta.getEstCarga());
	            if(hojaRuta.getEstCarga()==0){
				List<Object> listaCodigo=hojaRutaModel.listXCodBar(hojaRuta.getCodBarRuta());
				List<Estados> estados = distribucionModel.getEstadosMotivosToComboBox("1", null,null);
				List<Estados> vinculo = distribucionModel.getEstadosToComboBox("4", null,null);

			    for(Estados est : estados){
			       situacion+=getHTMLDinamicOption(est.getCod_estado()+"_"+est.getDes_estado()+"_", est.getDes_estado());
		        }
			    for(Estados vin : vinculo){
				       vinc+=getHTMLDinamicOption(vin.getId_estado(), vin.getDes_estado());
			    }
			    
				tablaCodBar = getHTMLTablaRutasCod(listaCodigo,situacion,vinc,hojaRutaModel);
				//model.addAttribute("listaCodigos", tablaCodBar);
				model.addAttribute("size", listaCodigo.size());
				model.addAttribute("piezas", hojaRuta.getPiezas());
				model.addAttribute("codBar", hojaRuta.getCodBarRuta());
				model.addAttribute("cargados", cargados);
				if(listaCodigo.size()>0){
					model.addAttribute("ruta", hojaRuta.getCodBarRuta());
					
				}
				//hojaRutaDetalleModel.
	            }else{
	            	model.addAttribute("estadoCarga", "true");
	            }
	            model.addAttribute("mostrar", "true");
	            
            }
            else{
            	
            	
            }
          //cambio ultimo A.CH.
            
            model.addAttribute("mostrar", "true");
		    List<Estados> listMotivos=distribucionModel.getEstadosMotivos("2", null,null);
			String view2="";
			for(Estados estado : listMotivos){
				view2 += "<option value=\""+estado.getCod_estado()+"_"+estado.getDes_estado()+"_"+estado.getIdPadre()+"_"+"\">"+estado.getDes_estado()+"</option>";
			}
			model.addAttribute("listaMotivoss", view2);
		    ////

		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		System.out.println("Done");
		return view;
	}
	
	@RequestMapping(value={"/consulta_buscar_cod_hojaruta.htm"} )
	public String consultaBuscarCodHojaRuta(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response,
								@RequestParam("codBar") String codBarRuta,
								@RequestParam("codBarra") String codBarRutaHoja,
								@RequestParam("codMotivo") String codMotivo){
		System.out.println("-------consulta buscar hoja ruta-------");
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.L1.name()));
		model.addAttribute("menuSelect", Menu.L5.name());
		String tablaCodBar="";
		String view="distribucion/consulta_buscar_hojaruta";
		String situacion="";
		String vinc="";
		String codMot="";
		String desMot="";
		String padre="";
		String view2="";
		List<Estados> listMotivos=distribucionModel.getEstadosMotivos("2", null,null);
		String tablita="";
		try {
			
				String codigoHojaRuta=hojaRutaModel.getCodHojaRutaxCod(codBarRutaHoja);			
				codBarRuta=codigoHojaRuta;
			
			Integer codBarPqr=distribucionModel.getCodbarPqr(codBarRutaHoja);
			List<Object> codBarCoord=distribucionModel.getCodbarCoordinacion(codBarRutaHoja);
			Distribucion distribucion=distribucionModel.getDistribucionXCodBar(codBarRutaHoja);
			//Distribucion paraRendicion = distribucionModel.getRendicionXCodBar(codBarRutaHoja);
			
			HojaRuta hojaRuta= new HojaRuta();
			System.out.println("codigobarraRuta: "+codBarRuta+ " codigoDetalle "+codBarRutaHoja+" codMOT"+codMotivo);

            hojaRuta = 	hojaRutaModel.getHojaRutaXCodBarRuta(codBarRuta);
            Integer cargados=0;
            
            if(hojaRuta!=null){
            	List<Object> lista = hojaRutaDetalleModel.listaCargados(hojaRuta.getIdRuta());
            	cargados=lista.size();
            
            if(hojaRuta.getEstCarga()==0){
			List<Object> listaCodigo=hojaRutaModel.listXCodBar(codBarRuta);
			List<Estados> estados = distribucionModel.getEstadosMotivosToComboBox("1", null,null);
			List<Estados> vinculo = distribucionModel.getEstadosToComboBox("4", null,null);
			System.out.println("tama�o situacion: "+estados.size());
			codMot=codMotivo.split("_")[0];
			desMot=codMotivo.split("_")[1];
			padre=codMotivo.split("_")[2];
		    for(Estados est : estados){
		       situacion+=getHTMLComboGestiones(est.getCod_estado()+"_"+est.getDes_estado()+"_", est.getDes_estado(),padre);
	        }
		    for(Estados vin : vinculo){
		    	
			       vinc+=getHTMLComboGestionesVinculo(vin.getId_estado(), vin.getDes_estado(),"7126",padre);
		    }
		    
			
			for(Estados estado : listMotivos){
				
				if((estado.getCod_estado()+"_"+estado.getDes_estado()+"_"+estado.getIdPadre()+"_").equals(codMotivo)){
					view2 += "<option value=\""+estado.getCod_estado()+"_"+estado.getDes_estado()+"_"+estado.getIdPadre()+"_"+"\" selected='selected'>"+estado.getDes_estado()+"</option>";
				}else{
					view2 += "<option value=\""+estado.getCod_estado()+"_"+estado.getDes_estado()+"_"+estado.getIdPadre()+"_"+"\">"+estado.getDes_estado()+"</option>";
				}
			}
			model.addAttribute("listaMotivoss", view2);
		    //valida que codigo de barra exista en hoja de ruta
		    List<Object> listaExiste = hojaRutaDetalleModel.existeCodEnHojaRuta(hojaRuta.getIdRuta(),codBarRutaHoja);
		    System.out.println("lista existe "+listaExiste.size());
		    if(listaExiste.size()>0){
		    	String titular="";
		    	String fechaHR = hojaRuta.getFecha().toString().substring(8)+'/'+hojaRuta.getFecha().toString().substring(5,7)+'/'+hojaRuta.getFecha().toString().substring(0,4);
		    	if(padre.equals("2")){
		    		if(distribucion!=null){
		    			titular=distribucion.getTitNomApe();
		    		}else
		    			titular="Agencia";
		    	}
		    	tablaCodBar = getHTMLTablaRutasCodBar(codBarRutaHoja,situacion,vinc,hojaRutaModel,fechaHR, titular,view2,codBarCoord,codBarPqr);
				model.addAttribute("listaCodigos", tablaCodBar);
				model.addAttribute("datos", "true");
				if(codBarPqr==2){
					model.addAttribute("pqr", 2);
				}
				
		    }else{
		    	model.addAttribute("aviso", "true");
		    }

			model.addAttribute("size", listaCodigo.size());
			model.addAttribute("piezas", hojaRuta.getPiezas());
			model.addAttribute("codBar", codBarRuta);
			model.addAttribute("cargados", cargados);
			if(listaCodigo.size()>0){
				model.addAttribute("ruta", codBarRuta);
				//model.addAttribute("datos", "true");
			}
			//hojaRutaDetalleModel.
			model.addAttribute("mostrar", "true");
            }else{
            	model.addAttribute("estadoCarga", "true");
            	
            }
            tablita=getHTMLTablaRutasCod(hojaRutaDetalleModel.getHojaRutaXID(hojaRuta.getIdRuta()));
            model.addAttribute("ListaPiezas", tablita);
            }else{
            	model.addAttribute("estadoCodBar", "true");
            	
            }
//            for(Estados estado : listMotivos){
//				
//				if((estado.getCod_estado()+"_"+estado.getDes_estado()+"_"+estado.getIdPadre()+"_").equals(codMotivo)){
//					view2 += "<option value=\""+estado.getCod_estado()+"_"+estado.getDes_estado()+"_"+estado.getIdPadre()+"_"+"\" selected='selected'>"+estado.getDes_estado()+"</option>";
//				}else{
//					view2 += "<option value=\""+estado.getCod_estado()+"_"+estado.getDes_estado()+"_"+estado.getIdPadre()+"_"+"\">"+estado.getDes_estado()+"</option>";
//				}
//			}
//			model.addAttribute("listaMotivoss", view2);
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		System.out.println("Done");
		return view;
	}
	
	@RequestMapping(value={"/getEstadosMotivos.htm"}) 
	public void getEstadosMotivos(HttpServletRequest req, HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		response.setContentType("application/json");
		PrintWriter pw = response.getWriter();
		String idSituacion = req.getParameter("idSituacion");
		String situa = (idSituacion.split("_")[0]);
		System.out.println("SITUACION: "+situa);
		HttpSession session = req.getSession();   
		List<Estados> motivos = distribucionModel.getEstadosMotivosToComboBox("2", situa,null);	//2 = motivo		
		List<Estados> estados = distribucionModel.getEstadosMotivosToComboBox("3", situa, null);	//3 = situacion
		String view = "<option value=\"\">::SELECCIONAR::</option>";
		for(Estados motivo : motivos){
			view += "<option value=\""+motivo.getCod_estado()+"_"+motivo.getDes_estado()+"_"+"\">"+motivo.getDes_estado()+"</option>";
		}
		String view2= "<option value=\"\">::SELECCIONAR::</option>";
		for(Estados estado : estados){
			view2 += "<option value=\""+estado.getCod_estado()+"_"+estado.getDes_estado()+"_"+"\">"+estado.getDes_estado()+"</option>";	
		}
		JSONObject combo = new JSONObject();
		combo.put("combo1", view);
		combo.put("combo2", view2);	
		response.getWriter().write(combo.toString());
	}
	
	@RequestMapping(value={"/guardar_estados.htm"}) //guarda estados a toda la hoja de ruta
	public void guardarEstados(Model model, HttpServletRequest req, HttpServletResponse response,
			@ModelAttribute("USUARIO_INFO") Usuario usuarioInfo
			)throws IOException{
		//response.setContentType("text/html;charset=UTF-8");
		response.setContentType("application/json");
		//PrintWriter pw = response.getWriter();
		
		String id_ruta = req.getParameter("id_ruta");
		JSONObject myjsonRutas = new JSONObject(req.getParameter("jsonRutas"));
		JSONArray the_json_array = myjsonRutas.getJSONArray("rutas");
		
		String view = "distribucion/consulta_buscar_hojaruta";
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.L1.name()));
		model.addAttribute("menuSelect", Menu.L5.name());
		Integer cargados=0;
		try{
			List<Object> visita;
			String nHoja="";
			String fecha="";
			String codMensajero="";
			String nomMensajero="";

            visita = distribucionModel.getDatosXCodigo(id_ruta);
            Iterator itr = visita.iterator();
            while(itr.hasNext()){
               Object[] obj = (Object[]) itr.next(); 
               nHoja=obj[0].toString();
               fecha=obj[1].toString();
               codMensajero=obj[2].toString();
               nomMensajero=(obj[3]!=null?obj[3].toString():"")+" "+(obj[4]!=null?obj[4].toString():"")+" "+(obj[5]!=null?obj[5].toString():"");
            }         
            Date convertedDate = new Date();
    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    		convertedDate = dateFormat.parse(fecha);
    		String codigo="";
			String codSit="";
			String desSit="";
			String codEst="";
			String desEst="";
			String codMot="";
			String desMot="";
			String domicilio="";
			String vinculo="";
			String recibido="";
			String fecRec="";
			String hora="";
			String comentario="";
			
			HojaRuta hojaRuta= new HojaRuta();
            hojaRuta = 	hojaRutaModel.getHojaRutaXCodBarRuta(id_ruta);
            
			hojaRuta.setEstCarga(0);
			//for(int i = 0; i < the_json_array.length(); i++)
			int tama�oTabla=1;
			for(int i = 0; i < tama�oTabla; i++){
				codigo = the_json_array.getJSONObject(i).getString("codigo");
				///////////para rendicion///////////////////
				Rendicion rendicion=rendicionModel.getRendicionXCodBarRendicion(codigo);
				if(rendicion!=null){
					List<Object> renDetalle= rendicionDetalleModel.listaRendicionXCodBar(rendicion.getCodBarRendicion());
					codSit = (the_json_array.getJSONObject(i).getString("situacion").split("_")[0]);
					desSit = (the_json_array.getJSONObject(i).getString("situacion").split("_")[1]);
					if(!the_json_array.getJSONObject(i).getString("estado").equals("")){
						codEst = (the_json_array.getJSONObject(i).getString("estado").split("_")[0]);
						desEst = (the_json_array.getJSONObject(i).getString("estado").split("_")[1]);
					}else{
						codEst="";
						desEst="";
					}
					codMot = (the_json_array.getJSONObject(i).getString("motivo").split("_")[0]);
					desMot = (the_json_array.getJSONObject(i).getString("motivo").split("_")[1]);
					domicilio=the_json_array.getJSONObject(i).getString("domicilio");
					vinculo=the_json_array.getJSONObject(i).getString("vinculo");
					recibido=the_json_array.getJSONObject(i).getString("recibido");
					fecRec=the_json_array.getJSONObject(i).getString("fecRec");
					hora=the_json_array.getJSONObject(i).getString("hora");
					comentario=the_json_array.getJSONObject(i).getString("comentario");
					System.out.println("COD y DES: "+codSit+" "+desSit);
					
					rendicion.setCodSituacion(codSit);
					rendicion.setSituacion(desSit);
					rendicion.setCodMotivo(codMot);
					rendicion.setMotivo(desMot);
					rendicionModel.update(rendicion);
					Iterator itr1 = renDetalle.iterator();
			        while(itr1.hasNext()){
			           Object[] obj1 = (Object[]) itr1.next();    
					
						Distribucion distribucion = new Distribucion();
						distribucion = distribucionModel.getDistribucionXCodBar(obj1[1].toString());
			            distribucion.setIndSit(codSit);
			            distribucion.setDesSit(desSit);
			            distribucion.setIndEst(codEst);
			            distribucion.setDesEst(desEst);
			            distribucion.setCodMot(codMot);
			            distribucion.setDesMot(desMot);
			            
			            distribucion.setFecUltima(CVDinamico.getDateFromString(fecRec, "dd/MM/yyyy"));
			            distribucion.setFecUltimaVisita(CVDinamico.getDateFromString(fecRec, "dd/MM/yyyy"));
			            distribucion.setResUltimaVisita(desMot);
			            distribucion.setLugarUltimo(domicilio);
			            if(codSit.equals("2")){
			            	distribucion.setTipoEntrega("Agencia");
			            }
			            System.out.println("CODIGO BARRA de rendicion: "+obj1[1].toString());
			            DistribucionVisita distribucionVisita = new DistribucionVisita();
			            distribucionVisita= distribucionModel.getDistribucionVisitaXCodBar(obj1[1].toString());
			            Integer max=distribucionModel.idImportMax()+1;
			            distribucionVisita.setIdImport(max);
			            distribucionVisita.setCodBar(obj1[1].toString());
			            distribucionVisita.setNroHoj(Integer.parseInt(nHoja));
			            distribucionVisita.setFecHoj(convertedDate);
			            distribucionVisita.setCodMsj(Integer.parseInt(codMensajero));
			            distribucionVisita.setNomMsj(nomMensajero);
			            distribucionVisita.setIndSit(codSit);
			            distribucionVisita.setDesSit(desSit);
			            distribucionVisita.setCodMot(codMot);
			            distribucionVisita.setDesMot(desMot);
			            distribucionVisita.setResultadoVisita(desMot);
			            distribucionVisita.setGestionVisita(desMot);
			            System.out.println("cod usuario: "+usuarioInfo.getCodigo());
			            System.out.println("cod usuario: "+usuarioInfo.getNombres());
			            distribucionVisita.setCodUsu(usuarioInfo.getCodigo());
			            distribucionVisita.setNomUsu(usuarioInfo.getNombres()+" "+usuarioInfo.getApellidos());
			            
			            if(!domicilio.equals("")){
			            	distribucionVisita.setDesTip(domicilio);
			            	distribucionVisita.setLugarVisita(domicilio);
							if(domicilio.toUpperCase().equals("DOMICILIO")){
								distribucionVisita.setTipDir("1");
							}else if(domicilio.toUpperCase().equals("COORDINADA")){
								distribucionVisita.setTipDir("9");
							}else if(domicilio.toUpperCase().equals("LABORAL")){
								distribucionVisita.setTipDir("2");
							}else if(domicilio.toUpperCase().equals("OPCIONAL")){
								distribucionVisita.setTipDir("3");
							}
						}
			            
			            if(!vinculo.equals("")){
							Estados tipoVinculo = distribucionModel.getEstadoByIdEstado(vinculo);				
							distribucion.setPerRec(recibido);
							distribucion.setDesVin(tipoVinculo.getDes_estado());
							distribucion.setIndVin(tipoVinculo.getCod_estado());				
						}else{
							distribucion.setPerRec("");
							distribucion.setDesVin("");
							distribucion.setIndVin("");
						}
			            
			            if(!fecRec.equals("")){
			            	distribucionVisita.setFecHoj(CVDinamico.getDateFromString(fecRec, "dd/MM/yyyy"));
							distribucion.setFecEnt(CVDinamico.getDateFromString(fecRec, "dd/MM/yyyy"));
							distribucion.setFecHoj(CVDinamico.getDateFromString(fecRec, "dd/MM/yyyy"));
						}
			            System.out.println("HORA; "+hora);
			            distribucionVisita.setHorVis(hora);
			            distribucionVisita.setFecCre(new Date());
			            distribucionVisita.setFromUi("1");
			            distribucionVisita.setDescargado("1");
			            System.out.println("comentario:"+comentario+" id "+distribucionVisita.getId());
			            distribucionVisita.setComentario(comentario);
			            distribucionModel.update(distribucionVisita);
			            System.out.println("SE CREO DISTRIBUCION VISITA con rendicion");
			            distribucionModel.actualizarDistribucion(distribucion);
			            System.out.println("SE ACTUALIZO DISTRIBUCION con rendicion");
			        }
				}
				//////////////////////////////
				else{
				codSit = (the_json_array.getJSONObject(i).getString("situacion").split("_")[0]);
				desSit = (the_json_array.getJSONObject(i).getString("situacion").split("_")[1]);
				if(!the_json_array.getJSONObject(i).getString("estado").equals("")){
					codEst = (the_json_array.getJSONObject(i).getString("estado").split("_")[0]);
					desEst = (the_json_array.getJSONObject(i).getString("estado").split("_")[1]);
				}else{
					codEst="";
					desEst="";
				}
				codMot = (the_json_array.getJSONObject(i).getString("motivo").split("_")[0]);
				desMot = (the_json_array.getJSONObject(i).getString("motivo").split("_")[1]);
				domicilio=the_json_array.getJSONObject(i).getString("domicilio");
				vinculo=the_json_array.getJSONObject(i).getString("vinculo");
				recibido=the_json_array.getJSONObject(i).getString("recibido");
				fecRec=the_json_array.getJSONObject(i).getString("fecRec");
				hora=the_json_array.getJSONObject(i).getString("hora");
				comentario=the_json_array.getJSONObject(i).getString("comentario");
				System.out.println("COD y DES: "+codSit+" "+desSit+" CODIGO: "+codigo);
				Distribucion distribucion = new Distribucion();
				distribucion = distribucionModel.getDistribucionXCodBar(codigo);
	            distribucion.setIndSit(codSit);
	            distribucion.setDesSit(desSit);
	            distribucion.setIndEst(codEst);
	            distribucion.setDesEst(desEst);
	            distribucion.setCodMot(codMot);
	            distribucion.setDesMot(desMot);
	            distribucion.setFecUltima(CVDinamico.getDateFromString(fecRec, "dd/MM/yyyy"));
	            distribucion.setFecUltimaVisita(CVDinamico.getDateFromString(fecRec, "dd/MM/yyyy"));
	            distribucion.setResUltimaVisita(desMot);
	            distribucion.setLugarUltimo(domicilio);
	            if(codSit.equals("2")){
	            	distribucion.setTipoEntrega("Cliente");
	            }
	            DistribucionVisita distribucionVisita1 = new DistribucionVisita();
	            distribucionVisita1= distribucionModel.getDistribucionVisitaXCodBar(codigo);
	            int max=distribucionModel.idImportMax()+1;
	            System.out.println("maximo: "+max+" "+distribucionVisita1.getCodBar()+" ID "+distribucionVisita1.getId());
	            //distribucionVisita1=distribucionModel.getDistribucionVisitaXID(distribucionVisita1.getId());
	            
	            distribucionVisita1.setIdImport(max);
	            distribucionVisita1.setCodBar(codigo);
	            distribucionVisita1.setNroHoj(Integer.parseInt(nHoja));
	            distribucionVisita1.setFecHoj(convertedDate);
	            distribucionVisita1.setCodMsj(Integer.parseInt(codMensajero));
	            distribucionVisita1.setNomMsj(nomMensajero);
	            distribucionVisita1.setIndSit(codSit);
	            distribucionVisita1.setDesSit(desSit);
	            distribucionVisita1.setCodMot(codMot);
	            distribucionVisita1.setDesMot(desMot);
	            distribucionVisita1.setResultadoVisita(desMot);
	            distribucionVisita1.setGestionVisita(desMot);
	            System.out.println("cod usuario: "+usuarioInfo.getCodigo());
	            System.out.println("cod usuario: "+usuarioInfo.getNombres());
	            distribucionVisita1.setCodUsu(usuarioInfo.getCodigo());
	            distribucionVisita1.setNomUsu(usuarioInfo.getNombres()+" "+usuarioInfo.getApellidos());
	            
	            if(!domicilio.equals("")){
	            	distribucionVisita1.setLugarVisita(domicilio);
	            	distribucionVisita1.setDesTip(domicilio);
					if(domicilio.toUpperCase().equals("DOMICILIO")){
						distribucionVisita1.setTipDir("1");
					}else if(domicilio.toUpperCase().equals("COORDINADA")){
						distribucionVisita1.setTipDir("9");
					}else if(domicilio.toUpperCase().equals("LABORAL")){
						distribucionVisita1.setTipDir("2");
					}else if(domicilio.toUpperCase().equals("OPCIONAL")){
						distribucionVisita1.setTipDir("3");
					}
				}
	            
	            if(!vinculo.equals("")){
					Estados tipoVinculo = distribucionModel.getEstadoByIdEstado(vinculo);				
					distribucion.setPerRec(recibido);
					distribucion.setDesVin(tipoVinculo.getDes_estado());
					distribucion.setIndVin(tipoVinculo.getCod_estado());				
				}else{
					distribucion.setPerRec("");
					distribucion.setDesVin("");
					distribucion.setIndVin("");
				}
	            
	            if(!fecRec.equals("")){
	            	distribucionVisita1.setFecHoj(CVDinamico.getDateFromString(fecRec, "dd/MM/yyyy"));
					distribucion.setFecEnt(CVDinamico.getDateFromString(fecRec, "dd/MM/yyyy"));
					distribucion.setFecHoj(CVDinamico.getDateFromString(fecRec, "dd/MM/yyyy"));
				}
	            System.out.println("HORA; "+hora);
	            distribucionVisita1.setHorVis(hora);
	            distribucionVisita1.setFecCre(new Date());
	            distribucionVisita1.setFromUi("1");
	            distribucionVisita1.setDescargado("1");
	            System.out.println("comentario:"+comentario);
	            distribucionVisita1.setComentario(comentario);
	            distribucionModel.update(distribucionVisita1);
	            System.out.println("SE CREO DISTRIBUCION VISITA sin rendicion");
	            distribucionModel.actualizarDistribucion(distribucion);
	            System.out.println("SE ACTUALIZO DISTRIBUCION sin rendicion");
				}
				
				HojaRutaDetalle hd = new HojaRutaDetalle();
				hd=hojaRutaDetalleModel.getDetalleXCodBarIdRendicion(hojaRuta.getIdRuta(),codigo);
				hd.setCarga(1);
				hojaRutaDetalleModel.update(hd);
				
	        }
			List<Object> lista = hojaRutaDetalleModel.listaCargados(hojaRuta.getIdRuta());
            
        	cargados=lista.size();

            hojaRutaModel.update(hojaRuta);
    		System.out.println("se actualizo hoja ruta "+hojaRuta.getCodBarRuta());
    		model.addAttribute("mensaje", "Se guard� la gesti�n de hoja de ruta.");
    		model.addAttribute("datos", "false");
    		model.addAttribute("cargados", cargados);
			
		}catch(Exception e){
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		JSONObject combo = new JSONObject();
		combo.put("cargados", cargados);
		response.getWriter().write(combo.toString());
		//return view;
	}
	
	@RequestMapping(value={"/guardar_estados_Xcodbar.htm"}) 
	public String guardarEstadosXcodbar(Model model, HttpServletRequest req, 
			@ModelAttribute("USUARIO_INFO") Usuario usuarioInfo,
			@RequestParam("id_ruta") String id_ruta,
			@RequestParam("codigo") String codigo,
			@RequestParam("situacion") String situacion,
			@RequestParam("estado") String estado,
			@RequestParam("motivo") String motivo,
			@RequestParam("domicilio") String domicilio,
			@RequestParam("vinculo") String vinculo,
			@RequestParam("recibido") String recibido,
			@RequestParam("fecRec") String fecRec,
			@RequestParam("hora") String hora,
			@RequestParam("comentario") String comentario
			){
		//String id_ruta = req.getParameter("id_ruta");
		//JSONObject myjsonRutas = new JSONObject(req.getParameter("jsonRutas"));
		
		/*String codigo=req.getParameter("codigo");
		String situacion=req.getParameter("situacion");
		String estado=req.getParameter("estado");
		String motivo=req.getParameter("motivo");
		String domicilio=req.getParameter("domicilio");
		String vinculo=req.getParameter("vinculo");
		String recibido=req.getParameter("codigo");
		String fecRec=req.getParameter("fecRec");
		String hora=req.getParameter("hora");
		String comentario=req.getParameter("comentario");*/
		
		
		//JSONArray the_json_array = myjsonRutas.getJSONArray("rutas");

		String view = "distribucion/consulta_buscar_hojaruta";
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.L1.name()));
		model.addAttribute("menuSelect", Menu.L5.name());
		try{
			List<Object> visita;
			String nHoja="";
			String fecha="";
			String codMensajero="";
			String nomMensajero="";
			
            visita = distribucionModel.getDatosXCodigo(id_ruta);
            Iterator itr = visita.iterator();
            while(itr.hasNext()){
               Object[] obj = (Object[]) itr.next(); 
               nHoja=obj[0].toString();
               fecha=obj[1].toString();
               codMensajero=obj[2].toString();
               nomMensajero=obj[3].toString()+" "+obj[4].toString()+" "+obj[5].toString();
            }         
            Date convertedDate = new Date();
    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    		convertedDate = dateFormat.parse(fecha);
    		 
			String codSit="";
			String desSit="";
			String codEst="";
			String desEst="";
			String codMot="";
			String desMot="";

			System.out.println("enttro ghfghfgh");
				//codigo = codigo;
				codSit = (situacion).split("_")[0];
				desSit = (situacion).split("_")[1];
				System.out.println("SITUACION: "+situacion);
				if((estado).equals("")){
					codEst = ("estado").split("_")[0];
					desEst =("estado").split("_")[1];
				}else{
					codEst="";
					desEst="";
				}
				codMot = (motivo).split("_")[0];
				desMot = ("motivo").split("_")[1];
				//domicilio=domicilio;
				//vinculo=vinculo;
				//recibido=recibido;
				//fecRec=fecRec;
				//hora=hora;
				//comentario=comentario;
				System.out.println("COD y DES: "+codSit+" "+desSit);
				Distribucion distribucion = new Distribucion();
				distribucion = distribucionModel.getDistribucionXCodBar(codigo);
	            distribucion.setIndSit(codSit);
	            distribucion.setDesSit(desSit);
	            distribucion.setIndEst(codEst);
	            distribucion.setDesEst(desEst);
	            distribucion.setCodMot(codMot);
	            distribucion.setDesMot(desMot);
	            
	            DistribucionVisita distribucionVisita = new DistribucionVisita();
	            distribucionVisita= distribucionModel.getDistribucionVisitaXCodBar(codigo);
	            
	            Integer max=distribucionModel.idImportMax()+1;
	            distribucionVisita.setIdImport(max);
	            distribucionVisita.setCodBar(codigo);
	            distribucionVisita.setNroHoj(Integer.parseInt(nHoja));
	            distribucionVisita.setFecHoj(convertedDate);
	            distribucionVisita.setCodMsj(Integer.parseInt(codMensajero));
	            distribucionVisita.setNomMsj(nomMensajero);
	            distribucionVisita.setIndSit(codSit);
	            distribucionVisita.setDesSit(desSit);
	            distribucionVisita.setCodMot(codMot);
	            distribucionVisita.setDesMot(desMot);
	            distribucionVisita.setCodUsu(usuarioInfo.getCodigo());
	            distribucionVisita.setNomUsu(usuarioInfo.getNombres()+" "+usuarioInfo.getApellidos());
	            
	            if(!domicilio.equals("")){
	            	distribucionVisita.setDesTip(domicilio);
					if(domicilio.toUpperCase().equals("DOMICILIO")){
						distribucionVisita.setTipDir("1");
					}else if(domicilio.toUpperCase().equals("COORDINADA")){
						distribucionVisita.setTipDir("9");
					}else if(domicilio.toUpperCase().equals("LABORAL")){
						distribucionVisita.setTipDir("2");
					}else if(domicilio.toUpperCase().equals("OPCIONAL")){
						distribucionVisita.setTipDir("3");
					}
				}
	            
	            if(!vinculo.equals("")){
					Estados tipoVinculo = distribucionModel.getEstadoByIdEstado(vinculo);				
					distribucion.setPerRec(recibido);
					distribucion.setDesVin(tipoVinculo.getDes_estado());
					distribucion.setIndVin(tipoVinculo.getCod_estado());				
				}else{
					distribucion.setPerRec("");
					distribucion.setDesVin("");
					distribucion.setIndVin("");
				}
	            
	            if(!fecRec.equals("")){
	            	distribucionVisita.setFecHoj(CVDinamico.getDateFromString(fecRec, "dd/MM/yyyy"));
					distribucion.setFecEnt(CVDinamico.getDateFromString(fecRec, "dd/MM/yyyy"));
					distribucion.setFecHoj(CVDinamico.getDateFromString(fecRec, "dd/MM/yyyy"));
				}
	            distribucionVisita.setHorVis(hora);
	            distribucionVisita.setFecCre(new Date());
	            distribucionVisita.setFromUi("1");
	            distribucionVisita.setDescargado("1");
	            System.out.println("comentario:"+comentario);
	            distribucionVisita.setComentario(comentario);
	            //distribucionModel.guardar(distribucionVisita);
	            distribucionModel.update(distribucionVisita);
	            System.out.println("SE ACTUALIZO DISTRIBUCION VISITA");
	            distribucionModel.actualizarDistribucion(distribucion);
	            System.out.println("SE ACTUALIZO DISTRIBUCION");
	        
			
			
			
			HojaRuta hojaRuta= new HojaRuta();
            hojaRuta = 	hojaRutaModel.getHojaRutaXCodBarRuta(id_ruta);
			hojaRuta.setEstCarga(1);
            hojaRutaModel.update(hojaRuta);
    		System.out.println("se actualizo hoja ruta "+hojaRuta.getCodBarRuta());
    		model.addAttribute("mensaje", "Se guard� la gesti�n de hoja de ruta.");
    		model.addAttribute("datos", "false");
			
		}catch(Exception e){
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		return view;
	}
	
	@RequestMapping(value={"/buscar_rendicion.htm"} )
	public String buscarRendicion(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response){
		System.out.println("-------buscar rendicion-------");
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.L1.name()));
		model.addAttribute("menuSelect", Menu.L6.name());
		String view="distribucion/buscar_rendicion";
		try {			
			System.out.println("COD USUARIO: "+usuarioInfo.getCodigo());
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		System.out.println("Done");
		return view;
	}	
	
	@RequestMapping(value={"/consulta_buscar_rendicion.htm"} )
	public String consultaBuscarRendicion(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response,
								@RequestParam("codBar") String codBarRendicion){
		System.out.println("-------consulta buscar rendicion-------");
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.L1.name()));
		model.addAttribute("menuSelect", Menu.L6.name());
		String tablaCodBar="";
		String view="distribucion/consulta_buscar_rendicion";
		String situacion="";
		String vinc="";
		try {
			Rendicion rendicion= new Rendicion();
			rendicion = 	rendicionModel.getRendicionXCodBarRendicion(codBarRendicion);
            System.out.println("ESTADO CARGAR;: "+rendicion.getEstCarga());
            
            /*if(rendicion.getEstCarga()==0){
			List<Object> listaCodigo=hojaRutaModel.listXCodBar(codBarRendicion);
			List<Estados> estados = distribucionModel.getEstadosMotivosToComboBox("1", null,null);
			List<Estados> vinculo = distribucionModel.getEstadosToComboBox("4", null,null);
			//List<Estados> motivos = distribucionModel.getEstadosToComboBox("2", idSituacion,beanOriginal.getCodCli());	//2 = motivo		
			//List<Estados> estados = distribucionModel.getEstadosToComboBox("3", idSituacion, beanOriginal.getCodCli());	//3 = situacion
			System.out.println("tama�o situacion: "+estados.size());
			
		    for(Estados est : estados){
		       situacion+=getHTMLDinamicOption(est.getCod_estado()+"_"+est.getDes_estado()+"_", est.getDes_estado());
	        }
		    for(Estados vin : vinculo){
			       vinc+=getHTMLDinamicOption(vin.getId_estado(), vin.getDes_estado());
		    }
			tablaCodBar = getHTMLTablaRutasCod(listaCodigo,situacion,vinc);
			model.addAttribute("listaCodigos", tablaCodBar);
			model.addAttribute("size", listaCodigo.size());
			if(listaCodigo.size()>0){
				model.addAttribute("ruta", codBarRendicion);
				model.addAttribute("datos", "true");
			}
			//hojaRutaDetalleModel.
            }else{
            	model.addAttribute("estadoCarga", "true");
            }*/

		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		System.out.println("Done");
		return view;
	}
	
	public static String getHTMLDinamicOption(String value, String descripcion){
        return  "<option value=\""+value+"\">"+descripcion+"</option>";
    }
	
	public static String getHTMLComboGestiones(String value, String descripcion,String padre){
		String cod=value.split("_")[0];
		if(cod.equals(padre)){
			System.out.println("iguales");
			return  "<option value=\""+value+"\" selected='selected'>"+descripcion+"</option>";
		}else{
			return  "<option value=\""+value+"\">"+descripcion+"</option>";
		}
    }
	public static String getHTMLComboGestionesVinculo(String value, String descripcion,String padre,String entrega){
		String cod=value.split("_")[0];
		if(cod.equals(padre) && entrega.equals("2")){//si es entrega 
			System.out.println("iguales");
			return  "<option value=\""+value+"\" selected='selected'>"+descripcion+"</option>";
		}else{
			return  "<option value=\""+value+"\">"+descripcion+"</option>";
		}
    }
	
	public static String getHTMLTablaRutasCod(List<Object> listaRutas, String situacion, String vinc, HojaRutaModel hr){
		Integer i=1;
		
		Integer esRendicion=0;
        String outPut = 
                            "<table id=\"tableCodRutas\" >" +
                                "<thead>" +
                                    "<tr>" +
                                        "<td>Codigo</td>" +
                                        "<td>Situcacion</td>" +
                                        "<td>Estado</td>" +
                                        "<td>Motivo</td>" +
                                        "<td>Tipo Domicilio</td>" +
                                        "<td>Vinculo</td>" +
                                        "<td>Recibido</td>" +
                                        "<td>Fecha Entrega</td>" +
                                        "<td>Hora</td>" +
                                        "<td>Comentarios</td>" +
                                    "</tr>" +
                                "</thead>" +
                                "<tbody>";
        
        Iterator itr = listaRutas.iterator();
        while(itr.hasNext()){
           Object[] obj = (Object[]) itr.next();    
           
           
           
           if(hr.existeHojaRutaXCodBarRuta(obj[1].toString()).size()>0){
        	   outPut +=    "<tr class='<c:if test=\"${rowCounter.count % 2 == 0}\">on</c:if>'>" +
                       "<td>"+obj[1]+"</td>" +
                       "<td><select required=\"required\" id=\"tipoSituacion"+i+"\" name=\"tipoSituacion"+i+"\" onchange=\"selectSituacion("+i+")\">"+
       				"<option value=\"\">::SELECCIONAR::</option>"+
       				situacion+
       				"</select></td>" +
                       "<td><select  required=\"required\" id=\"tipoEstado"+i+"\" name=\"tipoEstado"+i+"\">"+
       				"<option value=\"\">::SELECCIONAR::</option>"+
       				"</select</td>" +
                       "<td><select  required=\"required\" id=\"tipoMotivo"+i+"\" name=\"tipoMotivo"+i+"\" >"+
       				"<option value=\"\">::SELECCIONAR::</option>"+
       				"</select></td>" +
       				"<td><select hidden=\"\" required=\"required\" id=\"tipoDomicilio"+i+"\" name=\"tipoDomicilio"+i+"\">"+
       				"<option value=\"\">::SELECCIONAR::</option>"+
       				"<option value=\"Domicilio\">DOMICILIO</option>"+
       				"<option value=\"Coordinada\">COORDINADA</option>"+
       				"<option value=\"Laboral\">LABORAL</option>"+
       				"<option value=\"Opcional\">OPCIONAL</option>"+
       				"</select></td>" +
       				"<td><select hidden=\"\" required=\"required\" id=\"tipoVinculo"+i+"\" name=\"tipoVinculo"+i+"\">"+
       				"<option value=\"\">::SELECCIONAR::</option>"+
       				vinc+
       				"</select></td>" +
       				"<td>"+
       				"<input minlength=\"3\" required=\"required\" id=\"reciPor"+i+"\" type=\"text\" name=\"reciPor"+i+"\" value=\"\" title=\"Ingrese nombre de Destinatario o Titular\" placeholder=\"destinatario o titular\" size=\"26\" maxlength=\"30\">"+
       				"</td>" +
       				"<td>"+
       				"<input type=\"text\" required=\"required\" name=\"fecEntrega"+i+"\" id=\"fecEntrega"+i+"\" size=\"10\" maxlength=\"10\" title=\"Fecha Entrega:\" placeholder=\"Dia/Mes/A�o\"/>"+
       				"</td>"+
       				"<td>"+
       				"<input id=\"hora"+i+"\" required=\"required\" type=\"time\" name=\"hora"+i+"\" value=\"${hora}\" title=\"Ingrese la hora\" placeholder=\"ingrese la hora de entrega\" size=\"26\" maxlength=\"30\">"+
       				"</td>"+
       				"<td>"+
       				"<textarea id=\"coment"+i+"\" name=\"coment"+i+"\" rows=\"4\" cols=\"50\" maxlength=\"500\" placeholder=\"Escribir un comentario....\" ></textarea>"+
       				"</td>"+
       				"<td style=\"width: 10px\">"+
       				"<div>"+      
	        			"<input readonly=\"readonly\" hidden=\"\" type=\"text\" id=\"codBar"+i+"\" value=\""+obj[1]+"\" name=\"codBar"+i+"\"/>"+
						"</div>"+
						"</td>"+
						
              		"</tr>";
           }
           else{
           outPut +=    "<tr class='<c:if test=\"${rowCounter.count % 2 == 0}\">on</c:if>'>" +
                            "<td>"+obj[1]+"</td>" +
                            "<td><select required=\"required\" id=\"tipoSituacion"+i+"\" name=\"tipoSituacion"+i+"\" onchange=\"selectSituacion("+i+")\">"+
            				"<option value=\"\">::SELECCIONAR::</option>"+
            				situacion+
            				"</select></td>" +
                            "<td><select  required=\"required\" id=\"tipoEstado"+i+"\" name=\"tipoEstado"+i+"\">"+
            				"<option value=\"\">::SELECCIONAR::</option>"+
            				"</select</td>" +
                            "<td><select  required=\"required\" id=\"tipoMotivo"+i+"\" name=\"tipoMotivo"+i+"\">"+
            				"<option value=\"\">::SELECCIONAR::</option>"+
            				"</select></td>" +
            				"<td><select  required=\"required\" id=\"tipoDomicilio"+i+"\" name=\"tipoDomicilio"+i+"\">"+
            				"<option value=\"\">::SELECCIONAR::</option>"+
            				"<option value=\"Domicilio\">DOMICILIO</option>"+
            				"<option value=\"Coordinada\">COORDINADA</option>"+
            				"<option value=\"Laboral\">LABORAL</option>"+
            				"<option value=\"Opcional\">OPCIONAL</option>"+
            				"</select></td>" +
            				"<td><select  required=\"required\" id=\"tipoVinculo"+i+"\" name=\"tipoVinculo"+i+"\">"+
            				"<option value=\"\">::SELECCIONAR::</option>"+
            				vinc+
            				"</select></td>" +
            				"<td>"+
            				"<input minlength=\"3\" required=\"required\" id=\"reciPor"+i+"\" type=\"text\" name=\"reciPor"+i+"\" value=\"\" title=\"Ingrese nombre de Destinatario o Titular\" placeholder=\"destinatario o titular\" size=\"26\" maxlength=\"30\">"+
            				"</td>" +
            				"<td>"+
            				"<input type=\"text\" required=\"required\" name=\"fecEntrega"+i+"\" id=\"fecEntrega"+i+"\" size=\"10\" maxlength=\"10\" title=\"Fecha Entrega:\" placeholder=\"Dia/Mes/A�o\"/>"+
            				"</td>"+
            				"<td>"+
            				"<input id=\"hora"+i+"\" required=\"required\" type=\"time\" name=\"hora"+i+"\" value=\"${hora}\" title=\"Ingrese la hora\" placeholder=\"ingrese la hora de entrega\" size=\"26\" maxlength=\"30\">"+
            				"</td>"+
            				"<td>"+
            				"<textarea id=\"coment"+i+"\" name=\"coment"+i+"\" rows=\"4\" cols=\"50\" maxlength=\"500\" placeholder=\"Escribir un comentario....\" ></textarea>"+
            				"</td>"+
            				"<td style=\"width: 10px\">"+
	        				"<div>"+      
		        			"<input readonly=\"readonly\" hidden=\"\" type=\"text\" id=\"codBar"+i+"\" value=\""+obj[1]+"\" name=\"codBar"+i+"\"/>"+
							"</div>"+
							"</td>"+
							
                   		"</tr>";
           }
           i++;
        }         
                outPut +=       "</tbody>" +
                            "</table>";
                        
        
         return outPut;
    }
	
	public static String getHTMLTablaRutasCodBar(String codBar, String situacion, String vinc, HojaRutaModel hr, String fecha, String titular,String motivos, List<Object> coordinada, Integer pqr){
		Integer i=1;
		
		Integer esRendicion=0;
        String outPut = 
                            "<table id=\"tableCodRutas\" >" +
                                "<thead>" +
                                    "<tr>" +
                                        "<td>Codigo</td>" +
                                        "<td>Situcacion</td>" +
                                        "<td>Estado</td>" +
                                        "<td>Motivo</td>" +
                                        "<td>Tipo Domicilio</td>" +
                                        "<td>Vinculo</td>" +
                                        "<td>Recibido</td>" +
                                        "<td>Fecha Entrega</td>" +
                                        "<td>Hora</td>" +
                                        "<td>Comentarios</td>" +
                                    "</tr>" +
                                "</thead>" +
                                "<tbody>";
  
           
           
          
        outPut +=    "<tr class='<c:if test=\"${rowCounter.count % 2 == 0}\">on</c:if>'>" +
                "<td>"+codBar+"</td>" +
                "<td><select required=\"required\" id=\"tipoSituacion"+i+"\" name=\"tipoSituacion"+i+"\" onchange=\"selectSituacion("+i+")\" readonly=\"readonly\">"+
				"<option value=\"\">::SELECCIONAR::</option>"+
				situacion+
				"</select></td>" +
                "<td><select  required=\"required\" id=\"tipoEstado"+i+"\" name=\"tipoEstado"+i+"\" >"+
				"<option value=\"\">::SELECCIONAR::</option>"+
				"</select</td>" +
                "<td><select  required=\"required\" id=\"tipoMotivo"+i+"\" name=\"tipoMotivo"+i+"\" onchange=\"selectMotivo("+i+")\">"+
				"<option value=\"\">::SELECCIONAR::</option>"+
				motivos+
				"</select></td>" ;
        
				if(coordinada.size()>0 ){
					outPut +=			"<td><select  required=\"required\" id=\"tipoDomicilio"+i+"\" name=\"tipoDomicilio"+i+"\">"+
							"<option value=\"\">::SELECCIONAR::</option>"+
							"<option value=\"Domicilio\">DOMICILIO</option>"+
							"<option value=\"Coordinada\" selected='selected'>COORDINADA</option>"+
							"<option value=\"Laboral\">LABORAL</option>"+
							"<option value=\"Opcional\">OPCIONAL</option>"+
							"</select></td>" ;
				}
				else{
					outPut +=		"<td><select  required=\"required\" id=\"tipoDomicilio"+i+"\" name=\"tipoDomicilio"+i+"\">"+
							"<option value=\"\">::SELECCIONAR::</option>"+
							"<option value=\"Domicilio\" selected='selected'>DOMICILIO</option>"+
							"<option value=\"Coordinada\">COORDINADA</option>"+
							"<option value=\"Laboral\">LABORAL</option>"+
							"<option value=\"Opcional\">OPCIONAL</option>"+
							"</select></td>" ;
				}

				outPut +="<td><select  required=\"required\" id=\"tipoVinculo"+i+"\" name=\"tipoVinculo"+i+"\" readonly=\"readonly\">"+
				"<option value=\"\">::SELECCIONAR::</option>"+
				vinc+
				"</select></td>" +
				"<td>"+
				"<input minlength=\"3\" required=\"required\" id=\"reciPor"+i+"\" type=\"text\" name=\"reciPor"+i+"\" value=\""+titular+"\" title=\"Ingrese nombre de Destinatario o Titular\" placeholder=\"destinatario o titular\" size=\"26\" maxlength=\"30\">"+
				"</td>" +
				"<td>"+
				"<input type=\"text\" required=\"required\" value=\""+fecha+"\" name=\"fecEntrega"+i+"\" id=\"fecEntrega"+i+"\" size=\"10\" maxlength=\"10\" title=\"Fecha Entrega:\" placeholder=\"Dia/Mes/A�o\" readonly=\"readonly\"/>"+
				"</td>";
				if(pqr==2){//es pqr
					Date date = new Date();
					DateFormat hourFormat = new SimpleDateFormat("HH:mm");
					outPut +="<td>"+
					"<input id=\"hora"+i+"\" required=\"required\" type=\"time\" name=\"hora"+i+"\" value=\""+hourFormat.format(date)+"\" title=\"Ingrese la hora\" placeholder=\"ingrese la hora de entrega\" size=\"26\" maxlength=\"30\">"+
					"</td>";
				}else{
					outPut +="<td>"+
					"<input id=\"hora"+i+"\" required=\"required\" type=\"time\" name=\"hora"+i+"\" value=\"\" title=\"Ingrese la hora\" placeholder=\"ingrese la hora de entrega\" size=\"26\" maxlength=\"30\">"+

					"</td>";
				}
				outPut +="<td>"+
				"<textarea id=\"coment"+i+"\" name=\"coment"+i+"\" rows=\"4\" cols=\"50\" maxlength=\"500\" placeholder=\"Escribir un comentario....\" ></textarea>"+
				"</td>"+
				"<td style=\"width: 10px\">"+
				"<div>"+      
    			"<input readonly=\"readonly\" hidden=\"\" type=\"text\" id=\"codBar"+i+"\" value=\""+codBar+"\" name=\"codBar"+i+"\"/>"+
				"</div>"+
				"</td>"+
				
       		"</tr>";

       
                outPut +=       "</tbody>" +
                            "</table>";
                        
        
         return outPut;
    }
	
	public static String getHTMLTablaRendicionCod(List<Object> listaRutas, String situacion){
		Integer i=1;
		
		
        String outPut = 
                            "<table id=\"tableCodRutas\" >" +
                                "<thead>" +
                                    "<tr>" +
                                        "<td>Codigo</td>" +
                                        "<td>Situcacion</td>" +
                                        "<td>Estado</td>" +
                                        "<td>Motivo</td>" +
                                        "<td>Fecha Entrega</td>" +
                                        "<td>Hora</td>" +
                                        "<td>Comentarios</td>" +
                                    "</tr>" +
                                "</thead>" +
                                "<tbody>";
        
        Iterator itr = listaRutas.iterator();
        while(itr.hasNext()){
           Object[] obj = (Object[]) itr.next();    
           outPut +=    "<tr class='<c:if test=\"${rowCounter.count % 2 == 0}\">on</c:if>'>" +
                            "<td>"+obj[1]+"</td>" +
                            "<td><select required=\"required\" id=\"tipoSituacion"+i+"\" name=\"tipoSituacion"+i+"\" onchange=\"selectSituacion("+i+")\">"+
            				"<option value=\"\">::SELECCIONAR::</option>"+
            				situacion+
            				"</select></td>" +
                            "<td><select  required=\"required\" id=\"tipoEstado"+i+"\" name=\"tipoEstado"+i+"\">"+
            				"<option value=\"\">::SELECCIONAR::</option>"+
            				"</select</td>" +
                            "<td><select  required=\"required\" id=\"tipoMotivo"+i+"\" name=\"tipoMotivo"+i+"\">"+
            				"<option value=\"\">::SELECCIONAR::</option>"+
            				"</select></td>" +
            				"<td>"+
            				"<input type=\"text\" required=\"required\" name=\"fecEntrega"+i+"\" id=\"fecEntrega"+i+"\" size=\"10\" maxlength=\"10\" title=\"Fecha Entrega:\" placeholder=\"Dia/Mes/A�o\"/>"+
            				"</td>"+
            				"<td>"+
            				"<input id=\"hora"+i+"\" required=\"required\" type=\"time\" name=\"hora"+i+"\" value=\"${hora}\" title=\"Ingrese la hora\" placeholder=\"ingrese la hora de entrega\" size=\"26\" maxlength=\"30\">"+
            				"</td>"+
            				"<td>"+
            				"<textarea id=\"coment"+i+"\" name=\"coment"+i+"\" rows=\"4\" cols=\"50\" maxlength=\"500\" placeholder=\"Escribir un comentario....\" ></textarea>"+
            				"</td>"+
            				"<td style=\"width: 10px\">"+
	        				"<div>"+      
		        			"<input readonly=\"readonly\" hidden=\"\" type=\"text\" id=\"codBar"+i+"\" value=\""+obj[1]+"\" name=\"codBar"+i+"\"/>"+
							"</div>"+
							"</td>"+
							
                   		"</tr>";
           i++;
        }         
                outPut +=       "</tbody>" +
                            "</table>";
                        
        
         return outPut;
    }
	
	public static String getHTMLTablaRutasCod(List<Object> listaRutas){
		Integer estado;
        String outPut = 
                            "<table id=\"table-consulta-2\" >" +
                                "<thead>" +
                                    "<tr>" +
                                        "<td>Codigo</td>" +
                                        "<td>Cliente</td>" +
                                        "<td>Numero ide</td>" +
                                        "<td>Nombre</td>" +
                                        "<td>Motivo</td>" +
                                        "<td>Situacion</td>" +
                                    "</tr>" +
                                "</thead>" +
                                "<tbody>";
        
        Iterator itr = listaRutas.iterator();
        while(itr.hasNext()){
           Object[] obj = (Object[]) itr.next();    
           estado=Integer.parseInt(obj[8].toString());
           System.out.println("estado: "+estado);
           outPut +=    "<tr class='<c:if test=\"${rowCounter.count % 2 == 0}\">on</c:if>'>" +
                            "<td>"+obj[1]+"</td>" +
                            "<td>"+obj[3]+"</td>" +
                            "<td>"+(obj[4]!=null?obj[4]:"")+"</td>" +
                            "<td>"+obj[5]+"</td>" +
                            "<td>"+obj[6]+"</td>" +
                            "<td>"+obj[7]+"</td>" ;
                            if(obj[8].toString().equals("1")){
                   outPut +="<td style=\"width: 10px\">"+
	        				"<div>"+      
		        				"<button id=\"btnEliminar\" class=\"boton-default\" onclick=\"btn_eliminar("+obj[0]+");\">Eliminar</button>"+
							"</div>"+
							"</td>";
                            }
              outPut +=     "</tr>";          
        }         
                outPut +=       "</tbody>" +
                            "</table>";
         return outPut;
    }
	
}