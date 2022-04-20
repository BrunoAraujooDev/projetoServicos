package SoulCode.Services.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoulCode.Services.Models.Funcionario;
import SoulCode.Services.Models.Servico;
import SoulCode.Services.Repositories.FuncionarioRepository;
import SoulCode.Services.Repositories.ServicoRepository;

@Service
public class ServicoService {

	//injecao de dependencia - chamando o repository de servicos
	@Autowired
	ServicoRepository servicoRepository;
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	//findAll dos servicos cadastrados
	public List<Servico> mostrarTodosServicos(){
		return servicoRepository.findAll();
	}
	
	// findById - retorna o serviço pelo seu ID
	public Servico mostrarUmServico(Integer id) {
		Optional<Servico> servico = servicoRepository.findById(id);
		return servico.orElseThrow();
	}
	
	//findByFuncionario - retorna todos os servicos do funcionario x
	public List<Servico> buscarServicosDoFuncionario(Integer idFuncionario){
		Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);
		 return servicoRepository.findByFuncionario(funcionario);
	}
	
	//findByData
	public List<Servico> buscarServicosPelaData(Date data){
		List<Servico> servico = servicoRepository.findByDataEntrada(data);
		return servico;
	}
	
	//Procura os serviços num intervalo de datas
	public List<Servico> buscarServicosPorIntervaloData(Date data1, Date data2){
		List<Servico> servico =  servicoRepository.findByIntervaloDate(data1, data2);
		return servico;
	}
	
	
}
