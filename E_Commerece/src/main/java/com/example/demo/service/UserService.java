package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;

public interface UserService {
	
   public void createUser(User user);
   
   public List<User> allUser();
   
   public User findUser(int id);
   
   public String authenticate(String email, String password);

}
