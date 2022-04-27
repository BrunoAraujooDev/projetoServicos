package SoulCode.Services.Controllers;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import SoulCode.Services.Models.Servico;
import SoulCode.Services.Services.ServicoService;

@CrossOrigin
@RestController
@RequestMapping("servicos") //rota base
public class ServicoController {
	
	
	@Autowired
	ServicoService servicoService;
	
	// mapeamento para mostrar todos os servicos
	@GetMapping("/servico")
	public List<Servico> mostrarTodosServicos(){
		List<Servico> servicos = servicoService.mostrarTodosServicos();
		return servicos;
	}
	
	@GetMapping("/servico/{idServico}")
	public ResponseEntity<Servico> mostrarUmServico(@PathVariable Integer idServico) {
		Servico servico = servicoService.mostrarUmServico(idServico);
		return ResponseEntity.ok().body(servico);
	}
	
	@GetMapping("/servico/funcionario/{idFuncionario}")
	public List<Servico> buscarServicosDoFuncionario(@PathVariable Integer idFuncionario){
		List<Servico> servicos =  servicoService.buscarServicosDoFuncionario(idFuncionario);
		return servicos;
	}
	
	//@ResquestParam permite que os dados venham pelos parametros da requisicao
	@GetMapping("/servicosData")
	public List<Servico> buscarServicoPelaData(@RequestParam("dataEntrada") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dataEntrada){
		List<Servico> servicos = servicoService.buscarServicosPelaData(dataEntrada);
		return servicos;
	}
	
	@GetMapping("/servicoIntervaloData")
	public List<Servico> buscarServicoPorIntervaloData (@RequestParam("data1") @DateTimeFormat(
			iso = DateTimeFormat.ISO.DATE) Date data1, @RequestParam("data2") @DateTimeFormat(
					iso = DateTimeFormat.ISO.DATE) Date data2 ){
		List<Servico> servicos = servicoService.buscarServicosPorIntervaloData(data1, data2);
		return servicos;	
	}
	
	@GetMapping("/servicoStatus")
	public List<Servico> buscarServicoPeloStatus(@RequestParam("status") String status){
		return servicoService.buscarServicoPeloStatus(status);
	}
	
	@GetMapping("/servicoNull")
	public List<Servico> buscarServicoSemAtribuicao(){
		return servicoService.buscarServicoSemAtribuicao();
	}
	
	@PostMapping("/servico")
	public ResponseEntity<Servico> inserirServico(@RequestBody Servico servico){
		servico = servicoService.inserirServico(servico);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(servico.getIdServico()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping("/atribuirServico/{idServico}/{idFuncionario}")
	public ResponseEntity<Servico> atribuirFuncionario(@PathVariable Integer idServico, @PathVariable Integer idFuncionario){
		servicoService.atribuirFuncionario(idServico, idFuncionario);
		return ResponseEntity.noContent().build();
	}
	
	//ResponseEntity -> retorna a requisição completa(status, body e cabeçalho)
	
	@PostMapping("/concluirServico/{idServico}")
	public ResponseEntity<Servico> concluirServico(@PathVariable Integer idServico){
		servicoService.concluirServico(idServico);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/servico/{id}/{idFuncionario}")
	public ResponseEntity<Servico> alterarServico(@PathVariable Integer id, @PathVariable Integer idFuncionario, @RequestBody Servico servico ){
		servico.setIdServico(id);
		servico = servicoService.alterarServico(servico, idFuncionario);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("servico/{id}")
	public ResponseEntity<Void> excluirServico(@PathVariable Integer id){
		servicoService.excluirServico(id);
		return ResponseEntity.noContent().build();
	}
	
}
