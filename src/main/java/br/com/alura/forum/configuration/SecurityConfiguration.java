package br.com.alura.forum.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.alura.forum.security.JwtAuthenticationFilter;
import br.com.alura.forum.security.jwt.TokenManager;
import br.com.alura.forum.service.UsersService;

@Configuration
@Order(2)
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UsersService userService; 
	@Autowired
	private TokenManager tokenManager;

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception{
			auth.userDetailsService(userService)
				.passwordEncoder(new BCryptPasswordEncoder());
		}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.antMatcher("/api/**")
		.authorizeRequests()
			.antMatchers(HttpMethod.GET,"/api/topics/**").permitAll()
			.antMatchers("/api/auth/**").permitAll()
//			.antMatchers("/admin/reports/**").permitAll()
			.anyRequest().authenticated()
		.and()
			.cors()
		.and()
			.csrf().disable()
		.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.addFilterBefore(new JwtAuthenticationFilter(tokenManager, userService),
					UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/**.html","/v2/api-docs","/webjars/**", "/configuration/**","/swagger-resources/**",
									"/css/**","/**.ico","/js/**");
	}
	
	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
//curl -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBbHVyYSBGb3J1bSBBUEkiLCJzdWIiOiI0IiwiaWF0IjoxNTY5NjMwNTc5LCJleHAiOjE1NzAyMzUzNzl9._SabxbF7Sa12PL-XbRiB7vVc_6yi7wXpxSWqSOrGygc" -X GET http://localhost:8080/
