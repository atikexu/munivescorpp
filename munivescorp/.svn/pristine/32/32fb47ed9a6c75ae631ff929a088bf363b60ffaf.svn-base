package pe.nasqa.values.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.nasqa.values.model.UsuarioModel;
import pe.nasqa.values.model.entity.Perfil;
import pe.nasqa.values.model.entity.Usuario;
import pe.nasqa.values.model.entity.UsuarioEstado;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioModel usuarioModel;
	
	private Logger log = Logger.getLogger(UserDetailsServiceImpl.class);

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Usuario usuario=usuarioModel.findUserByName(username);
		User user=null;
		log.info("Usuario buscado : " + usuario);
		if(usuario!=null){
			try {
				String password=usuario.getPassword();
				
				boolean enabled = usuario.getEstado().equals(UsuarioEstado.ACTIVE);
				boolean accountNonExpired = usuario.getEstado().equals(UsuarioEstado.ACTIVE);
				boolean credentialsNonExpired = usuario.getEstado().equals(UsuarioEstado.ACTIVE);
				boolean accountNonLocked = usuario.getEstado().equals(UsuarioEstado.ACTIVE);
				
				Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				
				log.info("Usuario : " + usuario + " con " + usuario.getPerfilSet().size() + " perfiles, passwd ["+usuario.getPassword()+"].");
				
				for(Perfil perfil:usuario.getPerfilSet()){
					log.info("Usuario Perfil: " + usuario.getUsername() + " > " + perfil.getPerfilNombre() + " perfiles.");
					authorities.add(new SimpleGrantedAuthority(perfil.getPerfilNombre()));
				}
				
				user=new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
			} catch (Exception e) {
				log.error("ERROR CRITICO en el LOGIN "+ new Date().toString());
				e.printStackTrace();
			}
		}else{
			log.error("Usuario no existe en DB : " + usuario);
			throw new UsernameNotFoundException("User Not Found !");
		}
		
		return user;
	}

}
