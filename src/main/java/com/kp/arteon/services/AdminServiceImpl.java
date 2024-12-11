package com.kp.arteon.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kp.arteon.models.UserUpdationModel;
import com.kp.arteon.models.Users;
import com.kp.arteon.models.profileupdate;
import com.kp.arteon.repository.UserRepository;

@Service
public class AdminServiceImpl  implements AdminService
{

	@Autowired
	private UserRepository userrepo;
	
	
	
	@Override
	public String update(profileupdate profile) 
	
	{
		 
		
		 // Ensure the userId is not null or invalid (if userId is Long, check if it is not null)
	    if (profile.getUserId() == null) {
	        return "Invalid user ID!";
	    }
	    
	    System.out.println("Received profile update request with userId: " + profile.getUserId());
	    
	    // Retrieve the user from the database
	    Users user = userrepo.findById(profile.getUserId()).orElse(null);
	    
	    // If user not found, return error message
	    if (user == null) {
	        return "User not found!";
	    }
	    
	   
	    
	    if (profile.getPassword() != null && !profile.getPassword().isEmpty()) {
	        user.setPassword(profile.getPassword());
	    }
	    
	    if (profile.getPhotourl() != null && !profile.getPhotourl().isEmpty()) {
	        user.setProfilePhotoUrl(profile.getPhotourl());
	    }
	    
	    // Save the updated user
	    userrepo.save(user);
	    
	    return "Profile updated successfully!";
	 }



	@Override
	public List<Users> getAllUsers() 
	{
		return userrepo.findAll();
	}



	@Override
	public List<Users> getVisitors() 
	{

		 return userrepo.findByIsRole1AndIsRole2(false, false);
	}



	@Override
	public List<Users> getArtists() {
		return userrepo.findByIsRole1AndIsRole2( true,false);
	}



	@Override
	public List<Users> getCurators() 
	{
		 return userrepo.findByIsRole1AndIsRole2( false,true);
		
	}



	@Override
	public List<Users> getAdmins()
	{
		return userrepo.findByIsRole1AndIsRole2(true, true);
	}



	@Override
	public Map<String, Long> getRoleCounts() 
	{
		Map<String, Long> roleCounts = new HashMap<>();

        roleCounts.put("Admins", userrepo.countByIsRole1AndIsRole2(true, true));
        roleCounts.put("Artists", userrepo.countByIsRole1AndIsRole2(true, false));
        roleCounts.put("Curators", userrepo.countByIsRole1AndIsRole2(false, true));
        roleCounts.put("Visitors", userrepo.countByIsRole1AndIsRole2(false, false));

        return roleCounts;
	}



	@Override
	public String userupdation(UserUpdationModel updateduser)
	{
	    if (updateduser.getId() == null) {
	        return "Invalid user ID!";
	    }

	   
	    // Retrieve the user from the database
	    Users user = userrepo.findById(updateduser.getId()).orElse(null);
	    
	    // If user not found, return error message
	    if (user == null) {
	        return "User not found!";
	    }

	    if (updateduser.getUserType() != null && !updateduser.getUserType().isEmpty()) 
	    {
	        String usertype = updateduser.getUserType().toLowerCase();
	        
	        System.out.println("Updating userType: " + usertype); // Log the lowercase userType
	        
	        if (usertype.equals("admin")) {
	            user.setRole1(true);
	            user.setRole2(true);
	        } else if (usertype.equals("artist")) {
	            user.setRole1(true);
	            user.setRole2(false);
	        } else if (usertype.equals("curator")) {
	            user.setRole1(false);
	            user.setRole2(true);
	        } else {
	            user.setRole1(false);
	            user.setRole2(false); // Default case for "visitor"
	        }
	    }

	    System.out.println("Updated User: " + user.toString()); // Log the updated user
	    
	    // Save the updated user
	    userrepo.save(user);
	    
	    return "User type updated successfully!";
	}



	@Override
	public String userdeletion(long id) 
	{
	    Users user = userrepo.findById(id).orElse(null);

	    // If user not found, return error message
	    if (user == null) 
	    {
	        return "User not found!";
	    }
	    else
	    {
	        userrepo.delete(user);
	        return "User deleted successfully!";
	    }
	    
	}

		
	
	
	
	
	
	}


	






