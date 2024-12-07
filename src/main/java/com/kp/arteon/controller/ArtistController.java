package com.kp.arteon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kp.arteon.models.ArtworkModel;
import com.kp.arteon.models.Users;
import com.kp.arteon.models.profileupdate;
import com.kp.arteon.services.ArtistService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/Arteon")
public class ArtistController 
{

	private ArtistService artistservice;
	
	public ArtistController(ArtistService artistservice)
	{
		this.artistservice=artistservice;
	}
	
	
	  @PostMapping("/artistprofileupdate")
	    public ResponseEntity<String> artistprofileupdate(@RequestBody profileupdate profile) 
	  {
		  
	        String result = artistservice.update(profile);
	        
	        if (result.equals("User not found!")) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                                 .body("{\"success\": false, \"message\": \"User not found!\"}");
	        } else {
	            return ResponseEntity.status(HttpStatus.OK)
	                                 .body("{\"success\": true, \"message\": \"" + result + "\"}");
	        }

	    }
	  
	  @PostMapping("/submitart")
	    public ResponseEntity<String> submitArt(@RequestBody ArtworkModel art) 
	  {
	        String result = artistservice.submitart(art);
	        
	        if (result.equals("User not found!")) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                                 .body("{\"success\": false, \"message\": \"User not found!\"}");
	        } else {
	            return ResponseEntity.status(HttpStatus.OK)
	                                 .body("{\"success\": true, \"message\": \"" + result + "\"}");
	        }

	    }
	  
	  
	  @GetMapping("/artworks/user/{id}")
	    public Users getUserById(@PathVariable("id") Long id) {
	        Users user = artistservice.getid(id);
	        if (user == null) {
	            throw new RuntimeException("User not found with ID: " + id);
	        }
	        return user;
	    }
	  
}
