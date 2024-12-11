package com.kp.arteon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kp.arteon.models.Exhibition;

public interface ExhibitionRepo  extends  JpaRepository<Exhibition,Long>
{

}
