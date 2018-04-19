package be.vdab.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration 
@EnableWebSecurity 
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final String MANAGER = "manager";   
	private static final String HELPDESKMEDEWERKER = "helpdeskmedewerker";   
	private static final String MAGAZIJNIER = "magazijnier";
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("joe").password("theboss")
			.authorities(MANAGER).and().withUser("averell").password("hungry")
			.authorities(HELPDESKMEDEWERKER, MAGAZIJNIER);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().and().authorizeRequests()
		.mvcMatchers("/filialen/toevoegen", "/filialen/*/wijzigen", "/filialen/*/verwijderen")
			.hasAuthority(MANAGER)
		.mvcMatchers(HttpMethod.POST, "/filialen").hasAuthority(MANAGER)
		.mvcMatchers("/werknemers").hasAnyAuthority(MAGAZIJNIER, HELPDESKMEDEWERKER)
		.mvcMatchers("/", "/login").permitAll()
		.mvcMatchers("/**").authenticated()
		.and().exceptionHandling().accessDeniedPage("/WEB-INF/JSP/forbidden.jsp"); ;
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().mvcMatchers("/images/**")
					.mvcMatchers("/styles/**")
					.mvcMatchers("/scripts/**");
	} 
	
}