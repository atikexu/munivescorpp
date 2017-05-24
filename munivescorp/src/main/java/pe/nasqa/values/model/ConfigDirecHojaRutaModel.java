package pe.nasqa.values.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.nasqa.values.dao.ConfigDirecHojaRutaDao;
import pe.nasqa.values.model.entity.ConfigDirecHojaRuta;

@Service
public class ConfigDirecHojaRutaModel{
	
	@Autowired
	private ConfigDirecHojaRutaDao configDirecHojaRutaDao;

	@Transactional(readOnly = true)
	public ConfigDirecHojaRuta findOne(long id) {
		return configDirecHojaRutaDao.findOne(id);
	}

	@Transactional(readOnly = true)
	public List<ConfigDirecHojaRuta> findAll() {
		return configDirecHojaRutaDao.findAll();
	}

	@Transactional
	public void create(ConfigDirecHojaRuta entity) {
		configDirecHojaRutaDao.create(entity);
	}

	@Transactional
	public void update(ConfigDirecHojaRuta entity) {
		configDirecHojaRutaDao.update(entity);
	}
	

	@Transactional
	public void delete(ConfigDirecHojaRuta entity) {
		configDirecHojaRutaDao.delete(entity);
	}

	@Transactional
	public void deleteById(long entityId) {
		configDirecHojaRutaDao.deleteById(entityId);
	}
	
	@Transactional(readOnly = true)
	public ConfigDirecHojaRuta findConfigDirecHojaRutaByName(String configDirecHojaRuta){
		return configDirecHojaRutaDao.findConfigDirecHojaRutaByName(configDirecHojaRuta);
	}

	@Transactional(readOnly = true)
	public ConfigDirecHojaRuta getMensajeroDetail(Integer configDirecHojaRuta){
		return configDirecHojaRutaDao.getConfigDirecHojaRutaDetail(configDirecHojaRuta);
	}
	
	@Transactional
	public List<ConfigDirecHojaRuta> getConfigDirecHojaRuta() {
		return configDirecHojaRutaDao.getConfigDirecHojaRuta();
	}
	
	@Transactional
	public List<ConfigDirecHojaRuta> getConfigXClienteProducto(String codClientem, String producto) {
		return configDirecHojaRutaDao.getConfigXClienteProducto(codClientem, producto);
	}
	
	@Transactional
	public List<Object> getDomicilioxConfig(String codClientem, Integer orden) {
		return configDirecHojaRutaDao.getDomicilioxConfig(codClientem, orden);
	}
}
