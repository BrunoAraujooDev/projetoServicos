package SoulCode.Services.Security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import SoulCode.Services.Services.DetalheUsuarioServiceImpl;

@EnableWebSecurity
public class JWTConfiguracao extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DetalheUsuarioServiceImpl usuarioService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.cors().and().csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.POST, "/login").permitAll()
			//.antMatchers(HttpMethod.GET, "/servicos**/**").permitAll()
			.anyRequest().authenticated().and()
			.addFilter(new JWTAutenticarFilter(authenticationManager()))
			.addFilter(new JWTValidarFilter(authenticationManager()))
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //última linha deixa as requisições independentes uma das outras
					
	}

	@Bean
	 CorsConfigurationSource corsConfigurationSource() {
		 CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedMethods(List.of(
	                HttpMethod.GET.name(),
	                HttpMethod.PUT.name(),
	                HttpMethod.POST.name(),
	                HttpMethod.DELETE.name()
	        ));
	        
	     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	     source.registerCorsConfiguration("/**", configuration.applyPermitDefaultValues());
	     return source;
	}
}










