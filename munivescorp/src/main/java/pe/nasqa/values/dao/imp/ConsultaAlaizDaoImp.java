package pe.nasqa.values.dao.imp;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.nasqa.values.dao.ConsultaAlaizDao;


@Repository
public class ConsultaAlaizDaoImp implements ConsultaAlaizDao{
	
	@Autowired
	SessionFactory session;
	
	private Logger log = Logger.getLogger(ConsultaAlaizDao.class);

	
	public boolean importReportGNB(File file) {
		// TODO Auto-generated method stub
		boolean result=false;
		
		int cant=0;
		try {
			
			if(file.exists()){
				session.getCurrentSession().
					createSQLQuery("SELECT fn_import_gnb_report(:pathFile)").
					setParameter("pathFile", file.getAbsolutePath()).
					uniqueResult();
				log.debug("Archivo Importado: "+file);
				cant++;			
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		result=cant==1;
		
		return result;
	}
	
	public boolean loadBD_reporte_gnb() {
		// TODO Auto-generated method stub
		boolean result=false;
		
		int cant=0;
		try {
				session.getCurrentSession().
				createSQLQuery("SELECT fn_load_gnb_report()").
				uniqueResult();
				cant++;
				
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		result=cant==1;
		
		return result;
	}
	
	public boolean importFiles(String path, List<String> files) {
		// TODO Auto-generated method stub
		boolean result=false;
		System.out.println("path:"+path);
		int cant=0;
		try {
			for(String file: files){
				if(file.startsWith("CAB")){
					session.getCurrentSession().
						createSQLQuery("SELECT fn_import_cab(:pathFile)").
						setParameter("pathFile", path+File.separator+file).
						uniqueResult();
					log.debug("Archivo Importado: "+file);
					cant++;
				}else if(file.startsWith("DET")){
					session.getCurrentSession().
						createSQLQuery("SELECT fn_import_det_visita(:pathFile)").
						setParameter("pathFile", path+File.separator+file).
						uniqueResult();
					log.debug("Archivo Importado: "+file);
					cant++;
				}else if(file.startsWith("COO")){
					session.getCurrentSession().
						createSQLQuery("SELECT fn_import_det_coord(:pathFile)").
						setParameter("pathFile", path+File.separator+file).
						uniqueResult();
					log.debug("Archivo Importado: "+file);
					cant++;
				}else if(file.startsWith("SEG")){
					session.getCurrentSession().
						createSQLQuery("SELECT fn_import_det_coord_telf(:pathFile)").
						setParameter("pathFile", path+File.separator+file).
						uniqueResult();
					log.debug("Archivo Importado: "+file);
					cant++;
				}else if(file.startsWith("PAQ")){
					session.getCurrentSession().
						createSQLQuery("SELECT fn_import_det_paquete(:pathFile)").
						setParameter("pathFile", path+File.separator+file).
						uniqueResult();
					log.debug("Archivo Importado: "+file);
					cant++;
				}else{
					log.debug("Archivo Desconocido");
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		result=cant==files.size();
		
		return result;
	}

	
	public boolean loadDb(int idUser) {
		// TODO Auto-generated method stub
		boolean result=false;
		
		int cant=0;
		try {
			session.getCurrentSession().
				createSQLQuery("SELECT fn_load_distribucion(:idUsuario)").
				setParameter("idUsuario", idUser).
				uniqueResult();
			cant++;
			
			session.getCurrentSession().
				createSQLQuery("SELECT fn_load_distribucion_visita(:idUsuario)").
				setParameter("idUsuario", idUser).
				uniqueResult();
			cant++;
			
			session.getCurrentSession().
				createSQLQuery("SELECT fn_load_distribucion_coord(:idUsuario)").
				setParameter("idUsuario", idUser).
				uniqueResult();
			cant++;
			
			session.getCurrentSession().
				createSQLQuery("SELECT fn_load_distribucion_coord_telf(:idUsuario)").
				setParameter("idUsuario", idUser).
				uniqueResult();
			cant++;
			
			session.getCurrentSession().
				createSQLQuery("SELECT fn_load_distribucion_paquete(:idUsuario)").
				setParameter("idUsuario", idUser).
				uniqueResult();
			cant++;

			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		result=cant>0;
		
		return result;
	}

	
	public boolean importCab(String path, List<String> files) {
		// TODO Auto-generated method stub
		boolean result=false;
		
		int cant=0;
		try {
			for(String file: files){
				if(file.startsWith("CAB")){
					session.getCurrentSession().
							createSQLQuery("SELECT fn_import_cab(:pathFile)").
							setParameter("pathFile", path+File.separator+file).
							uniqueResult();
					cant++;
					break;
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		result=cant==1;
		
		return result;
	}

	
	public boolean importDet(String path, List<String> files) {
		// TODO Auto-generated method stub
		boolean result=false;
		
		int cant=0;
		try {
			for(String file: files){
				if(file.startsWith("DET")){
					session.getCurrentSession().
						createSQLQuery("SELECT fn_import_det_visita(:pathFile)").
						setParameter("pathFile", path+File.separator+file).
						uniqueResult();
					cant++;
				}else if(file.startsWith("COO")){
					session.getCurrentSession().
						createSQLQuery("SELECT fn_import_det_coord(:pathFile)").
						setParameter("pathFile", path+File.separator+file).
						uniqueResult();
					cant++;
				}else if(file.startsWith("SEG")){
					session.getCurrentSession().
						createSQLQuery("SELECT fn_import_det_coord_telf(:pathFile)").
						setParameter("pathFile", path+File.separator+file).
						uniqueResult();
					cant++;
				}else if(file.startsWith("PAQ")){
					session.getCurrentSession().
						createSQLQuery("SELECT fn_import_det_paquete(:pathFile)").
						setParameter("pathFile", path+File.separator+file).
						uniqueResult();
					cant++;
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		result=cant==3;
		
		return result;
	}
	
	
	public boolean exportRegs(String path, List<String> files, String fromDate, String toDate, String reDescargar) {
		// TODO Auto-generated method stub
		boolean result=false;
		
		int cant=0;
		try {
			for(String file: files){
				if(file.startsWith("RCOO")){
					session.getCurrentSession().
						createSQLQuery("SELECT fn_export_det_coord(:pathFile, :fromDate, :toDate, :reDescargar)").
						setParameter("pathFile", path+File.separator+file).
						setParameter("fromDate", fromDate).
						setParameter("toDate", toDate).
						setParameter("reDescargar", reDescargar).
						uniqueResult();
					cant++;
				}else if(file.startsWith("RSEG")){
					session.getCurrentSession().
						createSQLQuery("SELECT fn_export_det_coord_telf(:pathFile, :fromDate, :toDate, :reDescargar)").
						setParameter("pathFile", path+File.separator+file).
						setParameter("fromDate", fromDate).
						setParameter("toDate", toDate).
						setParameter("reDescargar", reDescargar).
						uniqueResult();
					cant++;
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		result=cant==2;
		
		return result;
	}
	
	
	
	public List<Object> getLista() {
		// TODO Auto-generated method stub
		String sql;
		 sql = " select d.id_consulta, d.nro_orden, d.codigo, d.nro_archivador, d.nro_tomo, d.detalle, "+
				 " 	o.descripcion as oficina, d.clase_documental, d.referencia, d.fechas_extremas, d.serie_descripcion, "+
				 " 	d.empresa, d.original, d.nro_ori_gen, d.copia, d.nro_copias_gen, d.soporte, "+
				 " 	d.frecuencia_consulta, d.observacion, d.id_usuario, d.fecha_reg"+
				 " 	from documentos d, oficina o"+
				 " 	where d.oficina=o.codigo"+
				 " 	order by d.id_consulta";
				
		Query query = session.getCurrentSession().createSQLQuery(sql);
		System.out.println("la query: "+sql);
		return query.list();
	}
	
}
