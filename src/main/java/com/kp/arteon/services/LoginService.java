package com.kp.arteon.services;

import com.kp.arteon.models.Users;

public interface LoginService 
{
	Users AuthenticateUser(String email, String password);

}
