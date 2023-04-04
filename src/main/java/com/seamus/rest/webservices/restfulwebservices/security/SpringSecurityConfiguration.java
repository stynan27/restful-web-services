package com.seamus.rest.webservices.restfulwebservices.security;

// withDefaults from type search of Customizer type from org.springframework.security
// Type search: Ctrl + Shift + T 
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.security.auth.message.config.AuthConfig;

@Configuration
public class SpringSecurityConfiguration {

	// define a custom filterChain for SpringSecurity
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 1.) Ensure ALL requests are authenticated
		// currently deprecated, but NBD
		http.authorizeRequests(
				auth -> auth.anyRequest().authenticated()
		);
		
		// 2.) If not authenticated, show error page
		http.httpBasic(withDefaults());
		
		// 3.) CSRF -> POST, PUT Reqs
		http.csrf().disable();
		
		return http.build();
	}
}
