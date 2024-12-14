package com.kp.arteon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.kp.arteon.models.Cart;

public interface CartRepo   extends  JpaRepository<Cart,String>
{

	 
	List<Cart>   findAllByVisitorId(Long id);
}
