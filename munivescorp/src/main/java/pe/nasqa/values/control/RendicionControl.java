package pe.nasqa.values.control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
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

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.dataimagenes.utils.Constantes;
import pe.nasqa.values.model.ClienteModel;
import pe.nasqa.values.model.ConfigDirecHojaRutaModel;
import pe.nasqa.values.model.DistribucionModel;
import pe.nasqa.values.model.HojaRutaDetalleModel;
import pe.nasqa.values.model.HojaRutaModel;
import pe.nasqa.values.model.MVDinamico;
import pe.nasqa.values.model.MensajeroModel;
import pe.nasqa.values.model.PerfilModel;
import pe.nasqa.values.model.RendicionDetalleModel;
import pe.nasqa.values.model.RendicionModel;
import pe.nasqa.values.model.UsuarioModel;
import pe.nasqa.values.model.entity.ChoiceBean;
import pe.nasqa.values.model.entity.ConfigDirecHojaRuta;
import pe.nasqa.values.model.entity.Distribucion;
import pe.nasqa.values.model.entity.DistribucionVisita;
import pe.nasqa.values.model.entity.Estados;
import pe.nasqa.values.model.entity.HojaRuta;
import pe.nasqa.values.model.entity.HojaRutaDetalle;
import pe.nasqa.values.model.entity.Mensajero;
import pe.nasqa.values.model.entity.Menu;
import pe.nasqa.values.model.entity.Perfil;
import pe.nasqa.values.model.entity.Rendicion;
import pe.nasqa.values.model.entity.RendicionDetalle;
import pe.nasqa.values.model.entity.Usuario;
import pe.nasqa.values.model.entity.UsuarioEstado;
import pe.nasqa.values.model.entity.Valorado;

@Controller
@RequestMapping(value="/rendicion")

@SessionAttributes({"USUARIO_INFO","USUARIO_TIPO","SAConsultaDetalle","ID_CLIENTE"})
public class RendicionControl {
	
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
	private HojaRutaModel hojaRutaModel;
	@Autowired
	private HojaRutaDetalleModel hojaRutaDetalleModel;
	@Autowired
	private ConfigDirecHojaRutaModel configDirecHojaRutaModel;
	@Autowired
	private RendicionModel rendicionModel;
	@Autowired
	private RendicionDetalleModel rendicionDetalleModel;
	@Autowired
	private ImpExpDbZip zip;
	@Autowired
	private SendMail mail;
	
