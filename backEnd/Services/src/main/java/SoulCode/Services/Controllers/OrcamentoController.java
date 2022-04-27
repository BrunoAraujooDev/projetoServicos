package SoulCode.Services.Controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import SoulCode.Services.Models.Orcamento;
import SoulCode.Services.Services.OrcamentoService;

@CrossOrigin
@RestController
@RequestMapping("servicos")
public class OrcamentoController {

	@Autowired
	OrcamentoService orcamentoService;
	
	@GetMapping("/orcamento")
	public List<Orcamento> mostrarTodosOrcamentos(){
		List<Orcamento> orcamentos = orcamentoService.mostrarTodosOrcamentos();
		return orcamentos;
	}
	
	//metodo para pegar o orcamento pelo id
	@GetMapping("/orcamento/{id}")
	public ResponseEntity<Orcamento> mostrarUmOrcamento(@PathVariable Integer id){
		Orcamento orcamento = orcamentoService.mostrarUmOrcamento(id);
		return ResponseEntity.ok().body(orcamento);
	}
	
	@GetMapping("/orcamentoStatus")
	public List<Orcamento> mostrarOrcamentosPeloStatus(@RequestParam("status") String status){
		List<Orcamento> orcamentos = orcamentoService.mostrarOrcamentosPeloStatus(status);
		return orcamentos;
	}
	
	@PostMapping("/orcamento/{id}")
	public ResponseEntity<Orcamento> inserirOrcamento(@PathVariable Integer id, @RequestBody Orcamento orcamento) {
		orcamento = orcamentoService.inserirOrcamento(orcamento, id);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(orcamento.getIdOrcamento()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping("/orcamentoQuitado/{id}")
	public ResponseEntity<Void> quitarOrcamento(@PathVariable Integer id) {
		orcamentoService.quitarOrcamento(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/orcamento/{id}")
	public ResponseEntity<Orcamento> editarOrcamento(@PathVariable Integer id, @RequestBody Orcamento orcamento){
		orcamento.setIdOrcamento(id);
		orcamentoService.editarOrcamento(orcamento, id);
		return ResponseEntity.ok().body(orcamento);
	}
	
	@DeleteMapping("/orcamento/{id}")
	public ResponseEntity<Void> excluirOrcamento(@PathVariable Integer id){
		orcamentoService.excluirOrcamento(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
