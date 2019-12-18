package com.secure.api.springbootsecurity.jwttoken.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.secure.api.springbootsecurity.jwttoken.entites.ApplicationUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.secure.api.springbootsecurity.jwttoken.security.constants.SecurityConstants.EXPIRATION_TIME;
import static com.secure.api.springbootsecurity.jwttoken.security.constants.SecurityConstants.SECRET;
import static com.secure.api.springbootsecurity.jwttoken.security.constants.SecurityConstants.HEADER_STRING;
import static com.secure.api.springbootsecurity.jwttoken.security.constants.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager=authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            ApplicationUser creds=new ObjectMapper()
                    .readValue(request.getInputStream(),ApplicationUser.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>()
                    )
            );

        }catch(IOException exp){
            throw new RuntimeException(exp);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String jwtToken= Jwts.builder()
                .setSubject(((User)authResult.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
        response.addHeader(HEADER_STRING,TOKEN_PREFIX + jwtToken);
    }
}
