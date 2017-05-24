package pe.nasqa.values.dao;

import java.util.Date;
import java.util.List;

import pe.nasqa.values.model.entity.Estados;
import pe.nasqa.values.model.entity.HojaRutaDetalle;

public interface HojaRutaDetalleDao extends IGenericDao<HojaRutaDetalle, Long>{
	HojaRutaDetalle findHojaRutaByName(String hojaRuta);
	
	HojaRutaDetalle getHojaRutaDetail(Integer hojaRuta);
	
	List<HojaRutaDetalle> getHojaRuta();
	
	List<Object> getHojaRutaXID(Integer id);
	
	List<HojaRutaDetalle> getHojaRutaXCodBar(String cod);
	
	List<Object> getHojaRutaXFecIdMen(Date fec, Integer idMen);
	
	List<HojaRutaDetalle> getDistribucionXCodBar(String cod);
	
	List<HojaRutaDetalle> getDistribucionXCodBarCierre(String cod);
	
	List<HojaRutaDetalle> existeDistribucionXCodBar(String cod);
	
	List<HojaRutaDetalle> getHojaRutaXCodBarFec(String cod, Date fec);
	
	List<HojaRutaDetalle> cantidadPiezas(Integer id_ruta);
	
	void updateXID(Integer id_ruta);
	
	void updateDetalleXID(Integer id_ruta);
	
	void deleteDetalleXIdRuta(Integer id_ruta);
	
	List<Object> listaClientesXIdRuta(Integer idRuta);
	
	List<Object> listaEntregados(Integer idRuta);
	
	HojaRutaDetalle getDetalleXIdDetalle(Integer id);
	
	HojaRutaDetalle getDetalleXCodBarIdRendicion(Integer id,String codBar);
	
	List<Object> listaCargados(Integer idRuta);
	
	List<Object> existeCodEnHojaRuta(Integer idRuta,String codBarDet);
	
	List<Object> cantidadHojaRutaDetalle(Integer id_ruta) ;
	
	List<HojaRutaDetalle> getHojaRutaRendicionXCodBarFec(String cod, Date fec);
	
	List<HojaRutaDetalle> getConGestion(String cod);
	
	List<Object> codbarClienteSeleccionado(String cod,Integer idRendicion);
	
	List<Object> existeRendicionXCodBar(String cod);
	
	void updateDetalleCargaXID(Integer id_ruta, String codbar);
	List<Object> getHojaRutaXCodBarIdRuta(String codigo,Integer idRuta);
	
}
