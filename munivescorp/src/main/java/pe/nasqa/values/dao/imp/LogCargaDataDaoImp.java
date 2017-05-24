package pe.nasqa.values.dao.imp;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


//import pe.dataimagenes.dao.ResultSet;
//import pe.dataimagenes.dao.SQLException;
//import pe.dataimagenes.dao.String;
import pe.nasqa.values.dao.LogCargaDataDao;
import pe.nasqa.values.model.entity.LogCargaData;


@Repository
public class LogCargaDataDaoImp implements LogCargaDataDao{
	
	@Autowired
	private SessionFactory session;


	
	
	public void create(LogCargaData entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().persist(entity);
	}

	
	public void update(LogCargaData entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().merge(entity);
	}

	
	public void delete(LogCargaData entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(entity);
	}

	

	public boolean countLogCargaDataNombre(String nombre) {		
			
	        String consultaSQL =  "SELECT 	COUNT(1) as cont "
	        					+ "FROM 	log_cargadata "
	        					+ "WHERE 	nombre = :nombre";
	        
	        Query query = session.getCurrentSession().createSQLQuery(consultaSQL);
			query.setString("nombre", nombre);
						
			BigInteger result = (BigInteger)query.uniqueResult();
			
			if (result == BigInteger.valueOf(0)){
				return false;
			}
			else
				return true;
			
	
	}
	

	
	public List<LogCargaData> getLogCargaDataXnombre(String nombre ) {
		// TODO Auto-generated method stub
		String sql = " SELECT * "+
					 " FROM LogCargaData " ;
					 
		//System.out.println(sql);
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("nombre", nombre);
		return query.list();
	}
	
	public void inserLogCargaData(String nombre,String fecha,String usuario) {
		// TODO Auto-generated method stub
		int rs = 0;
		rs=session.getCurrentSession().		
		createSQLQuery("INSERT INTO public.log_cargadata(nombre, fecha, usuario) values ( :nombre,to_date(:fecha,'DD/MM/YYYY'),:usuario)").
		
		setString("nombre",nombre).
		setString("fecha", fecha).
		setString("usuario",usuario)
		.executeUpdate();
		
	//	System.out.println("Insertar log cargada");
		//return rs>0;
	}
	
	
	
}
