package pe.nasqa.values.dao;

import java.io.File;
import java.util.List;

public interface ReportesInternosDao {
	
	
	boolean loadBD_reporte_gnb();
	boolean importReportGNB(File file);
	boolean importFiles(String path, List<String> files);
	boolean loadDb(int idUser);
	
	boolean importCab(String path, List<String> files);
	boolean importDet(String path, List<String> files);
	boolean exportRegs(String path, List<String> files, String fromDate, String toDate, String reDescargar);
	
	List<Object> reportePiezasRendidas(String fecDel, String fecA);
	
	List<Object> reporteGestionPiezas(String fecDel, String fecA);
	
	List<Object> reporteResultadoGestion(String fecDel, String fecA);
	
	List<Object> reporteResumenHojaRuta(String fecDel, String fecA);
	
	List<Object> reporteMovimientoHojaRuta(String fecDel, String fecA);
	
	List<Object> reporteMovimientoMensajero(String fecDel, String fecA);
	
	List<Object> reporteSeguimientoCoordinaciones(String fecDel, String fecA);

	List<Object> reporteGestionUsuario(String fecDel, String fecA);
	
	List<Object> reporteBcpScc(String fecDel, String fecA);
	
	List<Object> datosDistribucionCoord(String codigo, String fecha);
	
	List<Object> reporteSituacionDespachoProvincia(String codigo, String fecha);
}
