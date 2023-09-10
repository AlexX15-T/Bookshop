package com.PSproject.controller;

import com.PSproject.model.User;
import com.PSproject.model.UserHelper;
import com.PSproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    User login(@RequestBody UserHelper userHelper) {
        return userRepository.findByUsernameAndPassword(userHelper.getUsername(), userHelper.getPassword());
    }

    @PostMapping("/register")
    User register(@RequestBody UserHelper userHelper) {
        User newUser = new User();
        newUser.setUsername(userHelper.getUsername());
        newUser.setPassword(userHelper.getPassword());
        newUser.setUsertype("employee");
        return userRepository.save(newUser);
    }

}
