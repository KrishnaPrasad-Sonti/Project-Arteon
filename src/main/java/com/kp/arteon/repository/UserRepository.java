package com.kp.arteon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.kp.arteon.models.Users;

@Repository
public interface UserRepository  extends  JpaRepository<Users, Long>
{
   
	   Users findByEmail(String email);
	   List<Users> findByIsRole1AndIsRole2(boolean isRole1, boolean isRole2);
	   long countByIsRole1AndIsRole2(boolean isRole1, boolean isRole2);
	   
	
}
