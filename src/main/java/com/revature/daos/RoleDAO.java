package com.revature.daos;

import com.revature.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends JpaRepository<Role, Integer> {
  Role findByTitle(String title);
}
