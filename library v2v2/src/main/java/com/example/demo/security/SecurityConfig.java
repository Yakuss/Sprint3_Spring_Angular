package com.example.demo.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.csrf(csrf -> csrf.disable())

				.cors(cors -> cors.configurationSource(new CorsConfigurationSource() {

					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						CorsConfiguration cors = new CorsConfiguration();

						cors.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
						cors.setAllowedMethods(Collections.singletonList("*"));
						cors.setAllowCredentials(true);
						cors.setAllowedHeaders(Collections.singletonList("*"));
						cors.setExposedHeaders(Collections.singletonList("Authorization"));
						cors.setMaxAge(3600L);
						return cors;
					}
				}))

				// .authorizeHttpRequests().anyRequest().permitAll();
				/*.authorizeHttpRequests(requests -> requests.requestMatchers("/api/all/**")
						.hasAnyAuthority("ADMIN", "USER").requestMatchers(HttpMethod.GET, "/api/getbyid/**")
						.hasAnyAuthority("ADMIN", "USER").requestMatchers(HttpMethod.POST, "/api/addbook/**")
						.hasAnyAuthority("ADMIN").requestMatchers(HttpMethod.PUT, "/api/updatebook/**")
						.hasAuthority("ADMIN").requestMatchers(HttpMethod.DELETE, "/api/delbook/**")
						.hasAuthority("ADMIN").anyRequest().authenticated())
				.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);*/
				.authorizeHttpRequests(requests -> requests
		                // Publicly accessible image endpoint
		                .requestMatchers(HttpMethod.GET, "/api/image/loadfromFS/**").permitAll()
		                
		                // Endpoints accessible to users with specific roles
		                .requestMatchers("/api/all/**").hasAnyAuthority("ADMIN", "USER")
		                .requestMatchers(HttpMethod.GET, "/api/getbyid/**").hasAnyAuthority("ADMIN", "USER")
		                
		                // Endpoints restricted to admin role only
		                .requestMatchers(HttpMethod.POST, "/api/addbook/**").hasAuthority("ADMIN")
		                .requestMatchers(HttpMethod.PUT, "/api/updatebook/**").hasAuthority("ADMIN")
		                .requestMatchers(HttpMethod.DELETE, "/api/delbook/**").hasAuthority("ADMIN")

		                // Authenticate all other requests
		                .anyRequest().authenticated())
		            .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		        
		return http.build();

		/*
		 * http.csrf(csrf -> csrf.disable()).sessionManagement(management ->
		 * management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		 * 
		 * .cors(cors -> cors.configurationSource(new CorsConfigurationSource() {
		 * 
		 * @Override public CorsConfiguration getCorsConfiguration(HttpServletRequest
		 * request) { CorsConfiguration config = new CorsConfiguration();
		 * 
		 * config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
		 * config.setAllowedMethods(Collections.singletonList("*"));
		 * config.setAllowCredentials(true);
		 * config.setAllowedHeaders(Collections.singletonList("*"));
		 * config.setExposedHeaders(Arrays.asList("Authorization"));
		 * config.setMaxAge(3600L); return config; } }))
		 * 
		 * .authorizeHttpRequests(requests -> requests.anyRequest().permitAll()); /*
		 * .requestMatchers("/api/all/**").hasAnyAuthority("ADMIN","USER")
		 * .requestMatchers("/api/getbyid/**").hasAnyAuthority("ADMIN","USER")
		 * .requestMatchers(HttpMethod.POST,"/api/addprod/**").hasAuthority("ADMIN")
		 * .requestMatchers(HttpMethod.PUT,"/api/updateprod/**").hasAuthority("ADMIN")
		 * .requestMatchers(HttpMethod.DELETE,"/api/delprod/**").hasAuthority("ADMIN")
		 * .requestMatchers("/cat/**").hasAnyAuthority("ADMIN","USER")
		 * .anyRequest().authenticated().and() .addFilterBefore(new
		 * JWTAuthorizationFilter(), BasicAuthenticationFilter.class);
		 */

	}

}
