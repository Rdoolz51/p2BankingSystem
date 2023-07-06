package com.revature.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {
  private final TokenGenerator tokenGenerator;
  private final UDService udService;

  @Autowired
  public JWTFilter(TokenGenerator tokenGenerator, UDService udService) {
    this.tokenGenerator = tokenGenerator;
    this.udService = udService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws
    ServletException, IOException {
    String token = getJwtFromRequest(request);

    if (token != null && tokenGenerator.validateToken(token)) {
      UserDetails user = udService.loadUserByUsername(
        tokenGenerator.getEmailFromToken(token)
      );

      UsernamePasswordAuthenticationToken authToken =
        new UsernamePasswordAuthenticationToken(
          user, null, user.getAuthorities()
        );

      authToken.setDetails(
        new WebAuthenticationDetailsSource().buildDetails(request)
      );

      SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    filterChain.doFilter(request, response);
  }

  private String getJwtFromRequest(HttpServletRequest request) {
    String bearer = request.getHeader("Authorization");

    if (bearer != null && bearer.startsWith("Bearer")) {
      return bearer.substring(7, bearer.length());
    }

    return null;
  }
}
