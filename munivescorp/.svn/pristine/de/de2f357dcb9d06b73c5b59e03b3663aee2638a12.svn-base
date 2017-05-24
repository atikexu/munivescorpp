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
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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

import pe.nasqa.values.model.ImpExpDbModel;
import pe.nasqa.values.model.MUtilReportFiles;
import pe.nasqa.values.model.entity.ExportVisita;
import pe.nasqa.values.model.entity.Menu;
import pe.nasqa.values.model.entity.ReporteGNB;
import pe.nasqa.values.model.entity.ReporteRevistas;
import pe.nasqa.values.model.entity.Usuario;

@Controller
@RequestMapping(value="/impexpdb")
@SessionAttributes({"USUARIO_INFO","USUARIO_TIPO","USUARIO_CLIENTE"})
public class ImpExpDbControl {
	
	@Autowired
	ImpExpDbModel impExpDbModel;
	
	@Autowired
	ImpExpDbZip zip;
	
	private Logger log = Logger.getLogger(ImpExpDbControl.class);
	
	@RequestMapping(value={"/index.htm"}) 
	public String index(Model model, HttpServletRequest request){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.F1.name()));
		model.addAttribute("menuSelect", Menu.F1.name());
		return "impexpdb/index";
	}
	
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
			
			/*
			File dir = new File ("E:\\REPORTE GNB");//BORRAR ESTO
			if (!dir.exists())dir.mkdirs();
			*/
			log.debug("Carpeta de Carga: "+dir);
			System.out.println("-->"+dir);
			File uploadFile = new File(dir.getAbsolutePath(), file.getOriginalFilename());
			System.out.println("-->"+uploadFile);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile));
			stream.write(bytes);
			stream.close();
			model.addAttribute("file", file.getOriginalFilename());
			/*
			String pathFinal = deleteCabecera(uploadFile, dir.getAbsolutePath());			
			if(!pathFinal.equals("ERROR")){
				File fileFinal = new File(pathFinal);System.out.println("---->1");
				if(impExpDbModel.importReportGNB(fileFinal)){
					import_reporte_gnb_load(model, usuarioInfo);
					view="impexpdb/success";
					log.debug("Data Importada a DB");
				}else{
					view="impexpdb/subir_reporte_gnb";
					log.debug("Error Al subir los Datos, Archivo inconsistente");
					throw new Exception("ERROR EN LA FUNCION:"+fileFinal.getAbsolutePath());
				}
				fileFinal.delete();		
				*/
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
			/*			
			}else{
				throw new Exception("ERROR eliminar cabecera:"+pathFinal);
			}*/
			
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
	}	

}
