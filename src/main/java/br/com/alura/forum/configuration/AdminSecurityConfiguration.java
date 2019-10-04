package br.com.alura.forum.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.alura.forum.service.UsersService;

@Configuration
@Order(1)
@EnableWebSecurity
public class AdminSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsersService userService;
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http.antMatcher("/admin/**")
			.authorizeRequests().anyRequest().hasRole("ADMIN")
		.and()
			.httpBasic();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth)
				throws Exception {
		auth.userDetailsService(this.userService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
}
