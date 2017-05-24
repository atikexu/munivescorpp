package pe.nasqa.values.control;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.io.PrintWriter;
import java.net.MalformedURLException;
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
import pe.nasqa.values.model.MUtilReportFiles;
import pe.nasqa.values.model.MVDinamico;
import pe.nasqa.values.model.MensajeroModel;
import pe.nasqa.values.model.PerfilModel;
import pe.nasqa.values.model.RendicionDetalleModel;
import pe.nasqa.values.model.RendicionModel;
import pe.nasqa.values.model.TipoZonaModel;
import pe.nasqa.values.model.UsuarioModel;
import pe.nasqa.values.model.entity.ChoiceBean;
import pe.nasqa.values.model.entity.ClienteAgencia;
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
@RequestMapping(value="/hojaruta")
@SessionAttributes({"USUARIO_INFO","USUARIO_TIPO","SAConsultaDetalle","ID_CLIENTE"})
public class HojaRutaControl {
	
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
	private TipoZonaModel tipoZonaModel;
	@Autowired
	private RendicionModel rendicionModel;
	@Autowired
	private RendicionDetalleModel rendicionDetalleModel;
	
	@Autowired
	private ImpExpDbZip zip;
	@Autowired
	private SendMail mail;
	
	
	String rutasa;
	private Logger log = Logger.getLogger(HojaRutaControl.class);
	static Connection conn = null;
	@RequestMapping(value="/index.htm")
	public String inicio(Model model){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.N1.name()));
		model.addAttribute("menuSelect", Menu.N1.name());
		return "hojaruta/index";
	}
	
	@RequestMapping(value={"/configurar_direccion.htm"})
	public String generarCargos(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req){
		String view="hojaruta/configurar_direccion";
		try {
			
			user_load(model);
			
			
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.N1.name()));
		model.addAttribute("menuSelect", Menu.N2.name());
		return view;
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

		   List<ChoiceBean> listaProductos = new ArrayList<ChoiceBean>();
	       ChoiceBean bean = new ChoiceBean();
	       selectProductos+="<option value=\"\" >::SELECCIONAR::</option>";
	       for(Valorado val : valorado){
	    	   bean = new ChoiceBean();
	    	   bean.setValue(val.getId());
		       bean.setDescripcion(val.getNombre());
		       listaProductos.add(bean);
		       selectProductos+=getHTMLDinamicOption(val.getCodTipo().getCodTipo(), val.getNombre());
	       }
	       outPut.put("selectProductos", selectProductos);
	       System.out.println("LISTA PRODUCTOS ultimo:"+listaProductos.size());
	       response.getWriter().write(outPut.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		//model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.M1.name()));
		//model.addAttribute("menuSelect", Menu.M2.name());
		//return view;
	}
	
	@RequestMapping(value={"/guardar_direccion.htm"})
	public String guardarDireccion(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req,
			@RequestParam("codCliente") String codCliente,
			@RequestParam("productos") String productos,
			@RequestParam("id1") String id1,
			@RequestParam("id2") String id2,
			@RequestParam("id3") String id3,
			@RequestParam("id4") String id4,
			@RequestParam("visita1") String visita1,
			@RequestParam("visita2") String visita2,
			@RequestParam("visita3") String visita3,
			@RequestParam("visita4") String visita4){
		
		String view="hojaruta/configurar_direccion";
		String mensaje="";
		try {
			
			System.out.println("ESTADO: "+id1+" "+codCliente+" "+productos+" "+visita1+" "+visita2+" "+visita3+" "+visita4);
			
			if(visita1.equals("") || visita2.equals("")){
				mensaje="Debe seleccionar al menos las 2 primeras visitas";
			}
			
			if(mensaje.equals("")){
				ConfigDirecHojaRuta configDirecHojaRuta1 = new ConfigDirecHojaRuta();
				ConfigDirecHojaRuta configDirecHojaRuta2 = new ConfigDirecHojaRuta();
				ConfigDirecHojaRuta configDirecHojaRuta3 = new ConfigDirecHojaRuta();
				ConfigDirecHojaRuta configDirecHojaRuta4 = new ConfigDirecHojaRuta();
				
				configDirecHojaRuta1.setCodCliente(codCliente);
				configDirecHojaRuta1.setCodProducto((productos));
				configDirecHojaRuta1.setDireccion(visita1);
				configDirecHojaRuta1.setOrden(1);
				configDirecHojaRuta1.setEstado(1);
				
				configDirecHojaRuta2.setCodCliente(codCliente);
				configDirecHojaRuta2.setCodProducto((productos));
				configDirecHojaRuta2.setDireccion(visita2);
				configDirecHojaRuta2.setOrden(2);
				configDirecHojaRuta2.setEstado(1);
				
				configDirecHojaRuta3.setCodCliente(codCliente);
				configDirecHojaRuta3.setCodProducto((productos));
				configDirecHojaRuta3.setDireccion(visita3);
				configDirecHojaRuta3.setOrden(3);
				configDirecHojaRuta3.setEstado(1);

				configDirecHojaRuta4.setCodCliente(codCliente);
				configDirecHojaRuta4.setCodProducto((productos));
				configDirecHojaRuta4.setDireccion(visita4);
				configDirecHojaRuta4.setOrden(4);
				configDirecHojaRuta4.setEstado(1);
				
				if(id1.equals("")){
					configDirecHojaRutaModel.create(configDirecHojaRuta1);
					configDirecHojaRutaModel.create(configDirecHojaRuta2);
					configDirecHojaRutaModel.create(configDirecHojaRuta3);
					configDirecHojaRutaModel.create(configDirecHojaRuta4);
				}else{
					configDirecHojaRuta1.setIdConfig(Integer.parseInt(id1));
					configDirecHojaRuta2.setIdConfig(Integer.parseInt(id2));
					configDirecHojaRuta3.setIdConfig(Integer.parseInt(id3));
					configDirecHojaRuta4.setIdConfig(Integer.parseInt(id4));
					configDirecHojaRutaModel.update(configDirecHojaRuta1);
					configDirecHojaRutaModel.update(configDirecHojaRuta2);
					configDirecHojaRutaModel.update(configDirecHojaRuta3);
					configDirecHojaRutaModel.update(configDirecHojaRuta4);
				}
				System.out.println("SE GUARDO o ACTUALIZO");
				model.addAttribute("success", "true");
			}else{
				model.addAttribute("error", "true");
				model.addAttribute("cause", mensaje);
				model.addAttribute("codCliente", codCliente);
				model.addAttribute("productos", productos);
				System.out.println("IDS "+id1+" "+id2);
			}
			user_load(model);
			//HttpSession session = req.getSession();
			//model.addAttribute("TipoMotivo", motivos);
			//model.addAttribute("codigoCliente", codCliente);
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.N1.name()));
		model.addAttribute("menuSelect", Menu.N2.name());
		return view;
	}
	
	@RequestMapping(value={"/cargar_direcciones.htm"})
	public void cargarDirecciones(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req, HttpServletResponse response,
			@RequestParam("codCliente") String codCliente,
			@RequestParam("productos") String productos){
		response.setContentType("application/json");
        JSONObject outPut = new JSONObject(); 
		try{  
			user_load(model);
			List<ConfigDirecHojaRuta> configDirecHojaRuta = configDirecHojaRutaModel.getConfigXClienteProducto(codCliente,productos);
			System.out.println("LISTA PRODUCTOS:"+configDirecHojaRuta.size());

	       int i=1;
	       if(configDirecHojaRuta.size()>0){
		       for(ConfigDirecHojaRuta con : configDirecHojaRuta){
		    	   
		    	   System.out.println("ORDEN: "+con.getOrden());
		    	   outPut.put("direc"+i, con.getDireccion());
		    	   outPut.put("id"+i, con.getIdConfig());
		    	   i++;
		       }
		       
	       }else{
	    	   outPut.put("id", "");
	       }
	       response.getWriter().write(outPut.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		//model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.M1.name()));
		//model.addAttribute("menuSelect", Menu.M2.name());
		//return view;
	}
	
	@RequestMapping(value={"/creacion_hojaruta.htm"})
	public String creacionHojaRuta(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req){
		String view="hojaruta/creacion_hojaruta";
		try {
			
			user_load(model);
			model.addAttribute("ListaMensajeros", mensajeroModel.findAll());
			//model.addAttribute("ListaRutas", hojaRutaModel.findAll());
			System.out.println("TAMA�O: "+hojaRutaModel.findAll().size());
			
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.N1.name()));
		model.addAttribute("menuSelect", Menu.N3.name());
		return view;
	}
	
	////////////////////////////////
	@RequestMapping(value={"/listar_ruta.htm"})
	public void listaRuta(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req, HttpServletResponse response,
			@RequestParam("fec") String fec,
			@RequestParam("idMen") String idMen){
		response.setContentType("application/json");
        JSONObject outPut = new JSONObject(); 
        System.out.println("fecha:"+fec+" mensajero "+idMen);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String mensaje="";
        String tablita="";
        Date convertedDate = new Date();
		try{  
			user_load(model);
			if(fec.equals("")){
				mensaje="Ingrese fecha";
			}
			if(idMen.equals("")){
				idMen="0";
			}
			if(mensaje.equals("")){
				
				
			convertedDate = dateFormat.parse(fec);
			List<Object> hojaRuta =hojaRutaModel.getHojaRutaXFecIdMen(convertedDate,Integer.parseInt(idMen));
			System.out.println("LISTA RUTAS:"+hojaRuta.size());
			tablita=getHTMLTablaRutas(hojaRuta);
			outPut.put("tablita", tablita);
			if(hojaRuta.size()>0){

		       }else{
		    	   outPut.put("id", "");
		       }
			}else{
				//model.addAttribute("error", "true");
				//model.addAttribute("cause", mensaje);
			}
	       
	       response.getWriter().write(outPut.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		//model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.M1.name()));
		//model.addAttribute("menuSelect", Menu.M2.name());
		//return view;
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
			
			List<Object> hojDetalle = hojaRutaDetalleModel.listaEntregados(Integer.parseInt(id_rut));
			if(hojDetalle.size()>0){
				mensaje="No se puede anular, una pieza ya entregada.";
			}
			if(mensaje.equals("")){
			HojaRuta hojRut = new HojaRuta();
			hojRut.setIdRuta(Integer.parseInt(id_rut));
			
			//cambiar estados a codbarras y rendidicones
			
			List<Object> listaHojaDetalles = hojaRutaDetalleModel.cantidadHojaRutaDetalle(Integer.parseInt(id_rut));//retorna lista hoja ruta detalle
			Iterator itr = listaHojaDetalles.iterator();
	        while(itr.hasNext()){
	           Object[] obj = (Object[]) itr.next();  
	           Rendicion rendicion = new Rendicion();
	           
	           rendicion=rendicionDetalleModel.getDetalleXIdDetalleHoja(Integer.parseInt(obj[2].toString()));
	           
	           
	           if(rendicion!=null){
					System.out.println("CODIGO BARRA RENDICION: "+rendicion.getCodBarRendicion()+" "+rendicion.getId());
					List<Object> renDetalle=rendicionDetalleModel.listaDetalleXIdRendicion(rendicion.getId());
					Iterator itr1 = renDetalle.iterator();
			        while(itr1.hasNext()){
			           Object[] obj1 = (Object[]) itr1.next();
			           DistribucionVisita distribucionVisita= distribucionModel.getDistribucionVisitaXCodBar(obj1[1].toString());
			           distribucionModel.delete(distribucionVisita);
			           distribucionVisita= distribucionModel.getDistribucionVisitaXCodBar(obj1[1].toString());
			           Distribucion dist=new Distribucion();
			           dist = distribucionModel.getDistribucionXCodBar(obj1[1].toString());
			           if(distribucionVisita==null){
							System.out.println("no tiene visitas");
							
							dist.setIndSit("1");
							dist.setDesSit("Pendiente");
							dist.setCodMot("");
							dist.setDesMot("");
							dist.setFecUltimaVisita(null);
					        dist.setResUltimaVisita(null);
					        dist.setTipoEntrega("");
							distribucionModel.actualizarDistribucion(dist);
						}else{
							System.out.println("DESSIT: "+distribucionVisita.getDesSit());
							dist.setIndSit(distribucionVisita.getIndSit());
							dist.setDesSit(distribucionVisita.getDesSit());
							dist.setCodMot(distribucionVisita.getCodMot());
							dist.setDesMot(distribucionVisita.getDesMot());
							distribucionModel.actualizarDistribucion(dist);
						}
			        }
				}
	           else{
	           
	           
		           Distribucion distribucion = new Distribucion();
		           distribucion = distribucionModel.getDistribucionXCodBar(obj[1].toString());
		           DistribucionVisita distribucionVisita= distribucionModel.getDistribucionVisitaXCodBar(obj[1].toString());
		           distribucionModel.delete(distribucionVisita);
		           distribucionVisita= distribucionModel.getDistribucionVisitaXCodBar(obj[1].toString());
		           if(distribucionVisita==null){
						System.out.println("no tiene visitas");
						distribucion.setIndSit("1");
						distribucion.setDesSit("Pendiente");
						distribucion.setCodMot("");
						distribucion.setDesMot("");
						distribucion.setFecUltimaVisita(null);
						distribucion.setResUltimaVisita(null);
						distribucion.setTipoEntrega("");
						distribucionModel.actualizarDistribucion(distribucion);
					}else{
						System.out.println("DESSIT: "+distribucionVisita.getDesSit());
						distribucion.setIndSit(distribucionVisita.getIndSit());
						distribucion.setDesSit(distribucionVisita.getDesSit());
						distribucion.setCodMot(distribucionVisita.getCodMot());
						distribucion.setDesMot(distribucionVisita.getDesMot());
						distribucionModel.actualizarDistribucion(distribucion);
					}
	           }
	           
	        }
			//////////////////////////////////////////////////////
	        
			hojaRutaDetalleModel.deleteDetalleXIdRuta(Integer.parseInt(id_rut));
			hojaRutaModel.delete(hojRut);	
			convertedDate = dateFormat.parse(fec);
			List<Object> hojaRuta =hojaRutaModel.getHojaRutaXFecIdMen(convertedDate,Integer.parseInt(idMen));
			System.out.println("LISTA RUTAS:"+hojaRuta.size());
			tablita=getHTMLTablaRutas(hojaRuta);
			outPut.put("tablita", tablita);
			if(hojaRuta.size()>0){
				
		       }else{
		    	   outPut.put("id", "");
		       }
			}else{
				outPut.put("mensaje", mensaje);
				//model.addAttribute("error", "true");
				//model.addAttribute("cause", mensaje);
			}
	       
	       response.getWriter().write(outPut.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		//model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.M1.name()));
		//model.addAttribute("menuSelect", Menu.M2.name());
		//return view;
	}
	
	@RequestMapping(value={"/nueva_ruta.htm"})
	public String nuevaRuta(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req){
		String view="hojaruta/nueva_hojaruta";
		System.out.println("ENTRO nueva ruta");
		String fec=req.getParameter("fecha");
		String idMen=req.getParameter("choiceMensajero");
		Integer ruta=0;
		System.out.println("FECHA MENSAJERO: "+fec+" - "+idMen);
		try {
			user_load(model);

			ruta=hojaRutaModel.nroHojaMax()+1;

	            //ruta=Integer.parseInt(obj[0].toString());
	        System.out.println("MAXIMO: "+ruta);


			model.addAttribute("ListaMensajeros", mensajeroModel.findAll());
			model.addAttribute("fecha", fec);
			model.addAttribute("ruta", ruta.toString());
			model.addAttribute("choiceMensajero", idMen);
			model.addAttribute("ListaZonas", tipoZonaModel.findAll());
			model.addAttribute("botonGenerar", "true");
			System.out.println("TAMA�O: "+hojaRutaModel.findAll().size());
			
			if(fec.equals("")){
				view="hojaruta/creacion_hojaruta";
				model.addAttribute("error", "true");
				model.addAttribute("cause", "Ingrese fecha");
			}
			
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.N1.name()));
		model.addAttribute("menuSelect", Menu.N3.name());
		return view;
	}
	
	@RequestMapping(value={"/generar_ruta.htm"})
	public String generarRuta(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req){
		String view="hojaruta/nueva_hojaruta";
		System.out.println("ENTRO nueva ruta");
		String fec=req.getParameter("fecha");
		String idMen=req.getParameter("choiceMensajero");
		String ruta=req.getParameter("ruta");
		String zona=req.getParameter("choiceZona");
		Date convertedDate = new Date();
		Date hoy= new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			
			String cod_bar_ruta=generarCodBar(Integer.parseInt(ruta));
			HojaRuta hojaRuta = new HojaRuta();
			convertedDate = dateFormat.parse(fec);
			hojaRuta.setEstCarga(0);
			hojaRuta.setEstado(1);
			hojaRuta.setFecha(convertedDate);
			hojaRuta.setIdMensajero(Integer.parseInt(idMen));
			hojaRuta.setRuta(ruta);
			hojaRuta.setPiezas(0);
			hojaRuta.setNroHoja(Integer.parseInt(ruta));
			hojaRuta.setCodBarRuta(cod_bar_ruta);
			hojaRuta.setSituacion("");
			hojaRuta.setZona(zona);
			hojaRuta.setUsuario(usuarioInfo.getApellidos()+" "+usuarioInfo.getNombres());
			hojaRuta.setFechaProceso(hoy);
			hojaRutaModel.create(hojaRuta);
			
			System.out.println("ID RUTA"+hojaRuta.getIdRuta());

			model.addAttribute("idRuta", hojaRuta.getIdRuta());
			model.addAttribute("ruta", ruta);
			model.addAttribute("ListaMensajeros", mensajeroModel.findAll());
			model.addAttribute("ListaZonas", tipoZonaModel.findAll());
			model.addAttribute("fecha", fec);
			model.addAttribute("choiceMensajero", idMen);
			model.addAttribute("choiceZona", zona);
			model.addAttribute("generar", "true");
			model.addAttribute("t_id", hojaRuta.getIdRuta());
			model.addAttribute("t_fecha", fec);
			model.addAttribute("t_ruta", ruta);
			model.addAttribute("t_mensajero", idMen);
			model.addAttribute("t_piezas", 0);
			model.addAttribute("t_estado", 1);
			System.out.println("t_estado: "+fec);
			
			model.addAttribute("botonNueva", "true");
			
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.N1.name()));
		model.addAttribute("menuSelect", Menu.N3.name());
		return view;
	}
	
	@RequestMapping(value={"/listar_ruta_cod.htm"})
	public void listaRutaCod(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req, HttpServletResponse response,
			@RequestParam("idRuta") String idRuta,
			@RequestParam("codigoB") String codigo,
			@RequestParam("fecha") String fecha,
			@RequestParam("hoja") String hoja,
			@RequestParam("mensajero") String mensajero,
			@RequestParam("zona") String zona
			){
		response.setContentType("application/json");
        JSONObject outPut = new JSONObject(); 
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String mensaje="";
        String tablita="";
        String codBar_Rendicion="";
        Date convertedDate = new Date();
        System.out.println("ZONA: "+zona);
		try{  
			
			String direc_rendicion="";
			user_load(model);
			convertedDate = dateFormat.parse(fecha);
			HojaRuta hojaRuta=hojaRutaModel.getHojaRutaXNroHoja(Integer.parseInt(hoja));
			List<Rendicion> listRendicion = hojaRutaModel.existeHojaRutaXCodBarRuta(codigo);
			if(listRendicion.size()>0){
				for(Rendicion r : listRendicion){
					//ClienteAgencia cliagen=hojaRutaModel.getClienteAgencia(r.getCodAgencia());
					direc_rendicion=r.getCodAgencia();
					codBar_Rendicion=r.getCodBarRendicion();
		        	System.out.println("AGENCIA: "+direc_rendicion+" "+codBar_Rendicion);
		        }
			}
	
			if(idRuta.equals("")){
				mensaje="Genere una hoja de ruta";
			}else if(codigo.equals("")){
				mensaje="Ingrese codigo de barras";
			}else if((hojaRutaDetalleModel.existeDistribucionXCodBar(codigo)).size()==0 && listRendicion.size()==0){
				mensaje="C�digo no registrado en la base de datos";
			}else if((hojaRutaDetalleModel.existeRendicionXCodBar(codigo)).size()>0){
				mensaje="C�digo de barra en una rendici�n";
			}else if((hojaRutaDetalleModel.getConGestion(codigo)).size()>0){
				mensaje="C�digo de barra en hoja de ruta";
			}else if((hojaRutaDetalleModel.getDistribucionXCodBarCierre(codigo)).size()>0){
				mensaje="C�digo de barra ya entregada o cierre de distribucion";
			}else if(hojaRutaDetalleModel.getHojaRutaXCodBarIdRuta(codigo,Integer.parseInt(idRuta)).size()>0){
				mensaje="C�digo de barra ya en esta hoja de ruta";
			}
			//else if((hojaRutaDetalleModel.getHojaRutaXCodBarFec(codigo,convertedDate)).size()>0 || hojaRutaDetalleModel.getHojaRutaRendicionXCodBarFec(codigo,convertedDate).size()>0){
			//	mensaje="C�digo de barra ya asignada";
			//}
			
			if(mensaje.equals("")){
				///////////////////
				HojaRutaDetalle hojaRutaDetalle = new HojaRutaDetalle();
				String domicilio;
				Distribucion distribucion = new Distribucion();
				distribucion=distribucionModel.getByCodBar(codigo,null);
				Integer visitas= distribucionModel.findVisitas(codigo).size();
				System.out.println("NUM VISITAS+: "+visitas);
				if(visitas>=4){
					visitas=0;
				}
				
				if(listRendicion.size()>0){
					hojaRutaDetalle.setDireccion(direc_rendicion);
					hojaRutaDetalle.setOrden(1);
		            hojaRutaDetalle.setVisitas(0);
		            //para registrar visita a los cod_bar de la rendicion
		            List<Object> renDetalle=rendicionDetalleModel.listaRendicionXCodBar(codBar_Rendicion);
					Iterator itr = renDetalle.iterator();
			        while(itr.hasNext()){
			           Object[] obj = (Object[]) itr.next();
			           DistribucionVisita distribucionVisita = new DistribucionVisita();
			           
			            Distribucion dis=new Distribucion();
			            dis= distribucionModel.getByCodBar(obj[1].toString(),null);
			            Integer max=distribucionModel.idImportMax()+1;
			            distribucionVisita.setIdImport(max);
			            distribucionVisita.setCodBar(obj[1].toString());
			            distribucionVisita.setNroHoj(Integer.parseInt(hoja));
			            distribucionVisita.setFecHoj(convertedDate);
			            distribucionVisita.setCodMsj(Integer.parseInt(mensajero));
			            Mensajero men=mensajeroModel.getMensajeroDetail(Integer.parseInt(mensajero));
			            distribucionVisita.setNomMsj((men.getApellidoPat()!=null?men.getApellidoPat():"")+" "+(men.getApellidoMat()!=null?men.getApellidoMat():"")+" "+(men.getNombres()!=null?men.getNombres():""));
			            distribucionVisita.setIndSit("");
			            distribucionVisita.setDesSit("");
			            distribucionVisita.setCodMot("");
			            distribucionVisita.setDesMot("");
			            if(zona.equals("501")){
			            	distribucionVisita.setDesMot("DESPACHADO A PROVINCIA");
				        	distribucionVisita.setCodMot("45");
				        	distribucionVisita.setIndSit("3");
				            distribucionVisita.setDesSit("IMPOSIBLE");
				            distribucionVisita.setResultadoVisita("DESPACHADO A PROVINCIA");
				            distribucionVisita.setFecCre(new Date());
				            distribucionVisita.setFromUi("1");
				            distribucionVisita.setDescargado("1");
				        }
			            distribucionVisita.setCodUsu(usuarioInfo.getCodigo());
			            distribucionVisita.setNomUsu(usuarioInfo.getNombres()+" "+usuarioInfo.getApellidos());
			            distribucionModel.guardar(distribucionVisita);
			            System.out.println("SE CREO DISTRIBUCION VISITA");
			            if(zona.equals("501")){
				        	dis.setDesMot("DESPACHADO A PROVINCIA");
					        dis.setCodMot("45");
				        }else{
				        	if(hojaRuta.getEstado()==2){
				        		dis.setDesMot("EN HOJA DE RUTA");
				        		dis.setCodMot("52");
				        	}else{
				        		dis.setDesMot("En Ruta");
				        		dis.setCodMot("66");
				        	}
				        }
			            dis.setFecUltima(convertedDate);
				        distribucionModel.actualizarDistribucion(dis); 
			            
			        }
		            ///////////////////////////////////////////////////////////////////
				}else{
					List<Object> listaCoordinaciones=distribucionModel.coordinacionesXCodBarFecha(codigo,convertedDate);
					Iterator itr1 = listaCoordinaciones.iterator();
					String men_coord="";
			        while(itr1.hasNext()){
			        	Object[] obj = (Object[]) itr1.next();   
			        	String agencia=obj[2]!=null?obj[2].toString().trim():"";
				        if(agencia.equals("")){
			        		men_coord="C�digo de barra coordinada para la fecha: "+obj[0];
			        	}else{
			        		men_coord="C�digo de barra coordinada para Agencia para la fecha: "+obj[0];
			        	}
			        }
					List<Object> listaDirecciones=configDirecHojaRutaModel.getDomicilioxConfig(codigo,visitas+1);
					Iterator itr = listaDirecciones.iterator();
			        while(itr.hasNext()){
			        	Object[] obj = (Object[]) itr.next();           
			            System.out.println("ORDEN: "+obj[4]);
			            System.out.println("DIRECCION: "+obj[0]);
			            System.out.println("DIRECCION DOM: "+obj[1]);
			            System.out.println("DIRECCION LAB: "+obj[2]);
			            System.out.println("DIRECCION OPC: "+obj[3]);
			            
			            if(obj[1]==null){
			            	obj[1]="";
			            }
			            if(obj[2]==null){
			            	obj[2]="";
			            }
			            if(obj[3]==null){
			            	obj[3]="";
			            }
			            
			            
			            if(Integer.parseInt(obj[0].toString())==1){
			            	hojaRutaDetalle.setDireccion(obj[1].toString());
						} else if(Integer.parseInt(obj[0].toString())==2){
							hojaRutaDetalle.setDireccion(obj[2].toString());
						} else if(Integer.parseInt(obj[0].toString())==3){
							hojaRutaDetalle.setDireccion(obj[3].toString());
						} else{
							hojaRutaDetalle.setDireccion(obj[1].toString());
						}
			            hojaRutaDetalle.setOrden(Integer.parseInt(obj[4].toString()));
			            hojaRutaDetalle.setVisitas(visitas);
			        }
			        
			        //////distribucion_visita//////////
			        if(zona.equals("501")){
			        	distribucion.setDesMot("DESPACHADO A PROVINCIA");
				        distribucion.setCodMot("45");
			        }else{
			        	if(hojaRuta.getEstado()==2){
			        		distribucion.setDesMot("EN HOJA DE RUTA");
					        distribucion.setCodMot("52");
			        	}else{
			        		distribucion.setDesMot("En Ruta");
					        distribucion.setCodMot("66");
			        	}
			        }
			        distribucion.setFecUltima(convertedDate);
			        distribucionModel.actualizarDistribucion(distribucion); 
					
					DistribucionVisita distribucionVisita = new DistribucionVisita();
		            Integer max=distribucionModel.idImportMax()+1;
		            distribucionVisita.setIdImport(max);
		            distribucionVisita.setCodBar(codigo);
		            distribucionVisita.setNroHoj(Integer.parseInt(hoja));
		            distribucionVisita.setFecHoj(convertedDate);
		            distribucionVisita.setCodMsj(Integer.parseInt(mensajero));
		            Mensajero men=mensajeroModel.getMensajeroDetail(Integer.parseInt(mensajero));
		            distribucionVisita.setNomMsj((men.getApellidoPat()!=null?men.getApellidoPat():"")+" "+(men.getApellidoMat()!=null?men.getApellidoMat():"")+" "+(men.getNombres()!=null?men.getNombres():""));		            distribucionVisita.setIndSit("");
		            distribucionVisita.setDesSit("");
		            distribucionVisita.setCodMot("");
		            distribucionVisita.setDesMot("");
		            if(zona.equals("501")){
		            	distribucionVisita.setDesMot("DESPACHADO A PROVINCIA");
			        	distribucionVisita.setCodMot("45");
			        	distribucionVisita.setIndSit("3");
			            distribucionVisita.setDesSit("IMPOSIBLE");
			            distribucionVisita.setResultadoVisita("DESPACHADO A PROVINCIA");
			            distribucionVisita.setFecCre(new Date());
			            distribucionVisita.setFromUi("1");
			            distribucionVisita.setDescargado("1");
			        }
		            distribucionVisita.setCodUsu(usuarioInfo.getCodigo());
		            distribucionVisita.setNomUsu(usuarioInfo.getNombres()+" "+usuarioInfo.getApellidos());
		            distribucionModel.guardar(distribucionVisita);
		            System.out.println("SE CREO DISTRIBUCION VISITA");
					/////////////////////
		            //mensaje si tiene coordinaciones
		            outPut.put("mensaje_coor", men_coord);
				}
				convertedDate = dateFormat.parse(fecha);
				hojaRutaDetalle.setFecha(convertedDate);
				hojaRutaDetalle.setCodBar(codigo);
				hojaRutaDetalle.setIdRuta(Integer.parseInt(idRuta));
				if(hojaRuta.getEstado()==2){
					hojaRutaDetalle.setEstado("2");
				}else{
					hojaRutaDetalle.setEstado("1");
				}
				hojaRutaDetalle.setCarga(0);
				hojaRutaDetalleModel.create(hojaRutaDetalle);
				
				hojaRutaModel.updatePiezas(Integer.parseInt(idRuta), hojaRutaDetalleModel.cantidadPiezas(Integer.parseInt(idRuta)).size());
				
				tablita=getHTMLTablaRutasCod(hojaRutaDetalleModel.getHojaRutaXID(Integer.parseInt(idRuta)),1);
				outPut.put("tablita", tablita);
		     
			}else{

			}
			outPut.put("mensaje", mensaje);
	       response.getWriter().write(outPut.toString());
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	@RequestMapping(value={"/listar_ruta_codF.htm"})
	public void listaRutaCodF(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req, HttpServletResponse response,
			@RequestParam("idRuta") String idRuta,
			@RequestParam("codigoB") String codigo,
			@RequestParam("fecha") String fecha,
			@RequestParam("hoja") String hoja,
			@RequestParam("mensajero") String mensajero,
			@RequestParam("zona") String zona,
			@RequestParam("idmensajero") String idmensajero
			){
		response.setContentType("application/json");
        JSONObject outPut = new JSONObject(); 
        fecha = fecha.substring(8)+'/'+fecha.substring(5,7)+'/'+fecha.substring(0,4);
        System.out.println("ruta: "+idRuta+" fecha: "+fecha);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String mensaje="";
        String tablita="";
        Date convertedDate = new Date();
        String codBar_Rendicion="";
		try{  
			String direc_rendicion="";
			user_load(model);
			convertedDate = dateFormat.parse(fecha);
			
			HojaRuta hojaRuta=hojaRutaModel.getHojaRutaXNroHoja(Integer.parseInt(hoja));
			
			List<Rendicion> listRendicion = hojaRutaModel.existeHojaRutaXCodBarRuta(codigo);
			if(listRendicion.size()>0){
				for(Rendicion r : listRendicion){
					direc_rendicion=r.getCodAgencia();
					codBar_Rendicion=r.getCodBarRendicion();
		        	System.out.println("AGENCIA: "+direc_rendicion+" "+codBar_Rendicion);
		        }
			}
	
			if(idRuta.equals("")){
				mensaje="Genere una hoja de ruta";
			}else if(codigo.equals("")){
				mensaje="Ingrese codigo de barras";
			}else if((hojaRutaDetalleModel.existeDistribucionXCodBar(codigo)).size()==0 && listRendicion.size()==0){
				mensaje="C�digo no registrado en la base de datos";
			}else if((hojaRutaDetalleModel.existeRendicionXCodBar(codigo)).size()>0){
				mensaje="C�digo de barra en una rendici�n";
			}else if((hojaRutaDetalleModel.getConGestion(codigo)).size()>0){
				mensaje="C�digo de barra en hoja de ruta";
			}else if((hojaRutaDetalleModel.getDistribucionXCodBarCierre(codigo)).size()>0){
				mensaje="C�digo de barra ya entregada o cierre de distribucion";
			}else if(hojaRutaDetalleModel.getHojaRutaXCodBarIdRuta(codigo,Integer.parseInt(idRuta)).size()>0){
				mensaje="C�digo de barra ya en esta hoja de ruta";
			}
			//else if((hojaRutaDetalleModel.getHojaRutaXCodBarFec(codigo,convertedDate)).size()>0){
			//	mensaje="C�digo de barra ya asignada";
			//}
			
			if(mensaje.equals("")){
				///////////////////
				HojaRutaDetalle hojaRutaDetalle = new HojaRutaDetalle();
				String domicilio;
				Distribucion distribucion = new Distribucion();
				distribucion=distribucionModel.getByCodBar(codigo,null);
				Integer visitas= distribucionModel.findVisitas(codigo).size();
				System.out.println("NUM VISITAS+: "+visitas);
				if(visitas>=4){
					visitas=0;
				}
				
				if(listRendicion.size()>0){
					hojaRutaDetalle.setDireccion(direc_rendicion);
					hojaRutaDetalle.setOrden(1);
		            hojaRutaDetalle.setVisitas(0);
		            //para registrar visita a los cod_bar de la rendicion
		            List<Object> renDetalle=rendicionDetalleModel.listaRendicionXCodBar(codBar_Rendicion);
					Iterator itr = renDetalle.iterator();
			        while(itr.hasNext()){
			           Object[] obj = (Object[]) itr.next();
			           DistribucionVisita distribucionVisita = new DistribucionVisita();
			           
			            Distribucion dis=new Distribucion();
			            dis= distribucionModel.getByCodBar(obj[1].toString(),null);
			            Integer max=distribucionModel.idImportMax()+1;
			            distribucionVisita.setIdImport(max);
			            distribucionVisita.setCodBar(obj[1].toString());
			            distribucionVisita.setNroHoj(Integer.parseInt(hoja));
			            distribucionVisita.setFecHoj(convertedDate);
			            distribucionVisita.setCodMsj(Integer.parseInt(idmensajero));
			            Mensajero men=mensajeroModel.getMensajeroDetail(Integer.parseInt(idmensajero));
			            distribucionVisita.setNomMsj((men.getApellidoPat()!=null?men.getApellidoPat():"")+" "+(men.getApellidoMat()!=null?men.getApellidoMat():"")+" "+(men.getNombres()!=null?men.getNombres():""));
			            distribucionVisita.setIndSit("");
			            distribucionVisita.setDesSit("");
			            distribucionVisita.setCodMot("");
			            distribucionVisita.setDesMot("");
			            if(zona.equals("501")){
			            	distribucionVisita.setDesMot("DESPACHADO A PROVINCIA");
				        	distribucionVisita.setCodMot("45");
				        	distribucionVisita.setIndSit("3");
				            distribucionVisita.setDesSit("IMPOSIBLE");
				            distribucionVisita.setResultadoVisita("DESPACHADO A PROVINCIA");
				            distribucionVisita.setFecCre(new Date());
				            distribucionVisita.setFromUi("1");
				            distribucionVisita.setDescargado("1");
				        }
			            distribucionVisita.setCodUsu(usuarioInfo.getCodigo());
			            distribucionVisita.setNomUsu(usuarioInfo.getNombres()+" "+usuarioInfo.getApellidos());
			            distribucionModel.guardar(distribucionVisita);
			            System.out.println("SE CREO DISTRIBUCION VISITA");
			            if(zona.equals("501")){
				        	dis.setDesMot("DESPACHADO A PROVINCIA");
					        dis.setCodMot("45");
				        }else{
				        	if(hojaRuta.getEstado()==2){
				        		dis.setDesMot("EN HOJA DE RUTA");
				        		dis.setCodMot("52");
				        	}else{
				        		dis.setDesMot("En Ruta");
				        		dis.setCodMot("66");
				        	}
				        }
			            dis.setFecUltima(convertedDate);
				        distribucionModel.actualizarDistribucion(dis); 
			            
			        }
		            ///////////////////////////////////////////////////////////////////
				}else{
					List<Object> listaCoordinaciones=distribucionModel.coordinacionesXCodBarFecha(codigo,convertedDate);
					Iterator itr1 = listaCoordinaciones.iterator();
					String men_coord="";
					
			        while(itr1.hasNext()){
			        	Object[] obj = (Object[]) itr1.next();   
			        	String agencia=obj[2]!=null?obj[2].toString().trim():"";
				        if(agencia.equals("")){
			        		men_coord="C�digo de barra coordinada para la fecha: "+obj[0];
			        	}else{
			        		men_coord="C�digo de barra coordinada para Agencia para la fecha: "+obj[0];
			        	}
			        }
					List<Object> listaDirecciones=configDirecHojaRutaModel.getDomicilioxConfig(codigo,visitas+1);
					Iterator itr = listaDirecciones.iterator();
			        while(itr.hasNext()){
			        	Object[] obj = (Object[]) itr.next();           
			            System.out.println("ORDEN: "+obj[4]);
			            System.out.println("DIRECCION: "+obj[0]);
			            System.out.println("DIRECCION DOM: "+obj[1]);
			            System.out.println("DIRECCION LAB: "+obj[2]);
			            System.out.println("DIRECCION OPC: "+obj[3]);
			            if(obj[1]==null){
			            	obj[1]="";
			            }
			            if(obj[2]==null){
			            	obj[2]="";
			            }
			            if(obj[3]==null){
			            	obj[3]="";
			            }
			            
			            if(Integer.parseInt(obj[0].toString())==1){
			            	hojaRutaDetalle.setDireccion(obj[1].toString());
						} else if(Integer.parseInt(obj[0].toString())==2){
							hojaRutaDetalle.setDireccion(obj[2].toString());
						} else if(Integer.parseInt(obj[0].toString())==3){
							hojaRutaDetalle.setDireccion(obj[3].toString());
						} else{
							hojaRutaDetalle.setDireccion(obj[1].toString());
						}
			            hojaRutaDetalle.setOrden(Integer.parseInt(obj[4].toString()));
			            hojaRutaDetalle.setVisitas(visitas);
			        }
			        
			        //////distribucion_visita//////////
			        if(zona.equals("501")){
			        	distribucion.setDesMot("DESPACHADO A PROVINCIA");
				        distribucion.setCodMot("45");
				        
			        }else{
			        	if(hojaRuta.getEstado()==2){
			        		distribucion.setDesMot("EN HOJA DE RUTA");
					        distribucion.setCodMot("52");
			        	}else{
			        		distribucion.setDesMot("En Ruta");
					        distribucion.setCodMot("66");
			        	}
			        }
			        distribucion.setFecUltima(convertedDate);
			        distribucionModel.actualizarDistribucion(distribucion); 
					
					DistribucionVisita distribucionVisita = new DistribucionVisita();
		            Integer max=distribucionModel.idImportMax()+1;
		            distribucionVisita.setIdImport(max);
		            distribucionVisita.setCodBar(codigo);
		            distribucionVisita.setNroHoj(Integer.parseInt(hoja));
		            distribucionVisita.setFecHoj(convertedDate);
		            distribucionVisita.setCodMsj(Integer.parseInt(idmensajero));
		            Mensajero men=mensajeroModel.getMensajeroDetail(Integer.parseInt(idmensajero));
		            System.out.println("ID MENSAJEROOOO: "+idmensajero);
		            distribucionVisita.setNomMsj((men.getApellidoPat()!=null?men.getApellidoPat():"")+" "+(men.getApellidoMat()!=null?men.getApellidoMat():"")+" "+(men.getNombres()!=null?men.getNombres():""));
		            distribucionVisita.setIndSit("");
		            distribucionVisita.setDesSit("");
		            distribucionVisita.setCodMot("");
		            distribucionVisita.setDesMot("");
		            if(zona.equals("501")){
		            	distribucionVisita.setDesMot("DESPACHADO A PROVINCIA");
			        	distribucionVisita.setCodMot("45");
			        	distribucionVisita.setIndSit("3");
			            distribucionVisita.setDesSit("IMPOSIBLE");
			            distribucionVisita.setResultadoVisita("DESPACHADO A PROVINCIA");
			            distribucionVisita.setFecCre(new Date());
			            distribucionVisita.setFromUi("1");
			            distribucionVisita.setDescargado("1");
			        }
		            distribucionVisita.setCodUsu(usuarioInfo.getCodigo());
		            distribucionVisita.setNomUsu(usuarioInfo.getNombres()+" "+usuarioInfo.getApellidos());
		            distribucionModel.guardar(distribucionVisita);
		            System.out.println("SE CREO DISTRIBUCION VISITA");
					/////////////////////
		            //para saber si tiene coordinacion
		            outPut.put("mensaje_coor", men_coord);
		            
				}
				convertedDate = dateFormat.parse(fecha);
				hojaRutaDetalle.setFecha(convertedDate);
				hojaRutaDetalle.setCodBar(codigo);
				hojaRutaDetalle.setIdRuta(Integer.parseInt(idRuta));
				if(hojaRuta.getEstado()==2){
					hojaRutaDetalle.setEstado("2");
				}else{
					hojaRutaDetalle.setEstado("1");
				}
				hojaRutaDetalle.setCarga(0);
				hojaRutaDetalleModel.create(hojaRutaDetalle);
				
				hojaRutaModel.updatePiezas(Integer.parseInt(idRuta), hojaRutaDetalleModel.cantidadPiezas(Integer.parseInt(idRuta)).size());
				List<Object> listRut=hojaRutaDetalleModel.getHojaRutaXID(Integer.parseInt(idRuta));
				tablita=getHTMLTablaRutasCod(listRut,1);
				outPut.put("tablita", tablita);
				outPut.put("piezas", listRut.size());
			}else{

			}
			outPut.put("mensaje", mensaje);
	       response.getWriter().write(outPut.toString());
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	@RequestMapping(value={"/cerrar_ruta_cod.htm"})
	public void cerrarRutaCod(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req, HttpServletResponse response,
			@RequestParam("idRuta") String idRuta,
			@RequestParam("nroHoja") String nroHoja
			){
		response.setContentType("application/json");
        JSONObject outPut = new JSONObject(); 
        System.out.println("ruta a cerrar: "+idRuta);
        String mensaje="";
        String tablita="";
		try{  
			user_load(model);
		
			if(mensaje.equals("")){
				
				HojaRuta hojRuta=hojaRutaModel.getHojaRutaXNroHoja(Integer.parseInt(nroHoja));
				
				hojaRutaDetalleModel.updateXID(Integer.parseInt(idRuta));
				hojaRutaDetalleModel.updateDetalleXID(Integer.parseInt(idRuta));
				//tablita=getHTMLTablaRutasCod(hojaRutaDetalleModel.getHojaRutaXID(Integer.parseInt(idRuta)),1);
				
				//obtener la zona
				String zonaRuta = hojaRutaModel.getZonaXIdRuta(Integer.parseInt(idRuta));
				
				List<Object> listaCodBar=hojaRutaDetalleModel.getHojaRutaXID(Integer.parseInt(idRuta));
				System.out.println("TAMA�O lista completya: "+listaCodBar.size());
				Iterator itr = listaCodBar.iterator();
		        while(itr.hasNext()){
		           Object[] obj = (Object[]) itr.next();  
		           System.out.println("TAMA�O: "+hojaRutaModel.existeHojaRutaXCodBarRuta(obj[1].toString()).size());
		           if(hojaRutaModel.existeHojaRutaXCodBarRuta(obj[1].toString()).size()>0){
		        	   Rendicion rendicion = rendicionModel.getRendicionXCodBarRendicion(obj[1].toString());
		        	   rendicion.setCodSituacion("1");
		        	   rendicion.setSituacion("PENDIENTE");
		        	   rendicion.setCodMotivo("52");
		        	   rendicion.setMotivo("EN HOJA DE RUTA");
		        	   if(zonaRuta.equals("501")){
		        		   rendicion.setCodSituacion("3");
			        	   rendicion.setSituacion("IMPOSIBLE");
			        	   rendicion.setCodMotivo("45");
			        	   rendicion.setMotivo("DESPACHADO A PROVINCIA");
		        	   }
		        	   rendicionModel.update(rendicion);
		        	   System.out.println("Se actualizo rendicion");
		        	   	
		        	   List<Object> listaBarRendicion = rendicionDetalleModel.listaRendicionXCodBar(obj[1].toString());
		        	   System.out.println("tama�o de los cod de rendicion: "+listaBarRendicion.size());
		        	   Iterator itr1 = listaBarRendicion.iterator();
				        while(itr1.hasNext()){
				           Object[] obj1 = (Object[]) itr1.next();
				           Distribucion distribucion1 = new Distribucion();
				           distribucion1 = distribucionModel.getDistribucionXCodBar(obj1[1].toString());
				           distribucion1.setIndSit("1");
				           distribucion1.setDesSit("PENDIENTE");
				           distribucion1.setCodMot("52");
				           distribucion1.setDesMot("EN HOJA DE RUTA");
				           distribucion1.setIndEst("2");
				           distribucion1.setDesEst("EN HOJA DE RUTA");
				           if(zonaRuta.equals("501")){
				        	   distribucion1.setIndSit("3");
					           distribucion1.setDesSit("IMPOSIBLE");
				        	   distribucion1.setDesMot("DESPACHADO A PROVINCIA");
				        	   distribucion1.setCodMot("45");
				        	   //distribucion1.setFecUltima(hojRuta.getFecha());
					           distribucion1.setFecUltimaVisita(hojRuta.getFecha());
					           distribucion1.setResUltimaVisita("DESPACHADO A PROVINCIA");
					           distribucion1.setTipoEntrega("Cliente");
					           hojaRutaDetalleModel.updateDetalleCargaXID(Integer.parseInt(idRuta), obj1[1].toString());
					        }
				           distribucion1.setNroHoj(Integer.parseInt(nroHoja));
				           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				           Date convertedDate = new Date();
				   		   convertedDate = dateFormat.parse(obj[9].toString());
				           distribucion1.setFecHoj(convertedDate);
				           distribucion1.setFecUltima(convertedDate);
				           distribucionModel.actualizarDistribucion(distribucion1);
				           
				           System.out.println("SE ACTUALIZO " +obj1[1].toString()+" DISTRIBUCION rendidicon");
				        }
		           }else{
			           System.out.println("CODIGO DE BARRA "+obj[1].toString());
			           Distribucion distribucion = new Distribucion();
			           distribucion = distribucionModel.getDistribucionXCodBar(obj[1].toString());
			           distribucion.setIndSit("1");
			           distribucion.setDesSit("PENDIENTE");
			           distribucion.setCodMot("52");
			           distribucion.setDesMot("EN HOJA DE RUTA");
			           distribucion.setIndEst("2");
			           distribucion.setDesEst("EN HOJA DE RUTA");
			           if(zonaRuta.equals("501")){
			        	   distribucion.setIndSit("3");
				           distribucion.setDesSit("IMPOSIBLE");
			        	   distribucion.setDesMot("DESPACHADO A PROVINCIA");
			        	   distribucion.setCodMot("45");
			        	   //distribucion.setFecUltima(hojRuta.getFecha());
				           distribucion.setFecUltimaVisita(hojRuta.getFecha());
				           distribucion.setResUltimaVisita("DESPACHADO A PROVINCIA");
				           distribucion.setTipoEntrega("Cliente");
				           hojaRutaDetalleModel.updateDetalleCargaXID(Integer.parseInt(idRuta), obj[1].toString());
				        }
			           distribucion.setNroHoj(Integer.parseInt(nroHoja));
			           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			           Date convertedDate = new Date();
			   		   convertedDate = dateFormat.parse(obj[9].toString());
			           distribucion.setFecHoj(convertedDate);
			           distribucion.setFecUltima(convertedDate);
			           distribucionModel.actualizarDistribucion(distribucion);
			           
			           System.out.println("SE ACTUALIZO " +obj[1].toString()+" DISTRIBUCION");
		           }
		        }
		        tablita=getHTMLTablaRutasCod(hojaRutaDetalleModel.getHojaRutaXID(Integer.parseInt(idRuta)),1);
				outPut.put("tablita", tablita);
		     
			}else{
				//model.addAttribute("error", "true");
				//model.addAttribute("cause", mensaje);
			}
			outPut.put("mensaje", mensaje);
	       response.getWriter().write(outPut.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		//model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.M1.name()));
		//model.addAttribute("menuSelect", Menu.M2.name());
		//return view;
	}
	
	@RequestMapping(value={"/eliminar_ruta_cod.htm"})//cambia de estados a rendiciones y hoja de ruta
	public void eliminarRutaCod(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req, HttpServletResponse response,
			@RequestParam("id_rut") String id_rut,
			@RequestParam("idRuta") String idRuta
			){
		response.setContentType("application/json");
        JSONObject outPut = new JSONObject(); 
        System.out.println("ruta: "+id_rut);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String mensaje="";
        String tablita="";
        Date convertedDate = new Date();
		try{  
			user_load(model);
			///
			Rendicion rendicion=new Rendicion();
			RendicionDetalle rendicionDetalle=new RendicionDetalle();//si se elimina una rendicion
			///
			HojaRutaDetalle hojaRutaDetalle = new HojaRutaDetalle();
			///
			rendicion=rendicionDetalleModel.getDetalleXIdDetalleHoja(Integer.parseInt(id_rut));
			hojaRutaDetalle=hojaRutaDetalleModel.getDetalleXIdDetalle(Integer.parseInt(id_rut));
			if(rendicion!=null){
				System.out.println("CODIGO BARRA RENDICION: "+rendicion.getCodBarRendicion()+" "+rendicion.getId());
				List<Object> renDetalle=rendicionDetalleModel.listaDetalleXIdRendicion(rendicion.getId());
				Iterator itr = renDetalle.iterator();
		        while(itr.hasNext()){
		           Object[] obj = (Object[]) itr.next();
		           DistribucionVisita distribucionVisita= distribucionModel.getDistribucionVisitaXCodBar(obj[1].toString());
		           distribucionModel.delete(distribucionVisita);
		           distribucionVisita= distribucionModel.getDistribucionVisitaXCodBar(obj[1].toString());
		           Distribucion dist=new Distribucion();
		           dist = distribucionModel.getDistribucionXCodBar(obj[1].toString());
		           if(distribucionVisita==null){
						System.out.println("no tiene visitas");
						dist.setIndSit("1");
						dist.setDesSit("Pendiente");
						dist.setCodMot("");
						dist.setDesMot("");
						dist.setFecUltimaVisita(null);
				        dist.setResUltimaVisita(null);
				        dist.setTipoEntrega("");
					}else{
						System.out.println("DESSIT: "+distribucionVisita.getDesSit());
						dist.setIndSit(distribucionVisita.getIndSit());
						dist.setDesSit(distribucionVisita.getDesSit());
						dist.setCodMot(distribucionVisita.getCodMot());
						dist.setDesMot(distribucionVisita.getDesMot());
						distribucionModel.actualizarDistribucion(dist);
					}
		        }
			}else{
				//hojaRutaDetalle=hojaRutaDetalleModel.getDetalleXIdDetalle(Integer.parseInt(id_rut));
				Distribucion distribucion = new Distribucion();
				distribucion = distribucionModel.getDistribucionXCodBar(hojaRutaDetalle.getCodBar());
				System.out.println("codbarrrr : "+hojaRutaDetalle.getCodBar());
				DistribucionVisita distribucionVisita= distribucionModel.getDistribucionVisitaXCodBar(hojaRutaDetalle.getCodBar());
				
				distribucionModel.delete(distribucionVisita);
				distribucionVisita= distribucionModel.getDistribucionVisitaXCodBar(hojaRutaDetalle.getCodBar());
				if(distribucionVisita==null){
					//System.out.println("datos: "+distribucionVisita.getCodBar());
					System.out.println("no tiene visitas");
					distribucion.setIndSit("1");
					distribucion.setDesSit("Pendiente");
					distribucion.setCodMot("");
					distribucion.setDesMot("");
					distribucion.setFecUltimaVisita(null);
					distribucion.setResUltimaVisita(null);
					distribucion.setTipoEntrega("");
				}else{
					System.out.println("DESSIT: "+distribucionVisita.getDesSit());
					distribucion.setIndSit(distribucionVisita.getIndSit());
					distribucion.setDesSit(distribucionVisita.getDesSit());
					distribucion.setCodMot(distribucionVisita.getCodMot());
					distribucion.setDesMot(distribucionVisita.getDesMot());
					distribucionModel.actualizarDistribucion(distribucion);
				}
			}
			//Distribucion distribucion = new Distribucion();
			//distribucion = distribucionModel.getDistribucionXCodBar(rendicionDetalle.getCargo());
			///
			//hojaRutaDetalle.setId(Integer.parseInt(id_rut));	
			hojaRutaDetalleModel.delete(hojaRutaDetalle);
			hojaRutaModel.updatePiezas(Integer.parseInt(idRuta), hojaRutaDetalleModel.cantidadPiezas(Integer.parseInt(idRuta)).size());
			System.out.println("Se elimino el detalle");
			List<Object> listRut=hojaRutaDetalleModel.getHojaRutaXID(Integer.parseInt(idRuta));
			tablita=getHTMLTablaRutasCod(listRut,1);
			outPut.put("tablita", tablita);
			outPut.put("piezas", listRut.size());
			outPut.put("mensaje", mensaje);
	       response.getWriter().write(outPut.toString());
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	@RequestMapping(value={"/consulta_ruta.htm"})
	public String consultaRuta(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req){
		String view="hojaruta/consulta_hojaruta";
		System.out.println("ENTRO consulta ruta");
		String id=req.getParameter("id1");
		String t_fecha=req.getParameter("t_fecha");
		String t_ruta=req.getParameter("t_ruta");
		String t_mensajero=req.getParameter("t_mensajero");
		String t_piezas=req.getParameter("t_piezas");
		String t_estado=req.getParameter("t_estado");
		String t_idmensajero=req.getParameter("t_idmensajero");
		String t_zona=req.getParameter("t_zona");
		//String idMen=req.getParameter("choiceMensajero");
		System.out.println("id "+id);
		String tablita="";
		try {
			tablita=getHTMLTablaRutasCod(hojaRutaDetalleModel.getHojaRutaXID(Integer.parseInt(id)),2);
			System.out.println("LISTAAA "+hojaRutaDetalleModel.getHojaRutaXID(Integer.parseInt(id)).size());
			user_load(model);
			model.addAttribute("ListaPiezas", tablita);
			model.addAttribute("t_fecha", t_fecha);
			model.addAttribute("t_ruta", t_ruta);
			model.addAttribute("t_mensajero", t_mensajero);
			model.addAttribute("t_piezas", t_piezas);
			model.addAttribute("t_id", id);
			model.addAttribute("t_estado", t_estado);
			model.addAttribute("t_idmensajero", t_idmensajero);
			model.addAttribute("t_zona", t_zona);
			//para agregar asi este cerrada la hoja de ruta
			//if(Integer.parseInt(t_estado)==1){
				model.addAttribute("generar", "true");
				model.addAttribute("cerrar", "true");
				
			//}
			if(Integer.parseInt(t_estado)==2){
				model.addAttribute("cerrar", "false");
			}
			
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.N1.name()));
		model.addAttribute("menuSelect", Menu.N3.name());
		return view;
	}
	
	@RequestMapping(value={"/pdf_ruta.htm"})
	public String pdfRuta(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req){
		String view="hojaruta/consulta_hojaruta";
		System.out.println("ENTRO pdf ruta");
		String id=req.getParameter("id");
		String t_fecha=req.getParameter("id_fecha");
		String t_ruta=req.getParameter("id_ruta");
		String t_mensajero=req.getParameter("id_mensajero");
		String t_piezas=req.getParameter("id_piezas");
		String t_estado=req.getParameter("id_estado");
		System.out.println("FECHA MENSAJEROID: "+id+" - ");
		String tablita="";
		String cadena="";
		try {
			cadena= ""+System.currentTimeMillis();
			PdfHojaRuta pdf = new PdfHojaRuta(); 
			generarPdfHojaRuta(id,cadena,req);
			tablita=getHTMLTablaRutasCod(hojaRutaDetalleModel.getHojaRutaXID(Integer.parseInt(id)),2);
			System.out.println("LISTAAA "+hojaRutaDetalleModel.getHojaRutaXID(Integer.parseInt(id)).size());
			user_load(model);
			model.addAttribute("ListaPiezas", tablita);
			model.addAttribute("t_fecha", t_fecha);
			model.addAttribute("t_ruta", t_ruta);
			model.addAttribute("t_mensajero", t_mensajero);
			model.addAttribute("t_piezas", t_piezas);
			model.addAttribute("t_id", id);
			model.addAttribute("t_estado", t_estado);
			
			if(Integer.parseInt(t_estado)==1){
				model.addAttribute("generar", "true");
			}
			
		} catch (Exception e) {
			model.addAttribute("error", "true");
			System.out.println(e);
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.N1.name()));
		model.addAttribute("menuSelect", Menu.N3.name());
		return view;
	}
	
	@RequestMapping(value={"/pdf_ruta_nuevo.htm"})
	public void pdfRutaNuevo(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req, HttpServletResponse response,
			@RequestParam("idRuta") String idRuta
			){
		response.setContentType("application/json");
        JSONObject outPut = new JSONObject(); 
        String cadena="";
		try{
			cadena= ""+System.currentTimeMillis();
			
			String rutaa=generarPdfHojaRuta(idRuta,cadena,req);
			//Thread.sleep(2000);
			outPut.put("rutaa", rutaa);
			outPut.put("mensaje", "se cre� pdf");
			outPut.put("cadena", cadena);
	       response.getWriter().write(outPut.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/////////////////////////////////
	
	@RequestMapping(value={"/descargar_hojaruta.htm"})
	public String descargarHojaRuta(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req){
		String view="hojaruta/descargar_hojaruta";
		try {
			
			//user_load(model);
			
			
		} catch (Exception e) {
			model.addAttribute("error", "true");
			log.error(e.getMessage());
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.N1.name()));
		model.addAttribute("menuSelect", Menu.N4.name());
		return view;
	}
	
	@RequestMapping(value={"/download_hojaruta.htm"} )
	public String downloadHojaRuta(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, 
								HttpServletRequest request,  HttpServletResponse response) throws IOException{
		System.out.println("-------descarga_hoja_ruta-------");

		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.N1.name()));
		model.addAttribute("menuSelect", Menu.N4.name());
		String view="hojaruta/descargar_hojaruta";
		try {			
			String fechadel = request.getParameter("fechade");
			String fechaa = request.getParameter("fechaa");
			
			System.out.println("fecha: "+fechadel+" "+fechaa);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        Date convertedDel = new Date();
	        Date convertedA = new Date();
	        convertedDel = dateFormat.parse(fechadel);
	        convertedA = dateFormat.parse(fechaa);
			List<Object> listaGestiones = hojaRutaModel.gestionesBeetrack(fechadel,fechaa);
			System.out.println("tamaa�o: "+listaGestiones.size());
			if(listaGestiones.size() > 0){
				//System.out.println("--"+fecha);
				final javax.servlet.ServletContext servletContext = request.getSession().getServletContext();
				final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				final String temperotyFilePath = tempDirectory.getAbsolutePath();
				String fileName = "beetrack_"+System.currentTimeMillis()+".xls";
				String path = temperotyFilePath+File.separator;
				System.out.println(path);
				
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition","attachment;filename="+fileName);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				
				MUtilReportFiles reportFiles = new MUtilReportFiles();
				reportFiles.createExcelBeetrack(path, fileName, listaGestiones, response.getOutputStream());
				model.addAttribute("archi", "true");
			}
		} catch (Exception e) {
			model.addAttribute("error", "true");
			e.printStackTrace();
		}
		
		System.out.println("Done");
		return view;
	}	
	
	public void user_load(Model model){
		model.addAttribute("RPAdminUserPerfiles", perfilModel.findAll());
		model.addAttribute("RPAdminUserUsuarios", usuarioModel.findAll());
		model.addAttribute("RPAdminUserClientes", distribucionModel.getClientes());
	}
	
	public String generarCodBar(Integer nroHoja){
		String codBar="0";
		if(nroHoja>9999){
			codBar="00404000000"+nroHoja;
		}else if(nroHoja>99999){
			codBar="0040400000"+nroHoja;
		}else if(nroHoja>999999){
			codBar="004040000"+nroHoja;
		}else if(nroHoja>9999999){
			codBar="00404000"+nroHoja;
		}else if(nroHoja>99999999){
			codBar="0040400"+nroHoja;
		}
		return codBar;
	}
	
	public static String getHTMLDinamicOption(String value, String descripcion){
        return  "<option value=\""+value+"\" >"+descripcion+"</option>";
    }
	
	public static String getHTMLTablaRutas(List<Object> listaRutas){
        String outPut = 
                            "<table id=\"table-consulta-2\" >" +
                                "<thead>" +
                                    "<tr>" +
                                        "<td>Fecha</td>" +
                                        "<td>Ruta</td>" +
                                        "<td>Mensajero</td>" +
                                        "<td>Piezas</td>" +
                                        "<td>Situacion</td>" +
                                        "<td>Zona</td>" +
                                    "</tr>" +
                                "</thead>" +
                                "<tbody>";
        Iterator itr = listaRutas.iterator();
        while(itr.hasNext()){
           Object[] obj = (Object[]) itr.next();           
           outPut +=    "<tr class='<c:if test=\"${rowCounter.count % 2 == 0}\">on</c:if>'>" +
                            "<td>"+obj[1]+"</td>" +
                            "<td>"+obj[4]+"</td>" +
                            //"<td>"+obj[9]!=null?obj[9]:""+" "+obj[10]!=null?obj[10]:""+" "+obj[3]+"</td>" +
                            "<td>"+(obj[9]!=null?obj[9]:"")+" "+(obj[10]!=null?obj[10]:"")+" "+obj[3]+"</td>" +
                            "<td>"+obj[5]+"</td>" +
                            "<td>"+obj[6]+"</td>" +
                            "<td>"+obj[7]+"</td>" +
                            "<td style=\"width: 10px\">"+
	        				"<div>"+      
	       					"<form action=\"./consulta_ruta.htm\" method=\"post\">"+      	
		        				"<input readonly=\"readonly\" hidden=\"\" type=\"text\" id=\"id1\" value=\""+obj[0]+"\" name=\"id1\"/>"+
		        				"<input readonly=\"readonly\" hidden=\"\" type=\"text\" id=\"t_fecha\" value=\""+obj[1]+"\" name=\"t_fecha\"/>"+
		        				"<input readonly=\"readonly\" hidden=\"\" type=\"text\" id=\"t_ruta\" value=\""+obj[4]+"\" name=\"t_ruta\"/>"+
		        				"<input readonly=\"readonly\" hidden=\"\" type=\"text\" id=\"t_mensajero\" value=\""+(obj[9]!=null?obj[9]:"")+" "+(obj[10]!=null?obj[10]:"")+" "+(obj[3]!=null?obj[3]:"")+"\" name=\"t_mensajero\"/>"+
		        				"<input readonly=\"readonly\" hidden=\"\" type=\"text\" id=\"t_piezas\" value=\""+obj[5]+"\" name=\"t_piezas\"/>"+
		        				"<input readonly=\"readonly\" hidden=\"\" type=\"text\" id=\"t_estado\" value=\""+obj[8]+"\" name=\"t_estado\"/>"+
		        				"<input readonly=\"readonly\" hidden=\"\" type=\"text\" id=\"t_idmensajero\" value=\""+obj[11]+"\" name=\"t_idmensajero\"/>"+
		        				"<input readonly=\"readonly\" hidden=\"\" type=\"text\" id=\"t_zona\" value=\""+obj[12]+"\" name=\"t_zona\"/>"+
								"<input type=\"submit\" class=\"boton-default\" style=\"min-width: 100px;\" value=\"Ver\" id=\"smt\"/>"+
							"</form>"+	
							"</div>"+
							"</td>";
							if(obj[8].toString().equals("1")){// || obj[8].toString().equals("2")){
				                   outPut +="<td style=\"width: 10px\">"+
					        				"<div>"+      
						        				"<button id=\"btnEliminar\" class=\"boton-default\" onclick=\"btn_eliminarRuta("+obj[0]+");\">Eliminar</button>"+
											"</div>"+
											"</td>";
				                            }
							outPut +="</tr>";          
        }         
                outPut +=       "</tbody>" +
                            "</table>";
         return outPut;
    }
	
	public static String getHTMLTablaRutasCod(List<Object> listaRutas, Integer tipo){
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
	
	public String generarPdfHojaRuta(String id, String cadena,HttpServletRequest req) throws MalformedURLException{
		String ruta="";
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

	        String lista="";
	        Integer total=0;
	        //LISTA  DE CLIENTES DESPACHADO
	        List<Object> listaClientes=hojaRutaDetalleModel.listaClientesXIdRuta(Integer.parseInt(id));
	        Iterator itr = listaClientes.iterator();
	        while(itr.hasNext()){
	           Object[] obj = (Object[]) itr.next();  
	           String clien = obj[1].toString()+"________________________________________________________________________________________";
	           lista+=(clien.substring(0,35)+""+obj[2]+"			______________		______________		______________		______________\n");
	           total+=Integer.parseInt(obj[2].toString());
	        }      
	        System.out.println("DATOS: "+lista+ " TOTAL: "+total);
	        //////////
			System.out.println("entro para generar reporte");
			JasperReport repor;
			String zona=hojaRutaModel.getZonaXIdRuta(Integer.parseInt(id));
			//getClass().getResource("/pe/nasqa/values/PdfRutas/BBVA.jrxml").getPath()

			if(zona.equals("501")){//para provincia
				//repor = JasperCompileManager.compileReport(Constantes.RUTA_JASPER+"HOJA_RUTA_PROVINCIA.jrxml");


				repor = JasperCompileManager.compileReport(req.getServletContext().getRealPath("/WEB-INF/jasper/HOJA_RUTA_PROVINCIA.jrxml"));

			}else{
				//repor = JasperCompileManager.compileReport(Constantes.RUTA_JASPER+"HOJA_RUTA_LIMA.jrxml");
				repor = JasperCompileManager.compileReport(req.getServletContext().getRealPath("/WEB-INF/jasper/HOJA_RUTA_LIMA.jrxml"));
			}
			//System.out.println("RUTA JASPER: "+getClass().getResource("/pe/nasqa/values/PdfRutas/HOJA_RUTA_LIMA.jrxml").getPath());
			Map parametro = new HashMap();;
			//repor=(JasperReport)JRLoader.loadObject(in);
			parametro.put("id_ruta",Integer.parseInt(id));
			parametro.put("lista",lista.trim());
			parametro.put("total",total);
	        String sourceDir = ""; // Pdf files are read from this folder
	        String destinationDir= "imagen\\"; 
	        
	        JasperPrint jPrint = JasperFillManager.fillReport(repor, parametro,conn);
	        jPrint.setProperty("net.sf.jasperreports.expo rt.character.encoding","ISO-8859-1");
	        //JasperExportManager.exportReportToPdfFile(jPrint,Constantes.RUTA_PDF+"hojaruta\\hojaruta"+cadena+".pdf");
	        
	        JasperExportManager.exportReportToPdfFile(jPrint,req.getServletContext().getRealPath("/pdf/hojaruta/hojaruta"+cadena+".pdf"));
	        //JasperExportManager.exportReportToPdfFile(jPrint,req.getSession().getServletContext().getResource("/WEB-INF/pdf/hojaruta/hojaruta"+cadena+".pdf").getPath());
	        ruta=req.getServletContext().getRealPath("/pdf/hojaruta/hojaruta"+cadena+".pdf");
	        System.out.println(req.getServletContext().getRealPath("/pdf/hojaruta/hojaruta"+cadena+".pdf"));
	        System.out.println("creo pdf");
	        File sourceFile = new File(sourceDir);
	        File destinationFile = new File(destinationDir);
	        if (!destinationFile.exists()) {
	            destinationFile.mkdir();
	        }
		}catch(JRException ex){
            System.out.println(ex.toString());
        }
		return ruta;
	}
}