package pe.nasqa.values.dao;

import java.util.List;

import pe.nasqa.values.model.entity.Valorado;

public interface ValoradosDao extends IGenericDao<Valorado, Integer>{
	List<Valorado> findValoradoCliServ(String nroDoc,String codCli );
}
