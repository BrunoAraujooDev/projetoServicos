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
import SoulCode.Services.Utils.UploadFile;

@CrossOrigin
@RequestMapping("servicos")
@RestController
public class UploadFileController {
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@PostMapping("/funcionario/envioFoto/{id}")
	public ResponseEntity<Void> enviarDados(@PathVariable Integer id,
			MultipartFile foto, @RequestParam("nome") String nome){
		String fileName = nome;
		String uploadDir = "D:/Desktop/projetos/java/projetoServico/frontEnd/frontServico/src/assets/imagens";
		// /d/Desktop/projetos/java/projetoServico/frontEnd/frontServico/src/assets
		String nomeMaisCaminho = "assets/imagens/" + nome;
		
		funcionarioService.salvarFoto(id, nomeMaisCaminho);
		
		try {
			UploadFile.salvarArquivo(uploadDir, fileName, foto);
		} catch(Exception e) {
			System.out.println("O arquivo não foi enviado!" + e);
		}
		
		System.out.println("Arquivo enviado com sucesso: " + nomeMaisCaminho);
		
		return ResponseEntity.ok().build();
		
	}


}
