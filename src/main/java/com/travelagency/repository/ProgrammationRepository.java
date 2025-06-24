package com.travelagency.repository;

import com.travelagency.entity.Programmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammationRepository extends JpaRepository<Programmation, Long> {
}
