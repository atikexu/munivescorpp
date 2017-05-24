package pe.nasqa.values.dao;

import java.util.Date;
import java.util.List;

import pe.nasqa.values.model.entity.ClienteAgencia;
import pe.nasqa.values.model.entity.Estados;
import pe.nasqa.values.model.entity.HojaRuta;
import pe.nasqa.values.model.entity.HojaRutaDetalle;
import pe.nasqa.values.model.entity.Rendicion;

public interface HojaRutaDao extends IGenericDao<HojaRuta, Long>{
	HojaRuta findHojaRutaByName(String hojaRuta);
	
	HojaRuta getHojaRutaDetail(Integer hojaRuta);
	
	List<HojaRuta> getHojaRuta();
	
	List<HojaRuta> getHojaRutaXID(Integer id);
	
	List<Object> getHojaRutaXFecIdMen(Date fec, Integer idMen);
	
	void updatePiezas(Integer id_ruta, Integer piezas);
	
	Integer nroHojaMax();

	List<Object> listXCodBar(String codBarRuta);
	
	HojaRuta getHojaRutaXCodBarRuta(String codBarRuta);
	
	String getZonaXIdRuta(Integer id_ruta);
	
	List<Rendicion> existeHojaRutaXCodBarRuta(String codBarRuta);
	
	ClienteAgencia getClienteAgencia(String cod_agencia);
	
	HojaRuta getHojaRutaXNroHoja(Integer nroHoja);
	
	List<Object> gestionesBeetrack(String fecDel, String fecA);
	
	String getCodHojaRutaxCod(String cod);
}
