package pe.nasqa.values.dao.imp;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.nasqa.values.dao.ReportesInternosDao;


@Repository
public class ReportesInternosDaoImp implements ReportesInternosDao{
	
	@Autowired
	SessionFactory session;
	
	private Logger log = Logger.getLogger(ReportesInternosDao.class);

	
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
	
	
	
	public List<Object> reportePiezasRendidas(String fecDel, String fecA) {
		// TODO Auto-generated method stub
		String sql;
		System.out.println(fecDel+"   "+fecA);
		 sql = " select d.fec_ren, d.nro_ren, d.fec_pro, d.ord_pro, d.cod_cli, d.cod_bar, d.val_nro_ide,"+
				 " d.des_nom_ape, d.tit_num_doc, d.fec_pro as fec_pro2, d.val_fec_emi, d.ind_vin, d.per_rec, d.ind_sit,"+
				 " d.ind_est, d.cod_mot, d.cod_pro_val, "
				 + "(select tipser from tb_servicios where nrobin=SUBSTRING (d.val_nro_ide, 1, 6) limit 1) as tipser,"+//falta indtjt
				 " d.dir_dom_des,d.dir_dom_pos, d.dir_lab_des, d.dir_lab_pos, d.tit_tlf_dom, d.tit_tlf_lab,"+
				 " d.nom_cli, d.des_mot, d.des_sit, d.des_pro_val, d.val_des_emi,d.des_est,d.des_vin, d.tit_nom_ape, d.val_tip_emi"+
				 " from distribucion d"+
				 " where d.fec_ren between to_date(:fec_inicio, 'DD/MM/YYYY') AND to_date(:fec_fin, 'DD/MM/YYYY')";
				
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("fec_inicio", fecDel);
		query.setString("fec_fin", fecA);
		System.out.println("la query: "+sql);
		return query.list();
	}
	
