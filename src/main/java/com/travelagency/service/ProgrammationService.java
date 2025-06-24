package com.travelagency.service;

import com.travelagency.entity.Programmation;
import com.travelagency.repository.ProgrammationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammationService {
    
    @Autowired
    private ProgrammationRepository programmationRepository;
    
    public List<Programmation> findAll() {
        return programmationRepository.findAll();
    }
    
    public Optional<Programmation> findById(Long id) {
        return programmationRepository.findById(id);
    }
    
    public Programmation save(Programmation programmation) {
        return programmationRepository.save(programmation);
    }
}
