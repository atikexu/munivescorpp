package pe.nasqa.values.model;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.nasqa.values.dao.CoordinacionDao;
import pe.nasqa.values.model.entity.DistribucionCoord;
import pe.nasqa.values.model.entity.RegistroCoord;

@Service
public class CoordinacionModel {
	
	@Autowired
	private CoordinacionDao coordDao;
	
	private Logger log = Logger.getLogger(CoordinacionModel.class);
	
	@Transactional
	public void guardar(RegistroCoord registroCoord){
		coordDao.create(registroCoord);
	}
	
	@Transactional
	public void actualizar(RegistroCoord registroCoord){
		coordDao.update(registroCoord);
	}
	
	@Transactional
	public void remover(Integer idCoordinacion){
		coordDao.deleteById(idCoordinacion);
	}
	
	@Transactional(readOnly = true)
	public RegistroCoord obtenerPorId(Integer idCoordinacion){
		return coordDao.findOne(idCoordinacion);
	}
	
	@Transactional(readOnly = true)
	public List<RegistroCoord> buscarPorCodBar(String codBar){
		return coordDao.findByCodBar(codBar);
	}
	
	@Transactional(readOnly = true)
	public RegistroCoord getLastCoordinacionByCodBar(String codBar){
		return coordDao.getLastCoordinacionByCodBar(codBar);
	}
	
	@Transactional
	public Integer importCoordinacionBaseGen(String fields[],String CodUsuario,Integer Sw,String CodCliente  ) {
		return coordDao.importCoordinacionBaseGen(fields, CodUsuario,Sw,CodCliente);
	}
	
	
	@Transactional
	public Integer importCoordinacionBaseBCP(String fields[],String CodUsuario,Integer Sw ) {
		return coordDao.importCoordinacionBaseBCP(fields, CodUsuario,Sw);
	}
	
	@Transactional
	public boolean importCoordinaciones(File file,String codusuario) {
		return coordDao.importCoordinacion(file,codusuario);
	}
	
	@Transactional
	public boolean importCoordinacionesGen(File file,String codusuario,String CodCliente) {
		return coordDao.importCoordinacionGen(file,codusuario,CodCliente);
	}

	@Transactional
	public void guardarDist(DistribucionCoord distribucionCoord){
		coordDao.guardarDist(distribucionCoord);
	}
	
	@Transactional
	public void actualizarDistCoord(DistribucionCoord distribucionCoord){
		coordDao.actualizarDistCoord(distribucionCoord);
	}
	
	@Transactional
	public void removerDistCoord(DistribucionCoord distribucionCoord){
		coordDao.removerDistCoord(distribucionCoord);
	}

	
	@Transactional(readOnly = true)
	public DistribucionCoord obtenerPorIdDist(Integer idCoordinacion){
		return coordDao.obtenerPorIdDist(idCoordinacion);
	}
}
