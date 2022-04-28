package SoulCode.Services.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SoulCode.Services.Models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, String>{
	
	Usuario findByLogin(String login);

}
