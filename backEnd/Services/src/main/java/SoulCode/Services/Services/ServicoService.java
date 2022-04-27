package SoulCode.Services.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoulCode.Services.Models.Funcionario;
import SoulCode.Services.Models.Servico;
import SoulCode.Services.Models.statusServico;
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
	
	// procura os serviços pelo status dele
	public List<Servico> buscarServicoPeloStatus(String status){
		return servicoRepository.findByStatus(status);
	}
	
	public List<Servico> buscarServicoSemAtribuicao(){
		return servicoRepository.findByIdFuncionarioNull();
	}
	
	//método para cadastro de um serviço. No momento do cadastro do novo servico o status tem que ficar como recebido
	// no momneto do cadastro do novo serviço o ifFuncionario tem que ficar como null 
	public Servico inserirServico(Servico servico) {
		servico.setIdServico(null);
		servico.setStatus(statusServico.RECEBIDO);
		servico.setFuncionario(null);
		return servicoRepository.save(servico);
	}
	
	// metodo apra atribuir um determinado servico para um funcionario
	public Servico atribuirFuncionario(Integer IdServico, Integer IdFuncionario) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(IdFuncionario);
		Servico servico = this.mostrarUmServico(IdServico);
		servico.setFuncionario(funcionario.get());
		servico.setStatus(statusServico.ATRIBUIDO);
		
		return servicoRepository.save(servico);
	}
	
	//Método para mudar o status do serviço para concluido
	public Servico concluirServico(Integer idServico) {
		Servico servico = this.mostrarUmServico(idServico);
		if(servico.getFuncionario() != null) {
			servico.setStatus(statusServico.CONCLUIDO);		
		}
		return servicoRepository.save(servico);
	}
	
	//método para alterar o serviço
	public Servico alterarServico (Servico servico, Integer idFuncionario) {
		mostrarUmServico(servico.getIdServico());
		Funcionario funcionario = funcionarioRepository.getById(idFuncionario);
		servico.setFuncionario(funcionario);
		return servicoRepository.save(servico);
	}
	
	//método para deletar o serviço
	public void excluirServico (Integer id) {
		mostrarUmServico(id);
		servicoRepository.deleteById(id);
	}
	
}
