package pe.nasqa.values.dao.imp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.nasqa.values.dao.TipoZonaDao;
import pe.nasqa.values.model.entity.Estados;
import pe.nasqa.values.model.entity.TipoZona;

@Repository
public class TipoZonaDaoImp implements TipoZonaDao{
	
	@Autowired
	private SessionFactory session;

	
	public TipoZona findOne(Long id) {
		// TODO Auto-generated method stub
		return (TipoZona)session.getCurrentSession().get(TipoZona.class, id);
	}

	
	public List<TipoZona> findAll() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from TipoZona order by id").list();
	}

	
	public void create(TipoZona entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().persist(entity);
	}

	
	public void update(TipoZona entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().merge(entity);
	}

	
	public void delete(TipoZona entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(entity);
	}

	
	public void deleteById(Long entityId) {
		// TODO Auto-generated method stub
		delete(findOne(entityId));
	}
	
	
	public TipoZona findMensajeroByName(String mensajero){
		Criteria criteria = session.getCurrentSession().createCriteria(TipoZona.class);
//		criteria.setFetchMode("perfiles", FetchMode.JOIN);
		return (TipoZona) criteria.uniqueResult();
	}

	
	public TipoZona getMensajeroDetail(Integer mensajero) {
		Criteria criteria = session.getCurrentSession().createCriteria(TipoZona.class);
		criteria.add(Restrictions.eq("id_mensajero", mensajero));
//		criteria.setFetchMode("valorados", FetchMode.JOIN);
		
//		.setFetchMode("blist", JOIN)
//        .setFetchMode("clist", JOIN)
//        .createAlias("clist", "c")
//        .setFetchMode("c", JOIN)
		//otras tablas
		return (TipoZona) criteria.uniqueResult();
	}

	public List<TipoZona> getMensajeros() {
		// TODO Auto-generated method stub
		String sql = " SELECT id_mensajero, cod_mensajero, nombres, apellido_pat, apellido_mat, dni, correo, telefono, empresa, estado "+
					 " FROM mensajero " +
					 " ORDER BY cod_mensajero";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql).addEntity(TipoZona.class);
		
		return query.list();
	}
	
	public List<TipoZona> getMensajeroXDNI(String dni) {
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
