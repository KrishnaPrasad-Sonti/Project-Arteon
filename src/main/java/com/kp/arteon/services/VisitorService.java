package com.kp.arteon.services;

import java.util.List;

import com.kp.arteon.models.Cart;
import com.kp.arteon.models.Exhibition;
import com.kp.arteon.models.Users;
import com.kp.arteon.models.profileupdate;

public interface VisitorService 
{
	public String update(profileupdate profile);
	public List<Exhibition> findAllExhibitions();
	public List<Users> findallartists();
	
	public String addtocart(String imageUrl, long userId);
	public List<Cart> getcart(long id);
	

}
