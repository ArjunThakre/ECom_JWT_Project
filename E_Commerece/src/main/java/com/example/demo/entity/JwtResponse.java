package com.example.demo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class JwtResponse {
	
	private String access_token;
	
	private String refresh_token;
	
	private String username;

	public JwtResponse(String access_token, String refresh_token, String username) {
		super();
		this.access_token = access_token;
		this.refresh_token = refresh_token;
		this.username = username;
	}

	public JwtResponse() {
		super();
	}

	
	

	
	

}
