package pe.nasqa.values.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.nasqa.values.dao.ReportesInternosDao;
//import pe.nasqa.values.dao.DistribucionDao;
//import pe.nasqa.values.dao.ImpExpDbDao;
import pe.nasqa.values.model.entity.Distribucion;
import pe.nasqa.values.model.entity.DistribucionCoord;
import pe.nasqa.values.model.entity.DistribucionVisita;
import pe.nasqa.values.model.entity.ExportVisita;
import pe.nasqa.values.model.entity.ReporteBBVAentr;
import pe.nasqa.values.model.entity.ReporteBBVApend;
import pe.nasqa.values.model.entity.ReporteBBVAworkf;
import pe.nasqa.values.model.entity.ReporteBCPtc;
import pe.nasqa.values.model.entity.ReporteCoordinacionDia;
import pe.nasqa.values.model.entity.ReporteEnvioProv;
import pe.nasqa.values.model.entity.ReporteGNB;
import pe.nasqa.values.model.entity.ReporteGNBtoken;
import pe.nasqa.values.model.entity.ReporteGNBvales;
import pe.nasqa.values.model.entity.ReporteHerbalife;
import pe.nasqa.values.model.entity.ReporteBCPdetalleGen;
import pe.nasqa.values.model.entity.ReporteRevistas;
import pe.nasqa.values.model.entity.ReporteOrbinGestion;


@Service
public class ReportesInternosModel{
	
	@Autowired
	ReportesInternosDao reportesInternosDao;
	
	
	@Autowired
	private DistribucionModel distribucionModel;
	
	private Logger log = Logger.getLogger(ReportesInternosModel.class);
	
	@Transactional
	public List<Object> reportePiezasRendidas(String fecDel, String fecA) {
		return reportesInternosDao.reportePiezasRendidas(fecDel, fecA);
	}
	
	@Transactional
	public List<Object> reporteGestionPiezas(String fecDel, String fecA) {
		return reportesInternosDao.reporteGestionPiezas(fecDel, fecA);
	}
	
	@Transactional
	public List<Object> reporteResultadoGestion(String fecDel, String fecA) {
		return reportesInternosDao.reporteResultadoGestion(fecDel, fecA);
	}
	
	@Transactional
	public List<Object> reporteResumenHojaRuta(String fecDel, String fecA) {
		return reportesInternosDao.reporteResumenHojaRuta(fecDel, fecA);
	}
	
	@Transactional
	public List<Object> reporteMovimientoHojaRuta(String fecDel, String fecA) {
		return reportesInternosDao.reporteMovimientoHojaRuta(fecDel, fecA);
	}
	
	@Transactional
	public List<Object> reporteMovimientoMensajero(String fecDel, String fecA) {
		return reportesInternosDao.reporteMovimientoMensajero(fecDel, fecA);
	}
	
	@Transactional
	public List<Object> reporteSeguimientoCoordinaciones(String fecDel, String fecA) {
		return reportesInternosDao.reporteSeguimientoCoordinaciones(fecDel, fecA);
	}
	
	@Transactional
	public List<Object> reporteGestionUsuario(String fecDel, String fecA) {
		return reportesInternosDao.reporteGestionUsuario(fecDel, fecA);
	}
	
	@Transactional
	public List<Object> reporteBcpScc(String fecDel, String fecA) {
		return reportesInternosDao.reporteBcpScc(fecDel, fecA);
	}
	
	@Transactional
	public List<Object> datosDistribucionCoord(String codigo, String fecha) {
		return reportesInternosDao.datosDistribucionCoord(codigo, fecha);
	}
	
