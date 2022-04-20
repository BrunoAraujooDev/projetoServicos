package SoulCode.Services.Controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
