package com.kp.arteon.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

	@Entity
	public class ArtworkDetails {

	    @Id     	
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false)
	    private String imageUrl;

	    private String description;

	    @Column(nullable = false)
	    private String artistName;

	   

	 

	    @ManyToOne
	    @JoinColumn(name = "exhibition_id")
	    private Exhibition exhibition;

	    // Default constructor
	    public ArtworkDetails() {}

	    // Constructor with fields
	    public ArtworkDetails(String imageUrl, String description, String artistName) {
	        this.imageUrl = imageUrl;
	        this.description = description;
	        this.artistName = artistName;
	        	        
	    }

	    // Getters and Setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getImageUrl() {
	        return imageUrl;
	    }

	    public void setImageUrl(String imageUrl) {
	        this.imageUrl = imageUrl;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public String getArtistName() {
	        return artistName;
	    }

	    public void setArtistName(String artistName) {
	        this.artistName = artistName;
	    }

	   
	   

	    public Exhibition getExhibition() {
	        return exhibition;
	    }

	    public void setExhibition(Exhibition exhibition) {
	        this.exhibition = exhibition;
	    }
	    	
	    @Override
	    public String toString() {
	        return "ArtworkDetails [id=" + id + ", imageUrl=" + imageUrl + ", description=" + description + 
	               ", artistName=" + artistName+ "]";
	        
	    }
	}