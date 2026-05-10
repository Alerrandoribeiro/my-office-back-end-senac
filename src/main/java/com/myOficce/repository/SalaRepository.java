package com.myOficce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myOficce.entity.Sala;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {
    
}
