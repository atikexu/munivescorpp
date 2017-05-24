package pe.nasqa.values.dao.imp;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.nasqa.values.dao.CoordinacionFeriadoDao;
import pe.nasqa.values.model.entity.RegistroCoordTelf;
import pe.nasqa.values.model.entity.RegistroFeriado;

@Repository
public class CoordinacionFeriadoDaoImp implements CoordinacionFeriadoDao{
	
	@Autowired
	private SessionFactory session;

	
	public RegistroFeriado findOne(Integer id) {
		// TODO Auto-generated method stub
		return (RegistroFeriado)session.getCurrentSession().get(RegistroFeriado.class,id);
	}

	
	public List<RegistroFeriado> findAll() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from RegistroFeriado").list();
	}

	
	public void create(RegistroFeriado entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().persist(entity);
	}

	
	public void update(RegistroFeriado entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().merge(entity);
	}

	
	public void delete(RegistroFeriado entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(entity);
	}

	
	public void deleteById(Integer entityId) {
		// TODO Auto-generated method stub
		delete(findOne(entityId));
	}

}
