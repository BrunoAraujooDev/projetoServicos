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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import SoulCode.Services.Models.Funcionario;
import SoulCode.Services.Services.FuncionarioService;

@CrossOrigin
@RestController
@RequestMapping("servicos")
public class FuncionarioController {
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@GetMapping("/funcionario")
	public List<Funcionario> mostrasTodosFuncionarios(){
		return funcionarioService.mostrasTodosFuncionarios();
		/*List<Funcionario> funcionarios = funcionarioService.mostrasTodosFuncionarios();
		return funcionarios;*/
	}
	
	@GetMapping("/funcionario/{id}")
	public ResponseEntity<Funcionario> mostrarUmFuncionario(@PathVariable Integer id){
		Funcionario funcionario = funcionarioService.mostrarUmFuncionario(id);
		return ResponseEntity.ok().body(funcionario);
	}
	
	@GetMapping("/funcionarioEmail/{email}")
	public ResponseEntity<Funcionario> mostrarFuncionarioPeloEmail(@PathVariable String email){
		Funcionario funcionario = funcionarioService.mostrarFuncionarioPeloEmail(email);
		return ResponseEntity.ok().body(funcionario);
	}
	
	@GetMapping("funcionarioNomeEmail/{nome}/{email}")
	public ResponseEntity<Funcionario> mostrarFuncionarioPeloNomeEEmail(@PathVariable String email,@PathVariable String nome){
		Funcionario funcionario = funcionarioService.mostrarFuncionarioPeloNomeEEmail(nome, email);
		return ResponseEntity.ok().body(funcionario);
	}
	
	//Quando o retorno é responseEntity, está dizendo que a resposta do http vai ser do formato que vai representar toda a resposta (status, header, body)
	@PostMapping("/funcionario")
	public ResponseEntity<Funcionario> inserirFuncionario(@RequestBody Funcionario funcionario){
		// nessa linha 45 o novo funcionário já é salvo no banco de dados e já é criado o seu id
		funcionario = funcionarioService.inserirFuncionario(funcionario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(funcionario.getIdFuncionario()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/funcionario/{id}")
	public ResponseEntity<Funcionario> editarFuncionario(@PathVariable Integer id, @RequestBody Funcionario funcionario){
		funcionario.setIdFuncionario(id);
		funcionario = funcionarioService.editarFuncionario(funcionario);
		return ResponseEntity.noContent().build();
	}
		
	@DeleteMapping("funcionario/{id}")
	public ResponseEntity<Void> excluirFuncionario(@PathVariable Integer id){
		funcionarioService.excluirFuncionario(id);
		return ResponseEntity.noContent().build();
	}
	
	

}
