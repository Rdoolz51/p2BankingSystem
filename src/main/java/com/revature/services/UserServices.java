package com.revature.services;

import com.revature.daos.AccountDAO;
import com.revature.daos.AddressDAO;
import com.revature.daos.UserDAO;
import com.revature.exceptions.UserUpdateException;
import com.revature.models.Address;
import com.revature.models.User;
import com.revature.security.TokenGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServices {

  private final UserDAO userDAO;
  private final AddressDAO adddressDAO;
  private final AccountDAO accountDAO;
  private final TokenGenerator tokenGenerator;

  @Autowired
  public UserServices(UserDAO userDAO, AddressDAO adddressDAO, AccountDAO accountDAO,
                      TokenGenerator tokenGenerator) {
    this.userDAO = userDAO;
    this.adddressDAO = adddressDAO;
    this.accountDAO = accountDAO;
    this.tokenGenerator = tokenGenerator;
  }

  public List<User> getAllUsers() {
    log.info("Retrieved all users");
    return userDAO.findAll();
  }

  public User getUserById(int id) {
    if (id > 0 && userDAO.existsById(id)) {
      Optional<User> user = userDAO.findById(id);

      if (user.isPresent()) {
        User retrieved = user.get();

        log.info("Retrieved user with ID: " + id);
        return retrieved;
      }
    }
    log.info("No user found with ID: " + id);
    return null;
  }

  public User getUserByEmail(String email) {
    if (email != null && !email.isEmpty()) {
      log.info("Retrieved user with email: " + email);
      User user = userDAO.findByEmail(email);

      return user;
    }
    log.info("No user found with email: " + email);
    return null;
  }

  public User updateUser(User user, User updated) {
    if (user == null || updated == null) {
      log.warn("User object received was null");
      throw new NullPointerException("User object was null");
    }

    if (user.getId() != updated.getId()) {
      log.warn(
        "User IDs do not match userID: " + user.getId() + " udpatedID: " +
        updated.getId());
      return null;
    }

    if (userDAO.existsById(user.getId())) {

      User complete = userDAO.save(user);

      if (complete != null) {
        log.info("Updated user with email: " + complete.getEmail());
        return complete;
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

  public User checkUserToken(String token) {
    String email = tokenGenerator.getEmailFromToken(token);
    User user = userDAO.findByEmail(email);

    if (token.isEmpty() || user == null) {
      return null;
    }

    return user;
  }
}
