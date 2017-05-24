package pe.nasqa.values.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.nasqa.values.dao.ValoradosDao;
import pe.nasqa.values.model.entity.DistribucionPaquete;
import pe.nasqa.values.model.entity.Valorado;

@Service
public class ValoradosModel {
	
	@Autowired
	private ValoradosDao valoradosDao;
	
	@Transactional(readOnly = true)
	public List<Valorado> findAll(){
		return valoradosDao.findAll();
	}
	@Transactional(readOnly=true)
	public List<Valorado> findValoradoCliServ(String nroDoc,  String codCli) {
		return valoradosDao.findValoradoCliServ(nroDoc,codCli);
	}
	@Transactional(readOnly = true)
	public Valorado findOne(Integer id){
		return valoradosDao.findOne(id);
	}

}