	public List<Object> reporteGestionPiezas(String fecDel, String fecA) {
		// TODO Auto-generated method stub
		String sql;
		System.out.println(fecDel+"   "+fecA);
		 sql = " select d.cod_bar, d.ord_pro, d.val_nro_ide, d.des_nom_ape, d.tit_num_doc, d.tit_tlf_dom, d.tit_tlf_lab,"+
				 " d.dir_dom_des, d.dir_dom_ubi, d.dir_dom_dis, d.dir_dom_pro, d.dir_dom_dep, d.dir_lab_des, "+
				 " case when d.cod_bar!=null then '' end as empresa,"+
				 " case when d.cod_bar!=null then '' end as ruc_emp,"+
				 " case when d.cod_bar!=null then '' end as dir_emp,"+
				 " case when d.cod_bar!=null then '' end as departm,"+
				 " d.fec_pro, d.des_pro_val, "+
				 " (select desser from tb_servicios where nrobin=SUBSTRING (d.val_nro_ide, 1, 6) limit 1) as tipser,"+
				 " d.val_des_emi, case when d.cod_bar!=null then 0 end as cant_dep, case when d.cod_bar!=null then 0 end as peso,"+
				 " (d.des_sit || ' ' || d.des_est), d.fec_ultima, d.res_ultima_visita as res_ultima, d.lugar_ultimo, d.tipo_entrega, "+
				 " d.fec_ultima_visita, res_ultima_visita, (select count(*) from distribucion_visita where cod_bar=d.cod_bar) as cant_vis,"+
				 " (select count(*) from distribucion_coord where cod_bar=d.cod_bar) as cant_coo,"+
				 " (select count(*) from distribucion_coord_telf where cod_bar=d.cod_bar) as cant_seg,"+
			//--visitas
				" (select fec_hoj from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 0 )  as fecha_visita_1,"+
				" (select resultado_visita from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 0 )  as resultado_visita_1,"+
				" (select lugar_visita from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 0 )  as lugar_visita_1,"+
				" (select gestion_visita from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 0 )  as gestion_visita_1,"+
				
				" (select fec_hoj from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 1 )  as fecha_visita_2,"+
				" (select resultado_visita from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 1 )  as resultado_visita_2,"+
				" (select lugar_visita from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 1 )  as lugar_visita_2,"+
				" (select gestion_visita from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 1 )  as gestion_visita_2,"+
			
				" (select fec_hoj from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 2 )  as fecha_visita_3,"+
				" (select resultado_visita from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 2 )  as resultado_visita_3,"+
				" (select lugar_visita from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 2 )  as lugar_visita_3,"+
				" (select gestion_visita from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 2 )  as gestion_visita_3,"+
							
				" (select fec_hoj from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 3 )  as fecha_visita_4,"+
				" (select resultado_visita from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 3 )  as resultado_visita_4,"+
				" (select lugar_visita from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 3 )  as lugar_visita_4,"+
				" (select gestion_visita from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 3 )  as gestion_visita_4,"+
							
				" (select fec_hoj from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 4 )  as fecha_visita_5,"+
				" (select resultado_visita from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 4 )  as resultado_visita_5,"+
				" (select lugar_visita from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 4 )  as lugar_visita_5,"+
				" (select gestion_visita from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 4 )  as gestion_visita_5,"+
							
				" (select fec_hoj from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 5 )  as fecha_visita_6,"+
				" (select resultado_visita from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 5 )  as resultado_visita_6,"+
				" (select lugar_visita from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 5 )  as lugar_visita_6,"+
				" (select gestion_visita from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 5 )  as gestion_visita_6,"+
							
				" (select fec_hoj from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 6 )  as fecha_visita_7,"+
				" (select resultado_visita from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 6 )  as resultado_visita_7,"+
				" (select lugar_visita from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 6 )  as lugar_visita_7,"+
				" (select gestion_visita from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 6 )  as gestion_visita_7,"+
							
				" (select fec_hoj from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 7 )  as fecha_visita_8,"+
				" (select resultado_visita from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 7 )  as resultado_visita_8,"+
				" (select lugar_visita from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 7 )  as lugar_visita_8,"+
				" (select gestion_visita from distribucion_visita where cod_bar=d.cod_bar order by fec_hoj ASC  limit 1 offset 7 )  as gestion_visita_8,"+
			//--coordinaciones--
				" (select fec_coo from distribucion_coord where cod_bar=d.cod_bar order by fec_coo ASC  limit 1 offset 0 )  as fecha_coo_1,"+
				" (select hor_coo from distribucion_coord where cod_bar=d.cod_bar order by fec_coo ASC  limit 1 offset 0 )  as hor_coo_1,"+
				" (select fec_reg from distribucion_coord where cod_bar=d.cod_bar order by fec_coo ASC  limit 1 offset 0 )  as fec_reg_1,"+
				" (select dir_coo from distribucion_coord where cod_bar=d.cod_bar order by fec_coo ASC  limit 1 offset 0 )  as dir_coo_1,"+
				" (select ind_dir from distribucion_coord where cod_bar=d.cod_bar order by fec_coo ASC  limit 1 offset 0 )  as ind_coo_1,"+
			
				" (select fec_coo from distribucion_coord where cod_bar=d.cod_bar order by fec_coo ASC  limit 1 offset 1 )  as fecha_coo_2,"+
				" (select hor_coo from distribucion_coord where cod_bar=d.cod_bar order by fec_coo ASC  limit 1 offset 1 )  as hor_coo_2,"+
				" (select fec_reg from distribucion_coord where cod_bar=d.cod_bar order by fec_coo ASC  limit 1 offset 1 )  as fec_reg_2,"+
				" (select dir_coo from distribucion_coord where cod_bar=d.cod_bar order by fec_coo ASC  limit 1 offset 1 )  as dir_coo_2,"+
				" (select ind_dir from distribucion_coord where cod_bar=d.cod_bar order by fec_coo ASC  limit 1 offset 1 )  as ind_coo_2,"+
							
				" (select fec_coo from distribucion_coord where cod_bar=d.cod_bar order by fec_coo ASC  limit 1 offset 2 )  as fecha_coo_3,"+
				" (select hor_coo from distribucion_coord where cod_bar=d.cod_bar order by fec_coo ASC  limit 1 offset 2 )  as hor_coo_3,"+
				" (select fec_reg from distribucion_coord where cod_bar=d.cod_bar order by fec_coo ASC  limit 1 offset 2 )  as fec_reg_3,"+
				" (select dir_coo from distribucion_coord where cod_bar=d.cod_bar order by fec_coo ASC  limit 1 offset 2 )  as dir_coo_3,"+
				" (select ind_dir from distribucion_coord where cod_bar=d.cod_bar order by fec_coo ASC  limit 1 offset 2 )  as ind_coo_3,"+
			//---cord_telf------
				" (select fec_reg from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 0 )  as fecha_seg_1,"+
				" (select hor_reg from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 0 )  as hora_seg_1,"+
				" (select nro_tlf from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 0 )  as telef_seg_1,"+
				" (select det_obs from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 0 )  as detalle_seg_1,"+
				" (select des_mot from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 0 )  as resultado_seg_1,"+
				" (select ind_seg from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 0 )  as tipo_telf_seg_1,"+
			
				" (select fec_reg from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 1 )  as fecha_seg_2,"+
				" (select hor_reg from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 1 )  as hora_seg_2,"+
				" (select nro_tlf from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 1 )  as telef_seg_2,"+
				" (select det_obs from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 1 )  as detalle_seg_2,"+
				" (select des_mot from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 1 )  as resultado_seg_2,"+
				" (select ind_seg from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 1 )  as tipo_telf_seg_2,"+
							
				" (select fec_reg from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 2 )  as fecha_seg_3,"+
				" (select hor_reg from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 2 )  as hora_seg_3,"+
				" (select nro_tlf from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 2 )  as telef_seg_3,"+
				" (select det_obs from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 2 )  as detalle_seg_3,"+
				" (select des_mot from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 2 )  as resultado_seg_3,"+
				" (select ind_seg from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 2 )  as tipo_telf_seg_3,"+
							
				" (select fec_reg from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 3 )  as fecha_seg_4,"+
				" (select hor_reg from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 3 )  as hora_seg_4,"+
				" (select nro_tlf from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 3 )  as telef_seg_4,"+
				" (select det_obs from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 3 )  as detalle_seg_4,"+
				" (select des_mot from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 3 )  as resultado_seg_4,"+
				" (select ind_seg from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 3 )  as tipo_telf_seg_4,"+
							
				" (select fec_reg from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 4 )  as fecha_seg_5,"+
				" (select hor_reg from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 4 )  as hora_seg_5,"+
				" (select nro_tlf from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 4 )  as telef_seg_5,"+
				" (select det_obs from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 4 )  as detalle_seg_5,"+
				" (select des_mot from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 4 )  as resultado_seg_5,"+
				" (select ind_seg from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 4 )  as tipo_telf_seg_5,"+
							
				" (select fec_reg from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 5 )  as fecha_seg_6,"+
				" (select hor_reg from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 5 )  as hora_seg_6,"+
				" (select nro_tlf from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 5 )  as telef_seg_6,"+
				" (select det_obs from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 5 )  as detalle_seg_6,"+
				" (select des_mot from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 5 )  as resultado_seg_6,"+
				" (select ind_seg from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 5 )  as tipo_telf_seg_6,"+
							
				" (select fec_reg from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 6 )  as fecha_seg_7,"+
				" (select hor_reg from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 6 )  as hora_seg_7,"+
				" (select nro_tlf from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 6 )  as telef_seg_7,"+
				" (select det_obs from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 6 )  as detalle_seg_7,"+
				" (select des_mot from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 6 )  as resultado_seg_7,"+
				" (select ind_seg from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 6 )  as tipo_telf_seg_7,"+
			
				" (select fec_reg from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 7 )  as fecha_seg_8,"+
				" (select hor_reg from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 7 )  as hora_seg_8,"+
				" (select nro_tlf from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 7 )  as telef_seg_8,"+
				" (select det_obs from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 7 )  as detalle_seg_8,"+
				" (select des_mot from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 7 )  as resultado_seg_8,"+
				" (select ind_seg from distribucion_coord_telf where cod_bar=d.cod_bar order by fec_reg ASC  limit 1 offset 7 )  as tipo_telf_seg_8"+
							
				" from distribucion d"+
				" where d.fec_pro  BETWEEN to_date(:fec_inicio, 'DD/MM/YYYY') AND to_date(:fec_fin, 'DD/MM/YYYY')  ";
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("fec_inicio", fecDel);
		query.setString("fec_fin", fecA);
		System.out.println("la query: "+sql);
		return query.list();
	}
	
