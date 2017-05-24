package pe.nasqa.values.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.nasqa.values.dao.UsuarioDao;
import pe.nasqa.values.model.entity.Usuario;

@Service
public class UsuarioModel{
	
	@Autowired
	private UsuarioDao usuarioDao;

	@Transactional(readOnly = true)
	public Usuario findOne(long id) {
		return usuarioDao.findOne(id);
	}

	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return usuarioDao.findAll();
	}

	@Transactional
	public void create(Usuario entity) {
		usuarioDao.create(entity);
	}

	@Transactional
	public void update(Usuario entity) {
		usuarioDao.update(entity);
	}
	
	@Transactional
	public void setPerfil(Integer idUsuario, Integer idPerfil[]) {
		usuarioDao.setPerfil(idUsuario, idPerfil);
	}

	@Transactional
	public void delete(Usuario entity) {
		usuarioDao.delete(entity);
	}

	@Transactional
	public void deleteById(long entityId) {
		usuarioDao.deleteById(entityId);
	}
	
	@Transactional(readOnly = true)
	public Usuario findUserByName(String username){
		return usuarioDao.findUserByName(username);
	}

	@Transactional(readOnly = true)
	public Usuario getUserDetail(String username){
		return usuarioDao.getUserDetail(username);
	}
	
	@Transactional
	public boolean changePassword(String username, String passwordMd5Act, String passwordMd5New) {
		return usuarioDao.changePassword(username, passwordMd5Act, passwordMd5New);
	}
}
