package SoulCode.Services.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SoulCode.Services.Models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	Optional<Cliente> findByEmail(String email);
	List<Cliente> findByCidade(String cidade);
}
