package com.kp.arteon.services;

import org.springframework.stereotype.Service;

import com.kp.arteon.models.ContactForm;
import com.kp.arteon.repository.ContactRepo;

@Service
public class Contactusimplementation 
{

	private ContactRepo conrepo;
	
	
	 public Contactusimplementation(ContactRepo conrepo)
	 { 
		 this.conrepo=conrepo;
		 
	 }
	public void saveContactForm(ContactForm contactForm)
	{
		
		conrepo.save(contactForm);
	}

	
}
