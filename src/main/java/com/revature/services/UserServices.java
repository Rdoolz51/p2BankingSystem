package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.exceptions.UserRegistrationException;
import com.revature.exceptions.UserUpdateException;
import com.revature.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServices {

  private final UserDAO userDAO;

  @Autowired
  public UserServices(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  public List<User> getAllUsers() {
    log.info("Retrieved all users");
    return userDAO.findAll();
  }

  public User getUserById(int id) {
    if (id > 0) {
      Optional<User> user = userDAO.findById(id);

      if (user.isPresent()) {
        log.info("Retrieved user with ID: " + id);
        return user.get();
      }
    }
    log.info("No user found with ID: " + id);
    return null;
  }

  public User getUserByEmail(String email) {
    if (email != null && !email.isEmpty()) {
      log.info("Retrieved user with email: " + email);
      return userDAO.findByEmail(email);
    }
    log.info("No user found with email: " + email);
    return null;
  }

  public User registerUser(User user) {
    if (user == null) {
      log.warn("Null user object was received");
      throw new NullPointerException("User object was null");
    }

    User registered = userDAO.save(user);

    if (registered != null && registered.getId() > 0) {
      log.info("Registered new user with email: " + registered.getEmail());
      return registered;
    }

    log.warn("User registration failed");
    throw new UserRegistrationException(
      "Registration could not be completed using " + user);
  }

  public User updateUser(User user) {
    if (user == null) {
      log.warn("User object received was null");
      throw new NullPointerException("User object was null");
    }

    if (userDAO.existsById(user.getId())) {

      User updated = userDAO.save(user);

      if (updated != null) {
        log.info("Updated user with email: " + updated.getEmail());
        return updated;
      }
    } else {
      log.info("No user found with ID: " + user.getId());
      return null;
    }


    log.warn("User update failed to complete");
    throw new UserUpdateException(
      "User update could not be completed using " + user);
  }

  public boolean deleteUser(User user) {
    if (user == null) {
      log.warn("User object received was null");
      throw new NullPointerException("User object was null");
    }

    if (userDAO.existsById(user.getId())) {
      userDAO.delete(user);
      log.info("User with ID: " + user.getId() + " deleted");

      return true;
    } else {
      log.info("Deletion failed: user ID: " + user.getId() + " does not exist");
      return false;
    }
  }
}
