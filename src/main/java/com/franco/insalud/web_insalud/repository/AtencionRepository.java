package com.franco.insalud.web_insalud.repository;

import com.franco.insalud.web_insalud.entity.Atencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AtencionRepository extends JpaRepository<Atencion, Long> {

 
    List<Atencion> findByFecha(@Param("fecha") LocalDate fecha);
    
    @Query("SELECT a FROM Atencion a WHERE a.paciente.id = :pacienteId")
    List<Atencion> findByPacienteId(@Param("pacienteId") Long pacienteId);
}
