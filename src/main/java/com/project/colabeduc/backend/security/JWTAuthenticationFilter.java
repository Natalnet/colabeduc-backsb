package com.project.colabeduc.backend.security;

import java.io.IOException;
import java.util.Collections;

import org.apache.catalina.connector.Response;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.colabeduc.backend.entities.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
  private AuthenticationManager authenticationManager;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException {
    try {
      Usuario usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);

      return authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              usuario.getUsername(),
              usuario.getPassword(),
              Collections.emptyList()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
      Authentication auth) throws IOException, ServletException {

    response.setHeader("Content-Type", "application/json; charset=UTF-8");
    response.setCharacterEncoding("UTF-8");

    try {

      String login = ((User)auth.getPrincipal()).getUsername();

      String token = TokenUtil.getToken(login);

      String json = String.format("{\"token\": \"%s\" }", token);

      response.getWriter().write(json);

    } catch (Exception e) {
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      String json = String.format("{ \"msg\" : \"%s\" }", e.getMessage());
      response.getWriter().write(json);
    }
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException failed) throws IOException, ServletException {
    response.setHeader("Content-Type", "application/json; charset=UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.setStatus(Response.SC_UNAUTHORIZED);
    String json = "{ \"msg\" : \"Erro: login ou senha inválidos\" }";
    response.getWriter().write(json);
  }
}
