package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
		
		http.formLogin()
		.loginPage("/login")
		.usernameParameter("username")
		.passwordParameter("password")
		.defaultSuccessUrl("/")
		.failureUrl("/login?error")
		.permitAll();
		
		http.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/login?logout")
		.permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider);
	}
}