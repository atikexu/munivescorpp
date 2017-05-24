package pe.nasqa.values.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.nasqa.values.dao.PerfilDao;
import pe.nasqa.values.model.entity.Perfil;

@Service
public class PerfilModel {
	
	@Autowired
	private PerfilDao perfilDao;
	
	@Transactional(readOnly = true)
	public List<Perfil> findAll(){
		return perfilDao.findAll();
	}
	
	@Transactional(readOnly = true)
	public Perfil findOne(Integer id){
		return perfilDao.findOne(id);
	}

}
