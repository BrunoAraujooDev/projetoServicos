package SoulCode.Services.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
