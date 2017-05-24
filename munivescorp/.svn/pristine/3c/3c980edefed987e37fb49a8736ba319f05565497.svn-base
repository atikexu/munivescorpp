package pe.nasqa.values.control;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JPopupMenu.Separator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import pe.dataimagenes.utils.Utils;
import pe.dataimagenes.utils.DateUtil;
import pe.nasqa.values.model.ClienteModel;
import pe.nasqa.values.model.CoordinacionFeriadoModel;
import pe.nasqa.values.model.CoordinacionModel;
import pe.nasqa.values.model.CoordinacionTelfModel;
import pe.nasqa.values.model.DistribucionModel;
import pe.nasqa.values.model.HojaRutaDetalleModel;
import pe.nasqa.values.model.IndicadorGestionTelfModel;
import pe.nasqa.values.model.LogCargaDataModel;
import pe.nasqa.values.model.entity.Cliente;
import pe.nasqa.values.model.entity.ClienteAgencia;
import pe.nasqa.values.model.entity.Distribucion;
import pe.nasqa.values.model.entity.DistribucionCoord;
import pe.nasqa.values.model.entity.DistribucionCoordTelf;
import pe.nasqa.values.model.entity.DistribucionVisita;
import pe.nasqa.values.model.entity.HojaRutaDetalle;
import pe.nasqa.values.model.entity.Menu;
import pe.nasqa.values.model.entity.RegistroCoord;
import pe.nasqa.values.model.entity.RegistroCoordTelf;
import pe.nasqa.values.model.entity.RegistroFeriado;
import pe.nasqa.values.model.entity.Usuario;
import pe.nasqa.values.model.entity.UsuarioEstado;

import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
//
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;



import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.*;

@Controller
@RequestMapping(value="/coordinacion")
@SessionAttributes({"USUARIO_INFO","USUARIO_TIPO","SACoordinacionSelect"})
public class CoordinacionControl {
	
	@Autowired
	private LogCargaDataModel logCargaDataModel;
	
	@Autowired
	private CoordinacionModel coordinacionModel;
	
	@Autowired
	private CoordinacionTelfModel coordinacionTelfModel;
	
	@Autowired
	private CoordinacionFeriadoModel coordinacionFeriadoModel;
	
	@Autowired
	private DistribucionModel distribucionModel;
	
	@Autowired
	private ClienteModel clienteModel;
	
	@Autowired
	private IndicadorGestionTelfModel indicadorGestionTelfModel;
	
	@Autowired
	private HojaRutaDetalleModel hojaRutaDetalleModel;
	
	private Logger log = Logger.getLogger(CoordinacionControl.class);
	
