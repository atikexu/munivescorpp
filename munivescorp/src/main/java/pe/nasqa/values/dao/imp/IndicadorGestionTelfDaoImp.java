package pe.nasqa.values.dao.imp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.nasqa.values.dao.IndicadorGestionTelfDao;
import pe.nasqa.values.model.entity.IndicadorGestionTelf;


@Repository
public class IndicadorGestionTelfDaoImp implements IndicadorGestionTelfDao{
	
	@Autowired
	private SessionFactory session;

	
	public IndicadorGestionTelf findOne(String id) {
		// TODO Auto-generated method stub
		return (IndicadorGestionTelf)session.getCurrentSession().get(IndicadorGestionTelf.class, id);
	}

	
	public List<IndicadorGestionTelf> findAll() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from IndicadorGestionTelf").list();
	}

	
	public void create(IndicadorGestionTelf entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().persist(entity);
	}

	
	public void update(IndicadorGestionTelf entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().merge(entity);
	}

	
	public void delete(IndicadorGestionTelf entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(entity);
	}

	
	public void deleteById(String entityId) {
		// TODO Auto-generated method stub
		delete(findOne(entityId));
	}

	
	public List<IndicadorGestionTelf> findEnabled() {
		// TODO Auto-generated method stub
		Criteria criteria = session.getCurrentSession().createCriteria(IndicadorGestionTelf.class);
		criteria.add(Restrictions.eq("estGesTel", 1));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

}
