package pe.nasqa.values.dao;

import java.util.List;

import pe.nasqa.values.model.entity.DatoBaseLog;

public interface DatoBaseLogDao extends IGenericDao<DatoBaseLog, Integer>{
	List<DatoBaseLog> findDatoBaseLogCliServ(String nroDoc);
	void setDatoBaseLog(String codpro,String nomarchivo, String estado,String usuario);
}
