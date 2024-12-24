package com.example.demo.service;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userrepo;
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	 
	 @Autowired
	  private JwtUtil jwtUtil;

	
	@Override
	public void createUser(User user) {
		 if (userrepo.existsByEmail(user.getEmail())) {
	            throw new RuntimeException("Email already registered");
	        }

	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        userrepo.save(user);
	}

	@Override
	public List<User> allUser() {
	 	List<User>user1=userrepo.findAll();
	 	return user1;
		
	}

	@Override
	public User findUser(int id) {
		return userrepo.findById(id).orElseThrow();
	}

	 public String authenticate(String email, String password) {
		  User user = userrepo.findByEmail(email)
	                .orElseThrow(() -> new UsernameNotFoundException("Invalid email or password"));
		
	        if (!passwordEncoder.matches(password, user.getPassword())) {
	            throw new BadCredentialsException("Invalid email or password");
	        }

	        return jwtUtil.generateAccessToken(email);
	    }
}