	public List<Object> reporteResultadoGestion(String fecDel, String fecA) {
		// TODO Auto-generated method stub
		String sql;
		System.out.println(fecDel+"   "+fecA);
		 sql = " select distinct(d.ord_pro), fec_pro as fecrcc,fec_pro, "+
				 " (select count(*) from distribucion where ord_pro=d.ord_pro) as canrcc,"+
				 " (select count(*) from distribucion where ord_pro=d.ord_pro and ind_sit='2') as canent,"+
				 " (select count(*) from distribucion where ord_pro=d.ord_pro and ind_sit='3') as canimp,"+
				 " (select count(*) from distribucion where ord_pro=d.ord_pro and (ind_sit='1' or ind_sit='5')) as canpen,"+
				 " (select count(*) from distribucion_coord where cod_bar=d.cod_bar) as cancoo"+
				 " from distribucion d"+
				 " where d.fec_pro BETWEEN to_date(:fec_inicio, 'DD/MM/YYYY') AND to_date(:fec_fin, 'DD/MM/YYYY')";
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("fec_inicio", fecDel);
		query.setString("fec_fin", fecA);
		System.out.println("la query: "+sql);
		return query.list();
	}
	
	public List<Object> reporteResumenHojaRuta(String fecDel, String fecA) {
		// TODO Auto-generated method stub
		String sql;
		System.out.println(fecDel+"   "+fecA);
		 sql = " select distinct(d.nro_hoj), d.fec_hoj, d.nro_hoj as nrohojrut, m.cod_mensajero, m.nombres, "+
				 " (select count(*) from hoja_ruta_detalle where id_ruta=h.id_ruta) as canent,"+
				 " (select count(*) from hoja_ruta_detalle where id_ruta=h.id_ruta and carga=1) as candra"+
				 " from distribucion d, mensajero m, hoja_ruta h"+
				 " where d.fec_hoj BETWEEN to_date(:fec_inicio, 'DD/MM/YYYY') AND to_date(:fec_fin, 'DD/MM/YYYY')"+
				 " and h.nro_hoja=d.nro_hoj"+
				 " and m.id_mensajero=h.id_mensajero"+
				 " order by d.nro_hoj";
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("fec_inicio", fecDel);
		query.setString("fec_fin", fecA);
		System.out.println("la query: "+sql);
		return query.list();
	}
	
