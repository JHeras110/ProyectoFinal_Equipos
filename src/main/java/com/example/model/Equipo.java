package com.example.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Indexed;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Modelo de la Tabla Equipos
 * 
 * @param idequipo;
 * @param nombre
 * @param ciudad
 * @param goles
 * @param puntos
 * @param victorias
 * @param empates
 * @param derrotas
 */
@Entity
@Table(name ="equipos")
public class Equipo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idequipos;
    private String nombre;
    private String ciudad;
    private int goles;
    private int puntos;
    private int victorias;
    private int empates;
    private int derrotas;
    public Equipo() {
    }
    public Equipo(String nombre, String ciudad, int goles, int puntos, int victorias, int empates,
            int derrotas) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.goles = goles;
        this.puntos = puntos;
        this.victorias = victorias;
        this.empates = empates;
        this.derrotas = derrotas;
    }
    public int getIdequipo() {
        return idequipos;
    }
    public void setIdequipo(int idequipo) {
        this.idequipos = idequipo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public int getGoles() {
        return goles;
    }
    public void setGoles(int goles) {
        this.goles = goles;
    }
    public int getPuntos() {
        return puntos;
    }
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    public int getVictorias() {
        return victorias;
    }
    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }
    public int getEmpates() {
        return empates;
    }
    public void setEmpates(int empates) {
        this.empates = empates;
    }
    public int getDerrotas() {
        return derrotas;
    }
    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    
}
