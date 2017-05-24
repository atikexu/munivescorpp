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

import pe.nasqa.values.dao.HojaRutaDetalleDao;
import pe.nasqa.values.model.entity.DistribucionCoord;
import pe.nasqa.values.model.entity.Estados;
import pe.nasqa.values.model.entity.HojaRutaDetalle;
import pe.nasqa.values.model.entity.RendicionDetalle;

@Repository
public class HojaRutaDetalleDaoImp implements HojaRutaDetalleDao{
	
	@Autowired
	private SessionFactory session;

	
	public HojaRutaDetalle findOne(Long id) {
		// TODO Auto-generated method stub
		return (HojaRutaDetalle)session.getCurrentSession().get(HojaRutaDetalle.class, id);
	}

	
	public List<HojaRutaDetalle> findAll() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from HojaRutaDetalle order by id").list();
	}

	
	public void create(HojaRutaDetalle entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().persist(entity);
	}

	
	public void update(HojaRutaDetalle entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().merge(entity);
	}

	
	public void delete(HojaRutaDetalle entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(entity);
	}

	
	public void deleteById(Long entityId) {
		// TODO Auto-generated method stub
		delete(findOne(entityId));
	}
	
	
	public HojaRutaDetalle findHojaRutaByName(String hojaruta){
		Criteria criteria = session.getCurrentSession().createCriteria(HojaRutaDetalle.class);
//		criteria.setFetchMode("perfiles", FetchMode.JOIN);
		return (HojaRutaDetalle) criteria.uniqueResult();
	}

	
	public HojaRutaDetalle getHojaRutaDetail(Integer hojaruta) {
		Criteria criteria = session.getCurrentSession().createCriteria(HojaRutaDetalle.class);
		criteria.add(Restrictions.eq("id_ruta", hojaruta));
//		criteria.setFetchMode("valorados", FetchMode.JOIN);
		
//		.setFetchMode("blist", JOIN)
//        .setFetchMode("clist", JOIN)
//        .createAlias("clist", "c")
//        .setFetchMode("c", JOIN)
		//otras tablas
		return (HojaRutaDetalle) criteria.uniqueResult();
	}

	public List<HojaRutaDetalle> getHojaRuta() {
		// TODO Auto-generated method stub
		String sql = " SELECT id_mensajero, cod_mensajero, nombres, apellido_pat, apellido_mat, dni, correo, telefono, empresa, estado "+
					 " FROM mensajero " +
					 " ORDER BY cod_mensajero";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql).addEntity(HojaRutaDetalle.class);
		
		return query.list();
	}
	
	/*public List<Object> getHojaRutaXID(Integer id) {//antes de estar con rendiciones
		// TODO Auto-generated method stub
		String sql = " SELECT h.id, h.cod_bar, h.id_ruta, d.nom_cli, d.val_nro_ide, d.tit_nom_ape, d.des_mot, d.des_sit, h.estado"+
				 " FROM hoja_ruta_detalle h, distribucion d" +
				 " WHERE h.id_ruta=:h.id_ruta AND h.cod_bar=d.cod_bar";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setInteger("h.id_ruta", id);
		return query.list();
	}*/
	
	public List<Object> getHojaRutaXID(Integer id) {//con rendiciones
		// TODO Auto-generated method stub
		String sql = " SELECT h.id, h.cod_bar, h.id_ruta, d.nom_cli, d.val_nro_ide, d.tit_nom_ape, d.des_mot, d.des_sit, h.estado,h.fecha"+
				 " FROM hoja_ruta_detalle h, distribucion d" +
				 " WHERE h.id_ruta=:h.id_ruta AND h.cod_bar=d.cod_bar"+
				 " UNION ALL"+
				 " SELECT h.id, h.cod_bar, h.id_ruta, c.razonsocial, d.solicitado, ca.direccion, d.motivo, d.situacion, h.estado,h.fecha"+
				 " FROM hoja_ruta_detalle h, rendicion d, cliente c,cliente_agencia ca" +
				 " WHERE h.id_ruta=:h.id_ruta AND h.cod_bar=d.cod_bar_rendicion AND c.cod_cliente=d.id_cliente AND h.direccion=ca.cod_agencia";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setInteger("h.id_ruta", id);
		return query.list();
	}
	
	public List<HojaRutaDetalle> getHojaRutaXCodBar(String cod) {
		// TODO Auto-generated method stub
		String sql = " SELECT d.ind_sit,h.id, h.cod_bar, h.id_ruta "+
					 " FROM hoja_ruta_detalle h, distribucion d" +
					 " WHERE h.cod_bar=d.cod_bar AND d.ind_sit='1' AND h.cod_bar=:h.cod_bar";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("h.cod_bar", cod);
		return query.list();
	}
	
	public List<HojaRutaDetalle> getDistribucionXCodBar(String cod) {
		// TODO Auto-generated method stub
		String sql = " SELECT ind_sit"+
					 " FROM distribucion" +
					 " WHERE cod_bar=:cod_bar AND (ind_sit='2' OR ind_sit='4')";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("cod_bar", cod);
		return query.list();
	}
	
	public List<HojaRutaDetalle> getDistribucionXCodBarCierre(String cod) {
		// TODO Auto-generated method stub
		String sql = " SELECT ind_sit"+
					 " FROM distribucion" +
					 " WHERE cod_bar=:cod_bar AND (ind_sit='2' OR ind_sit='4' OR ind_est='4')";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("cod_bar", cod);
		return query.list();
	}
	
	public List<HojaRutaDetalle> existeDistribucionXCodBar(String cod) {
		// TODO Auto-generated method stub
		String sql = " SELECT ind_sit"+
					 " FROM distribucion" +
					 " WHERE cod_bar=:cod_bar";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("cod_bar", cod);
		return query.list();
	}
	
	public List<Object> codbarClienteSeleccionado(String cod,Integer idRendicion){
		String sql = " SELECT d.cod_bar"+
				 " FROM rendicion r, distribucion d" +
				 " WHERE r.id=:idRendicion"+
				 " AND d.cod_bar=:cod_bar"+
				 " AND d.cod_cli=r.id_cliente";
	//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("cod_bar", cod);
		query.setInteger("idRendicion", idRendicion);
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
	
	public List<HojaRutaDetalle> getHojaRutaXCodBarFec(String cod, Date fec) {
		// TODO Auto-generated method stub
		String sql = " SELECT d.ind_sit,h.id, h.cod_bar, h.id_ruta "+
					 " FROM hoja_ruta_detalle h, distribucion d" +
					 " WHERE h.cod_bar=d.cod_bar AND h.fecha=:h.fecha AND h.cod_bar=:h.cod_bar";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("h.cod_bar", cod);
		query.setDate("h.fecha", fec);
		return query.list();
	}
	//rendicion en hoja de ruta
	public List<HojaRutaDetalle> getHojaRutaRendicionXCodBarFec(String cod, Date fec) {
		// TODO Auto-generated method stub
		String sql = " SELECT h.id, h.cod_bar, h.id_ruta "+
					 " FROM hoja_ruta_detalle h, rendicion r" +
					 " WHERE h.cod_bar=r.cod_bar_rendicion AND h.fecha=:h.fecha AND h.cod_bar=:h.cod_bar";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("h.cod_bar", cod);
		query.setDate("h.fecha", fec);
		return query.list();
	}
	
	public List<HojaRutaDetalle> cantidadPiezas(Integer id_ruta) {
		// TODO Auto-generated method stub
		String sql = " SELECT id_ruta FROM hoja_ruta_detalle WHERE id_ruta=:id_ruta";
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setInteger("id_ruta", id_ruta);
		return query.list();
	}
	
	public void updateXID(Integer id_ruta) {
		// TODO Auto-generated method stub
		int rs = 0;
		rs=session.getCurrentSession().
		createSQLQuery("UPDATE hoja_ruta SET estado=:estado WHERE id_ruta=:id_ruta").
		setParameter("id_ruta", id_ruta).
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
	
	public void deleteDetalleXIdRuta(Integer id_ruta) {
		// TODO Auto-generated method stub
		int rs = 0;
		rs=session.getCurrentSession().
		createSQLQuery("DELETE FROM hoja_ruta_detalle WHERE id_ruta=:id_ruta").
		setParameter("id_ruta", id_ruta)
		.executeUpdate();
		//return rs>0;
	}
	
	public List<Object> listaClientesXIdRuta(Integer idRuta) {
		// TODO Auto-generated method stub
		String sql = /*" select d.cod_cli, d.nom_cli ,count(*)"+
					 " from hoja_ruta_detalle h, distribucion d"  +
					 " where h.cod_bar=d.cod_bar"+ 
					 " AND h.id_ruta=:h.id_ruta"+
					 " group by d.cod_cli,d.nom_cli ";*/
					"select d.cod_cli, d.nom_cli ,count(*)"+
					" from hoja_ruta_detalle h, distribucion d"+  
					" where h.cod_bar=d.cod_bar"+
					" AND h.id_ruta=:h.id_ruta"+
					" group by d.cod_cli,d.nom_cli"+
					" UNION ALL"+
					" select r.id_cliente, c.razonsocial ,count(*)"+
					" from hoja_ruta_detalle h, rendicion r  ,cliente c"+
					" where h.cod_bar=r.cod_bar_rendicion"+
					" AND c.cod_cliente=r.id_cliente"+
					" AND h.id_ruta=:h.id_ruta"+
					" group by r.id_cliente,c.razonsocial";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setInteger("h.id_ruta", idRuta);
		return query.list();
	}
	
	public List<Object> listaEntregados(Integer idRuta) {
		// TODO Auto-generated method stub
		String sql = " select d.ind_sit"+
					 " from hoja_ruta_detalle h, distribucion d"  +
					 " where h.cod_bar=d.cod_bar"+ 
					 " AND d.ind_sit='2'"+
					 " AND h.id_ruta=:h.id_ruta";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setInteger("h.id_ruta", idRuta);
		return query.list();
	}
	
	public HojaRutaDetalle getDetalleXIdDetalle(Integer id) {
		// TODO Auto-generated method stub
		String sql;
		 sql = " SELECT * "+	
				 " FROM hoja_ruta_detalle" +
				 " WHERE id=:id";

		 Query query = session.getCurrentSession().createSQLQuery(sql).addEntity(HojaRutaDetalle.class);
		query.setInteger("id", id);
		return (HojaRutaDetalle)query.uniqueResult();
	}
	
	public HojaRutaDetalle getDetalleXCodBarIdRendicion(Integer id,String codBar) {
		// TODO Auto-generated method stub
		String sql;
		 sql = " SELECT * "+	
				 " FROM hoja_ruta_detalle" +
				 " WHERE id_ruta=:id_ruta AND cod_bar=:cod_bar";

		 Query query = session.getCurrentSession().createSQLQuery(sql).addEntity(HojaRutaDetalle.class);
		query.setInteger("id_ruta", id);
		query.setString("cod_bar", codBar);
		return (HojaRutaDetalle)query.uniqueResult();
	}
	
	public List<Object> listaCargados(Integer idRuta) {
		// TODO Auto-generated method stub
		String sql = " select *"+
					 " from hoja_ruta_detalle "  +
					 " where id_ruta=:id_ruta" +
					 " AND carga=1";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setInteger("id_ruta", idRuta);
		return query.list();
	}
	
	public List<Object> existeCodEnHojaRuta(Integer idRuta,String codBarDet) {
		// TODO Auto-generated method stub
		String sql = " select *"+
					 " from hoja_ruta_detalle "  +
					 " where id_ruta=:id_ruta"+
					 " AND cod_bar=:cod_bar";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setInteger("id_ruta", idRuta);
		query.setString("cod_bar", codBarDet);
		return query.list();
	}
	
	public List<Object> cantidadHojaRutaDetalle(Integer id_ruta) {
		// TODO Auto-generated method stub
		String sql = " SELECT id_ruta,cod_bar,id FROM hoja_ruta_detalle WHERE id_ruta=:id_ruta";
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setInteger("id_ruta", id_ruta);
		return query.list();
	}
	
	public List<HojaRutaDetalle> getConGestion(String cod) {
		// TODO Auto-generated method stub
		String sql = " SELECT cod_bar"+
					 " FROM hoja_ruta_detalle" +
					 " WHERE cod_bar=:cod_bar" +
					 " AND carga=:carga";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("cod_bar", cod);
		query.setInteger("carga", 0);
		return query.list();
	}
	
	public List<Object> existeRendicionXCodBar(String codBar){
		String sql = " SELECT cargo"+
				 " FROM rendicion_detalle" +
				 " WHERE cargo=:cod_bar";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("cod_bar", codBar);
		return query.list();
	}
	
	public void updateDetalleCargaXID(Integer id_ruta, String codbar) {
		// TODO Auto-generated method stub
		int rs = 0;
		rs=session.getCurrentSession().
		createSQLQuery("UPDATE hoja_ruta_detalle SET carga=:estado WHERE id_ruta=:id_ruta AND cod_bar=:cod_bar").
		setParameter("id_ruta", id_ruta).
		setParameter("cod_bar", codbar).
		setParameter("estado", 1)
		.executeUpdate();
		//return rs>0;
	}

	public List<Object> getHojaRutaXCodBarIdRuta(String codigo,Integer idRuta){
		String sql = " SELECT d.cod_bar"+
				 " FROM hoja_ruta h, hoja_ruta_detalle d" +
				 " WHERE h.id_ruta=:id_ruta"+
				 " AND h.id_ruta=d.id_ruta AND d.cod_bar=:cod_bar";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setInteger("id_ruta", idRuta);
		query.setString("cod_bar", codigo);
		return query.list();
	}
}