	public List<Object> reporteMovimientoHojaRuta(String fecDel, String fecA) {
		// TODO Auto-generated method stub
		String sql;
		System.out.println(fecDel+"   "+fecA);
		 sql = " select d.fec_hoj, d.nro_hoj, m.cod_mensajero, m.nombres, d.cod_bar, d.des_nom_ape, "+
				 " case when cod_cli='0007' then (select ind_entrega_tarjeta from data_gnbtarjeta where cod_bar=d.cod_bar) else '0' end as inddir,"+
				 " d.ind_sit, d.des_sit, d.cod_mot, d.des_mot, d.ind_est, d.des_est, d.ind_sit as ind_sit2, d.des_sit as des_sit2, d.fec_pro,"+
				 " d.ord_pro, d.cod_cli, d.nom_cli, d.cod_pro_val, d.des_pro_val, d.val_tip_emi, d.val_des_emi, "+
				 " d.tit_num_doc, d.dir_dom_des, d.dir_dom_pos, tit_tlf_dom, d.fec_hoj as fec_hoj2, d.val_fec_emi"+
				 " from distribucion d, hoja_ruta h, mensajero m"+
				 " where d.fec_hoj BETWEEN to_date(:fec_inicio, 'DD/MM/YYYY') AND to_date(:fec_fin, 'DD/MM/YYYY') "+
				 " and h.nro_hoja=d.nro_hoj"+
				 " and m.id_mensajero=h.id_mensajero ";
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("fec_inicio", fecDel);
		query.setString("fec_fin", fecA);
		System.out.println("la query: "+sql);
		return query.list();
	}
	
