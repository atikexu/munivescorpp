package pe.nasqa.values.dao.imp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.nasqa.values.dao.ConfigDirecHojaRutaDao;
import pe.nasqa.values.model.entity.Estados;
import pe.nasqa.values.model.entity.ConfigDirecHojaRuta;

@Repository
public class ConfigDirecHojaRutaDaoImp implements ConfigDirecHojaRutaDao{
	
	@Autowired
	private SessionFactory session;

	
	public ConfigDirecHojaRuta findOne(Long id) {
		// TODO Auto-generated method stub
		return (ConfigDirecHojaRuta)session.getCurrentSession().get(ConfigDirecHojaRuta.class, id);
	}

	
	public List<ConfigDirecHojaRuta> findAll() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from ConfigDirecHojaRuta order by id_config").list();
	}

	
	public void create(ConfigDirecHojaRuta entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().persist(entity);
	}

	
	public void update(ConfigDirecHojaRuta entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().merge(entity);
	}

	
	public void delete(ConfigDirecHojaRuta entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(entity);
	}

	
	public void deleteById(Long entityId) {
		// TODO Auto-generated method stub
		delete(findOne(entityId));
	}
	
	
	public ConfigDirecHojaRuta findConfigDirecHojaRutaByName(String configDirecHojaRuta){
		Criteria criteria = session.getCurrentSession().createCriteria(ConfigDirecHojaRuta.class);
//		criteria.setFetchMode("perfiles", FetchMode.JOIN);
		return (ConfigDirecHojaRuta) criteria.uniqueResult();
	}

	
	public ConfigDirecHojaRuta getConfigDirecHojaRutaDetail(Integer configDirecHojaRuta) {
		Criteria criteria = session.getCurrentSession().createCriteria(ConfigDirecHojaRuta.class);
		criteria.add(Restrictions.eq("id_config", configDirecHojaRuta));
//		criteria.setFetchMode("valorados", FetchMode.JOIN);
		
//		.setFetchMode("blist", JOIN)
//        .setFetchMode("clist", JOIN)
//        .createAlias("clist", "c")
//        .setFetchMode("c", JOIN)
		//otras tablas
		return (ConfigDirecHojaRuta) criteria.uniqueResult();
	}

	public List<ConfigDirecHojaRuta> getConfigDirecHojaRuta() {
		// TODO Auto-generated method stub
		String sql = " SELECT id_mensajero, cod_mensajero, nombres, apellido_pat, apellido_mat, dni, correo, telefono, empresa, estado "+
					 " FROM mensajero " +
					 " ORDER BY cod_mensajero";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql).addEntity(ConfigDirecHojaRuta.class);
		
		return query.list();
	}
	
	public List<ConfigDirecHojaRuta> getConfigXClienteProducto(String cod_cliente, String cod_producto) {
		// TODO Auto-generated method stub
		String sql = " SELECT id_config,cod_cliente,cod_producto, direccion, orden,estado "+
					 " FROM config_direc_hoja_ruta " +
					 " WHERE cod_cliente=:cod_cliente AND cod_producto=:cod_producto"+
					 " ORDER BY orden ASC";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql).addEntity(ConfigDirecHojaRuta.class);
		query.setString("cod_cliente", cod_cliente);
		query.setString("cod_producto", cod_producto);
		return query.list();
	}
	
	public List<Object> getDomicilioxConfig(String cod_bar, Integer orden) {
		// TODO Auto-generated method stub
		String sql = " SELECT c.direccion,d.dir_dom_des,d.dir_lab_des, d.dir_opc_des, c.orden "+
					 " FROM config_direc_hoja_ruta c, distribucion d" +
					 " WHERE d.cod_bar=:d.cod_bar AND c.orden=:c.orden AND c.cod_cliente=d.cod_cli AND c.cod_producto=d.cod_pro_val"+
					 " ORDER BY orden ASC";
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("d.cod_bar", cod_bar);
		query.setInteger("c.orden", orden);
		return query.list();
	}

}
