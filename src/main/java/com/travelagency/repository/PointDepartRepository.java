package com.travelagency.repository;

import com.travelagency.entity.PointDepart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointDepartRepository extends JpaRepository<PointDepart, Long> {
}
