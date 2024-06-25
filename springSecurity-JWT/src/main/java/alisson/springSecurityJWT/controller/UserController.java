package alisson.springSecurityJWT.controller;

import alisson.springSecurityJWT.model.User;
import alisson.springSecurityJWT.repository.UserRepository;
import alisson.springSecurityJWT.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public void postUser(@RequestBody User user){
        userService.createUser(user);
    }

    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public List<User> getUser(){
        return userRepository.findAll();
    }
}
