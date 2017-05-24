package pe.nasqa.values.model;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.nasqa.values.dao.DatoBaseLogDao;
import pe.nasqa.values.model.entity.HojaRuta;

@Service
public class DatoBaseModel{
	
	@Autowired
	private DatoBaseLogDao datoBaseLogDao;
	/*
	@Transactional(readOnly = true)
	public DatoBaseModel findOne(int id) {
		return datoBaseLogDao.findOne(id);
	}

	@Transactional(readOnly = true)
	public List<HojaRuta> findAll() {
		return datoBaseLogDao.findAll();
	}

	@Transactional
	public void create(DatoBaseModel entity) {
		datoBaseLogDao.create(entity);
	}

	@Transactional
	public void update(DatoBaseModel entity) {
		datoBaseLogDao.update(entity);
	}
	

	@Transactional
	public void delete(DatoBaseModel entity) {
		datoBaseLogDao.delete(entity);
	}

	@Transactional
	public void deleteById(long entityId) {
		datoBaseLogDao.deleteById(entityId);
	}
	
	@Transactional(readOnly = true)
	public HojaRuta findUserByName(String hojaRuta){
		return datoBaseModel.findDatoBaseModelByName(datoBaseModel);
	}

	@Transactional(readOnly = true)
	public HojaRuta getMensajeroDetail(Integer hojaRuta){
		return datoBaseModel.getHojaRutaDetail(hojaRuta);
	}
	
	@Transactional
	public List<HojaRuta> getMensajeros() {
		return datoBaseModel.getHojaRuta();
	}
	
	@Transactional
	public List<HojaRuta> getMensajeroXDNI(String dni) {
		return datoBaseModel.getMensajeroXDNI(dni);
	}
	
	@Transactional
	public List<HojaRuta> getHojaRutaXFecIdMen(Date fec, Integer idMen) {
		return hojaRutaDao.getHojaRutaXFecIdMen(fec,idMen);
	}
	*/
	
	@Transactional
	public void setDatoBaseLog(String codpro,String nomarchivo, String estado,String usuario) {
		datoBaseLogDao.setDatoBaseLog(codpro,nomarchivo,estado,usuario);
	}
}
