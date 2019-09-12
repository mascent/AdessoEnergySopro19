package de.sopro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import de.sopro.data.Role;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AdessoUserDetailsService userDetailsService;

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER").and().withUser("admin")
//				.password("{noop}password").roles("USER", "ADMIN");
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				// HTTP Basic authentication
				.httpBasic().and().authorizeRequests()
				.antMatchers(HttpMethod.GET, "/api/login").hasRole(Role.Shared.toString())
				.antMatchers(HttpMethod.GET, "/api/users/me").hasRole(Role.User.toString())
				.antMatchers(HttpMethod.GET, "/api/users/me/**").hasRole(Role.User.toString())
				.antMatchers(HttpMethod.PUT, "/api/users/me/email").hasRole(Role.User.toString())
				.antMatchers(HttpMethod.POST, "/api/picture").hasRole(Role.User.toString())
				.antMatchers(HttpMethod.GET, "/api/**").hasRole(Role.Admin.toString())
				.antMatchers(HttpMethod.POST, "/api/**").hasRole(Role.Admin.toString())
				.antMatchers(HttpMethod.PUT, "/api/**").hasRole(Role.Admin.toString())
				.antMatchers(HttpMethod.PATCH, "/api/**").hasRole(Role.Admin.toString())
				.antMatchers(HttpMethod.DELETE, "/api/**").hasRole(Role.Admin.toString()).and().csrf().disable()
				.formLogin().disable();
	}
	/*
	 * @Bean public UserDetailsService userDetailsService() { //ok for demo
	 * User.UserBuilder users = User.withDefaultPasswordEncoder();
	 * 
	 * InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	 * manager.createUser(users.username("user").password("password").roles("USER").
	 * build());
	 * manager.createUser(users.username("admin").password("password").roles("USER",
	 * "ADMIN").build()); return manager; }
	 */

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
