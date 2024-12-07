package com.kp.arteon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kp.arteon.models.ContactForm;
import com.kp.arteon.services.Contactusimplementation;

@RestController
@RequestMapping("/contactus")
public class ContactController {

    private final Contactusimplementation contactService;

    // Constructor-based dependency injection
    public ContactController(Contactusimplementation contactService) {
        this.contactService = contactService;
    }

    
    @PostMapping("/submit")  //                      @requestbody for telling the spring to take that json incomming as object
    public ResponseEntity<String> submitContactForm(@RequestBody ContactForm contactForm)
    {
        contactService.saveContactForm(contactForm); // Delegate saving the contact form to the service
        return ResponseEntity.status(HttpStatus.CREATED).body("Thank you for reaching out. We will get back to you shortly!");
    }
}
