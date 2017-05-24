package pe.nasqa.values.dao;

import pe.nasqa.values.model.entity.Usuario;

public interface UsuarioDao extends IGenericDao<Usuario, Long>{
	Usuario findUserByName(String username);
	
	Usuario getUserDetail(String username);
	
	void setPerfil(Integer idUsuario, Integer idPerfil[]);
	
	boolean changePassword(String username, String passwordMd5Act, String passwordMd5New);
}
