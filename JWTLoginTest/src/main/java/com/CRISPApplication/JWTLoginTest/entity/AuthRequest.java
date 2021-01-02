package com.CRISPApplication.JWTLoginTest.entity;

public class AuthRequest {//this is an object that holds a username and password
							//it's only purpose is to map the info from Angular to here
	
	
	private String username;
	private String password;
	
	public AuthRequest() {
	}
	
	
	public AuthRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}
		
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
