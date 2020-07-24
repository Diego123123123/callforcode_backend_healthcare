package org.callforcode.healthcare.controllers;

import org.callforcode.healthcare.entities.RegisterUserModel;
import org.callforcode.healthcare.entities.User;
import org.callforcode.healthcare.services.UserService;
import org.callforcode.healthcare.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value = "/register")
public class UserRegistrationController {
    
    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtUtil jwtUtilService;

    @Autowired
    public UserRegistrationController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtilService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtilService = jwtUtilService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody RegisterUserModel registerUserModel) {
        User user = new User();
        user.setEmail(registerUserModel.getEmail());
        user.setName(registerUserModel.getName());
        user.setPassword(registerUserModel.getPassword());
        userService.register(user);
        return ResponseEntity.ok(user);
    }
}
