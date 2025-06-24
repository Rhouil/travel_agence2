package com.travelagency.repository;

import com.travelagency.entity.Voyage;
import com.travelagency.entity.TypeVoyage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoyageRepository extends JpaRepository<Voyage, Long> {
    List<Voyage> findByTypeVoyage(TypeVoyage typeVoyage);
    List<Voyage> findByHotelVilleNomContaining(String destination);
}
