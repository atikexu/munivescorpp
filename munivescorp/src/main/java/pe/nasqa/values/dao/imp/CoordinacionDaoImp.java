package pe.nasqa.values.dao.imp;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.nasqa.values.dao.CoordinacionDao;

//import pe.nasqa.values.dao.ImpExpDbDao;

import pe.nasqa.values.model.entity.DistribucionCoord;
import pe.nasqa.values.model.entity.Estados;
import pe.nasqa.values.model.entity.RegistroCoord;

@Repository
public class CoordinacionDaoImp implements CoordinacionDao{
	
	@Autowired
	private SessionFactory session;

	
	public RegistroCoord findOne(Integer id) {
		// TODO Auto-generated method stub
		return (RegistroCoord)session.getCurrentSession().get(RegistroCoord.class,id);
	}

	
	public List<RegistroCoord> findAll() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from RegistroCoord").list();
	}

	
	public void create(RegistroCoord entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().persist(entity);
	}

	
	public void update(RegistroCoord entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().merge(entity);
	}

	
	public void delete(RegistroCoord entity) {
		// TODO Auto-generated method stub
		//entity.getIdDistribucion().getRegistroCoordSet().remove(entity);
		session.getCurrentSession().delete(entity);
	}

	
	public void deleteById(Integer entityId) {
		// TODO Auto-generated method stub
		delete(findOne(entityId));
	}

	
	public List<RegistroCoord> findByCodBar(String codBar) {
		// TODO Auto-generated method stub
		Criteria criteria = session.getCurrentSession().createCriteria(RegistroCoord.class);
		criteria.add(Restrictions.and(Restrictions.eq("codBar", codBar), Restrictions.not(Restrictions.eq("flgStt", "UP"))));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.asc("fecCre"));
		return criteria.list();
	}
	
	public RegistroCoord getLastCoordinacionByCodBar(String codBar) {
		// TODO Auto-generated method stub
		String sql = "select * from registro_coord where cod_bar =:codBar order by fec_coo DESC";
		System.out.println(sql+"->codBar:"+codBar);
		Query query = session.getCurrentSession().createSQLQuery(sql).addEntity(RegistroCoord.class);
		query.setMaxResults(1);		
		return (RegistroCoord)query.setString("codBar", codBar).uniqueResult();
	}

	
