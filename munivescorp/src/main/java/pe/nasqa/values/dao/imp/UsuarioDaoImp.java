package pe.nasqa.values.dao.imp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.nasqa.values.dao.UsuarioDao;
import pe.nasqa.values.model.entity.Usuario;

@Repository
public class UsuarioDaoImp implements UsuarioDao{
	
	@Autowired
	private SessionFactory session;

	
	public Usuario findOne(Long id) {
		// TODO Auto-generated method stub
		return (Usuario)session.getCurrentSession().get(Usuario.class, id);
	}

	
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Usuario order by tipo, idCliente, username").list();
	}

	
	public void create(Usuario entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().persist(entity);
	}

	
	public void update(Usuario entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().merge(entity);
	}

	
	public void delete(Usuario entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(entity);
	}

	
	public void deleteById(Long entityId) {
		// TODO Auto-generated method stub
		delete(findOne(entityId));
	}
	
	
	public Usuario findUserByName(String username){
		Criteria criteria = session.getCurrentSession().createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("username", username));
//		criteria.setFetchMode("perfiles", FetchMode.JOIN);
		return (Usuario) criteria.uniqueResult();
	}

	
	public Usuario getUserDetail(String username) {
		Criteria criteria = session.getCurrentSession().createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("username", username));
//		criteria.setFetchMode("perfiles", FetchMode.JOIN);
//		criteria.setFetchMode("valorados", FetchMode.JOIN);
		
//		.setFetchMode("blist", JOIN)
//        .setFetchMode("clist", JOIN)
//        .createAlias("clist", "c")
//        .setFetchMode("c", JOIN)
		//otras tablas
		return (Usuario) criteria.uniqueResult();
	}

	
	public void setPerfil(Integer idUsuario, Integer[] idPerfils) {
		// TODO Auto-generated method stub
		session.getCurrentSession().createSQLQuery("delete from usuario_perfil where id_usuario=:idUsuario").
		setInteger("idUsuario", idUsuario).
		executeUpdate();
		for (int i = 0; i < idPerfils.length; i++) {
			session.getCurrentSession().createSQLQuery("insert into usuario_perfil(id_usuario,id_perfil) values(:idUsuario,:idPerfil)").
			setInteger("idUsuario", idUsuario).
			setInteger("idPerfil", idPerfils[i]).
			executeUpdate();
		}
	}

	
	public boolean changePassword(String username, String passwordMd5Act,
			String passwordMd5New) {
		// TODO Auto-generated method stub
		boolean result=false;
		
		int rows = 
		session.getCurrentSession().createSQLQuery("update usuario set password=:passwdNew where username=:userName and password=:passwdAct").
		setString("passwdNew", passwordMd5New).
		setString("userName", username).
		setString("passwdAct", passwordMd5Act).
		executeUpdate();
		if(rows>0) result=true;
		return result;
	}
	
}
