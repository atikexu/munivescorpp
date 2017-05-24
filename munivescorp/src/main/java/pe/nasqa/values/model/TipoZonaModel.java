package pe.nasqa.values.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.nasqa.values.dao.TipoZonaDao;
import pe.nasqa.values.model.entity.TipoZona;

@Service
public class TipoZonaModel{
	
	@Autowired
	private TipoZonaDao tipoZonaDao;

	@Transactional(readOnly = true)
	public TipoZona findOne(long id) {
		return tipoZonaDao.findOne(id);
	}

	@Transactional(readOnly = true)
	public List<TipoZona> findAll() {
		return tipoZonaDao.findAll();
	}

	@Transactional
	public void create(TipoZona entity) {
		tipoZonaDao.create(entity);
	}

	@Transactional
	public void update(TipoZona entity) {
		tipoZonaDao.update(entity);
	}
	

	@Transactional
	public void delete(TipoZona entity) {
		tipoZonaDao.delete(entity);
	}

	@Transactional
	public void deleteById(long entityId) {
		tipoZonaDao.deleteById(entityId);
	}


}
