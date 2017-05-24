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

import pe.nasqa.values.dao.RendicionDetalleDao;
import pe.nasqa.values.model.entity.DistribucionCoord;
import pe.nasqa.values.model.entity.Estados;
import pe.nasqa.values.model.entity.HojaRuta;
import pe.nasqa.values.model.entity.HojaRutaDetalle;
import pe.nasqa.values.model.entity.Rendicion;
import pe.nasqa.values.model.entity.RendicionDetalle;

@Repository
public class RendicionDetalleDaoImp implements RendicionDetalleDao{
	
	@Autowired
	private SessionFactory session;

	
	public RendicionDetalle findOne(Long id) {
		// TODO Auto-generated method stub
		return (RendicionDetalle)session.getCurrentSession().get(RendicionDetalle.class, id);
	}

	
	public List<RendicionDetalle> findAll() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from RendicionDetalle order by id").list();
	}

	
	public void create(RendicionDetalle entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().persist(entity);
	}

	
	public void update(RendicionDetalle entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().merge(entity);
	}

	
	public void delete(RendicionDetalle entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(entity);
	}

	
	public void deleteById(Long entityId) {
		// TODO Auto-generated method stub
		delete(findOne(entityId));
	}
	
	
	public RendicionDetalle findHojaRutaByName(String hojaruta){
		Criteria criteria = session.getCurrentSession().createCriteria(RendicionDetalle.class);
//		criteria.setFetchMode("perfiles", FetchMode.JOIN);
		return (RendicionDetalle) criteria.uniqueResult();
	}

	public List<Object> getRendicionXID(Integer id) {
		// TODO Auto-generated method stub
		String sql = " SELECT h.id, h.cargo, d.val_nro_ide, d.des_nom_ape, h.desc_motivo, h.desc_situacion, d.nom_cli,r.estado"+
				 " FROM rendicion_detalle h, distribucion d, rendicion r" +
				 " WHERE h.id_rendicion=:h.id_rendicion AND h.cargo=d.cod_bar AND h.id_rendicion=r.id";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setInteger("h.id_rendicion", id);
		return query.list();
	}
			
	public List<Object> getHojaRutaXFecIdMen(Date fec, Integer idMen) {
		// TODO Auto-generated method stub
		String sql = " SELECT id_ruta, fecha,fecha_proceso, id_mensajero,ruta,piezas,situacion,zona,estado "+
					 " FROM hoja_ruta " +
					 " WHERE fecha=:fecha AND id_mensajero=:id_mensajero";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setDate("fecha", fec);
		query.setInteger("id_mensajero", idMen);
		return query.list();
	}
	
	public void updateXID(Integer id_rendicion) {
		// TODO Auto-generated method stub
		int rs = 0;
		rs=session.getCurrentSession().
		createSQLQuery("UPDATE rendicion SET estado=:estado WHERE id=:id").
		setParameter("id", id_rendicion).
		setParameter("estado", 2)
		.executeUpdate();
		//return rs>0;
	}
	
	public void updateDetalleXID(Integer id_ruta) {
		// TODO Auto-generated method stub
		int rs = 0;
		rs=session.getCurrentSession().
		createSQLQuery("UPDATE hoja_ruta_detalle SET estado=:estado WHERE id_ruta=:id_ruta").
		setParameter("id_ruta", id_ruta).
		setParameter("estado", 2)
		.executeUpdate();
		//return rs>0;
	}
	
	public void deleteDetalleXIdRendicion(Integer id_rendicion) {
		// TODO Auto-generated method stub
		int rs = 0;
		rs=session.getCurrentSession().
		createSQLQuery("DELETE FROM rendicion_detalle WHERE id_rendicion=:id_rendicion").
		setParameter("id_rendicion", id_rendicion)
		.executeUpdate();
		//return rs>0;
	}
	
	public List<RendicionDetalle> getRendicionXCodBarFec(String cod, Date fec) {
		// TODO Auto-generated method stub
		String sql = " SELECT d.ind_sit,h.id, h.cargo, h.id_rendicion "+
					 " FROM rendicion_detalle h, distribucion d" +
					 " WHERE h.cargo=d.cod_bar AND h.fecha=:h.fecha AND h.cargo=:h.cargo";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("h.cargo", cod);
		query.setDate("h.fecha", fec);
		return query.list();
	}
	
	public List<RendicionDetalle> cantidadPiezas(Integer id_rendicion) {
		// TODO Auto-generated method stub
		String sql = " SELECT id FROM rendicion_detalle WHERE id_rendicion=:id_rendicion";
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setInteger("id_rendicion", id_rendicion);
		return query.list();
	}
	
	public HojaRuta listaMotivosEstados(String codBarRuta) {
		// TODO Auto-generated method stub
		String sql;
		 sql = " SELECT * "+	
				 " FROM estados" +
				 " WHERE cod_bar_ruta=:cod_bar_ruta";

		 Query query = session.getCurrentSession().createSQLQuery(sql).addEntity(HojaRuta.class);
		query.setString("cod_bar_ruta", codBarRuta);
		return (HojaRuta)query.uniqueResult();
	}
	
	public List<Object> listaRendicionXCodBar(String codBar) {
		// TODO Auto-generated method stub
		String sql = " SELECT h.id, h.cargo, r.estado"+
				 " FROM rendicion_detalle h, rendicion r" +
				 " WHERE r.cod_bar_rendicion=:r.cod_bar_rendicion AND h.id_rendicion=r.id";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("r.cod_bar_rendicion", codBar);
		return query.list();
	}
	
	public RendicionDetalle getDetalleXIdDetalle(Integer id) {
		// TODO Auto-generated method stub
		String sql;
		 sql = " SELECT * "+	
				 " FROM rendicion_detalle" +
				 " WHERE id=:id";

		 Query query = session.getCurrentSession().createSQLQuery(sql).addEntity(RendicionDetalle.class);
		query.setInteger("id", id);
		return (RendicionDetalle)query.uniqueResult();
	}
	
	public Rendicion getDetalleXIdDetalleHoja(Integer id) {//si es una rendicion
		// TODO Auto-generated method stub
		String sql;
		 sql = " SELECT r.fecha, r.fecha_proceso, r.numero, r.piezas, r.id_cliente, "+
				 " r.destino, r.situacion, r.motivo, r.documento, r.solicitado, "+
				 " r.estado, r.recibido, r.usuario, r.fecha_registro, r.cod_agencia,"+
				 " r.id, r.cod_bar_rendicion, r.nro_rendicion, r.cod_situacion, r.cod_motivo, r.est_carga"+
				 " from hoja_ruta_detalle h, rendicion r" +
				 " where h.id=:h.id AND h.cod_bar=r.cod_bar_rendicion";

		 Query query = session.getCurrentSession().createSQLQuery(sql).addEntity(Rendicion.class);
		query.setInteger("h.id", id);
		return (Rendicion)query.uniqueResult();
	}
	
	public List<Object> listaDetalleXIdRendicion(Integer idRendicion) {
		// TODO Auto-generated method stub
		String sql = " SELECT id, cargo"+
				 " FROM rendicion_detalle " +
				 " WHERE id_rendicion=:id_rendicion";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setInteger("id_rendicion", idRendicion);
		return query.list();
	}
}
