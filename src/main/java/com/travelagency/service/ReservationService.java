package com.travelagency.service;

import com.travelagency.entity.Reservation;
import com.travelagency.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.travelagency.entity.Passager;
import com.travelagency.repository.PassagerRepository;

@Service
public class ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PassagerRepository passagerRepository;
    
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }
    
    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }
    
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
    
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }

    public Passager savePassager(Passager passager) {
        return passagerRepository.save(passager);
    }
}
