package com.kp.arteon.models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "contactus_table")
public class ContactForm {

	
	@Id
    @Column(name = "email", length = 100, unique = true, nullable = false)    
    private String email;

    
    @Column(name = "message", length = 500, nullable = false) 
    private String message;

    // Getters and Setters
   
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
