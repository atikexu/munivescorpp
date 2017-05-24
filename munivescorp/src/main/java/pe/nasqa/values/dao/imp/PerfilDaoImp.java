package pe.nasqa.values.dao.imp;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.nasqa.values.dao.PerfilDao;
import pe.nasqa.values.model.entity.Perfil;

@Repository
public class PerfilDaoImp implements PerfilDao{
	
	@Autowired
	private SessionFactory session;

	
	public Perfil findOne(Integer id) {
		// TODO Auto-generated method stub
		return (Perfil)session.getCurrentSession().get(Perfil.class, id);
	}

	
	public List<Perfil> findAll() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Perfil order by perfilNombre").list();
	}

	
	public void create(Perfil entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().persist(entity);
	}

	
	public void update(Perfil entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().merge(entity);
	}

	
	public void delete(Perfil entity) {
		// TODO Auto-generated method stub
		//session.getCurrentSession().delete(entity);
	}

	
	public void deleteById(Integer entityId) {
		// TODO Auto-generated method stub
		//delete(findOne(entityId));
	}

}
