package com.myOficce.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myOficce.entity.Reserva;
import com.myOficce.entity.Sala;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
  @Query("""
        SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END
        FROM Reserva r
        WHERE r.sala = :sala
        AND r.data = :data
    """)
    boolean existsBySalaAndData(
            @Param("sala") Sala sala,
            @Param("data") LocalDate data
    );
}