	@RequestMapping(value={"/index.htm", "/"}) 
	public String index(Model model, HttpServletRequest request){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.D1.name()));
		model.addAttribute("menuSelect", Menu.D1.name());
		if(request.getParameter("ref")!=null){
			model.addAttribute("ref", request.getParameter("ref"));
		}
		return "coordinacion/coord_selec";
	}
	
	@RequestMapping(value={"/coord_buscar.htm"}) 
	public String seleccion(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, @RequestParam("codBar") String codBar, @RequestParam("nroRef") String nroRef, @RequestParam("docIde") String docIde, @RequestParam("nomDes") String nomDes, HttpServletRequest request){
		String view = "coordinacion/coord_selec";
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.D1.name()));
		model.addAttribute("menuSelect", Menu.D1.name());
		
		model.addAttribute("codBar", codBar);
		model.addAttribute("nroRef", nroRef);
		model.addAttribute("docIde", docIde);
		model.addAttribute("nomDes", nomDes);
		
		if(request.getParameter("ref")!=null){
			model.addAttribute("ref", request.getParameter("ref"));
		}
		
		if(codBar.length()>0 || nroRef.length()>0 || docIde.length()>0 || nomDes.length()>2){
			
			String codCliente=null;
			if(usuarioInfo.getTipo().equals(CVConstante.USUARIO_TIPO_CLIENTE)){
				codCliente = usuarioInfo.getIdCliente();
			}
			System.out.println("barra y cliente:_"+codBar+" "+codCliente);
			List<Distribucion> listaResultado = distribucionModel.findDistCodName(codBar, nroRef, docIde, nomDes, codCliente);
			System.out.println("lista:_"+listaResultado.size());
			if(listaResultado!=null){
				if(listaResultado.size()>1){
					model.addAttribute("resultadoSeleccion", listaResultado);
				}else if(listaResultado.size()==1){
					System.out.println("inssit "+listaResultado.get(0).getIndSit());
					System.out.println("REF: "+request.getParameter("ref"));
					if(request.getParameter("ref")!=null){//si viene referenciado de gest.telefonicas
						model.addAttribute("menuSelect", Menu.D4.name());
						model.addAttribute("SACoordinacionSelect", listaResultado.get(0));
						
						gestt_leer(model, listaResultado.get(0).getCodBar());
						model.addAttribute("RPCoordinacionSelectCoordTelfIndicadorGestionTelfs", indicadorGestionTelfModel.buscarIndicadoresActivos());
						
						view = "coordinacion/gestt_form";
					}else{
						model.addAttribute("menuSelect", Menu.D2.name());
						model.addAttribute("SACoordinacionSelect", listaResultado.get(0));
						coord_leer(model, listaResultado.get(0).getCodBar(), listaResultado.get(0).getCodCli());
						model.addAttribute("can_delete_coord", usuarioInfo.getDeleteCoord());
						System.out.println("CAN DELETE:"+usuarioInfo.getDeleteCoord());
						view = "coordinacion/coord_form";
					}
					
				}
			}
		}
		return view;
	}
	
	@RequestMapping(value={"/coord_form.htm"}) 
	public String coord_seleccion(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, @RequestParam("codBar") String codBar){
		
		String view = "coordinacion/coord_selec";
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.D1.name()));
		model.addAttribute("menuSelect", Menu.D1.name());
		
		String codCliente=null;
		if(usuarioInfo.getTipo().equals(CVConstante.USUARIO_TIPO_CLIENTE)){
			codCliente = usuarioInfo.getIdCliente();
		}
		Distribucion distribucion = distribucionModel.getByCodBar(codBar, codCliente);
		if(distribucion!=null){
			view = "coordinacion/coord_form";
			model.addAttribute("menuSelect", Menu.D2.name());
			model.addAttribute("SACoordinacionSelect", distribucion);
			
			coord_leer(model, distribucion.getCodBar(), distribucion.getCodCli());
		}
		return view;
	}
	
	@RequestMapping(value={"/coord_re_form.htm"}) 
	public String recupera_seleccion(Model model, ModelMap modelMap, HttpServletRequest req){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.D1.name()));
		model.addAttribute("menuSelect", Menu.D2.name());
		
		Distribucion distribucion = (Distribucion)modelMap.get("SACoordinacionSelect");
		coord_leer(model, distribucion.getCodBar(), distribucion.getCodCli());
		if(req.getParameter("telfRef")!=null){
			model.addAttribute("telfRef", req.getParameter("telfRef"));
		}
		
		return "coordinacion/coord_form";
	}
	
	@RequestMapping(value={"/coord_save.htm"})
	public String coord_guarda(Model model, ModelMap modelMap, HttpServletRequest req, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, @RequestParam("idDistribucion") String idDistribucion, @RequestParam("codBar") String codBar){
		
		String view = "coordinacion/coord_form";
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.D1.name()));
		model.addAttribute("menuSelect", Menu.D2.name());
		if(!CVDinamico.isSunDay(req.getParameter("fecCoo")) && CVDinamico.isFutureDate(req.getParameter("fecCoo"))){
			try {
				String fecCoo=req.getParameter("fecCoo");
				String horCoo=req.getParameter("horCoo");
				String dirCoo=req.getParameter("dirCoo");
				System.out.println(dirCoo);
				dirCoo=dirCoo.replace("\r\n", " ");
				System.out.println(dirCoo);
				dirCoo=dirCoo.replace("\n", " ");
				System.out.println(dirCoo);
				String posCoo="";
				String ubiCoo=req.getParameter("ubiCoo");
				String refCoo=req.getParameter("refCoo");
				String obsCoo=req.getParameter("obsCoo");
				obsCoo=obsCoo.replace("\r\n", " ");
				obsCoo=obsCoo.replace("\n", " ");
				String tlfCoo=req.getParameter("tlfCoo");
				String nroTlf=req.getParameter("nroTlf");
				String codUsu=usuarioInfo.getId().toString();
				String nomUsu=usuarioInfo.getUsername();
				Integer indUsu=Integer.parseInt(req.getParameter("indUsu"));
				String desUsu=indUsu.intValue()==2?"Cliente":"Dataimagenes";
				/*Si indicador de lugar es 1 entonces puede seleccionar una de las direcciones, de lo contrario todo son coordinados (9) */
				Integer indLug=Integer.parseInt(req.getParameter("indLug"));
				String desLug=indLug.intValue()==1?"Destinatario":(indLug.intValue()==2?"Agencia":"Dataimagenes");
				Integer indDir=indLug.intValue()==1?(Integer.parseInt(req.getParameter("indDir"))):(9);
				String desDir=indLug.intValue()==1?(
						indDir.intValue()==1?"Domiciliaria":(
								indDir.intValue()==2?"Laboral":(
										indDir.intValue()==3?"Opcional":"Coordinada"
											)
										)
								):("Coordinada");
				System.out.println("CODIGO USUARIO "+codUsu+ " NOMBRE: "+nomUsu+ "indUsu: "+indUsu+" "+idDistribucion);
				String codAge=indLug.intValue()==2?req.getParameter("codAge"):"";
				String nomAge=indLug.intValue()==2?req.getParameter("nomAge"):"";
				Integer indPri=Integer.parseInt(req.getParameter("indPri"));
				String desPri=indPri.intValue()==1?"Normal":"Express";
				Integer indLla=Integer.parseInt(req.getParameter("indLla"));
				String desLla=indLla.intValue()==1?"Entrante":"Saliente";
				
				RegistroCoord regCoord=new RegistroCoord();
				regCoord.setCodBar(codBar);
				regCoord.setFecReg(new Date());
				regCoord.setFecCoo(CVDinamico.getDateFromString(fecCoo, "dd/MM/yyyy"));
				regCoord.setHorCoo(horCoo);
				regCoord.setDirCoo(dirCoo);
				regCoord.setPosCoo(posCoo);
				regCoord.setUbiCoo(ubiCoo);
				regCoord.setRefCoo(refCoo);
				regCoord.setObsCoo(obsCoo);
				regCoord.setTlfCoo(tlfCoo);
				regCoord.setCodAge(codAge);
				regCoord.setNomAge(nomAge);
				regCoord.setCodUsu(codUsu);
				regCoord.setNomUsu(nomUsu);
				regCoord.setIndUsu(indUsu);
				regCoord.setDesUsu(desUsu);
				regCoord.setIndLug(indLug);
				regCoord.setDesLug(desLug);
				regCoord.setIndDir(indDir);
				regCoord.setDesDir(desDir);
				regCoord.setIndPri(indPri);
				regCoord.setDesPri(desPri);
				regCoord.setIndLla(indLla);
				regCoord.setDesLla(desLla);
				regCoord.setUsuCre(usuarioInfo.getId());
				regCoord.setFecCre(new Date());
				regCoord.setFlgStt("RG");//RG=Registrado
				
				//coordinacionModel.guardar(regCoord);
				
				//registrar en distribucion_coordinacion//
				DistribucionCoord distribucionCoord=new DistribucionCoord();
				distribucionCoord.setCodBar(codBar);
				distribucionCoord.setFecReg(new Date());
				distribucionCoord.setFecCoo(CVDinamico.getDateFromString(fecCoo, "dd/MM/yyyy"));
				distribucionCoord.setHorCoo(horCoo);
				
				distribucionCoord.setDirCoo(dirCoo);
				distribucionCoord.setPosCoo(posCoo);
				distribucionCoord.setUbiCoo(ubiCoo);
				distribucionCoord.setRefCoo(refCoo);
				distribucionCoord.setObsCoo(obsCoo);
				distribucionCoord.setTlfCoo(tlfCoo);
				distribucionCoord.setCodAge(codAge);
				distribucionCoord.setNomAge(nomAge);
				distribucionCoord.setCodUsu(codUsu);
				distribucionCoord.setNomUsu(nomUsu);
				distribucionCoord.setIndUsu(indUsu);
				distribucionCoord.setDesUsu(desUsu);
				distribucionCoord.setIndLug(indLug);
				distribucionCoord.setDesLug(desLug);
				distribucionCoord.setIndDir(indDir);
				distribucionCoord.setDesDir(desDir);
				distribucionCoord.setIndPri(indPri);
				distribucionCoord.setDesPri(desPri);
				distribucionCoord.setIndLla(indLla);
				distribucionCoord.setDesLla(desLla);
				distribucionCoord.setUsuCre(usuarioInfo.getId());
				distribucionCoord.setFecCre(new Date());
				
				coordinacionModel.guardarDist(distribucionCoord);
				//distribucionCoord.setFlgStt("RG");//RG=Registrado
				///////////fin////////////////////////////
				
				String codCliente=null;
				if(usuarioInfo.getTipo().equals(CVConstante.USUARIO_TIPO_CLIENTE)){
					codCliente = usuarioInfo.getIdCliente();
				}
			
				Distribucion distribucion = distribucionModel.getByCodBar(codBar, codCliente);
				if(distribucion!=null){
					view = "coordinacion/coord_form";
					model.addAttribute("SACoordinacionSelect", distribucion);
					coord_leer(model, distribucion.getCodBar(), distribucion.getCodCli());
					
					model.addAttribute("success", "true");
				}
			} catch (Exception e) {
				log.error(e.getMessage());
				model.addAttribute("error", "true");
			}
		}else{
			log.error("Fecha no permitido : "+req.getParameter("fecCoo"));
			model.addAttribute("error", "true");
		}
		
		return view;
	}
	
	@RequestMapping(value={"/coord_list.htm"}) 
	public String coord_lista(Model model, ModelMap modelMap){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.D1.name()));
		model.addAttribute("menuSelect", Menu.D3.name());
		Distribucion distribucion = (Distribucion)modelMap.get("SACoordinacionSelect");
		coord_leer(model, distribucion.getCodBar(), distribucion.getCodCli());
		List<HojaRutaDetalle> listaDetalle = hojaRutaDetalleModel.getConGestion(distribucion.getCodBar());
		if(listaDetalle.size()==0){
			model.addAttribute("menuActivo", true);
		}
		return "coordinacion/coord_list";
	}
	
	public void coord_leer(Model model, String codBar, String codCliente){
		System.out.println(codBar+"  "+codCliente);
		List<RegistroCoord> distribucionCoords = coordinacionModel.buscarPorCodBar(codBar);
		model.addAttribute("RPCoordinacionSelectCoords", distribucionCoords);
		List<DistribucionCoord> distribucionCoordinaciones = distribucionModel.findCoordinaciones(codBar);
		model.addAttribute("RPConsultaDetalleCoordinaciones", distribucionCoordinaciones);
		List<ClienteAgencia> clienteAgencias = clienteModel.buscarAgencias(codCliente);
		model.addAttribute("RPCoordinacionClienteAgencias", clienteAgencias);
		List<RegistroFeriado> feriados = coordinacionFeriadoModel.listarFeriados();
		model.addAttribute("RPCoordinacionFeriados", feriados);
		
	}
	
	@RequestMapping(value={"/coord_remove.htm"}) 
	public String coord_remover(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, @RequestParam("codBar") String codBar, @RequestParam("idCoord") Integer idCoord){
		
		String view = "coordinacion/coord_form";
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.D1.name()));
		model.addAttribute("menuSelect", Menu.D2.name());
		
		//coordinacionModel.remover(idCoord);
		DistribucionCoord regCoord = coordinacionModel.obtenerPorIdDist(idCoord);
		coordinacionModel.removerDistCoord(regCoord);
		
		String codCliente=null;
		if(usuarioInfo.getTipo().equals(CVConstante.USUARIO_TIPO_CLIENTE)){
			codCliente = usuarioInfo.getIdCliente();
		}
		Distribucion distribucion = distribucionModel.getByCodBar(codBar, codCliente);
		if(distribucion!=null){
			model.addAttribute("SACoordinacionSelect", distribucion);
			coord_leer(model, distribucion.getCodBar(), distribucion.getCodCli());
		}
		return view;
	}
	
	@RequestMapping(value={"/coord_edit.htm"}) 
	public String coord_editar(Model model, ModelMap modelMap, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, @RequestParam("idCoord") Integer idCoord){
		
		String view = "coordinacion/coord_form";
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.D1.name()));
		model.addAttribute("menuSelect", Menu.D2.name());
		System.out.println("TITULO: "+CVConstante.getPageTitle(Menu.D1.name()));
		Distribucion distribucion = (Distribucion)modelMap.get("SACoordinacionSelect");
		
		//RegistroCoord regCoord = coordinacionModel.obtenerPorId(idCoord);
		DistribucionCoord regCoord = coordinacionModel.obtenerPorIdDist(idCoord);
		if(distribucion.getCodBar().equals(regCoord.getCodBar())){
			System.out.println("codigo barra"+regCoord.getCodBar());
			coord_leer(model, distribucion.getCodBar(), distribucion.getCodCli());
			model.addAttribute("RPRegistroCoordEdit", regCoord);
		}		
		return view;
	}
	
	@RequestMapping(value={"/coord_update.htm"})
	public String coord_actualiza(Model model, ModelMap modelMap, HttpServletRequest req, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, @RequestParam("idDistribucion") String idDistribucion, @RequestParam("codBar") String codBar, @RequestParam("idCoord") Integer idCoord){
		
		String view = "coordinacion/coord_form";
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.D1.name()));
		model.addAttribute("menuSelect", Menu.D2.name());
		if(!CVDinamico.isSunDay(req.getParameter("fecCoo")) && CVDinamico.isFutureDate(req.getParameter("fecCoo"))){
			try {
				String fecCoo=req.getParameter("fecCoo");
				String horCoo=req.getParameter("horCoo");
				String dirCoo=req.getParameter("dirCoo");
				dirCoo=dirCoo.replace("\r\n", " ");
				dirCoo=dirCoo.replace("\n", " ");
				String posCoo="";
				String ubiCoo=req.getParameter("ubiCoo");
				String refCoo=req.getParameter("refCoo");
				String obsCoo=req.getParameter("obsCoo");
				obsCoo=obsCoo.replace("\r\n", " ");
				obsCoo=obsCoo.replace("\n", " ");
				String codAge=req.getParameter("codAge");
				String nomAge=req.getParameter("nomAge");
				String tlfCoo=req.getParameter("tlfCoo");
				String codUsu=usuarioInfo.getId().toString();
				String nomUsu=usuarioInfo.getUsername();
				Integer indUsu=Integer.parseInt(req.getParameter("indUsu"));
				String desUsu=indUsu.intValue()==2?"Cliente":"Dataimagenes";
				Integer indLug=Integer.parseInt(req.getParameter("indLug"));
				String desLug=indLug.intValue()==1?"Destinatario":(indLug.intValue()==2?"Agencia":"Dataimagenes");
				Integer indDir=indLug.intValue()==1?(Integer.parseInt(req.getParameter("indDir"))):(9);
				String desDir=indLug.intValue()==1?(
						indDir.intValue()==1?"Domiciliaria":(
								indDir.intValue()==2?"Laboral":(
										indDir.intValue()==3?"Opcional":"Coordinada"
											)
										)
								):("Coordinada");
				Integer indPri=Integer.parseInt(req.getParameter("indPri"));
				String desPri=indPri.intValue()==1?"Normal":"Express";
				Integer indLla=Integer.parseInt(req.getParameter("indLla"));
				String desLla=indLla.intValue()==1?"Entrante":"Saliente";
				
				//RegistroCoord regCoord = coordinacionModel.obtenerPorId(idCoord);
				DistribucionCoord regCoord = coordinacionModel.obtenerPorIdDist(idCoord);
				regCoord.setFecCoo(CVDinamico.getDateFromString(fecCoo, "dd/MM/yyyy"));
				regCoord.setHorCoo(horCoo);
				regCoord.setDirCoo(dirCoo);
				regCoord.setPosCoo(posCoo);
				regCoord.setUbiCoo(ubiCoo);
				regCoord.setRefCoo(refCoo);
				regCoord.setObsCoo(obsCoo);
				regCoord.setTlfCoo(tlfCoo);
				regCoord.setCodAge(codAge);
				regCoord.setNomAge(nomAge);
				regCoord.setCodUsu(codUsu);
				regCoord.setNomUsu(nomUsu);
				regCoord.setIndUsu(indUsu);
				regCoord.setDesUsu(desUsu);
				regCoord.setIndLug(indLug);
				regCoord.setDesLug(desLug);
				regCoord.setIndDir(indDir);
				regCoord.setDesDir(desDir);
				regCoord.setIndPri(indPri);
				regCoord.setDesPri(desPri);
				regCoord.setIndLla(indLla);
				regCoord.setDesLla(desLla);
				regCoord.setUsuCre(usuarioInfo.getId());
				regCoord.setFecCre(new Date());
				
				//coordinacionModel.actualizar(regCoord);
				coordinacionModel.actualizarDistCoord(regCoord);
				
				String codCliente=null;
				if(usuarioInfo.getTipo().equals(CVConstante.USUARIO_TIPO_CLIENTE)){
					codCliente = usuarioInfo.getIdCliente();
				}
			
				Distribucion distribucion = distribucionModel.getByCodBar(codBar, codCliente);
				if(distribucion!=null){
					model.addAttribute("SACoordinacionSelect", distribucion);
					coord_leer(model, distribucion.getCodBar(), distribucion.getCodCli());
					
					model.addAttribute("success", "true");
				}
			} catch (Exception e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}
		}else{
			log.error("Fecha no permitido en modificacion : "+req.getParameter("fecCoo"));
			model.addAttribute("error", "true");
		}
		
		return view;
	}
	
	@RequestMapping(value={"/gestt_buscar.htm"}) 
	public String selec_gestt(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, @RequestParam("codBar") String codBar){
		String view = "coordinacion/gestt_form";
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.D1.name()));
		model.addAttribute("menuSelect", Menu.D4.name());
		if(codBar.length()>0){
			
			String codCliente=null;
			if(usuarioInfo.getTipo().equals(CVConstante.USUARIO_TIPO_CLIENTE)){
				codCliente = usuarioInfo.getIdCliente();
			}
		
			Distribucion distribucion = distribucionModel.getByCodBar(codBar, codCliente);
			if(distribucion!=null){
				model.addAttribute("SACoordinacionSelect", distribucion);
				gestt_leer(model, distribucion.getCodBar());
				model.addAttribute("RPCoordinacionSelectCoordTelfIndicadorGestionTelfs", indicadorGestionTelfModel.buscarIndicadoresActivos());
			}
		}
		return view;
	}
	
	@RequestMapping(value={"/gestt_form.htm"}) 
	public String gestt_form(Model model, ModelMap modelMap, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.D1.name()));
		model.addAttribute("menuSelect", Menu.D4.name());
		
		Distribucion distribucion = (Distribucion)modelMap.get("SACoordinacionSelect");
		gestt_leer(model, distribucion.getCodBar());
		model.addAttribute("RPCoordinacionSelectCoordTelfIndicadorGestionTelfs", indicadorGestionTelfModel.buscarIndicadoresActivos());
		return "coordinacion/gestt_form";
	}
	
	@RequestMapping(value={"/gestt_save.htm"})
	public String gestt_guarda(Model model, ModelMap modelMap, HttpServletRequest req, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, @RequestParam("idDistribucion") String idDistribucion, @RequestParam("codBar") String codBar){
		
		String view = "coordinacion/gestt_form";
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.D1.name()));
		model.addAttribute("menuSelect", Menu.D4.name());
		try {
			String fecReg=req.getParameter("fecReg");
			String horReg=req.getParameter("horReg");
			String nroTlf=req.getParameter("nroTlf");
			String codMot=req.getParameter("codMot");
			String desMot=req.getParameter("desMot");
			String detObs=req.getParameter("detObs");
			detObs=detObs.replace("\r\n", " ");
			detObs=detObs.replace("\n", " ");
			String codUsu=usuarioInfo.getId().toString();
			String nomUsu=usuarioInfo.getUsername();
			String indSeg=req.getParameter("indSeg");
			String desSeg=indSeg.equals("1")?"Dataimagenes":(indSeg.equals("2")?"Cliente":"Call Center");
						
			RegistroCoordTelf regCoordTelf = new RegistroCoordTelf();
			//regCoordTelf.setIdDistribucion((Distribucion)modelMap.get("SACoordinacionSelect"));
			regCoordTelf.setCodBar(codBar);
			regCoordTelf.setFecReg(CVDinamico.getDateFromString(fecReg, "dd/MM/yyyy"));
			regCoordTelf.setHorReg(horReg);
			regCoordTelf.setNroTlf(nroTlf);
			regCoordTelf.setCodMot(codMot);
			regCoordTelf.setDesMot(desMot);
			regCoordTelf.setDetObs(detObs);
			regCoordTelf.setCodUsu(codUsu);
			regCoordTelf.setNomUsu(nomUsu);
			regCoordTelf.setIndSeg(indSeg);
			regCoordTelf.setDesSeg(desSeg);
			regCoordTelf.setUsuCre(usuarioInfo.getId());
			regCoordTelf.setFecCre(new Date());
			regCoordTelf.setFlgStt("RG");//RG=Registrado
			
			coordinacionTelfModel.guardar(regCoordTelf);
			
			String codCliente=null;
			if(usuarioInfo.getTipo().equals(CVConstante.USUARIO_TIPO_CLIENTE)){
				codCliente = usuarioInfo.getIdCliente();
			}
		
			Distribucion distribucion = distribucionModel.getByCodBar(codBar, codCliente);
			if(distribucion!=null){
				view = "coordinacion/gestt_form";
				model.addAttribute("SACoordinacionSelect", distribucion);
				gestt_leer(model, distribucion.getCodBar());
				model.addAttribute("RPCoordinacionSelectCoordTelfIndicadorGestionTelfs", indicadorGestionTelfModel.buscarIndicadoresActivos());
				model.addAttribute("success", "true");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		
		return view;
	}
	
	@RequestMapping(value={"/gestt_list.htm"}) 
	public String gestt_lista(Model model, ModelMap modelMap){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.D1.name()));
		model.addAttribute("menuSelect", Menu.D5.name());
		Distribucion distribucion = (Distribucion)modelMap.get("SACoordinacionSelect");
		gestt_leer(model, distribucion.getCodBar());
		return "coordinacion/gestt_list";
	}
	public void gestt_leer(Model model, String codBar){
		List<RegistroCoordTelf> distribucionCoordTelfs = coordinacionTelfModel.buscarPorCodBar(codBar);
		model.addAttribute("RPCoordinacionSelectCoordTelfs", distribucionCoordTelfs);
		List<DistribucionCoordTelf> distribucionDetalleCoordTelfs = distribucionModel.findCoordinacionesTelf(codBar);
		model.addAttribute("RPConsultaDetalleCoordTelfs", distribucionDetalleCoordTelfs);
		model.addAttribute("RPNowDate", new Date());
	}
	
	@RequestMapping(value={"/gestt_remove.htm"}) 
	public String gestt_remover(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, @RequestParam("codBar") String codBar, @RequestParam("idCoordTelf") Integer idCoordTelf){
		
		String view = "coordinacion/gestt_form";
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.D1.name()));
		model.addAttribute("menuSelect", Menu.D4.name());
		
		coordinacionTelfModel.remover(idCoordTelf);
		
		String codCliente=null;
		if(usuarioInfo.getTipo().equals(CVConstante.USUARIO_TIPO_CLIENTE)){
			codCliente = usuarioInfo.getIdCliente();
		}
		Distribucion distribucion = distribucionModel.getByCodBar(codBar, codCliente);
		if(distribucion!=null){
			model.addAttribute("SACoordinacionSelect", distribucion);
			gestt_leer(model, distribucion.getCodBar());
			model.addAttribute("RPCoordinacionSelectCoordTelfIndicadorGestionTelfs", indicadorGestionTelfModel.buscarIndicadoresActivos());
		}
		return view;
	}
	
	@RequestMapping(value={"/gestt_edit.htm"}) 
	public String gestt_editar(Model model, ModelMap modelMap, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, @RequestParam("idCoordTelf") Integer idCoordTelf){
		
		String view = "coordinacion/gestt_form";
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.D1.name()));
		model.addAttribute("menuSelect", Menu.D4.name());
		
		Distribucion distribucion = (Distribucion)modelMap.get("SACoordinacionSelect");
		RegistroCoordTelf regCoordTelfs = coordinacionTelfModel.obtenerPorId(idCoordTelf);
		if(distribucion.getCodBar().equals(regCoordTelfs.getCodBar())){
			model.addAttribute("SARegistroCoordTelfEdit", regCoordTelfs);
			gestt_leer(model, distribucion.getCodBar());
			model.addAttribute("RPCoordinacionSelectCoordTelfIndicadorGestionTelfs", indicadorGestionTelfModel.buscarIndicadoresActivos());
		}
		return view;
	}
	
	@RequestMapping(value={"/gestt_update.htm"})
	public String gestt_actualiza(Model model, ModelMap modelMap, HttpServletRequest req, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, @RequestParam("idDistribucion") String idDistribucion, @RequestParam("codBar") String codBar, @RequestParam("idCoordTelf") Integer idCoordTelf){
		
		String view = "coordinacion/gestt_form";
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.D1.name()));
		model.addAttribute("menuSelect", Menu.D4.name());
		try {
			String fecReg=req.getParameter("fecReg");
			String horReg=req.getParameter("horReg");
			String nroTlf=req.getParameter("nroTlf");
			String codMot=req.getParameter("codMot");
			String desMot=req.getParameter("desMot");
			String detObs=req.getParameter("detObs");
			detObs=detObs.replace("\r\n", " ");
			detObs=detObs.replace("\n", " ");
			String codUsu=usuarioInfo.getId().toString();
			String nomUsu=usuarioInfo.getUsername();
			String indSeg=req.getParameter("indSeg");
			String desSeg=indSeg.equals("1")?"Dataimagenes":(indSeg.equals("2")?"Cliente":"Call Center");
			
			RegistroCoordTelf regCoordTelf = coordinacionTelfModel.obtenerPorId(idCoordTelf);
			regCoordTelf.setFecReg(CVDinamico.getDateFromString(fecReg, "dd/MM/yyyy"));
			regCoordTelf.setHorReg(horReg);
			regCoordTelf.setNroTlf(nroTlf);
			regCoordTelf.setCodMot(codMot);
			regCoordTelf.setDesMot(desMot);
			regCoordTelf.setDetObs(detObs);
			regCoordTelf.setCodUsu(codUsu);
			regCoordTelf.setNomUsu(nomUsu);
			regCoordTelf.setIndSeg(indSeg);
			regCoordTelf.setDesSeg(desSeg);
			regCoordTelf.setFecCre(new Date());
			
			coordinacionTelfModel.actualizar(regCoordTelf);
			
			String codCliente=null;
			if(usuarioInfo.getTipo().equals(CVConstante.USUARIO_TIPO_CLIENTE)){
				codCliente = usuarioInfo.getIdCliente();
			}
		
			Distribucion distribucion = distribucionModel.getByCodBar(codBar, codCliente);
			if(distribucion!=null){
				view = "coordinacion/gestt_form";
				model.addAttribute("SACoordinacionSelect", distribucion);
				gestt_leer(model, distribucion.getCodBar());
				model.addAttribute("RPCoordinacionSelectCoordTelfIndicadorGestionTelfs", indicadorGestionTelfModel.buscarIndicadoresActivos());
				model.addAttribute("success", "true");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		
		return view;
	}
	
	@RequestMapping(value={"/coordinacion_masiva.htm"})
	public String coordinacion_masiva(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		String view="coordinacion/coordinacion_masiva";
		try {
			System.out.println("Coordinacion masiva");
			
			//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			//impExpDbModel.loadBD_reporte_gnb();
			//log.debug("La carga fue realizado correctamente");
			//view="impexpdb/success";
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.D1.name()));
		model.addAttribute("menuSelect", Menu.D6.name());
		//model.addAttribute("ref", "import");
		return view;
	}
	
	
	
	@RequestMapping(value={"/upload_coordinacion.htm"}, method = RequestMethod.POST)
	public String upload_coordinacion(Model model, @RequestParam("file") MultipartFile file, HttpServletRequest request, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		String view="coordinacion/coordinacion_masiva";
		String path =  request.getSession().getServletContext().getRealPath("dinamic/upload");
		
		System.out.println("###############  "+System.getProperty("user.dir"));
		System.out.println("###############  "+path);
		
		try {
			System.out.println("Upload coordinacion");
			byte[] bytes = file.getBytes();
			
			//String dirDestino = CVDinamico.getDateInFormat("yyyy-MM-dd_hhmm");
			//File dir = new File(path + File.separator + dirDestino);
			File dir = new File(path + File.separator + "BASES");
		
			if (!dir.exists())dir.mkdirs();
		
		// Carga el archivo plano en un directorio del servidor
			log.debug("Carpeta de Carga: "+dir);
			System.out.println("-->"+dir);
			File uploadFile = new File(dir.getAbsolutePath(), file.getOriginalFilename());
			
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile)); 
			stream.write(bytes);
			stream.close();
			model.addAttribute("file", file.getOriginalFilename());
		
			System.out.println("-------> dir  "+dir.getAbsolutePath());
			System.out.println("-------> file  "+file.getOriginalFilename());
			System.out.println("--uo load file >"+uploadFile);
		
			System.out.println("antes Leer Excel");
			
		//	leerexcel(dir.getAbsolutePath(),file.getOriginalFilename());
		//	leerexcel(dir.getAbsolutePath()+File.separator+file.getOriginalFilename());
			
			
			
			System.out.println("Despues Excel");
		// Luego de cargar el archivo plano, este se carga a la base de datos.
			
			 Boolean permitida;
			String codUsuario=null ;
			codUsuario=  usuarioInfo.getId().toString();
			Utils fec = new Utils();
