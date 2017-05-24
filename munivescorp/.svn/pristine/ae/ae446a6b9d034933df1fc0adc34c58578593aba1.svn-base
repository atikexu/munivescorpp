package pe.nasqa.values.control;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import pe.nasqa.values.model.CargaImgModel;
import pe.nasqa.values.model.entity.Menu;
import pe.nasqa.values.model.entity.Usuario;

@Controller
@RequestMapping(value="/cargaimg")
public class CargaImgControl {
	
	@Autowired
	private ImpExpDbZip zip;
	
	@Autowired
	private CargaImgModel cargaImgModel;
	
	private Logger log = Logger.getLogger(CargaImgControl.class);
	
	@RequestMapping(value={"/index.htm"}) 
	public String index(Model model, HttpServletRequest request){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.G1.name()));
		model.addAttribute("menuSelect", Menu.G1.name());
		return "cargaimg/index";
	}
	
	@RequestMapping(value={"/cargo_upload.htm"}, method = RequestMethod.POST)
	public String cargo_upload(Model model, @RequestParam("file") MultipartFile file, HttpServletRequest request){
		String view="cargaimg/index";
		String path =  request.getSession().getServletContext().getRealPath("dinamic/upload");
		try {
			
			byte[] bytes = file.getBytes();
			String dirDestino = "CARGOS"+CVDinamico.getDateInFormat("yyyy-MM-dd_hhmm");
			File dir = new File(path,dirDestino);
			if (!dir.exists())dir.mkdirs();
			
			log.info("Carpeta de Carga (Img-Cargos): "+dir);
			
			File uploadFile = new File(dir.getAbsolutePath(), file.getOriginalFilename());
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile));
			stream.write(bytes);
			stream.close();
			
			String llave = request.getParameter("llave")==null?"":request.getParameter("llave");
			
			List<String> unZipFiles = zip.unZipFileZip4j(uploadFile, (llave.length()==0?null:llave));
			int cantValidos = CVDinamico.nombresValidosCargos(unZipFiles);
			
			String logCarga = "";
			logCarga+=unZipFiles.size()+" Imagennes Cargadas. \n\n";
			if(cantValidos==unZipFiles.size()){
				logCarga+="Todos estan correctamente renombrados. \n";
			}else{
				logCarga+=cantValidos+" NO estan correctamente renombrados. \n";
			}
			
			model.addAttribute("upLog", logCarga);
			model.addAttribute("upFiles", unZipFiles);
			model.addAttribute("upDir", dirDestino);
			
			uploadFile.delete();
			
			view="cargaimg/cargo_public";
			
		} catch (Exception e) {
			log.error(e.getMessage());
			model.addAttribute("error", "true");
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.G1.name()));
		model.addAttribute("menuSelect", Menu.G1.name());
		return view;
	}
	
	@RequestMapping(value={"/cargo_public.htm"}, method = RequestMethod.POST)
	public String cargo_public(Model model, @ModelAttribute("USUARIO_INFO") Usuario usuarioInfo, HttpServletRequest request){
		String view="cargaimg/error";
		String path =  request.getSession().getServletContext().getRealPath("dinamic/upload");
		try {
			String logCarga="";
			
			String upDir=request.getParameter("upDir");
			if(upDir!=null){
				File dir = new File(path,upDir);
				if(dir.exists()){
					logCarga = cargaImgModel.cargoPublicar(dir.getAbsolutePath(), getFiles(dir));
					dir.delete();
				}
			}
			
			model.addAttribute("logCarga", logCarga);
			
			view="cargaimg/success";
		} catch (Exception e) {
			model.addAttribute("error", "true");
		}
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.G1.name()));
		model.addAttribute("menuSelect", Menu.G1.name());
		model.addAttribute("ref", "cargo");
		return view;
	}
	
	
	private List<String> getFiles(File dir){
		List<String> files=new ArrayList<String>();
		for (String file : dir.list()) {
			if(new File(dir,file).isFile()){
				files.add(file);
			}
		}
		return files;
	}

}
