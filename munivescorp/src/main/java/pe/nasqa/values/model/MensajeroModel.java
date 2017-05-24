package pe.nasqa.values.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.nasqa.values.dao.MensajeroDao;
import pe.nasqa.values.model.entity.Mensajero;

@Service
public class MensajeroModel{
	
	@Autowired
	private MensajeroDao mensajeroDao;

	@Transactional(readOnly = true)
	public Mensajero findOne(long id) {
		return mensajeroDao.findOne(id);
	}

	@Transactional(readOnly = true)
	public List<Mensajero> findAll() {
		return mensajeroDao.findAll();
	}

	@Transactional
	public void create(Mensajero entity) {
		mensajeroDao.create(entity);
	}

	@Transactional
	public void update(Mensajero entity) {
		mensajeroDao.update(entity);
	}
	

	@Transactional
	public void delete(Mensajero entity) {
		mensajeroDao.delete(entity);
	}

	@Transactional
	public void deleteById(long entityId) {
		mensajeroDao.deleteById(entityId);
	}
	
	@Transactional(readOnly = true)
	public Mensajero findUserByName(String mensajero){
		return mensajeroDao.findMensajeroByName(mensajero);
	}

	@Transactional(readOnly = true)
	public Mensajero getMensajeroDetail(Integer mensajero){
		return mensajeroDao.getMensajeroDetail(mensajero);
	}
	
	@Transactional
	public List<Mensajero> getMensajeros() {
		return mensajeroDao.getMensajeros();
	}
	
	@Transactional
	public List<Mensajero> getMensajeroXDNI(String dni) {
		return mensajeroDao.getMensajeroXDNI(dni);
	}

}
