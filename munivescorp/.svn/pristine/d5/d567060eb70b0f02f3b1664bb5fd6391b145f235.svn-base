package pe.nasqa.values.dao;

import java.util.List;

import pe.nasqa.values.model.entity.Estados;
import pe.nasqa.values.model.entity.Mensajero;

public interface MensajeroDao extends IGenericDao<Mensajero, Long>{
	Mensajero findMensajeroByName(String mensajero);
	
	Mensajero getMensajeroDetail(Integer mensajero);
	
	List<Mensajero> getMensajeros();
	
	List<Mensajero> getMensajeroXDNI(String dni);
}
