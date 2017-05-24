package pe.nasqa.values.dao.imp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.nasqa.values.dao.ClienteDao;
import pe.nasqa.values.model.entity.Cliente;
import pe.nasqa.values.model.entity.ClienteAgencia;


@Repository
public class ClienteDaoImp implements ClienteDao{
	
	@Autowired
	private SessionFactory session;

	public Cliente findOne(String id) {
		// TODO Auto-generated method stub
		return (Cliente)session.getCurrentSession().get(Cliente.class,id);
	}

	
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Cliente").list();
	}

	
	public void create(Cliente entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().persist(entity);
	}

	
	public void update(Cliente entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().merge(entity);
	}

	
	public void delete(Cliente entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(entity);
	}

	
	public void deleteById(String entityId) {
		// TODO Auto-generated method stub
		delete(findOne(entityId));
	}

	
	public List<ClienteAgencia> findAgencias(String codCliente) {
		// TODO Auto-generated method stub
		Criteria criteria = session.getCurrentSession().createCriteria(ClienteAgencia.class);
		criteria.add(
				Restrictions.and(
						Restrictions.eq("codCliente", codCliente),
						Restrictions.eq("estado", 1)
						
						)
				);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.asc("codAgencia"));
		return criteria.list();
	}
	
	//Opciones de configuracion.

	
	public Integer opcionConServicioPaqueteria(String codCliente) {
		// TODO Auto-generated method stub
		String sql = " SELECT con_srv_paq FROM cliente_config WHERE cod_cliente=:codCliente";
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("codCliente", codCliente);
		Integer result = (Integer)query.uniqueResult();
		return result;
	}
	
	
	public void UpdateAgencia(ClienteAgencia entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().merge(entity);
	}
	
	public void InsertAgencia(ClienteAgencia entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().persist(entity);
	}
	
	public boolean updateEstado(String idEstado, String descPersonalizado) {
		// TODO Auto-generated method stub
		int rs = 0;
		rs=session.getCurrentSession().
		createSQLQuery("UPDATE estados SET desc_estado_per=:descPerso WHERE id_estado=:idEstado").
		setParameter("idEstado", Integer.parseInt(idEstado)).
		setParameter("descPerso", descPersonalizado)
		.executeUpdate();
		return rs>0;
	}

	public boolean hasBusquedaRapida(String codBarra, String nroRef, String docIde) {
		String sql = " SELECT c.busq_rapida from cliente c, distribucion d where c.cod_cliente = d.cod_cli ";
		if(!codBarra.equals("")){
			sql +=" and d.cod_bar =:codBarra  ";
		}
		if(!nroRef.equals("")){
			sql +="and d.val_nro_ide =:valNroIde  ";
		}
		if(!docIde.equals("")){
			sql +="and d.tit_num_doc =:titNumDoc  ";
		}
		Query query = session.getCurrentSession().createSQLQuery(sql);
		
		if(!codBarra.equals("")){
		query.setString("codBarra", codBarra);
		}
		if(!nroRef.equals("")){
			query.setString("valNroIde", nroRef);
		}
		if(!docIde.equals("")){
			query.setString("titNumDoc", docIde);
		}
		String result = (String)query.uniqueResult();
		if(result!=null && result.equals("1")){
			return true;
		}else {
			return false;
		}
	}

		public boolean BusquedaRapidaAgencia(String codAgencia, String codCliente) {
			String sql = " SELECT cod_cliente from cliente_agencia c where c.cod_agencia = :scodAgencia and c.cod_cliente=:scodCliente";
			
			Query query = session.getCurrentSession().createSQLQuery(sql);
			
				query.setString("scodAgencia", codAgencia);
				query.setString("scodCliente", codCliente);
			
			String result = (String)query.uniqueResult();
			if(result!=null ){
				return true;  //encontro
			}else {
				return false; // no lo encontro
			}
	
	
	}
	
	//SELECT c.busq_rapida from cliente c, distribucion d where c.cod_cliente = d.cod_cli and d.cod_bar = '0800080000030000' and val_nro_ide = '4L01292275' and tit_num_doc ='947584125'; 

}
