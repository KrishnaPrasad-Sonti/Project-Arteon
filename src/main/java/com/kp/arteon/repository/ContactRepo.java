package com.kp.arteon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kp.arteon.models.ContactForm;


public interface ContactRepo  extends JpaRepository<ContactForm, String>
{

    ContactForm findByEmail(String email);
}