//---------------------------------------------------
			String extension =(uploadFile.getName().substring(uploadFile.getName().lastIndexOf("."))).toLowerCase();
			 
			String[] extensiones_permitida = {".txt"}; 
			
			 permitida=false;
			 
			 for(int i=0; i<extensiones_permitida.length; i++)
			 	{
					System.out.println("extencion y permitida --------"+extensiones_permitida[i]+extension+"----"+extensiones_permitida[i].equals(extension));
					if (extensiones_permitida[i].equals(extension)) { 
				         permitida = true; 
				         break; 
				         }
				 	}

			 if (!permitida)
					model.addAttribute("error", "2");
			 
				 // Pregunta si el archivo ya ha sido cargado...no permite cargar mas de dos veces 
			
			 if 	(logCargaDataModel.countLogCargaDataNombre(uploadFile.getName().replace(" ", "_")))
			{
				permitida= false;
				//log.debug("Error Al subir los Datos, No puede Cargar un mismo archivo mas de dos veces");
				model.addAttribute("error2", "3");
			
			}
			// ------------------------------------------------
			
	 //-- verifica que tenga 17 17campos
			 String texto0="";
			 try
			 {
			 //Creamos un archivo FileReader que obtiene lo que tenga el archivo
			 FileReader lector=new FileReader(uploadFile);

			 //El contenido del archivo (lector) se guarda en un BufferedReader
			 BufferedReader contenido=new BufferedReader(lector);

			 
			 //Con el siguiente ciclo extraemos todo el contenido del objeto "contenido" y lo mostramos
			 int resp=-1;
			 int cant=0;
			 String reg="";
			 
			 while((texto0=contenido.readLine())!=null)
			 {
				 
				   String fields[] = texto0.split("\t",-1);
                   if((fields.length==17)&&(cant!=0)){
                            	   
                	   //	System.out.println("---- "+Integer.toString(resp));
                //	System.out.println(fields[0]+fields[1]);
                //	  resp= coordinacionModel.importCoordinacionBaseBCP(fields, codUsuario,1);
                   		
                   	}
                   else
                	   if(fields.length!=17)
                			   {
                		   permitida= false;
                		   reg+= cant+ ",";
                		   model.addAttribute("msg", "Error en el rgistro nro : "+reg);
                			   }
                   cant++;
                   }
			 
			 }

			 //Si se causa un error al leer cae aqui
			 catch(Exception e)
			 {
			 System.out.println("Error al leer");
			 }

			 
		/////	 // fin verificacion 
			 
			 if (permitida) 
					{
			 String texto="";

			 try
			 {
			 //Creamos un archivo FileReader que obtiene lo que tenga el archivo
			 FileReader lector=new FileReader(uploadFile);

			 //El contenido del archivo (lector) se guarda en un BufferedReader
			 BufferedReader contenido=new BufferedReader(lector);

			 
			 //Con el siguiente ciclo extraemos todo el contenido del objeto "contenido" y lo mostramos
			 int resp=-1;
			 int cant=0;
			 
			 while((texto=contenido.readLine())!=null)
			 {
				 
				   String fields[] = texto.split("\t",-1);
                   if((fields.length==17)&&(cant!=0)){
                //	System.out.println("---- "+Integer.toString(resp));
                //	System.out.println(fields[0]+fields[1]);
                	  resp= coordinacionModel.importCoordinacionBaseBCP(fields, codUsuario,1);
                   		if (resp==0) {
                   			
                   			break;
                   		}
                   		
                   	}   
                   cant++;
                   }
			 
			 }

			 //Si se causa un error al leer cae aqui
			 catch(Exception e)
			 {
			 System.out.println("Error al leer");
			 }
		 
		}
		String field2[] = {" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "} ;
			int  resp2= coordinacionModel.importCoordinacionBaseBCP(field2, codUsuario,2);
			 System.out.println(resp2);
			if ((resp2==2) && (permitida))
					{
				logCargaDataModel.inserLogCargaData(uploadFile.getName().replace(" ", "_"),fec.getFechaActual(),usuarioInfo.getUsername());
				uploadFile.delete();
				System.out.println("Se inserto toda la carga");
				model.addAttribute("msg", "Carga Satisfactoria...");
					}
			
			if (resp2==3)
			{
		    System.out.println("No cargo nada");
			model.addAttribute("msg", "No existe data base para estas Coordinaciones");
			}
	     
			if (resp2==0)
			{
		    System.out.println("Error en el proceso");
			}
	 //-------------------------------------------------------------------------------------------
			 
