package pe.nasqa.values.control;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class PdfHojaRuta {
	public void generarPdf(String id){
		try{
			JasperReport repor;
        Map parametro = new HashMap();
        URL  in=this.getClass().getResource("/pe.nasqa.values.PdfRutas/Hoja_RUTA1.jasper");
        repor=(JasperReport)JRLoader.loadObject(in);
        parametro.put("nombre","BCP");
        //parametro.put("cargoEmpleado",cargoPersona);
        
        String sourceDir = ""; // Pdf files are read from this folder
        String destinationDir= "imagen\\"; 
        
        /* JasperPrint jPrint = JasperFillManager.fillReport(repor, parametro,new JREmptyDataSource());
        jPrint.setProperty("net.sf.jasperreports.expo rt.character.encoding","ISO-8859-1");


            JasperExportManager.exportReportToPdfFile(jPrint,"C:\\Users\\proyecto.migracion2\\Documents\\workspace-sts-3.7.2.RELEASE\\generarPDF\\cargos\\archivo_generico.pdf");
            sourceDir = "pdf\\archivo_generico.pdf";
       
        System.out.println("creo pdf");
        File sourceFile = new File(sourceDir);
        File destinationFile = new File(destinationDir);
        if (!destinationFile.exists()) {
            destinationFile.mkdir();
        }*/
		}catch(JRException ex){
            System.out.println(ex.toString());
        }
	}
}
