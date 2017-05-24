package pe.nasqa.values.dao.imp;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.nasqa.values.dao.RendicionDao;
import pe.nasqa.values.model.entity.DistribucionCoord;
import pe.nasqa.values.model.entity.Estados;
import pe.nasqa.values.model.entity.HojaRuta;
import pe.nasqa.values.model.entity.Rendicion;

@Repository
public class RendicionDaoImp implements RendicionDao{
	
	@Autowired
	private SessionFactory session;

	
	public Rendicion findOne(Long id) {
		// TODO Auto-generated method stub
		return (Rendicion)session.getCurrentSession().get(Rendicion.class, id);
	}

	
	public List<Rendicion> findAll() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Rendicion order by fecha").list();
	}

	
	public void create(Rendicion entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().persist(entity);
	}

	
	public void update(Rendicion entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().merge(entity);
	}

	
	public void delete(Rendicion entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(entity);
	}

	
	public void deleteById(Long entityId) {
		// TODO Auto-generated method stub
		delete(findOne(entityId));
	}
	
	
	public Rendicion findRendicionByName(String hojaruta){
		Criteria criteria = session.getCurrentSession().createCriteria(Rendicion.class);
//		criteria.setFetchMode("perfiles", FetchMode.JOIN);
		return (Rendicion) criteria.uniqueResult();
	}

	
	public Rendicion getRendicionDetail(Integer hojaruta) {
		Criteria criteria = session.getCurrentSession().createCriteria(Rendicion.class);
		criteria.add(Restrictions.eq("id_ruta", hojaruta));
//		criteria.setFetchMode("valorados", FetchMode.JOIN);
		
//		.setFetchMode("blist", JOIN)
//        .setFetchMode("clist", JOIN)
//        .createAlias("clist", "c")
//        .setFetchMode("c", JOIN)
		//otras tablas
		return (Rendicion) criteria.uniqueResult();
	}

	public List<Rendicion> getRendicion() {
		// TODO Auto-generated method stub
		String sql = " SELECT id_mensajero, cod_mensajero, nombres, apellido_pat, apellido_mat, dni, correo, telefono, empresa, estado "+
					 " FROM mensajero " +
					 " ORDER BY cod_mensajero";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql).addEntity(Rendicion.class);
		
		return query.list();
	}
	
	public List<Rendicion> getRendicionXID(Integer id) {
		// TODO Auto-generated method stub
		String sql = " SELECT id, cod_bar, id_ruta "+
					 " FROM hoja_ruta_detalle " +
					 " WHERE id_ruta=:id_ruta";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setInteger("id_ruta", id);
		return query.list();
	}
	
	public List<Object> getRendicionXFecIdCliente(Date fec, String idCliente) {
		// TODO Auto-generated method stub
		System.out.println("fecha: "+fec+ " id: "+idCliente);
		String sql;
		if(idCliente.equals("")){
			 sql = " SELECT r.id, r.fecha, r.nro_rendicion, r.piezas, c.razonsocial, "+
			 		 " ca.ubigeo, r.situacion, r.motivo, r.documento, ca.direccion, ca.departamento, "+
			 		 " r.usuario, r.fecha_registro, r.estado, ca.nombre, ca.cod_agencia"+
					 " FROM rendicion r, cliente c, cliente_agencia ca" +
					 " WHERE r.fecha=:r.fecha"+
					 " AND r.id_cliente=c.cod_cliente"+
					 " AND r.cod_agencia=ca.cod_agencia";
		}else{
			sql = " SELECT r.id, r.fecha, r.nro_rendicion, r.piezas, c.razonsocial, "+
			 		 " ca.ubigeo, r.situacion, r.motivo, r.documento, ca.direccion, ca.departamento, "+
			 		 " r.usuario, r.fecha_registro, r.estado, ca.nombre, ca.cod_agencia"+
					 " FROM rendicion r, cliente c, cliente_agencia ca" +
					 " WHERE r.fecha=:r.fecha"+
					 " AND r.id_cliente=c.cod_cliente"+
					 " AND r.cod_agencia=ca.cod_agencia"+
					 " AND r.id_cliente=:r.id_cliente";
		}
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setDate("r.fecha", fec);
		if(!idCliente.equals("")){
			query.setString("r.id_cliente", idCliente);
		}
		
		return query.list();
	}
	
	public List<DistribucionCoord> findDirEntregfffffffa(String codBar, Date fecCoo) {
		// TODO Auto-generated method stub
		Criteria criteria = session.getCurrentSession().createCriteria(DistribucionCoord.class);
		//criteria.setFetchMode("visitas", FetchMode.JOIN);
		criteria.add(
				Restrictions.and(
						Restrictions.eq("fecCoo", fecCoo),
						Restrictions.eq("codBar", codBar)
						)
				);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		//return (DistribucionCoord)criteria.uniqueResult();
		return criteria.list();
	}
	
	public void updatePiezas(Integer id_rendicion, Integer piezas) {
		// TODO Auto-generated method stub
		int rs = 0;
		rs=session.getCurrentSession().
		createSQLQuery("UPDATE rendicion SET piezas=:piezas WHERE id=:id").
		setParameter("id", id_rendicion).
		setParameter("piezas", piezas)
		.executeUpdate();
		//return rs>0;
	}


	public List<Object> getAgenciasXIdCliente(String idCliente) {
		System.out.println("id: "+idCliente);
		String sql;
			 sql = " SELECT cod_agencia, cod_cliente, nombre, direccion, departamento, provincia, distrito"+	
					 " FROM cliente_agencia"+
					 " WHERE cod_cliente=:cod_cliente";

		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("cod_cliente", idCliente);
		
		return query.list();
	}

	public Integer nroRendicionMax() {
		// TODO Auto-generated method stub
		String sql;
		 sql = " select max(nro_rendicion) from rendicion";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		
		Integer result = (Integer)query.uniqueResult();
		return result;
	}
	
	public Rendicion getRendicionXCodBarRendicion(String codBarRendicion) {
		// TODO Auto-generated method stub
		String sql;
		 sql = " SELECT * "+	
				 " FROM rendicion" +
				 " WHERE cod_bar_rendicion=:cod_bar_rendicion";
		 Query query = session.getCurrentSession().createSQLQuery(sql).addEntity(Rendicion.class);
		query.setString("cod_bar_rendicion", codBarRendicion);
		return (Rendicion)query.uniqueResult();
	}
}
