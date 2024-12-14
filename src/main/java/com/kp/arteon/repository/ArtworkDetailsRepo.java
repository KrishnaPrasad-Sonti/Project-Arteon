package com.kp.arteon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kp.arteon.models.ArtworkDetails;

public interface ArtworkDetailsRepo  extends  JpaRepository<ArtworkDetails,Long>
{
    
	 ArtworkDetails findFirstByImageUrl(String  imageurl);
	     
}
