package pe.nasqa.values.dao;

import java.io.File;
import java.util.List;

public interface ImpExpDbDao {
	
	
	boolean loadBD_reporte_gnb();
	boolean importReportGNB(File file);
	boolean importFiles(String path, List<String> files);
	boolean loadDb(int idUser);
	
	boolean importCab(String path, List<String> files);
	boolean importDet(String path, List<String> files);
	boolean exportRegs(String path, List<String> files, String fromDate, String toDate, String reDescargar);
	
}
