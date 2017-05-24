package pe.nasqa.values.control;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.nasqa.values.model.CargaImgModel;
import pe.nasqa.values.model.ClienteModel;
import pe.nasqa.values.model.CoordinacionModel;
import pe.nasqa.values.model.CoordinacionTelfModel;
import pe.nasqa.values.model.DistribucionModel;
import pe.nasqa.values.model.UsuarioModel;
import pe.nasqa.values.model.entity.Cliente;
import pe.nasqa.values.model.entity.Distribucion;
import pe.nasqa.values.model.entity.DistribucionCoord;
import pe.nasqa.values.model.entity.DistribucionCoordTelf;
import pe.nasqa.values.model.entity.DistribucionPaquete;
import pe.nasqa.values.model.entity.DistribucionVisita;
import pe.nasqa.values.model.entity.Menu;
import pe.nasqa.values.model.entity.RegistroCoord;
import pe.nasqa.values.model.entity.RegistroCoordTelf;
import pe.nasqa.values.model.entity.Usuario;
import pe.nasqa.values.model.entity.UsuarioEstado;

@Controller
@RequestMapping(value="/inicio")
@SessionAttributes({"USUARIO_INFO","USUARIO_TIPO","USUARIO_CLIENTE", "CLIENTE_CONFIG_CONSRVPAQ","ID_CLIENTE"})
public class InicioControl{
	
	@Autowired
	private DistribucionModel distribucionModel;
	
	@Autowired
	private CoordinacionTelfModel coordinacionTelfModel;
	
	@Autowired
	private CargaImgModel cargaImgModel;
	
	@Autowired
	private UsuarioModel usuarioModel;
	
	@Autowired
	private CoordinacionModel coordinacionModel;
	
	@Autowired
	private ClienteModel clienteModel;
	
	@Autowired
	private SendMail mail;
	/*
	@Autowired
	private SessionFactory session;
	*/
	private Logger log = Logger.getLogger(InicioControl.class);

