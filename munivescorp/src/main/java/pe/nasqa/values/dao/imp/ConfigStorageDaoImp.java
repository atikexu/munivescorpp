package pe.nasqa.values.dao.imp;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.nasqa.values.dao.ConfigStorageDao;
import pe.nasqa.values.model.entity.Cliente;
import pe.nasqa.values.model.entity.ConfigStorage;

@Repository
public class ConfigStorageDaoImp implements ConfigStorageDao{
	
	@Autowired
	private SessionFactory session;

	
	public ConfigStorage findOne(Integer id) {
		// TODO Auto-generated method stub
		return (ConfigStorage)session.getCurrentSession().get(ConfigStorage.class,id);
	}

	
	public List<ConfigStorage> findAll() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from ConfigStorage").list();
	}

	
	public void create(ConfigStorage entity) {
		// TODO Auto-generated method stub
		// not found
	}

	
	public void update(ConfigStorage entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().merge(entity);
	}

	
	public void delete(ConfigStorage entity) {
		// TODO Auto-generated method stub
		// not found
	}

	
	public void deleteById(Integer entityId) {
		// TODO Auto-generated method stub
		// not found
	}

}
