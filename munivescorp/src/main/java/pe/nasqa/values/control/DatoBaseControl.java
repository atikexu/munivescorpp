package pe.nasqa.values.control;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.multipart.MultipartFile;

import pe.dataimagenes.utils.Utils;
import pe.nasqa.values.dao.imp.LogCargaDataDaoImp;
import pe.nasqa.values.model.ClienteModel;
import pe.nasqa.values.model.DatoBaseModel;
import pe.nasqa.values.model.ConfigDirecHojaRutaModel;
import pe.nasqa.values.model.DistribucionModel;
import pe.nasqa.values.model.HojaRutaModel;
import pe.nasqa.values.model.LogCargaDataModel;
import pe.nasqa.values.model.MVDinamico;
import pe.nasqa.values.model.MensajeroModel;
import pe.nasqa.values.model.PerfilModel;
import pe.nasqa.values.model.UsuarioModel;
import pe.nasqa.values.model.ValoradosModel;
import pe.nasqa.values.model.entity.Cliente;
import pe.nasqa.values.model.entity.ChoiceBean;
import pe.nasqa.values.model.entity.ClienteAgencia;
import pe.nasqa.values.model.entity.ConfigDirecHojaRuta;
import pe.nasqa.values.model.entity.Estados;
import pe.nasqa.values.model.entity.HojaRuta;
import pe.nasqa.values.model.entity.Mensajero;
import pe.nasqa.values.model.entity.Menu;
import pe.nasqa.values.model.entity.Perfil;
//import pe.nasqa.values.model.entity.TablaReporte;
import pe.nasqa.values.model.entity.Usuario;
import pe.nasqa.values.model.entity.UsuarioEstado;
import pe.nasqa.values.model.entity.Valorado;
import pe.nasqa.values.model.entity.LogCargaData;

@Controller
@RequestMapping(value="/datobase")
@SessionAttributes({"USUARIO_INFO","USUARIO_TIPO","SACoordinacionSelect"})

public class DatoBaseControl {
	
	@Autowired
	private ValoradosModel valoradosModel;

	@Autowired
	private LogCargaDataModel logCargaDataModel;

	
	@Autowired
	private DatoBaseModel datobaseModel;

	
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
	private ConfigDirecHojaRutaModel configDirecHojaRutaModel;
	
	@Autowired
	private ImpExpDbZip zip;
	@Autowired
	private SendMail mail;
	
	private Logger log = Logger.getLogger(DatoBaseControl.class);
	