	@RequestMapping(value={"/index.htm"})
	public String index(Model model){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.A1.name()));
		model.addAttribute("menuSelect", Menu.A1.name());
		Authentication aut =  SecurityContextHolder.getContext().getAuthentication();
		if(aut != null && aut.isAuthenticated() && !aut.getName().equals("anonymousUser")){
			log.debug("Usuario CON Session :"+aut.getName());
			
			Usuario usuario_info = usuarioModel.getUserDetail(aut.getName());
			String  usuario_tipo = usuario_info.getTipo();
			String  codigo_cliente = usuario_info.getIdCliente();
			String 	delete_coord = usuario_info.getDeleteCoord();
			
			model.addAttribute("USUARIO_INFO", usuario_info);
			model.addAttribute("USUARIO_TIPO", usuario_tipo);
			if(usuario_info.getIdCliente()!=null){
				Cliente usuario_cliente = clienteModel.buscarCliente(usuario_info.getIdCliente());
				model.addAttribute("USUARIO_CLIENTE", usuario_cliente);
				Integer conServPaqueteria=0;
				conServPaqueteria=clienteModel.opcionConServPaqueteria(usuario_info.getIdCliente());
				if(conServPaqueteria==null)conServPaqueteria=0;
				model.addAttribute("CLIENTE_CONFIG_CONSRVPAQ", conServPaqueteria);
				model.addAttribute("ID_CLIENTE", codigo_cliente);
				//model.addAttribute("DELETE_CORD", delete_coord);
				System.out.println("codigo_cliente:"+codigo_cliente);
			}
		}else{
			log.debug("Usuario SIN Session ");
		}
		return "inicio/index";
	}
	
	@RequestMapping(value={"/login.htm"})
	public String login(Model model){
		model.addAttribute("pageTitle", "Ingreso");
		model.addAttribute("menuSelect", Menu.A1.name());
		return "inicio/login";
	}
	
	@RequestMapping(value={"/register.htm"})
	public String registro(Model model){
		model.addAttribute("pageTitle", "Registro de Nuevo Usuario");
		model.addAttribute("menuSelect", Menu.A1.name());
		return "inicio/register";
	}
	
	@RequestMapping(value={"/passwd_form.htm"})
	public String passwd_form(Model model){
		model.addAttribute("pageTitle", "Ingreso");
		model.addAttribute("menuSelect", Menu.A1.name());
		return "inicio/passwd";
	}
	
	@RequestMapping(value={"/passwd_save.htm"})
	public String user_passwd_chng(Model model, ModelMap modelMap, HttpServletRequest req, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		String view="inicio/passwd";
		try {
			
			String username=usuarioInfo.getUsername();
			String password_act=req.getParameter("contrasena_act");
			String password=req.getParameter("contrasena");
			String password_rep=req.getParameter("contrasena_rep");
			if(password_act.length()>0 && password.length()>7 && password.equals(password_rep)){
				String passwordMd5Act=CVDinamico.getHashMD5(password_act);
				String passwordMd5New=CVDinamico.getHashMD5(password);
				log.info("Cambiando...: "+username+" to: "+password+" ("+passwordMd5New+") from: "+password_act + "("+passwordMd5Act+")");
				if(usuarioModel.changePassword(username, passwordMd5Act, passwordMd5New)){
					model.addAttribute("success", "true");
					model.addAttribute("info", "Su Contraseña fue cambiado correctamente.");
					log.info("Change Passwd: "+username+" to: "+password+" ("+passwordMd5New+") from: "+password_act + "("+passwordMd5Act+")");
					mail.sendSimpleMail(usuarioInfo.getCorreo(), "Cambio de contraseña", "Su contraseña fue cambiado correctamente a: <b>"+password+"</b>", usuarioInfo.getNombres(), null);
				}else{
					model.addAttribute("error", "true");
					model.addAttribute("cause", "Contraseña actual no coincide.");
				}
			}else{
				model.addAttribute("error", "true");
				model.addAttribute("cause", "Contraseña no coincide y/o no es mayor a 8 caracteres.");
			}
			
		} catch (Exception e) {
			model.addAttribute("error", "true");
			model.addAttribute("cause", e.getMessage());
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.A1.name()));
		model.addAttribute("menuSelect", Menu.A1.name());
		return view;
	}
	
	@RequestMapping(value={"/try-register.htm"})
	public String try_registro(Model model, HttpServletRequest req){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.A1.name()));
		model.addAttribute("menuSelect", Menu.A1.name());
		
