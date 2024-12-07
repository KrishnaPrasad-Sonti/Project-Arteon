package com.kp.arteon.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kp.arteon.models.ArtworkModel;
import com.kp.arteon.models.Users;
import com.kp.arteon.models.profileupdate;
import com.kp.arteon.repository.UserRepository;

@Service
public class ArtistServiceImpl  implements ArtistService  {



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




	public String submitart(ArtworkModel art) {
	    // Check if userId is valid
	    if (art.getUserId() == null) {
	        return "Invalid user ID!";
	    }

	    System.out.println("Received artwork submission request with userId: " + art.getUserId());

	    // Retrieve the user from the database using the user ID
	    Users user = userrepo.findById(art.getUserId()).orElse(null);

	    // If user not found, return an error message
	    if (user == null) {
	        return "User not found!";
	    }

	    // Add the artwork details (artDetails) and image URL to the user
	    // We check if the lists (galleryImages and galleryImagesDescriptions) are initialized
	    if (art.getArtDetails() != null && !art.getArtDetails().isEmpty()) {
	        if (user.getGalleryImagesDescriptions() == null) {
	            user.setGalleryImagesDescriptions(new ArrayList<>());
	        }
	        user.getGalleryImagesDescriptions().add(art.getArtDetails());  // Add the artwork details to the descriptions
	    }

	    if (art.getImageUrl() != null && !art.getImageUrl().isEmpty()) {
	        if (user.getGalleryImages() == null) {
	            user.setGalleryImages(new ArrayList<>());
	        }
	        user.getGalleryImages().add(art.getImageUrl());  // Add the image URL to the gallery images
	    }

	    // Save the updated user with new artwork data
	    userrepo.save(user);

	    return "Artwork submitted successfully!";
	}




	@Override
	public Users getid(long id)
	{
		return userrepo.findById(id).orElse(null);
		
	}



}
