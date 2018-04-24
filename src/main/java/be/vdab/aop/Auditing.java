package be.vdab.aop;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.jboss.logging.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
class Auditing {
	private static final Logger LOGGER = Logger.getLogger(Auditing.class.getName());
	
	@AfterReturning(pointcut = "be.vdab.aop.PointcutExpressions.services()", 
			returning = "returnValue")
	void schrijfAudit(JoinPoint joinPoint, Object returnValue) {
		StringBuilder builder = new StringBuilder("\nTijdstip\t").append(LocalDateTime.now());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && ! "anonymousUser".equals(authentication.getName())) {
			builder.append("\nGebruiker\t").append(authentication.getName());
		}
		
		builder.append("\nMethod\t\t").append(joinPoint.getSignature().toLongString());
		Arrays.stream(joinPoint.getArgs())
			.forEach(object -> builder.append("\nParameters\t").append(object));
		if (returnValue != null) {
			builder.append("\nReturn\t\t");
			if (returnValue instanceof Collection) {
				builder.append(((Collection<?>) returnValue).size()).append(" objects");
			} else {
				builder.append(returnValue.toString());
			}
		}
		
		LOGGER.info(builder.toString());
	}
}
