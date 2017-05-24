package pe.nasqa.values.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.nasqa.values.dao.IndicadorGestionTelfDao;
import pe.nasqa.values.model.entity.IndicadorGestionTelf;

@Service
public class IndicadorGestionTelfModel {
	
	@Autowired
	private IndicadorGestionTelfDao indicadorGestionTelfDao;
	
	@Transactional(readOnly = true)
	public List<IndicadorGestionTelf> buscarIndicadoresActivos(){
		return indicadorGestionTelfDao.findEnabled();
	}
	
}
