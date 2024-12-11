package com.kp.arteon.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kp.arteon.models.ArtworkDetails;
import com.kp.arteon.models.Exhibition;
import com.kp.arteon.models.Users;
import com.kp.arteon.repository.ArtworkDetailsRepo;
import com.kp.arteon.repository.ExhibitionRepo;
import com.kp.arteon.repository.UserRepository;

@Service
public class CuratorServiceImpl implements CuratorService {

    private final UserRepository userrepo;
    private final ExhibitionRepo exhibitionRepository;
    private final ArtworkDetailsRepo artworkDetailsRepository;

    
    public CuratorServiceImpl(UserRepository userrepo, ExhibitionRepo exhibitionRepository, ArtworkDetailsRepo artworkDetailsRepository) 
    {
        this.userrepo = userrepo;
        this.exhibitionRepository = exhibitionRepository;
        this.artworkDetailsRepository = artworkDetailsRepository;
    }

    public void createExhibition(Exhibition exhibition) {
        // Save the exhibition first
        Exhibition savedExhibition = exhibitionRepository.save(exhibition); // No more undefined method error

        // Save each artwork related to the exhibition
        for (ArtworkDetails artwork : exhibition.getArtworkDetails()) {
            artwork.setExhibition(savedExhibition); // Associate each artwork with the exhibition
            artworkDetailsRepository.save(artwork); // Save the artwork details
        }
    }

    // Fetch all exhibitions
    public List<Exhibition> findAllExhibitions() {
        return exhibitionRepository.findAll(); // Fetch all exhibitions from the database
    }

    @Override
    public List<Users> findallartists() {
        return userrepo.findByIsRole1AndIsRole2(true, false);
    }
}
