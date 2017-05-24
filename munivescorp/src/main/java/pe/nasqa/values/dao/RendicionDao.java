package pe.nasqa.values.dao;

import java.util.Date;
import java.util.List;

import pe.nasqa.values.model.entity.Estados;
import pe.nasqa.values.model.entity.Rendicion;

public interface RendicionDao extends IGenericDao<Rendicion, Long>{
	Rendicion findRendicionByName(String rendicion);
	
	Rendicion getRendicionDetail(Integer rendicion);
	
	List<Rendicion> getRendicion();
	
	List<Rendicion> getRendicionXID(Integer id);
	
	List<Object> getRendicionXFecIdCliente(Date fec, String idCliente);
	
	void updatePiezas(Integer id_ruta, Integer piezas);
	
	List<Object> getAgenciasXIdCliente(String idCliente);
	
	Integer nroRendicionMax();
	
	Rendicion getRendicionXCodBarRendicion(String codBarRendicion);
}
