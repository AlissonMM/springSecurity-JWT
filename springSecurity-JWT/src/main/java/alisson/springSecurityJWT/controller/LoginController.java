package alisson.springSecurityJWT.controller;

import alisson.springSecurityJWT.dtos.Login;
import alisson.springSecurityJWT.dtos.Session;
import alisson.springSecurityJWT.model.User;
import alisson.springSecurityJWT.repository.UserRepository;
import alisson.springSecurityJWT.security.JWTCreator;
import alisson.springSecurityJWT.security.JWTObject;
import alisson.springSecurityJWT.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
@RestController
public class LoginController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public Session logIn(@RequestBody Login login){
        User user = userRepository.findByUsername(login.getUsername());
        if (user != null){
            boolean passwordOk = passwordEncoder.matches(login.getPassword(), user.getPassword());
            if(!passwordOk){
                throw new RuntimeException("Invalid Password to login: " + login.getUsername());
            }
            //
            Session session = new Session();
            session.setLogin(user.getUsername());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration(null);
            jwtObject.setRoles(user.getRoles());
            session.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
            return session;
        }else {
            throw new RuntimeException("Login error");
        }
    }
}
