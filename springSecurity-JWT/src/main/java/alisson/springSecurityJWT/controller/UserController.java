package alisson.springSecurityJWT.controller;

import alisson.springSecurityJWT.model.User;
import alisson.springSecurityJWT.repository.UserRepository;
import alisson.springSecurityJWT.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @Autowired
    ObjectMapper objectMapper;
    @GetMapping()
    public String getUsers() throws JsonProcessingException {
        List<User> users = userRepository.findAll();
        //ObjectMapper is used to convert Java objects to JSON
        return objectMapper.writeValueAsString(users);
    }

    @GetMapping("/{username}")
    public User getByUsername (@PathVariable("username") String username){
        return userRepository.findByUsername(username);
    }
}
