package SoulCode.Services.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import SoulCode.Services.Services.FuncionarioService;

@CrossOrigin
@RequestMapping("servicos")
@RestController
public class UploadFileController {
	
	@Autowired
	FuncionarioService funcionarioService;
	
	/*@PostMapping("/funcionario/envioFoto/{id}")
	public ResponseEntity<String> enviarDados(@PathVariable Integer id,
			MultipartFile foto, @RequestParam("nome") String nome){
		String fileName = nome;
		String uploadDir = "D:/Desktop/projetos/java/projetoServico/frontEnd/frontServico/src/assets";
		// /d/Desktop/projetos/java/projetoServico/frontEnd/frontServico/src/assets
	}*/


}
