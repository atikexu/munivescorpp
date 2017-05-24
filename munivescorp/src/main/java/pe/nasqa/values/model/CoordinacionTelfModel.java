package pe.nasqa.values.model;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.nasqa.values.dao.CoordinacionTelfDao;
import pe.nasqa.values.model.entity.RegistroCoordTelf;

@Service
public class CoordinacionTelfModel {
	
	@Autowired
	private CoordinacionTelfDao coordTelfDao;
	
	private Logger log = Logger.getLogger(CoordinacionTelfModel.class);
	
	@Transactional
	public void guardar(RegistroCoordTelf registroCoordTelf){
		coordTelfDao.create(registroCoordTelf);
	}
	
	@Transactional
	public void actualizar(RegistroCoordTelf registroCoordTelf){
		coordTelfDao.update(registroCoordTelf);
	}
	
	@Transactional
	public void remover(Integer idCoordinacionTelf){
		coordTelfDao.deleteById(idCoordinacionTelf);
	}
	
	@Transactional(readOnly = true)
	public RegistroCoordTelf obtenerPorId(Integer idCoordinacionTelf){
		return coordTelfDao.findOne(idCoordinacionTelf);
	}
	
	@Transactional(readOnly = true)
	public List<RegistroCoordTelf> buscarPorCodBar(String codBar){
		return coordTelfDao.findByCodBar(codBar);
	}
}
