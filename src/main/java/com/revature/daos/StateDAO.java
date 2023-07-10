package com.revature.daos;

import com.revature.models.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateDAO extends JpaRepository<State, Integer> {
}
