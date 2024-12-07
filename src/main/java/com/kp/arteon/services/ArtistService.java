package com.kp.arteon.services;

import com.kp.arteon.models.ArtworkModel;
import com.kp.arteon.models.Users;
import com.kp.arteon.models.profileupdate;

public interface ArtistService 
{
	
	public Users getid(long id);
	public String update(profileupdate profile);
	public String submitart(ArtworkModel art);
}