//			
				uploadFile.delete();

			
		} catch (Exception e) {
			model.addAttribute("error", "1");
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
			
			view="coordinacion/coordinacion_masiva";
			//model.addAttribute("error", "true");
			model.addAttribute("vacio", "true");
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.F1.name()));
		model.addAttribute("menuSelect", Menu.D6.name());
		return view;
	}


	
	@RequestMapping(value={"/upload_coordinacionxls.htm"}, method = RequestMethod.POST)
	public String upload_coordinacionxls(Model model, @RequestParam("file") MultipartFile file, HttpServletRequest request, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		String view="coordinacion/coordinacion_masiva";
		String path =  request.getSession().getServletContext().getRealPath("dinamic/upload");
		
		System.out.println("###############  "+System.getProperty("user.dir"));
		System.out.println("###############  "+path);
		
		try {
			System.out.println("Upload coordinacionxls");
			byte[] bytes = file.getBytes();
			
			//String dirDestino = CVDinamico.getDateInFormat("yyyy-MM-dd_hhmm");
			//File dir = new File(path + File.separator + dirDestino);
			File dir = new File(path + File.separator + "BASES");
		
			if (!dir.exists())dir.mkdirs();
		
		// Carga el archivo plano en un directorio del servidor
			log.debug("Carpeta de Carga: "+dir);
			System.out.println("-->"+dir);
			File uploadFile = new File(dir.getAbsolutePath(), file.getOriginalFilename());
			
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile)); 
			stream.write(bytes);
			stream.close();
			model.addAttribute("file", file.getOriginalFilename());
		
			System.out.println("-------> dir  "+dir.getAbsolutePath());
			System.out.println("-------> file  "+file.getOriginalFilename());
			System.out.println("--uo load file >"+uploadFile);
		
			System.out.println("antes Leer Excel");
			
		//	leerexcel(dir.getAbsolutePath(),file.getOriginalFilename());
		//	leerexcel(dir.getAbsolutePath()+File.separator+file.getOriginalFilename());
			
			
			
			System.out.println("Despues Excel");
		// Luego de cargar el archivo plano, este se carga a la base de datos.
			
			 Boolean permitida;
			String codUsuario=null ;
			codUsuario=  usuarioInfo.getId().toString();
			Utils fec = new Utils();
