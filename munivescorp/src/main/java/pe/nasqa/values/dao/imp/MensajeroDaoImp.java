package pe.nasqa.values.dao.imp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.nasqa.values.dao.MensajeroDao;
import pe.nasqa.values.model.entity.Estados;
import pe.nasqa.values.model.entity.Mensajero;

@Repository
public class MensajeroDaoImp implements MensajeroDao{
	
	@Autowired
	private SessionFactory session;

	
	public Mensajero findOne(Long id) {
		// TODO Auto-generated method stub
		return (Mensajero)session.getCurrentSession().get(Mensajero.class, id);
	}

	
	public List<Mensajero> findAll() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Mensajero order by cod_mensajero").list();
	}

	
	public void create(Mensajero entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().persist(entity);
	}

	
	public void update(Mensajero entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().merge(entity);
	}

	
	public void delete(Mensajero entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(entity);
	}

	
	public void deleteById(Long entityId) {
		// TODO Auto-generated method stub
		delete(findOne(entityId));
	}
	
	
	public Mensajero findMensajeroByName(String mensajero){
		Criteria criteria = session.getCurrentSession().createCriteria(Mensajero.class);
//		criteria.setFetchMode("perfiles", FetchMode.JOIN);
		return (Mensajero) criteria.uniqueResult();
	}

	
	public Mensajero getMensajeroDetail(Integer mensajero) {
		Criteria criteria = session.getCurrentSession().createCriteria(Mensajero.class);
		criteria.add(Restrictions.eq("id_mensajero", mensajero));
//		criteria.setFetchMode("valorados", FetchMode.JOIN);
		
//		.setFetchMode("blist", JOIN)
//        .setFetchMode("clist", JOIN)
//        .createAlias("clist", "c")
//        .setFetchMode("c", JOIN)
		//otras tablas
		return (Mensajero) criteria.uniqueResult();
	}

	public List<Mensajero> getMensajeros() {
		// TODO Auto-generated method stub
		String sql = " SELECT id_mensajero, cod_mensajero, nombres, apellido_pat, apellido_mat, dni, correo, telefono, empresa, estado "+
					 " FROM mensajero " +
					 " ORDER BY cod_mensajero";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql).addEntity(Mensajero.class);
		
		return query.list();
	}
	
	public List<Mensajero> getMensajeroXDNI(String dni) {
		// TODO Auto-generated method stub
		String sql = " SELECT id_mensajero "+
					 " FROM mensajero " +
					 " WHERE dni=:dni";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("dni", dni);
		return query.list();
	}
}
