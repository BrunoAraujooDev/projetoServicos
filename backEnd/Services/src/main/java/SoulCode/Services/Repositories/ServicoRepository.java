package SoulCode.Services.Repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import SoulCode.Services.Models.Funcionario;
import SoulCode.Services.Models.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Integer>{
	
	// esse metodo vai trazer todos os servicos de um determinado funcionario
	// o Optional é um recurso que surgiu depois do java 8, que quando da erro de null exception ele não trava a aplicação e lança o retorno de null e continua a aplicação
	List<Servico> findByFuncionario(Optional<Funcionario> funcionario);
	
	List<Servico> findByDataEntrada(Date data);
	
	@Query(value = "SELECT * FROM servico WHERE data_entrada BETWEEN :data1 AND :data2", nativeQuery = true)
	List<Servico> findByIntervaloDate(Date data1, Date data2);

}
