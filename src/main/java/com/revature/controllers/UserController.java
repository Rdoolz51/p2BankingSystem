package com.revature.controllers;

import com.revature.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
//@CrossOrigin //TODO later
public class UserController {

    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }
  /*@GetMapping //TODO
    @PostMapping
    @PostMapping
    @DeleteMapping
    
     */
}
