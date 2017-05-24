package pe.nasqa.values.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.nasqa.values.dao.ReporteDistribucionDao;
import pe.nasqa.values.model.entity.ReporteDistribucionJoin;

@Repository
public class ReporteDistribucionDaoImp implements ReporteDistribucionDao{
	
	@Autowired
	private SessionFactory session;

	
	public void setCountDetalles(String ordPro, int idUsuario) {
		// TODO Auto-generated method stub
		String sql = " SELECT fn_load_distribucion_count(:ordPro) ";
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setInteger("ordPro", Integer.parseInt(ordPro));
		query.list();
	}

	
	public void preloadTempDataVisitas(String ordPro, int idUsuario) {
		// TODO Auto-generated method stub
		String sql = " SELECT fn_reporte_preload_dist_visita(:ordPro,:idUsuario) ";
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setInteger("ordPro", Integer.parseInt(ordPro));
		query.setInteger("idUsuario", idUsuario);
		query.list();
	}

	
	public void preloadTempDataCoord(String ordPro, int idUsuario) {
		// TODO Auto-generated method stub
		String sql = " SELECT fn_reporte_preload_dist_coord(:ordPro,:idUsuario) ";
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setInteger("ordPro", Integer.parseInt(ordPro));
		query.setInteger("idUsuario", idUsuario);
		query.list();
	}
	
	public void preloadTempDataCoordTelf(String ordPro, int idUsuario) {
		// TODO Auto-generated method stub
		String sql = " SELECT fn_reporte_preload_dist_coord_telf(:ordPro,:idUsuario) ";
		Query query = session.getCurrentSession().createSQLQuery(sql);
		query.setInteger("ordPro", Integer.parseInt(ordPro));
		query.setInteger("idUsuario", idUsuario);
		query.list();
	}

	
	public List<ReporteDistribucionJoin> getListReportePorOP(String ordPro, int idUsuario) {
		// TODO Auto-generated method stub
		String sql = " SELECT d.*, " +
					 " v.det_visita1,v.det_visita2,v.det_visita3,v.det_visita4,v.det_visita5,v.det_visita6,v.det_visita7,v.det_visita8,v.det_visita9,v.det_visita10, " +
					 " c.det_coord1,c.det_coord2,c.det_coord3,c.det_coord4,c.det_coord5,c.det_coord6,c.det_coord7,c.det_coord8,c.det_coord9,c.det_coord10, " +
					 " t.det_coord_telf1,t.det_coord_telf2,t.det_coord_telf3,t.det_coord_telf4,t.det_coord_telf5,t.det_coord_telf6,t.det_coord_telf7,t.det_coord_telf8,t.det_coord_telf9,t.det_coord_telf10, " +
					 " u.departamento, u.provincia, u.distrito "+
					 " FROM distribucion d " +
					 " left join fn_reporte_load_dist_coord(d.cod_bar,:idUsuario) c on d.cod_bar=c.cod_bar " +
					 " left join fn_reporte_load_dist_visita(d.cod_bar,:idUsuario) v on d.cod_bar=v.cod_bar " +
					 " left join fn_reporte_load_dist_coord_telf(d.cod_bar,:idUsuario) t on d.cod_bar=t.cod_bar " +
					 " left join ubigeo u on u.ubigeo = d.dir_dom_ubi "+
					 " WHERE d.ord_pro=:ordPro ORDER BY d.id_import; ";
		
		System.out.println("-----------ordpro>>"+ordPro);
		System.out.println(idUsuario);
		System.out.println("----------->>"+ sql);
		
		Query query = session.getCurrentSession().createSQLQuery(sql).addEntity(ReporteDistribucionJoin.class);
		query.setInteger("idUsuario", idUsuario);
		query.setInteger("ordPro", Integer.parseInt(ordPro));
		return query.list();
	}

}
