
package pe.nasqa.values.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;  //Cp
import org.apache.poi.hssf.usermodel.HSSFCellStyle;




import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;





import pe.dataimagenes.utils.Utils;
import pe.nasqa.values.model.entity.DistribucionCoord;
import pe.nasqa.values.model.entity.DistribucionVisita;
import pe.nasqa.values.model.entity.ReporteBBVAentr;
import pe.nasqa.values.model.entity.ReporteBBVApend;
import pe.nasqa.values.model.entity.ReporteBBVAworkf;
import pe.nasqa.values.model.entity.ReporteBCPdetalleGen;
import pe.nasqa.values.model.entity.ReporteBCPtc;
import pe.nasqa.values.model.entity.ReporteDistribucionJoin;
import pe.nasqa.values.model.entity.ReporteEnvioProv;
import pe.nasqa.values.model.entity.ReporteCoordinacionDia;
import pe.nasqa.values.model.entity.ReporteGNB;
import pe.nasqa.values.model.entity.ReporteGNBtoken;
import pe.nasqa.values.model.entity.ReporteGNBvales;
import pe.nasqa.values.model.entity.ReporteHerbalife;
import pe.nasqa.values.model.entity.ReporteOrbinGestion;
import pe.nasqa.values.model.entity.ReporteRevistas;

public class MUtilReportFiles {
	
	public Map<String,String> createFilesReport(String path, String fileName, String idCliente, 
			boolean datoOpcional, boolean datoMandatario, 
			int cantVisita, int cantCoord, int cantCoordTelf,
			List<ReporteDistribucionJoin> listaReporte){
		Map<String,String> result = new HashMap<String,String>();
		try{
//			ReporteDistribucionJoin rep=null;
//			for(int i=0;i<listaReporte.size(); i++)
//			{
//			    rep=listaReporte.get(i);       
//			    
//			}
			System.out.println(path);
			String fileXLS = fileName+".xls";
			String fileTXT = fileName+".txt";
			System.out.println("entro1");
			createFilesReportXLS(path, fileXLS, idCliente, datoOpcional, datoMandatario, cantVisita, cantCoord, cantCoordTelf, listaReporte);
			System.out.println("entro2");
			result.put("xls", fileXLS);
			
			
		}catch(Exception e){
			
		}
		return result;
	}
	
