
package org.callforcode.healthcare.controllers;

import org.callforcode.healthcare.entities.AuthenticationRequest;
import org.callforcode.healthcare.entities.AuthenticationResponse;
import org.callforcode.healthcare.services.UserService;
import org.callforcode.healthcare.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value = "/authenticate")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    
    private final UserService userService;
    
    private final JwtUtil jwtUtilService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtilService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtilService = jwtUtilService;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_CREDENTIALS_MESSAGE_ERROR);
        }
        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtilService.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
    
    private static final String INVALID_CREDENTIALS_MESSAGE_ERROR = "Invalid credentials";
    
}