	@Transactional
	public List<Object> reporteSituacionDespachoProvincia(String codigo, String fecha) {
		return reportesInternosDao.reporteSituacionDespachoProvincia(codigo, fecha);
	}

//	@Transactional
//	public boolean loadBD_reporte_gnb() {
//		return impExpDbDao.loadBD_reporte_gnb();
//	}
//	
//	@Transactional
//	public boolean importReportGNB(File file) {
//		return impExpDbDao.importReportGNB(file);
//	}
//	
//	@Transactional
//	public boolean importFiles(String path, List<String> unZipFiles) {
//		return impExpDbDao.importFiles(path, unZipFiles);
//	}
//
//	@Transactional
//	public boolean loadDb(int idUser) {
//		return impExpDbDao.loadDb(idUser);
//	}
//	
//	@Transactional
//	public boolean exportFiles(String path, List<String> toZipFiles, String fromDate, String toDate, String reDescargar) {
//		return impExpDbDao.exportRegs(path, toZipFiles, fromDate, toDate, reDescargar);
//	}
	
//	@Transactional
//	public int updateGestion(String pathFile, String codCli) {
//		int cant=0;
//		Map<String, String> mapSituacion=new HashMap<String, String>();
//		Map<String, String> mapEstado=new HashMap<String, String>();
//		
//		mapSituacion.put("1", "Pendiente");
//		mapSituacion.put("2", "Entregada");
//		mapSituacion.put("3", "Imposible");
//		
//		mapEstado.put("1", "En Oficina");
//		mapEstado.put("2", "En Hoja de Ruta");
//		mapEstado.put("3", "Rendido");
//		
//		BufferedReader br;
//        try {
//            br = new BufferedReader(new FileReader(pathFile));
//            try {
//            	String line;
//                while ( (line = br.readLine()) != null ) {
//                    //System.out.println(line);
//                    String fields[] = line.split("\t",-1);
//                    if(fields.length==3){
//                    	Distribucion dist=distribucionDao.getByCodBar(fields[0], codCli);
//                    	if(dist!=null){
//                    		dist.setIndSit(fields[1]);
//                    		dist.setDesSit(mapSituacion.get(fields[1]));
//                    		
//                    		dist.setIndEst(fields[2]);
//                    		dist.setDesEst(mapEstado.get(fields[2]));
//                    		
//                    		cant++;
//                    	}
//
//                    }
//                }
//                br.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } 
//        } catch (FileNotFoundException e) {
//            System.out.println(e);
//            e.printStackTrace();
//        }
//		
//		return cant;
//	}
//	
//	@Transactional(readOnly=true)
//	public List<ExportVisita> getVisitasFromUI(Date fec_inicio, Date fec_fin, String reDescarga){
//		return distribucionDao.getVisitasFromUI(fec_inicio, fec_fin, reDescarga);
//	}
//	
//	@Transactional(readOnly=true)
//	public List<ReporteGNB> getReporteGNB(String fec_inicio, String fec_fin){
//		return distribucionDao.getReporteGNB(fec_inicio, fec_fin);
//	}
//	
//	@Transactional(readOnly=true)
//	public List<ReporteGNBtoken> getReporteGNBtoken(String fec_inicio, String fec_fin){
//		return distribucionDao.getReporteGNBtoken(fec_inicio, fec_fin);
//	}
//
//	@Transactional(readOnly=true)
//	public List<ReporteBCPtc> getReporteBCPtc(String fec_inicio, String fec_fin){
//		return distribucionDao.getReporteBCPtc(fec_inicio, fec_fin);
//	}
//	
//	@Transactional(readOnly=true)
//	public List<ReporteHerbalife> getReporteHerbalife(String fec_inicio, String fec_fin){
//		return distribucionDao.getReporteHerbalife(fec_inicio, fec_fin);
//	}
//	
//	@Transactional(readOnly=true)
//	public List<ReporteBBVApend> getReporteBBVApend(String fec_inicio, String fec_fin){
//		return distribucionDao.getReporteBBVApend(fec_inicio, fec_fin);
//	}
//	
//	@Transactional(readOnly=true)
//	public List<ReporteBBVAentr> getReporteBBVAentr(String fec_inicio, String fec_fin){
//		return distribucionDao.getReporteBBVAentr(fec_inicio, fec_fin);
//	}
//	
//	@Transactional(readOnly=true)
//	public List<ReporteBBVAworkf> getReporteBBVAworkf(String fec_inicio, String fec_fin){
//		return distribucionDao.getReporteBBVAworkf(fec_inicio, fec_fin);
//	}
//	
//	@Transactional(readOnly=true)
//	public List<ReporteGNBvales> getReporteGNBvales(String fec_inicio, String fec_fin){
//		return distribucionDao.getReporteGNBvales(fec_inicio, fec_fin);
//	}
//	
//	@Transactional(readOnly=true)
//	public List<ReporteBCPdetalleGen> getReporteBCPdetallegen(String fec_inicio, String fec_fin){
//		return distribucionDao.getReporteBCPdetalleGen(fec_inicio, fec_fin);
//	}
//	
//	 
//	
//	
//	@Transactional(readOnly=true)
//	public List<ReporteEnvioProv> getReporteEnvioProv(String fec_inicio, String fec_fin){
//		return distribucionDao.getReporteEnvioProv(fec_inicio, fec_fin);
//	}
//	
//
//		
//		@Transactional(readOnly=true)
//		public List<ReporteCoordinacionDia> getReporteCoordinacionDia(String fec_inicio, String fec_fin){
//			return distribucionDao.getReporteCoordinacionDia(fec_inicio, fec_fin);
//		}
//		
//	
//	
//	@Transactional(readOnly=true)
//	public List<ReporteRevistas> getReporteRevistas(String fec_inicio, String fec_fin){
//		return distribucionDao.getReporteRevistas(fec_inicio, fec_fin);
//	}
//	
//	@Transactional(readOnly=true)
//	public List<ReporteOrbinGestion> getReporteoOrbinGestion(String fec_inicio, String fec_fin,int sw ){
//		return distribucionDao.getReporteOrbinGestion(fec_inicio, fec_fin,sw);
//	}
//	
//	@Transactional
//	public boolean updateVisita(String id_visita, String descargado){
//		return distribucionDao.updateVisita(id_visita, descargado);
//	}
//	
//	@Transactional
//	public List<Object> getGestiones(Date fecha){
//		return distribucionDao.getGestiones(fecha);
//	}
}
