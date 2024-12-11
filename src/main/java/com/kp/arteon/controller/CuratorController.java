package com.kp.arteon.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kp.arteon.models.Exhibition;
import com.kp.arteon.models.Users;
import com.kp.arteon.services.CuratorService;

@RequestMapping("/Arteon/Curator")
@RestController
public class CuratorController 

{
	private CuratorService curatorservice;
		

	   public CuratorController(CuratorService curatorservice)
	   {
		   this.curatorservice=curatorservice;
	   }
	
	   
	   @GetMapping("/artists")
	   public ResponseEntity<List<Users>> getAllArtists() {
	       List<Users> artists = curatorservice.findallartists(); // Fetch users with role "Artist"
	       
	       if (artists == null || artists.isEmpty()) {
	           return ResponseEntity.noContent().build(); // Return 204 No Content if no artists are found
	       }
	       
	       return ResponseEntity.ok(artists);  // Return 200 OK with list of artist objects
	   }
	   
	   @PostMapping("/create-exhibition")
	   public ResponseEntity<?> createExhibition(@RequestBody Exhibition exhibition) {
	       try {
	           // Call the service method to save the exhibition along with artwork details
	           curatorservice.createExhibition(exhibition);

	           // Create a success response with JSON data
	           Map<String, Object> response = new HashMap<>();
	           response.put("message", "Exhibition created successfully!");
	           response.put("exhibitionId", exhibition.getId());  // Assuming exhibition has an ID

	           return ResponseEntity.status(HttpStatus.CREATED).body(response);
	       } catch (Exception e) {
	           // Create an error response with JSON data
	           Map<String, Object> errorResponse = new HashMap<>();
	           errorResponse.put("error", "Error creating exhibition");
	           errorResponse.put("details", e.getMessage());

	           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	       }
	   }
	    // You can also add a GET endpoint to fetch exhibitions if required
	   
	    @GetMapping("/exhibitions")
	    public ResponseEntity<List<Exhibition>> getAllExhibitions() 
	    {
	        List<Exhibition> exhibitions = curatorservice.findAllExhibitions();
	        
	        if (exhibitions == null || exhibitions.isEmpty()) 
	        {
	            return ResponseEntity.noContent().build(); // Return 204 No Content if no exhibitions are found
	      
	            
	            
	        }
	        return ResponseEntity.ok(exhibitions); 
	    }

	    
}
