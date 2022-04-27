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

import SoulCode.Services.Models.Cliente;
import SoulCode.Services.Services.ClienteService;

@CrossOrigin
@RestController
@RequestMapping("servicos")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	@GetMapping("cliente")
	public List<Cliente> mostrarTodosClientes(){
		return clienteService.mostrarTodosClientes();
	}
	
	@GetMapping("/cliente/{id}")
	public ResponseEntity<Cliente> mostrarUmCliente(@PathVariable Integer id){
		Cliente cliente = clienteService.mostrarUmCliente(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@GetMapping("/clienteEmail/{email}")
	public ResponseEntity<Cliente> mostrarClientePeloEmail(@PathVariable String email){
		Cliente cliente = clienteService.mostrarClientePeloEmail(email);
		return ResponseEntity.ok().body(cliente);
	}
	
	@GetMapping("clientes/")
	public List<Cliente> mostrarTodosClientesPelaCidade(@RequestParam("cidade") String cidade){
		return clienteService.mostrarTodosClientesPelaCidade(cidade);
	}
	
	@PostMapping("cliente")
	public ResponseEntity<Cliente> inserirCliente(@RequestBody Cliente cliente){
		cliente = clienteService.inserirCliente(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("cliente/{id}")
	public ResponseEntity<Cliente> editarCliente(@PathVariable Integer id, 
			@RequestBody Cliente cliente){
		cliente = clienteService.editarCliente(cliente);
		return ResponseEntity.ok().body(cliente);
	}
	
	@DeleteMapping("cliente/{id}")
	public ResponseEntity<Void> excluirCliente(@PathVariable Integer id){
		clienteService.excluirCliente(id);
		return ResponseEntity.noContent().build();
	}

}
