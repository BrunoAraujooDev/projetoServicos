package SoulCode.Services.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SoulCode.Services.Models.Funcionario;

@Repository
public interface FuncionarioRepository extends  JpaRepository<Funcionario, Integer>{
	
	Optional<Funcionario> findByEmail(String email);
	Optional<Funcionario> findByNomeAndEmail(String nome, String email);
	

}
