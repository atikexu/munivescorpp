package pe.nasqa.values.model;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.nasqa.values.dao.HojaRutaDao;
import pe.nasqa.values.model.entity.ClienteAgencia;
import pe.nasqa.values.model.entity.HojaRuta;
import pe.nasqa.values.model.entity.HojaRutaDetalle;
import pe.nasqa.values.model.entity.Rendicion;

@Service
public class HojaRutaModel{
	
	@Autowired
	private HojaRutaDao hojaRutaDao;

	@Transactional(readOnly = true)
	public HojaRuta findOne(long id) {
		return hojaRutaDao.findOne(id);
	}

	@Transactional(readOnly = true)
	public List<HojaRuta> findAll() {
		return hojaRutaDao.findAll();
	}

	@Transactional
	public void create(HojaRuta entity) {
		hojaRutaDao.create(entity);
	}

	@Transactional
	public void update(HojaRuta entity) {
		hojaRutaDao.update(entity);
	}
	

	@Transactional
	public void delete(HojaRuta entity) {
		hojaRutaDao.delete(entity);
	}

	@Transactional
	public void deleteById(long entityId) {
		hojaRutaDao.deleteById(entityId);
	}
	
	@Transactional(readOnly = true)
	public HojaRuta findUserByName(String hojaRuta){
		return hojaRutaDao.findHojaRutaByName(hojaRuta);
	}

	@Transactional(readOnly = true)
	public HojaRuta getMensajeroDetail(Integer hojaRuta){
		return hojaRutaDao.getHojaRutaDetail(hojaRuta);
	}
	
	@Transactional
	public List<HojaRuta> getMensajeros() {
		return hojaRutaDao.getHojaRuta();
	}
	
	@Transactional
	public List<HojaRuta> getHojaRutaXID(Integer id) {
		return hojaRutaDao.getHojaRutaXID(id);
	}
	
	@Transactional
	public List<Object> getHojaRutaXFecIdMen(Date fec, Integer idMen) {
		return hojaRutaDao.getHojaRutaXFecIdMen(fec,idMen);
	}
	
	@Transactional
	public void updatePiezas(Integer id_ruta, Integer piezas) {
		hojaRutaDao.updatePiezas(id_ruta,piezas);
	}
	
	@Transactional
	public Integer nroHojaMax() {
		return hojaRutaDao.nroHojaMax();
	}
	
	@Transactional
	public List<Object> listXCodBar(String codBarRuta) {
		return hojaRutaDao.listXCodBar(codBarRuta);
	}
	
	@Transactional
	public HojaRuta getHojaRutaXCodBarRuta(String codBarRuta) {
		System.out.println("HERE!");
		return hojaRutaDao.getHojaRutaXCodBarRuta(codBarRuta);
	}

	@Transactional
	public String getZonaXIdRuta(Integer id_ruta) {
		return hojaRutaDao.getZonaXIdRuta(id_ruta);
	}
	
	@Transactional
	public List<Rendicion> existeHojaRutaXCodBarRuta(String codBarRuta) {
		System.out.println("MODEL "+codBarRuta);
		return hojaRutaDao.existeHojaRutaXCodBarRuta(codBarRuta);
	}
	
	@Transactional
	public ClienteAgencia getClienteAgencia(String cod_agencia) {
		return hojaRutaDao.getClienteAgencia(cod_agencia);
	}
	
	@Transactional
	public HojaRuta getHojaRutaXNroHoja(Integer nroHoja) {
		return hojaRutaDao.getHojaRutaXNroHoja(nroHoja);
	}
	
	@Transactional
	public List<Object> gestionesBeetrack(String fecDel, String fecA) {
		return hojaRutaDao.gestionesBeetrack(fecDel, fecA);
	}
	
	@Transactional
	public String getCodHojaRutaxCod(String cod) {
		return hojaRutaDao.getCodHojaRutaxCod(cod);
	}
}
