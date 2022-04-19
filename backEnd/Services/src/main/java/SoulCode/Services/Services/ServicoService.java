package SoulCode.Services.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoulCode.Services.Models.Servico;
import SoulCode.Services.Repositories.ServicoRepository;

@Service
public class ServicoService {

	//injecao de dependencia - chamando o repository de servicos
	@Autowired
	ServicoRepository servicoRepository;
	
	//findAll dos servicos cadastrados
	public List<Servico> mostrarTodosServicos(){
		return servicoRepository.findAll();
	}
	
	// findById - retorna o serviço pelo seu ID
	public Servico mostrarUmServico(Integer id) {
		Optional<Servico> servico = servicoRepository.findById(id);
		return servico.orElseThrow();
	}
	
	
}
