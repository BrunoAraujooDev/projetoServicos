package SoulCode.Services.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SoulCode.Services.Models.UsuarioJWT;
import SoulCode.Services.Repositories.UsuarioJWTRepository;
import SoulCode.Services.Services.UsuarioJWTService;

@CrossOrigin
@RestController
@RequestMapping("servicos")
public class UsuarioJWTController {
	
	@Autowired
	UsuarioJWTService usuarioJWTService;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UsuarioJWTRepository usuarioJWTRepository;
	
	@GetMapping("/usuarioJWT")
	public ResponseEntity<List<UsuarioJWT>> listarUsuarioJWT(){
		return ResponseEntity.ok(usuarioJWTService.listarUsuarioJWT());
	}
	
	@PostMapping("/usuarioJWT")
	public ResponseEntity<UsuarioJWT> inserirUsuario(@RequestBody UsuarioJWT usuario){
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		return ResponseEntity.ok(usuarioJWTService.inserirUsuario(usuario));
	}

}
