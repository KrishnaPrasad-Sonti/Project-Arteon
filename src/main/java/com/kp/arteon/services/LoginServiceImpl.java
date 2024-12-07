package com.kp.arteon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kp.arteon.models.Users;
import com.kp.arteon.repository.UserRepository;

@Service
public class LoginServiceImpl implements LoginService
{
	@Autowired
	private UserRepository userrepo;

	@Override
	public Users AuthenticateUser(String email, String password) 
	{
		// TODO Auto-generated method stub
		Users user= userrepo.findByEmail(email);
		
		 if (user != null && user.getPassword().equals(password)) 
		 {
	            
	            return user; 
	        } 
		 else 
		 {
	            // Return null or appropriate message if authentication fails
	            return null;
		
	      }
	}
}

	



