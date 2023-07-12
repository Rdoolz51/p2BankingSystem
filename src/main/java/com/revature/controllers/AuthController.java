package com.revature.controllers;

import com.revature.daos.*;
import com.revature.dtos.AuthDTO;
import com.revature.dtos.LoginDTO;
import com.revature.dtos.RegisterDTO;
import com.revature.models.Address;
import com.revature.models.User;
import com.revature.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:3000"})
public class AuthController {
  private final AuthenticationManager authManager;
  private final UserDAO userDAO;
  private final AddressDAO addressDAO;
  private final StateDAO stateDAO;
  private final ZipCodeDAO zipCodeDAO;
  private final RoleDAO roleDAO;
  private final PasswordEncoder passEncoder;
  private final TokenGenerator tokenGenerator;

  @Autowired
  public AuthController(AuthenticationManager authManager,
                        UserDAO userDAO,
                        AddressDAO addressDAO, StateDAO stateDAO,
                        ZipCodeDAO zipCodeDAO, RoleDAO roleDAO, PasswordEncoder passEncoder,
                        TokenGenerator tokenGenerator) {
    this.authManager = authManager;
    this.userDAO = userDAO;
    this.addressDAO = addressDAO;
    this.stateDAO = stateDAO;
    this.zipCodeDAO = zipCodeDAO;
    this.roleDAO = roleDAO;
    this.passEncoder = passEncoder;
    this.tokenGenerator = tokenGenerator;
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
    if (userDAO.existsByEmail(registerDTO.getEmail())) {
      return new ResponseEntity<>("Email is already in use", HttpStatus.BAD_REQUEST);
    }

    Address address = registerDTO.getAddress();

    address.setState(stateDAO.findById(registerDTO.getAddress().getState().getId()).get());
    address.setZip(zipCodeDAO.findById(registerDTO.getAddress().getZip().getId()).get());

    User user = new User(
      registerDTO.getFirstName(),
      registerDTO.getLastName(),
      registerDTO.getEmail(),
      passEncoder.encode(registerDTO.getPassword()),
      registerDTO.getPhoneNumber(),
      roleDAO.findByTitle("Customer"),
      addressDAO.save(address),
      registerDTO.getIncome()
      );

    return new ResponseEntity<>(userDAO.save(user), HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<AuthDTO> login(@RequestBody LoginDTO loginDTO) {
    Authentication authentication = authManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        loginDTO.getEmail(),
        loginDTO.getPassword()
      )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    return new ResponseEntity<>(
      new AuthDTO(tokenGenerator.generateToken(authentication)),
      HttpStatus.OK
    );
  }
}