package pe.nasqa.values.model;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.nasqa.values.dao.RendicionDetalleDao;
import pe.nasqa.values.model.entity.HojaRutaDetalle;
import pe.nasqa.values.model.entity.Rendicion;
import pe.nasqa.values.model.entity.RendicionDetalle;

@Service
public class RendicionDetalleModel{
	
	@Autowired
	private RendicionDetalleDao rendicionDetalleDao;

	@Transactional(readOnly = true)
	public RendicionDetalle findOne(long id) {
		return rendicionDetalleDao.findOne(id);
	}

	@Transactional(readOnly = true)
	public List<RendicionDetalle> findAll() {
		return rendicionDetalleDao.findAll();
	}

	@Transactional
	public void create(RendicionDetalle entity) {
		rendicionDetalleDao.create(entity);
	}

	@Transactional
	public void update(RendicionDetalle entity) {
		rendicionDetalleDao.update(entity);
	}
	
	@Transactional
	public void delete(RendicionDetalle entity) {
		rendicionDetalleDao.delete(entity);
	}

	@Transactional
	public void deleteById(long entityId) {
		rendicionDetalleDao.deleteById(entityId);
	}
	
	@Transactional(readOnly = true)
	public RendicionDetalle findUserByName(String hojaRuta){
		return rendicionDetalleDao.findHojaRutaByName(hojaRuta);
	}

	@Transactional
	public List<Object> getRendicionXID(Integer id) {
		return rendicionDetalleDao.getRendicionXID(id);
	}
	
	@Transactional
	public void updateXID(Integer id_ruta) {
		rendicionDetalleDao.updateXID(id_ruta);
	}

	@Transactional
	public void updateDetalleXID(Integer id_ruta) {
		rendicionDetalleDao.updateDetalleXID(id_ruta);
	}
	
	@Transactional
	public void deleteDetalleXIdRendicion(Integer id_rendicion) {
		rendicionDetalleDao.deleteDetalleXIdRendicion(id_rendicion);
	}
	
	@Transactional
	public List<RendicionDetalle> getRendicionXCodBarFec(String cod, Date fec) {
		return rendicionDetalleDao.getRendicionXCodBarFec(cod,fec);
	}

	@Transactional
	public List<RendicionDetalle> cantidadPiezas(Integer id_rendicion) {
		return rendicionDetalleDao.cantidadPiezas(id_rendicion);
	}
	
	@Transactional
	public List<Object> listaRendicionXCodBar(String codBar) {
		return rendicionDetalleDao.listaRendicionXCodBar(codBar);
	}
	
	@Transactional
	public RendicionDetalle getDetalleXIdDetalle(Integer id)  {
		return rendicionDetalleDao.getDetalleXIdDetalle(id);
	}
	
	@Transactional
	public List<Object> listaDetalleXIdRendicion(Integer idRendicion) {
		return rendicionDetalleDao.listaDetalleXIdRendicion(idRendicion);
	}
	
	@Transactional
	public Rendicion getDetalleXIdDetalleHoja(Integer id) {
		return rendicionDetalleDao.getDetalleXIdDetalleHoja(id);
	}
}
