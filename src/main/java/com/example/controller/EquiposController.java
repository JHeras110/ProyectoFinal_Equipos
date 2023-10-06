package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Equipo;
import com.example.services.EquiposService;

/**
 * RestController que nos ayuda con el Mapeo de la aplicacion
 */
@RestController
public class EquiposController {
    
    @Autowired
    EquiposService service;

    @GetMapping(value="equipos")
    public List<Equipo> getEquipos(Model model){
        return service.getEquipos();
    }

    @GetMapping(value="equipos/Victorias")
    public List<Equipo> getEquiposVictorias(){
        return service.getEquiposVictorias();
    }

    @GetMapping(value="equipos/Empates")
    public List<Equipo> getEquiposEmpates(){
        return service.getEquiposEmpates();
    }

    @GetMapping(value="equipos/Derrotas")
    public List<Equipo> getEquiposDerrotas(){
        return service.getEquiposDerrotas();
    }

    @GetMapping(value="equipos/Goles")
    public List<Equipo> getEquiposGoleadores(){
        return service.getEquiposGoleadores();
    }

    @GetMapping(value="equipos/puntos/{puntos}")
    public List<Equipo> getEquiposConPuntos(@PathVariable("puntos") int puntos){
        return service.getEquiposConPuntos(puntos);
    }

    @GetMapping(value="equipos/ganadores")
    public List<Equipo> getEquiposGanadores(){
        return service.getEquiposGanadores();
    }

    @GetMapping(value="jugarPartido")
    public String jugarPartidoAleatorio(){
        return service.jugarPartidoAleatorio();
    }
    
    @GetMapping(value="jugarPartido/{equipo1}/{equipo2}")
    public String jugarPartidoConcreto(@PathVariable("equipo1") String equipo1, @PathVariable("equipo2") String equipo2){
        return  service.jugarPartidoConcreto(equipo1, equipo2);
    }

    @DeleteMapping(value="equipo/{equipoDescender}")
    public List<Equipo> deleteDescenderEquipo(@PathVariable("equipoDescender") String equipo){
        return  service.deleteDescenderEquipo(equipo);
    }

    @PostMapping(value="/equipo")
    public List<Equipo> postAscenderEquipos(@RequestBody Equipo equipo){
        return  service.postAscenderEquipo(equipo);
    }

}
