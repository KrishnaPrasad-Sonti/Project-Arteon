package com.kp.arteon.services;

import java.util.List;

import com.kp.arteon.models.Exhibition;
import com.kp.arteon.models.Users;

public interface CuratorService
{

      public List<Users> findallartists();
      public void createExhibition(Exhibition exhibition);
      public List<Exhibition> findAllExhibitions();
      
}
