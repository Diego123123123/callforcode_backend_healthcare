package org.callforcode.healthcare.services;

import java.util.List;
import org.callforcode.healthcare.entities.User;
import org.callforcode.healthcare.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserService implements IUserService {

    private final UserRepository repository;

    private final RoleService roleService;

    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository repository, RoleService roleService, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.roleService = roleService;
        this.encoder = encoder;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = repository.findUserByEmail(s);
        if (user == null) {
            throw new UsernameNotFoundException("The user does not exist");
        }
        return user;
    }
    
    public void register(User user) {
        if (repository.findUserByEmail(user.getEmail()) == null) {
            user.setPassword("{bcrypt}" + encoder.encode(user.getPassword()));
            user.setAuthorities(List.of(roleService.find("ROLE_USER")));
            repository.save(user);  
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
