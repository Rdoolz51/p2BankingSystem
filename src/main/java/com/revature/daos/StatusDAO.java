package com.revature.daos;

import com.revature.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusDAO extends JpaRepository<Status, Integer> {
  Status findByStatus(String status);
}
