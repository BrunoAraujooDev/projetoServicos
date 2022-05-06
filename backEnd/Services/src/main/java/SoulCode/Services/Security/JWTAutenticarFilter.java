package SoulCode.Services.Security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import SoulCode.Services.Data.DetalheUsuarioData;
import SoulCode.Services.Models.UsuarioJWT;

public class JWTAutenticarFilter extends UsernamePasswordAuthenticationFilter{

	public static final int TOKEN_EXPIRACAO = 600_000;
	
	public static final String TOKEN_SENHA = "4e9394b4d2876b8741b10a2fb46589b60f1a1c121e9bc4c280fae85af75b75ae8609d49f0e4215f3b682306dc7f262b171ffc181f886f764d638210d6ff7ba28";
	
	private final AuthenticationManager authenticationManager;
	
	public JWTAutenticarFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	//O método AttemptAuthentication faz a tentativa de autenticação
	//nesse momento é verificado a autenticidade do username e password do usuario
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		
		try {
			UsuarioJWT usuario = new ObjectMapper().readValue(request.getInputStream(), UsuarioJWT.class);
			
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					usuario.getLogin(), usuario.getPassword(), new ArrayList<>()));
		} catch(IOException e) {
			throw new RuntimeException("Falha ao tentar autenticar o usuario", e);
		}
	}
	
	@Override
	protected void  successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult ) 
												throws IOException{
		
		DetalheUsuarioData usuarioData = (DetalheUsuarioData) authResult.getPrincipal();
		
		String token = JWT.create().withSubject(usuarioData.getUsername()).withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO))
							.sign(Algorithm.HMAC512(TOKEN_SENHA));
		
		response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, PATCH, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
        response.getWriter().flush();
	}
}









