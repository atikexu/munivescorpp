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

import pe.nasqa.values.dao.HojaRutaDao;
import pe.nasqa.values.model.entity.ClienteAgencia;
import pe.nasqa.values.model.entity.DistribucionCoord;
import pe.nasqa.values.model.entity.Estados;
import pe.nasqa.values.model.entity.HojaRuta;
import pe.nasqa.values.model.entity.HojaRutaDetalle;
import pe.nasqa.values.model.entity.Rendicion;

@Repository
public class HojaRutaDaoImp implements HojaRutaDao{
	
	@Autowired
	private SessionFactory session;

	
	public HojaRuta findOne(Long id) {
		// TODO Auto-generated method stub
		return (HojaRuta)session.getCurrentSession().get(HojaRuta.class, id);
	}

	
	public List<HojaRuta> findAll() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from HojaRuta order by fecha").list();
	}

	
	public void create(HojaRuta entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().persist(entity);
	}

	
	public void update(HojaRuta entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().merge(entity);
	}

	
	public void delete(HojaRuta entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(entity);
	}

	
	public void deleteById(Long entityId) {
		// TODO Auto-generated method stub
		delete(findOne(entityId));
	}
	
	
	public HojaRuta findHojaRutaByName(String hojaruta){
		Criteria criteria = session.getCurrentSession().createCriteria(HojaRuta.class);
//		criteria.setFetchMode("perfiles", FetchMode.JOIN);
		return (HojaRuta) criteria.uniqueResult();
	}

	
	public HojaRuta getHojaRutaDetail(Integer hojaruta) {
		Criteria criteria = session.getCurrentSession().createCriteria(HojaRuta.class);
		criteria.add(Restrictions.eq("id_ruta", hojaruta));
//		criteria.setFetchMode("valorados", FetchMode.JOIN);
		
//		.setFetchMode("blist", JOIN)
//        .setFetchMode("clist", JOIN)
//        .createAlias("clist", "c")
//        .setFetchMode("c", JOIN)
		//otras tablas
		return (HojaRuta) criteria.uniqueResult();
	}

	public List<HojaRuta> getHojaRuta() {
		// TODO Auto-generated method stub
		String sql = " SELECT id_mensajero, cod_mensajero, nombres, apellido_pat, apellido_mat, dni, correo, telefono, empresa, estado "+
					 " FROM mensajero " +
					 " ORDER BY cod_mensajero";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql).addEntity(HojaRuta.class);
		
		return query.list();
	}
	
	public List<HojaRuta> getHojaRutaXID(Integer id) {
		// TODO Auto-generated method stub
		String sql = " SELECT id, cod_bar, id_ruta "+
					 " FROM hoja_ruta_detalle " +
					 " WHERE id_ruta=:id_ruta";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setInteger("id_ruta", id);
		return query.list();
	}
	
	public List<Object> getHojaRutaXFecIdMen(Date fec, Integer idMen) {
		// TODO Auto-generated method stub
		System.out.println("fecha: "+fec+ " id: "+idMen);
		String sql;
		if(idMen==0){
			 sql = " SELECT h.id_ruta, h.fecha, h.fecha_proceso, m.nombres, h.ruta, h.piezas, e.desc_estado, t.descripcion, h.estado, "+
					 " m.apellido_pat, m.apellido_mat,m.id_mensajero,h.zona"+	
					 " FROM hoja_ruta h, mensajero m, tipo_zona t, estados_hoja_ruta e" +
					 " WHERE h.fecha=:h.fecha AND h.id_mensajero=m.id_mensajero AND h.zona=t.codigo AND h.estado=e.codigo";
		}else{
			sql = " SELECT h.id_ruta, h.fecha, h.fecha_proceso, m.nombres, h.ruta, h.piezas, e.desc_estado, t.descripcion, h.estado, "+
					 " m.apellido_pat, m.apellido_mat,m.id_mensajero,h.zona"+	
					 " FROM hoja_ruta h, mensajero m, tipo_zona t, estados_hoja_ruta e" +
					 " WHERE h.fecha=:h.fecha AND h.id_mensajero=:h.id_mensajero AND h.id_mensajero=m.id_mensajero AND h.zona=t.codigo AND h.estado=e.codigo";
		}
		System.out.println(sql+"   "+fec  );
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setDate("h.fecha", fec);
		if(idMen!=0){
			query.setInteger("h.id_mensajero", idMen);
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
	
	public void updatePiezas(Integer id_ruta, Integer piezas) {
		// TODO Auto-generated method stub
		int rs = 0;
		rs=session.getCurrentSession().
		createSQLQuery("UPDATE hoja_ruta SET piezas=:piezas WHERE id_ruta=:id_ruta").
		setParameter("id_ruta", id_ruta).
		setParameter("piezas", piezas)
		.executeUpdate();
		//return rs>0;
	}
	
	public Integer nroHojaMax() {
		// TODO Auto-generated method stub
		String sql;
		 sql = " select max(nro_hoja) from hoja_ruta";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		
		Integer result = (Integer)query.uniqueResult();
		return result;
	}
	
	public List<Object> listXCodBar(String codBarRuta) {
		// TODO Auto-generated method stub
		String sql;
		 sql = " SELECT d.id, d.cod_bar"+
				 " FROM hoja_ruta h, hoja_ruta_detalle d" +
				 " WHERE h.cod_bar_ruta=:h.cod_bar_ruta AND h.id_ruta=d.id_ruta";
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("h.cod_bar_ruta", codBarRuta);

		return query.list();
	}
	
	public HojaRuta getHojaRutaXCodBarRuta(String codBarRuta) {
		// TODO Auto-generated method stub
		String sql;
		 sql = " SELECT * "+	
				 " FROM hoja_ruta" +
				 " WHERE cod_bar_ruta=:cod_bar_ruta OR ruta=:cod_bar_ruta";
		 Query query = session.getCurrentSession().createSQLQuery(sql).addEntity(HojaRuta.class);
		query.setString("cod_bar_ruta", codBarRuta);
		return (HojaRuta)query.uniqueResult();
	}
	
	public String getZonaXIdRuta(Integer id_ruta) {
		// TODO Auto-generated method stub
		String sql;
		 sql = " select zona from hoja_ruta"
		 		+ " where id_ruta=:id_ruta";
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setInteger("id_ruta", id_ruta);
		String result = (String)query.uniqueResult();
		return result;
	}
	
	public List<Rendicion> existeHojaRutaXCodBarRuta(String codBarRuta) {
		// TODO Auto-generated method stub
		String sql = " SELECT *"+
					 " FROM rendicion" +
					 " WHERE cod_bar_rendicion=:cod_bar_rendicion";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql).addEntity(Rendicion.class);
		query.setString("cod_bar_rendicion", codBarRuta);
		return query.list();
	}
	
	public ClienteAgencia getClienteAgencia(String cod_agencia) {
		// TODO Auto-generated method stub
		String sql;
		 sql = " SELECT * "+	
				 " FROM cliente_agencia" +
				 " WHERE cod_agencia=:cod_agencia";

		 Query query = session.getCurrentSession().createSQLQuery(sql).addEntity(ClienteAgencia.class);
		query.setString("cod_agencia", cod_agencia);
		return (ClienteAgencia)query.uniqueResult();
	}
	
	public HojaRuta getHojaRutaXNroHoja(Integer nroHoja) {
		// TODO Auto-generated method stub
		String sql;
		 sql = " SELECT * "+	
				 " FROM hoja_ruta" +
				 " WHERE nro_hoja=:nro_hoja";

		 Query query = session.getCurrentSession().createSQLQuery(sql).addEntity(HojaRuta.class);
		query.setInteger("nro_hoja", nroHoja);
		return (HojaRuta)query.uniqueResult();
	}
	
	public List<Object> gestionesBeetrack(String fecDel, String fecA) {
		// TODO Auto-generated method stub
		String sql;
		System.out.println(fecDel+"   "+fecA);
		 sql = " select h.fecha, h.nro_hoja, m.cod_mensajero, concat(m.nombres,' ',m.apellido_pat,' ',m.apellido_mat) as nom,"+
				" hd.cod_bar, d.des_nom_ape, 0 as inddir, d.ind_sit, d.des_sit, d.cod_mot, d.des_mot, d.ind_est, d.des_est, "+
				" d.ind_sit as sitpza, d.des_sit as dessitpza, d.fec_pro, d.ord_pro, d.cod_cli, d.nom_cli, d.cod_pro_val, d.des_pro_val,"+ 
				" d.val_tip_emi, e.des_emision, d.tit_num_doc, d.dir_dom_des, d.dir_dom_pos, d.tit_tlf_dom,CAST('  -   -' as varchar(10))  as fecact, d.val_fec_emi,"+
				" d.val_nro_ide"+
				" from hoja_ruta h left join mensajero m"+
				" on"+
				" h.id_mensajero=m.id_mensajero"+ 
				" left join hoja_ruta_detalle hd"+
				" on h.id_ruta=hd.id_ruta"+
				" left join distribucion d"+
				"  on hd.cod_bar=d.cod_bar"+
				" left join tipo_emision e"+
				"  on e.cod_emision=d.val_tip_emi"+
				" where h.fecha  BETWEEN to_date(:fec_inicio, 'DD/MM/YYYY') AND to_date(:fec_fin, 'DD/MM/YYYY')  ";
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("fec_inicio", fecDel);
		query.setString("fec_fin", fecA);
		System.out.println("la query: "+sql);
		return query.list();
	}
	
	public String getCodHojaRutaxCod(String cod) {
		// TODO Auto-generated method stub
		String sql;
		 sql = " select h.cod_bar_ruta from hoja_ruta h, hoja_ruta_detalle d"
		 		+ " where d.cod_bar=:cod_bar"
				 + " and d.id_ruta=h.id_ruta"
		 		  + " order by d.id_ruta desc limit 1";
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("cod_bar", cod);
		String result = (String)query.uniqueResult();
		return result;
	}
}
