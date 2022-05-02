package com.bcstp.helpdesk;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class LogoutSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
 	protected void configure(HttpSecurity http) throws Exception {
 		http.authorizeRequests().antMatchers("/**").hasRole("USER").and().formLogin()
 				.and()
 				// sample logout customization
 				.logout().deleteCookies("remove").invalidateHttpSession(false)
 				.logoutUrl("/custom-logout").logoutSuccessUrl("/logout-success");
 	}

 	@Override
 	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 		auth.inMemoryAuthentication().withUser("username").password("password").roles("USER");
 	}

}
