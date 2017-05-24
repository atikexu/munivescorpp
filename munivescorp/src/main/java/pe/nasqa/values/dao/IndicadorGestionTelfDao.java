package pe.nasqa.values.dao;

import java.util.List;

import pe.nasqa.values.model.entity.IndicadorGestionTelf;

public interface IndicadorGestionTelfDao extends IGenericDao<IndicadorGestionTelf, String>{
	
	List<IndicadorGestionTelf> findEnabled();
	
}
