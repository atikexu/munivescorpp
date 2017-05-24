package pe.nasqa.values.service.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/*
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.core.InjectParam;
import com.sun.jersey.core.header.ContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;

import pe.nasqa.values.model.CargaImgModel;
import pe.nasqa.values.service.SVConstante;

@Component
@Path("/cargaimg")*/
public class CargaImgEndpoint {
	/*
	@InjectParam
	private CargaImgModel cargaImgModel;
	
	private Logger log = Logger.getLogger(CargaImgEndpoint.class);
	
	private static final String SERVER_UPLOAD_LOCATION_FOLDER = "/Users/edwin/Workspace/hdd_hddi/uploads/";

	@GET
	@Path("/info")
	@Produces("application/json")
	public Response cargoRead()throws JSONException{
		JSONObject json=new JSONObject();
		json.put("api", "public cargos");
		json.put("version", "0.1");
		return Response.status(200).entity(json.toString()).build();
	}
	
	@POST
	@Path("/cargo")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(FormDataMultiPart form) {
		Integer status=SVConstante.REQ_STATUS_CODE_200;
		String message=SVConstante.REQ_STATUS_MESSAGE_SENT;
		String detail ="";
		
		FormDataBodyPart filePart = form.getField("file");
		ContentDisposition headerOfFilePart =  filePart.getContentDisposition();
		InputStream fileInputStream = filePart.getValueAs(InputStream.class);
	    
		String filePath = SERVER_UPLOAD_LOCATION_FOLDER + headerOfFilePart.getFileName();
		saveFile(fileInputStream, filePath);
		System.out.println(">>>"+headerOfFilePart.getFileName());
		
		detail = cargaImgModel.cargoPublicarUno(SERVER_UPLOAD_LOCATION_FOLDER, headerOfFilePart.getFileName());
		
		JSONObject json=new JSONObject();
		json.put("status", status);
		json.put("message", message);
		json.put("detail", detail);
		
		return Response.status(200).entity(json.toString()).build();
    }
	
	private void saveFile(InputStream uploadedInputStream, String serverLocation) {
        try {
            OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
            int read = 0;
            byte[] bytes = new byte[1024];
            outpuStream = new FileOutputStream(new File(serverLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }
            outpuStream.flush();
            outpuStream.close();
            uploadedInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
}
