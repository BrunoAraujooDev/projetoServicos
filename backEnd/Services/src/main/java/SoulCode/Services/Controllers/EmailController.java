package SoulCode.Services.Controllers;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/servicos")
public class EmailController {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping("/envioEmail/{email}")
	public String sendEmail(@PathVariable String email) {
		
		String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		String senha = "";
		
		int index = 0;
		for(int i = 0; i < 9; i++) {
			index = random.nextInt(letras.length());
			senha += letras.substring(index, index + 1);
		}
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setText("Sua nova senha para redefinição é: "+ senha);
		message.setSubject("Redifinição de senha");
		message.setTo(email);
		message.setFrom("bruno.oliveira270303@gmail.com");
		
		try {
			mailSender.send(message);
			return "Email enviado com sucesso!";
		} catch(Exception e) {
			return "Erro ao enviar o email";
		}
	
	}

}
