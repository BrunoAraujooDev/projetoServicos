package SoulCode.Services.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoulCode.Services.Models.Funcionario;
import SoulCode.Services.Repositories.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	//injecao de dependencia - o autowired faz a injeção de dependencia
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	// findAll (metodo spring data) - busca todos os funcionarios
	public  List<Funcionario> mostrasTodosFuncionarios(){
		return funcionarioRepository.findAll();
	}
	
	//findById (metodo spring data) - busca um funcionario específico pelo seu id
	public Funcionario mostrarUmFuncionario(Integer id) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		return funcionario.orElseThrow();
	}
	
	//findByEmail
	public Funcionario mostrarFuncionarioPeloEmail(String email) {
		Optional<Funcionario> funcionario = funcionarioRepository.findByEmail(email);
		return funcionario.orElseThrow();
	}
	
	//findByNomeAndEmail
	public Funcionario mostrarFuncionarioPeloNomeEEmail(String nome, String email) {
		Optional<Funcionario> funcionario = funcionarioRepository.findByNomeAndEmail(nome, email);
		return funcionario.orElseThrow();
	}
	
	//save - insere um novo registro na tabela do nosso db
	public Funcionario inserirFuncionario(Funcionario funcionario) {
		funcionario.setIdFuncionario(null);
		return funcionarioRepository.save(funcionario);
		
		//o método save pega os dados do novo funcionário, salva no db e já gera um id para esse funcionario.
	}
	
	//editar um funcionario já cadastrado
	public Funcionario editarFuncionario(Funcionario funcionario) {
		mostrarUmFuncionario(funcionario.getIdFuncionario());
		return funcionarioRepository.save(funcionario);
	}
	
	//deleteById - excluir o funcionario pelo ID
	public void excluirFuncionario(Integer id) {
		mostrarUmFuncionario(id);
		funcionarioRepository.deleteById(id);
	}
	
	//salvar o caminho da  foto no db
	public Funcionario salvarFoto(Integer id, String CaminhoFoto) {
		Funcionario funcionario = mostrarUmFuncionario(id);
		funcionario.setFoto(CaminhoFoto);
		return funcionarioRepository.save(funcionario);
	}

}
