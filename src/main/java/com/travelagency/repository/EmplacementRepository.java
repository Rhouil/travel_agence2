package com.travelagency.repository;

import com.travelagency.entity.Emplacement;
import com.travelagency.entity.Autocar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmplacementRepository extends JpaRepository<Emplacement, Long> {
    List<Emplacement> findByAutocar(Autocar autocar);
    List<Emplacement> findByPassagerIsNull();
}
