package com.kp.arteon.services;


import org.springframework.stereotype.Service;

import com.kp.arteon.models.Users;
import com.kp.arteon.repository.UserRepository;

@Service
public class SignupServiceImpl  implements SignupService
{

	
	private final UserRepository userrepo;

  
    public SignupServiceImpl(UserRepository userRepository)
    {
        this.userrepo = userRepository;
    }
    
	@Override
	public void saveUser(Users user) 
	{
		// TODO Auto-generated method stub
		userrepo.save(user);
		
	}

}
