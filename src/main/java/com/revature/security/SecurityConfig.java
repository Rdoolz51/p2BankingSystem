package com.revature.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig {
  private final UDService udService;
  private final AuthEntry authEntry;
  private final JWTFilter filter;

  @Autowired
  public SecurityConfig(UDService udService, AuthEntry authEntry,
                        JWTFilter filter) {
    this.udService = udService;
    this.authEntry = authEntry;
    this.filter = filter;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .cors()
      .and()
      .csrf().disable()
      .exceptionHandling()
      .authenticationEntryPoint(authEntry)
      .and()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .authorizeRequests()
      .antMatchers("/").permitAll()
      .antMatchers("/auth/**").permitAll()
      .antMatchers(HttpMethod.POST, "/transactions/**").hasAuthority("Merchant")
      .antMatchers(HttpMethod.GET, "/admin/**").hasAuthority("Admin")
      .antMatchers(HttpMethod.POST, "/admin/**").hasAuthority("Admin")
      .antMatchers(HttpMethod.PUT, "/admin/**").hasAuthority("Admin")
      .antMatchers(HttpMethod.DELETE, "/admin/**").hasAuthority("Admin")
      .antMatchers(HttpMethod.GET, "/mybank/**").hasAnyAuthority("Admin", "Customer")
      .antMatchers(HttpMethod.POST, "/mybank/**").hasAnyAuthority("Admin", "Customer")
      .antMatchers(HttpMethod.PUT, "/mybank/**").hasAnyAuthority("Admin", "Customer")
      .antMatchers("**").denyAll()
      .and()
      .httpBasic();

    http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public AuthenticationManager getAuthenticationManager(
    AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