//---------------------------------------------------
			String extension =(uploadFile.getName().substring(uploadFile.getName().lastIndexOf("."))).toLowerCase();
			 
			String[] extensiones_permitida = {".xlsx"};
			
			 permitida=false;
			 
			 for(int i=0; i<extensiones_permitida.length; i++)
			 	{
					System.out.println("extencion y permitida --------"+extensiones_permitida[i]+extension+"----"+extensiones_permitida[i].equals(extension));
					if (extensiones_permitida[i].equals(extension)) { 
				         permitida = true; 
				         break; 
				         }
				 	}

			 if (!permitida)
					model.addAttribute("error", "2");
			 
				 // Pregunta si el archivo ya ha sido cargado...no permite cargar mas de dos veces 
			
			 if 	(logCargaDataModel.countLogCargaDataNombre(uploadFile.getName().replace(" ", "_")))
			{
				permitida= false;
				//log.debug("Error Al subir los Datos, No puede Cargar un mismo archivo mas de dos veces");
				model.addAttribute("error2", "3");
			
			}
			// ------------------------------------------------
						 
			 if (permitida) 
					{
//			// String texto="";
//			 int resp=-1;
//			 int cant=0;
//			 
//			
//			    resp=leerexcel(dir.getAbsolutePath(),file.getOriginalFilename(),codUsuario);
//				 
//				 if (resp==0) {
//                   			
//                   		//	break;
//			                    		}
//			
//		 
		
					//	-------------------------------- leer excel
					
					FileInputStream is = null;
					
					File file2 = new File(dir.getAbsolutePath()+ File.separatorChar +file.getOriginalFilename());
					is = new FileInputStream(file2);
					System.out.println("entrando leer + +++"+dir.getAbsolutePath()+File.separatorChar+file.getOriginalFilename());
						
				//	FileInputStream file = new FileInputStream (new File(srutaxls+File.separatorChar+"prueba.xlsx"));
						 	//FileInputStream file = new FileInputStream (new File("C:\\prueb_excel.xls"));
					//FileInputStream file = new FileInputStream (new File("C:\\Users\\cponte\\Documents\\Mis Proyectos\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\nasqa.values\\dinamic\\upload\\BASES\\trama33.xlsx"));
					
				//		System.out.println("--->file "+file.getFD()); 	
						 	// Crear el objeto que tendra el libro de Excel
					 XSSFWorkbook workbook = null;
						try {

							   workbook = new XSSFWorkbook(is);
						} catch (Exception e) {
//							System.out.println(e.getMessage());
							e.printStackTrace();
						}

						 	
						 	/*
						
						 	 * Obtenemos la primera pestaña a la que se quiera procesar indicando el indice.
						 	 * Una vez obtenida la hoja excel con las filas que se quieren leer obtenemos el iterator
							 * que nos permite recorrer cada una de las filas que contiene.
									 	 */
						 	
						 	XSSFSheet sheet = workbook.getSheetAt(0);
						 	
						 	Iterator<Row> rowIterator = sheet.iterator();
						 	
						  
						 	int resp=1;
						 	Row row;
						 	int cant=0;
						 	// Recorremos todas las filas para mostrar el contenido de cada celda
						 	try
						    {
						 	while (rowIterator.hasNext()){
						 	
						 	    row = rowIterator.next();
						 	
						  
						 	
						 	    // Obtenemos el iterator que permite recorres todas las celdas de una fila
						 	
						 	    Iterator<Cell> cellIterator = row.cellIterator();
						 	
						 	    Cell celda;
						 	String stexto ="";
					    System.out.println("-----------------"+row.getRowNum());
					    
					    
					    String celda0=row.getCell(0)!=null?row.getCell(0).toString():" "; celda0=celda0.replace("\n", " ");celda0=celda0.replace("\r", " ");celda0=celda0+"\t";
					    System.out.println(celda0);
					    
					    String celda1=row.getCell(1)!=null?row.getCell(1).toString():" "; celda1=celda1.replace("\n", " ");celda1=celda1.replace("\r", " ");celda1=celda1+"\t";
					    String celda2=row.getCell(2)!=null?row.getCell(2).toString():" "; celda2=celda2.replace("\n", " ");celda2=celda2.replace("\r", " ");celda2=celda2+"\t";
					    String celda3=row.getCell(3)!=null?row.getCell(3).toString():" "; celda3=celda3.replace("\n", " ");celda3=celda3.replace("\r", " ");celda3=celda3+"\t";
					    String celda4=row.getCell(4)!=null?row.getCell(4).toString():" "; celda4=celda4.replace("\n", " ");celda4=celda4.replace("\r", " ");celda4=celda4+"\t";
					    String celda5=row.getCell(5)!=null?row.getCell(5).toString():" "; celda5=celda5.replace("\n", " ");celda5=celda5.replace("\r", " ");celda5=celda5+"\t";
					    String celda6=row.getCell(6)!=null?row.getCell(6).toString():" "; celda6=celda6.replace("\n", " ");celda6=celda6.replace("\r", " ");celda6=celda6+"\t";
					    String celda7=row.getCell(7)!=null?row.getCell(7).toString():" "; celda7=celda7.replace("\n", " ");celda7=celda7.replace("\r", " ");celda7=celda7+"\t";
					    String celda8=row.getCell(8)!=null?row.getCell(8).toString():" "; celda8=celda8.replace("\n", " ");celda8=celda8.replace("\r", " ");celda8=celda8+"\t";
					    String celda9=row.getCell(9)!=null?row.getCell(9).toString():" "; celda9=celda9.replace("\n", " ");celda9=celda9.replace("\r", " ");celda9=celda9+"\t";
					    String celda10=row.getCell(10)!=null?row.getCell(10).toString():" "; celda10=celda10.replace("\n", " ");celda10=celda10.replace("\r", " ");celda10=celda10+"\t";
					    String celda11=row.getCell(11)!=null?row.getCell(11).toString():" "; celda11=celda11.replace("\n", " ");celda11=celda11.replace("\r", " ");celda11=celda11+"\t";
					    String celda12="";
					    if(cant!=0){
					    	celda12=row.getCell(12)!=null? fec.getFechacadena(row.getCell(12).toString()):" "; celda12=celda12.replace("\n", " ");celda12=celda12.replace("\r", " ");celda12=celda12+"\t";
					    }else{
					    	celda12=row.getCell(12)!=null? row.getCell(12).toString():" "; celda12=celda12.replace("\n", " ");celda12=celda12.replace("\r", " ");celda12=celda12+"\t";
					    }
					      
					    String celda13=row.getCell(13)!=null?row.getCell(13).toString():" "; celda13=celda13.replace("\n", " ");celda13=celda13.replace("\r", " ");celda13=celda13+"\t";
					    String celda14=row.getCell(14)!=null?row.getCell(14).toString():" "; celda14=celda14.replace("\n", " ");celda14=celda14.replace("\r", " ");celda14=celda14+"\t";
					    String celda15=row.getCell(15)!=null?row.getCell(15).toString():" "; celda15=celda15.replace("\n", " ");celda15=celda15.replace("\r", " ");celda15=celda15+"\t";
					    String celda16=row.getCell(16)!=null?row.getCell(16).toString():" "; celda16=celda16.replace("\n", " ");celda16=celda16.replace("\r", " ");

					    stexto=celda0+celda1+celda2+celda3+celda4+celda5+celda6+celda7+celda8+celda9+celda10+celda11
					    		+celda12 
					    		
					    		+
					    				celda13+celda14+celda15+celda16;
					    System.out.println("Celda   " + stexto);
					    
					    
					    
						   String fields[] = stexto.split("\t",-1);
						   System.out.println("LONGITUD" +fields.length); 
			               if((fields.length==17)&&(cant!=0)){
			            System.out.println("Entra ... "+fields[0]);
			            System.out.println("Entra ... "+fields[1]);
			            System.out.println("Entra ... "+fields[2]);
			            System.out.println("Entra ... "+fields[3]);
			            System.out.println("Entra ... "+fields[4]);
			            System.out.println("Entra ... "+fields[5]);
			            System.out.println("Entra ... "+fields[6]);
			            System.out.println("Entra ... "+fields[7]);
			            System.out.println("Entra ... "+fields[8]);
			            System.out.println("Entra ... "+fields[9]);
			            System.out.println("Entra ... "+fields[10]);
			            System.out.println("Entra ... "+fields[11]);
			           // System.out.println("Entra fecha ... "+  fields[12]);
			        
			            
			               System.out.println((fields[12]));
			               
			            System.out.println("Entra ... "+fields[13]);
			            System.out.println("Entra ... "+fields[14]);
			            System.out.println("Entra ... "+fields[15]);
			            System.out.println("Entra ... "+fields[16]);
			            
			            resp= coordinacionModel.importCoordinacionBaseBCP(fields,codUsuario,1);
			            System.out.println("respuesta "+resp);
			               	//	if (resp==0) {
			               	//		return 0;
			               		//	break;
			               			
			               	//	}

			         
			               	}   
			               cant++;
			               
						 System.out.println("-----------------");	
						 	    
//						 	int x=0;
//						 	    while (cellIterator.hasNext()){
//						 	
//						 		celda = cellIterator.next();
//						 	
//						 	x++;
//						 		// Dependiendo del formato de la celda el valor se debe mostrar como String, Fecha, boolean, entero...
//						 	
//						 		switch(celda.getCellType()) {
//						 	
//						 		case Cell.CELL_TYPE_NUMERIC:
//						 	
//						 		    if( DateUtil.isCellDateFormatted(celda) ){
//						 	
//						 		       System.out.println(celda.getDateCellValue());
//						 	
//						 		    }else{
//						 	
//						 		       System.out.println(celda.getNumericCellValue());
//						 	
//						 		    }
//						 	
//						 		    break;
//						 	
//						 		case Cell.CELL_TYPE_STRING:
//						 	
//						 		    System.out.println(celda.getStringCellValue());
//						 	
//						 		    break;
//						 	
//						 		case Cell.CELL_TYPE_BOOLEAN:
//						 	
//						 		    System.out.println(celda.getBooleanCellValue());
//						 	
//						 		    break;
//						 	
//						 		}
//						 	
//						 		System.out.println("nro : "+ x);
//						 		// System.out.println("Data nro " + x+" "+celda.getStringCellValue()+"- ");
						// 	    }
						 
						 	}
							
						  
						    }catch (Exception e) {
//								e.System.out.println(e.getMessage());
						    	e.getMessage();
								e.printStackTrace();
								        
					         }
						 	// cerramos el libro excel
							
						 	workbook.close();			 
			///------------------------------   fin lectura		 
				 
				 
			 
			 
			 
		}
			 
			 
			 
		String field2[] = {" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "} ;
			int  resp2= coordinacionModel.importCoordinacionBaseBCP(field2, codUsuario,2);
			 System.out.println(resp2);
			if ((resp2==2) && (permitida))
					{
				logCargaDataModel.inserLogCargaData(uploadFile.getName().replace(" ", "_"),fec.getFechaActual(),usuarioInfo.getUsername());
				uploadFile.delete();
				System.out.println("Se inserto toda la carga");
				model.addAttribute("msg", "Carga Satisfactoria...");
					}
			
			if (resp2==3)
			{
		    System.out.println("No cargo nada");
			model.addAttribute("msg", "No existe data base para estas Coordinaciones");
			}
	     
			if (resp2==0)
			{
		    System.out.println("Error en el proceso");
			}
	 //-------------------------------------------------------------------------------------------
			 
