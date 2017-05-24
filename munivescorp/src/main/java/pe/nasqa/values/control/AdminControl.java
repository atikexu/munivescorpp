package pe.nasqa.values.control;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hamcrest.Matcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pe.nasqa.values.model.ClienteModel;
import pe.nasqa.values.model.DistribucionModel;
import pe.nasqa.values.model.MVDinamico;
import pe.nasqa.values.model.MensajeroModel;
import pe.nasqa.values.model.PerfilModel;
import pe.nasqa.values.model.UsuarioModel;
import pe.nasqa.values.model.entity.Cliente;
import pe.nasqa.values.model.entity.ClienteAgencia;
import pe.nasqa.values.model.entity.Estados;
import pe.nasqa.values.model.entity.Mensajero;
import pe.nasqa.values.model.entity.Menu;
import pe.nasqa.values.model.entity.Perfil;
import pe.nasqa.values.model.entity.Usuario;
import pe.nasqa.values.model.entity.UsuarioEstado;

@Controller
@RequestMapping(value="/admin")
public class AdminControl {
	
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
	
	private Logger log = Logger.getLogger(AdminControl.class);
	
	@RequestMapping(value="/index.htm")
	public String inicio(Model model){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.K1.name()));
		model.addAttribute("menuSelect", Menu.K1.name());
		return "admin/index";
	}
	
	@RequestMapping(value={"/user_main.htm"})
	public String user_main(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req){
		String view="admin/user_main";
		try {
			
			user_load(model);
			
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.K1.name()));
		model.addAttribute("menuSelect", Menu.K2.name());
		return view;
	}
	
	@RequestMapping(value={"/cofig_estados.htm"})
	public String configEstados(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req){
		String view="admin/config_estados";
		try {
			
			user_load(model);
			
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.K1.name()));
		model.addAttribute("menuSelect", Menu.K5.name());
		return view;
	}
	
	@RequestMapping(value={"/save_estado.htm"})
	public String guardarEstado(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req,
			@RequestParam("idEstado") String idEstado,
			@RequestParam("nomPerso") String nomPerso){
		
		String view="admin/config_estados";
		try {
			
			clienteModel.updateEstado(idEstado, nomPerso);
			user_load(model);
			HttpSession session = req.getSession();
			String codCliente = (String) session.getAttribute("idClienteEditEstado");			
			List<Estados> estados = distribucionModel.getEstadosToComboBox("1", null, codCliente);			
			model.addAttribute("TipoSituacion", estados);
			List<Estados> motivos = distribucionModel.getEstadosToComboBox("2", null, codCliente);
			model.addAttribute("TipoMotivo", motivos);
			model.addAttribute("codigoCliente", codCliente);
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.K1.name()));
		model.addAttribute("menuSelect", Menu.K5.name());
		return view;
	}
	
	@RequestMapping(value={"/save_estadoAg.htm"},method = RequestMethod.POST)
	public String guardarEstadoAg(Model model, HttpServletRequest req,
			@RequestParam("codigo") String codigo,
			@RequestParam("nombre") String nombre,
			@RequestParam("tipo") String tipo,
			@RequestParam("direccion") String direccion,
			@RequestParam("codCliente2") String codCliente2,
			@RequestParam("ubigeo") String ubigeo,
			@RequestParam("departamento") String departamento,
			@RequestParam("provincia") String provincia,
			@RequestParam("distrito") String distrito,
			@RequestParam("coordenadas") String coordenadas,
			@RequestParam("estado") String estado
		
			
			){
		System.out.println("entroooooooo");
		String view="admin/mant_agencias";
		String mensaje;
		mensaje="";
		System.out.println(codCliente2);
		if(codCliente2.equals("") ||codCliente2.equals(null) )
		{
			System.out.println("mensaje 1");

			mensaje="Codigo de cliente en Blanco......Seleccione Cliente y dar Buscar";
			
			//model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.K1.name()));
		//	model.addAttribute("menuSelect", Menu.K6.name());
			model.addAttribute("error", "true");
			model.addAttribute("cause", mensaje);
			System.out.println("mensaje 2");
	//		return view;
		}
				
		
		try {
			
			if(mensaje.equals(""))
			{
				System.out.println("mensaje 3");
		//	clienteModel.updateEstado(idEstado, nomPerso);
			
			ClienteAgencia agencia = new ClienteAgencia();
			System.out.println("mensaje 4");
			agencia.setCodAgencia(codigo);
			agencia.setNombre(nombre);
			agencia.setTipo(tipo);
			System.out.println("mensaje 5");
			agencia.setDireccion(direccion);
			agencia.setCodCliente(codCliente2);
			agencia.setUbigeo(ubigeo);
			agencia.setDepartamento(departamento);
			agencia.setProvincia(provincia);
			agencia.setDistrito(distrito);
			agencia.setCoordenadas(coordenadas);
			agencia.setEstado(Integer.parseInt(estado));
			
			System.out.println("mensaje 6");
			 if (clienteModel.BusquedaRapidaAgencia(codigo, codCliente2))
			 {
				 System.out.println("true - actualizado");
				 clienteModel.UpdateAgencia(agencia);
			 }
				 
			 else
			 {
				 System.out.println("falso - insertado");
				 clienteModel.InsertAgencia(agencia);
			 }
				 
			 model.addAttribute("success", "true");
			 List<ClienteAgencia> listaAgencia = clienteModel.buscarAgencias(codCliente2);
			 model.addAttribute("ListaAgencia", listaAgencia);
			 
			}
			
			System.out.println("mensaje 7");
			
					user_load2(model,codCliente2);
					
			
		//	HttpSession session = req.getSession();
		//	String codCliente = (String) session.getAttribute("idClienteEditEstado");			
		
//			List<Estados> estados = distribucionModel.getEstadosToComboBox("1", null, codCliente);			
//			model.addAttribute("TipoSituacion", estados);
//			List<Estados> motivos = distribucionModel.getEstadosToComboBox("2", null, codCliente);
//			model.addAttribute("TipoMotivo", motivos);
//			
			model.addAttribute("codigoCliente", codCliente2);
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.K1.name()));
		model.addAttribute("menuSelect", Menu.K7.name());
		return view;
	}
	
	@RequestMapping(value={"/edit_estado.htm"})
	public String editEstado(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req,
			@RequestParam("idEstadoToEdit") String idEstadoToEdit,
			@RequestParam("codEstadoToEdit") String codEstadoToEdit,
			@RequestParam("desEstadoToEdit") String desEstadoToEdit,
			@RequestParam("desPerEstadoToEdit") String desPerEstadoToEdit){
		String view="admin/config_estados";
		try {
			
			user_load(model);
			HttpSession session = req.getSession();
			String codCliente = (String) session.getAttribute("idClienteEditEstado");			
			List<Estados> estados = distribucionModel.getEstadosToComboBox("1", null, codCliente);			
			model.addAttribute("TipoSituacion", estados);
			List<Estados> motivos = distribucionModel.getEstadosToComboBox("2", null, codCliente);
			model.addAttribute("TipoMotivo", motivos);
			model.addAttribute("codigoCliente", codCliente);
			model.addAttribute("codEstado", codEstadoToEdit);
			model.addAttribute("nomGenerico", desEstadoToEdit);
			model.addAttribute("nomPerso", desPerEstadoToEdit);			
			model.addAttribute("idEstado", idEstadoToEdit);	
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.K1.name()));
		model.addAttribute("menuSelect", Menu.K6.name());
		return view;
	}
	
	
	@RequestMapping(value={"/edit_estadoAg.htm"},method = RequestMethod.POST)
	public String editEstadoAg(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req,
			@RequestParam("codAgenciaToEdit") String codAgenciaToEdit,
			@RequestParam("nombreAgenciaToEdit") String nombreAgenciaToEdit,
			@RequestParam("tipoAgenciaToEdit") String tipoAgenciaToEdit,
			@RequestParam("direccionAgenciaToEdit") String direccionAgenciaToEdit,
			@RequestParam("codClienteAgenciaToEdit") String codCliente,
			@RequestParam("ubigeoAgenciaToEdit") String ubigeoAgenciaToEdit,
			@RequestParam("departamentoAgenciaToEdit") String departamentoAgenciaToEdit,
			@RequestParam("provinciaAgenciaToEdit") String provinciaAgenciaToEdit,
			@RequestParam("distritoAgenciaToEdit") String distritoAgenciaToEdit,
			@RequestParam("coordenadasAgenciaToEdit") String coordenadasAgenciaToEdit,
			@RequestParam("estadoAgenciaToEdit") String estadoAgenciaToEdit
			
			){
		
		String view="admin/mant_agencias";
		try {
			
			if (usuarioInfo.getIdCliente()==null)
				user_load2(model,"0000");
				else
					user_load2(model,codCliente);

			List<ClienteAgencia> listaAgencia = clienteModel.buscarAgencias(codCliente);
            model.addAttribute("ListaAgencia", listaAgencia);

			
			//HttpSession session = req.getSession();
			//String codCliente = (String) session.getAttribute("idClienteEditEstado");			
			//List<Estados> estados = distribucionModel.getEstadosToComboBox("1", null, codCliente);			
		
			//model.addAttribute("TipoSituacion", estados);
			//List<Estados> motivos = distribucionModel.getEstadosToComboBox("2", null, codCliente);
			
			//model.addAttribute("TipoMotivo", motivos);
			//model.addAttribute("codigoCliente", codCliente);
			model.addAttribute("codigo", codAgenciaToEdit);
			model.addAttribute("nombre", nombreAgenciaToEdit);
			model.addAttribute("tipo", tipoAgenciaToEdit);			
			model.addAttribute("direccion", direccionAgenciaToEdit);	
			model.addAttribute("ubigeo", ubigeoAgenciaToEdit);
			model.addAttribute("departamento", departamentoAgenciaToEdit);
			model.addAttribute("provincia", provinciaAgenciaToEdit);
			model.addAttribute("distrito", distritoAgenciaToEdit);
			model.addAttribute("coordenadas", coordenadasAgenciaToEdit);
			model.addAttribute("estado", estadoAgenciaToEdit);
			
			
			model.addAttribute("codCliente", codCliente);
			model.addAttribute("codCliente2", codCliente);
			
		
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.K1.name()));
		model.addAttribute("menuSelect", Menu.K7.name());
		return view;
	}
	
	@RequestMapping(value={"/selectClienteAg.htm"})
	public String getTablesEstadoByClienteAg(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req,
			@RequestParam("codCliente") String codCliente){
		String view="admin/mant_agencias";
		try{
			
			if (usuarioInfo.getIdCliente()==null)
				user_load2(model,"0000");
				else
				{
					user_load2(model,codCliente);
					
				}
			
			 List<ClienteAgencia> listaAgencia = clienteModel.buscarAgencias(codCliente);
			 model.addAttribute("ListaAgencia", listaAgencia);
			 model.addAttribute("codCliente2", codCliente);
			 model.addAttribute("codigoCliente", codCliente);
			 System.out.println("listaAgencia"+ listaAgencia.size());	
			
			
		//	List<Estados> estados = distribucionModel.getEstadosToComboBox("1", null, codCliente);
			//System.out.println("estados:"+estados.size());

			//model.addAttribute("TipoSituacion", estados);
			//List<Estados> motivos = distribucionModel.getEstadosToComboBox("2", null, codCliente);
		    //model.addAttribute("TipoMotivo", motivos);
			//model.addAttribute("codigoCliente", codCliente);
			
			HttpSession session = req.getSession();
			session.setAttribute("idClienteEditEstado", codCliente);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("cliente:"+codCliente);
		
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.K1.name()));
		model.addAttribute("menuSelect", Menu.K7.name());
		return view;
	}
	
	
	@RequestMapping(value={"/selectCliente.htm"})
	public String getTablesEstadoByCliente(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req,
			@RequestParam("codCliente") String codCliente){
		String view="admin/config_estados";
		try{
			user_load(model);
			List<Estados> estados = distribucionModel.getEstadosToComboBox("1", null, codCliente);
			System.out.println("estados:"+estados.size());
			model.addAttribute("TipoSituacion", estados);
			List<Estados> motivos = distribucionModel.getEstadosToComboBox("2", null, codCliente);
			model.addAttribute("TipoMotivo", motivos);
			model.addAttribute("codigoCliente", codCliente);
			
			HttpSession session = req.getSession();
			session.setAttribute("idClienteEditEstado", codCliente);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("cliente:"+codCliente);
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.K1.name()));
		model.addAttribute("menuSelect", Menu.K5.name());
		return view;
	}
	
	@RequestMapping(value={"/user_edit.htm"})
	public String user_edit(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req){
		String view="admin/user_main";
		try {
			
			String username=req.getParameter("username");
			model.addAttribute("RPAdminUserEdit", usuarioModel.findUserByName(username));
			user_load(model);
			
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.K1.name()));
		model.addAttribute("menuSelect", Menu.K2.name());
		return view;
	}
	
	@RequestMapping(value={"/user_save.htm"}, method = RequestMethod.POST)
	public String user_save(Model model, ModelMap modelMap, HttpServletRequest req, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		String view="admin/user_main";
		try {
			
			String username=req.getParameter("username");
			String password="";
			
			
			String idCliente=req.getParameter("idCliente").equals("0")?null:req.getParameter("idCliente");
			String tipo=idCliente==null?"DI":"CL";
			String codigo=req.getParameter("codigo");
			String nombres=req.getParameter("nombres");
			String apellidos=req.getParameter("apellidos");
			String correo=req.getParameter("correo");
			String telefono=req.getParameter("telefono");
			String area=req.getParameter("area");
			String cargo=req.getParameter("cargo");
			UsuarioEstado estado=req.getParameter("estado").equals(UsuarioEstado.ACTIVE.toString())?
					UsuarioEstado.ACTIVE:UsuarioEstado.INACTIVE;
			boolean usuarioExiste = usuarioModel.getUserDetail(username)!=null;
			if(!usuarioExiste && username.length()>0  && nombres.length()>0 && correo.length()>4){
				Usuario usuario = new Usuario();
				usuario.setUsername(username);
				usuario.setPassword(password);
				usuario.setTipo(tipo);
				usuario.setIdCliente(idCliente);
				usuario.setCodigo(codigo);
				usuario.setNombres(nombres);
				usuario.setApellidos(apellidos);
				usuario.setCorreo(correo);
				usuario.setTelefono(telefono);
				usuario.setArea(area);
				usuario.setCargo(cargo);
				usuario.setEstado(estado);
				usuarioModel.create(usuario);
				
				usuario = usuarioModel.getUserDetail(username);
				if(usuario!=null){
					String idPerfil[]=req.getParameterValues("idPerfil");
					if(idPerfil.length>0){
						Integer idPerfilInteger[]=new Integer[idPerfil.length];
						for (int i = 0; i < idPerfil.length; i++) {
							idPerfilInteger[i]=Integer.parseInt(idPerfil[i]);
						}
						usuarioModel.setPerfil(usuario.getId(), idPerfilInteger);
					}
				}
				
				model.addAttribute("success", "true");
			}else{
				model.addAttribute("error", "true");
				model.addAttribute("cause", usuarioExiste?"Usuario ya existe.":"Ingrese Nombre y Correo");
				
				model.addAttribute("codigo", codigo);
				model.addAttribute("nombres", nombres);
				model.addAttribute("apellidos", apellidos);
				model.addAttribute("correo", correo);
				model.addAttribute("telefono", telefono);
				model.addAttribute("area", area);
				model.addAttribute("cargo", cargo);
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		user_load(model);
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.K1.name()));
		model.addAttribute("menuSelect", Menu.K2.name());
		return view;
	}
	
	@RequestMapping(value={"/user_update.htm"}, method = RequestMethod.POST)
	public String user_update(Model model, ModelMap modelMap, HttpServletRequest req, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		String view="admin/user_main";
		try {
			
			String username=req.getParameter("username");
			String idCliente=req.getParameter("idCliente").equals("0")?null:req.getParameter("idCliente");
			String tipo=idCliente==null?"DI":"CL";
			String codigo=req.getParameter("codigo");
			String nombres=req.getParameter("nombres");
			String apellidos=req.getParameter("apellidos");
			String correo=req.getParameter("correo");
			String telefono=req.getParameter("telefono");
			String area=req.getParameter("area");
			String cargo=req.getParameter("cargo");
			UsuarioEstado estado=req.getParameter("estado").equals(UsuarioEstado.ACTIVE.toString())?UsuarioEstado.ACTIVE:UsuarioEstado.INACTIVE;
			if(username.length()>0 && nombres.length()>0 && correo.length()>4){
				Usuario usuario = usuarioModel.getUserDetail(username);
				usuario.setTipo(tipo);
				usuario.setIdCliente(idCliente);
				usuario.setCodigo(codigo);
				usuario.setNombres(nombres);
				usuario.setApellidos(apellidos);
				usuario.setCorreo(correo);
				usuario.setTelefono(telefono);
				usuario.setArea(area);
				usuario.setCargo(cargo);
				usuario.setEstado(estado);
				usuarioModel.update(usuario);
				
				String idPerfil[]=req.getParameterValues("idPerfil");
				if(idPerfil.length>0){
					Integer idPerfilInteger[]=new Integer[idPerfil.length];
					for (int i = 0; i < idPerfil.length; i++) {
						idPerfilInteger[i]=Integer.parseInt(idPerfil[i]);
					}
					usuarioModel.setPerfil(usuario.getId(), idPerfilInteger);
				}
				
				model.addAttribute("success", "true");
			}else{
				model.addAttribute("error", "true");
				model.addAttribute("cause", "Falta datos.");
				
				model.addAttribute("codigo", codigo);
				model.addAttribute("nombres", nombres);
				model.addAttribute("apellidos", apellidos);
				model.addAttribute("correo", correo);
				model.addAttribute("telefono", telefono);
				model.addAttribute("area", area);
				model.addAttribute("cargo", cargo);
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		user_load(model);
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.K1.name()));
		model.addAttribute("menuSelect", Menu.K2.name());
		return view;
	}
	
	@RequestMapping(value={"/user_passwd.htm"})
	public String user_reset(Model model, ModelMap modelMap, HttpServletRequest req, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		String view="admin/user_main";
		String path =  req.getSession().getServletContext().getRealPath("dinamic/tempusr");
		try {
			
			String username=req.getParameter("username");
			String password=CVDinamico.getPasswordGen(8);
			String passwordMd5=CVDinamico.getHashMD5(password);
			Usuario usuario = usuarioModel.getUserDetail(username);
			if(usuario!=null){
				String keyZip="Pass"+CVDinamico.getDateInFormat("yyyy")+CVDinamico.getPasswordGen(2);
				File directorio=new File(path);
				File passwordTxt = CVDinamico.getPasswordFileTxt(directorio, password, passwordMd5);
				//System.out.println(passwordTxt.getParent()+" >>> "+passwordTxt.getName());
				File passwordZip = new File(passwordTxt.getParent(),zip.zipFileZip4j(directorio, passwordTxt.getName(), keyZip));
				//passwordTxt.delete();
				System.out.println(passwordZip);
				if(passwordZip.exists()){
					mail.sendSimpleMail(usuario.getCorreo(), "Clave de Acceso", "Adjuntamos su clave de acceso Web (values.dataimagenes.pe), para el usuario: <b>"+username+"</b>, en un segundo correo le estaremos enviando la clave del ZIP adjunto.", usuario.getNombres(), passwordZip);
					usuario.setPassword(passwordMd5);
					usuarioModel.update(usuario);
					Thread.sleep(5L);
					mail.sendSimpleMail(usuario.getCorreo(), "Clave del ZIP Adjunto (Clave de Acceso)", "La clave para abrir el ZIP ("+passwordZip.getName()+"), es: <b>"+keyZip+"</b>", usuario.getNombres(), null);
					model.addAttribute("success", "true");
					model.addAttribute("info", "Clave enviado al "+usuario.getCorreo()+" correctamente.");
					log.info("Restart Passwd: "+usuario.getCorreo()+" to: "+passwordMd5+" zip: "+passwordZip.getName()+" key: "+keyZip);
				}else{
					log.error("No es posible continuar porque el ZIP no existe.");
				}
			}else{
				model.addAttribute("error", "true");
				model.addAttribute("cause", "Usuario no existe.");
			}
			
		} catch (Exception e) {
			model.addAttribute("error", "true");
			model.addAttribute("cause", e.getMessage());
			log.error(e.getMessage());
			e.printStackTrace();
		}
		user_load(model);
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.K1.name()));
		model.addAttribute("menuSelect", Menu.K2.name());
		return view;
	}
	
	@RequestMapping(value={"/user_passwd_chng.htm"})
	public String user_passwd_chng(Model model, ModelMap modelMap, HttpServletRequest req, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo){
		String view="admin/user_main";
		String path =  req.getSession().getServletContext().getRealPath("dinamic/tempusr");
		try {
			
			String username=req.getParameter("username");
			String password=CVDinamico.getPasswordGen(8);
			String passwordMd5=CVDinamico.getHashMD5(password);
			Usuario usuario = usuarioModel.getUserDetail(username);
			if(usuario!=null){
				String keyZip="Pass"+CVDinamico.getDateInFormat("yyyy")+CVDinamico.getPasswordGen(2);
				File directorio=new File(path);
				File passwordTxt = CVDinamico.getPasswordFileTxt(directorio, password, passwordMd5);
				//System.out.println(passwordTxt.getParent()+" >>> "+passwordTxt.getName());
				File passwordZip = new File(passwordTxt.getParent(),zip.zipFileZip4j(directorio, passwordTxt.getName(), keyZip));
				//passwordTxt.delete();
				System.out.println(passwordZip);
				if(passwordZip.exists()){
					mail.sendSimpleMail(usuario.getCorreo(), "Clave de Acceso", "Adjuntamos su clave de acceso Web (values.dataimagenes.pe), para el usuario: <b>"+username+"</b>, en un segundo correo le estaremos enviando la clave del ZIP adjunto.", usuario.getNombres(), passwordZip);
					usuario.setPassword(passwordMd5);
					usuarioModel.update(usuario);
					mail.sendSimpleMail(usuario.getCorreo(), "Clave del ZIP Adjunto (Clave de Acceso)", "La clave para abrir el ZIP ("+passwordZip.getName()+"), es: <b>"+keyZip+"</b>", usuario.getNombres(), null);
					model.addAttribute("success", "true");
					model.addAttribute("info", "Clave enviado al "+usuario.getCorreo()+" correctamente.");
					log.info("Restart Passwd: "+usuario.getCorreo()+" to: "+passwordMd5+" zip: "+passwordZip.getName()+" key: "+keyZip);
				}else{
					log.error("No es posible continuar porque el ZIP no existe.");
				}
			}else{
				model.addAttribute("error", "true");
				model.addAttribute("cause", "Usuario no existe.");
			}
			
		} catch (Exception e) {
			model.addAttribute("error", "true");
			model.addAttribute("cause", e.getMessage());
			log.error(e.getMessage());
			e.printStackTrace();
		}
		user_load(model);
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.K1.name()));
		model.addAttribute("menuSelect", Menu.K2.name());
		return view;
	}
	
	public void user_load(Model model){
		model.addAttribute("RPAdminUserPerfiles", perfilModel.findAll());
		model.addAttribute("RPAdminUserUsuarios", usuarioModel.findAll());
		model.addAttribute("RPAdminUserClientes", clienteModel.findAll());
	}
	
	
	public void user_load2(Model model,String codCliente ){
		
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
		System.out.println("codigo del cliente en lista "+ codCliente);

		             
	}

	
	
	@RequestMapping(value={"/man_mensajeros.htm"})
	public String manMensajeros(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req){
		String view="admin/mant_mensajeros";
		try {
			
			user_load(model);
			List<Mensajero> mensajeros = mensajeroModel.findAll();
		//	System.out.println("Mensajero tamaño: "+mensajeros.size());
			model.addAttribute("listaMensajeros", mensajeros);
			
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.K1.name()));
		model.addAttribute("menuSelect", Menu.K6.name());
		return view;
	}
	
	@RequestMapping(value={"/man_agencias.htm"})
	public String manAgencias(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req){
		String view="admin/mant_agencias";
		try {
			
			if (usuarioInfo.getIdCliente()==null)
				user_load2(model,"0000");
				else
					user_load2(model,usuarioInfo.getIdCliente());
			
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.K1.name()));
		model.addAttribute("menuSelect", Menu.K7.name());
		return view;
		     }

	
	@RequestMapping(value={"/mensajero_save.htm"}, method = RequestMethod.POST)
	public String mensajeroSave(Model model, ModelMap modelMap, HttpServletRequest req){
		String view="admin/mant_mensajeros";
		String mensaje="";
		try {
			user_load(model);
		
			List<Mensajero> mensajerosXdni;

			//String idCliente=req.getParameter("idCliente").equals("0")?null:req.getParameter("idCliente");
			System.out.println("ID: "+req.getParameter("idMensajero"));
			String id=req.getParameter("idMensajero");
			String codigo=req.getParameter("codigo");
			String nombres=req.getParameter("nombres");
			String apellido_pat=req.getParameter("apellido_pat");
			String apellido_mat=req.getParameter("apellido_mat");
			String dni=req.getParameter("dni");
			String correo=req.getParameter("correo");
			String telefono=req.getParameter("telefono");
			String empresa=req.getParameter("empresa");
			String estado=req.getParameter("estado");
			System.out.println("ESTADOSAVE: "+estado);
			Mensajero mensajero = new Mensajero();
			if(!id.equals("")){
				mensajero = mensajeroModel.getMensajeroDetail(Integer.parseInt(id));
			}
			
			System.out.println(validarEmail(correo));
			
			if(codigo.equals("") || nombres.equals("") || apellido_pat.equals("") || apellido_mat.equals("") || dni.equals("") || correo.equals("") || telefono.equals("") || empresa.equals("")){
				mensaje="Ingrese todos los campos.";
			}else{
				if(dni.length()!=8){
		            mensaje="Longitud de DNI no es valido, debe ser igual a 8 dígitos.";
		        }else{
		        	if(id.equals("")){
			        	mensajerosXdni=mensajeroModel.getMensajeroXDNI(dni);
			        	System.out.println("LISTA TAMAÑO: "+mensajerosXdni.size());
			        	if(mensajerosXdni.size()>0){
			        		mensaje="DNI ya registrado.";
			        	}
		        	}
		        }
				if(!validarEmail(correo)){
					mensaje="La dirección de correo electrónica es incorrecta.";
				}
			}
			
			
			if(mensaje.equals("")){
				mensajero.setCodMensajero(codigo);
				mensajero.setNombres(nombres);
				mensajero.setApellidoPat(apellido_pat);
				mensajero.setApellidoMat(apellido_mat);
				mensajero.setCorreo(correo);
				mensajero.setTelefono(telefono);
				mensajero.setDni(dni);
				mensajero.setEmpresa(empresa);
				mensajero.setEstado(Integer.parseInt(estado));
				System.out.println("ESTADOSAVEEE: "+Integer.parseInt(estado));
				if(id.equals("")){
					mensajeroModel.create(mensajero);
				}else{
					mensajeroModel.update(mensajero);
				}

				model.addAttribute("success", "true");
				
			}else{
				model.addAttribute("error", "true");
				model.addAttribute("cause", mensaje);
				
				model.addAttribute("codigo", codigo);
				model.addAttribute("nombres", nombres);
				model.addAttribute("apellido_pat", apellido_pat);
				model.addAttribute("apellido_mat", apellido_mat);
				model.addAttribute("dni", dni);
				model.addAttribute("correo", correo);
				model.addAttribute("telefono", telefono);
				model.addAttribute("empresa", empresa);
				model.addAttribute("estado", estado);
			}
			List<Mensajero> mensajeros = mensajeroModel.findAll();
			model.addAttribute("listaMensajeros", mensajeros);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		//user_load(model);
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.K1.name()));
		model.addAttribute("menuSelect", Menu.K6.name());
		return view;
	}
	
	@RequestMapping(value={"/mensajero_edit.htm"})
	public String mensajero_edit(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req){
		String view="admin/mant_mensajeros";
		try {
			
			String codigo=req.getParameter("codigo");
			model.addAttribute("RPMensajeroEdit", mensajeroModel.findUserByName(codigo));
			user_load(model);
			
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.K1.name()));
		model.addAttribute("menuSelect", Menu.K6.name());
		return view;
	}
	
	@RequestMapping(value={"/edit_mensajero.htm"})
	public String editMensajero(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req,
			
			@RequestParam("idMensajeroToEdit") String idMensajeroToEdit,
			@RequestParam("codMensajeroToEdit") String codMensajeroToEdit,
			@RequestParam("nombresToEdit") String nombresToEdit,
			@RequestParam("apellidoPatToEdit") String apellidoPatToEdit,
			@RequestParam("apellidoMatToEdit") String apellidoMatToEdit,
			@RequestParam("dniToEdit") String dniToEdit,
			@RequestParam("correoToEdit") String correoToEdit,
			@RequestParam("telefonoToEdit") String telefonoToEdit,
			@RequestParam("empresaToEdit") String empresaToEdit,
			@RequestParam("estadoToEdit") String estadoToEdit){
		
		System.out.println("ESTADO: "+estadoToEdit);
		String view="admin/mant_mensajeros";
		try {
			
			user_load(model);
			HttpSession session = req.getSession();
			String codCliente = (String) session.getAttribute("idClienteEditEstado");	
			List<Mensajero> mensajeros = mensajeroModel.findAll();
			//	System.out.println("Mensajero tamaño: "+mensajeros.size());
			model.addAttribute("listaMensajeros", mensajeros);
			
			model.addAttribute("id", idMensajeroToEdit);
			model.addAttribute("codigo", codMensajeroToEdit);
			model.addAttribute("nombres", nombresToEdit);
			model.addAttribute("apellido_pat", apellidoPatToEdit);
			model.addAttribute("apellido_mat", apellidoMatToEdit);
			model.addAttribute("dni", dniToEdit);
			model.addAttribute("correo", correoToEdit);
			model.addAttribute("telefono", telefonoToEdit);
			model.addAttribute("empresa", empresaToEdit);
			model.addAttribute("estado", estadoToEdit);
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.K1.name()));
		model.addAttribute("menuSelect", Menu.K6.name());
		return view;
	}
	
	public boolean validarEmail(String  email){
		String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	    // El email a validar

		Pattern pattern = Pattern.compile(PATTERN_EMAIL);
		 
        // Match the given input against this pattern
        java.util.regex.Matcher matcher = pattern.matcher(email);
        return matcher.matches();
	}
	
	
}
