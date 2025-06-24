package com.travelagency.service;

import com.travelagency.entity.TypeAutocar;
import com.travelagency.repository.TypeAutocarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeAutocarService {
    
    @Autowired
    private TypeAutocarRepository typeAutocarRepository;
    
    public List<TypeAutocar> findAll() {
        return typeAutocarRepository.findAll();
    }
    
    public Optional<TypeAutocar> findById(Long id) {
        return typeAutocarRepository.findById(id);
    }
    
    public TypeAutocar save(TypeAutocar typeAutocar) {
        return typeAutocarRepository.save(typeAutocar);
    }
    
    public void deleteById(Long id) {
        typeAutocarRepository.deleteById(id);
    }
}
