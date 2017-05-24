package pe.nasqa.values.dao;

import java.io.File;
import java.util.List;

import pe.nasqa.values.model.entity.DistribucionCoord;
import pe.nasqa.values.model.entity.RegistroCoord;

public interface CoordinacionDao extends IGenericDao<RegistroCoord, Integer>{
	List<RegistroCoord> findByCodBar(String codBar);
	boolean importCoordinacion(File file,String codusuario);
	
	Integer importCoordinacionBaseBCP(String fields[],String CodUsuario,Integer Sw);
	Integer importCoordinacionBaseGen(String fields[],String CodUsuario,Integer Sw,String CodCliente);
	
	boolean importCoordinacionGen(File file,String codusuario,String CodCliente);
	RegistroCoord getLastCoordinacionByCodBar(String codBar);
	void guardarDist(DistribucionCoord distribucionCoord);
	void actualizarDistCoord(DistribucionCoord distribucionCoord);
	void removerDistCoord(DistribucionCoord distribucionCoord);
	DistribucionCoord obtenerPorIdDist(Integer idCoordinacion);
}