	@RequestMapping(value="/index.htm")
	public String inicio(Model model){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.P1.name()));
		model.addAttribute("menuSelect", Menu.P1.name());
		return "datobase/index";
	}
	
	
	
	
	//-------------------------- CP carga de archivo
	
		@RequestMapping(value={"/carga_archivo.htm"})
		public String carga_archivo(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req){
			String view="datobase/carga_archivo";
			System.out.println("Codigo cliente al cargar  " +usuarioInfo.getIdCliente());
			System.out.println("----------------------------------->>>>>>>>>>>>>view  "+view);
			try {
			//	user_loadCliSer(model, codiCliente);
				if (usuarioInfo.getIdCliente()==null)
				user_load(model,"0000");
				else
					user_load(model,usuarioInfo.getIdCliente());
				
			} catch (Exception e) {
				model.addAttribute("error", "true");
				log.error(e.getMessage());
			}
			model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.P1.name()));
			model.addAttribute("menuSelect", Menu.P2.name());

			return view;
		}

		
		//--------------------------cp

		@RequestMapping(value={"/upload_reportSRV.htm"}, method = RequestMethod.POST)
		
		public String upload_reportSRV(Model model, @RequestParam("file") MultipartFile file, HttpServletRequest request, 
				@ModelAttribute("USUARIO_INFO") Usuario usuarioInfo,HttpServletResponse response){
			
			Utils fec = new Utils();
			
			
			String componente = request.getParameter("RutaTexto");
			String CodProducto;
		
			     System.out.println("codigo Producto "+ componente);
			
			String view="datobase/carga_archivo";
			if (componente.length()>0)
			{
			CodProducto = (componente.substring(1,15)).trim();
			componente =  componente.substring(15, componente.length())+fec.getFechaISO()+"/PENDIENTE";
			
			     System.out.println("componente cliente "+ componente);
			     System.out.println("codigo Producto "+ CodProducto);
			
			
			
		//	String view="datobase/carga_archivo";
			String path =  request.getSession().getServletContext().getRealPath("dinamic/upload");
			System.out.println("srv "+ path);
			
			try 
			{
		
				
				byte[] bytes = file.getBytes();
				
				//String dirDestino = CVDinamico.getDateInFormat("yyyy-MM-dd_hhmm");
				File dir = new File(path + File.separator + "BASES");
				if (!dir.exists())dir.mkdirs();
				
				/*
				File dir = new File ("E:\\REPORTE GNB");//BORRAR ESTO
				if (!dir.exists())dir.mkdirs();
				*/
				log.debug("Carpeta de Carga: "+dir);
							System.out.println("-----directorio------>"+dir);
							
							
				File uploadFile = new File(dir.getAbsolutePath(), CodProducto+"_"+  fec.stripAccents(file.getOriginalFilename().replace(" ", "_")));
							System.out.println("----director + cod producto + nombre archivo---------------Fin >"+uploadFile);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile));
				stream.write(bytes);
				stream.close();
				
				model.addAttribute("file", file.getOriginalFilename());
				
				
				System.out.println("FILE:"+uploadFile.getAbsolutePath());
				System.out.println("FILE:"+uploadFile.getPath());
				System.out.println("FILE:.... nombre ............"+uploadFile.getName());
				
				System.out.println("       FILE:.... nombre"+uploadFile.getName().replace(" ", "_"));

				System.out.println("extension =          "+(uploadFile.getName().substring(uploadFile.getName().lastIndexOf("."))).toLowerCase());
				
				String extension =(uploadFile.getName().substring(uploadFile.getName().lastIndexOf("."))).toLowerCase();
				 
				String[] extensiones_permitida = {".txt", ".xls", ".xlsx"}; 
				 Boolean permitida;
				 permitida=false;
				 
				 for(int i=0; i<extensiones_permitida.length; i++)
				 	{
						System.out.println("extencion y permitida --------"+extensiones_permitida[i]+extension+"----"+extensiones_permitida[i].equals(extension));
						if (extensiones_permitida[i].equals(extension)) { 
					         permitida = true; 
					         break; 
					         }
					}
				// Pregunta si el archivo ya ha sido cargado...no permite cargar mas de dos veces 
				if 	(logCargaDataModel.countLogCargaDataNombre(uploadFile.getName().replace(" ", "_").replace(CodProducto+"_","")))
				{
					permitida= false;
					model.addAttribute("error2", "3");
					System.out.println("error2 3");
				
				}
				 
				System.out.println("sin espacios ................. "+uploadFile.getName().replace(" ", "_").replace(CodProducto+"_",""));
				System.out.println("codigos extra�os ................. "+uploadFile.getName().replace("���", "_").replace(CodProducto+"_",""));
				
				//permitida=false;
				
				System.out.println(fec.stripAccents(uploadFile.getName().replace(" ", "_")));
				
             			
				
				if (permitida)
				{
								 
				 System.out.println("Archivo permitido---------------->:"+permitida);
				 
				 System.out.println("Usuario---------------->:"+usuarioInfo.getUsername());
				 
				// System.out.println("Usuario---------------->:"+);
				 
				 
				SCPFile scp = new SCPFile();
				String resp =	scp.uploadFileToSFTP3(uploadFile.getPath(), componente,request);
			// String resp="1";

			System.out.println("Fecha  hoy -------------->"+fec.getFechaISO());
			System.out.println("Fecha  hoy -------------->"+fec.getFechaActual());
			
			if (resp=="1" )
			{
				
				// Inserta el nombre del archivo al log de carga
				logCargaDataModel.inserLogCargaData(uploadFile.getName().replace(" ", "_").replace(CodProducto+"_",""),fec.getFechaActual(),usuarioInfo.getUsername());
				
				
			model.addAttribute("msg", "Carga Satisfactoria...");
			
			datobaseModel.setDatoBaseLog(CodProducto,uploadFile.getName().replace(" ", "_"), "En Espera", usuarioInfo.getUsername());
	//		view="impexpdb/success";
		//	log.debug("Data Importada a DB");
	
			}
			else
			{
				// model.addAttribute("ms", "");
				 model.addAttribute("error", "1");
			//		view="impexpdb/subir_reporte_gnb";
				//	log.debug("Error Al subir los Datos, Archivo inconsistente");

			}	
				 //scp.uploadFileToSFTP(uploadFile.getPath(), "/home/dataimagenes/ingenieria/home/radar/preproceso/BCP/20161107/PENDIENTE");
				                
	
				/*
					if(impExpDbModel.importReportGNB(uploadFile)){
						//getselecArchivoFTP(model, usuarioInfo,,);
						view="impexpdb/success";
						log.debug("Data Importada a DB");
					}else{
						view="impexpdb/subir_reporte_gnb";
						log.debug("Error Al subir los Datos, Archivo inconsistente");
						throw new Exception("ERROR EN LA FUNCION:"+uploadFile.getAbsolutePath());
					}
				*/
				//enviar ftp 
					//uploadFile.delete();
				/*			
				}else{
					throw new Exception("ERROR eliminar cabecera:"+pathFinal);
				}*/
				
				}
				else
					
				{
				
					model.addAttribute("error", "2");
					
					System.out.println("error 2");
					
				}
				 
				//view="datobase/carga_archivo";
			
				
			} catch (Exception e) {
                
	
				log.error(e.getMessage());
				     model.addAttribute("error", "1");
				
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
                
	
			//	view="impexpdb/subir_reporte_gnb";
	
				//model.addAttribute("error", "true");
				model.addAttribute("vacio", "true");
			}
			
			}
			else
			{
				 model.addAttribute("error", "1");
					 
			}
			
			if (usuarioInfo.getIdCliente()==null)
				user_load(model,"0000");
				else
					user_load(model,usuarioInfo.getIdCliente());
			
			model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.F1.name()));
		
			model.addAttribute("menuSelect", Menu.F6.name());
		
			System.out.println("view a mandar :"+ view);
			return view;
			
			
		}
		
		//--------------------------cp
		
		@RequestMapping(value={"/cargar_productos.htm"})
	    public void cargarProductos(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest req, HttpServletResponse response,
	                 @RequestParam("codCliente") String codCliente){
	          response.setContentType("application/json");
	     JSONObject outPut = new JSONObject(); 
	          try{  
	                 String  selectProductos="";
	                 selectProductos="<option value=\""+""+"\" >"+"::SELECCIONAR::"+"</option>";
	                 user_load(model,codCliente);
	                 
	                 
	             	List <Valorado>  listavalorados = valoradosModel.findValoradoCliServ(codCliente,codCliente);
	        		model.addAttribute("RPAdminUserTablasR", listavalorados);
	        		System.out.println("DATOS lista valorados ............ >"+listavalorados.size());
	        		for(Valorado entidadv : listavalorados){
	        			System.out.println(" Admincontrol->"+entidadv.getNombre());
//	        			System.out.println("->"+entidadC.getId_cliente());
	        	                                           }
	        		
	        	
	                 
	                 
	            //     List<Valorado> valorado = distribucionModel.getProductoXCliente(codCliente);
	              //   System.out.println("LISTA PRODUCTOS:"+valorado.size());
	                
	        	//	model.addAttribute("valorado", valorado);

	             //List<ChoiceBean> listaProductos = new ArrayList<ChoiceBean>();
	           //ChoiceBean bean = new ChoiceBean();

	           for(Valorado val : listavalorados){
	             // bean = new ChoiceBean();
	             // bean.setValue(val.getId());
	                 //bean.setDescripcion(val.getNombre());
	                // listaProductos.add(bean);
	        	   
	        	   
	        	   System.out.println("sin espacion   --->"+val.getCodProd());
	        	   System.out.println("con espacion   --->"+agregarCeros(val.getCodProd(),15));
	                 selectProductos+=getHTMLDinamicOption(agregarCeros(val.getCodProd(),15)+val.getRuta (), val.getNombre());
	           }
	           outPut.put("selectProductos", selectProductos);
	           System.out.println("LISTA PRODUCTOS ultimo:"+listavalorados.size());
	           response.getWriter().write(outPut.toString());
	          }catch(Exception e){
	                 e.printStackTrace();
	          }
	      	model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.P1.name()));
			model.addAttribute("menuSelect", Menu.P1.name());
		
	          //model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.M1.name()));
	          //model.addAttribute("menuSelect", Menu.M2.name());
	          //return view;
	    }

		
		

	
	
	/////////////////////////////////
	
		public static String getHTMLDinamicOption(String Ruta, String descripcion){
	        return  "<option value=\""+Ruta+"\" >"+descripcion+"</option>";
	    }

		private static String agregarCeros(String string, int largo)
		{
			String ceros = "";

			int cantidad = largo - string.length();

			if (cantidad >= 1)
			{
				for(int i=0;i<cantidad;i++)
				{
					ceros += " ";
				}

				return (ceros + string);
			}
			else
				return string;
		}
		
		public void user_load(Model model,String codCliente ){
			
			model.addAttribute("RPAdminUserPerfiles", perfilModel.findAll());
				List<Usuario> listaUsuarios = usuarioModel.findAll();
			
				//System.out.println("DATOS PRUEBA:Admin Control:::::::>"+listaUsuarios.size());
			model.addAttribute("RPAdminUserUsuarios", listaUsuarios);
			
			
			if (codCliente=="0000")
			{
				//model.addAttribute("RPAdminUserClientes", clienteModel.findAll());
				model.addAttribute("RPAdminUserClientes", distribucionModel.getClientes());
						//clienteModel.findAll());
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
			
			// --- carlos P
				//	List<TablaReporte> listaReporte = tablaModel.findAll();
				//	System.out.println("DATOS PRUEBA::::::::>"+listaReporte.size());
				
				/*	for(TablaReporte entidad : listaReporte){
						System.out.println("-AdminControl>"+entidad.getCampo());
					}*/
		//	model.addAttribute("RPAdminUserTablas", listaReporte);		
			
			
				List<ClienteAgencia> listaTablas = clienteModel.buscarAgencias("0007");
		
		//	System.out.println("DATOS PRUEBA::::::::>"+listaTablas.size());
			
		
			/*
			List <String> listaTablas;
			listaTablas = new ArrayList <String>();
			listaTablas.add("distribucion");
			listaTablas.add("distribucion_coord");
			listaTablas.add("distribucion_coord_telf");
			listaTablas.add("distribucion_paquete");
			listaTablas.add("distribucion_visita");
			model.addAttribute("RPAdminUserTablasR", listaTablas);*/
		}
		
		//------------------------------------------------------------------------------------------------------CP
		
		
		
	/////////////////////////////////	
}
