package com.travelagency.service;

import com.travelagency.entity.PointDepart;
import com.travelagency.repository.PointDepartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PointDepartService {
    
    @Autowired
    private PointDepartRepository pointDepartRepository;
    
    public List<PointDepart> findAll() {
        return pointDepartRepository.findAll();
    }
    
    public Optional<PointDepart> findById(Long id) {
        return pointDepartRepository.findById(id);
    }
    
    public PointDepart save(PointDepart pointDepart) {
        return pointDepartRepository.save(pointDepart);
    }
    
    public Set<PointDepart> findByIds(List<Long> ids) {
        return ids.stream()
                .map(id -> pointDepartRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }
    
    public void deleteById(Long id) {
        pointDepartRepository.deleteById(id);
    }
}
