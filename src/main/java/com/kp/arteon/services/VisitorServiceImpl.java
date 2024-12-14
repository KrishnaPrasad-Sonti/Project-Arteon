package com.kp.arteon.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kp.arteon.models.ArtworkDetails;
import com.kp.arteon.models.Cart;
import com.kp.arteon.models.Exhibition;
import com.kp.arteon.models.Users;
import com.kp.arteon.models.profileupdate;
import com.kp.arteon.repository.ArtworkDetailsRepo;
import com.kp.arteon.repository.CartRepo;
import com.kp.arteon.repository.ExhibitionRepo;
import com.kp.arteon.repository.UserRepository;

@Service
public class VisitorServiceImpl  implements VisitorService
{

	@Autowired
	   private final UserRepository userrepo;
	    private final ExhibitionRepo exhibitionRepository;
	    private final ArtworkDetailsRepo artworkDetailsRepository;
	    private final CartRepo cartrepo;
	    
	    public VisitorServiceImpl( UserRepository userrepo, ExhibitionRepo exhibitionRepository, ArtworkDetailsRepo artworkDetailsRepository,CartRepo cartrepo) 
	    {
	        this.userrepo = userrepo;
	        this.exhibitionRepository = exhibitionRepository;
	        this.artworkDetailsRepository = artworkDetailsRepository;
	        this.cartrepo=cartrepo;
	    }
	    
	
	 @Override
	    public String update(profileupdate profile) {
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

	        // Update password if provided
	        if (profile.getPassword() != null && !profile.getPassword().isEmpty()) {
	            user.setPassword(profile.getPassword());
	        }

	        // Update profile photo URL if provided
	        if (profile.getPhotourl() != null && !profile.getPhotourl().isEmpty()) {
	            user.setProfilePhotoUrl(profile.getPhotourl());
	        }

	        // Save the updated user
	        userrepo.save(user);

	        return "Profile updated successfully!";
	    }
	 
	// Fetch all exhibitions
	    public List<Exhibition> findAllExhibitions()
	    {
	        return exhibitionRepository.findAll(); // Fetch all exhibitions from the database
	    }


		@Override
		public List<Users> findallartists() 
		{
			
			return userrepo.findByIsRole1AndIsRole2(true, false);
			
		}


		


		@Override
		public String addtocart(String imageUrl, long userId) {
		    // Fetch the artwork details by image URL
		    ArtworkDetails art = artworkDetailsRepository.findFirstByImageUrl(imageUrl);
		    
		    if (art == null) {
		        return "Artwork not found.";
		    }

		    // Get the description of the artwork
		    String descript = art.getDescription();

		    // Regular expression to extract the price (matches "$3", "$3.50", etc.)
		    String regex = "Price:\\s*\\$([0-9]+(?:\\.[0-9]{1,2})?)";
		    Pattern pattern = Pattern.compile(regex);
		    Matcher matcher = pattern.matcher(descript);

		    double price = 0.0;  // Default value if no price is found

		    // If a price is found, extract it
		    if (matcher.find()) {
		        price = Double.parseDouble(matcher.group(1));  // Extract and convert price to double
		    }

		    // Create a new Cart object and set the details
		    Cart cart = new Cart();
		    cart.setDescription(descript);
		    cart.setImageUrl(imageUrl);
		    cart.setPrice(price);  
		    cart.setVisitorId(userId);

		    
		    cartrepo.save(cart);

		    return "Artwork added to cart successfully!";
		}

		
		


		public List<Cart> getcart(long id) 
		{
					
			List<Cart> cartItems = cartrepo.findAllByVisitorId(id);
		    System.out.println("Fetched Cart Items: " + cartItems); // Log cart items
		    return cartItems;
			   	
		}


}
