

package org.callforcode.healthcare.controllers;

import org.callforcode.healthcare.entities.User;
import org.callforcode.healthcare.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    
    
    @RequestMapping(path = "/users/{userId}",method = RequestMethod.GET)
    public User findUserById(@PathVariable("userId") Long id) {
        return userService.findUserById(id);
    }
}