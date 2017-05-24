package pe.nasqa.values.dao.imp;

import java.io.File;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.nasqa.values.dao.CargaImgDao;

@Repository
public class CargaImgDaoImp implements CargaImgDao{
	
	@Autowired
	private SessionFactory session;

	public boolean updateCargo(String codBar, String imgCarNom) {
		// TODO Auto-generated method stub
		int rs = 0;
		rs=session.getCurrentSession().
		createSQLQuery("UPDATE distribucion SET img_car_est=1, img_car_nom=:imgCarNom, img_car_fec=NOW() WHERE cod_bar=:codBar").
		setParameter("imgCarNom", imgCarNom).
		setParameter("codBar", codBar)
		.executeUpdate();
		return rs>0;
	}

	public boolean updateDocumento(String codBar, String imgDocNom) {
		// TODO Auto-generated method stub
		int rs = 0;
		rs=session.getCurrentSession().
		createSQLQuery("UPDATE distribucion SET img_doc_est=1, img_doc_nom=:imgDocNom, img_doc_fec=NOW() WHERE cod_bar=:codBar").
		setParameter("imgDocNom", imgDocNom).
		setParameter("codBar", codBar)
		.executeUpdate();
		return rs>0;
	}

}
