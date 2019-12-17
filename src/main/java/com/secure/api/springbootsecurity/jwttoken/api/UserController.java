package com.secure.api.springbootsecurity.jwttoken.api;

import com.secure.api.springbootsecurity.jwttoken.entites.ApplicationUser;
import com.secure.api.springbootsecurity.jwttoken.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody ApplicationUser user){
        if(!isUserExists(user)) {
            user.setUsername(user.getUsername().toLowerCase());
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            applicationUserRepository.save(user);
            return new ResponseEntity<>("Username "+user.getUsername()+" Created",HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Username already exists", HttpStatus.FOUND);
        }
    }

    private boolean isUserExists(ApplicationUser user){

        return applicationUserRepository.findByUsername(user.getUsername().toLowerCase())!=null?true:false;
    }
}
