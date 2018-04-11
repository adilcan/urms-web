package com.gitlab.esenbogagnu.urmsweb.config;

import com.gitlab.esenbogagnu.urmsweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * Created on April, 2018
 *
 * @author adilcan
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(getPasswordEncoder());
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/css/**", "/assets/**", "/js/**", "/templates/**", "/scss/**").permitAll()
				.antMatchers("/").hasAnyAuthority("ADMIN, USER")
				.antMatchers("/register").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/h2-console/**").permitAll()
				.anyRequest().fullyAuthenticated()
				.and()
				.exceptionHandling().accessDeniedPage("/")
				.and()
				.formLogin()
				.loginPage("/login")
				.usernameParameter("username")
				.defaultSuccessUrl("/")
				.permitAll()
				.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login")
				.permitAll()
				.and()
				.httpBasic()
				.authenticationEntryPoint(authenticationEntryPoint);
		http.csrf().disable();
		http.headers().frameOptions().disable();

		//		http.headers().cacheControl().disable();

	}

}
