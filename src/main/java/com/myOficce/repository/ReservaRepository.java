package com.myOficce.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myOficce.entity.Reserva;
import com.myOficce.entity.Sala;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    boolean existsBySalaAndData(Sala sala, LocalDate data);
}
