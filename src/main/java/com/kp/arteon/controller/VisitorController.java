package com.kp.arteon.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kp.arteon.models.Cart;
import com.kp.arteon.models.Exhibition;
import com.kp.arteon.models.Users;
import com.kp.arteon.models.profileupdate;
import com.kp.arteon.repository.CartRequestDTO;
import com.kp.arteon.services.VisitorService;

@RestController
@RequestMapping("/Arteon/Visitor")
public class VisitorController
{
	private  VisitorService visitorservice;

	public VisitorController(VisitorService visitorservice )
	{
		this.visitorservice=visitorservice;
	}
	
	 @PostMapping("/visitorprofileupdate")
	    public ResponseEntity<String> visitorprofileupdate(@RequestBody profileupdate profile) 
	  {
		  
	        String result = visitorservice.update(profile);
	        
	        if (result.equals("User not found!")) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                                 .body("{\"success\": false, \"message\": \"User not found!\"}");
	        } else {
	            return ResponseEntity.status(HttpStatus.OK)
	                                 .body("{\"success\": true, \"message\": \"" + result + "\"}");
	        }

	    }
	 
	 @GetMapping("/exhibitions")
	    public ResponseEntity<List<Exhibition>> getAllExhibitions() 
	    {
	        List<Exhibition> exhibitions = visitorservice.findAllExhibitions();
	        
	        if (exhibitions == null || exhibitions.isEmpty()) 
	        {
	            return ResponseEntity.noContent().build(); // Return 204 No Content if no exhibitions are found
	      
	            
	            
	        }
	        return ResponseEntity.ok(exhibitions); 
	    }
	 
	 @GetMapping("/artists")
	   public ResponseEntity<List<Users>> getAllArtists() {
	       List<Users> artists = visitorservice.findallartists(); // Fetch users with role "Artist"
	       
	       if (artists == null || artists.isEmpty()) {
	           return ResponseEntity.noContent().build(); // Return 204 No Content if no artists are found
	       }
	       
	       return ResponseEntity.ok(artists);  // Return 200 OK with list of artist objects
	   }
	 
	  @PostMapping("/addcart")
	    public ResponseEntity<Map<String, Object>> addToCart(@RequestBody CartRequestDTO request) {
	        // Check if userId or imageUrls are missing
		  
		  System.out.println("User ID: " + request.getUserId());
		  System.out.println("Image URLs: " + request.getImageUrls());

	        if (request.getUserId() == null || request.getUserId().isEmpty()) 
	        {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body(Map.of("message", "User ID is required."));
	        }

	        if (request.getImageUrls() == null || request.getImageUrls().isEmpty()) 
	        {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body(Map.of("message", "No image URLs provided."));
	        }

	        List<String> failedItems = new ArrayList<>();
	        
	        long visitorid = Long.parseLong(request.getUserId());
	        for (String imageUrl : request.getImageUrls()) 
	        {
	            String result = visitorservice.addtocart(imageUrl, visitorid);
	            if (!result.equalsIgnoreCase("Artwork added to cart successfully!")) 
	            {
	                failedItems.add(imageUrl);
	            }
	        }

	        Map<String, Object> response = new HashMap<>();
	        if (!failedItems.isEmpty()) {
	            response.put("message", "Some items could not be added to the cart.");
	            response.put("failedItems", failedItems);
	            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(response);
	        }

	        response.put("message", "All artworks successfully added to the cart.");
	        return ResponseEntity.status(HttpStatus.OK).body(response);
	    }
	  
	  @GetMapping("/getcart/{id}")
	  public ResponseEntity<List<Cart>> getCartItems(@PathVariable Long id) {
	      List<Cart> cartItems = visitorservice.getcart(id);
	      
	      if (cartItems == null || cartItems.isEmpty()) {
	          System.out.println("Cart is empty or no carts found for ID: " + id);
	          return ResponseEntity.noContent().build(); // 204 No Content
	      } else {
	          System.out.println("Cart found: " + cartItems);
	          return ResponseEntity.ok(cartItems); // 200 OK with the list
	      }
	  }

		    

	    
	
}
