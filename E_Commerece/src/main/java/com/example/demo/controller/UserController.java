package com.example.demo.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	@Autowired
    private JwtUtil jwtUtil;
	
	 @PostMapping("/signup")
	    public ResponseEntity<?> signUp(@RequestBody User user) {
	        userservice.createUser(user);
	        return ResponseEntity.ok("User Register SuccessFully");
	    }

	
	 @PostMapping("/signin")
	    public ResponseEntity<?> signIn(@RequestBody Map<String, String> request) {
	        String email = request.get("email");
	        String password = request.get("password");
	        String accessToken = userservice.authenticate(email, password);
	        String refreshToken = jwtUtil.generateRefreshToken(email);

	        return ResponseEntity.ok(Map.of(
	            "access_token", accessToken,
	            "refresh_token", refreshToken
	        ));
	    }
	 
	 @PostMapping("/refresh")
	    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
	        String refreshToken = request.get("refresh_token");
	        if (jwtUtil.validateToken(refreshToken)) {
	            String email = jwtUtil.getEmailFromToken(refreshToken);
	            String newAccessToken = jwtUtil.generateAccessToken(email);
	            return ResponseEntity.ok(Map.of("access_token", newAccessToken));
	        }
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }
	
	@GetMapping("/getone/{id}")
	public User getUser(@PathVariable int id) {
		return userservice.findUser(id);
	}
	

}