	public Map<String,String> createFilesReport_R2(String path, String fileName, String idCliente, 
			boolean datoOpcional, boolean datoMandatario, 
			int cantVisita, int cantCoord, int cantCoordTelf,
			List<ReporteDistribucionJoin> listaReporte){
		Map<String,String> result = new HashMap<String,String>();
		try{
//			ReporteDistribucionJoin rep=null;
//			for(int i=0;i<listaReporte.size(); i++)
//			{
//			    rep=listaReporte.get(i);       
//			    
//			}
			System.out.println(path);
			String fileXLS = fileName+".xls";
			String fileTXT = fileName+".txt";
			
			createFilesReportXLS_R2(path, fileXLS, idCliente, datoOpcional, datoMandatario, cantVisita, cantCoord, cantCoordTelf, listaReporte);
			result.put("xls", fileXLS);
			
			
		}catch(Exception e){
			
		}
		return result;
	}
	//se crea reporte que no entendia: 
	private void createFilesReportXLS(String path, String fileName, String idCliente, 
			boolean datoOpcional, boolean datoMandatario, 
			int cantVisita, int cantCoord, int cantCoordTelf, 
			List<ReporteDistribucionJoin> listaReporte){
		try{			
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			HSSFRow row = sheet.createRow((short)0);
			HSSFRichTextString celdaHead;
			int h=-1;
			celdaHead = new HSSFRichTextString("COD-BARRAS"); row.createCell(++h).setCellValue(celdaHead);
			if(idCliente==null){
			celdaHead = new HSSFRichTextString("COD-CLIENTE"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("NOM-CLIENTE"); row.createCell(++h).setCellValue(celdaHead);
			}
			celdaHead = new HSSFRichTextString("COD-PRODUCT"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("NOM-PRODUCT"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("OP"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("FECHA-PROCESO"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("TIPO-VAL"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("TIPO-EMI"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("DESC-EMI"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("FECHA-EMI"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("NRO-DOC1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("NRO-DOC2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("DESTINATARIO"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("TIT-NOMBRE"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("TIT-TIPDOC"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("TIT-NUMDOC"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("DIREC-DOM"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("REF-DOM"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("UBIGEO-DOM"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("DIREC-LAB"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("REF-LAB"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("UBIGEO-LAB"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("EMPRESA-LAB"); row.createCell(++h).setCellValue(celdaHead);
			if(datoOpcional){
			celdaHead = new HSSFRichTextString("DIREC-OPC"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("REF-OPC"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("UBIGEO-OPC"); row.createCell(++h).setCellValue(celdaHead);
			}
			celdaHead = new HSSFRichTextString("TELF-DOM"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("TELF-LAB"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("TELF-OPC"); row.createCell(++h).setCellValue(celdaHead);
			if(datoMandatario){
			celdaHead = new HSSFRichTextString("MAND1-NOMBRE"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("MAND1-TELEF"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("MAND2-NOMBRE"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("MAND2-TELEF"); row.createCell(++h).setCellValue(celdaHead);
			}
			celdaHead = new HSSFRichTextString("IND.(SITUACION)"); celdaHead.applyFont(HSSFFont.BOLDWEIGHT_BOLD); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("IND.(ESTADO)"); celdaHead.applyFont(HSSFFont.BOLDWEIGHT_BOLD); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("IND.(DESCRIPCION)"); celdaHead.applyFont(HSSFFont.BOLDWEIGHT_BOLD); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ENTREGA-FECHA"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ENTREGA-VINCULO"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ULT.VISITA-FECHA"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ULT.VISITA-RESULT"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ULT.VISITA-LUGAR"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("NRO-HOJARUTA"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("NRO-RENDICION"); row.createCell(++h).setCellValue(celdaHead);
			
			celdaHead = new HSSFRichTextString("CANT-VISITAS"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("CANT-COORDS"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("CANT-SEGTELF"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("CON-IMAGEN"); row.createCell(++h).setCellValue(celdaHead);
			
			for(int v=1;v<=cantVisita;v++){
			celdaHead = new HSSFRichTextString("VISITA"+v+"_FECHA"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("VISITA"+v+"_RESULT"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("VISITA"+v+"_LUGAR"); row.createCell(++h).setCellValue(celdaHead);
			}
			
			for(int c=1;c<=cantCoord;c++){
			celdaHead = new HSSFRichTextString("COORD"+c+"_FECHA"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("COORD"+c+"_HORA"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("COORD"+c+"_F-REG"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("COORD"+c+"_T-DIR"); row.createCell(++h).setCellValue(celdaHead);
			}
			
			for(int t=1;t<=cantCoordTelf;t++){
			celdaHead = new HSSFRichTextString("SEG.TLF"+t+"_FECHA"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("SEG.TLF"+t+"_HORA"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("SEG.TLF"+t+"_NROTLF"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("SEG.TLF"+t+"_OBS."); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("SEG.TLF"+t+"_RESULT"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("SEG.TLF"+t+"_TIPO"); row.createCell(++h).setCellValue(celdaHead);
			}
			celdaHead = new HSSFRichTextString("DEPARTAMENTO"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("PROVINCIA"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("DISTRITO"); row.createCell(++h).setCellValue(celdaHead);
			ReporteDistribucionJoin rep=null;
			for(int i=0;i<listaReporte.size(); i++)
			{
			    rep=listaReporte.get(i);       
			    HSSFRow rowBody = sheet.createRow((int)(i+1));
			    int k=-1;
			    //rowBody.createCell(++k).setCellValue(rep.getCodBar());
			    HSSFRichTextString celdaBody;
			    celdaBody = new HSSFRichTextString(rep.getCodBar()); rowBody.createCell(++k).setCellValue(celdaBody);
				if(idCliente==null){
				celdaBody = new HSSFRichTextString(rep.getCodCli()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getNomCli()); rowBody.createCell(++k).setCellValue(celdaBody);
				 
				}
				celdaBody = new HSSFRichTextString(rep.getCodProVal()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getDesProVal()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getOrdPro()+""); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString((rep.getFecPro()==null) ? "" : MVDinamico.getDateInFormat(rep.getFecPro(),"dd/MM/yyyy")); rowBody.createCell(++k).setCellValue(celdaBody);
				
				celdaBody = new HSSFRichTextString(rep.getValTip()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getValTipEmi()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getValDesEmi()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString((rep.getValFecEmi()==null) ? "" : MVDinamico.getDateInFormat(rep.getValFecEmi(),"dd/MM/yyyy")); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getValNroIde()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getValOtrId1()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getDesNomApe()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getTitNomApe()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getTitTipDoc()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getTitNumDoc()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getDirDomDes()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getDirDomRef()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getDirDomUbi()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getDirLabDes()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getDirLabRef()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getDirLabUbi()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getDirLabEmp()); rowBody.createCell(++k).setCellValue(celdaBody);
				if(datoOpcional){
				celdaBody = new HSSFRichTextString(rep.getDirOpcDes()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getDirOpcRef()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getDirOpcUbi()); rowBody.createCell(++k).setCellValue(celdaBody);
				}
				celdaBody = new HSSFRichTextString(rep.getTitTlfDom()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getTitTlfLab()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getTitTlfOpc()); rowBody.createCell(++k).setCellValue(celdaBody);
				if(datoMandatario){
				celdaBody = new HSSFRichTextString(rep.getMd1NomApe()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getMd1NumTlf()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getMd2NomApe()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getMd2NumTlf()); rowBody.createCell(++k).setCellValue(celdaBody);
				}
				celdaBody = new HSSFRichTextString(rep.getDesSit()); celdaBody.applyFont(HSSFFont.BOLDWEIGHT_NORMAL); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getDesEst()); celdaBody.applyFont(HSSFFont.BOLDWEIGHT_NORMAL); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getDesMot()); celdaBody.applyFont(HSSFFont.BOLDWEIGHT_NORMAL); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString((rep.getFecEnt()==null) ? "" : MVDinamico.getDateInFormat(rep.getFecEnt(),"dd/MM/yyyy")); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getDesVin()); rowBody.createCell(++k).setCellValue(celdaBody);
				
				String[] camposUltVis = (rep.getDetVisita1()!=null?rep.getDetVisita1():"").split("\\|",-1); //1=Fecha, 5=Resultado, 10=Lugar
				celdaBody = new HSSFRichTextString(camposUltVis.length>2?camposUltVis[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(camposUltVis.length>2?camposUltVis[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(camposUltVis.length>2?camposUltVis[10]:""); rowBody.createCell(++k).setCellValue(celdaBody);
				
				celdaBody = new HSSFRichTextString(rep.getNroHoj()+""); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getNroRen()+""); rowBody.createCell(++k).setCellValue(celdaBody);
				
				celdaBody = new HSSFRichTextString(rep.getResNroVis()+""); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getResNroCoo()+""); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getResNroLla()+""); rowBody.createCell(++k).setCellValue(celdaBody);
				String cargo=((rep.getImgCarEst()==null?"0":rep.getImgCarEst().toString()));
				celdaBody = new HSSFRichTextString(cargo.equals("0")?"NO":"SI"); rowBody.createCell(++k).setCellValue(celdaBody);
				
				//Visitas: "3300|15/04/2015|83|CESAR AUGUSTO HUAMANAS MARIN|1|Pendiente||||0|"
				for(int v=1;v<=cantVisita;v++){
					if(v==1){
						String[] campos = (rep.getDetVisita1()!=null?rep.getDetVisita1():"").split("\\|",-1);
						//1=Fecha, 5=Resultado, 10=Tipo direccion
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[10]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(v==2){
						String[] campos = (rep.getDetVisita2()!=null?rep.getDetVisita2():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[10]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(v==3){
						String[] campos = (rep.getDetVisita3()!=null?rep.getDetVisita3():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[10]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(v==4){
						String[] campos = (rep.getDetVisita4()!=null?rep.getDetVisita4():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[10]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(v==5){
						String[] campos = (rep.getDetVisita5()!=null?rep.getDetVisita5():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[10]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(v==6){
						String[] campos = (rep.getDetVisita6()!=null?rep.getDetVisita6():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[10]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(v==7){
						String[] campos = (rep.getDetVisita7()!=null?rep.getDetVisita7():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[10]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(v==8){
						String[] campos = (rep.getDetVisita8()!=null?rep.getDetVisita8():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[10]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(v==9){
						String[] campos = (rep.getDetVisita9()!=null?rep.getDetVisita9():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[10]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(v==10){
						String[] campos = (rep.getDetVisita10()!=null?rep.getDetVisita10():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[10]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
				
				}
				
				//Coordinaciones : "21/04/2015|24/04/2015|1PM-4PM|CAL. AMADOR MERINO REYNA 551 SAN ISIDRO ALT CDRA 10 AV JAVIER PRADO||150131||Departamento Compras||||1|Destinatario|2|Laboral|1|Normal|2|Saliente"
				for(int c=1;c<=cantCoord;c++){
					if(c==1){
						String[] campos = (rep.getDetCoord1()!=null?rep.getDetCoord1():"").split("\\|",-1);
						//0=Fecha Reg, 1=Fecha Coordinada, 2=Hora Coordinada, 14=Tipo de Direccion
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[14]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(c==2){
						String[] campos = (rep.getDetCoord2()!=null?rep.getDetCoord2():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[14]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(c==3){
						String[] campos = (rep.getDetCoord3()!=null?rep.getDetCoord3():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[14]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(c==4){
						String[] campos = (rep.getDetCoord4()!=null?rep.getDetCoord4():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[14]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(c==5){
						String[] campos = (rep.getDetCoord5()!=null?rep.getDetCoord5():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[14]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(c==6){
						String[] campos = (rep.getDetCoord6()!=null?rep.getDetCoord6():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[14]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(c==7){
						String[] campos = (rep.getDetCoord7()!=null?rep.getDetCoord7():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[14]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(c==8){
						String[] campos = (rep.getDetCoord8()!=null?rep.getDetCoord8():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[14]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(c==9){
						String[] campos = (rep.getDetCoord9()!=null?rep.getDetCoord9():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[14]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(c==10){
						String[] campos = (rep.getDetCoord10()!=null?rep.getDetCoord10():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[14]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					
				}
				
				//Seguimiento telefonico : "20/04/2015|17:09:48|97506799|10|FUERA DE SERVICIO|.|0015|CARLOS ALCAZAR|1|Dataimagenes"
				for(int t=1;t<=cantCoordTelf;t++){
					if(t==1){
						String[] campos = (rep.getDetCoordTelf1()!=null?rep.getDetCoordTelf1():"").split("\\|",-1);
						//0=Fecha Reg, 1=Hora Coordinada, 2=Nro.Telf, 4=Resultado, 5=Observaciones, ?=Tipo Telf.
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[4]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(t==2){
						String[] campos = (rep.getDetCoordTelf2()!=null?rep.getDetCoordTelf2():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[4]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(t==3){
						String[] campos = (rep.getDetCoordTelf3()!=null?rep.getDetCoordTelf3():"").split("\\|",-1);
						//0=Fecha Reg, 1=Hora Coordinada, 2=Nro.Telf, 4=Resultado, 5=Observaciones, ?=Tipo Telf.
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[4]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(t==4){
						String[] campos = (rep.getDetCoordTelf4()!=null?rep.getDetCoordTelf4():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[4]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(t==5){
						String[] campos = (rep.getDetCoordTelf5()!=null?rep.getDetCoordTelf5():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[4]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(t==6){
						String[] campos = (rep.getDetCoordTelf6()!=null?rep.getDetCoordTelf6():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[4]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(t==7){
						String[] campos = (rep.getDetCoordTelf7()!=null?rep.getDetCoordTelf7():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[4]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(t==8){
						String[] campos = (rep.getDetCoordTelf8()!=null?rep.getDetCoordTelf8():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[4]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(t==9){
						String[] campos = (rep.getDetCoordTelf9()!=null?rep.getDetCoordTelf9():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[4]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(t==10){
						String[] campos = (rep.getDetCoordTelf10()!=null?rep.getDetCoordTelf10():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[4]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
				}
				
				//System.out.println(i+")"+rep.getDepartamento().trim());
				
				if(rep.getDepartamento()==null){
					celdaBody = new HSSFRichTextString(rep.getDirDomDep()); rowBody.createCell(++k).setCellValue(celdaBody);
					celdaBody = new HSSFRichTextString(rep.getDirDomPro()); rowBody.createCell(++k).setCellValue(celdaBody);
					celdaBody = new HSSFRichTextString(rep.getDirDomDis()); rowBody.createCell(++k).setCellValue(celdaBody);
				}else{
					celdaBody = new HSSFRichTextString(rep.getDepartamento()); rowBody.createCell(++k).setCellValue(celdaBody);
					celdaBody = new HSSFRichTextString(rep.getProvincia()); rowBody.createCell(++k).setCellValue(celdaBody);
					celdaBody = new HSSFRichTextString(rep.getDistrito()); rowBody.createCell(++k).setCellValue(celdaBody);
				}
				
				
			}
			File file = new File(path,fileName);

			FileOutputStream fout = new FileOutputStream(file);

			wb.write(fout);
			fout.flush();
			fout.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void createFilesReportXLS_R2(String path, String fileName, String idCliente, 
			boolean datoOpcional, boolean datoMandatario, 
			int cantVisita, int cantCoord, int cantCoordTelf, 
			List<ReporteDistribucionJoin> listaReporte){
		try{
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	        Date today = Calendar.getInstance().getTime();   
	        String fechaHoy = df.format(today);
			
			//String fechaHoy = "24/09/2016";	
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			HSSFRow row = sheet.createRow((short)0);
			HSSFRichTextString celdaHead;
			int h=-1;
			celdaHead = new HSSFRichTextString("CARGO"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("GUIA"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("TARJETA"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("DESTINATARIO"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("MANDATARIO"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("MANDATARIO2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("TITULAR"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("DOC_IDENTIDAD_A"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("DOC_IDENTIDAD_B"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("TELEFONO"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("TELEFONO_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("FECHA_RECEPCION"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("FECHA_DESPACHO"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("TIPO_DOCUMENTO"); row.createCell(++h).setCellValue(celdaHead);		
			celdaHead = new HSSFRichTextString("TIPO_EMISION"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("PROVINCIA"); row.createCell(++h).setCellValue(celdaHead);
			//<----UBICACION ACTUAL---->
			celdaHead = new HSSFRichTextString("UBICACION_ACTUAL"); row.createCell(++h).setCellValue(celdaHead);
			//<------------------------>
			celdaHead = new HSSFRichTextString("FECHA ULTIMA"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("RESULTADO ULTIMO"); celdaHead.applyFont(HSSFFont.BOLDWEIGHT_BOLD); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("LUGAR ULTIMO"); row.createCell(++h).setCellValue(celdaHead);
			
			celdaHead = new HSSFRichTextString("TIPO_ENTREGA"); row.createCell(++h).setCellValue(celdaHead);

			//TIPO ENTREGA FALTAAAAAAAAAAAAAAAAAAAAA-->
			celdaHead = new HSSFRichTextString("FECHA_ULTIMA_VISITA"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("RESULTADO_ULTIMA_VISITA"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("CANT_VISITAS"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("CANT_COORDINADAS"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("CANT_SEGUIMIENTOS"); row.createCell(++h).setCellValue(celdaHead);
						
			for(int v=1;v<=cantVisita;v++){
				celdaHead = new HSSFRichTextString("FECHA_VISITA_"+v); row.createCell(++h).setCellValue(celdaHead);
				celdaHead = new HSSFRichTextString("RESULTADO_VISITA_"+v); row.createCell(++h).setCellValue(celdaHead);
				celdaHead = new HSSFRichTextString("LUGAR_VISITA_"+v); row.createCell(++h).setCellValue(celdaHead);
				celdaHead = new HSSFRichTextString("GESTION_VISITA_"+v); row.createCell(++h).setCellValue(celdaHead);
				//GESTION_VISITA FALTAAAAAAAAAAAAAAAAAAAAA-->
			}
			
			for(int c=1;c<=cantCoord;c++){
			celdaHead = new HSSFRichTextString("FECHA_COORD_"+c); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("HORA_COORD_"+c); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("FECHA_REGISTRO_COORD_"+c); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("DIRECCION_COORD_"+c); row.createCell(++h).setCellValue(celdaHead);
			//IND DIRECCION FALTAAAAAAAAAAAAAAAAAAAAA-->
			}
			
			for(int t=1;t<=cantCoordTelf;t++){
			celdaHead = new HSSFRichTextString("FECHA_SEG_TLF_"+t); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("HOR_SEG_TLF_"+t); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("TELEF_SEG_"+t); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("DETALLE_SEG_TLF_"+t); row.createCell(++h).setCellValue(celdaHead);
			}
			
			ReporteDistribucionJoin rep=null;
			for(int i=0;i<listaReporte.size(); i++)
			{
			    rep=listaReporte.get(i);       
			    HSSFRow rowBody = sheet.createRow((int)(i+1));
			    int k=-1;
			    //rowBody.createCell(++k).setCellValue(rep.getCodBar());
			    HSSFRichTextString celdaBody;
			    celdaBody = new HSSFRichTextString(rep.getCodBar()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getOrdPro()+""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getValNroIde()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getDesNomApe()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getMd1NomApe()); rowBody.createCell(++k).setCellValue(celdaBody);
			    //celdaBody = new HSSFRichTextString(rep.getMd2NomApe()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getMd2NumTlf()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getTitNomApe()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getTitNumDoc()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getTitNumDoc()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getTitTlfDom()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getTitTlfLab()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString((rep.getFecPro()==null) ? "" : MVDinamico.getDateInFormat(rep.getFecPro(),"dd/MM/yyyy")); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString("--"); rowBody.createCell(++k).setCellValue(celdaBody);//FECHA DESPACHO HARCODEADO 
				celdaBody = new HSSFRichTextString(rep.getDesProVal()); rowBody.createCell(++k).setCellValue(celdaBody);				
				celdaBody = new HSSFRichTextString(rep.getValDesEmi()); rowBody.createCell(++k).setCellValue(celdaBody);
				if(rep.getProvincia()==null){					
					celdaBody = new HSSFRichTextString(rep.getDirDomPro()); rowBody.createCell(++k).setCellValue(celdaBody);					
				}else{					
					celdaBody = new HSSFRichTextString(rep.getProvincia()); rowBody.createCell(++k).setCellValue(celdaBody);					
				}
				//celdaBody = new HSSFRichTextString(camposUltVis.length>2?camposUltVis[5].toUpperCase():""); rowBody.createCell(++k).setCellValue(celdaBody);
				/////////UBICACION ACTUAL 				
					celdaBody = new HSSFRichTextString(rep.getDesSit().toUpperCase()); rowBody.createCell(++k).setCellValue(celdaBody);				
				////////////////////////
				
				String[] camposUltVis = (rep.getDetVisita1()!=null?rep.getDetVisita1():"").split("\\|",-1); //1=Fecha, 5=Resultado, 10=Lugar
				//FECHA ULTIMA
				/*if(camposUltVis.length>4 && camposUltVis[5].trim().toUpperCase().equals("IMPOSIBLE")){
					celdaBody = new HSSFRichTextString(camposUltVis.length>2?camposUltVis[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
				}else if(camposUltVis.length>4 && rep.getFecRen()==null && camposUltVis[5].trim().toUpperCase().equals("ENTREGADO")){
					celdaBody = new HSSFRichTextString(camposUltVis.length>2?camposUltVis[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
				}else if(camposUltVis.length>4 && rep.getFecRen()==null && camposUltVis[5].trim().toUpperCase().equals("PENDIENTE")){
					celdaBody = new HSSFRichTextString(camposUltVis.length>2?camposUltVis[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
				}else if(camposUltVis.length>4 && rep.getFecRen()==null && camposUltVis[5].trim().toUpperCase().equals("GESTION")){
					celdaBody = new HSSFRichTextString(camposUltVis.length>2?camposUltVis[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
				}else{
					celdaBody = new HSSFRichTextString(((rep.getFecRen()==null) ? "" : MVDinamico.getDateInFormat(rep.getFecRen(),"dd/MM/yyyy"))); celdaBody.applyFont(HSSFFont.BOLDWEIGHT_NORMAL); rowBody.createCell(++k).setCellValue(celdaBody);
				}*/
				celdaBody = new HSSFRichTextString((rep.getFecUltima()==null) ? "" : MVDinamico.getDateInFormat(rep.getFecUltima(),"dd/MM/yyyy")); rowBody.createCell(++k).setCellValue(celdaBody);	
				//TERMINA FECHA ULTIMA
				/*if(camposUltVis.length>4 && ( rep.getDesSit().toUpperCase().equals("PENDIENTE") && rep.getDesMot().equals("ENTREGADO EN AGENCIA"))){						
				//System.out.println("COD_BAR:"+rep.getCodBar()+" / camposUltVis[5]"+camposUltVis[5]+" / rep.getDesMot():"+rep.getDesMot());
					celdaBody = new HSSFRichTextString("EN REPARTO"); celdaBody.applyFont(HSSFFont.BOLDWEIGHT_NORMAL); rowBody.createCell(++k).setCellValue(celdaBody);
				}else if(camposUltVis.length>4 && (camposUltVis[1].equals(fechaHoy) && rep.getDesSit().toUpperCase().equals("PENDIENTE"))){
					
					celdaBody = new HSSFRichTextString("EN REPARTO"); celdaBody.applyFont(HSSFFont.BOLDWEIGHT_NORMAL); rowBody.createCell(++k).setCellValue(celdaBody);
				}else if(camposUltVis.length>4 && ( rep.getDesMot()==null && rep.getDesSit().toUpperCase().equals("GESTION"))){					
					celdaBody = new HSSFRichTextString("EN REPARTO"); celdaBody.applyFont(HSSFFont.BOLDWEIGHT_NORMAL); rowBody.createCell(++k).setCellValue(celdaBody);
				}else{
					celdaBody = new HSSFRichTextString(((rep.getDesMot()==null) ? "" : rep.getDesMot()+"")); celdaBody.applyFont(HSSFFont.BOLDWEIGHT_NORMAL); rowBody.createCell(++k).setCellValue(celdaBody);
				}*/
				
				//RESULTADO ULTIMO
				celdaBody = new HSSFRichTextString(((rep.getDesMot()==null) ? "" : rep.getDesMot()+"")); celdaBody.applyFont(HSSFFont.BOLDWEIGHT_NORMAL); rowBody.createCell(++k).setCellValue(celdaBody);
				//TERMINA RESULTADO ULTIMO
				
				/*LUGAR ULTIMO Y TIPO ENTREGA*/
				/*	
			 	if(rep.getDesMot().equals("ENTREGADO EN AGENCIA")){
					celdaBody = new HSSFRichTextString("Agencia"); celdaBody.applyFont(HSSFFont.BOLDWEIGHT_NORMAL); rowBody.createCell(++k).setCellValue(celdaBody);
					celdaBody = new HSSFRichTextString("Agencia"); celdaBody.applyFont(HSSFFont.BOLDWEIGHT_NORMAL); rowBody.createCell(++k).setCellValue(celdaBody);
				}else{
					celdaBody = new HSSFRichTextString(camposUltVis.length>2?camposUltVis[10]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					if(camposUltVis.length>9 && (camposUltVis[10].toUpperCase().equals("COORDINADA") || camposUltVis[10].toUpperCase().equals("DOMICILIO") || camposUltVis[10].toUpperCase().equals("LABORAL"))){
						celdaBody = new HSSFRichTextString("Cliente"); celdaBody.applyFont(HSSFFont.BOLDWEIGHT_NORMAL); rowBody.createCell(++k).setCellValue(celdaBody);
					}else{
						celdaBody = new HSSFRichTextString(""); celdaBody.applyFont(HSSFFont.BOLDWEIGHT_NORMAL); rowBody.createCell(++k).setCellValue(celdaBody);
					}

				}
				*/
				celdaBody = new HSSFRichTextString(rep.getLugarUltimo()); celdaBody.applyFont(HSSFFont.BOLDWEIGHT_NORMAL); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getTipoEntrega()); celdaBody.applyFont(HSSFFont.BOLDWEIGHT_NORMAL); rowBody.createCell(++k).setCellValue(celdaBody);

				/**TERMINA LUGAR ULTIMO Y TIPO ENTREGA**/
				//*FECHA ULTIMA VISITA , RESULTADO ULTIMA VISITA
				/*celdaBody = new HSSFRichTextString(camposUltVis.length>2?camposUltVis[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);				
				celdaBody = new HSSFRichTextString(((rep.getDesMot()==null) ? "" : rep.getDesMot()+"")); celdaBody.applyFont(HSSFFont.BOLDWEIGHT_NORMAL); rowBody.createCell(++k).setCellValue(celdaBody);
				*/
				celdaBody = new HSSFRichTextString((rep.getFecUltimaVisita()==null) ? "" : MVDinamico.getDateInFormat(rep.getFecUltimaVisita(),"dd/MM/yyyy")); celdaBody.applyFont(HSSFFont.BOLDWEIGHT_NORMAL); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getResUltimaVisita()); celdaBody.applyFont(HSSFFont.BOLDWEIGHT_NORMAL); rowBody.createCell(++k).setCellValue(celdaBody);
				/**************************/
				celdaBody = new HSSFRichTextString(rep.getResNroVis()+""); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getResNroCoo()+""); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getResNroLla()+""); rowBody.createCell(++k).setCellValue(celdaBody);
				
				//Visitas: "3300|15/04/2015|83|CESAR AUGUSTO HUAMANAS MARIN|1|Pendiente||||0|"	
				int faltantes = 0;		
				for(int v=cantVisita;v!=0;v--){
				//for(int v=1;v<=cantVisita;v++){
					if(v==1){
						String[] campos = (rep.getDetVisita1()!=null?rep.getDetVisita1():"").split("\\|",-1);
						/*
						System.out.print("v("+v+")"+rep.getCodBar()+"-");
						int x = 0;
						for(String str : campos){				
							System.out.print("("+x+")"+str +" ");
							x++;
						}
						System.out.println();
						*/
						//1=Fecha, 5=Resultado, 10=Tipo direccion
						if(rep.getDetVisita1()!=null){
							celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							/*
							celdaBody = new HSSFRichTextString(campos.length>2?campos[7]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							celdaBody = new HSSFRichTextString(campos.length>2?campos[10]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							*/
							celdaBody = new HSSFRichTextString(campos.length>2?campos[12]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							celdaBody = new HSSFRichTextString(campos.length>2?campos[13]:""); rowBody.createCell(++k).setCellValue(celdaBody);
/*GESTION VISITA -->*/		celdaBody = new HSSFRichTextString(campos.length>2?campos[11]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						}else{
							faltantes++;
						}
					}
					if(v==2){
						String[] campos = (rep.getDetVisita2()!=null?rep.getDetVisita2():"").split("\\|",-1);
						if(rep.getDetVisita2()!=null){
							celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							/*
							celdaBody = new HSSFRichTextString(campos.length>2?campos[7]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							celdaBody = new HSSFRichTextString(campos.length>2?campos[10]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							*/
							celdaBody = new HSSFRichTextString(campos.length>2?campos[12]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							celdaBody = new HSSFRichTextString(campos.length>2?campos[13]:""); rowBody.createCell(++k).setCellValue(celdaBody);
/*GESTION VISITA -->*/		celdaBody = new HSSFRichTextString(campos.length>2?campos[11]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						}else{
							faltantes++;
						}
					}
					if(v==3){
						String[] campos = (rep.getDetVisita3()!=null?rep.getDetVisita3():"").split("\\|",-1);
						if(rep.getDetVisita3()!=null){
							celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							/*
							celdaBody = new HSSFRichTextString(campos.length>2?campos[7]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							celdaBody = new HSSFRichTextString(campos.length>2?campos[10]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							*/
							celdaBody = new HSSFRichTextString(campos.length>2?campos[12]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							celdaBody = new HSSFRichTextString(campos.length>2?campos[13]:""); rowBody.createCell(++k).setCellValue(celdaBody);
/*GESTION VISITA -->*/		celdaBody = new HSSFRichTextString(campos.length>2?campos[11]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						}else{
							faltantes++;
						}
					}
					if(v==4){
						String[] campos = (rep.getDetVisita4()!=null?rep.getDetVisita4():"").split("\\|",-1);
						if(rep.getDetVisita4()!=null){
							celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							/*
							celdaBody = new HSSFRichTextString(campos.length>2?campos[7]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							celdaBody = new HSSFRichTextString(campos.length>2?campos[10]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							*/
							celdaBody = new HSSFRichTextString(campos.length>2?campos[12]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							celdaBody = new HSSFRichTextString(campos.length>2?campos[13]:""); rowBody.createCell(++k).setCellValue(celdaBody);
/*GESTION VISITA -->*/		celdaBody = new HSSFRichTextString(campos.length>2?campos[11]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						}else{
							faltantes++;
						}
					}
					if(v==5){
						String[] campos = (rep.getDetVisita5()!=null?rep.getDetVisita5():"").split("\\|",-1);
						if(rep.getDetVisita5()!=null){						
							celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							/*
							celdaBody = new HSSFRichTextString(campos.length>2?campos[7]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							celdaBody = new HSSFRichTextString(campos.length>2?campos[10]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							*/
							celdaBody = new HSSFRichTextString(campos.length>2?campos[12]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							celdaBody = new HSSFRichTextString(campos.length>2?campos[13]:""); rowBody.createCell(++k).setCellValue(celdaBody);
/*GESTION VISITA -->*/		celdaBody = new HSSFRichTextString(campos.length>2?campos[11]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						}else{
							faltantes++;
						}
					}
					if(v==6){
						String[] campos = (rep.getDetVisita6()!=null?rep.getDetVisita6():"").split("\\|",-1);
						if(rep.getDetVisita6()!=null){
							celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							/*
							celdaBody = new HSSFRichTextString(campos.length>2?campos[7]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							celdaBody = new HSSFRichTextString(campos.length>2?campos[10]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							*/
							celdaBody = new HSSFRichTextString(campos.length>2?campos[12]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							celdaBody = new HSSFRichTextString(campos.length>2?campos[13]:""); rowBody.createCell(++k).setCellValue(celdaBody);
/*GESTION VISITA -->*/		celdaBody = new HSSFRichTextString(campos.length>2?campos[11]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						}else{
							faltantes++;
						}
					}
					if(v==7){
						String[] campos = (rep.getDetVisita7()!=null?rep.getDetVisita7():"").split("\\|",-1);
						if(rep.getDetVisita7()!=null){
							celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							/*
							celdaBody = new HSSFRichTextString(campos.length>2?campos[7]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							celdaBody = new HSSFRichTextString(campos.length>2?campos[10]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							*/
							celdaBody = new HSSFRichTextString(campos.length>2?campos[12]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							celdaBody = new HSSFRichTextString(campos.length>2?campos[13]:""); rowBody.createCell(++k).setCellValue(celdaBody);
/*GESTION VISITA -->*/		celdaBody = new HSSFRichTextString(campos.length>2?campos[11]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						}else{
							faltantes++;
						}
					}
					if(v==8){
						String[] campos = (rep.getDetVisita8()!=null?rep.getDetVisita8():"").split("\\|",-1);
						if(rep.getDetVisita8()!=null){
							celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							/*
							celdaBody = new HSSFRichTextString(campos.length>2?campos[7]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							celdaBody = new HSSFRichTextString(campos.length>2?campos[10]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							*/
							celdaBody = new HSSFRichTextString(campos.length>2?campos[12]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							celdaBody = new HSSFRichTextString(campos.length>2?campos[13]:""); rowBody.createCell(++k).setCellValue(celdaBody);
/*GESTION VISITA -->*/		celdaBody = new HSSFRichTextString(campos.length>2?campos[11]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						}else{
							faltantes++;
						}
					}
					if(v==9){						
						String[] campos = (rep.getDetVisita9()!=null?rep.getDetVisita9():"").split("\\|",-1);
						if(rep.getDetVisita9()!=null){	
							celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							/*
							celdaBody = new HSSFRichTextString(campos.length>2?campos[7]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							celdaBody = new HSSFRichTextString(campos.length>2?campos[10]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							*/
							celdaBody = new HSSFRichTextString(campos.length>2?campos[12]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							celdaBody = new HSSFRichTextString(campos.length>2?campos[13]:""); rowBody.createCell(++k).setCellValue(celdaBody);
/*GESTION VISITA -->*/		celdaBody = new HSSFRichTextString(campos.length>2?campos[11]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						}else{
							faltantes++;
						}
					}
					if(v==10){
						String[] campos = (rep.getDetVisita10()!=null?rep.getDetVisita10():"").split("\\|",-1);
						if(rep.getDetVisita9()!=null){
							celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							/*
							celdaBody = new HSSFRichTextString(campos.length>2?campos[7]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							celdaBody = new HSSFRichTextString(campos.length>2?campos[10]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							*/
							celdaBody = new HSSFRichTextString(campos.length>2?campos[12]:""); rowBody.createCell(++k).setCellValue(celdaBody);
							celdaBody = new HSSFRichTextString(campos.length>2?campos[13]:""); rowBody.createCell(++k).setCellValue(celdaBody);
/*GESTION VISITA -->*/		celdaBody = new HSSFRichTextString(campos.length>2?campos[11]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						}else{
							faltantes++;
						}
					}
				
				}
				
				for(int z = 0; z<faltantes; z++){
					celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
					celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
					celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
					celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
				}
				//Coordinaciones : "21/04/2015|24/04/2015|1PM-4PM|CAL. AMADOR MERINO REYNA 551 SAN ISIDRO ALT CDRA 10 AV JAVIER PRADO||150131||Departamento Compras||||1|Destinatario|2|Laboral|1|Normal|2|Saliente"
				for(int c=1;c<=cantCoord;c++){
					if(c==1){
						String[] campos = (rep.getDetCoord1()!=null?rep.getDetCoord1():"").split("\\|",-1);
						//0=Fecha Reg, 1=Fecha Coordinada, 2=Hora Coordinada, 14=Tipo de Direccion
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[3]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(c==2){
						String[] campos = (rep.getDetCoord2()!=null?rep.getDetCoord2():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[14]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(c==3){
						String[] campos = (rep.getDetCoord3()!=null?rep.getDetCoord3():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[14]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(c==4){
						String[] campos = (rep.getDetCoord4()!=null?rep.getDetCoord4():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[14]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(c==5){
						String[] campos = (rep.getDetCoord5()!=null?rep.getDetCoord5():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[14]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(c==6){
						String[] campos = (rep.getDetCoord6()!=null?rep.getDetCoord6():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[14]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(c==7){
						String[] campos = (rep.getDetCoord7()!=null?rep.getDetCoord7():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[14]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(c==8){
						String[] campos = (rep.getDetCoord8()!=null?rep.getDetCoord8():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[14]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(c==9){
						String[] campos = (rep.getDetCoord9()!=null?rep.getDetCoord9():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[14]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					if(c==10){
						String[] campos = (rep.getDetCoord10()!=null?rep.getDetCoord10():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[14]:""); rowBody.createCell(++k).setCellValue(celdaBody);
					}
					
				}
				
				//Seguimiento telefonico : "20/04/2015|17:09:48|97506799|10|FUERA DE SERVICIO|.|0015|CARLOS ALCAZAR|1|Dataimagenes"
				for(int t=1;t<=cantCoordTelf;t++){
					if(t==1){
						String[] campos = (rep.getDetCoordTelf1()!=null?rep.getDetCoordTelf1():"").split("\\|",-1);
						//0=Fecha Reg, 1=Hora Coordinada, 2=Nro.Telf, 4=Resultado, 5=Observaciones, ?=Tipo Telf.
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);						
					}
					if(t==2){
						String[] campos = (rep.getDetCoordTelf2()!=null?rep.getDetCoordTelf2():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);						
					}
					if(t==3){
						String[] campos = (rep.getDetCoordTelf3()!=null?rep.getDetCoordTelf3():"").split("\\|",-1);
						//0=Fecha Reg, 1=Hora Coordinada, 2=Nro.Telf, 4=Resultado, 5=Observaciones, ?=Tipo Telf.
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);						
					}
					if(t==4){
						String[] campos = (rep.getDetCoordTelf4()!=null?rep.getDetCoordTelf4():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);						
					}
					if(t==5){
						String[] campos = (rep.getDetCoordTelf5()!=null?rep.getDetCoordTelf5():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);						
					}
					if(t==6){
						String[] campos = (rep.getDetCoordTelf6()!=null?rep.getDetCoordTelf6():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);					
					}
					if(t==7){
						String[] campos = (rep.getDetCoordTelf7()!=null?rep.getDetCoordTelf7():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);					
					}
					if(t==8){
						String[] campos = (rep.getDetCoordTelf8()!=null?rep.getDetCoordTelf8():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);						
					}
					if(t==9){
						String[] campos = (rep.getDetCoordTelf9()!=null?rep.getDetCoordTelf9():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);						
					}
					if(t==10){
						String[] campos = (rep.getDetCoordTelf10()!=null?rep.getDetCoordTelf10():"").split("\\|",-1);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[0]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[1]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[2]:""); rowBody.createCell(++k).setCellValue(celdaBody);
						celdaBody = new HSSFRichTextString(campos.length>2?campos[5]:""); rowBody.createCell(++k).setCellValue(celdaBody);						
					}
				}			
							
			}
			File file = new File(path,fileName);

			FileOutputStream fout = new FileOutputStream(file);

			wb.write(fout);
			fout.flush();
			fout.close();
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
		}
	}
	
	public void createExcelReporteValesGNB(String path, String fileName, List<ReporteGNB> listaVisita, OutputStream output){
		try{
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	        Date today = Calendar.getInstance().getTime();   
	        String fechaHoy = df.format(today);
			
			//String fechaHoy = "24/09/2016";	
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			HSSFRow row = sheet.createRow((short)0);
			HSSFRichTextString celdaHead;
			int h=-1;
			celdaHead = new HSSFRichTextString("FECHA INGRESO"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("CARGO"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("INSTITUCION"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("DOCUMENTO"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("NRO_TDC"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("APELL_NOM_CLIENTE"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("CERTIFICADO"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("TIPO_VALE"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("COD_VALE"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("PUNTAJE	"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("FECHA_ENTREGA"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("FECHA_ACTUALIZACION_TABLA"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("CERTIFICADO"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("DIRECCION"); row.createCell(++h).setCellValue(celdaHead);		
			celdaHead = new HSSFRichTextString("DISTRITO"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("PROVINCIA"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("DEPARTAMENTO"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("MOVIL"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("DOCUMENTO_RECEPTOR"); celdaHead.applyFont(HSSFFont.BOLDWEIGHT_BOLD); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("NOMBRE_RECEPTOR"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("EMAIL"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("LIFEMILES"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("UBICACION_ACTUAL"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("FECHA_ULTIMA"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("RESULTADO_ULTIMO"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("RESULTADO_ULTIMA_VISITA"); row.createCell(++h).setCellValue(celdaHead);
			ReporteGNB rep=null;
			for(int i=0;i<listaVisita.size(); i++)
			{
			    rep=listaVisita.get(i);       
			    HSSFRow rowBody = sheet.createRow((int)(i+1));
			    int k=-1;
			    HSSFRichTextString celdaBody;
			    celdaBody = new HSSFRichTextString(rep.getFecha_ingreso()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCargo()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getInstitucion()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getDocumento()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getNro_tdc()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getNombres_apellidos()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCertificado_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getTipo_vale()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCodigo_vale()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getPuntaje()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getFecha_entrega()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getFecha_actualizacion_tabla()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getCertificado_2()); rowBody.createCell(++k).setCellValue(celdaBody);//FECHA DESPACHO HARCODEADO 
				celdaBody = new HSSFRichTextString(rep.getDireccion()); rowBody.createCell(++k).setCellValue(celdaBody);				
				celdaBody = new HSSFRichTextString(rep.getDistrito()); rowBody.createCell(++k).setCellValue(celdaBody);			
				celdaBody = new HSSFRichTextString(rep.getProvincia()); rowBody.createCell(++k).setCellValue(celdaBody);		
				celdaBody = new HSSFRichTextString(rep.getDepartamento()); rowBody.createCell(++k).setCellValue(celdaBody);		
				celdaBody = new HSSFRichTextString(rep.getTelofono_movil()); rowBody.createCell(++k).setCellValue(celdaBody);	
				celdaBody = new HSSFRichTextString(rep.getDocumento_receptor()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getNombre_receptor()); rowBody.createCell(++k).setCellValue(celdaBody);			
				celdaBody = new HSSFRichTextString(rep.getEmail()); rowBody.createCell(++k).setCellValue(celdaBody);	
				celdaBody = new HSSFRichTextString(rep.getLifemiles()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getUbicacion_actual()); rowBody.createCell(++k).setCellValue(celdaBody);	
				celdaBody = new HSSFRichTextString(rep.getFecha_ultima()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getResultado_ultimo()); rowBody.createCell(++k).setCellValue(celdaBody);
				celdaBody = new HSSFRichTextString(rep.getResultado_ultima_visita()); rowBody.createCell(++k).setCellValue(celdaBody);			
			}
			//File file = new File(path,fileName);
			wb.write(output);
			output.flush();
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void createExcelReporteBCPtc(String path, String fileName, List<ReporteBCPtc> listaVisita, OutputStream output){
		try{
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	        Date today = Calendar.getInstance().getTime();   
	        String fechaHoy = df.format(today);
			
			//String fechaHoy = "29/11/2016";	
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			HSSFRow row = sheet.createRow((int)0);
			HSSFRichTextString celdaHead;
			int h=-1;
			celdaHead = new HSSFRichTextString("vid"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_de_proceso"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_de_grabacion"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_de_recepcion"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctipo_emision"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cid_bin"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cid_marca_del_producto"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cnombre_del_producto"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("csegmento"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccanal_venta"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccodigo_seguimiento"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cnombre_titular"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cnombre_tarjetahabiente"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctipo_de_tarjeta"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cidc_del_cliente"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdir_domicilio"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cprovincia_domicilio"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdireccion_trabajo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cprovincia_trabajo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cid_codigo_zip"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cnumero_acuse"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cnombre_mandatario"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctipo_doc_mandatario_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cnum_doc_mandatario_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cid_mensajeria"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("crequiere_contrato"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("crequiere_recibo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctipo_de_entrega"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdirecci�n_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cprovincia_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdirecci�n_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cprovincia_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdirecci�n_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cprovincia_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctel�fono_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctel�fono_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctel�fono_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctel�fono_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctel�fono_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdias_transcurridos"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cestado_final"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdetalle_de_estado"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("crecepcion"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccodigo_de_estado"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdireccion_de_ultima_gestion"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctipo_direccion"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_entrega_ultima_gestion"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdias_de_entrega"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccantidad_de_visitas"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cvisita_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("chora_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cestado_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdetalle_de_estado_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cvisita_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("chora_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cestado_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdetalle_de_estado_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cvisita_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("chora_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cestado_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdetalle_de_estado_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cvisita_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("chora_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cestado_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdetalle_de_estado_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cvisita_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("chora_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cestado_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdetalle_de_estado_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cultimo_motivo_de_rezago"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cnumero_de_coordinaciones_realizadas"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cnumero_de_coordinaciones_cumplidas"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cnumero_de_coordinaciones_incumplidas"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccoordinacion_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccoordinacion_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccoordinacion_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccoordinacion_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccoordinacion_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cambito"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccargo"); row.createCell(++h).setCellValue(celdaHead);

			ReporteBCPtc rep=null;
			for(int i=0;i<listaVisita.size(); i++)
			{
			    rep=listaVisita.get(i);       
			    HSSFRow rowBody = sheet.createRow((int)(i+1));
			    int k=-1;
			    HSSFRichTextString celdaBody;
			    celdaBody = new HSSFRichTextString(rep.getVid()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_de_proceso()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_de_grabacion()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_de_recepcion()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_emision()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCid_bin()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCid_marca_del_producto()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCnombre_del_producto()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCsegmento()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcanal_venta()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcodigo_seguimiento()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCnombre_titular()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCnombre_tarjetahabiente()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_de_tarjeta()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCidc_del_cliente()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdir_domicilio()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCprovincia_domicilio()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdireccion_trabajo()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCprovincia_trabajo()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCid_codigo_zip()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCnumero_acuse()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCnombre_mandatario()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_doc_mandatario_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCnum_doc_mandatario_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCid_mensajeria()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCrequiere_contrato()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCrequiere_recibo()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_de_entrega()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdirecci�n_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCprovincia_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdirecci�n_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCprovincia_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdirecci�n_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCprovincia_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtel�fono_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtel�fono_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtel�fono_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtel�fono_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtel�fono_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdias_transcurridos()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCestado_final()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetalle_de_estado()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCrecepcion()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcodigo_de_estado()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdireccion_de_ultima_gestion()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_direccion()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_entrega_ultima_gestion()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdias_de_entrega()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcantidad_de_visitas()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCvisita_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChora_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCestado_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetalle_de_estado_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCvisita_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChora_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCestado_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetalle_de_estado_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCvisita_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChora_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCestado_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetalle_de_estado_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCvisita_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChora_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCestado_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetalle_de_estado_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCvisita_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChora_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCestado_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetalle_de_estado_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCultimo_motivo_de_rezago()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCnumero_de_coordinaciones_realizadas()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCnumero_de_coordinaciones_cumplidas()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCnumero_de_coordinaciones_incumplidas()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcoordinacion_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcoordinacion_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcoordinacion_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcoordinacion_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcoordinacion_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCambito()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcargo()); rowBody.createCell(++k).setCellValue(celdaBody);


			}
			//File file = new File(path,fileName);
			wb.write(output);
			output.flush();
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void createExcelReporteHerbalife(String path, String fileName, List<ReporteHerbalife> listaVisita, OutputStream output){
		try{
			java.util.Date d =new Date(); //CP
		    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");  //Cp
		    
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	        Date today = Calendar.getInstance().getTime();   
	        String fechaHoy = df.format(today);
			
			//String fechaHoy = "29/11/2016";	
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			
			
			HSSFRow row = sheet.createRow((short)0);
			
			HSSFCell celda = row.createCell(1); //CP
			
			HSSFRichTextString celdaHead;
			int h=-1;
			
			celdaHead = new HSSFRichTextString("cmes"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_proceso"); row.createCell(++h).setCellValue(celdaHead);
				
			celdaHead = new HSSFRichTextString("ccod_bar"); row.createCell(++h).setCellValue(celdaHead);

			celdaHead = new HSSFRichTextString("cval_nro_ide"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctit_nom_ape"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdir_domi_des "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdestino"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccajas"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cpeso "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctelefono "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cstatus "); row.createCell(++h).setCellValue(celdaHead);
		
			celdaHead = new HSSFRichTextString("cfech_entr_ult_ges"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccant_visita"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cvisita1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdeta_esta1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cvisita2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdeta_esta2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cvisita3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdeta_esta3"); row.createCell(++h).setCellValue(celdaHead);
			
			celdaHead = new HSSFRichTextString("cnum_coord"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccoordinacion1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccoordinacion2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccoordinacion3"); row.createCell(++h).setCellValue(celdaHead);
			
			  
			


			ReporteHerbalife rep=null;
			for(int i=0;i<listaVisita.size(); i++)
			{
			    rep=listaVisita.get(i);       
			    HSSFRow rowBody = sheet.createRow((int)(i+1));
			    int k=-1;
			    HSSFRichTextString celdaBody;

			    celdaBody = new HSSFRichTextString(rep.getCmes()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_proceso()); rowBody.createCell(++k).setCellValue(celdaBody);
			    
  
			    celdaBody = new HSSFRichTextString(rep.getCcod_bar()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCval_nro_ide()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtit_nom_ape()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdir_domi_des()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdestino()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcajas()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCpeso ()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtelefono ()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCstatus ()); rowBody.createCell(++k).setCellValue(celdaBody);
			    
			   celdaBody = new HSSFRichTextString(rep.getCfech_entr_ult_ges()); rowBody.createCell(++k).setCellValue(celdaBody);		   
			    
			    
			    celdaBody = new HSSFRichTextString(rep.getCcant_visita()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCvisita1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdeta_esta1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCvisita2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdeta_esta2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCvisita3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdeta_esta3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCnum_coord()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcoordinacion1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcoordinacion2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcoordinacion3()); rowBody.createCell(++k).setCellValue(celdaBody);
			
			}
			//File file = new File(path,fileName);
			wb.write(output);
			output.flush();
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void createExcelReporteBBVApend(String path, String fileName, List<ReporteBBVApend> listaVisita, OutputStream output){
		try{
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	        Date today = Calendar.getInstance().getTime();   
	        String fechaHoy = df.format(today);
			
			//String fechaHoy = "29/11/2016";	
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			HSSFRow row = sheet.createRow((short)0);
			HSSFRichTextString celdaHead;
			int h=-1;
			celdaHead = new HSSFRichTextString("cval_nro_ide"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctit_nom_ape"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccodclitit"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctit_num_doc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cval_fec_emi"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfec_pro"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cnroctt"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("clocalidad"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccod_mot"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cindval"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cindtjtbco"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cofiges"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctiptjttit"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccodref"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cnrorcc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccodresul1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccodresul2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cval_tip"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctip_carpoder"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cbascodubi"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("caltcodubi"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccodmot1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccodmot2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccodmot3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccodmot4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccodmot5"); row.createCell(++h).setCellValue(celdaHead);


			ReporteBBVApend rep=null;
			for(int i=0;i<listaVisita.size(); i++)
			{
			    rep=listaVisita.get(i);       
			    HSSFRow rowBody = sheet.createRow((int)(i+1));
			    int k=-1;
			    HSSFRichTextString celdaBody;

			    celdaBody = new HSSFRichTextString(rep.getCval_nro_ide()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtit_nom_ape()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcodclitit()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtit_num_doc()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCval_fec_emi()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfec_pro()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCnroctt()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClocalidad()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcod_mot()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCindval()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCindtjtbco()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCofiges()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtiptjttit()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcodref()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCnrorcc()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcodresul1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcodresul2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCval_tip()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtip_carpoder()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCbascodubi()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCaltcodubi()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcodmot1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcodmot2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcodmot3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcodmot4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcodmot5()); rowBody.createCell(++k).setCellValue(celdaBody);



			}
			//File file = new File(path,fileName);
			wb.write(output);
			output.flush();
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void createExcelReporteBBVAentr(String path, String fileName, List<ReporteBBVAentr> listaVisita, OutputStream output){
		try{
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	        Date today = Calendar.getInstance().getTime();   
	        String fechaHoy = df.format(today);
			
			//String fechaHoy = "29/11/2016";	
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			HSSFRow row = sheet.createRow((short)0);
			HSSFRichTextString celdaHead;
			int h=-1;
		
			celdaHead = new HSSFRichTextString("cval_nro_ide"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctit_nom_ape"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccodclitit"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctit_num_doc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cval_fec_emi"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfec_pro"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfech_entr_ult_ges"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cnroctt"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("clocalidad"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccodigo_situacion"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cind_act"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cindval"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cindtjtbco"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("clugarentr"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cofiges"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctiptjttit"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccodref"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cnrorcc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cval_tip"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("chora_ult"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccodresul1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccodresul2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctip_carpoder"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cindfast"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cbascodubi"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("caltcodubi"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cnroval"); row.createCell(++h).setCellValue(celdaHead);



			ReporteBBVAentr rep=null;
			for(int i=0;i<listaVisita.size(); i++)
			{
			    rep=listaVisita.get(i);       
			    HSSFRow rowBody = sheet.createRow((int)(i+1));
			    int k=-1;
			    HSSFRichTextString celdaBody;

			    celdaBody = new HSSFRichTextString(rep.getCval_nro_ide()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtit_nom_ape()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcodclitit()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtit_num_doc()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCval_fec_emi()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfec_pro()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfech_entr_ult_ges()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCnroctt()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClocalidad()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcodigo_situacion()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCind_act()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCindval()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCindtjtbco()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugarentr()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCofiges()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtiptjttit()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcodref()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCnrorcc()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCval_tip()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChora_ult()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcodresul1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcodresul2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtip_carpoder()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCindfast()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCbascodubi()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCaltcodubi()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCnroval()); rowBody.createCell(++k).setCellValue(celdaBody);

			}
			//File file = new File(path,fileName);
			wb.write(output);
			output.flush();
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	public void createExcelReporteBBVAworkf(String path, String fileName, List<ReporteBBVAworkf> listaVisita, OutputStream output){
		try{
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	        Date today = Calendar.getInstance().getTime();   
	        String fechaHoy = df.format(today);
			
			//String fechaHoy = "29/11/2016";	
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			HSSFRow row = sheet.createRow((short)0);
			HSSFRichTextString celdaHead;
			int h=-1;
		
			celdaHead = new HSSFRichTextString("cval_nro_ide"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctit_nom_ape"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccodclitit"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctit_num_doc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cval_fec_emi"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfec_ultima_visita"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cnroctt"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfec_pro"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("clocalidad"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccodigo_situacion"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdetsit"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cindsit"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cindval"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cindtjtbco"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("clugarentr"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cofiges"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctiptjttit"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccodref"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cnrorcc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cval_tip"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("chor_vis"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctip_carpoder"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cindfast1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cbascodubi"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("caltcodubi"); row.createCell(++h).setCellValue(celdaHead);


			ReporteBBVAworkf rep=null;
			for(int i=0;i<listaVisita.size(); i++)
			{
			    rep=listaVisita.get(i);       
			    HSSFRow rowBody = sheet.createRow((int)(i+1));
			    int k=-1;
			    HSSFRichTextString celdaBody;

			    celdaBody = new HSSFRichTextString(rep.getCval_nro_ide()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtit_nom_ape()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcodclitit()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtit_num_doc()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCval_fec_emi()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfec_ultima_visita()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCnroctt()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClocalidad()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcodigo_situacion()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetsit()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCindsit()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCindval()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCindtjtbco()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugarentr()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCofiges()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtiptjttit()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcodref()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCnrorcc()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCval_tip()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChor_vis()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtip_carpoder()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCindfast1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCbascodubi()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCaltcodubi()); rowBody.createCell(++k).setCellValue(celdaBody);

			}
			//File file = new File(path,fileName);
			wb.write(output);
			output.flush();
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	
	
	public void createExcelReporteGNBvales(String path, String fileName, List<ReporteGNBvales> listaVisita, OutputStream output){
		try{
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	        Date today = Calendar.getInstance().getTime();   
	        String fechaHoy = df.format(today);
			
			//String fechaHoy = "29/11/2016";	
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			HSSFRow row = sheet.createRow((short)0);
			HSSFRichTextString celdaHead;
			int h=-1;
			celdaHead = new HSSFRichTextString("cfecha_ingreso"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccargo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cinstitucion"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdocumento"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cnro_tdc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("capell_nom_cliente"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccertificadoo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctipo_vale"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccod_vale"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cpuntaje"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_entrega"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_actualizacion_tabla"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccertificado"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdireccion"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdistrito"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cprovincia "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdepartamento"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cmovil"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdocumento_receptor"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cnombre_receptor"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cemail"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("clifemiles"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cubicacion_actual"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_ultima"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresultado_ultimo"); row.createCell(++h).setCellValue(celdaHead);



			ReporteGNBvales rep=null;
			for(int i=0;i<listaVisita.size(); i++)
			{
			    rep=listaVisita.get(i);       
			    HSSFRow rowBody = sheet.createRow((int)(i+1));
			    int k=-1;
			    HSSFRichTextString celdaBody;
			    celdaBody = new HSSFRichTextString(rep.getCfecha_ingreso()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcargo()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCinstitucion()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdocumento()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCnro_tdc()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCapell_nom_cliente()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcertificadoo()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_vale()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcod_vale()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCpuntaje()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_entrega()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_actualizacion_tabla()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcertificado()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdireccion()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdistrito()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCprovincia ()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdepartamento()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCmovil()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdocumento_receptor()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCnombre_receptor()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCemail()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClifemiles()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCubicacion_actual()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_ultima()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_ultimo()); rowBody.createCell(++k).setCellValue(celdaBody);




			}
			//File file = new File(path,fileName);
			wb.write(output);
			output.flush();
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void createExcelReporteGNBtoken(String path, String fileName, List<ReporteGNBtoken> listaVisita, OutputStream output){
		try{
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	        Date today = Calendar.getInstance().getTime();   
	        String fechaHoy = df.format(today);
			
			//String fechaHoy = "29/11/2016";	
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			HSSFRow row = sheet.createRow((short)0);
			HSSFRichTextString celdaHead;
			int h=-1;
			celdaHead = new HSSFRichTextString("ccod_bar "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cord_pro "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cval_nro_ide "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctit_nom_ape "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctit_num_doc "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctit_tlf_dom "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctit_tlf_lab "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdir_dom_des "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cubigeo "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdistrito "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cprovincia "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdepartamento "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfec_pro"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdes_pro_val "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cval_des_emi "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdes_sit "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfec_ultima "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cres_ultima_visita "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdeta_ult "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctipo_entrega "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfec_ultima_visita "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdetamot0 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccant_visita "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccant_coord "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccant_segui "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cvisita1 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresuvis1 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("clugavis1 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cgestvis1 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cvisita2 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresuvis2 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("clugavis2 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cgestvis2 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cvisita3 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresuvis3 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("clugavis3 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cgestvis3 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cvisita4 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresuvis4 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("clugavis4 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cgestvis4 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cvisita5 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresuvis5 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("clugavis5 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cgestvis5 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cvisita6 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresuvis6 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("clugavis6 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cgestvis6 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cvisita7 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresuvis7 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("clugavis7 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cgestvis7 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cvisita8 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresuvis8 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("clugavis8 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cgestvis8 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccoordina1 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("choracoor1 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecregco1 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdircoor1 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cindircoor1 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccoordina2 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("choracoor2 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecregco2 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdircoor2 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cindircoor2 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccoordina3 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("choracoor3 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecregco3 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdircoor3 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cindircoor3 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cseguimiento1 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("choraseg1 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cteleseg1 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdetaseg1 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresulseg1 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cseguimiento2 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("choraseg2 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cteleseg2 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdetaseg2 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresulseg2 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cseguimiento3 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("choraseg3 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cteleseg3 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdetaseg3 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresulseg3 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cseguimiento4 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("choraseg4 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cteleseg4 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdetaseg4 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresulseg4 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cseguimiento5 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("choraseg5 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cteleseg5 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdetaseg5 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresulseg5 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cseguimiento6 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("choraseg6 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cteleseg6 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdetaseg6 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresulseg6 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cseguimiento7 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("choraseg7 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cteleseg7 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdetaseg7 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresulseg7 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cseguimiento8 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("choraseg8 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cteleseg8 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdetaseg8 "); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresulseg8 "); row.createCell(++h).setCellValue(celdaHead);





			ReporteGNBtoken rep=null;
			for(int i=0;i<listaVisita.size(); i++)
			{
			    rep=listaVisita.get(i);       
			    HSSFRow rowBody = sheet.createRow((int)(i+1));
			    int k=-1;
			    HSSFRichTextString celdaBody;
			    celdaBody = new HSSFRichTextString(rep.getCcod_bar()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCord_pro()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCval_nro_ide()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtit_nom_ape()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtit_num_doc()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtit_tlf_dom()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtit_tlf_lab()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdir_dom_des()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCubigeo()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdistrito()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCprovincia()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdepartamento()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfec_pro()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdes_pro_val()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCval_des_emi()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdes_sit()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfec_ultima()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCres_ultima_visita()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdeta_ult()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_entrega()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfec_ultima_visita()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetamot0()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcant_visita()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcant_coord()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcant_segui()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCvisita1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresuvis1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugavis1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCgestvis1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCvisita2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresuvis2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugavis2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCgestvis2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCvisita3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresuvis3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugavis3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCgestvis3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCvisita4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresuvis4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugavis4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCgestvis4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCvisita5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresuvis5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugavis5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCgestvis5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCvisita6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresuvis6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugavis6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCgestvis6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCvisita7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresuvis7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugavis7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCgestvis7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCvisita8()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresuvis8()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugavis8()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCgestvis8()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcoordina1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChoracoor1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecregco1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdircoor1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCindircoor1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcoordina2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChoracoor2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecregco2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdircoor2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCindircoor2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcoordina3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChoracoor3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecregco3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdircoor3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCindircoor3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCseguimiento1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChoraseg1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCteleseg1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetaseg1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresulseg1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCseguimiento2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChoraseg2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCteleseg2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetaseg2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresulseg2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCseguimiento3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChoraseg3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCteleseg3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetaseg3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresulseg3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCseguimiento4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChoraseg4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCteleseg4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetaseg4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresulseg4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCseguimiento5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChoraseg5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCteleseg5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetaseg5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresulseg5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCseguimiento6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChoraseg6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCteleseg6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetaseg6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresulseg6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCseguimiento7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChoraseg7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCteleseg7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetaseg7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresulseg7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCseguimiento8()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChoraseg8()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCteleseg8()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetaseg8()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresulseg8()); rowBody.createCell(++k).setCellValue(celdaBody);





			}
			//File file = new File(path,fileName);
			wb.write(output);
			output.flush();
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void createExcelReporteBCPdetalleGen(String path, String fileName, List<ReporteBCPdetalleGen > listaVisita, OutputStream output){
		try{
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	        Date today = Calendar.getInstance().getTime();   
	        String fechaHoy = df.format(today);
			
			//String fechaHoy = "29/11/2016";	
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			HSSFRow row = sheet.createRow((short)0);
			HSSFRichTextString celdaHead;
			int h=-1;

			celdaHead = new HSSFRichTextString("cnroman"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccodigo_seguimiento"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresultado"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdetalle_resultado"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdestino"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_ultima_coor"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cprov_domicilio"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cprov_trabajo"); row.createCell(++h).setCellValue(celdaHead);


			ReporteBCPdetalleGen rep=null;
			for(int i=0;i<listaVisita.size(); i++)
			{
			    rep=listaVisita.get(i);       
			    HSSFRow rowBody = sheet.createRow((int)(i+1));
			    int k=-1;
			    HSSFRichTextString celdaBody;
			    
			    celdaBody = new HSSFRichTextString(rep.getCnroman()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcodigo_seguimiento()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetalle_resultado()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdestino()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_ultima_coor()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCprov_domicilio()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCprov_trabajo()); rowBody.createCell(++k).setCellValue(celdaBody);


			}
			//File file = new File(path,fileName);
			wb.write(output);
			output.flush();
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void createExcelReporteOrbis(String path, String fileName, List<ReporteOrbinGestion > listaVisita, OutputStream output){
		try{
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	        Date today = Calendar.getInstance().getTime();   
	        String fechaHoy = df.format(today);
			
			//String fechaHoy = "29/11/2016";	
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			HSSFRow row = sheet.createRow((short)0);
			HSSFRichTextString celdaHead;
			int h=-1;

			celdaHead = new HSSFRichTextString("cargo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrorcc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("pieza"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("destinatario"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("doc_identidad"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telefono"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("dir_basica"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("dir_bas_ubigeo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("dir_bas_distrito"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("dir_bas_prov"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("dir_bas_depa"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_recepcion"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_documento"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_servicio"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_emision"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ubicacion_actual"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("desmotcli"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_ultima"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_ultimo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("lugar_ultimo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_entrega"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_ultima_visita"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_ultima_visita"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cant_vis"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cant_coo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cant_seg"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_visita_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_visita_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("lugar_visita_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("gestion_visita_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_visita_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_visita_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("lugar_visita_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("gestion_visita_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_visita_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_visita_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("lugar_visita_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("gestion_visita_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_visita_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_visita_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("lugar_visita_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("gestion_visita_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_visita_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_visita_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("lugar_visita_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("gestion_visita_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_visita_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_visita_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("lugar_visita_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("gestion_visita_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_visita_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_visita_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("lugar_visita_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("gestion_visita_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_visita_8"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_visita_8"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("lugar_visita_8"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("gestion_visita_8"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_coo_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("hor_coo_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_reg_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion_coo_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ind_coo_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_coo_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("hor_coo_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_reg_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion_coo_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ind_coo_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_coo_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("hor_coo_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_reg_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion_coo_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ind_coo_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_seg_tlf_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("hor_seg_tlf_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telef_seg_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("detalle_seg_tlf_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_seg_tlf_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_tlf_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_seg_tlf_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("hor_seg_tlf_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telef_seg_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("detalle_seg_tlf_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_seg_tlf_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_tlf_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_seg_tlf_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("hor_seg_tlf_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telef_seg_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("detalle_seg_tlf_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_seg_tlf_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_tlf_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_seg_tlf_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("hor_seg_tlf_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telef_seg_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("detalle_seg_tlf_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_seg_tlf_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_tlf_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_seg_tlf_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("hor_seg_tlf_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telef_seg_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("detalle_seg_tlf_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_seg_tlf_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_tlf_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_seg_tlf_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("hor_seg_tlf_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telef_seg_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("detalle_seg_tlf_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_seg_tlf_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_tlf_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_seg_tlf_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("hor_seg_tlf_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telef_seg_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("detalle_seg_tlf_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_seg_tlf_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_tlf_7"); row.createCell(++h).setCellValue(celdaHead);


			ReporteOrbinGestion rep=null;
			for(int i=0;i<listaVisita.size(); i++)
			{
			    rep=listaVisita.get(i);       
			    HSSFRow rowBody = sheet.createRow((int)(i+1));
			    int k=-1;
			    HSSFRichTextString celdaBody;
			    
			    celdaBody = new HSSFRichTextString(rep.getCcargo()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCnrorcc()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCpieza()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdestinatario()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdoc_identidad()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtelefono()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdir_basica()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdir_bas_ubigeo()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdir_bas_distrito()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdir_bas_prov()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdir_bas_depa()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_recepcion()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_documento()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_servicio()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_emision()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCubicacion_actual()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdesmotcli()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_ultima()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_ultimo()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugar_ultimo()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_entrega()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_ultima_visita()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_ultima_visita()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcant_vis()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcant_coo()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcant_seg()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_visita_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_visita_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugar_visita_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCgestion_visita_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_visita_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_visita_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugar_visita_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCgestion_visita_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_visita_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_visita_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugar_visita_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCgestion_visita_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_visita_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_visita_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugar_visita_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCgestion_visita_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_visita_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_visita_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugar_visita_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCgestion_visita_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_visita_6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_visita_6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugar_visita_6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCgestion_visita_6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_visita_7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_visita_7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugar_visita_7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCgestion_visita_7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_visita_8()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_visita_8()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugar_visita_8()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCgestion_visita_8()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_coo_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChor_coo_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_reg_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdireccion_coo_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCind_coo_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_coo_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChor_coo_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_reg_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdireccion_coo_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCind_coo_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_coo_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChor_coo_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_reg_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdireccion_coo_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCind_coo_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_seg_tlf_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChor_seg_tlf_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtelef_seg_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetalle_seg_tlf_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_seg_tlf_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_tlf_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_seg_tlf_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChor_seg_tlf_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtelef_seg_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetalle_seg_tlf_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_seg_tlf_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_tlf_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_seg_tlf_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChor_seg_tlf_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtelef_seg_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetalle_seg_tlf_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_seg_tlf_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_tlf_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_seg_tlf_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChor_seg_tlf_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtelef_seg_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetalle_seg_tlf_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_seg_tlf_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_tlf_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_seg_tlf_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChor_seg_tlf_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtelef_seg_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetalle_seg_tlf_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_seg_tlf_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_tlf_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_seg_tlf_6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChor_seg_tlf_6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtelef_seg_6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetalle_seg_tlf_6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_seg_tlf_6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_tlf_6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_seg_tlf_7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChor_seg_tlf_7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtelef_seg_7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetalle_seg_tlf_7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_seg_tlf_7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_tlf_7()); rowBody.createCell(++k).setCellValue(celdaBody);


			}
			//File file = new File(path,fileName);
			wb.write(output);
			output.flush();
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void createExcelReporteRevistas(String path, String fileName, List<ReporteRevistas > listaVisita, OutputStream output){
		try{
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	        Date today = Calendar.getInstance().getTime();   
	        String fechaHoy = df.format(today);
			
			//String fechaHoy = "29/11/2016";	
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			HSSFRow row = sheet.createRow((short)0);
			HSSFRichTextString celdaHead;
			int h=-1;

			celdaHead = new HSSFRichTextString("ccargo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cnrorcc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cpieza"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdestinatario"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdir_basica"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdir_bas_ubigeo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdir_bas_distrito"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdir_bas_prov"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdir_bas_depa"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_recepcion"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctipo_documento"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctipo_servicio"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctipo_emision"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cubicacion_actual"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_ultima"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresultado_ultimo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("clugar_ultimo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctipo_entrega"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_ultima_visita"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresultado_ultima_visita"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccant_vis"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccant_coo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccant_seg"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_visita_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresultado_visita_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("clugar_visita_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cgestion_visita_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_visita_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresultado_visita_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("clugar_visita_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cgestion_visita_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_visita_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresultado_visita_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("clugar_visita_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cgestion_visita_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_visita_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresultado_visita_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("clugar_visita_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cgestion_visita_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_visita_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresultado_visita_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("clugar_visita_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cgestion_visita_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_visita_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresultado_visita_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("clugar_visita_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cgestion_visita_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_visita_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresultado_visita_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("clugar_visita_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cgestion_visita_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_visita_8"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresultado_visita_8"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("clugar_visita_8"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cgestion_visita_8"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_coo_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("chor_coo_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_reg_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdireccion_coo_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cind_coo_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_coo_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("chor_coo_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_reg_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdireccion_coo_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cind_coo_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_coo_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("chor_coo_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_reg_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdireccion_coo_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cind_coo_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_seg_tlf_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("chor_seg_tlf_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctelef_seg_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdetalle_seg_tlf_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresultado_seg_tlf_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctipo_tlf_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_seg_tlf_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("chor_seg_tlf_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctelef_seg_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdetalle_seg_tlf_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresultado_seg_tlf_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctipo_tlf_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_seg_tlf_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("chor_seg_tlf_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctelef_seg_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdetalle_seg_tlf_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresultado_seg_tlf_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctipo_tlf_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_seg_tlf_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("chor_seg_tlf_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctelef_seg_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdetalle_seg_tlf_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresultado_seg_tlf_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctipo_tlf_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_seg_tlf_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("chor_seg_tlf_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctelef_seg_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdetalle_seg_tlf_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresultado_seg_tlf_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctipo_tlf_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_seg_tlf_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("chor_seg_tlf_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctelef_seg_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdetalle_seg_tlf_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresultado_seg_tlf_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctipo_tlf_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_seg_tlf_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("chor_seg_tlf_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctelef_seg_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdetalle_seg_tlf_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresultado_seg_tlf_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctipo_tlf_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_seg_tlf_8"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("chor_seg_tlf_8"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctelef_seg_8"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cdetalle_seg_tlf_8"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cresultado_seg_tlf_8"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ctipo_tlf_8"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ccodimp"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("crevista"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cnro_edicion"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cfecha_publica"); row.createCell(++h).setCellValue(celdaHead);



			ReporteRevistas rep=null;
			for(int i=0;i<listaVisita.size(); i++)
			{
			    rep=listaVisita.get(i);       
			    HSSFRow rowBody = sheet.createRow((int)(i+1));
			    int k=-1;
			    HSSFRichTextString celdaBody;
			    
			    celdaBody = new HSSFRichTextString(rep.getCcargo()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCnrorcc()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCpieza()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdestinatario()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdir_basica()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdir_bas_ubigeo()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdir_bas_distrito()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdir_bas_prov()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdir_bas_depa()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_recepcion()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_documento()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_servicio()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_emision()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCubicacion_actual()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_ultima()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_ultimo()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugar_ultimo()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_entrega()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_ultima_visita()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_ultima_visita()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcant_vis()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcant_coo()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcant_seg()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_visita_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_visita_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugar_visita_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCgestion_visita_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_visita_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_visita_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugar_visita_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCgestion_visita_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_visita_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_visita_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugar_visita_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCgestion_visita_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_visita_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_visita_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugar_visita_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCgestion_visita_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_visita_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_visita_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugar_visita_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCgestion_visita_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_visita_6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_visita_6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugar_visita_6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCgestion_visita_6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_visita_7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_visita_7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugar_visita_7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCgestion_visita_7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_visita_8()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_visita_8()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugar_visita_8()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCgestion_visita_8()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_coo_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChor_coo_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_reg_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdireccion_coo_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCind_coo_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_coo_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChor_coo_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_reg_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdireccion_coo_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCind_coo_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_coo_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChor_coo_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_reg_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdireccion_coo_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCind_coo_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_seg_tlf_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChor_seg_tlf_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtelef_seg_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetalle_seg_tlf_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_seg_tlf_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_tlf_1()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_seg_tlf_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChor_seg_tlf_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtelef_seg_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetalle_seg_tlf_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_seg_tlf_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_tlf_2()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_seg_tlf_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChor_seg_tlf_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtelef_seg_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetalle_seg_tlf_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_seg_tlf_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_tlf_3()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_seg_tlf_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChor_seg_tlf_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtelef_seg_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetalle_seg_tlf_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_seg_tlf_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_tlf_4()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_seg_tlf_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChor_seg_tlf_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtelef_seg_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetalle_seg_tlf_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_seg_tlf_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_tlf_5()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_seg_tlf_6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChor_seg_tlf_6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtelef_seg_6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetalle_seg_tlf_6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_seg_tlf_6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_tlf_6()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_seg_tlf_7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChor_seg_tlf_7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtelef_seg_7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetalle_seg_tlf_7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_seg_tlf_7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_tlf_7()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_seg_tlf_8()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChor_seg_tlf_8()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtelef_seg_8()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdetalle_seg_tlf_8()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCresultado_seg_tlf_8()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_tlf_8()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcodimp()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCrevista()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCnro_edicion()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha_publica()); rowBody.createCell(++k).setCellValue(celdaBody);



			}
			//File file = new File(path,fileName);
			wb.write(output);
			output.flush();
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	
	public void createExcelReporteEnvioProv(String path, String fileName, List<ReporteEnvioProv > listaVisita, OutputStream output){
		try{
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	        Date today = Calendar.getInstance().getTime();   
	        String fechaHoy = df.format(today);
			
			//String fechaHoy = "29/11/2016";	
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			HSSFRow row = sheet.createRow((int)0);
			HSSFRichTextString celdaHead;
			int h=-1;

			celdaHead = new HSSFRichTextString("Nombres Mensajero"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Cod. Barra"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Cod. Referencia"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Destino"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Direccion"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Cliente"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Fecha"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Fec. Ultima"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Dias Trans."); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Motivo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Fec. Ult.Visita"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Result. Ult. Visita"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Nro. Hoja Ruta"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Zona Men."); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Zona Hoja Ruta"); row.createCell(++h).setCellValue(celdaHead);
			


			ReporteEnvioProv rep=null;
			for(int i=0;i<listaVisita.size(); i++)
			{
			    rep=listaVisita.get(i);       
			    HSSFRow rowBody = sheet.createRow((int)(i+1));
			    int k=-1;
			    HSSFRichTextString celdaBody;
			    
			    celdaBody = new HSSFRichTextString(rep.getCnombres()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcod_bar()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCval_nro_ide()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdes_nom_ape()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdir_dom_des()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCnom_cli()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfecha()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfec_ultima()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdias_transcurrido()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdes_mot()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfec_ultima_visita()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCres_ultima_visita()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCnro_hoj()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcod_zona()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCzona()); rowBody.createCell(++k).setCellValue(celdaBody);
			    


			}
			//File file = new File(path,fileName);
			wb.write(output);
			output.flush();
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void createExcelReporteCoordincionDia(String path, String fileName, List<ReporteCoordinacionDia > listaVisita, OutputStream output){
		try{
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	        Date today = Calendar.getInstance().getTime();   
	        String fechaHoy = df.format(today);
			
			//String fechaHoy = "29/11/2016";	
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			HSSFRow row = sheet.createRow((int)0);
			HSSFRichTextString celdaHead;
			int h=-1;

			celdaHead = new HSSFRichTextString("Cod. Barra"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Cod. Referencia"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Titular"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Telefono 1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Telefono 2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Fecha Coor"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Hora Coord"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Direcci�n"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Motivo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Fec. Ult.Visita"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Result. Ult. Visita"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Ultima Visita"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Tipo Entrega"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Cod Cliente"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("Cliente"); row.createCell(++h).setCellValue(celdaHead);
			


			ReporteCoordinacionDia rep=null;
			for(int i=0;i<listaVisita.size(); i++)
			{
			    rep=listaVisita.get(i);       
			    HSSFRow rowBody = sheet.createRow((int)(i+1));
			    int k=-1;
			    HSSFRichTextString celdaBody;
			    
			    celdaBody = new HSSFRichTextString(rep.getCcod_bar()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCnro_ref()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtit_num_doc()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtit_tlf_dom()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtit_tlf_lab()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfec_coo()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getChor_coo()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdir_coo()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCdes_mot()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCfec_ultima_visita()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCres_ultima_visita()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getClugar_ultimo()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCtipo_entrega()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCcod_cli()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(rep.getCnom_cli()); rowBody.createCell(++k).setCellValue(celdaBody);
			    


			}
			//File file = new File(path,fileName);
			wb.write(output);
			output.flush();
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	
	public void createExcelBeetrack(String path, String fileName, List<Object> listaGestiones, OutputStream output){
		try{
	
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        Date convertedDate0 = new Date();
	        Date convertedDate1 = new Date();
	        Date convertedDate2 = new Date();
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			HSSFRow row = sheet.createRow((short)0);

			HSSFRichTextString celdaHead;
			
			
			
			
			
			int h=-1;

			celdaHead = new HSSFRichTextString("fechoj"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrohojrut"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codmsj"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nommsj"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nroman"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("destino"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("inddir"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("indsit"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("dessit"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codimp"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("desmot"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("indest"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("desest"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("sitpza"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("dessitpza"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecrcc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrorcc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codcli"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nomcli"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipdoc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("desdoc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("indtjt"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("desemi"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("docide"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codpostal"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrotlf"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecact"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecpcs"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codSeg"); row.createCell(++h).setCellValue(celdaHead);
			
			Iterator itr = listaGestiones.iterator();
			int i=0;
	        while(itr.hasNext()){
	            Object[] obj = (Object[]) itr.next(); 
			    HSSFRow rowBody = sheet.createRow((int)(i+1));
			    int k=-1;
			    HSSFRichTextString celdaBody;
			    String fechoja="";
			    String fecrcc="";
			    String fecpcs="";
			    if(obj[0]!=null){
			    	fechoja=obj[0].toString().substring(8)+'/'+obj[0].toString().substring(5,7)+'/'+obj[0].toString().substring(0,4);
			    	convertedDate0 = dateFormat.parse(fechoja);
			    }
			    if(obj[15]!=null){
			    	fecrcc=obj[15].toString().substring(8)+'/'+obj[15].toString().substring(5,7)+'/'+obj[15].toString().substring(0,4);
			    	convertedDate1 = dateFormat.parse(fecrcc);
			    }
			    if(obj[28]!=null){
			    	fecpcs=obj[28].toString().substring(8)+'/'+obj[28].toString().substring(5,7)+'/'+obj[28].toString().substring(0,4);
			    	convertedDate2 = dateFormat.parse(fecpcs);
			    }

			    HSSFCell cel = rowBody.createCell(++k);
                
              HSSFCellStyle dateCellStyle = wb.createCellStyle();
              short df2 = wb.createDataFormat().getFormat("dd/MM/yyyy");
              dateCellStyle.setDataFormat(df2);
              cel.setCellStyle(dateCellStyle);
              
              cel.setCellValue(convertedDate0);
			                       
				//|celdaBody = new HSSFRichTextString(obj[0]!=null?obj[0].toString():""); rowBody.createCell(++k).setCellValue(convertedDate0);
			    celdaBody = new HSSFRichTextString(obj[1]!=null?obj[1].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[2]!=null?obj[2].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[3]!=null?obj[3].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[4]!=null?obj[4].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[5]!=null?obj[5].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[6]!=null?obj[6].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[7]!=null?obj[7].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[8]!=null?obj[8].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[9]!=null?obj[9].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[10]!=null?obj[10].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[11]!=null?obj[11].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[12]!=null?obj[12].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[13]!=null?obj[13].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[14]!=null?obj[14].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(convertedDate1);
			    celdaBody = new HSSFRichTextString(obj[16]!=null?obj[16].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[17]!=null?obj[17].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[18]!=null?obj[18].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[19]!=null?obj[19].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[20]!=null?obj[20].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[21]!=null?obj[21].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[22]!=null?obj[22].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[23]!=null?obj[23].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[24]!=null?obj[24].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[25]!=null?obj[25].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[26]!=null?obj[26].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[27]!=null?obj[27].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    //celdaBody = new HSSFRichTextString(fecpcs); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(convertedDate2);
			    celdaBody = new HSSFRichTextString(obj[29]!=null?obj[29].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    
			    //celdaBody = new HSSFRichTextString(fec); rowBody.createCell(++k).setCellValue(celdaBody);
			    i++;
	        }

			wb.write(output);
			output.flush();
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void createExcelGestiones(String path, String fileName, List<Object> listaGestiones, OutputStream output){
		try{
	
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			HSSFRow row = sheet.createRow((short)0);

			Iterator itr = listaGestiones.iterator();
			int i=0;
	        while(itr.hasNext()){
	            Object[] obj = (Object[]) itr.next(); 
			    HSSFRow rowBody = sheet.createRow((int)(i));
			    int k=-1;
			    HSSFRichTextString celdaBody;
			    
			    String fec=obj[2].toString().substring(8)+'/'+obj[2].toString().substring(5,7)+'/'+obj[2].toString().substring(0,4);
			    celdaBody = new HSSFRichTextString(obj[0].toString()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[3].toString()); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(fec); rowBody.createCell(++k).setCellValue(celdaBody);
			    i++;
	        }

			wb.write(output);
			output.flush();
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void createExcelPiezasRendidas(String path, String fileName, List<Object> listaGestiones, OutputStream output){
		try{
	
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
	        Date convertedDate0 = new Date();
	        Date convertedDate1 = new Date();
	        Date convertedDate2 = new Date();
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			HSSFRow row = sheet.createRow((short)0);

			HSSFRichTextString celdaHead;
					
			Utils utils = new Utils();
					
			int h=-1;
			//33
			celdaHead = new HSSFRichTextString("fecenv"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nroenv"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecrcc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrorcc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codcli"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nroman"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nroref"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("destino"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("docide"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecent"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecpcs"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("indvin"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nomperrec"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("indsit"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("indest"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codimp"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipdoc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipser"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("indtjt"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codpostal"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codpostal1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrotlf"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrotlf1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nomcli"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("desmot"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("dessit"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("desdoc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("desemi"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("desest"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("desvin"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("recepcionado"); row.createCell(++h).setCellValue(celdaHead);
			
			Iterator itr = listaGestiones.iterator();
			int i=0;
	        while(itr.hasNext()){
	            Object[] obj = (Object[]) itr.next(); 
			    HSSFRow rowBody = sheet.createRow((int)(i+1));
			    int k=-1;
			    HSSFRichTextString celdaBody;
			    String fechoja="";
			    String fecrcc="";
			    String fecpcs="";
			    /*if(obj[0]!=null){
			    	fechoja=obj[0].toString().substring(8)+'/'+obj[0].toString().substring(5,7)+'/'+obj[0].toString().substring(0,4);
			    	convertedDate0 = dateFormat.parse(fechoja);
			    }
			    if(obj[15]!=null){
			    	fecrcc=obj[15].toString().substring(8)+'/'+obj[15].toString().substring(5,7)+'/'+obj[15].toString().substring(0,4);
			    	convertedDate1 = dateFormat.parse(fecrcc);
			    }
			    if(obj[28]!=null){
			    	fecpcs=obj[28].toString().substring(8)+'/'+obj[28].toString().substring(5,7)+'/'+obj[28].toString().substring(0,4);
			    	convertedDate2 = dateFormat.parse(fecpcs);
			    }*/

			    HSSFCell cel = rowBody.createCell(++k);
                
              HSSFCellStyle dateCellStyle = wb.createCellStyle();
              short df2 = wb.createDataFormat().getFormat("dd/MM/yyyy");
              dateCellStyle.setDataFormat(df2);
              cel.setCellStyle(dateCellStyle);
              
                cel.setCellValue(obj[0]!=null?utils.getFechacadenaReporte(obj[0].toString()):"  -   -");               
				//celdaBody = new HSSFRichTextString(obj[0]!=null?obj[0].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[1]!=null?obj[1].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[2]!=null?utils.getFechacadenaReporte(obj[2].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[2]!=null?obj[2].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[3]!=null?obj[3].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[4]!=null?obj[4].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[5]!=null?obj[5].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[6]!=null?obj[6].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[7]!=null?obj[7].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[8]!=null?obj[8].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[9]!=null?utils.getFechacadenaReporte(obj[9].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[9]!=null?obj[9].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[10]!=null?utils.getFechacadenaReporte(obj[10].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[10]!=null?obj[10].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[11]!=null?obj[11].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    //celdaBody = new HSSFRichTextString(obj[12]!=null?obj[12].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[31]!=null?obj[31].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[13]!=null?obj[13].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[14]!=null?obj[14].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[15]!=null?obj[15].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[16]!=null?obj[16].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[17]!=null?obj[17].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[32]!=null?obj[32].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);//indtjt
			    celdaBody = new HSSFRichTextString(obj[18]!=null?obj[18].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[19]!=null?obj[19].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[20]!=null?obj[20].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[21]!=null?obj[21].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[22]!=null?obj[22].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[23]!=null?obj[23].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[24]!=null?obj[24].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[25]!=null?obj[25].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[26]!=null?obj[26].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[27]!=null?obj[27].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[28]!=null?obj[28].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[29]!=null?obj[29].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[30]!=null?obj[30].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[31]!=null?obj[31].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);

			    i++;
	        }

			wb.write(output);
			output.flush();
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void createExcelGestionPiezas(String path, String fileName, List<Object> listaGestiones, OutputStream output){
		try{

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			HSSFRow row = sheet.createRow((short)0);

			HSSFRichTextString celdaHead;

			int h=-1;
			Utils utils = new Utils();

			celdaHead = new HSSFRichTextString("cargo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrorcc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("pieza"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("destinatario"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("doc_identidad"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telefono"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telefono_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("dir_basica"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("dir_bas_ubigeo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("dir_bas_distrito"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("dir_bas_prov"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("dir_bas_depa"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("dir_alterna"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("empresa"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ruc_emp"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion_emp"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("departamento"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_recepcion"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_documento"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_servicio"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_emision"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cant_det"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("peso"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ubicacion_actual"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_ultima"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_ultimo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("lugar_ultimo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_entrega"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_ultima_visita"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_ultima_visita"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cant_vis"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cant_coo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cant_seg"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_visita_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_visita_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("lugar_visita_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("gestion_visita_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_visita_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_visita_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("lugar_visita_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("gestion_visita_2"); row.createCell(++h).setCellValue(celdaHead);
			
			celdaHead = new HSSFRichTextString("fecha_visita_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_visita_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("lugar_visita_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("gestion_visita_3"); row.createCell(++h).setCellValue(celdaHead);
			
			celdaHead = new HSSFRichTextString("fecha_visita_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_visita_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("lugar_visita_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("gestion_visita_4"); row.createCell(++h).setCellValue(celdaHead);
			
			celdaHead = new HSSFRichTextString("fecha_visita_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_visita_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("lugar_visita_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("gestion_visita_5"); row.createCell(++h).setCellValue(celdaHead);
			
			celdaHead = new HSSFRichTextString("fecha_visita_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_visita_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("lugar_visita_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("gestion_visita_6"); row.createCell(++h).setCellValue(celdaHead);
			
			celdaHead = new HSSFRichTextString("fecha_visita_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_visita_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("lugar_visita_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("gestion_visita_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_visita_8"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_visita_8"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("lugar_visita_8"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("gestion_visita_8"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_coo_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("hor_coo_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_reg_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion_coo_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ind_coo_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_coo_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("hor_coo_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_reg_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion_coo_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ind_coo_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_coo_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("hor_coo_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_reg_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion_coo_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ind_coo_3"); row.createCell(++h).setCellValue(celdaHead);
			
			celdaHead = new HSSFRichTextString("fecha_seg_tlf_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("hor_seg_tlf_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telef_seg_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("detalle_seg_tlf_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_seg_tlf_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_tlf_1"); row.createCell(++h).setCellValue(celdaHead);
			
			celdaHead = new HSSFRichTextString("fecha_seg_tlf_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("hor_seg_tlf_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telef_seg_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("detalle_seg_tlf_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_seg_tlf_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_tlf_2"); row.createCell(++h).setCellValue(celdaHead);
			
			celdaHead = new HSSFRichTextString("fecha_seg_tlf_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("hor_seg_tlf_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telef_seg_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("detalle_seg_tlf_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_seg_tlf_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_tlf_3"); row.createCell(++h).setCellValue(celdaHead);
			
			celdaHead = new HSSFRichTextString("fecha_seg_tlf_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("hor_seg_tlf_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telef_seg_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("detalle_seg_tlf_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_seg_tlf_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_tlf_4"); row.createCell(++h).setCellValue(celdaHead);
			
			celdaHead = new HSSFRichTextString("fecha_seg_tlf_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("hor_seg_tlf_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telef_seg_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("detalle_seg_tlf_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_seg_tlf_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_tlf_5"); row.createCell(++h).setCellValue(celdaHead);
			
			celdaHead = new HSSFRichTextString("fecha_seg_tlf_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("hor_seg_tlf_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telef_seg_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("detalle_seg_tlf_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_seg_tlf_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_tlf_6"); row.createCell(++h).setCellValue(celdaHead);
			
			celdaHead = new HSSFRichTextString("fecha_seg_tlf_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("hor_seg_tlf_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telef_seg_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("detalle_seg_tlf_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_seg_tlf_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_tlf_7"); row.createCell(++h).setCellValue(celdaHead);
			
			celdaHead = new HSSFRichTextString("fecha_seg_tlf_8"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("hor_seg_tlf_8"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telef_seg_8"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("detalle_seg_tlf_8"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("resultado_seg_tlf_8"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_tlf_8"); row.createCell(++h).setCellValue(celdaHead);
			
			celdaHead = new HSSFRichTextString("codimp"); row.createCell(++h).setCellValue(celdaHead);
			
			
			Iterator itr = listaGestiones.iterator();
			int i=0;
	        while(itr.hasNext()){
	            Object[] obj = (Object[]) itr.next(); 
			    HSSFRow rowBody = sheet.createRow((int)(i+1));
			    int k=-1;
			    HSSFRichTextString celdaBody;

			    HSSFCell cel = rowBody.createCell(++k);
                
              HSSFCellStyle dateCellStyle = wb.createCellStyle();
              short df2 = wb.createDataFormat().getFormat("dd/MM/yyyy");
              dateCellStyle.setDataFormat(df2);
              cel.setCellStyle(dateCellStyle);
              
                cel.setCellValue(obj[1]!=null?obj[0].toString():"");                 
				//|celdaBody = new HSSFRichTextString(obj[0]!=null?obj[0].toString():""); rowBody.createCell(++k).setCellValue(convertedDate0);
			    celdaBody = new HSSFRichTextString(obj[1]!=null?obj[1].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[2]!=null?obj[2].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[3]!=null?obj[3].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[4]!=null?obj[4].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[5]!=null?obj[5].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[6]!=null?obj[6].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[7]!=null?obj[7].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[8]!=null?obj[8].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[9]!=null?obj[9].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[10]!=null?obj[10].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[11]!=null?obj[11].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[12]!=null?obj[12].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[13]!=null?obj[13].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[14]!=null?obj[14].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[15]!=null?obj[15].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[16]!=null?obj[16].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[17]!=null?utils.getFechacadenaReporte(obj[17].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[17]!=null?obj[17].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[18]!=null?obj[18].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[19]!=null?obj[19].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[20]!=null?obj[20].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[21]!=null?obj[21].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[22]!=null?obj[22].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[23]!=null?obj[23].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[24]!=null?utils.getFechacadenaReporte(obj[24].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[24]!=null?obj[24].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[25]!=null?obj[25].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[26]!=null?obj[26].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[27]!=null?obj[27].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[28]!=null?utils.getFechacadenaReporte(obj[28].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[28]!=null?obj[28].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[29]!=null?obj[29].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[30]!=null?obj[30].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[31]!=null?obj[31].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[32]!=null?obj[32].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[33]!=null?utils.getFechacadenaReporte(obj[33].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[33]!=null?obj[33].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[34]!=null?obj[34].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[35]!=null?obj[35].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[36]!=null?obj[36].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[37]!=null?utils.getFechacadenaReporte(obj[37].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[37]!=null?obj[37].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[38]!=null?obj[38].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[39]!=null?obj[39].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[40]!=null?obj[40].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[41]!=null?utils.getFechacadenaReporte(obj[41].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[41]!=null?obj[41].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[42]!=null?obj[42].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[43]!=null?obj[43].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[44]!=null?obj[44].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[45]!=null?utils.getFechacadenaReporte(obj[45].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[45]!=null?obj[45].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[46]!=null?obj[46].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[47]!=null?obj[47].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[48]!=null?obj[48].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[49]!=null?utils.getFechacadenaReporte(obj[49].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[49]!=null?obj[49].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[50]!=null?obj[50].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[51]!=null?obj[51].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[52]!=null?obj[52].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[53]!=null?utils.getFechacadenaReporte(obj[53].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[53]!=null?obj[53].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[54]!=null?obj[54].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[55]!=null?obj[55].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[56]!=null?obj[56].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[57]!=null?utils.getFechacadenaReporte(obj[57].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[57]!=null?obj[57].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[58]!=null?obj[58].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[59]!=null?obj[59].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[60]!=null?obj[60].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[61]!=null?utils.getFechacadenaReporte(obj[61].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[61]!=null?obj[61].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[62]!=null?obj[62].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[63]!=null?obj[63].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[64]!=null?obj[64].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[65]!=null?utils.getFechacadenaReporte(obj[65].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[65]!=null?obj[65].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[66]!=null?obj[66].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[67]!=null?utils.getFechacadenaReporte(obj[67].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[67]!=null?obj[67].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[68]!=null?obj[68].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[69]!=null?obj[69].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[70]!=null?utils.getFechacadenaReporte(obj[70].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[70]!=null?obj[70].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[71]!=null?obj[71].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[72]!=null?utils.getFechacadenaReporte(obj[72].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[72]!=null?obj[72].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[73]!=null?obj[73].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[74]!=null?obj[74].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[75]!=null?utils.getFechacadenaReporte(obj[75].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[75]!=null?obj[75].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[76]!=null?obj[76].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[77]!=null?utils.getFechacadenaReporte(obj[77].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[77]!=null?obj[77].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[78]!=null?obj[78].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[79]!=null?obj[79].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[80]!=null?utils.getFechacadenaReporte(obj[80].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[80]!=null?obj[80].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[81]!=null?obj[81].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[82]!=null?obj[82].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[83]!=null?obj[83].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[84]!=null?obj[84].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[85]!=null?obj[85].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[86]!=null?utils.getFechacadenaReporte(obj[86].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[86]!=null?obj[86].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[87]!=null?obj[87].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[88]!=null?obj[88].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[89]!=null?obj[89].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[90]!=null?obj[90].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[91]!=null?obj[91].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[92]!=null?utils.getFechacadenaReporte(obj[92].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[92]!=null?obj[92].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[93]!=null?obj[93].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[94]!=null?obj[94].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[95]!=null?obj[95].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[96]!=null?obj[96].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[97]!=null?obj[97].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[98]!=null?utils.getFechacadenaReporte(obj[98].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[98]!=null?obj[98].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[99]!=null?obj[99].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[100]!=null?obj[100].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[101]!=null?obj[101].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[102]!=null?obj[102].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[103]!=null?obj[103].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[104]!=null?utils.getFechacadenaReporte(obj[104].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[104]!=null?obj[104].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[105]!=null?obj[105].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[106]!=null?obj[106].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[107]!=null?obj[107].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[108]!=null?obj[108].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[109]!=null?obj[109].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[110]!=null?utils.getFechacadenaReporte(obj[110].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[110]!=null?obj[110].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[111]!=null?obj[111].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[112]!=null?obj[112].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[113]!=null?obj[113].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[114]!=null?obj[114].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[115]!=null?obj[115].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[116]!=null?utils.getFechacadenaReporte(obj[116].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[116]!=null?obj[116].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[117]!=null?obj[117].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[118]!=null?obj[118].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[119]!=null?obj[119].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[120]!=null?obj[120].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[121]!=null?obj[121].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[122]!=null?utils.getFechacadenaReporte(obj[122].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[122]!=null?obj[122].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[123]!=null?obj[123].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[124]!=null?obj[124].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[125]!=null?obj[125].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[126]!=null?obj[126].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[127]!=null?obj[127].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);

			    //celdaBody = new HSSFRichTextString(fec); rowBody.createCell(++k).setCellValue(celdaBody);
			    i++;
	        }

			wb.write(output);
			output.flush();
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void createExcelResultadoGestion(String path, String fileName, List<Object> listaGestiones, OutputStream output){
		try{

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			HSSFRow row = sheet.createRow((short)0);

			HSSFRichTextString celdaHead;
			Utils utils= new Utils();
			int h=-1;
			//15
			celdaHead = new HSSFRichTextString("fecpcs"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecrcc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrorcc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("canrcc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("canent"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("pcjent"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("canimp"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("pcjimp"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("canpen"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("pcjpen"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cancoo"); row.createCell(++h).setCellValue(celdaHead);
			//celdaHead = new HSSFRichTextString("canrut"); row.createCell(++h).setCellValue(celdaHead);
			//celdaHead = new HSSFRichTextString("canrzg"); row.createCell(++h).setCellValue(celdaHead);
			//celdaHead = new HSSFRichTextString("canges"); row.createCell(++h).setCellValue(celdaHead);
			//celdaHead = new HSSFRichTextString("canenv"); row.createCell(++h).setCellValue(celdaHead);
			
			
			Iterator itr = listaGestiones.iterator();
			int i=0;
	        while(itr.hasNext()){
	            Object[] obj = (Object[]) itr.next(); 
			    HSSFRow rowBody = sheet.createRow((int)(i+1));
			    int k=-1;
			    HSSFRichTextString celdaBody;
			    
			    HSSFCell cel = rowBody.createCell(++k);
                
              HSSFCellStyle dateCellStyle = wb.createCellStyle();
              short df2 = wb.createDataFormat().getFormat("dd/MM/yyyy");
              dateCellStyle.setDataFormat(df2);
              cel.setCellStyle(dateCellStyle);
              
              	cel.setCellValue(obj[1]!=null?utils.getFechacadenaReporte(obj[1].toString()):"  -   -");
              	cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[2]!=null?utils.getFechacadenaReporte(obj[2].toString()):"  -   -");                   
				//|celdaBody = new HSSFRichTextString(obj[0]!=null?obj[0].toString():""); rowBody.createCell(++k).setCellValue(convertedDate0);
			    celdaBody = new HSSFRichTextString(obj[0]!=null?obj[0].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    
			    Double total=obj[3]!=null?Double.parseDouble(obj[3].toString()):0;
			    Double ent=obj[4]!=null?Double.parseDouble(obj[4].toString()):0;
			    Double imp=obj[5]!=null?Double.parseDouble(obj[5].toString()):0;
			    Double pen=obj[6]!=null?Double.parseDouble(obj[6].toString()):0;
			    Double pcjent=(ent*100)/total;
			    Double pcjimp=(imp*100)/total;
			    Double pcjpen=(pen*100)/total;
			    celdaBody = new HSSFRichTextString(obj[3]!=null?obj[3].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[4]!=null?obj[4].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(round(pcjent, 2)+""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[5]!=null?obj[5].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(round(pcjimp, 2)+""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[6]!=null?obj[6].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(round(pcjpen, 2)+""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[7]!=null?obj[7].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    
			    i++;
	        }

			wb.write(output);
			output.flush();
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	public void createExcelResumenHojaRuta(String path, String fileName, List<Object> listaGestiones, OutputStream output){
		try{

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			HSSFRow row = sheet.createRow((short)0);

			HSSFRichTextString celdaHead;

			Utils utils= new Utils();
			int h=-1;
			//8
			celdaHead = new HSSFRichTextString("nrohoj"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fechoj"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrohojrut"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codmsj"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nommsj"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("canent"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("candga"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("candif"); row.createCell(++h).setCellValue(celdaHead);

			Iterator itr = listaGestiones.iterator();
			int i=0;
	        while(itr.hasNext()){
	            Object[] obj = (Object[]) itr.next(); 
			    HSSFRow rowBody = sheet.createRow((int)(i+1));
			    int k=-1;
			    HSSFRichTextString celdaBody;
			
			    HSSFCell cel = rowBody.createCell(++k);
                
              HSSFCellStyle dateCellStyle = wb.createCellStyle();
              short df2 = wb.createDataFormat().getFormat("dd/MM/yyyy");
              dateCellStyle.setDataFormat(df2);
              cel.setCellStyle(dateCellStyle);
              
                cel.setCellValue(obj[0]!=null?obj[0].toString():"");
                cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[1]!=null?utils.getFechacadenaReporte(obj[1].toString()):"  -   -");                   
				//|celdaBody = new HSSFRichTextString(obj[0]!=null?obj[0].toString():""); rowBody.createCell(++k).setCellValue(convertedDate0);
			    celdaBody = new HSSFRichTextString(obj[2]!=null?obj[2].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[3]!=null?obj[3].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[4]!=null?obj[4].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[5]!=null?obj[5].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[6]!=null?obj[6].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    Integer ent=obj[5]!=null?Integer.parseInt(obj[5].toString()):0;
			    Integer des=obj[6]!=null?Integer.parseInt(obj[6].toString()):0;
			    Integer resul=ent-des;
			    celdaBody = new HSSFRichTextString(resul+""); rowBody.createCell(++k).setCellValue(celdaBody);
			    i++;
	        }

			wb.write(output);
			output.flush();
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void createExcelMovimientoHojaRuta(String path, String fileName, List<Object> listaGestiones, OutputStream output){
		try{

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			HSSFRow row = sheet.createRow((short)0);

			HSSFRichTextString celdaHead;
			Utils utils = new Utils();
			int h=-1;
			//29
			celdaHead = new HSSFRichTextString("fechoj"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrohoj"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codmsj"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nommsj"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nroman"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("destino"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("inddir"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("indsit"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("dessit"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codimp"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("desmot"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("indest"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("desest"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("sitpza"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("dessitpza"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecrcc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrorcc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codcli"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nomcli"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipdoc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("desdoc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("indtjt"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("desemi"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("docide"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codpostal"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrotlf"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecact"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecpcs"); row.createCell(++h).setCellValue(celdaHead);

			Iterator itr = listaGestiones.iterator();
			int i=0;
	        while(itr.hasNext()){
	            Object[] obj = (Object[]) itr.next(); 
			    HSSFRow rowBody = sheet.createRow((int)(i+1));
			    int k=-1;
			    HSSFRichTextString celdaBody;
			    HSSFCell cel = rowBody.createCell(++k);
                
              HSSFCellStyle dateCellStyle = wb.createCellStyle();
              short df2 = wb.createDataFormat().getFormat("dd/MM/yyyy");
              dateCellStyle.setDataFormat(df2);
              cel.setCellStyle(dateCellStyle);
              
              	cel.setCellValue(obj[0]!=null?utils.getFechacadenaReporte(obj[0].toString()):"  -   -");              
				//|celdaBody = new HSSFRichTextString(obj[0]!=null?obj[0].toString():""); rowBody.createCell(++k).setCellValue(convertedDate0);
			    celdaBody = new HSSFRichTextString(obj[1]!=null?obj[1].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[2]!=null?obj[2].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[3]!=null?obj[3].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[4]!=null?obj[4].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[5]!=null?obj[5].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[6]!=null?obj[6].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[7]!=null?obj[7].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[8]!=null?obj[8].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[9]!=null?obj[9].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[10]!=null?obj[10].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[11]!=null?obj[11].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[12]!=null?obj[12].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[13]!=null?obj[13].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[14]!=null?obj[14].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellValue(obj[15]!=null?utils.getFechacadenaReporte(obj[15].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[15]!=null?obj[15].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[16]!=null?obj[16].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[17]!=null?obj[17].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[18]!=null?obj[18].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[19]!=null?obj[19].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[20]!=null?obj[20].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[21]!=null?obj[21].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[22]!=null?obj[22].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[23]!=null?obj[23].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[24]!=null?obj[24].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[25]!=null?obj[25].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[26]!=null?obj[26].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellValue(obj[27]!=null?utils.getFechacadenaReporte(obj[27].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[27]!=null?obj[27].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellValue(obj[28]!=null?utils.getFechacadenaReporte(obj[28].toString()):"  -   -");
			    //celdaBody = new HSSFRichTextString(obj[28]!=null?obj[28].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    i++;
	        }

			wb.write(output);
			output.flush();
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void createExcelMovimientoMensajero(String path, String fileName, List<Object> listaGestiones, OutputStream output){
		try{

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			HSSFRow row = sheet.createRow((short)0);

			HSSFRichTextString celdaHead;
			Utils utils = new Utils();
			int h=-1;
			//35
			celdaHead = new HSSFRichTextString("fechoj"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrohoj"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrohojrut"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codmsj"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nommsj"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nroman"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("indsit"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("dessit"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codimp"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("desmot"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("indir"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("horvis"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecrcc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrorcc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codcli"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nomcli"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nroref"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipdoc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("desdoc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipser"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("indtjt"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("destino"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("docide"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("dessitpza"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("indest"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrotlf"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrocoo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecreg"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("feccoo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("horcoo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codpostal"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("indcoo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("usucoo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("cancoo"); row.createCell(++h).setCellValue(celdaHead);

			Iterator itr = listaGestiones.iterator();
			int i=0;
	        while(itr.hasNext()){
	            Object[] obj = (Object[]) itr.next(); 
			    HSSFRow rowBody = sheet.createRow((int)(i+1));
			    int k=-1;
			    HSSFRichTextString celdaBody;
			   

			    HSSFCell cel = rowBody.createCell(++k);
                
              HSSFCellStyle dateCellStyle = wb.createCellStyle();
              short df2 = wb.createDataFormat().getFormat("dd/MM/yyyy");
              dateCellStyle.setDataFormat(df2);
              cel.setCellStyle(dateCellStyle);
              
                cel.setCellValue(obj[0]!=null?utils.getFechacadenaReporte(obj[0].toString()):"  -   -");          
				//|celdaBody = new HSSFRichTextString(obj[0]!=null?obj[0].toString():""); rowBody.createCell(++k).setCellValue(convertedDate0);
			    celdaBody = new HSSFRichTextString(obj[1]!=null?obj[1].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[2]!=null?obj[2].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[3]!=null?obj[3].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[4]!=null?obj[4].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[5]!=null?obj[5].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[6]!=null?obj[6].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[7]!=null?obj[7].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[8]!=null?obj[8].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[9]!=null?obj[9].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[10]!=null?obj[10].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[11]!=null?obj[11].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[12]!=null?utils.getFechacadenaReporte(obj[12].toString()):"  -   -");
			    celdaBody = new HSSFRichTextString(obj[13]!=null?obj[13].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[14]!=null?obj[14].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[15]!=null?obj[15].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[16]!=null?obj[16].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[17]!=null?obj[17].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[18]!=null?obj[18].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[19]!=null?obj[19].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[21]!=null?obj[21].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[22]!=null?obj[22].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[23]!=null?obj[23].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[24]!=null?obj[24].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[25]!=null?obj[25].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[26]!=null?obj[26].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[27]!=null?utils.getFechacadenaReporte(obj[27].toString()):"  -   -");
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[28]!=null?utils.getFechacadenaReporte(obj[28].toString()):"  -   -");
			    celdaBody = new HSSFRichTextString(obj[29]!=null?obj[29].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[30]!=null?obj[30].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[31]!=null?obj[31].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[32]!=null?obj[32].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[33]!=null?obj[33].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[26]!=null?"1":"0"); rowBody.createCell(++k).setCellValue(celdaBody);

			    i++;
	        }

			wb.write(output);
			output.flush();
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void createExcelSeguimientoCoordinaciones(String path, String fileName, List<Object> listaGestiones, OutputStream output){
		try{

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			HSSFRow row = sheet.createRow((short)0);

			HSSFRichTextString celdaHead;
			Utils utils=new Utils();
			int h=-1;
			//19
			celdaHead = new HSSFRichTextString("desest"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("sitpza"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("motpza"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("horcoo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nomcli"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nommsj"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("dirdtt"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("dirprv"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("dirdep"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("referencia"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("desdoc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("destino"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("observa"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrotlf"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nnroman"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecrcc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("feccoo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecreg"); row.createCell(++h).setCellValue(celdaHead);
			

			Iterator itr = listaGestiones.iterator();
			int i=0;
	        while(itr.hasNext()){
	            Object[] obj = (Object[]) itr.next(); 
			    HSSFRow rowBody = sheet.createRow((int)(i+1));
			    int k=-1;
			    HSSFRichTextString celdaBody;

			    HSSFCell cel = rowBody.createCell(++k);
                
              HSSFCellStyle dateCellStyle = wb.createCellStyle();
              short df2 = wb.createDataFormat().getFormat("dd/MM/yyyy");
              dateCellStyle.setDataFormat(df2);
              //cel.setCellStyle(dateCellStyle);
              
              	cel.setCellValue(obj[0]!=null?obj[0].toString():"");
			                       
				//celdaBody = new HSSFRichTextString(obj[0]!=null?obj[0].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[1]!=null?obj[1].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[2]!=null?obj[2].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[3]!=null?obj[3].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[4]!=null?obj[4].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[5]!=null?obj[5].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[6]!=null?obj[6].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[7]!=null?obj[7].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[8]!=null?obj[8].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[9]!=null?obj[9].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[10]!=null?obj[10].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[11]!=null?obj[11].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[12]!=null?obj[12].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[13]!=null?obj[13].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[14]!=null?obj[14].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[15]!=null?obj[15].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[16]!=null?utils.getFechacadenaReporte(obj[16].toString()):"  -   -");
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[17]!=null?utils.getFechacadenaReporte(obj[17].toString()):"  -   -");
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(obj[18]!=null?utils.getFechacadenaReporte(obj[18].toString()):"  -   -");
			 
			    i++;
	        }

			wb.write(output);
			output.flush();
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void createExcelGestionUsuario(String path, String fileName, List<Object> listaGestiones, OutputStream output){
		try{

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			HSSFRow row = sheet.createRow((short)0);

			HSSFRichTextString celdaHead;
			Utils utils= new Utils();
			int h=-1;
			//31
			celdaHead = new HSSFRichTextString("fecmov"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codusu"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codcli"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("GUIA/OP"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nroman"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecrcc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nroref"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("destino"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("indsit"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("indest"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codmov"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("desmov"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("feccoo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("inddir"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("desdir"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("motseg"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("desseg"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("detseg"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codofi"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrotlf1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("obstlf1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrotlf2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("obstlf2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrotlf3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("obstlf3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrotlf4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("obstlf4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrotlf5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("obstlf5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nomusu"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nomcli"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("dessit"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("desofi"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codter"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("dester"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipser"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nomemp"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codemp"); row.createCell(++h).setCellValue(celdaHead);
			
			Iterator itr = listaGestiones.iterator();
			int i=0;
	        while(itr.hasNext()){
	            Object[] obj = (Object[]) itr.next(); 
			    HSSFRow rowBody = sheet.createRow((int)(i+1));
			    int k=-1;
			    HSSFRichTextString celdaBody;

			    HSSFCell cel = rowBody.createCell(++k);
                
              HSSFCellStyle dateCellStyle = wb.createCellStyle();
              short df2 = wb.createDataFormat().getFormat("dd/MM/yyyy");
              dateCellStyle.setDataFormat(df2);
              cel.setCellStyle(dateCellStyle);
              
                cel.setCellValue(obj[0]!=null?utils.getFechacadenaReporte(obj[0].toString()):"  -   -");           
				//|celdaBody = new HSSFRichTextString(obj[0]!=null?obj[0].toString():""); rowBody.createCell(++k).setCellValue(convertedDate0);
			    celdaBody = new HSSFRichTextString(obj[1]!=null?obj[1].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[2]!=null?obj[2].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[3]!=null?obj[3].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[4]!=null?obj[4].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[5]!=null?obj[5].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[6]!=null?obj[6].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[7]!=null?obj[7].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[8]!=null?obj[8].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[9]!=null?obj[9].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[10]!=null?obj[10].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[11]!=null?obj[11].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[12]!=null?obj[12].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[13]!=null?obj[13].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[14]!=null?obj[14].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[15]!=null?obj[15].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[16]!=null?obj[16].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[17]!=null?obj[17].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[18]!=null?obj[18].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[19]!=null?obj[19].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[20]!=null?obj[20].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[21]!=null?obj[21].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[22]!=null?obj[22].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[23]!=null?obj[23].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[24]!=null?obj[24].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[25]!=null?obj[25].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[26]!=null?obj[26].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[27]!=null?obj[27].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[28]!=null?obj[28].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[29]!=null?obj[29].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[30]!=null?obj[30].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[31]!=null?obj[31].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[32]!=null?obj[32].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[33]!=null?obj[33].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[34]!=null?obj[34].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[35]!=null?obj[35].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[36]!=null?obj[36].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    //celdaBody = new HSSFRichTextString(obj[37]!=null?obj[37].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);

			    i++;
	        }

			wb.write(output);
			output.flush();
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void createExcelBCPSCC(String path, String fileName, List<Object> listaGestiones, OutputStream output){
		try{

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			HSSFRow row = sheet.createRow((short)0);

			HSSFRichTextString celdaHead;

			int h=-1;
			//31
			celdaHead = new HSSFRichTextString("codigo_seguimiento"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("numero_acuse"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_tarjeta"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_emision"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("id_bin"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("id_afinidad"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nombre_tarjetahabiente"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nombre_titular"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nombre_mandatario"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_doc_mandatario_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("num_doc_mandatario_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("ruc_empresa"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nombre_empresa"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("representante_autorizado"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telefono_de_contacto"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_destino"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("id_mensajeria"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("id_codigo_zip"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("id_sucursal"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("id_agencia"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("dir_domicilio"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("provincia_domicilio"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion_trabajo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("provincia_trabajo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("requiere_contrato"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("requiere_recibo"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipo_de_entrega"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codigo_rel_empaque"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_bcp"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("canal_venta"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecha_concilia_dist"); row.createCell(++h).setCellValue(celdaHead);
			
			celdaHead = new HSSFRichTextString("tipo_de_cliente"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("idc_cliente"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telefono_1"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telefono_2"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telefono_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telefono_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telefono_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telefono_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telefono_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telefono_8"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telefono_9"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telefono_10"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telefono_11"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telefono_12"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telefono_13"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telefono_14"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("telefono_15"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("provincia_3"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("provincia_4"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("provincia_5"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("provincia_6"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("provincia_7"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion_8"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("provincia_8"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion_9"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("provincia_9"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion_10"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("provincia_10"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion_11"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("provincia_11"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion_12"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("provincia_12"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion_13"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("provincia_13"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion_14"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("provincia_14"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion_15"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("provincia_15"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("estado_final"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("detalle_del_estado"); row.createCell(++h).setCellValue(celdaHead);
			
			
			Iterator itr = listaGestiones.iterator();
			int i=0;
	        while(itr.hasNext()){
	            Object[] obj = (Object[]) itr.next(); 
			    HSSFRow rowBody = sheet.createRow((int)(i+1));
			    int k=-1;
			    HSSFRichTextString celdaBody;
			    String fechoja="";
			    String fecrcc="";
			    String fecpcs="";
		

			    HSSFCell cel = rowBody.createCell(++k);
                
              HSSFCellStyle dateCellStyle = wb.createCellStyle();
              short df2 = wb.createDataFormat().getFormat("dd/MM/yyyy");
              dateCellStyle.setDataFormat(df2);
              cel.setCellStyle(dateCellStyle);
              
                cel.setCellValue(obj[0]!=null?obj[0].toString():"");                
				//|celdaBody = new HSSFRichTextString(obj[0]!=null?obj[0].toString():""); rowBody.createCell(++k).setCellValue(convertedDate0);
			    celdaBody = new HSSFRichTextString(obj[1]!=null?obj[1].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[2]!=null?obj[2].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[3]!=null?obj[3].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[4]!=null?obj[4].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[5]!=null?obj[5].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[6]!=null?obj[6].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[7]!=null?obj[7].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[8]!=null?obj[8].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[9]!=null?obj[9].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[10]!=null?obj[10].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[11]!=null?obj[11].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[12]!=null?obj[12].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[13]!=null?obj[13].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[14]!=null?obj[14].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[15]!=null?obj[15].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    ///cel = rowBody.createCell(++k);
			    ///cel.setCellStyle(dateCellStyle);cel.setCellValue(convertedDate1);
			    celdaBody = new HSSFRichTextString(obj[16]!=null?obj[16].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[17]!=null?obj[17].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[18]!=null?obj[18].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[19]!=null?obj[19].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[20]!=null?obj[20].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[21]!=null?obj[21].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[22]!=null?obj[22].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[23]!=null?obj[23].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[24]!=null?obj[24].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[25]!=null?obj[25].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[26]!=null?obj[26].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[27]!=null?obj[27].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    //celdaBody = new HSSFRichTextString(fecpcs); rowBody.createCell(++k).setCellValue(celdaBody);
			    ///cel = rowBody.createCell(++k);
			    ///cel.setCellStyle(dateCellStyle);cel.setCellValue(convertedDate2);

			    celdaBody = new HSSFRichTextString(obj[28]!=null?obj[28].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    
			    celdaBody = new HSSFRichTextString(obj[29]!=null?obj[29].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[30]!=null?obj[30].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[31]!=null?obj[31].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[32]!=null?obj[32].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[33]!=null?obj[33].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[34]!=null?obj[34].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[35]!=null?obj[35].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[36]!=null?obj[36].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[37]!=null?obj[37].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[38]!=null?obj[38].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(""); rowBody.createCell(++k).setCellValue(celdaBody);

			    i++;
	        }

			wb.write(output);
			output.flush();
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void createExcelSituacionDespachoProvincia(String path, String fileName, List<Object> listaGestiones, OutputStream output){
		try{
	
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        Date convertedDate0 = new Date();
	        Date convertedDate1 = new Date();
	        Date convertedDate2 = new Date();
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Reporte");

			HSSFRow row = sheet.createRow((short)0);

			HSSFRichTextString celdaHead;

			int h=-1;

			celdaHead = new HSSFRichTextString("fechoj"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrohojrut"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codmsj"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nommsj"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nroman"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("destino"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("inddir"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("indsit"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("dessit"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codimp"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("desmot"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("indest"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("desest"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("sitpza"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("dessitpza"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecrcc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrorcc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codcli"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nomcli"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("tipdoc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("desdoc"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("indtjt"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("desemi"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("docide"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("direccion"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codpostal"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("nrotlf"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecact"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("fecpcs"); row.createCell(++h).setCellValue(celdaHead);
			celdaHead = new HSSFRichTextString("codSeg"); row.createCell(++h).setCellValue(celdaHead);
			
			Iterator itr = listaGestiones.iterator();
			int i=0;
	        while(itr.hasNext()){
	            Object[] obj = (Object[]) itr.next(); 
			    HSSFRow rowBody = sheet.createRow((int)(i+1));
			    int k=-1;
			    HSSFRichTextString celdaBody;
			    String fechoja="";
			    String fecrcc="";
			    String fecpcs="";
			    if(obj[0]!=null){
			    	fechoja=obj[0].toString().substring(8)+'/'+obj[0].toString().substring(5,7)+'/'+obj[0].toString().substring(0,4);
			    	convertedDate0 = dateFormat.parse(fechoja);
			    }
			    if(obj[15]!=null){
			    	fecrcc=obj[15].toString().substring(8)+'/'+obj[15].toString().substring(5,7)+'/'+obj[15].toString().substring(0,4);
			    	convertedDate1 = dateFormat.parse(fecrcc);
			    }
			    if(obj[28]!=null){
			    	fecpcs=obj[28].toString().substring(8)+'/'+obj[28].toString().substring(5,7)+'/'+obj[28].toString().substring(0,4);
			    	convertedDate2 = dateFormat.parse(fecpcs);
			    }

			    HSSFCell cel = rowBody.createCell(++k);
                
              HSSFCellStyle dateCellStyle = wb.createCellStyle();
              short df2 = wb.createDataFormat().getFormat("dd/MM/yyyy");
              dateCellStyle.setDataFormat(df2);
              cel.setCellStyle(dateCellStyle);
              
              cel.setCellValue(convertedDate0);
			                       
				//|celdaBody = new HSSFRichTextString(obj[0]!=null?obj[0].toString():""); rowBody.createCell(++k).setCellValue(convertedDate0);
			    celdaBody = new HSSFRichTextString(obj[1]!=null?obj[1].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[2]!=null?obj[2].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[3]!=null?obj[3].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[4]!=null?obj[4].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[5]!=null?obj[5].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[6]!=null?obj[6].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[7]!=null?obj[7].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[8]!=null?obj[8].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[9]!=null?obj[9].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[10]!=null?obj[10].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[11]!=null?obj[11].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[12]!=null?obj[12].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[13]!=null?obj[13].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[14]!=null?obj[14].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(convertedDate1);
			    celdaBody = new HSSFRichTextString(obj[16]!=null?obj[16].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[17]!=null?obj[17].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[18]!=null?obj[18].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[19]!=null?obj[19].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[20]!=null?obj[20].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[21]!=null?obj[21].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[22]!=null?obj[22].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[23]!=null?obj[23].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[24]!=null?obj[24].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[25]!=null?obj[25].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[26]!=null?obj[26].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    celdaBody = new HSSFRichTextString(obj[27]!=null?obj[27].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);
			    //celdaBody = new HSSFRichTextString(fecpcs); rowBody.createCell(++k).setCellValue(celdaBody);
			    cel = rowBody.createCell(++k);
			    cel.setCellStyle(dateCellStyle);cel.setCellValue(convertedDate2);

			    celdaBody = new HSSFRichTextString(obj[29]!=null?obj[29].toString():""); rowBody.createCell(++k).setCellValue(celdaBody);

			    
			    //celdaBody = new HSSFRichTextString(fec); rowBody.createCell(++k).setCellValue(celdaBody);
			    i++;
	        }

			wb.write(output);
			output.flush();
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
