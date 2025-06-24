package com.travelagency.repository;

import com.travelagency.entity.Autocar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutocarRepository extends JpaRepository<Autocar, Long> {
}
