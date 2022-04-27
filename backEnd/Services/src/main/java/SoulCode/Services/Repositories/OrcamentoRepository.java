package SoulCode.Services.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import SoulCode.Services.Models.Orcamento;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Integer>{
	
	@Query(value = "SELECT * FROM orcamento where status = :status", nativeQuery = true)
	List<Orcamento> findByStatus(String status);
	
	
}
