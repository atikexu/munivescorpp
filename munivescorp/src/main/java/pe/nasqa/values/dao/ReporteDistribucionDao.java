package pe.nasqa.values.dao;

import java.util.List;

import pe.nasqa.values.model.entity.ReporteDistribucionJoin;

public interface ReporteDistribucionDao {
	void setCountDetalles(String ordPro, int idUsuario);
	void preloadTempDataVisitas(String ordPro, int idUsuario);
	void preloadTempDataCoord(String ordPro, int idUsuario);
	void preloadTempDataCoordTelf(String ordPro, int idUsuario);
	
	List<ReporteDistribucionJoin> getListReportePorOP(String ordPro, int idUsuario);
}
