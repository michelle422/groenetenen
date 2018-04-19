package be.vdab.security;

import javax.servlet.ServletContext;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

public class RegisterSecurityFilter extends AbstractSecurityWebApplicationInitializer {

	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext context) {
		// TODO Auto-generated method stub
		super.insertFilters(context, new CharacterEncodingFilter("UTF-8")); 
	}
	
}
