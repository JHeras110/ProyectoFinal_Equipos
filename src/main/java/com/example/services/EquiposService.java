package com.example.services;

import java.util.List;

import com.example.model.Equipo;

public interface EquiposService {
    List<Equipo> getEquipos();
    List<Equipo> getEquiposVictorias();
    List<Equipo> getEquiposDerrotas();
    List<Equipo> getEquiposEmpates();
    List<Equipo> getEquiposGoleadores();
    List<Equipo> getEquiposGanadores();
    List<Equipo> getEquiposConPuntos(int puntos);
    String jugarPartidoAleatorio();
    String jugarPartidoConcreto(String equipo1, String equipo2);
    List<Equipo> deleteDescenderEquipo(String equipo);
    List<Equipo> postAscenderEquipo(Equipo equipo);
    
}
