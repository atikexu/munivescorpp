package pe.nasqa.values.dao;

import java.util.List;

import pe.nasqa.values.model.entity.Estados;
import pe.nasqa.values.model.entity.TipoZona;

public interface TipoZonaDao extends IGenericDao<TipoZona, Long>{
	TipoZona findMensajeroByName(String mensajero);
	
	TipoZona getMensajeroDetail(Integer mensajero);
	
	List<TipoZona> getMensajeros();
	
	List<TipoZona> getMensajeroXDNI(String dni);
}
