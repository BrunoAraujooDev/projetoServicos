package SoulCode.Services.Services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import SoulCode.Services.Data.DetalheUsuarioData;
import SoulCode.Services.Models.UsuarioJWT;
import SoulCode.Services.Repositories.UsuarioJWTRepository;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService{
	
	private final UsuarioJWTRepository usuarioRepo;
	
	public DetalheUsuarioServiceImpl(UsuarioJWTRepository user) {
		this.usuarioRepo = user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UsuarioJWT> usuario = usuarioRepo.findByLogin(username);
		if(usuario.isEmpty()) {
			throw new UsernameNotFoundException("Usuário não cadastrado!");
		}
		return new DetalheUsuarioData(usuario);
	}

	
	
}
