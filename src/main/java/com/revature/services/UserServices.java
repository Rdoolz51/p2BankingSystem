package com.revature.services;

import com.revature.daos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    private final UserDAO userDAO;
    @Autowired
    public UserServices(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
