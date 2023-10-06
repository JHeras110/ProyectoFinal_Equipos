package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.model.Equipo;

/**
 * Interfaz que nos ayuda a mapear el objeto con el que vamos a trabajar a
 * la hora de intercambiar informacion con la BBDD
 */
public interface EquiposDao extends JpaRepository<Equipo, Integer> {

    
    @Query("SELECT e FROM Equipo e WHERE e.puntos > :puntos")
    List<Equipo> getJugadoresConPuntos(@Param("puntos") int puntos);
}
