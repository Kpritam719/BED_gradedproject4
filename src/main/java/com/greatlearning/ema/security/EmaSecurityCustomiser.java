package com.greatlearning.ema.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class EmaSecurityCustomiser 
	extends WebSecurityConfigurerAdapter{

	// Authentication
	
	public void configure(AuthenticationManagerBuilder auth) 
			throws Exception {
		
		auth.authenticationProvider(EmaAuthenticationProvider());		
	}
	
	private AuthenticationProvider EmaAuthenticationProvider() {
		// TODO Auto-generated method stub
		return null;
	}

	@Bean
	public AuthenticationProvider srsAuthenticationProvider() {
	
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
			// Username
				
		authProvider.setUserDetailsService(userDetailsService());
		
			// Password
		
		authProvider.setPasswordEncoder(getPasswordEncoder());
		
		
		
		return authProvider;
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		return  new EmaUserDetailService();
	}
	
	
	// Authorization
	
	@Override




	public void configure(HttpSecurity http) throws Exception {

		/*
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
		obj1 = 
		http.authorizeRequests();
		
		obj1.antMatchers("");
		*/
		
		// ANT  Matchers
		// /a/b/c*
		// /a/b/**/*
		//
		
       http.authorizeRequests()
       .antMatchers("/","/employees/save","/employees/showFormForAdd","/employees/403")
       		.hasAnyAuthority("USER","ADMIN")
       .antMatchers("/employees/showFormForUpdate","/employees/delete")
       		.hasAuthority("ADMIN")
       .anyRequest().authenticated()
       .and()
       .formLogin().loginProcessingUrl("/login").successForwardUrl("/employees/list").permitAll()
       .and()
       .logout().logoutSuccessUrl("/login").permitAll()
       .and()
       .exceptionHandling().accessDeniedPage("/employees/403")
       .and()
       .cors().and().csrf().disable();		
	}
}