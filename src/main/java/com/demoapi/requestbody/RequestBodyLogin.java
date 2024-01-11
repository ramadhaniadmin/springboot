package com.demoapi.requestbody;

public class RequestBodyLogin {
	
	String username;
	String password_hash;
	
	public String getPassword_hash() {
		return password_hash;
	}
	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	@Override
	public String toString() {
		return "RequestBodyLogin [username=" + username + ", password=" + password_hash + "]";
	}
	
}
