package com.revature.daos;

import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface UserDAO extends JpaRepository<User, Integer>
{
    User getUserByID(int userID);
    User getUserByEmail(String email);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    List<User> getAllUsers();
}
