package com.revature.daos;

import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {


  User findByUserId(int userId);
  User findByEmail(String email);
  List<User> findAll();

    User findByUsername(String username);
}