//		String username = req.getParameter("username")==null?"":req.getParameter("username");
//		String password = req.getParameter("password")==null?"":req.getParameter("password");
//		String password_r = req.getParameter("password_r")==null?"":req.getParameter("password_r");
		String nombres = req.getParameter("nombres")==null?"":req.getParameter("nombres");
		String apellidos = req.getParameter("apellidos")==null?"":req.getParameter("apellidos");
		String correo = req.getParameter("correo")==null?"":req.getParameter("correo");
		String telefono = req.getParameter("telefono")==null?"":req.getParameter("telefono");
		String area = req.getParameter("area")==null?"":req.getParameter("area");
		String cargo = req.getParameter("cargo")==null?"":req.getParameter("cargo");
		
		if(/*!username.equals("")&&
			!password.equals("")&&
			!password_r.equals("")&&
			 password_r.equals(password)&&*/
			!nombres.equals("")&&
			!apellidos.equals("")&&
			!correo.equals("")&&
			!telefono.equals("")&&
			!area.equals("")&&
			!cargo.equals("")){
			
			Usuario usuario = new Usuario();
			usuario.setUsername("");
			usuario.setPassword("");
			usuario.setTipo(CVConstante.USUARIO_TIPO_PUBLICO);
			usuario.setIdCliente("");
			usuario.setCodigo("0");
			usuario.setNombres(nombres);
			usuario.setApellidos(apellidos);
			usuario.setCorreo(correo);
			usuario.setTelefono(telefono);
			usuario.setArea(area);
			usuario.setCargo(cargo);
			usuario.setEstado(UsuarioEstado.INACTIVE);
			
			usuarioModel.create(usuario);
			
			model.addAttribute("success", "true");
			
			log.info("Usuario ["+usuario.getUsername()+"] creado");
			
		}else{
			
			model.addAttribute("error", "true");
			//model.addAttribute("username", username);
			//model.addAttribute("nombres", nombres);
			model.addAttribute("apellidos", apellidos);
			model.addAttribute("correo", correo);
			model.addAttribute("telefono", telefono);
			model.addAttribute("area", area);
			model.addAttribute("cargo", cargo);
		}
		
		return "inicio/register";
	}
	
	@RequestMapping(value="/page403.htm")
	public String error403(Model model){
		model.addAttribute("pageTitle", "Acceso denegado");
		model.addAttribute("menuSelect", Menu.A1.name());
		return "inicio/error403";
	}
	
	@RequestMapping(value="/page404.htm")
	public String error404(Model model){
		model.addAttribute("pageTitle", "Pagina no encontrado");
		model.addAttribute("menuSelect", Menu.A1.name());
		return "inicio/error404";
	}
	
	@RequestMapping(value={"/consulta_rapida.htm"})
	public String consulta_rapida(Model model){
		model.addAttribute("pageTitle", "Consulta Rapida");
		model.addAttribute("menuSelect", Menu.A1.name());
		return "inicio/consulta_rapida";
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
	
	@RequestMapping(value={"/busqueda_rapida.htm"}) 
	public String buscar(Model model, HttpServletRequest req, 			
			@RequestParam("codBar") String codBar,
			@RequestParam("nroRef") String nroRef,
			@RequestParam("docIde") String docIde){
		
		boolean hasPermiso = false;
		String view = "inicio/consulta_rapida";
		if(!codBar.equals("")||!nroRef.equals("")||!docIde.equals("")){
			hasPermiso = clienteModel.hasBusquedaRapida(codBar,nroRef,docIde);
		}
		
		if(hasPermiso){	//SOLO SI EL CLIENTE TIENE PERMISO DE BUQSUEDA RAPIDA
			model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.B1.name()));
			model.addAttribute("menuSelect", Menu.B1.name());
			
			HttpSession session = req.getSession();
			String tipoBuscar = req.getParameter("tipoBuscar")==null?"0":req.getParameter("tipoBuscar");
			
			model.addAttribute("codBar", codBar);
			model.addAttribute("nroRef", nroRef);
			model.addAttribute("docIde", docIde);
			model.addAttribute("tipoBuscar", tipoBuscar);
			
			int totalPaquetes = 0;
			if(codBar.length()>0 || nroRef.length()>0 || docIde.length()>0){
				/*
				String codCliente=null;
				if(usuarioInfo.getTipo().equals(CVConstante.USUARIO_TIPO_CLIENTE)){
					codCliente = usuarioInfo.getIdCliente();				
				}
				*/
				if(tipoBuscar.equals("0")){
					List<Distribucion> listaResultado = distribucionModel.findDistCodName(codBar, nroRef, docIde, "", null);//codCiente
					if(listaResultado!=null){
						if(listaResultado.size()>1){
							model.addAttribute("resultadoBusqueda", listaResultado);
						}else if(listaResultado.size()==1){
							model.addAttribute("codBar", codBar);
							model.addAttribute("menuSelect", Menu.B2.name());
							model.addAttribute("SAConsultaDetalle", listaResultado.get(0));
							session.setAttribute("SAConsultaDetalle", listaResultado.get(0));
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
							view = "inicio/consulta_rapida_detalle";
						}
					}
				}else if(tipoBuscar.equals("PQ")){
					List<DistribucionPaquete> listaResultadoPaquete = distribucionModel.findPaqueteCodName(nroRef, "", null);
					if(listaResultadoPaquete!=null){
						if(listaResultadoPaquete.size()>1){
							model.addAttribute("resultadoBusquedaPaquete", listaResultadoPaquete);
						}else if(listaResultadoPaquete.size()==1){
							Distribucion distribucion = distribucionModel.getByCodBar(listaResultadoPaquete.get(0).getCodBar(), null);
							model.addAttribute("codBar", codBar);
							model.addAttribute("idPaquete", listaResultadoPaquete.get(0).getId());
							model.addAttribute("menuSelect", Menu.B2.name());
							model.addAttribute("SAConsultaDetalle", distribucion);
							session.setAttribute("SAConsultaDetalle", distribucion);
							
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
							view = "inicio/consulta_rapida_detalle";
						}
					}
				}			
				
			}			
		}else{
			model.addAttribute("mensajeBusqueda", "El cliente no cuenta con los permisisos de búsqueda rápida.");
		}
		return view;
	}
	
	@RequestMapping(value={"/consulta_coord_rapida.htm"}) 
	public String coordinaciones(Model model, ModelMap modelMap, HttpServletRequest req){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.B1.name()));
		model.addAttribute("menuSelect", Menu.B3.name());
		
		//Distribucion distribucion = (Distribucion)modelMap.get("SAConsultaDetalle");
		HttpSession session = req.getSession();
		Distribucion distribucion = (Distribucion)session.getAttribute("SAConsultaDetalle");
		model.addAttribute("SAConsultaDetalle", distribucion);
		List<DistribucionCoord> distribucionCoordinaciones = distribucionModel.findCoordinaciones(distribucion.getCodBar());
		model.addAttribute("RPConsultaDetalleCoordinaciones", distribucionCoordinaciones);
		
		List<RegistroCoord> distribucionCoords = coordinacionModel.buscarPorCodBar(distribucion.getCodBar());
		model.addAttribute("RPCoordinacionRegistCoords", distribucionCoords);
		return "inicio/consulta_rapida_coord";
	}
	
	@RequestMapping(value={"/consulta_rapida_coord_telf.htm"}) 
	public String gestionesTelefonicas(Model model, ModelMap modelMap, HttpServletRequest req){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.B1.name()));
		model.addAttribute("menuSelect", Menu.B4.name());
		
		//Distribucion distribucion = (Distribucion)modelMap.get("SAConsultaDetalle");
		HttpSession session = req.getSession();
		Distribucion distribucion = (Distribucion)session.getAttribute("SAConsultaDetalle");
		List<DistribucionCoordTelf> distribucionCoordTelfs = distribucionModel.findCoordinacionesTelf(distribucion.getCodBar());
		model.addAttribute("RPConsultaDetalleCoordTelfs", distribucionCoordTelfs);
		
		List<RegistroCoordTelf> registroCoordTelfs = coordinacionTelfModel.buscarPorCodBar(distribucion.getCodBar());
		model.addAttribute("RPCoordinacionRegistCoordTelfs", registroCoordTelfs);
		
		return "inicio/consulta_rapida_coord_telf";
	}
	
	@RequestMapping(value={"/consulta_detalle_rap.htm"}) 
	public String detalles(Model model, HttpServletRequest req, @RequestParam("codBar") String codBar){
		
		String view = "inicio/consulta_rapida";
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.B1.name()));
		model.addAttribute("menuSelect", Menu.B1.name());
		String idPaquete=req.getParameter("idPaquete");
		
		boolean hasPermiso = false;
		if(!codBar.equals("")){
			hasPermiso = clienteModel.hasBusquedaRapida(codBar,"","");
		}
		
		if(hasPermiso){
		
			Distribucion distribucion = distribucionModel.getByCodBar(codBar,null);
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
				view = "inicio/consulta_rapida_detalle";
				model.addAttribute("menuSelect", Menu.B2.name());
				model.addAttribute("SAConsultaDetalle", distribucion);
				
				List<DistribucionVisita> distribucionVisitas = distribucionModel.findVisitas(distribucion.getCodBar());
				model.addAttribute("RPConsultaDetalleVisitas", distribucionVisitas);
				model.addAttribute("RPConsultaDetalleCoordEntregado", distribucionModel.findDireccionEntrega(distribucionVisitas));
				
				List<DistribucionPaquete> distribucionPaquetes = distribucionModel.findPaquetes(distribucion.getCodBar());
				model.addAttribute("RPConsultaDetallePaquetes", distribucionPaquetes);
				model.addAttribute("idPaquete", idPaquete);
			}
		}else{
			model.addAttribute("mensajeBusqueda", "El cliente no cuenta con los permisisos de búsqueda rápida.");
		}
		return view;
	}
}
