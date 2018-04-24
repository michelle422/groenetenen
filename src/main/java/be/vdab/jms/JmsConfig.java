package be.vdab.jms;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;

@Configuration 
@ComponentScan 
@PropertySource("classpath:/jms.properties") 
@EnableJms
public class JmsConfig {
	final static String QUEUE_NAME="nieuwFiliaalQueue";   
	
	@Bean   
	static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {     
		return new PropertySourcesPlaceholderConfigurer();   
	}
	
	@Bean   
	public ActiveMQConnectionFactory connectionFactory(
			@Value("${messageBroker.url}") String messageBrokerUrl) {     
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();     
		connectionFactory.setBrokerURL(messageBrokerUrl);     
		return connectionFactory;   
	}
	
	@Bean   
	public JmsMessagingTemplate jmsMessagingTemplate(ActiveMQConnectionFactory connectionFactory) {     
		JmsMessagingTemplate template = new JmsMessagingTemplate(connectionFactory);     
		template.setDefaultDestinationName(QUEUE_NAME);     
		return template;   
	}
	
	@Bean 
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
			 ActiveMQConnectionFactory connectionFactory) {   
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();   
		factory.setConnectionFactory(connectionFactory);   
		return factory; 
	}
}
