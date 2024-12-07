package com.kp.arteon.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kp.arteon.models.Users;
import com.kp.arteon.services.SignupService;

@RestController
@RequestMapping("/Arteon")
public class SignupController 
{
	
	private  SignupService  signupservice ;
	
	
	public SignupController(SignupService signupservice)
	{
		this.signupservice= signupservice;
	}
	
	 @PostMapping("/signup")
	 
	  public ResponseEntity<String> registerUser(@RequestBody Users user)
	  
	 {
	       
	        signupservice.saveUser(user);
	        
	        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
	    }
	
	
}
