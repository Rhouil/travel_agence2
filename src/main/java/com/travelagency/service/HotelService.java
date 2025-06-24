package com.travelagency.service;

import com.travelagency.entity.Hotel;
import com.travelagency.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    
    @Autowired
    private HotelRepository hotelRepository;
    
    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }
    
    public Optional<Hotel> findById(Long id) {
        return hotelRepository.findById(id);
    }
    
    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public void deleteById(Long id) {

    }
}
