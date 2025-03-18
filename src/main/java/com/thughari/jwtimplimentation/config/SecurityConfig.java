package com.thughari.jwtimplimentation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    	return httpSecurity.csrf(customizer -> customizer.disable())
    			.authorizeHttpRequests(request -> request.anyRequest().authenticated())
    			.formLogin(Customizer.withDefaults())
    			.httpBasic(Customizer.withDefaults())
    			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    			.build();
	}
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    UserDetailsService userDetailsService() {
    	UserDetails user1 = User.builder()
                .username("hari1")
                .password(passwordEncoder().encode("111")) // Securely encode password
                .roles("USER")
                .build();

        UserDetails user2 = User.builder()
                .username("siva")
                .password(passwordEncoder().encode("222"))
                .roles("ADMIN")
                .build();
    	return new InMemoryUserDetailsManager(user1, user2);
    }
    
    @Bean
    AuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
    	provider.setUserDetailsService(userDetailsService);
    	return provider;
    }
	
}
