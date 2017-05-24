package pe.nasqa.values.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.nasqa.values.dao.DVConstante;
import pe.nasqa.values.dao.DatoBaseLogDao;
import pe.nasqa.values.model.entity.DistribucionPaquete;
import pe.nasqa.values.model.entity.ReporteDistribucionJoin;
import pe.nasqa.values.model.entity.DatoBaseLog;

@Repository
public class DatoBaseLogDaoImp implements DatoBaseLogDao{
	
	@Autowired
	private SessionFactory session;

	
	public DatoBaseLog findOne(Integer id) {
		// TODO Auto-generated method stub
		return (DatoBaseLog)session.getCurrentSession().get(DatoBaseLog.class, id);
	}

	
	public List<DatoBaseLog> findAll() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from DatoBaseLog order by cod_prod").list();
	}

	
	public void create(DatoBaseLog entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().persist(entity);
	}

	
	public void update(DatoBaseLog entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().merge(entity);
	}
	
	
	public List<DatoBaseLog> findDatoBaseLogCliServ(String nroDoc) {
		// TODO Auto-generated method stub
		
				
		List<DatoBaseLog> result=new ArrayList<DatoBaseLog>();
		if(nroDoc.length()>0){
		
			
			String jpql=" select * from DatoBaseLog where cod_pro=:nroDoc and activo='ON'";
					//	"  ( d.cod_tipo = :nroDoc ) ";
			
		//	if(codCli!=null)jpql+= " AND c.codCli = :codCli ";
			   
			  System.out.println("Nro de documento "+ nroDoc);
			 
			  System.out.println("hola "+ jpql);
			Query query = session.getCurrentSession().createSQLQuery(jpql).addEntity(DatoBaseLog.class);
			query.setParameter("nroDoc", nroDoc);
			
			       // query.setInteger("idUsuario", idUsuario);
				//	query.setInteger("ordPro", Integer.parseInt(ordPro));
					
		//	Query query=session.getCurrentSession().createQuery(jpql);
		//	query.setParameter("nroDoc", nroDoc);
		//	if(codCli!=null)query.setParameter("codCli", codCli);
			
			result = query.list();
		}
		
		return result;
	}


	
	
	public void delete(DatoBaseLog entity) {
		// TODO Auto-generated method stub
		//session.getCurrentSession().delete(entity);
	}

	
	public void deleteById(Integer entityId) {
		// TODO Auto-generated method stub
		//delete(findOne(entityId));
	}

	
	public void setDatoBaseLog(String codpro,String nomarchivo,  String estado,String usuario) {
		// TODO Auto-generated method stub
			
			session.getCurrentSession().createSQLQuery("insert into datobase_log(cod_pro,nom_archivo,estado,usuario,fec_carga,fec_mod) values(:codPro,:nomArchivo,:Estado,:Usuario,now(),now())").
			setString("codPro", codpro).
			setString("nomArchivo",nomarchivo).
			setString("Estado",estado).
			setString("Usuario",usuario).
			executeUpdate();
	}
	
}
