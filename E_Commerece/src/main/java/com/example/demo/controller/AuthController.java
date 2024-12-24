package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.JwtRequest;
import com.example.demo.entity.JwtResponse;

import com.example.demo.security.JwtUtil;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	   @Autowired
	    private UserDetailsService userDetailsService;

	    @Autowired
	    private AuthenticationManager manager;

	    @Autowired
	    private JwtUtil helper;
	    
	    private Logger logger = (Logger) LoggerFactory.getLogger(AuthController.class);
	    
	    @PostMapping("/login")
	    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

	        this.doAuthenticate(request.getEmail(), request.getPassword());


	        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
	        String token = this.helper. generateToken(userDetails);
	        String newtoken = this.helper.generateRefreshToken(userDetails.getUsername());
	        JwtResponse response =  JwtResponse.builder()
	                .access_token(token)
	                .username(userDetails.getUsername())
	                .refresh_token(newtoken)
	                .build();
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }

		
	    private void doAuthenticate(String email, String password) {

	        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
	        try {
	            manager.authenticate(authentication);


	        } catch (BadCredentialsException e) {
	            throw new BadCredentialsException(" Invalid Username or Password  !!");
	        }

	    }
	    
	    @PostMapping("/refresh")
	    public Map<String, String> refresh(@RequestBody Map<String, String> request) {
	        String refreshToken = request.get("refresh_token");
	        String email = JwtUtil. getEmailFromToken(refreshToken);
	        Map<String, String> response = new HashMap<>();
	        response.put("access_token", JwtUtil.generateAccessToken(email));
	        return response;
	    }
	    
	    
	    @ExceptionHandler(BadCredentialsException.class)
	    public String exceptionHandler() {
	        return "Credentials Invalid !!";
	    }
}
