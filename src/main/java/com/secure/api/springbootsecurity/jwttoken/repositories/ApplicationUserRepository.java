package com.secure.api.springbootsecurity.jwttoken.repositories;

import com.secure.api.springbootsecurity.jwttoken.entites.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser,Long> {
    ApplicationUser findByUsername(String username);
}