//			
				uploadFile.delete();

			
		} catch (Exception e) {
			model.addAttribute("error", "1");
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
			
			view="coordinacion/coordinacion_masiva";
			//model.addAttribute("error", "true");
			model.addAttribute("vacio", "true");
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.F1.name()));
		model.addAttribute("menuSelect", Menu.D6.name());
		return view;
	}


	
	
	
	@RequestMapping(value={"/coordinacion_masivaGen.htm"})
	public String coordinacion_masivaGen(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		String view="coordinacion/coordinacion_masivaGen";
		try {
			System.out.println("Coordinacion masiva");
			
			if (usuarioInfo.getIdCliente()==null)
				user_load(model,"0000");
				else
					user_load(model,usuarioInfo.getIdCliente());
			
		//	String sPath = File.separator + sDirectorio + File.separator + sFichero;
		//	System.out.println(sPath);

			
//			String path22 =  request.getServletContext().getRealPath("dinamic/upload");
//			System.out.println("..............."+path22);
//			
//			String path2 = System.getProperty("user.dir");
//						
//			System.out.println("path2     "+path2);
//			
//			 String rootPath = System.getProperty("catalina.home"); 
//			 File dir = new File(rootPath + File.separator + "tmpFiles" );
//			 System.out.println("rootpath     "+rootPath);
//			 System.out.println("dir     "+dir);
//			
//			 System.out.println (new File (".").getAbsolutePath ());
//			 
//			 URL rutaca = CoordinacionControl.class.getProtectionDomain().getCodeSource().getLocation(); // traigo dirreccion
//			 System.out.println(rutaca);
//			 String rutama=rutaca.toString();
//			 System.out.println(File.separatorChar);
//			 System.out.println(File.separator);
			 
			 
			 
			 
			 
			 
			//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			//impExpDbModel.loadBD_reporte_gnb();
			//log.debug("La carga fue realizado correctamente");
			//view="impexpdb/success";
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.D1.name()));
		model.addAttribute("menuSelect", Menu.D7.name());
		//model.addAttribute("ref", "import");
		return view;
	}
	
	
	@RequestMapping(value={"/upload_coordinaciongen.htm"}, method = RequestMethod.POST)
	public String upload_coordinaciongen(Model model, @RequestParam("file") MultipartFile file, HttpServletRequest request, 
			@RequestParam("codCliente") String codCliente ,@ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
	
		String view="coordinacion/coordinacion_masivaGen";
		String path =  request.getSession().getServletContext().getRealPath("dinamic/upload");
		
		System.out.println("###############  "+System.getProperty("user.dir"));
		System.out.println("###############  "+path);
		
		try {
			System.out.println("Upload coordinacion");
			byte[] bytes = file.getBytes();
			
			//String dirDestino = CVDinamico.getDateInFormat("yyyy-MM-dd_hhmm");
			//File dir = new File(path + File.separator + dirDestino);
			File dir = new File(path + File.separator + "BASES");
		
			if (!dir.exists())dir.mkdirs();
		
		// Carga el archivo plano en un directorio del servidor
			log.debug("Carpeta de Carga: "+dir);
			System.out.println("-->"+dir);
			File uploadFile = new File(dir.getAbsolutePath(), file.getOriginalFilename());
			System.out.println("-->"+uploadFile);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile));
			stream.write(bytes);
			stream.close();
			model.addAttribute("file", file.getOriginalFilename());
		
			
			
			
		// Luego de cargar el archivo plano, este se carga a la base de datos.
			
			 Boolean permitida;
			String codUsuario=null ;
			codUsuario=  usuarioInfo.getId().toString();
			Utils fec = new Utils();