	public List<Object> reporteMovimientoMensajero(String fecDel, String fecA) {
		// TODO Auto-generated method stub
		String sql;
		System.out.println(fecDel+"   "+fecA);
		 sql = " select d.fec_hoj, d.nro_hoj, d.nro_hoj as nro_hoj2, m.cod_mensajero, m.nombres, d.cod_bar, d.ind_sit,"+
				 " d.des_sit, d.cod_mot, d.des_mot, "+
				 " case when cod_cli='0007' then (select ind_entrega_tarjeta from data_gnbtarjeta where cod_bar=d.cod_bar limit 1) else '0' end as inddir,"+
				 " (select hor_vis from distribucion_visita where nro_hoj=d.nro_hoj order by nro_hoj desc limit 1) as hor_vis, d.fec_pro, d.ord_pro, "+
				 " d.cod_cli, d.nom_cli, d.val_nro_ide, d.cod_pro_val, d.des_pro_val, "+
				 " (select tipser from tb_servicios where nrobin=SUBSTRING (d.val_nro_ide, 1, 6) limit 1) as tipser, d.val_tip_emi,"+
				 " d.des_nom_ape, d.tit_num_doc, d.ind_sit as ind_sit2, d.ind_est, d.tit_tlf_dom, "+
				 " (select c.nro_coo from distribucion_coord c where c.fec_coo=d.fec_hoj and c.cod_bar=d.cod_bar limit 1) as nro_coo,"+
				 " (select c.fec_reg from distribucion_coord c where c.fec_coo=d.fec_hoj and c.cod_bar=d.cod_bar limit 1) as fec_reg,"+
				 " (select c.fec_coo from distribucion_coord c where c.fec_coo=d.fec_hoj and c.cod_bar=d.cod_bar limit 1) as fec_coo,"+
				 " (select c.hor_coo from distribucion_coord c where c.fec_coo=d.fec_hoj and c.cod_bar=d.cod_bar limit 1) as hor_coo,"+
				 " (select c.dir_coo from distribucion_coord c where c.fec_coo=d.fec_hoj and c.cod_bar=d.cod_bar limit 1) as dir_coo,"+
				 " (select c.pos_coo from distribucion_coord c where c.fec_coo=d.fec_hoj and c.cod_bar=d.cod_bar limit 1) as pos_coo,"+
				 " (select c.ind_usu from distribucion_coord c where c.fec_coo=d.fec_hoj and c.cod_bar=d.cod_bar limit 1) as ind_usu,"+
				 " (select c.des_usu from distribucion_coord c where c.fec_coo=d.fec_hoj and c.cod_bar=d.cod_bar limit 1) as des_usu"+
				 " from distribucion d, mensajero m, hoja_ruta h"+
				 " where h.nro_hoja=d.nro_hoj"+
				 " and m.id_mensajero=h.id_mensajero "+
				 " and d.fec_hoj BETWEEN to_date(:fec_inicio, 'DD/MM/YYYY') AND to_date(:fec_fin, 'DD/MM/YYYY') ";
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("fec_inicio", fecDel);
		query.setString("fec_fin", fecA);
		System.out.println("la query: "+sql);
		return query.list();
	}
	
	public List<Object> reporteSeguimientoCoordinaciones(String fecDel, String fecA) {
		// TODO Auto-generated method stub
		String sql;
		System.out.println(fecDel+"   "+fecA);
		 sql = " select d.des_est, d.des_sit, d.des_mot, c.hor_coo, d.nom_cli, "+
				 " (select m.nombres from mensajero m, hoja_ruta h where nro_hoja=d.nro_hoj and m.id_mensajero=h.id_mensajero) as nombres,"+
				 " c.dir_coo, c.pos_coo,"+
				 " d.dir_dom_pro, d.dir_dom_dep, c.ref_coo, d.des_pro_val, d.des_nom_ape, c.obs_coo, c.tlf_coo,"+
				 " d.cod_bar, d.fec_pro, c.fec_coo, c.fec_reg"+
				 " from distribucion d, distribucion_coord c"+
				 " where d.cod_bar=c.cod_bar"+
				 " and c.fec_coo  BETWEEN to_date(:fec_inicio, 'DD/MM/YYYY') AND to_date(:fec_fin, 'DD/MM/YYYY')  ";
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("fec_inicio", fecDel);
		query.setString("fec_fin", fecA);
		System.out.println("la query: "+sql);
		return query.list();
	}
	
