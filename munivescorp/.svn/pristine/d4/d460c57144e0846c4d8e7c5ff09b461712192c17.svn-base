package pe.nasqa.values.dao.imp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.nasqa.values.dao.CoordinacionTelfDao;
import pe.nasqa.values.model.entity.RegistroCoordTelf;

@Repository
public class CoordinacionTelfDaoImp implements CoordinacionTelfDao{
	
	@Autowired
	private SessionFactory session;

	
	public RegistroCoordTelf findOne(Integer id) {
		// TODO Auto-generated method stub
		return (RegistroCoordTelf)session.getCurrentSession().get(RegistroCoordTelf.class,id);
	}

	
	public List<RegistroCoordTelf> findAll() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from RegistroCoordTelf").list();
	}

	
	public void create(RegistroCoordTelf entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().persist(entity);
	}

	
	public void update(RegistroCoordTelf entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().merge(entity);
	}

	
	public void delete(RegistroCoordTelf entity) {
		// TODO Auto-generated method stub
		//entity.getIdDistribucion().getRegistroCoordTelfSet().remove(entity);
		session.getCurrentSession().delete(entity);
	}

	
	public void deleteById(Integer entityId) {
		// TODO Auto-generated method stub
		delete(findOne(entityId));
	}

	
	public List<RegistroCoordTelf> findByCodBar(String codBar) {
		// TODO Auto-generated method stub
		Criteria criteria = session.getCurrentSession().createCriteria(RegistroCoordTelf.class);
		criteria.add(Restrictions.and(Restrictions.eq("codBar", codBar), Restrictions.not(Restrictions.eq("flgStt", "UP"))));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.asc("fecCre"));
		return criteria.list();
	}

	
}
