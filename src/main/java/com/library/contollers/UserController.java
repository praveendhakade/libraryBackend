package com.library.contollers;

import com.library.models.User;
import com.library.models.UserResponse;
import com.library.services.JwtService;
import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("register")
    public User registerUser(@RequestBody User user) {
        User newUser = userService.saveUser(user);
//        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(newUser.getUsername(), newUser.getPassword()));
//        UserResponse userResponse = new UserResponse();
//        System.out.println(" controller" + newUser.getUsername());
//        if (auth.isAuthenticated()) {
//            userResponse.setToken(jwtService.generateToken(newUser.getUsername()));
//            userResponse.setIsLoggedIn(true);
//            userResponse.setData("newUser");
//            userResponse.setMessage("User registered successfully");
//            userResponse.setSuccess(true);
//            userResponse.setEmail(newUser.getUsername());
//        } else {
//            userResponse.setToken(null);
//            userResponse.setIsLoggedIn(false);
//            userResponse.setMessage("User could not be registered");
//            userResponse.setSuccess(false);
//            userResponse.setData(null);
//            userResponse.setEmail(null);
//        }
        return newUser;
    }

    @PostMapping("login")
    public UserResponse login(@RequestBody User user) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        UserResponse userResponse = new UserResponse();
        if (auth.isAuthenticated()) {
            userResponse.setToken(jwtService.generateToken(user.getUsername()));
            userResponse.setIsLoggedIn(true);
            userResponse.setData("user");
            userResponse.setMessage("User logged in successfully");
            userResponse.setSuccess(true);
            userResponse.setEmail(user.getUsername());
        } else {
            userResponse.setToken(null);
            userResponse.setIsLoggedIn(false);
            userResponse.setMessage("User could not be logged in");
            userResponse.setSuccess(false);
            userResponse.setData(null);
            userResponse.setEmail(null);
        }
        return userResponse;
    }
}
