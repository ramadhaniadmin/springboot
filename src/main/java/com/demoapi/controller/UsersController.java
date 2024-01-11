package com.demoapi.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demoapi.model.Users;
import com.demoapi.repository.UsersRepository;
import com.demoapi.requestbody.RequestBodyLogin;
import com.demoapi.responsebody.ResponseBodyListUsers;
import com.demoapi.responsebody.ResponseBodyLogin;
import com.demoapi.utils.JWTUtils;
import com.demoapi.utils.Utils;


@RestController
@RequestMapping("/api/user")
public class UsersController{
	
	@Autowired
	private UsersRepository usersRepository;
	
	//add new user
	@PostMapping(value = "/register", produces = "application/json", consumes = "application/json")
	@ResponseBody
	public Users addUsers(@Valid @RequestBody Users users) {
		Users newUsers = new Users();
		try {
		
			newUsers.setPassword_hash(Utils.getMD5(users.getPassword_hash()));
			
		    newUsers.setUsername(users.getUsername());
            	    
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
		return usersRepository.save(newUsers);
	}
	
	//get list user
	@PostMapping(value = "/list",produces = "application/json")
	@ResponseBody
	public ResponseBodyListUsers listUser(@RequestHeader (name = "Authorization") String token) 
	{
		Optional<Users> user 			= usersRepository.findByAccesToken(token);
		ResponseBodyListUsers response  = new ResponseBodyListUsers();
		List<Users> listUsers 		    = new ArrayList<Users>();
		
		if(user.isPresent()) 
		{
			listUsers = usersRepository.findAll();
			if(listUsers.isEmpty()) {
				response.setStatus("false");
				response.setMessage("Data tidak ditemukan");
				response.setRespCode("01");
				response.setUsers(listUsers);
			}
			else 
			{
				response.setStatus("true");
	     		response.setMessage(listUsers.size() + " Data ditemukan");
	     		response.setRespCode("00");
				response.setUsers(listUsers);
			}
		}
		else 
		{
			response.setStatus("false");
     		response.setMessage("Token expired");
     		response.setRespCode("02");
			response.setUsers(listUsers);
		}
		
	    return response;
	}
	
	//addToken to user table
	public Users addTokenToUser(Users users) {
		return usersRepository.save(users);
	}
	
	
	//login
	@PostMapping(value = "/login",produces = "application/json", consumes = "application/json")
	@ResponseBody
	public ResponseBodyLogin doLogin(@Valid @RequestBody RequestBodyLogin requestBodyLogin) {
		ResponseBodyLogin response = new ResponseBodyLogin();
		
System.out.println("Lgin " + requestBodyLogin.toString());
		
	Optional<Users> user;
		try {
			user = usersRepository.doLogin(requestBodyLogin.getUsername(),Utils.getMD5(requestBodyLogin.getPassword_hash()));
			
			if(user.isPresent()) {
				
				Users userExist = user.get();
			    JWTUtils jwtUtils = new JWTUtils();
				userExist.setAccesToken(jwtUtils.generateToken(userExist));
				addTokenToUser(userExist);
			
				response.setStatus("true");
				response.setMessage("Login Success");
				response.setRespCode("00");
				response.setUsers(userExist);
				
			} else {
				
				response.setStatus("false");
				response.setMessage("Login Failed");
				response.setRespCode("01");
				response.setUsers(null);
				
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			response.setStatus("false");
			response.setMessage(e.getMessage());
			response.setRespCode("01");
			response.setUsers(null);
			e.printStackTrace();
		}
		
		return response;
		
	}
}
