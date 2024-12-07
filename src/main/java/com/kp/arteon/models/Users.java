package com.kp.arteon.models;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "user_table")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-incrementing primary key
    private Long id;

    @Column(name = "username", length = 50, unique = true, nullable = false)  // Unique and non-nullable
    private String username;

    @Column(name = "password", length = 50, nullable = false)  // Non-nullable
    private String password;

    @Column(name = "email", length = 100, unique = true, nullable = false)  // Unique and non-nullable
    private String email;

    @Column(name = "is_role1") // Representing one role
    private boolean isRole1;

    @Column(name = "is_role2") // Representing another role
    private boolean isRole2;
    
    
    @Column(name = "profile_photo_url", length = 255,nullable=true) // Cloud URL for profile photo
    private String profilePhotoUrl;

     

    @ElementCollection
    @CollectionTable(
        name = "gallery_images",  // Name of the table
        joinColumns = @JoinColumn(name = "user_id")  // Foreign key to the Users table
    )
    @Column(name = "image_url", length = 255)  // The column to store the image URL
    private List<String> galleryImages;

 //-----------------------------------------------
    
    // Additional field for the gallery image description (e.g., description or metadata)
    @ElementCollection
    @CollectionTable( name = "gallery_images_metadata",// New table for metadata
        joinColumns = @JoinColumn(name = "user_id")  // Foreign key to the Users table
                    )
    @Column(name = "image_description", length = 255)  // New column for storing additional data
    private List<String> galleryImagesDescriptions;
    
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isRole1() {
        return isRole1;
    }

    public void setRole1(boolean isRole1) {
        this.isRole1 = isRole1;
    }

    public boolean isRole2() {
        return isRole2;
    }

    public void setRole2(boolean isRole2) {
        this.isRole2 = isRole2;
    }

    // Method to get user type directly from booleans
    public String getUserType() {
        return (isRole1 ? "1" : "0") + (isRole2 ? "1" : "0"); // "00", "01", "10", "11"
    }

	public String getProfilePhotoUrl() {
		return profilePhotoUrl;
	}

	public void setProfilePhotoUrl(String profilePhotoUrl) {
		this.profilePhotoUrl = profilePhotoUrl;
	}

	public List<String> getGalleryImages() {
		return galleryImages;
	}



	public void setGalleryImages(List<String> galleryImages) {
		this.galleryImages = galleryImages;
	}
	
	public List<String> getGalleryImagesDescriptions() {
		return galleryImagesDescriptions;
	}

	public void setGalleryImagesDescriptions(List<String> galleryImagesDescriptions) {
		this.galleryImagesDescriptions = galleryImagesDescriptions;
	}
}
