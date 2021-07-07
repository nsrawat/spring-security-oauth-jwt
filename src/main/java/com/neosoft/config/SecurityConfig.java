package com.neosoft.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.neosoft.filter.JwtRequestFilter;
import com.neosoft.service.CustomUserDetailsService;
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomUserDetailsService userDetailsService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("admin").password("pass").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("user").password("pass").roles("STUDENT");
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/admin").fullyAuthenticated().and().httpBasic();
		http.csrf().disable()
				.authorizeRequests().antMatchers("/authenticate").permitAll().
				anyRequest().authenticated()
				.and().exceptionHandling().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
	//	@Override
	//	protected void configure(HttpSecurity http) throws Exception {
	//		http.csrf().disable();
	//		http.authorizeRequests()
	//		//security for all api
	//		//	.anyRequest().fullyAuthenticated().and().httpBasic();
	//
	//		//securit based on url
	//		//	.antMatchers("/admin").fullyAuthenticated().and().httpBasic();
	//
	//		//Security based on role
	//		.antMatchers("/admin/**").hasAnyRole("ADMIN").anyRequest().fullyAuthenticated().and().httpBasic();
	//		//http.authorizeRequests().antMatchers("/user/**").hasAnyRole("STUDENT").anyRequest().fullyAuthenticated().and().httpBasic();
	//		//        .antMatchers("/").permitAll()
	//		//        .and().formLogin();
	//	}



