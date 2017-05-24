package pe.nasqa.values.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import pe.nasqa.values.control.CVConstante;
import pe.nasqa.values.control.CVDinamico;
import pe.nasqa.values.model.CargaImgModel;
import pe.nasqa.values.model.entity.Menu;

@Controller
@RequestMapping(value="/ws/cargaimg")
public class CargaImgWService {

	@Autowired
	private CargaImgModel cargaImgModel;
	
	private Logger log = Logger.getLogger(CargaImgWService.class);
	
	@RequestMapping(value={"/cargo_img_upload.htm"}, method = RequestMethod.GET)
	public @ResponseBody String cargo_sigle_upload_info(){
		JSONObject json=new JSONObject();
		json.put("status", 200);
		json.put("message", "Success");
		json.put("detail", "REST Endpoint ready");
		return json.toString();
	}
	
	@RequestMapping(value={"/cargo_img_upload.htm"}, method = RequestMethod.POST)
	public @ResponseBody String cargo_single_upload(Model model, @RequestParam("file") MultipartFile file, HttpServletRequest request){
		Integer status=SVConstante.REQ_STATUS_CODE_200;
		String message=SVConstante.REQ_STATUS_MESSAGE_SENT;
		String detail ="";
		
		String path =  request.getSession().getServletContext().getRealPath("dinamic/endpoint");
		try {
			
			byte[] bytes = file.getBytes();
			message=SVConstante.REQ_STATUS_MESSAGE_PROCESS;
			File uploadFile = new File(path, file.getOriginalFilename());
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile));
			stream.write(bytes);
			stream.close();
			
			detail = cargaImgModel.cargoPublicarUno(path, uploadFile.getName());
			uploadFile.delete();
			message=SVConstante.REQ_STATUS_MESSAGE_SUCCESS;
			
		} catch (Exception e) {
			status=SVConstante.REQ_STATUS_CODE_500;
			message=SVConstante.REQ_STATUS_MESSAGE_ERROR + ": "+e.getMessage();
			log.error(e.getMessage());
			//model.addAttribute("error", "true");
		}
		/*
		JSONObject json=new JSONObject();
		json.put("status", status);
		json.put("message", message);
		json.put("detail", detail);
		//Error sin resolver en VB.NET con Endpoint Jersey
		*/
		String result = status + "|" + message + "|" + detail;
		return result;
	}
}
