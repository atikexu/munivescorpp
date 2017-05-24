package pe.nasqa.values.dao;

import java.util.List;

import pe.nasqa.values.model.entity.Estados;
import pe.nasqa.values.model.entity.ConfigDirecHojaRuta;

public interface ConfigDirecHojaRutaDao extends IGenericDao<ConfigDirecHojaRuta, Long>{
	ConfigDirecHojaRuta findConfigDirecHojaRutaByName(String configDirecHojaRuta);
	
	ConfigDirecHojaRuta getConfigDirecHojaRutaDetail(Integer configDirecHojaRuta);
	
	List<ConfigDirecHojaRuta> getConfigDirecHojaRuta();
	
	List<ConfigDirecHojaRuta> getConfigXClienteProducto(String codClientem, String producto);
	//List<ConfigDirecHojaRuta> getMensajeroXDNI(String dni);
	List<Object> getDomicilioxConfig(String codigo, Integer orden);
}