//private Logger log = Logger.getLogger(ImpExpDbDao.class);
	
	public boolean importCoordinacion(File file,String CodUsuario) {
		// TODO Auto-generated method stub
		boolean result=false;
		System.out.println("SELECT fn_import_data_bcpcoor(:pathFile)"+file.getAbsolutePath() );
		int cant=0;
		try {
			
			if(file.exists()){
				
				
				session.getCurrentSession().
					 createSQLQuery("SELECT fn_import_data_bcpcoor(:pathFile,:codusuario)").
					setParameter("pathFile", file.getAbsolutePath()).
					setParameter("codusuario", CodUsuario).
					uniqueResult();
		//		log.debug("Archivo Importado: "+file);
				cant++;			
				
			}
		} catch (Exception e) {
			System.out.println("errrrrooooooooo..... "+e.getMessage());
	//		log.error(e.getMessage());
		}
		
		result=cant==1;
		
		return result;
	}
	//--Dejar de usar
	public boolean importCoordinacionGen(File file,String CodUsuario,String CodCliente) {
		// TODO Auto-generated method stub
		boolean result=false;
		System.out.println("SELECT fn_import_data_cargacoor(:pathFile)"+file.getAbsolutePath()+"usuario  "+CodUsuario +"codCli"+ CodCliente );
		int cant=0;
		try {
			
			if(file.exists()){
				
				
				session.getCurrentSession().
					 createSQLQuery("SELECT fn_import_data_cargacoor(:pathFile,:codusuario,:codcliente)").
					setParameter("pathFile", file.getAbsolutePath()).
					setParameter("codusuario", CodUsuario).
					setParameter("codcliente", CodCliente).
					uniqueResult();
		//		log.debug("Archivo Importado: "+file);
				cant++;			
				
			}
		} catch (Exception e) {
			System.out.println("Errrrroooooo..... "+e.getMessage());
	//		log.error(e.getMessage());
		}
		
		result=cant==1;
		
		return result;
	}
	

	public Integer importCoordinacionBaseBCP(String fields[],String CodUsuario,Integer Sw) {
		// TODO Auto-generated method stub
		boolean result=false;
		
		int cant=0;
		try {
			
			
			String sql;
			 sql = "SELECT fn_import_CoordinacionBaseBCP(:dat0,:dat1,:dat2,:dat3,:dat4,:dat5,:dat6,:dat7,:dat8,:dat9,:dat10,:dat11,:dat12,:dat13,:dat14,:dat15,:dat16,:codusuario,:sw)";
			//System.out.println(sql);
			Query query = session.getCurrentSession().createSQLQuery(sql);
			query.setString("dat0", fields[0]);
			query.setString("dat1", fields[1]);
			query.setString("dat2", fields[2]);
			query.setString("dat3", fields[3]);
			query.setString("dat4", fields[4]);
			query.setString("dat5", fields[5]);
			query.setString("dat6", fields[6]);
			query.setString("dat7", fields[7]);
			query.setString("dat8", fields[8]);
			query.setString("dat9", fields[9]);
			query.setString("dat10", fields[10]);
			query.setString("dat11", fields[11]);
			query.setString("dat12", fields[12]);
			query.setString("dat13", fields[13]);
			query.setString("dat14", fields[14]);
			query.setString("dat15", fields[15]);
			query.setString("dat16", fields[16]);
			query.setString("codusuario",CodUsuario);
			query.setInteger("sw" , Sw);
				
			System.out.println("antes: "+cant);
			
			cant = (Integer)query.uniqueResult();
			
			System.out.println("despues: "+cant);
			
	//		session.getCurrentSession().
	//				 createSQLQuery("SELECT fn_import_CoordinacionBaseBCP(:dat0,:dat1,:dat2,:dat3,:dat4,:dat5,:dat6,:dat7,:dat8,:dat9,:dat10,:dat11,:dat12,:dat13,:dat14,:dat15,:dat16,:codusuario)").
	//				setParameter("dat0", fields[0]).
	//				setParameter("dat1", fields[1]).
	//				setParameter("dat2", fields[2]).
	//				setParameter("dat3", fields[3]).
	//				setParameter("dat4", fields[4]).
	//				setParameter("dat5", fields[5]).
	//				setParameter("dat6", fields[6]).
	//				setParameter("dat7", fields[7]).
	//				setParameter("dat8", fields[8]).
	//				setParameter("dat9", fields[9]).
	//				setParameter("dat10", fields[10]).
	//				setParameter("dat11", fields[11]).
	//				setParameter("dat12", fields[12]).
	//				setParameter("dat13", fields[13]).
	//				setParameter("dat14", fields[14]).
	//				setParameter("dat15", fields[15]).
	//				setParameter("dat16", fields[16]).
	//				setParameter("codusuario", fields[1]).
	//			   uniqueResult();
				
			
			
		//		log.debug("Archivo Importado: "+file);
				//cant++;			
				
		
		} catch (Exception e) {
			System.out.println("Errrrroooooo..... "+e.getMessage());
			e.printStackTrace();
		}
		
		//result=cant==1;
		//return result;
		return cant;
	}
	

	public Integer importCoordinacionBaseGen(String fields[],String CodUsuario,Integer Sw,String CodCliente) {
		// TODO Auto-generated method stub
		boolean result=false;
		
		int cant=0;
		try {
			
			
			String sql;
			 sql = "SELECT fn_import_CoordinacionBaseGen(:dat0,:dat1,:dat2,:dat3,:dat4,:dat5,:dat6,:dat7,:dat8,:dat9,:dat10,:codusuario,:sw,:codcliente)";
			//System.out.println(sql);
			Query query = session.getCurrentSession().createSQLQuery(sql);
			query.setString("dat0", fields[0]);
			query.setString("dat1", fields[1]);
			query.setString("dat2", fields[2]);
			query.setString("dat3", fields[3]);
			query.setString("dat4", fields[4]);
			query.setString("dat5", fields[5]);
			query.setString("dat6", fields[6]);
			query.setString("dat7", fields[7]);
			query.setString("dat8", fields[8]);
			query.setString("dat9", fields[9]);
			query.setString("dat10", fields[10]);
			query.setString("codusuario",CodUsuario);
			query.setInteger("sw" , Sw);
			query.setString("codcliente" , CodCliente);
			
			cant = (Integer)query.uniqueResult();
			
		//	System.out.println(sql);
			
	
		
		} catch (Exception e) {
			System.out.println("Error..... "+e.getMessage());
	//		log.error(e.getMessage());
		}
		
		//result=cant==1;
		//return result;
		return cant;
	}
	


	
	public void guardarDist(DistribucionCoord entity){
		session.getCurrentSession().persist(entity);
	}
	
	public void actualizarDistCoord(DistribucionCoord entity){
		session.getCurrentSession().merge(entity);
	}
	
	public void removerDistCoord(DistribucionCoord entity){
		session.getCurrentSession().delete(entity);
	}
	
	public DistribucionCoord obtenerPorIdDist(Integer id){
		String sql = "select * from distribucion_coord where id =:id";
		Query query = session.getCurrentSession().createSQLQuery(sql).addEntity(DistribucionCoord.class);	
		return (DistribucionCoord)query.setInteger("id", id).uniqueResult();
	}

}
