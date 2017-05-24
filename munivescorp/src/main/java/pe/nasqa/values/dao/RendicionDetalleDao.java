package pe.nasqa.values.dao;

import java.util.Date;
import java.util.List;

import pe.nasqa.values.model.entity.Estados;
import pe.nasqa.values.model.entity.Rendicion;
import pe.nasqa.values.model.entity.RendicionDetalle;

public interface RendicionDetalleDao extends IGenericDao<RendicionDetalle, Long>{
	RendicionDetalle findHojaRutaByName(String hojaRuta);
	
	List<Object> getRendicionXID(Integer id);
	
	List<Object> getHojaRutaXFecIdMen(Date fec, Integer idMen);
	
	void updateXID(Integer id_ruta);
	
	void updateDetalleXID(Integer id_ruta);
	
	void deleteDetalleXIdRendicion(Integer id_rendicion);
	
	List<RendicionDetalle> getRendicionXCodBarFec(String cod, Date fec);
	
	List<RendicionDetalle> cantidadPiezas(Integer id_rendicion);
	
	List<Object> listaRendicionXCodBar(String codBar);
	
	RendicionDetalle getDetalleXIdDetalle(Integer id);
	
	List<Object> listaDetalleXIdRendicion(Integer idRendicion);
	
	Rendicion getDetalleXIdDetalleHoja(Integer id);
	
}
