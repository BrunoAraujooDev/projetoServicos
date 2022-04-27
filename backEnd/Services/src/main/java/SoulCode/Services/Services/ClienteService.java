package SoulCode.Services.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoulCode.Services.Models.Cliente;
import SoulCode.Services.Repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public List<Cliente> mostrarTodosClientes(){
		return clienteRepository.findAll();
	}
	
	public Cliente mostrarUmCliente(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow();
	}
	
	public Cliente mostrarClientePeloEmail(String email) {
		Optional<Cliente> cliente = clienteRepository.findByEmail(email);
		return cliente.orElseThrow();
	}
	
	public List<Cliente> mostrarTodosClientesPelaCidade(String cidade) {
		return clienteRepository.findByCidade(cidade);
	}
	
	public Cliente inserirCliente(Cliente cliente) {
		cliente.setId(null);
		return clienteRepository.save(cliente);
	}
	
	public Cliente editarCliente(Cliente cliente) {
		mostrarUmCliente(cliente.getId());
		return clienteRepository.save(cliente);
	}
	
	public void excluirCliente(Integer id) {
		mostrarUmCliente(id);
		clienteRepository.deleteById(id);
	}
	

}
