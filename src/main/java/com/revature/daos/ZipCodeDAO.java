package com.revature.daos;

import com.revature.models.ZipCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZipCodeDAO extends JpaRepository<ZipCode, Integer> {
}
