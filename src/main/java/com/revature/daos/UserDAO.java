package com.revature.daos;

import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
  User findByEmail(String email);

  //List<User> findByUserAddresses(Address address);

  boolean existsByEmail(String email);
}