	public List<Object> reporteGestionUsuario(String fecDel, String fecA) {
		// TODO Auto-generated method stub
		String sql;
		System.out.println(fecDel+"   "+fecA);
		 sql = " select (select distinct(fec_reg) from distribucion_coord where cod_bar=d.cod_bar and fec_reg BETWEEN to_date(:fec_inicio, 'DD/MM/YYYY') AND to_date(:fec_fin, 'DD/MM/YYYY')"+
				 " union"+
				 " select distinct(fec_reg) from distribucion_coord_telf where cod_bar=d.cod_bar and fec_reg BETWEEN to_date(:fec_inicio, 'DD/MM/YYYY') AND to_date(:fec_fin, 'DD/MM/YYYY')) as fecmov,"+
				
				 " case when (select count(*) from distribucion_coord_telf where cod_bar=d.cod_bar and fec_reg BETWEEN to_date(:fec_inicio, 'DD/MM/YYYY') AND to_date(:fec_fin, 'DD/MM/YYYY'))>0 "+
				 " then (select cod_usu from distribucion_coord_telf where cod_bar=d.cod_bar and fec_reg BETWEEN to_date(:fec_inicio, 'DD/MM/YYYY') AND to_date(:fec_fin, 'DD/MM/YYYY') limit 1) "+
				 " else (select cod_usu from distribucion_coord where cod_bar=d.cod_bar and fec_reg BETWEEN to_date(:fec_inicio, 'DD/MM/YYYY') AND to_date(:fec_fin, 'DD/MM/YYYY') limit 1) end as codusu,"+
				
				 " d.cod_cli, d.ord_pro, d.cod_bar, d.fec_pro, d.val_nro_ide, d.des_nom_ape, d.ind_sit, d. ind_est,  d.cod_mot,d.des_mot, "+
				 " (select fec_coo from distribucion_coord where cod_bar=d.cod_bar order by id DESC limit 1) as feccoo,"+
				 " (select ind_dir from distribucion_coord where cod_bar=d.cod_bar order by id DESC limit 1) as inddir,"+
				 " (select des_dir from distribucion_coord where cod_bar=d.cod_bar order by id DESC limit 1) as desdir,"+
				
				 " (select cod_mot from distribucion_coord_telf where cod_bar=d.cod_bar order by id DESC limit 1) as motseg,"+
				 " (select des_mot from distribucion_coord_telf where cod_bar=d.cod_bar order by id DESC limit 1) as desseg,"+
				 " (select det_obs from distribucion_coord_telf where cod_bar=d.cod_bar order by id DESC limit 1) as desobs,"+
				 " CAST(' ' as varchar(10))  as codofi,"+
				 " (select nro_tlf from distribucion_coord_telf where cod_bar=d.cod_bar order by id DESC limit 1 offset 0) as nrotlf1,"+
				 " (select des_mot from distribucion_coord_telf where cod_bar=d.cod_bar order by id DESC limit 1 offset 0) as obstlf1,"+
				
				 " (select nro_tlf from distribucion_coord_telf where cod_bar=d.cod_bar order by id DESC limit 1 offset 1) as nrotlf2,"+
				 " (select des_mot from distribucion_coord_telf where cod_bar=d.cod_bar order by id DESC limit 1 offset 1) as obstlf2,"+
				
				 " (select nro_tlf from distribucion_coord_telf where cod_bar=d.cod_bar order by id DESC limit 1 offset 2) as nrotlf3,"+
				 " (select des_mot from distribucion_coord_telf where cod_bar=d.cod_bar order by id DESC limit 1 offset 2) as obstlf3,"+
				
				 " (select nro_tlf from distribucion_coord_telf where cod_bar=d.cod_bar order by id DESC limit 1 offset 3) as nrotlf4,"+
				 " (select des_mot from distribucion_coord_telf where cod_bar=d.cod_bar order by id DESC limit 1 offset 3) as obstlf4,"+
				
				 " (select nro_tlf from distribucion_coord_telf where cod_bar=d.cod_bar order by id DESC limit 1 offset 4) as nrotlf5,"+
				 " (select des_mot from distribucion_coord_telf where cod_bar=d.cod_bar order by id DESC limit 1 offset 4) as obstlf5,"+
				
				 " case when (select count(*) from distribucion_coord_telf where cod_bar=d.cod_bar and fec_reg BETWEEN to_date(:fec_inicio, 'DD/MM/YYYY') AND to_date(:fec_fin, 'DD/MM/YYYY'))>0 "+
				 " then (select nom_usu from distribucion_coord_telf where cod_bar=d.cod_bar and fec_reg BETWEEN to_date(:fec_inicio, 'DD/MM/YYYY') AND to_date(:fec_fin, 'DD/MM/YYYY') limit 1) "+
				 " else (select nom_usu from distribucion_coord where cod_bar=d.cod_bar and fec_reg BETWEEN to_date(:fec_inicio, 'DD/MM/YYYY') AND to_date(:fec_fin, 'DD/MM/YYYY') limit 1) end as nomusu,"+
				 " d.nom_cli, CAST(' ' as varchar(10))  as dessit, CAST(' ' as varchar(10))  as desofi, CAST(' ' as varchar(10))  as codter,"+
				 " CAST(' ' as varchar(10))  as dester, "+
				 " (select tipser from tb_servicios where nrobin=SUBSTRING (d.val_nro_ide, 1, 6) limit 1) as tipser, "+
				 " CAST(' ' as varchar(10))  as nomemp, CAST(' ' as varchar(10))  as codemp"+
				 " from distribucion d"+
				 " where d.cod_bar in (select distinct(cod_bar) from distribucion_coord where fec_reg BETWEEN to_date(:fec_inicio, 'DD/MM/YYYY') AND to_date(:fec_fin, 'DD/MM/YYYY') "+
				 " union"+
				 " select distinct(cod_bar) from distribucion_coord_telf where fec_reg BETWEEN to_date(:fec_inicio, 'DD/MM/YYYY') AND to_date(:fec_fin, 'DD/MM/YYYY') )";
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("fec_inicio", fecDel);
		query.setString("fec_fin", fecA);
		System.out.println("la query: "+sql);
		return query.list();
	}
	
