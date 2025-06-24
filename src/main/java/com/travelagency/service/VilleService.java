package com.travelagency.service;

import com.travelagency.entity.Ville;
import com.travelagency.repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VilleService {
    
    @Autowired
    private VilleRepository villeRepository;
    
    public List<Ville> findAll() {
        return villeRepository.findAll();
    }
    
    public Optional<Ville> findById(Long id) {
        return villeRepository.findById(id);
    }
    
    public Ville save(Ville ville) {
        return villeRepository.save(ville);
    }
}