//---------------------------------------------------
			String extension =(uploadFile.getName().substring(uploadFile.getName().lastIndexOf("."))).toLowerCase();
			 
			String[] extensiones_permitida = {".txt"}; 
			
			 permitida=false;
			 
			 for(int i=0; i<extensiones_permitida.length; i++)
			 	{
					System.out.println("extencion y permitida --------"+extensiones_permitida[i]+extension+"----"+extensiones_permitida[i].equals(extension));
					if (extensiones_permitida[i].equals(extension)) { 
				         permitida = true; 
				         break; 
				         }
				 	}

			 if (!permitida)
					model.addAttribute("error", "2");
			 
				 // Pregunta si el archivo ya ha sido cargado...no permite cargar mas de dos veces 
			
			 if 	(logCargaDataModel.countLogCargaDataNombre(uploadFile.getName().replace(" ", "_")))
			{
				permitida= false;
				//log.debug("Error Al subir los Datos, No puede Cargar un mismo archivo mas de dos veces");
				model.addAttribute("error2", "3");
			
			}
			// ------------------------------------------------
			 int resp=-1;
			 if (permitida) 
					{
			 String texto="";
			 
			 try
			 {
			 //Creamos un archivo FileReader que obtiene lo que tenga el archivo
			 FileReader lector=new FileReader(uploadFile);

			 //El contenido del archivo (lector) se guarda en un BufferedReader
			 BufferedReader contenido=new BufferedReader(lector);

			 //Con el siguiente ciclo extraemos todo el contenido del objeto "contenido" y lo mostramos
			 
			 int cant=0;
			 while((texto=contenido.readLine())!=null)
			 {
				 
				   String fields[] = texto.split("\t",-1);
                   if((fields.length==11) && (cant!=0)) {
                //	System.out.println("---- "+Integer.toString(resp));
                //	System.out.println(fields[0]+fields[1]);
                	  resp= coordinacionModel.importCoordinacionBaseGen(fields, codUsuario,1,codCliente);
                   		if (resp==0) {
                   			
                   			break;
                   		}
                   		
                   	}   
                   cant++;
                   }
			 
			 }

			 //Si se causa un error al leer cae aqui
			 catch(Exception e)
			 {
			 System.out.println("Error al leer");
			 }
		 
		}
		String field2[] = {" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "} ;
			int  resp2= coordinacionModel.importCoordinacionBaseGen(field2, codUsuario,2,codCliente);
			 System.out.println(resp2);
			
			 if ((resp==-1)&& (permitida))
			 {
				 System.out.println("Archivo no tiene 11 columnas");
					model.addAttribute("msg", "Archivo no tiene 11 columnas y/o data inconsotente..");
			 }
			 
			 if ((resp2==2) && (permitida)&& (resp!=-1))
					{
				logCargaDataModel.inserLogCargaData(uploadFile.getName().replace(" ", "_"),fec.getFechaActual(),usuarioInfo.getUsername());
				uploadFile.delete();
				System.out.println("Se inserto toda la carga");
				model.addAttribute("msg", "Carga Satisfactoria...");
					}
			
			if (resp2==3)
			{
		    System.out.println("No cargo nada");
			model.addAttribute("msg", "No existe data base para estas Coordinaciones");
			}
	     
			if (resp2==0)
			{
		    System.out.println("Error en el proceso");
			}
			 //--------------------------------------
			
		} catch (Exception e) {
			model.addAttribute("error", "1");
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
			
			view="coordinacion/coordinacion_masivaGen";
			//model.addAttribute("error", "true");
			model.addAttribute("vacio", "true");
		}
		coordinacion_masivaGen(model, usuarioInfo);
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.F1.name()));
		model.addAttribute("menuSelect", Menu.D7.name());
		return view;
	}
	

	public static Integer leerexcel(String srutaxls ,String sfile,String scodusuario )throws IOException
	{
		
	//	-------------------------------- leer excel
		
		FileInputStream is = null;
		
		File file = new File(srutaxls+File.separatorChar+sfile);
		is = new FileInputStream(file);
		System.out.println("entrando leer + +++"+srutaxls+File.separatorChar+sfile);
			
	//	FileInputStream file = new FileInputStream (new File(srutaxls+File.separatorChar+"prueba.xlsx"));
			 	//FileInputStream file = new FileInputStream (new File("C:\\prueb_excel.xls"));
		//FileInputStream file = new FileInputStream (new File("C:\\Users\\cponte\\Documents\\Mis Proyectos\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\nasqa.values\\dinamic\\upload\\BASES\\trama33.xlsx"));
		
	//		System.out.println("--->file "+file.getFD()); 	
			 	// Crear el objeto que tendra el libro de Excel
		 XSSFWorkbook workbook = null;
			try {

				   workbook = new XSSFWorkbook(is);
			} catch (Exception e) {
//				System.out.println(e.getMessage());
				e.printStackTrace();
			}

			 	
			 	/*
			
			 	 * Obtenemos la primera pestaña a la que se quiera procesar indicando el indice.
			 	 * Una vez obtenida la hoja excel con las filas que se quieren leer obtenemos el iterator
				 * que nos permite recorrer cada una de las filas que contiene.
						 	 */
			 	
			 	XSSFSheet sheet = workbook.getSheetAt(0);
			 	
			 	Iterator<Row> rowIterator = sheet.iterator();
			 	
			  
			 	int resp=1;
			 	Row row;
			 	int cant=0;
			 	// Recorremos todas las filas para mostrar el contenido de cada celda
			 	try
			    {
			 	while (rowIterator.hasNext()){
			 	
			 	    row = rowIterator.next();
			 	
			  
			 	
			 	    // Obtenemos el iterator que permite recorres todas las celdas de una fila
			 	
			 	    Iterator<Cell> cellIterator = row.cellIterator();
			 	
			 	    Cell celda;
			 	String stexto ="";
		    System.out.println("-----------------"+row.getRowNum());
		    
		    
		    String celda0=row.getCell(0)!=null?row.getCell(0).toString():""; celda0=celda0.replace("\n", " ");celda0=celda0.replace("\r", " ");celda0=celda0+"\t";
		    System.out.println(celda0);
		    
		    String celda1=row.getCell(1)!=null?row.getCell(1).toString():" "; celda1=celda1.replace("\n", " ");celda1=celda1.replace("\r", " ");celda1=celda1+"\t";
		    String celda2=row.getCell(2)!=null?row.getCell(2).toString():" "; celda2=celda2.replace("\n", " ");celda2=celda2.replace("\r", " ");celda2=celda2+"\t";
		    String celda3=row.getCell(3)!=null?row.getCell(3).toString():" "; celda3=celda3.replace("\n", " ");celda3=celda3.replace("\r", " ");celda3=celda3+"\t";
		    String celda4=row.getCell(4)!=null?row.getCell(4).toString():" "; celda4=celda4.replace("\n", " ");celda4=celda4.replace("\r", " ");celda4=celda4+"\t";
		    String celda5=row.getCell(5)!=null?row.getCell(5).toString():" "; celda5=celda5.replace("\n", " ");celda5=celda5.replace("\r", " ");celda5=celda5+"\t";
		    String celda6=row.getCell(6)!=null?row.getCell(6).toString():" "; celda6=celda6.replace("\n", " ");celda6=celda6.replace("\r", " ");celda6=celda6+"\t";
		    String celda7=row.getCell(7)!=null?row.getCell(7).toString():" "; celda7=celda7.replace("\n", " ");celda7=celda7.replace("\r", " ");celda7=celda7+"\t";
		    String celda8=row.getCell(8)!=null?row.getCell(8).toString():" "; celda8=celda8.replace("\n", " ");celda8=celda8.replace("\r", " ");celda8=celda8+"\t";
		    String celda9=row.getCell(9)!=null?row.getCell(9).toString():" "; celda9=celda9.replace("\n", " ");celda9=celda9.replace("\r", " ");celda9=celda9+"\t";
		    String celda10=row.getCell(10)!=null?row.getCell(10).toString():" "; celda10=celda10.replace("\n", " ");celda10=celda10.replace("\r", " ");celda10=celda10+"\t";
		    String celda11=row.getCell(11)!=null?row.getCell(11).toString():" "; celda11=celda11.replace("\n", " ");celda11=celda11.replace("\r", " ");celda11=celda11+"\t";
		       String celda12=row.getCell(12)!=null?row.getCell(12).toString():" "; celda12=celda12.replace("\n", " ");celda12=celda12.replace("\r", " ");celda12=celda12+"\t";
		    String celda13=row.getCell(13)!=null?row.getCell(13).toString():" "; celda13=celda13.replace("\n", " ");celda13=celda13.replace("\r", " ");celda13=celda13+"\t";
		    String celda14=row.getCell(14)!=null?row.getCell(14).toString():" "; celda14=celda14.replace("\n", " ");celda14=celda14.replace("\r", " ");celda14=celda14+"\t";
		    String celda15=row.getCell(15)!=null?row.getCell(15).toString():" "; celda15=celda15.replace("\n", " ");celda15=celda15.replace("\r", " ");celda15=celda15+"\t";
		    String celda16=row.getCell(16)!=null?row.getCell(16).toString():" "; celda16=celda16.replace("\n", " ");celda16=celda16.replace("\r", " ");
		    
		    

		    
		    
		    stexto=celda0+celda1+celda2+celda3+celda4+celda5+celda6+celda7+celda8+celda9+celda10+celda11+celda12 +
		    				celda13+celda14+celda15+celda16;
		    System.out.println("Celda   " + stexto);
		    
		    
		    
			   String fields[] = stexto.split("\t",-1);
               if((fields.length==17)&&(cant!=0)){
            System.out.println("Entra ... "+fields[0]);
            System.out.println("Entra ... "+fields[1]);
            System.out.println("Entra ... "+fields[2]);
            System.out.println("Entra ... "+fields[3]);
            System.out.println("Entra ... "+fields[4]);
            System.out.println("Entra ... "+fields[5]);
            System.out.println("Entra ... "+fields[6]);
            System.out.println("Entra ... "+fields[7]);
            System.out.println("Entra ... "+fields[8]);
            System.out.println("Entra ... "+fields[9]);
            System.out.println("Entra ... "+fields[10]);
            System.out.println("Entra ... "+fields[11]);
            System.out.println("Entra ... "+fields[12]);
            System.out.println("Entra ... "+fields[13]);
            System.out.println("Entra ... "+fields[14]);
            System.out.println("Entra ... "+fields[15]);
            System.out.println("Entra ... "+fields[16]);
            
          
            CoordinacionModel coordinaModel=new CoordinacionModel();
            resp= coordinaModel.importCoordinacionBaseBCP(fields,scodusuario,1)!=null?coordinaModel.importCoordinacionBaseBCP(fields,scodusuario,1):10;
            System.out.println("respuesta "+resp);
               		if (resp==0) {
               			return 0;
               		//	break;
               			
               		}

         
               	}   
               cant++;
               
			 System.out.println("-----------------");	
			 	    
//			 	int x=0;
//			 	    while (cellIterator.hasNext()){
//			 	
//			 		celda = cellIterator.next();
//			 	
//			 	x++;
//			 		// Dependiendo del formato de la celda el valor se debe mostrar como String, Fecha, boolean, entero...
//			 	
//			 		switch(celda.getCellType()) {
//			 	
//			 		case Cell.CELL_TYPE_NUMERIC:
//			 	
//			 		    if( DateUtil.isCellDateFormatted(celda) ){
//			 	
//			 		       System.out.println(celda.getDateCellValue());
//			 	
//			 		    }else{
//			 	
//			 		       System.out.println(celda.getNumericCellValue());
//			 	
//			 		    }
//			 	
//			 		    break;
//			 	
//			 		case Cell.CELL_TYPE_STRING:
//			 	
//			 		    System.out.println(celda.getStringCellValue());
//			 	
//			 		    break;
//			 	
//			 		case Cell.CELL_TYPE_BOOLEAN:
//			 	
//			 		    System.out.println(celda.getBooleanCellValue());
//			 	
//			 		    break;
//			 	
//			 		}
//			 	
//			 		System.out.println("nro : "+ x);
//			 		// System.out.println("Data nro " + x+" "+celda.getStringCellValue()+"- ");
			// 	    }
			 
			 	}
				
			  
			    }catch (Exception e) {
//					e.System.out.println(e.getMessage());
			    	e.getMessage();
					e.printStackTrace();
					        
		         }
			 	// cerramos el libro excel
				
			 	workbook.close();			 
///------------------------------   fin lectura						
			 return resp;

		 }

	
	
	public void user_load(Model model,String codCliente ){
			
		if (codCliente=="0000")
		{
		
			model.addAttribute("RPAdminUserClientes", distribucionModel.getClientes());
		
		}
		else
		{
			
		Cliente dataCliente = clienteModel.buscarCliente(codCliente);
		
		ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();//creamos el objeto lista
		listaCliente.add(dataCliente);//almacenamos el cliiente en la lista
		model.addAttribute("RPAdminUserClientes", listaCliente);
		System.out.println("DATOS PRUEBA clinete en lista::::::::>"+listaCliente.size());
		}
	
	
	}
	
			
}
