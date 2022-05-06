package SoulCode.Services.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SoulCode.Services.Models.UsuarioJWT;

@Repository													//classe, tipo da chave primaria
public interface UsuarioJWTRepository extends JpaRepository<UsuarioJWT, Integer> {

	public Optional<UsuarioJWT> findByLogin(String login);
}
