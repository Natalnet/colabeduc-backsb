package com.project.colabeduc.backend.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.project.colabeduc.backend.services.UsuarioService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

  @Autowired
  private AuthenticationConfiguration configuration;

  @Autowired
  private UsuarioService usuarioService;

  // Filters
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .cors(cors -> cors.configure(http))
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(
            authorizeConfig -> {
              authorizeConfig.requestMatchers(HttpMethod.POST, "/login").permitAll();
              authorizeConfig.requestMatchers(HttpMethod.GET, "/status").permitAll();
              authorizeConfig.requestMatchers(HttpMethod.POST, "/api/usuarios/recuperar-senha").permitAll();
              authorizeConfig.requestMatchers(HttpMethod.POST, "/api/usuarios/resetar-senha").permitAll();
              authorizeConfig.anyRequest().authenticated();
            })
        .addFilter(new JWTAuthenticationFilter(configuration.getAuthenticationManager()))
        .addFilter(new JWTAuthorizationFilter(configuration.getAuthenticationManager(), usuarioService))
        .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOriginPatterns(Arrays.asList("*"));
    configuration.setAllowCredentials(true);
    configuration.setExposedHeaders(Arrays.asList("fresh-token"));
    configuration.setAllowedHeaders(Arrays.asList("content-type", "authorization"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));

    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }

  
}
