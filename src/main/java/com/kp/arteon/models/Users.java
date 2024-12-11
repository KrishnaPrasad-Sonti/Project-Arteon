package com.kp.arteon.models;

import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.MapKeyColumn;


@Entity
@Table(name = "user_table")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 50, unique = true, nullable = false)
    private String username;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    @Column(name = "is_role1")
    private boolean isRole1;

    @Column(name = "is_role2")
    private boolean isRole2;

    @Column(name = "profile_photo_url", length = 255, nullable = true)
    private String profilePhotoUrl;

    @ElementCollection
    @CollectionTable(
        name = "gallery_images_metadata", 
        joinColumns = @JoinColumn(name = "user_id") 
    )
    @MapKeyColumn(name = "image_url") // This is the column for the key (image URL)
    @Column(name = "image_description", length = 255) // This is the column for the value (description)
    private Map<String, String> galleryImagesMetadata;

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

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public Map<String, String> getGalleryImagesMetadata() {
        return galleryImagesMetadata;
    }

    public void setGalleryImagesMetadata(Map<String, String> galleryImagesMetadata) {
        this.galleryImagesMetadata = galleryImagesMetadata;
    }
    public String getUserType() {
        return (isRole1 ? "1" : "0") + (isRole2 ? "1" : "0"); // "00", "01", "10", "11"
    }
}
