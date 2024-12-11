package com.kp.arteon.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kp.arteon.models.UserUpdationModel;
import com.kp.arteon.models.Users;
import com.kp.arteon.models.profileupdate;

@Service
public interface AdminService
{
	
      public String update(profileupdate profile);
  
      public String userupdation(UserUpdationModel updateduser);
      
      public List<Users> getAllUsers();
      public List<Users> getVisitors();
      public List<Users> getArtists();
      public List<Users> getCurators();
      public List<Users> getAdmins() ;
      public Map<String, Long> getRoleCounts();
      
      public String userdeletion(long id);
      
      
      
}
