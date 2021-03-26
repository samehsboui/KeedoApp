package tn.esprit.pi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class CustomAuthenticationFailureHandler {
	@Autowired
	private MessageSource messageSource;
	
	
}
