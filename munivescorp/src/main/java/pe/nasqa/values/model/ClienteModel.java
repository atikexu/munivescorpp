package pe.nasqa.values.model;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import pe.nasqa.values.dao.ClienteDao;
import pe.nasqa.values.model.entity.Cliente;
import pe.nasqa.values.model.entity.ClienteAgencia;

@Service
public class ClienteModel{
	
	@Autowired
	private ClienteDao clienteDao;
	
	private Logger log = Logger.getLogger(ClienteModel.class);

	@Transactional(readOnly = true)
	public List<Cliente> findAll(){
		return clienteDao.findAll();
	}
	
	@Transactional(readOnly = true)
	public Cliente buscarCliente(String codCliente){
		return clienteDao.findOne(codCliente);
	}
	
	@Transactional
	public void registrarCliente(Cliente cliente){
		clienteDao.create(cliente);
	}
	
	@Transactional
	public void UpdateAgencia(ClienteAgencia agencia){
		clienteDao.UpdateAgencia(agencia);
	}
	
	@Transactional
	public void InsertAgencia(ClienteAgencia agencia){
		clienteDao.InsertAgencia(agencia);
	}
	
	
	
	@Transactional(readOnly = true)
	public List<ClienteAgencia> buscarAgencias(String codCliente){
		return clienteDao.findAgencias(codCliente);
	}
	
	@Transactional(readOnly = true)
	public Integer opcionConServPaqueteria(String codCliente){
		return clienteDao.opcionConServicioPaqueteria(codCliente);
	}
	
	@Transactional
	public boolean updateEstado(String idEstado, String descPersonalizado){
		return clienteDao.updateEstado(idEstado, descPersonalizado);
	}
	
	@Transactional(readOnly = true)
	public boolean hasBusquedaRapida(String codBarra, String nroRef, String docIde){
		return clienteDao.hasBusquedaRapida(codBarra,nroRef,docIde);
	}
	
	@Transactional(readOnly = true)
	public boolean BusquedaRapidaAgencia(String codAgencia, String codCliente){
		return clienteDao.BusquedaRapidaAgencia(codAgencia,codCliente);
	}
	
}
