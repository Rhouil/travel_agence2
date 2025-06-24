package com.travelagency.service;

import com.travelagency.entity.Autocar;
import com.travelagency.entity.Emplacement;
import com.travelagency.repository.AutocarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AutocarService {
    
    @Autowired
    private AutocarRepository autocarRepository;
    
    @Autowired
    private EmplacementService emplacementService;
    
    public List<Autocar> findAll() {
        return autocarRepository.findAll();
    }
    
    public Optional<Autocar> findById(Long id) {
        return autocarRepository.findById(id);
    }
    
    public Autocar save(Autocar autocar) {
        return autocarRepository.save(autocar);
    }
    
    public Set<Autocar> findByIds(List<Long> ids) {
        return ids.stream()
                .map(id -> autocarRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }
    
    public void creerEmplacements(Autocar autocar) {
        for (int i = 1; i <= autocar.getNombrePlaces(); i++) {
            Emplacement emplacement = new Emplacement();
            emplacement.setNumeroSiege(i);
            emplacement.setAutocar(autocar);
            emplacementService.save(emplacement);
        }
    }
    
    public void deleteById(Long id) {
        autocarRepository.deleteById(id);
    }
}
