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
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
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







import pe.nasqa.values.model.ConsultaAlaizModel;
//import pe.nasqa.values.model.ImpExpDbModel;
import pe.nasqa.values.model.MUtilReportFiles;
import pe.nasqa.values.model.ReportesInternosModel;
import pe.nasqa.values.model.entity.DistribucionCoord;
//import pe.nasqa.values.model.entity.ExportVisita;
import pe.nasqa.values.model.entity.Menu;
//import pe.nasqa.values.model.entity.ReporteGNB;
//import pe.nasqa.values.model.entity.ReporteRevistas;
//import pe.nasqa.values.model.entity.Usuario;
import pe.nasqa.values.model.entity.Usuario;

@Controller
@RequestMapping(value="/consultaalaiz")
//@SessionAttributes({"USUARIO_INFO","USUARIO_TIPO","USUARIO_CLIENTE"})
@SessionAttributes({"USUARIO_INFO","USUARIO_TIPO","USUARIO_CLIENTE", "CLIENTE_CONFIG_CONSRVPAQ","ID_CLIENTE"})
public class ConsultaAlaizControl {
	
	@Autowired
	ConsultaAlaizModel consultaAlaizModel;
	
	@Autowired
	ImpExpDbZip zip;
	
	private Logger log = Logger.getLogger(ConsultaAlaizControl.class);
	
	@RequestMapping(value={"/index.htm"}) 
	public String index(Model model, HttpServletRequest request){
		model.addAttribute("pageTitle", CVConstante.getPageTitle(Menu.R1.name()));
		model.addAttribute("menuSelect", Menu.R1.name());
		
		String tabla="";
		tabla=getHTMLTablaDocumentos(consultaAlaizModel.getLista());
		model.addAttribute("listaAgencias", tabla);
		return "consultaalaiz/index";
	}
	
	public String getHTMLTablaDocumentos(List<Object> listaAgencias){
		Integer estado;
        String outPut = 
                            "<table id=\"table-Agencia\" >" +
                                "<thead>" +
                                    "<tr>" +
                                        "<td>Codigo</td>" +
                                        "<td>Detalle</td>" +
                                        "<td>Oficina</td>" +
                                        "<td>Clase Documental</td>" +
                                        "<td>Serie Descripción</td>" +
                                        "<td>Empresa</td>" +
                                       
                                    "</tr>" +
                                "</thead>" +
                                "<tbody>";
        
        Iterator itr = listaAgencias.iterator();
        while(itr.hasNext()){
           Object[] obj = (Object[]) itr.next();    
           outPut +=    "<tr class='<c:if test=\"${rowCounter.count % 2 == 0}\">on</c:if>'>" +
                            "<td class=\"boton\">"+obj[2]+"</td>" +
                            "<td class=\"boton\">"+obj[5]+"</td>" +
                            "<td class=\"boton\">"+obj[6]+"</td>" +
                            "<td class=\"boton\">"+obj[7]+"</td>" +
                            "<td class=\"boton\">"+obj[10]+"</td>" +
                            "<td class=\"boton\">"+obj[11]+"</td>" +
                            "<td hidden=\"\" class=\"boton\">"+obj[3]+"</td>" +
                            "<td hidden=\"\" class=\"boton\">"+obj[4]+"</td>" +
                            "<td hidden=\"\" class=\"boton\">"+obj[8]+"</td>" +
                            "<td hidden=\"\" class=\"boton\">"+obj[12]+"</td>" +
                            "<td hidden=\"\" class=\"boton\">"+obj[14]+"</td>" +
                            "<td hidden=\"\" class=\"boton\">"+obj[18]+"</td>" +
                            "</tr>";          
        }         
                outPut +=       "</tbody>" +
                            "</table>";
                        
        
         return outPut;
    }
	

}
