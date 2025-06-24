package com.travelagency.service;

import com.travelagency.entity.Emplacement;
import com.travelagency.entity.Autocar;
import com.travelagency.repository.EmplacementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmplacementService {
    
    @Autowired
    private EmplacementRepository emplacementRepository;
    
    public List<Emplacement> findAll() {
        return emplacementRepository.findAll();
    }
    
    public Optional<Emplacement> findById(Long id) {
        return emplacementRepository.findById(id);
    }
    
    public Emplacement save(Emplacement emplacement) {
        return emplacementRepository.save(emplacement);
    }
    
    public List<Emplacement> findEmplacementsDisponibles(Set<Autocar> autocars) {
        return autocars.stream()
                .flatMap(autocar -> autocar.getEmplacements().stream())
                .filter(emplacement -> emplacement.getPassager() == null)
                .collect(Collectors.toList());
    }
    
    public List<Emplacement> findByAutocar(Autocar autocar) {
        return emplacementRepository.findByAutocar(autocar);
    }
}
