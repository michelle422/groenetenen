package be.vdab.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@ComponentScan
@PropertySource("classpath:/mail.properties")
public class MailConfig {
	@Bean
	static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	JavaMailSenderImpl javaMailSenderImpl(@Value("${mailserver.host}") String host, 
			@Value("${mailserver.port}") int port, 
			@Value("${mailserver.protocol}") String protocol, 
			@Value("${mailserver.username}") String username, 
			@Value("${mailserver.password}") String password) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(host);
		mailSender.setPort(port);
		mailSender.setProtocol(protocol);
		mailSender.setUsername(username);
		mailSender.setPassword(password);
		return mailSender;
	}
}
