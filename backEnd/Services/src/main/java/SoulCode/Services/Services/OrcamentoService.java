package SoulCode.Services.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoulCode.Services.Models.Orcamento;
import SoulCode.Services.Models.Servico;
import SoulCode.Services.Models.StatusOrcamento;
import SoulCode.Services.Models.statusServico;
import SoulCode.Services.Repositories.OrcamentoRepository;
import SoulCode.Services.Repositories.ServicoRepository;

@Service
public class OrcamentoService {

	@Autowired
	OrcamentoRepository orcamentoRepository;
	
	@Autowired
	ServicoRepository servicoRepository;
	
	//método para trazer todos os orcamentos cadastrados no db
	public List<Orcamento> mostrarTodosOrcamentos(){
		return orcamentoRepository.findAll();
	}
	
	public Orcamento mostrarUmOrcamento(Integer id) {
		Optional<Orcamento> orcamento =  orcamentoRepository.findById(id);
		return orcamento.orElseThrow();
	}
	
	public List<Orcamento> mostrarOrcamentosPeloStatus(String status){
		return orcamentoRepository.findByStatus(status);
	}
	
	//metodo para inserir um novo orcamento
	public Orcamento inserirOrcamento(Orcamento orcamento, Integer id) {
		orcamento.setIdOrcamento(id);
		orcamento.setStatus(StatusOrcamento.EMITIDO);
		orcamentoRepository.save(orcamento);
		Servico servico = servicoRepository.getById(id);
		servico.setStatus(statusServico.RECEBIDO);
		servico.setOrcamento(orcamento);
		servicoRepository.save(servico);
		return orcamento;
	}
	
	//servico para pagamento de um orcamento - mudar status para quitado
	public Orcamento quitarOrcamento(Integer idOrcamento) {
		Orcamento orcamento = mostrarUmOrcamento(idOrcamento);
		orcamento.setStatus(StatusOrcamento.QUITADO);
		return orcamentoRepository.save(orcamento);
	}
	
	
	//servico para alterar dados do orcamento
	public Orcamento editarOrcamento(Orcamento orcamento, Integer id) {
		mostrarUmOrcamento(orcamento.getIdOrcamento());
		return orcamentoRepository.save(orcamento);
	}
	
	
	//servico para deletar o orcamento
	public void excluirOrcamento(Integer id) {
		Servico servico = servicoRepository.getById(id);
		servico.setOrcamento(null);
		servico.setStatus(statusServico.ARQUIVADO);
		servicoRepository.save(servico);
		orcamentoRepository.deleteById(id);
	}
		
	
}
