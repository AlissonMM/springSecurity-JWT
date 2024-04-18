package alisson.springSecurityJWT.service;

import alisson.springSecurityJWT.model.User;
import alisson.springSecurityJWT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(User user){
        String password = user.getPassword();
        //encrypting before saving in database
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }
}
