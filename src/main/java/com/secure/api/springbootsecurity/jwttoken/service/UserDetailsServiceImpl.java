package com.secure.api.springbootsecurity.jwttoken.service;

import com.secure.api.springbootsecurity.jwttoken.entites.ApplicationUser;
import com.secure.api.springbootsecurity.jwttoken.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user=applicationUserRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getUsername(),user.getPassword(), Collections.emptyList());
    }
}
