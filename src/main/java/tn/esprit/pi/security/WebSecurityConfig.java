package tn.esprit.pi.security;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import tn.esprit.pi.security.jwt.AuthEntryPointJwt;
import tn.esprit.pi.security.jwt.AuthTokenFilter;
import tn.esprit.pi.security.jwt.SecurityConstants;
import tn.esprit.pi.security.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().ignoringAntMatchers("/resources/**").disable()
		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests()
		.antMatchers("/servlet/User/Service/**").permitAll()
		.antMatchers("/servlet/User/Stats/**").permitAll()
		.antMatchers("/servlet/book/**").permitAll()
		.antMatchers("/servlet/emprunt/**").permitAll()
		.antMatchers("/servlet/driver/**").permitAll()
		.antMatchers("/servlet/bus/**").permitAll()
		.antMatchers("/servlet/kid/**").permitAll()
		.antMatchers("/servlet/chat/**").permitAll()
		.antMatchers("/servlet/chatSug/**").permitAll()
		.antMatchers("/servlet/consult/**").permitAll()
		.antMatchers("/servlet/daycare/**").permitAll()
		.antMatchers("/servlet/Comment/**").permitAll()
		.antMatchers("/servlet/Liking/**").permitAll()
		.antMatchers("/servlet/Notif/**").permitAll()
		.antMatchers("/servlet/Post/**").permitAll()
		.antMatchers("/servlet/UnhealthyWords/**").permitAll()
		.antMatchers("/servlet/Workshop/**").permitAll()
		.antMatchers("/servlet/event/**").permitAll()
		.antMatchers("/servlet/advertisement/**").permitAll()
		.antMatchers("/servlet/payment/**").permitAll()
		.antMatchers("/servlet/donation/**").permitAll()
		.antMatchers("/servlet/meetings/**").permitAll()
		
		.anyRequest().authenticated()
		.and().logout();
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().regexMatchers("^(/servlet/User/Access/).*");
	}
	
    @Bean
    public JavaMailSender javaMailSender() { 
    	JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    	mailSender.setHost("smtp.gmail.com");
    	mailSender.setPort(587);
    	mailSender.setUsername(SecurityConstants.MY_EMAIL);
    	mailSender.setPassword(SecurityConstants.MY_PASSWORD);
    	Properties props = mailSender.getJavaMailProperties();
    	props.put("mail.transport.protocol", "smtp");
    	props.put("mail.smtp.auth", "true");
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.debug", "true");
    	return mailSender;
    }
}
