package com.kp.arteon.controller;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kp.arteon.models.UserUpdationModel;
import com.kp.arteon.models.Users;
import com.kp.arteon.models.profileupdate;
import com.kp.arteon.services.AdminService;

@RestController
@RequestMapping("/Arteon")
public class AdminController {

    private AdminService adminservice;

    public AdminController(AdminService adminservice) 
    {
        this.adminservice = adminservice;
    }
    
    
    @PostMapping("/adminprofileupdate")
    public ResponseEntity<String> adminprofileupdate(@RequestBody profileupdate profile) 
  {
        String result = adminservice.update(profile);
        
        if (result.equals("User not found!")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("{\"success\": false, \"message\": \"User not found!\"}");
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                                 .body("{\"success\": true, \"message\": \"" + result + "\"}");
        }

    }
    @GetMapping("/allusers")
    public List<Users> getAllUsers() {
        return adminservice.getAllUsers();
    }

   
    @GetMapping("/users/{role}")
    public ResponseEntity<List<Users>> getUsersByRole(@PathVariable String role) {
        List<Users> users;
        
        switch (role.toLowerCase()) {
            case "admins":
                users = adminservice.getAdmins();
                break;
            case "artists":
                users = adminservice.getArtists();
                break;
            case "curators":
                users = adminservice.getCurators();
                break;
            case "visitors":
                users = adminservice.getVisitors();
                break;
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                     .body(null);  // Invalid role
        }
        
        return ResponseEntity.ok(users);
    }

    
    
    
    
    
    @GetMapping("/roleCounts")
    public ResponseEntity<Map<String, Long>> getRoleCounts() {
        Map<String, Long> roleCounts = adminservice.getRoleCounts();
        return ResponseEntity.ok(roleCounts);
    }
    
    @PutMapping("/userupdation")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdationModel updatedUser) 
    {
        // You can validate and update the user in your database here
        // For example, update the user in the database with the new userType

        // Assuming you have a method updateUser that updates the user and returns the updated user
        String savedUser = adminservice.userupdation(updatedUser);

        if (savedUser.equals("User not found!")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("{\"success\": false, \"message\": \"User not found!\"}");
        } else 
        {
            return ResponseEntity.status(HttpStatus.OK)
                                 .body("{\"success\": true, \"message\": \"" + savedUser + "\"}");
        }
    
    
    }
    
}