	public List<Object> reporteBcpScc(String fecDel, String fecA) {
		// TODO Auto-generated method stub
		String sql;
		System.out.println(fecDel+"   "+fecA);
		 sql = " select d.val_nro_ide, d.cod_bar, b.tipo_tarjeta, b.tipo_emision, b.id_bin, b.id_afinidad,"+
				 " b.nombre_tarjetahabiente, b.nombre_titular, b.nombre_mandatario, b.tipo_doc_mandatario_1,"+
				 " b.num_doc_mandatario_1, b.ruc_empresa, b.nombre_empresa, b.representante_autorizado, b.telefono_contacto,"+
				 " b.tipo_destino, b.id_mensajeria, b.id_zip, b.id_sucursal, b.id_agencia, b.dir_domicilio, b.provincia_domicilio,"+
				 " b.direccion_trabajo, b.provincia_trabajo, b.requiere_contrato, b.requiere_recibo, b.urgente, b.codigo_rel_empaque,"+
				 " b.fecha_bcp, b.canal_venta, b.fecha_concilia_dist, b.tipo_de_cliente, b.id_cliente, b.telefono1,"+
				 " b.telefono2, b.telefono3, b.telefono4, b.telefono5, b.telefono6, d.des_mot"+
				 " from distribucion d, data_bcptarjeta b"+
				 " where d.ind_sit!='2'"+
				 " and d.cod_bar=b.numero_acuse "+
				 " and d.fec_pro  BETWEEN to_date(:fec_inicio, 'DD/MM/YYYY') AND to_date(:fec_fin, 'DD/MM/YYYY')";
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("fec_inicio", fecDel);
		query.setString("fec_fin", fecA);
		System.out.println("la query: "+sql);
		return query.list();
	}
	
	public List<Object> datosDistribucionCoord(String codigo, String fecha) {
		// TODO Auto-generated method stub
		String sql;
		 sql = " select cod_bar, des_dir, pos_coo, dir_coo, hor_coo, fec_coo"+
				 " from distribucion_coord"+
				 " where fec_coo = to_date(:fecha, 'DD/MM/YYYY')"+
				 " and cod_bar=:codigo"+
				 " order by id Desc"
				;
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setString("codigo", codigo);
		query.setString("fecha", fecha);
		System.out.println("la query: "+sql);
		return query.list();
	}
	
	
	public List<Object> reporteSituacionDespachoProvincia(String fecDel, String fecA) {
		// TODO Auto-generated method stub
		String sql;
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

}