	private Logger log = Logger.getLogger(RendicionControl.class);
	static Connection conn = null;
	@RequestMapping(value="/index.htm")
	public String inicio(Model model){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.O1.name()));
		model.addAttribute("menuSelect", Menu.O1.name());
		return "rendicion/index";
	}
	
	@RequestMapping(value={"/creacion_rendicion.htm"})
	public String creacionHojaRuta(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req){
		String view="rendicion/creacion_rendicion";
		try {
			user_load(model);
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.O1.name()));
		model.addAttribute("menuSelect", Menu.O2.name());
		return view;
	}
	
	////////////////////////////////
	@RequestMapping(value={"/listar_rendicion.htm"})
	public void listarRendicion(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req, HttpServletResponse response,
			@RequestParam("fec") String fec,
			@RequestParam("idCliente") String idCliente){
		response.setContentType("application/json");
        JSONObject outPut = new JSONObject(); 
        System.out.println("fecha:"+fec+" mensajero "+idCliente);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String mensaje="";
        String tablita="";
        Date convertedDate = new Date();
		try{  
			if(fec.equals("")){
				mensaje="Ingrese fecha";
			}
			if(mensaje.equals("")){
				
				
			convertedDate = dateFormat.parse(fec);
			List<Object> rendiciones =rendicionModel.getRendicionXFecIdCliente(convertedDate,idCliente);
			System.out.println("LISTA Rendiciones:"+rendiciones.size());
			tablita=getHTMLTablaRendiciones(rendiciones);
			outPut.put("tablita", tablita);
				if(rendiciones.size()>0){
	
		       }else{
		    	   outPut.put("id", "");
		       }
			}else{

			}
	       
	       response.getWriter().write(outPut.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value={"/eliminar_hoja_ruta.htm"})
	public void eliminarHojaRuta(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req, HttpServletResponse response,
			@RequestParam("fec") String fec,
			@RequestParam("idMen") String idMen,
			@RequestParam("id_rut") String id_rut){
		response.setContentType("application/json");
        JSONObject outPut = new JSONObject(); 
        System.out.println("fecha:"+fec+" mensajero "+idMen+" idruta "+id_rut);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String mensaje="";
        String tablita="";
        Date convertedDate = new Date();
		try{  
			user_load(model);
			if(idMen.equals("")){
				idMen="0";
			}
			if(mensaje.equals("")){
			HojaRuta hojRut = new HojaRuta();
			hojRut.setIdRuta(Integer.parseInt(id_rut));
			hojaRutaDetalleModel.deleteDetalleXIdRuta(Integer.parseInt(id_rut));
			hojaRutaModel.delete(hojRut);	
			convertedDate = dateFormat.parse(fec);
			List<Object> hojaRuta =hojaRutaModel.getHojaRutaXFecIdMen(convertedDate,Integer.parseInt(idMen));
			System.out.println("LISTA RUTAS:"+hojaRuta.size());
			tablita=getHTMLTablaRendiciones(hojaRuta);
			outPut.put("tablita", tablita);
			if(hojaRuta.size()>0){
				
		       }else{
		    	   outPut.put("id", "");
		       }
			}else{
			}
	       
	       response.getWriter().write(outPut.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value={"/nueva_rendicion.htm"})
	public String nuevaRendicion(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req){
		String view="rendicion/nueva_rendicion";
		System.out.println("ENTRO nueva rendicion");
		String fec=req.getParameter("fecha");
		String codCliente=req.getParameter("codCliente");
		String tablita="";
		System.out.println("Nueva rendicion: "+fec+" - "+codCliente);
		try {
			tablita=getHTMLTablaAgencias(rendicionModel.getAgenciasXIdCliente(codCliente));
			user_load(model);
			model.addAttribute("ListaMensajeros", mensajeroModel.findAll());
			model.addAttribute("fecha", fec);
			model.addAttribute("codCliente", codCliente);
			model.addAttribute("listaAgencias", tablita);
			//45model.addAttribute("nuevo", "true");
			model.addAttribute("nuevo", "true");
			
			if(fec.equals("")){
				view="rendicion/creacion_rendicion";
				model.addAttribute("error", "true");
				model.addAttribute("cause", "Ingrese fecha");
			}

			System.out.println("tablita: "+tablita);
			
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.O1.name()));
		model.addAttribute("menuSelect", Menu.O3.name());
		return view;
	}
	
	@RequestMapping(value={"/listar_agencias.htm"})
	public void listarAgencias(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req, HttpServletResponse response,
			@RequestParam("idCliente") String idCliente
			){
		response.setContentType("application/json");
        JSONObject outPut = new JSONObject(); 
        System.out.println("Cliente: "+idCliente);
        String mensaje="";
        String tablita="";
		try{  
			user_load(model);
			rendicionModel.getAgenciasXIdCliente(idCliente);
			tablita=getHTMLTablaAgencias(rendicionModel.getAgenciasXIdCliente(idCliente));
			outPut.put("tablita", tablita);
			outPut.put("mensaje", mensaje);
	       response.getWriter().write(outPut.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value={"/generar_rendicion.htm"})
	public String generarRuta(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req){
		String view="rendicion/nueva_rendicion";
		System.out.println("ENTRO generar rendicion");
		String fecha=req.getParameter("d_fecha");
		String codCliente=req.getParameter("d_idCliente");
		String codAgencia=req.getParameter("campo1");
		String nombre=req.getParameter("campo2");
		String direccion=req.getParameter("campo3");
		System.out.println("USUARIO: "+usuarioInfo.getApellidos()+" "+usuarioInfo.getNombres());
		System.out.println("FECHA rendicion: "+fecha+" - "+codCliente);
		Date convertedDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String tablita="";
		String mensaje="";
		try {
			if(codAgencia.equals("")){
				mensaje="Seleccione una agencia.";
				model.addAttribute("error", "true");
			}
			if(mensaje.equals("")){
				
				
			
			Rendicion	rendicion = new Rendicion();
			convertedDate = dateFormat.parse(fecha);
			Integer nroMax=rendicionModel.nroRendicionMax()+1;
			String cod_bar_rendicion=generarCodBar(nroMax,codCliente);
			rendicion.setFecha(convertedDate);
			rendicion.setPieza(0);
			rendicion.setIdCliente(codCliente);
			rendicion.setDestino("");
			rendicion.setCodSituacion("1");
			rendicion.setSituacion("PENDIENTE");
			rendicion.setCodMotivo("");
			rendicion.setMotivo("");
			rendicion.setDocumento("RENDICION");
			rendicion.setSolicitado("");
			rendicion.setEstado(1);
			rendicion.setRecibido("");
			rendicion.setUsuario(usuarioInfo.getApellidos()+" "+usuarioInfo.getNombres());
			rendicion.setCodAgencia(codAgencia);
			rendicion.setNroRendicion(nroMax);
			rendicion.setCodBarRendicion(cod_bar_rendicion);
			rendicion.setFechaRegistro(new Date());
			rendicion.setEstCarga(0);
			rendicionModel.create(rendicion);
			System.out.println("SE genero rendicion");
			model.addAttribute("success", "true");
			model.addAttribute("generar", "true");
			model.addAttribute("nuevo", "false");
			model.addAttribute("idRendicion", rendicion.getId());
			model.addAttribute("nroRendicion", nroMax);
			
			}
			else{
			user_load(model);
			model.addAttribute("fecha", fecha);
			model.addAttribute("codCliente", codCliente);
			model.addAttribute("codigo", codAgencia);
			model.addAttribute("descripcion", nombre);
			model.addAttribute("detalle", direccion);
			model.addAttribute("nuevo", "true");

			}
			user_load(model);
			//List<Estados> motivos = distribucionModel.getEstadosMotivosToComboBox("2", "0",null);	//2 = motivo
			List<Estados> listMotivos=distribucionModel.getEstadosMotivos("2", null,null);
			System.out.println("listaMotivos "+listMotivos.size());
			String view2="";
			//model.addAttribute("listaMotivosss", listMotivos);
			for(Estados estado : listMotivos){
				view2 += "<option value=\""+estado.getCod_estado()+"_"+estado.getDes_estado()+"_"+estado.getIdPadre()+"_"+"\">"+estado.getDes_estado()+"</option>";
			}
			model.addAttribute("listaMotivoss", view2);
			System.out.println("paso");
			model.addAttribute("fecha", fecha);
			model.addAttribute("codCliente", codCliente);
			model.addAttribute("codigo", codAgencia);
			model.addAttribute("descripcion", nombre);
			model.addAttribute("detalle", direccion);
			tablita=getHTMLTablaAgencias(rendicionModel.getAgenciasXIdCliente(codCliente));
			model.addAttribute("cause", mensaje);
			model.addAttribute("listaAgencias", tablita);
			
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.O1.name()));
		model.addAttribute("menuSelect", Menu.O2.name());
		return view;
	}
	
	@RequestMapping(value={"/listar_rendicion_cod.htm"})
	public void listaRendicionCod(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req, HttpServletResponse response,
			@RequestParam("idRendicion") String idRendicion,
			@RequestParam("codCliente") String codigo,
			@RequestParam("fecha") String fecha,
			@RequestParam("codBar") String codBar,
			@RequestParam("motivo") String motivo,
			@RequestParam("nroRendicion") String nroRendicion
			){

		response.setContentType("application/json");
        JSONObject outPut = new JSONObject(); 
        System.out.println("ruta: "+idRendicion+" fecha: "+fecha);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String mensaje="";
        String tablita="";
        Date convertedDate = new Date();
        String codMotivo="";
        String descMotivo="";
        String padre="";
		try{  
			user_load(model);
			convertedDate = dateFormat.parse(fecha);
			if(motivo.equals("")){
				mensaje="Seleccione el motivo";
			}else{
				codMotivo=motivo.split("_")[0];
				descMotivo=motivo.split("_")[1];
				padre=motivo.split("_")[2];
				System.out.println("DATOS MOTIVO: "+codMotivo+" "+descMotivo);
			}
			if(idRendicion.equals("")){
				mensaje="Genere una hoja de ruta";
			}else if(codBar.equals("")){
				mensaje="Ingrese codigo de barras";
			}else if((hojaRutaDetalleModel.existeDistribucionXCodBar(codBar)).size()==0){
				mensaje="Código no registrado en la base de datos";
			}else if((hojaRutaDetalleModel.codbarClienteSeleccionado(codBar,Integer.parseInt(idRendicion))).size()==0){
				mensaje="Codigo de barra de otro cliente";
			}else if((hojaRutaDetalleModel.getConGestion(codBar)).size()>0){//
				mensaje="Código de barra en hoja de ruta";
			}else if((hojaRutaDetalleModel.getDistribucionXCodBar(codBar)).size()>0){
				mensaje="Código de barra ya entregada";
			}else if((rendicionDetalleModel.getRendicionXCodBarFec(codBar,convertedDate)).size()>0){
				mensaje="Código de barra ya asignada";
			}
			
			if(mensaje.equals("")){
				RendicionDetalle rendicionDetalle = new RendicionDetalle();

				convertedDate = dateFormat.parse(fecha);
				rendicionDetalle.setCodMotivo(codMotivo); 
				rendicionDetalle.setDescMotivo(descMotivo);
				rendicionDetalle.setCodSituacion("");
				rendicionDetalle.setDescSituacion("");
				rendicionDetalle.setCargo(codBar);
				rendicionDetalle.setFecha(convertedDate);
				rendicionDetalle.setIdRendicion(Integer.parseInt(idRendicion));
				rendicionDetalle.setMotivo("");
				rendicionDetalle.setReferencia("");
				rendicionDetalle.setCodSituacion(padre);
				if(padre.equals("1")){
					rendicionDetalle.setDescSituacion("PENDIENTE");
				}else if(padre.equals("2")){
					rendicionDetalle.setDescSituacion("ENTREGADO");
				}else if(padre.equals("3")){
					rendicionDetalle.setDescSituacion("IMPOSIBLE");
				}
				
				rendicionDetalleModel.create(rendicionDetalle);
				
				Distribucion distribucion = new Distribucion();
				
				//Rendicion rendicion = rendicionModel.findOne(Integer.parseInt(idRendicion));
				System.out.println("nro Rendicion"+nroRendicion);

				distribucion = distribucionModel.getDistribucionXCodBar(codBar);
				
				if(codMotivo.equals("79")){
					distribucion.setCodMot(codMotivo);
					distribucion.setDesMot(descMotivo);
					distribucion.setIndSit(padre);
					distribucion.setDesSit("IMPOSIBLE");
				}
				distribucion.setIndEst("5");
				distribucion.setDesEst("Gestion");
				if(codMotivo.equals("09")){
					distribucion.setCodMot(codMotivo);
					distribucion.setDesMot(descMotivo);
					distribucion.setIndSit(padre);
					distribucion.setDesSit("IMPOSIBLE");
					distribucion.setIndEst("4");
					distribucion.setDesEst("ENVIADO");
				}
				distribucion.setNroRen(Integer.parseInt(nroRendicion));
				distribucion.setFecRen(convertedDate);
				distribucion.setFecUltima(convertedDate);
				distribucionModel.actualizarDistribucion(distribucion);	
				System.out.println("Se actualizo codBar");
				rendicionModel.updatePiezas(Integer.parseInt(idRendicion), rendicionDetalleModel.cantidadPiezas(Integer.parseInt(idRendicion)).size());
				tablita=getHTMLTablaRendicionesCod(rendicionDetalleModel.getRendicionXID(Integer.parseInt(idRendicion)),1);
				outPut.put("tablita", tablita);
		     
			}else{

			}
			outPut.put("mensaje", mensaje);
	       response.getWriter().write(outPut.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value={"/listar_rendicion_codF.htm"})
	public void listaRendicionCodF(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req, HttpServletResponse response,
			@RequestParam("idRendicion") String idRendicion,
			@RequestParam("codigoB") String codigo,
			@RequestParam("fecha") String fecha,
			@RequestParam("motivo") String motivo,
			@RequestParam("nroRendicion") String nroRendicion
			){
		response.setContentType("application/json");
        JSONObject outPut = new JSONObject(); 
        fecha = fecha.substring(8)+'/'+fecha.substring(5,7)+'/'+fecha.substring(0,4);
        System.out.println("rendicion: "+idRendicion+" fecha: "+fecha);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String mensaje="";
        String tablita="";
        Date convertedDate = new Date();
        String codMotivo="";
        String descMotivo="";
        String padre="";
		try{ /* 
			user_load(model);
			
			if(idRendicion.equals("")){
				mensaje="Genere una rendicion";
			}else if(codigo.equals("")){
				mensaje="Ingrese codigo de barras";
			}else if((hojaRutaDetalleModel.existeDistribucionXCodBar(codigo)).size()==0){
				mensaje="Código no registrado en la base de datos";
			}else if((hojaRutaDetalleModel.getDistribucionXCodBar(codigo)).size()>0){
				mensaje="Código de barra ya entregada";
			}else if((rendicionDetalleModel.getRendicionXCodBarFec(codigo,convertedDate)).size()>0){
				mensaje="Código de barra ya asignada";
			}
			System.out.println("CODBARFEC LISTA: "+rendicionDetalleModel.getRendicionXCodBarFec(codigo,convertedDate).size());
			if(mensaje.equals("")){
				RendicionDetalle rendicionDetalle = new RendicionDetalle();
				
				convertedDate = dateFormat.parse(fecha);
				
				rendicionDetalle.setCargo(codigo);
				rendicionDetalle.setFecha(convertedDate);
				rendicionDetalle.setIdRendicion(Integer.parseInt(idRendicion));
				rendicionDetalle.setMotivo("");
				rendicionDetalle.setReferencia("");
				
				rendicionDetalleModel.create(rendicionDetalle);
				
				rendicionModel.updatePiezas(Integer.parseInt(idRendicion), rendicionDetalleModel.cantidadPiezas(Integer.parseInt(idRendicion)).size());
				tablita=getHTMLTablaRendicionesCod(rendicionDetalleModel.getRendicionXID(Integer.parseInt(idRendicion)),1);
				outPut.put("tablita", tablita);
		     
			}else{

			}
			outPut.put("mensaje", mensaje);
	       response.getWriter().write(outPut.toString());*/
			user_load(model);
			convertedDate = dateFormat.parse(fecha);
			if(motivo.equals("")){
				mensaje="Seleccione el motivo";
			}else{
				codMotivo=motivo.split("_")[0];
				descMotivo=motivo.split("_")[1];
				padre=motivo.split("_")[2];
				System.out.println("DATOS MOTIVO: "+codMotivo+" "+descMotivo);
			}
			if(idRendicion.equals("")){
				mensaje="Genere una hoja de ruta";
			}else if(codigo.equals("")){
				mensaje="Ingrese codigo de barras";
			}else if((hojaRutaDetalleModel.existeDistribucionXCodBar(codigo)).size()==0){
				mensaje="Código no registrado en la base de datos";
			}else if((hojaRutaDetalleModel.codbarClienteSeleccionado(codigo,Integer.parseInt(idRendicion))).size()==0){
				mensaje="Codigo de barra de otro cliente";
			}else if((hojaRutaDetalleModel.getConGestion(codigo)).size()>0){//
				mensaje="Código de barra en hoja de ruta";
			}else if((hojaRutaDetalleModel.getDistribucionXCodBar(codigo)).size()>0){
				mensaje="Código de barra ya entregada";
			}else if((rendicionDetalleModel.getRendicionXCodBarFec(codigo,convertedDate)).size()>0){
				mensaje="Código de barra ya asignada";
			}
			
			if(mensaje.equals("")){
				RendicionDetalle rendicionDetalle = new RendicionDetalle();

				convertedDate = dateFormat.parse(fecha);
				rendicionDetalle.setCodMotivo(codMotivo); 
				rendicionDetalle.setDescMotivo(descMotivo);
				rendicionDetalle.setCodSituacion("");
				rendicionDetalle.setDescSituacion("");
				rendicionDetalle.setCargo(codigo);
				rendicionDetalle.setFecha(convertedDate);
				rendicionDetalle.setIdRendicion(Integer.parseInt(idRendicion));
				rendicionDetalle.setMotivo("");
				rendicionDetalle.setReferencia("");
				rendicionDetalle.setCodSituacion(padre);
				if(padre.equals("1")){
					rendicionDetalle.setDescSituacion("PENDIENTE");
				}else if(padre.equals("2")){
					rendicionDetalle.setDescSituacion("ENTREGADO");
				}else if(padre.equals("3")){
					rendicionDetalle.setDescSituacion("IMPOSIBLE");
				}
				rendicionDetalleModel.create(rendicionDetalle);
				
				Distribucion distribucion = new Distribucion();
				
				distribucion = distribucionModel.getDistribucionXCodBar(codigo);
				if(codMotivo.equals("79")){
					distribucion.setCodMot(codMotivo);
					distribucion.setDesMot(descMotivo);
					distribucion.setIndSit(padre);
					distribucion.setDesSit("IMPOSIBLE");
				}
				distribucion.setIndEst("5");
				distribucion.setDesEst("Gestion");
				if(codMotivo.equals("09")){
					distribucion.setCodMot(codMotivo);
					distribucion.setDesMot(descMotivo);
					distribucion.setIndSit(padre);
					distribucion.setDesSit("IMPOSIBLE");
					distribucion.setIndEst("4");
					distribucion.setDesEst("ENVIADO");
				}
				distribucion.setNroRen(Integer.parseInt(nroRendicion));
				distribucion.setFecRen(convertedDate);
				distribucion.setFecUltima(convertedDate);
				distribucionModel.actualizarDistribucion(distribucion);	
				System.out.println("Se actualizo codBar");
				rendicionModel.updatePiezas(Integer.parseInt(idRendicion), rendicionDetalleModel.cantidadPiezas(Integer.parseInt(idRendicion)).size());
				List<Object> listaRendiciones = rendicionDetalleModel.getRendicionXID(Integer.parseInt(idRendicion));
				tablita=getHTMLTablaRendicionesCod(listaRendiciones,1);
				outPut.put("tablita", tablita);
				outPut.put("piezas", listaRendiciones.size());
			}else{

			}
			outPut.put("mensaje", mensaje);
	       response.getWriter().write(outPut.toString());
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	@RequestMapping(value={"/eliminar_rendicion.htm"})
	public void eliminarRendicion(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req, HttpServletResponse response,
			@RequestParam("fecha") String fecha,
			@RequestParam("idClente") String idClente,
			@RequestParam("id_rendicion") String id_rendicion){
		response.setContentType("application/json");
        JSONObject outPut = new JSONObject(); 
        System.out.println("fecha:"+fecha+" cliente "+idClente+" idrendicion "+id_rendicion);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String mensaje="";
        String tablita="";
        Date convertedDate = new Date();
		try{  
			user_load(model);
			
			if(mensaje.equals("")){
			Rendicion rendicion = new Rendicion();
			rendicion.setId(Integer.parseInt(id_rendicion));
			
			List<Object> renDetalle=rendicionDetalleModel.listaDetalleXIdRendicion(Integer.parseInt(id_rendicion));
			Iterator itr = renDetalle.iterator();
	        while(itr.hasNext()){
	           Object[] obj = (Object[]) itr.next();
	           DistribucionVisita distribucionVisita= distribucionModel.getDistribucionVisitaXCodBar(obj[1].toString());
	           Distribucion dist=new Distribucion();
	           dist = distribucionModel.getDistribucionXCodBar(obj[1].toString());
	           if(distribucionVisita==null){
					System.out.println("no tiene visitas");
					dist.setIndSit("1");
					dist.setDesSit("Pendiente");
					dist.setIndEst("1");
					dist.setDesEst("En Oficina");
					dist.setCodMot("");
					dist.setDesMot("");
					dist.setNroRen(0);
					dist.setFecRen(null);
				}else{
					
					System.out.println("DESSIT: "+distribucionVisita.getDesSit());
					dist.setIndSit(distribucionVisita.getIndSit());
					dist.setDesSit(distribucionVisita.getDesSit());
					dist.setCodMot(distribucionVisita.getCodMot());
					dist.setDesMot(distribucionVisita.getDesMot());
					dist.setNroRen(0);
					dist.setFecRen(null);
					distribucionModel.actualizarDistribucion(dist);
				}

	        }
			
			rendicionDetalleModel.deleteDetalleXIdRendicion(Integer.parseInt(id_rendicion));
			rendicionModel.delete(rendicion);	
			convertedDate = dateFormat.parse(fecha);
			List<Object> listRendicion =rendicionModel.getRendicionXFecIdCliente(convertedDate,idClente);
			System.out.println("LISTA RUTAS:"+listRendicion.size());
			tablita=getHTMLTablaRendiciones(listRendicion);
			outPut.put("tablita", tablita);
			if(listRendicion.size()>0){
				
		       }else{
		    	   outPut.put("id", "");
		       }
			}else{

			}
	       
	       response.getWriter().write(outPut.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value={"/eliminar_rendicion_cod.htm"})
	public void eliminarRendicionCod(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req, HttpServletResponse response,
			@RequestParam("idRendicion") String idRendicion,
			@RequestParam("id_ren_det") String id_ren_det
			){
		response.setContentType("application/json");
        JSONObject outPut = new JSONObject(); 
        System.out.println("idRendicion: "+idRendicion+" rendiciondetalle "+id_ren_det);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String mensaje="";
        String tablita="";
        Date convertedDate = new Date();
		try{  
			user_load(model);

			RendicionDetalle rendicionDetalle = new RendicionDetalle();
			
			//rendicionDetalle.setId(Integer.parseInt(id_ren_det));	
			
			rendicionDetalle=rendicionDetalleModel.getDetalleXIdDetalle(Integer.parseInt(id_ren_det));
			
			/////////////////
			Distribucion distribucion = new Distribucion();
			distribucion = distribucionModel.getDistribucionXCodBar(rendicionDetalle.getCargo());
			System.out.println("codbar rendicion: "+rendicionDetalle.getCargo());
			DistribucionVisita distribucionVisita= distribucionModel.getDistribucionVisitaXCodBar(rendicionDetalle.getCargo());
			if(distribucionVisita==null){
				System.out.println("no tiene visitas");
				distribucion.setIndSit("1");
				distribucion.setDesSit("Pendiente");
				distribucion.setIndEst("1");
				distribucion.setDesEst("En Oficina");
				distribucion.setCodMot("");
				distribucion.setDesMot("");
				distribucion.setNroRen(0);
				distribucion.setFecRen(null);
			}else{
				System.out.println("DESSIT: "+distribucionVisita.getDesSit());
				distribucion.setIndSit(distribucionVisita.getIndSit());
				distribucion.setDesSit(distribucionVisita.getDesSit());
				distribucion.setCodMot(distribucionVisita.getCodMot());
				distribucion.setDesMot(distribucionVisita.getDesMot());
				distribucion.setNroRen(0);
				distribucion.setFecRen(null);
				distribucionModel.actualizarDistribucion(distribucion);
			}
			/////////////////
			rendicionDetalleModel.delete(rendicionDetalle);
			rendicionModel.updatePiezas(Integer.parseInt(idRendicion), rendicionDetalleModel.cantidadPiezas(Integer.parseInt(idRendicion)).size());
			List<Object> listaRend=rendicionDetalleModel.getRendicionXID(Integer.parseInt(idRendicion));
			tablita=getHTMLTablaRendicionesCod(listaRend,1);
			outPut.put("tablita", tablita);
			System.out.println("Se elimino el detalle");
			outPut.put("piezas", listaRend.size());
			outPut.put("mensaje", mensaje);
	       response.getWriter().write(outPut.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
}
	
	@RequestMapping(value={"/consulta_rendicion.htm"})
	public String consultaRendicion(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req){
		String view="rendicion/consulta_rendicion";
		System.out.println("ENTRO consulta rendicion");
		String id=req.getParameter("id");
		String t_fecha=req.getParameter("t_fecha");
		String t_cliente=req.getParameter("t_cliente");
		String t_piezas=req.getParameter("t_piezas");
		String t_estado=req.getParameter("t_estado");
		String t_codigo=req.getParameter("t_codigo");
		String t_descripcion=req.getParameter("t_descripcion");
		String t_detalle=req.getParameter("t_detalle");
		String t_codagencia=req.getParameter("t_codagencia");
		String t_nroRendicion=req.getParameter("t_nroRendicion");
		System.out.println("id "+id+" estado "+t_estado);
		String tablita="";
		try {
			tablita=getHTMLTablaRendicionesCod(rendicionDetalleModel.getRendicionXID(Integer.parseInt(id)),2);
			System.out.println("LISTAAA "+rendicionDetalleModel.getRendicionXID(Integer.parseInt(id)).size());
			user_load(model);
			model.addAttribute("ListaPiezas", tablita);
			model.addAttribute("t_fecha", t_fecha);
			model.addAttribute("t_cliente", t_cliente);
			model.addAttribute("t_piezas", t_piezas);
			model.addAttribute("t_id", id);
			model.addAttribute("codigo", t_codagencia);
			model.addAttribute("descripcion", t_descripcion);
			model.addAttribute("detalle", t_detalle);
			model.addAttribute("t_estado", t_estado);
			model.addAttribute("t_nroRendicion", t_nroRendicion);
			
			List<Estados> listMotivos=distribucionModel.getEstadosMotivos("2", null,null);
			System.out.println("listaMotivos "+listMotivos.size());
			String view2="";
			//model.addAttribute("listaMotivosss", listMotivos);
			for(Estados estado : listMotivos){
				view2 += "<option value=\""+estado.getCod_estado()+"_"+estado.getDes_estado()+"_"+estado.getIdPadre()+"_"+"\">"+estado.getDes_estado()+"</option>";
			}
			model.addAttribute("listaMotivoss", view2);
			
			if(Integer.parseInt(t_estado)==1){
				model.addAttribute("generar", "true");
			}
			
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.O1.name()));
		model.addAttribute("menuSelect", Menu.O3.name());
		return view;
	}
	
	@RequestMapping(value={"/cerrar_rendicion_cod.htm"})
	public void cerrarRutaCod(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req, HttpServletResponse response,
			@RequestParam("idRendicion") String idRendicion
			){
		response.setContentType("application/json");
        JSONObject outPut = new JSONObject(); 
        System.out.println("rendicion  a cerrar: "+idRendicion);
        String mensaje="";
        String tablita="";
		try{  
			user_load(model);
		
			if(mensaje.equals("")){
				rendicionDetalleModel.updateXID(Integer.parseInt(idRendicion));
				//hojaRutaDetalleModel.updateDetalleXID(Integer.parseInt(idRuta));
				tablita=getHTMLTablaRendicionesCod(rendicionDetalleModel.getRendicionXID(Integer.parseInt(idRendicion)),2);
				outPut.put("tablita", tablita);
			}else{

			}
			outPut.put("mensaje", mensaje);
	       response.getWriter().write(outPut.toString());
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	@RequestMapping(value={"/pdf_rendicion.htm"})
	public String pdfRendicion(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req){
		String view="rendicion/consulta_rendicion";
		System.out.println("ENTRO pdf rendicion");
		String id=req.getParameter("id");
		String t_fecha=req.getParameter("t_fecha");
		String t_cliente=req.getParameter("t_cliente");
		String t_piezas=req.getParameter("t_piezas");
		String t_estado=req.getParameter("t_estado");
		String t_codigo=req.getParameter("campo1");
		String t_descripcion=req.getParameter("campo2");
		String t_detalle=req.getParameter("campo3");
		String t_codagencia=req.getParameter("t_codagencia");
		String tablita="";
		String cadena="";
		System.out.println("id: "+id+" fecha "+t_fecha+" piezas "+t_piezas+" campo1 "+t_codigo);
		try {
			PdfHojaRuta pdf = new PdfHojaRuta(); 
			cadena= ""+System.currentTimeMillis();
			generarPdfRendicion(id, cadena,req);
			tablita=getHTMLTablaRendicionesCod(rendicionDetalleModel.getRendicionXID(Integer.parseInt(id)),2);
			System.out.println("LISTAAA "+rendicionDetalleModel.getRendicionXID(Integer.parseInt(id)).size());
			user_load(model);
			model.addAttribute("ListaPiezas", tablita);
			model.addAttribute("t_fecha", t_fecha);
			model.addAttribute("t_cliente", t_cliente);
			model.addAttribute("t_piezas", t_piezas);
			model.addAttribute("t_id", id);
			model.addAttribute("codigo", t_codigo);
			model.addAttribute("descripcion", t_descripcion);
			model.addAttribute("detalle", t_detalle);
			model.addAttribute("t_estado", t_estado);
			
			if(Integer.parseInt(t_estado)==1){
				model.addAttribute("generar", "true");
			}
			
		} catch (Exception e) {
			model.addAttribute("error", "true");
			System.out.println(e);
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.O1.name()));
		model.addAttribute("menuSelect", Menu.O2.name());
		return view;
	}
	
	@RequestMapping(value={"/pdf_rendicion_nuevo.htm"})
	public void pdfRendicionNuevo(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req, HttpServletResponse response,
			@RequestParam("idRendicion") String idRendicion
			){
		response.setContentType("application/json");
        JSONObject outPut = new JSONObject(); 
        String cadena="";
		try{  
			cadena= ""+System.currentTimeMillis();
			generarPdfRendicion(idRendicion, cadena,req);
			outPut.put("mensaje", "se creo pdf");
			outPut.put("cadena", cadena);
	       response.getWriter().write(outPut.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/////////////////////////////////
	
	public void user_load(Model model){
		model.addAttribute("RPAdminUserPerfiles", perfilModel.findAll());
		model.addAttribute("RPAdminUserUsuarios", usuarioModel.findAll());
		model.addAttribute("RPAdminUserClientes", distribucionModel.getClientes());
	}
	
	public String generarCodBar(Integer nroHoja, String codCli){
		String codBar="0";
		if(nroHoja>999){
			codBar=codCli+"00"+nroHoja+"000000";
		}else if(nroHoja>9999){
			codBar=codCli+"0"+nroHoja+"000000";
		}else if(nroHoja>99999){
			codBar=codCli+nroHoja+"000000";
		}
		return codBar;
	}
	
	public static String getHTMLDinamicOption(String value, String descripcion){
        return  "<option value=\""+value+"\" >"+descripcion+"</option>";
    }
	
	public static String getHTMLTablaRendicionesCod(List<Object> listaRendiciones, Integer tipo){
		Integer estado;
        String outPut = 
                            "<table id=\"table-Rendiciones\" >" +
                                "<thead>" +
                                    "<tr>" +
                                        "<td>Cargo</td>" +
                                        "<td>Pieza</td>" +
                                        "<td>Destino ide</td>" +
                                        "<td>Tramite</td>" +
                                        "<td>Situacion</td>" +
                                        "<td>Cliente</td>" +
                                    "</tr>" +
                                "</thead>" +
                                "<tbody>";
        System.out.println("TAMAÑO: "+listaRendiciones.size());
        Iterator itr = listaRendiciones.iterator();
        while(itr.hasNext()){
           Object[] obj = (Object[]) itr.next();    
           outPut +=    "<tr class='<c:if test=\"${rowCounter.count % 2 == 0}\">on</c:if>'>" +
                            "<td>"+obj[1]+"</td>" +
                            "<td>"+obj[2]+"</td>" +
                            "<td>"+obj[3]+"</td>" +
                            "<td>"+obj[4]+"</td>" +
                            "<td>"+obj[5]+"</td>" +
                            "<td>"+obj[6]+"</td>" ;
                            if(obj[7].toString().equals("1")){
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
	
	public static String getHTMLTablaRendiciones(List<Object> rendiciones){
        String outPut = 
                            "<table id=\"table-rendiciones\" >" +
                                "<thead>" +
                                    "<tr>" +
                                        "<td>Fecha</td>" +
                                        "<td>Nro</td>" +
                                        "<td>Piezas</td>" +
                                        "<td>Cliente</td>" +
                                        "<td>Destino</td>" +
                                        "<td>Situacion</td>" +
                                        "<td>Motivo</td>" +
                                        "<td>Documento</td>" +
                                        "<td>Solicitado por</td>" +
                                        "<td>Recibido por</td>" +
                                        "<td>Usuario</td>" +
                                        "<td>Fecha registro</td>" +
                                    "</tr>" +
                                "</thead>" +
                                "<tbody>";
        
        Iterator itr = rendiciones.iterator();
        while(itr.hasNext()){
           Object[] obj = (Object[]) itr.next();           
           outPut +=    "<tr class='<c:if test=\"${rowCounter.count % 2 == 0}\">on</c:if>'>" +
                            "<td>"+obj[1]+"</td>" +
                            "<td>"+obj[2]+"</td>" +
                            "<td>"+obj[3]+"</td>" +
                            "<td>"+obj[4]+"</td>" +
                            "<td>"+obj[9]+"</td>" +
                            "<td>"+obj[6]+"</td>" +
                            "<td>"+obj[7]+"</td>" +
                            "<td>"+obj[8]+"</td>" +
                            "<td>"+obj[9]+"</td>" +
                            "<td>"+obj[9]+"</td>" +
                            "<td>"+obj[11]+"</td>" +
                            "<td>"+obj[12]+"</td>" +
                            "<td style=\"width: 10px\">"+
	        				"<div>"+      
	       					"<form action=\"./consulta_rendicion.htm\" method=\"post\">"+      	
		        				"<input readonly=\"readonly\" hidden=\"\" type=\"text\" id=\"id\" value=\""+obj[0]+"\" name=\"id\"/>"+
		        				"<input readonly=\"readonly\" hidden=\"\" type=\"text\" id=\"t_fecha\" value=\""+obj[1]+"\" name=\"t_fecha\"/>"+
		        				"<input readonly=\"readonly\" hidden=\"\" type=\"text\" id=\"t_rendicion\" value=\""+obj[4]+"\" name=\"t_rendicion\"/>"+
		        				"<input readonly=\"readonly\" hidden=\"\" type=\"text\" id=\"t_piezas\" value=\""+obj[3]+"\" name=\"t_piezas\"/>"+
		        				"<input readonly=\"readonly\" hidden=\"\" type=\"text\" id=\"t_cliente\" value=\""+obj[4]+"\" name=\"t_cliente\"/>"+
		        				"<input readonly=\"readonly\" hidden=\"\" type=\"text\" id=\"t_descripcion\" value=\""+obj[14]+"\" name=\"t_descripcion\"/>"+
		        				"<input readonly=\"readonly\" hidden=\"\" type=\"text\" id=\"t_detalle\" value=\""+obj[9]+"\" name=\"t_detalle\"/>"+
		        				"<input readonly=\"readonly\" hidden=\"\" type=\"text\" id=\"t_estado\" value=\""+obj[13]+"\" name=\"t_estado\"/>"+
		        				"<input readonly=\"readonly\" hidden=\"\" type=\"text\" id=\"t_codagencia\" value=\""+obj[15]+"\" name=\"t_codagencia\"/>"+
		        				"<input readonly=\"readonly\" hidden=\"\" type=\"text\" id=\"t_nroRendicion\" value=\""+obj[2]+"\" name=\"t_nroRendicion\"/>"+
		        				//"<button id=\"btnVer\" class=\"boton-default\" onclick=\"btn_ver("+obj[0]+");\">Ver</button>"+
								"<input type=\"submit\" class=\"boton-default\" style=\"min-width: 100px;\" value=\"Ver\" id=\"smt\"/>"+
							//	"<button value=\"\" class=\"but-blue\" onclick=\"btn_click('#col1 p','#00F');\" />"+

							"</form>"+	
							"</div>"+
							"</td>";
							if(obj[13].toString().equals("1")){
				                   outPut +="<td style=\"width: 10px\">"+
					        				"<div>"+      
						        				"<button id=\"btnEliminar\" class=\"boton-default\" onclick=\"btn_eliminarRendicion("+obj[0]+");\">Eliminar</button>"+
											"</div>"+
											"</td>";
				                            }
							outPut +="</tr>";          
        }         
                outPut +=       "</tbody>" +
                            "</table>";
                        
        
         return outPut;
    }
	
	public static String getHTMLTablaAgencias(List<Object> listaAgencias){
		Integer estado;
        String outPut = 
                            "<table id=\"table-Agencia\" >" +
                                "<thead>" +
                                    "<tr>" +
                                        "<td>Codigo</td>" +
                                        "<td>Descripcion</td>" +
                                        "<td>Detalle</td>" +
                                        "<td>Ubicación</td>" +
                                    "</tr>" +
                                "</thead>" +
                                "<tbody>";
        
        Iterator itr = listaAgencias.iterator();
        while(itr.hasNext()){
           Object[] obj = (Object[]) itr.next();    
           outPut +=    "<tr class='<c:if test=\"${rowCounter.count % 2 == 0}\">on</c:if>'>" +
                            "<td class=\"boton\">"+obj[0]+"</td>" +
                            "<td class=\"boton\">"+obj[2]+"</td>" +
                            "<td class=\"boton\">"+obj[3]+"</td>" +
                            "<td class=\"boton\">"+obj[4]+"</td>" +
                            "</tr>";          
        }         
                outPut +=       "</tbody>" +
                            "</table>";
                        
        
         return outPut;
    }
	
	public void generarPdfRendicion(String id, String cadena,HttpServletRequest req){
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
	            System.out.println("Error de conexión: " + e.getMessage());
	        }

		
			System.out.println("entro para generar reporte");
			JasperReport repor;
	
			
//			repor = JasperCompileManager.compileReport(Constantes.RUTA_JASPER+"RENDICION.jrxml");
			repor = JasperCompileManager.compileReport(req.getServletContext().getRealPath("/WEB-INF/jasper/RENDICION.jrxml"));
			Map parametro = new HashMap();
			//URL  in=this.getClass().getResource("/pe/nasqa/values/PdfRutas/RENDICION.jasper");
			//System.out.println("RUTA: "+in);
			//repor=(JasperReport)JRLoader.loadObject(in);
			parametro.put("id_rendicion",Integer.parseInt(id));
	        //parametro.put("cargoEmpleado",cargoPersona);
	        
	        String sourceDir = ""; // Pdf files are read from this folder
	        String destinationDir= "imagen\\"; 
	        
	         JasperPrint jPrint = JasperFillManager.fillReport(repor, parametro,conn);
	        jPrint.setProperty("net.sf.jasperreports.expo rt.character.encoding","ISO-8859-1");

	        //JasperExportManager.exportReportToPdfFile(jPrint,Constantes.RUTA_PDF+"rendicion\\rendicion"+cadena+".pdf");
	        JasperExportManager.exportReportToPdfFile(jPrint,req.getServletContext().getRealPath("/pdf/rendicion/rendicion"+cadena+".pdf"));
	         sourceDir = "pdf\\rendicion.pdf";
	       
	        System.out.println("creo pdf");
	        File sourceFile = new File(sourceDir);
	        File destinationFile = new File(destinationDir);
	        if (!destinationFile.exists()) {
	            destinationFile.mkdir();
	        }
		}catch(JRException ex){
            System.out.println(ex.toString());
        }
	}
}
