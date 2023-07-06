package com.revature.security;

import com.revature.daos.UserDAO;
import com.revature.models.Role;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UDService implements UserDetailsService {
  private final UserDAO userDAO;

  @Autowired
  public UDService(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws
    UsernameNotFoundException {
    User u = userDAO.findByEmail(username);

    if (u == null) {
      new UsernameNotFoundException("Could not find username: " + username);
    }

    return new org.springframework.security.core.userdetails.User(
      u.getEmail(),
      u.getPassword(),
      mapRoleToAuthority(u.getRole())
    );
  }

  private Collection<GrantedAuthority> mapRoleToAuthority(Role role) {
    List<GrantedAuthority> authorities = new ArrayList<>();

    authorities.add(new SimpleGrantedAuthority(role.getTitle()));

    return authorities;
  }
}