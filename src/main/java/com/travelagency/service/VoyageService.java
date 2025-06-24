package com.travelagency.service;

import com.travelagency.entity.Voyage;
import com.travelagency.entity.TypeVoyage;
import com.travelagency.repository.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoyageService {
    
    @Autowired
    private VoyageRepository voyageRepository;
    
    public List<Voyage> findAll() {
        return voyageRepository.findAll();
    }
    
    public Optional<Voyage> findById(Long id) {
        return voyageRepository.findById(id);
    }
    
    public Voyage save(Voyage voyage) {
        return voyageRepository.save(voyage);
    }
    
    public void deleteById(Long id) {
        voyageRepository.deleteById(id);
    }
    
    public List<Voyage> findByTypeVoyage(TypeVoyage typeVoyage) {
        return voyageRepository.findByTypeVoyage(typeVoyage);
    }
    
    public List<Voyage> findByHotelVilleNomContaining(String destination) {
        return voyageRepository.findByHotelVilleNomContaining(destination);
    }
}
