package pe.nasqa.values.model;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.nasqa.values.dao.RendicionDao;
import pe.nasqa.values.model.entity.Rendicion;

@Service
public class RendicionModel{
	
	@Autowired
	private RendicionDao rendicionDao;

	@Transactional(readOnly = true)
	public Rendicion findOne(long id) {
		return rendicionDao.findOne(id);
	}

	@Transactional(readOnly = true)
	public List<Rendicion> findAll() {
		return rendicionDao.findAll();
	}

	@Transactional
	public void create(Rendicion entity) {
		rendicionDao.create(entity);
	}

	@Transactional
	public void update(Rendicion entity) {
		rendicionDao.update(entity);
	}
	

	@Transactional
	public void delete(Rendicion entity) {
		rendicionDao.delete(entity);
	}

	@Transactional
	public void deleteById(long entityId) {
		rendicionDao.deleteById(entityId);
	}
	
	@Transactional(readOnly = true)
	public Rendicion findRendicionByName(String hojaRuta){
		return rendicionDao.findRendicionByName(hojaRuta);
	}

	@Transactional(readOnly = true)
	public Rendicion getRendicionDetail(Integer hojaRuta){
		return rendicionDao.getRendicionDetail(hojaRuta);
	}
	
	@Transactional
	public List<Rendicion> getRendicion() {
		return rendicionDao.getRendicion();
	}
	
	@Transactional
	public List<Rendicion> getRendicionXID(Integer id) {
		return rendicionDao.getRendicionXID(id);
	}
	
	@Transactional
	public List<Object> getRendicionXFecIdCliente(Date fec, String idMen) {
		return rendicionDao.getRendicionXFecIdCliente(fec,idMen);
	}
	
	@Transactional
	public void updatePiezas(Integer id_ruta, Integer piezas) {
		rendicionDao.updatePiezas(id_ruta,piezas);
	}
	
	@Transactional
	public List<Object> getAgenciasXIdCliente(String idCliente) {
		return rendicionDao.getAgenciasXIdCliente(idCliente);
	}
	
	@Transactional
	public Integer nroRendicionMax() {
		return rendicionDao.nroRendicionMax();
	}
	
	@Transactional
	public Rendicion getRendicionXCodBarRendicion(String codBarRendicion) {
		return rendicionDao.getRendicionXCodBarRendicion(codBarRendicion);
	}
}
