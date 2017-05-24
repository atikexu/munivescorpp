package pe.nasqa.values.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.nasqa.values.dao.DVConstante;
import pe.nasqa.values.dao.ValoradosDao;
import pe.nasqa.values.model.entity.DistribucionPaquete;
import pe.nasqa.values.model.entity.ReporteDistribucionJoin;
import pe.nasqa.values.model.entity.Valorado;

@Repository
public class ValoradosDaoImp implements ValoradosDao{
	
	@Autowired
	private SessionFactory session;

	
	public Valorado findOne(Integer id) {
		// TODO Auto-generated method stub
		return (Valorado)session.getCurrentSession().get(Valorado.class, id);
	}

	
	public List<Valorado> findAll() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Valorado order by Nombre").list();
	}

	
	public void create(Valorado entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().persist(entity);
	}

	
	public void update(Valorado entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().merge(entity);
	}
	
	public List<Valorado> findValoradoCliServ(String nroDoc ,String codCli ) {
		// TODO Auto-generated method stub
		
				
		List<Valorado> result=new ArrayList<Valorado>();
		if(nroDoc.length()>0){
		
			
			String jpql=" select * from valorado where cod_cliente=:codCli and activo='ON'";
					//	"  ( d.cod_tipo = :nroDoc ) ";
			
		//	if(codCli!=null)jpql+= " AND c.codCli = :codCli ";
			   
			  System.out.println("codigo cliente "+ codCli);
			 
			  System.out.println("hola "+ jpql);
			Query query = session.getCurrentSession().createSQLQuery(jpql).addEntity(Valorado.class);
			query.setParameter("codCli", codCli);
			
			       // query.setInteger("idUsuario", idUsuario);
				//	query.setInteger("ordPro", Integer.parseInt(ordPro));
					
		//	Query query=session.getCurrentSession().createQuery(jpql);
		//	query.setParameter("nroDoc", nroDoc);
		//	if(codCli!=null)query.setParameter("codCli", codCli);
			
			result = query.list();
		}
		
		return result;
	}


	
	public void delete(Valorado entity) {
		// TODO Auto-generated method stub
		//session.getCurrentSession().delete(entity);
	}

	
	public void deleteById(Integer entityId) {
		// TODO Auto-generated method stub
		//delete(findOne(entityId));
	}

}
