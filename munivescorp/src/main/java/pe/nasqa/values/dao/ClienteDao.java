package pe.nasqa.values.dao;

import java.util.List;

import pe.nasqa.values.model.entity.Cliente;
import pe.nasqa.values.model.entity.ClienteAgencia;

public interface ClienteDao extends IGenericDao<Cliente, String>{
	
	 void UpdateAgencia(ClienteAgencia entity);
	 void InsertAgencia(ClienteAgencia entity);
	 
	List<ClienteAgencia> findAgencias(String codCliente);
	Integer opcionConServicioPaqueteria(String codCliente);
	boolean updateEstado(String idEstado, String descPersonalizado);
	boolean hasBusquedaRapida(String codBarra, String nroRef, String docIde);	
	boolean BusquedaRapidaAgencia(String codAgencia, String codCliente);
	
}
