package com.travelagency.repository;

import com.travelagency.entity.TypeAutocar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeAutocarRepository extends JpaRepository<TypeAutocar, Long> {
}
